<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="关联的护航任务ID" prop="missionId">
        <el-input
          v-model="queryParams.missionId"
          placeholder="请输入关联的护航任务ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户是否已确认" prop="isConfirmedByCustomer">
        <el-input
          v-model="queryParams.isConfirmedByCustomer"
          placeholder="请输入客户是否已确认"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['hanghang:voucher:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['hanghang:voucher:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['hanghang:voucher:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['hanghang:voucher:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="voucherList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="凭证ID" align="center" prop="voucherId" />
      <el-table-column label="关联的护航任务ID" align="center" prop="missionId" />
      <el-table-column label="上传者用户ID (通常是护航专家)" align="center" prop="uploaderId" />
      <el-table-column label="凭证类型" align="center" prop="voucherType">
        <template #default="scope">
          <span>{{ getVoucherTypeText(scope.row.voucherType) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="凭证文件URL或视频链接" align="center" prop="voucherUrl" />
      <el-table-column label="凭证描述 (如：撤离时结算界面截图)" align="center" prop="description" width="100">
        <template #default="scope">
          <image-preview :src="scope.row.description" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="最终物资价值 (根据凭证填写)" align="center" prop="finalAssetValue" />
      <el-table-column label="客户是否已确认" align="center" prop="isConfirmedByCustomer">
        <template #default="scope">
          <span>{{ getConfirmationText(scope.row.isConfirmedByCustomer) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="管理员审核状态" align="center" prop="adminAuditStatus">
        <template #default="scope">
          <span>{{ getAuditStatusText(scope.row.adminAuditStatus) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['hanghang:voucher:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['hanghang:voucher:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改护航专区-任务凭证对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="voucherRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="关联的护航任务ID" prop="missionId">
          <el-input v-model="form.missionId" placeholder="请输入关联的护航任务ID" />
        </el-form-item>
        <el-form-item label="上传者用户ID (通常是护航专家)" prop="uploaderId">
          <el-input v-model="form.uploaderId" placeholder="请输入上传者用户ID (通常是护航专家)" />
        </el-form-item>
        <el-form-item label="凭证文件URL或视频链接" prop="voucherUrl">
          <el-input v-model="form.voucherUrl" placeholder="请输入凭证文件URL或视频链接" />
        </el-form-item>
        <el-form-item label="凭证描述 (如：撤离时结算界面截图)" prop="description">
          <image-upload v-model="form.description"/>
        </el-form-item>
        <el-form-item label="最终物资价值 (根据凭证填写)" prop="finalAssetValue">
          <el-input v-model="form.finalAssetValue" placeholder="请输入最终物资价值 (根据凭证填写)" />
        </el-form-item>
        <el-form-item label="客户是否已确认" prop="isConfirmedByCustomer">
          <el-input v-model="form.isConfirmedByCustomer" placeholder="请输入客户是否已确认" />
        </el-form-item>
        <el-form-item label="删除标志 (0=存在, 1=删除)" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标志 (0=存在, 1=删除)" />
        </el-form-item>
        <el-form-item label="审核备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Voucher">
import { listVoucher, getVoucher, delVoucher, addVoucher, updateVoucher } from "@/api/hanghang/voucher"

const { proxy } = getCurrentInstance()

const voucherList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    missionId: null,
    isConfirmedByCustomer: null,
  },
  rules: {
    missionId: [
      { required: true, message: "关联的护航任务ID不能为空", trigger: "blur" }
    ],
    uploaderId: [
      { required: true, message: "上传者用户ID (通常是护航专家)不能为空", trigger: "blur" }
    ],
    voucherType: [
      { required: true, message: "凭证类型不能为空", trigger: "change" }
    ],
    voucherUrl: [
      { required: true, message: "凭证文件URL或视频链接不能为空", trigger: "blur" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询护航专区-任务凭证列表 */
function getList() {
  loading.value = true
  listVoucher(queryParams.value).then(response => {
    voucherList.value = response.rows
    total.value = response.total
    loading.value = false
  })
}

// 取消按钮
function cancel() {
  open.value = false
  reset()
}

// 表单重置
function reset() {
  form.value = {
    voucherId: null,
    missionId: null,
    uploaderId: null,
    voucherType: null,
    voucherUrl: null,
    description: null,
    finalAssetValue: null,
    isConfirmedByCustomer: null,
    adminAuditStatus: null,
    delFlag: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null
  }
  proxy.resetForm("voucherRef")
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef")
  handleQuery()
}

/** 获取客户确认状态文字 */
function getConfirmationText(status) {
  const statusMap = {
    '0': '未确认',
    '1': '已确认',
    '2': '有争议'
  }
  return statusMap[status] || status
}

/** 获取管理员审核状态文字 */
function getAuditStatusText(status) {
  const statusMap = {
    '0': '未审核',
    '1': '审核通过',
    '2': '审核不通过'
  }
  return statusMap[status] || status
}

/** 获取凭证类型文字 */
function getVoucherTypeText(type) {
  const typeMap = {
    '1': '截图',
    '2': '录屏链接'
  }
  return typeMap[type] || type
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.voucherId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加护航专区-任务凭证"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _voucherId = row.voucherId || ids.value
  getVoucher(_voucherId).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改护航专区-任务凭证"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["voucherRef"].validate(valid => {
    if (valid) {
      if (form.value.voucherId != null) {
        updateVoucher(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addVoucher(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功")
          open.value = false
          getList()
        })
      }
    }
  })
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _voucherIds = row.voucherId || ids.value
  proxy.$modal.confirm('是否确认删除护航专区-任务凭证编号为"' + _voucherIds + '"的数据项？').then(function() {
    return delVoucher(_voucherIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('hanghang/voucher/export', {
    ...queryParams.value
  }, `voucher_${new Date().getTime()}.xlsx`)
}

getList()
</script>
