<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="社团名称" prop="clubName">
        <el-input
          v-model="queryParams.clubName"
          placeholder="请输入社团名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="负责人用户ID (关联 sys_user)" prop="leaderId">
        <el-input
          v-model="queryParams.leaderId"
          placeholder="请输入负责人用户ID (关联 sys_user)"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="成立日期" prop="foundDate">
        <el-date-picker clearable
          v-model="queryParams.foundDate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择成立日期">
        </el-date-picker>
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
          v-hasPermi="['shetuan:info:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['shetuan:info:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['shetuan:info:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['shetuan:info:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="infoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="社团ID" align="center" prop="clubId" />
      <el-table-column label="社团名称" align="center" prop="clubName" />
      <el-table-column label="社团类型" align="center" prop="clubType" />
      <el-table-column label="负责人用户ID (关联 sys_user)" align="center" prop="leaderId" />
      <el-table-column label="社团简介" align="center" prop="description" />
      <el-table-column label="成立日期" align="center" prop="foundDate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.foundDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="社团状态 (0:正常, 1:冻结)" align="center" prop="status" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['shetuan:info:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['shetuan:info:remove']">删除</el-button>
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

    <!-- 添加或修改社团信息对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="infoRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="社团名称" prop="clubName">
          <el-input v-model="form.clubName" placeholder="请输入社团名称" />
        </el-form-item>
        <el-form-item label="负责人用户ID (关联 sys_user)" prop="leaderId">
          <el-input v-model="form.leaderId" placeholder="请输入负责人用户ID (关联 sys_user)" />
        </el-form-item>
        <el-form-item label="社团简介" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="成立日期" prop="foundDate">
          <el-date-picker clearable
            v-model="form.foundDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择成立日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="删除标志" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标志" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
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

<script setup name="Info">
import { listInfo, getInfo, delInfo, addInfo, updateInfo } from "@/api/shetuan/info"

const { proxy } = getCurrentInstance()

const infoList = ref([])
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
    clubName: null,
    clubType: null,
    leaderId: null,
    description: null,
    foundDate: null,
    status: null,
  },
  rules: {
    clubName: [
      { required: true, message: "社团名称不能为空", trigger: "blur" }
    ],
    clubType: [
      { required: true, message: "社团类型不能为空", trigger: "change" }
    ],
    leaderId: [
      { required: true, message: "负责人用户ID (关联 sys_user)不能为空", trigger: "blur" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询社团信息列表 */
function getList() {
  loading.value = true
  listInfo(queryParams.value).then(response => {
    infoList.value = response.rows
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
    clubId: null,
    clubName: null,
    clubType: null,
    leaderId: null,
    description: null,
    foundDate: null,
    status: null,
    delFlag: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null
  }
  proxy.resetForm("infoRef")
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

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.clubId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加社团信息"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _clubId = row.clubId || ids.value
  getInfo(_clubId).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改社团信息"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["infoRef"].validate(valid => {
    if (valid) {
      if (form.value.clubId != null) {
        updateInfo(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addInfo(form.value).then(response => {
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
  const _clubIds = row.clubId || ids.value
  proxy.$modal.confirm('是否确认删除社团信息编号为"' + _clubIds + '"的数据项？').then(function() {
    return delInfo(_clubIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('shetuan/info/export', {
    ...queryParams.value
  }, `info_${new Date().getTime()}.xlsx`)
}

getList()
</script>
