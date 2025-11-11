<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="打手代号" prop="nickname">
        <el-input
          v-model="queryParams.nickname"
          placeholder="请输入打手代号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="游戏段位/等级" prop="gameRank">
        <el-input
          v-model="queryParams.gameRank"
          placeholder="请输入游戏段位/等级"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="擅长领域" prop="specialties">
        <el-input
          v-model="queryParams.specialties"
          placeholder="请输入擅长领域"
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
          v-hasPermi="['hanghang:specialist:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['hanghang:specialist:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['hanghang:specialist:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['hanghang:specialist:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="specialistList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="打手ID" align="center" prop="specialistId" />
      <el-table-column label="打手代号" align="center" prop="nickname" />
      <el-table-column label="个人形象照片URL" align="center" prop="avatar" width="100">
        <template #default="scope">
          <image-preview :src="scope.row.avatar" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="游戏段位/等级" align="center" prop="gameRank" />
      <el-table-column label="任务成功率 (%)" align="center" prop="successRate" />
      <el-table-column label="场均带出资产价值" align="center" prop="avgAssetValue" />
      <el-table-column label="擅长领域 (如: 电视台专家, 北山指挥官, 跑刀王, 逗号分隔)" align="center" prop="specialties" />
      <el-table-column label="个人简介 (展示战绩和能力)" align="center" prop="profileBio" />
      <el-table-column label="接单所需定金比例 (%)" align="center" prop="depositRatio" />
      <el-table-column label="状态" align="center" prop="status">
        <template #default="scope">
          <span>{{ getSpecialistStatusText(scope.row.status) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['hanghang:specialist:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['hanghang:specialist:remove']">删除</el-button>
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

    <!-- 添加或修改护航专区-打手对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="specialistRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="打手代号" prop="nickname">
          <el-input v-model="form.nickname" placeholder="请输入打手代号" />
        </el-form-item>
        <el-form-item label="个人形象照片URL" prop="avatar">
          <image-upload v-model="form.avatar"/>
        </el-form-item>
        <el-form-item label="游戏段位/等级" prop="gameRank">
          <el-input v-model="form.gameRank" placeholder="请输入游戏段位/等级" />
        </el-form-item>
        <el-form-item label="任务成功率 (%)" prop="successRate">
          <el-input v-model="form.successRate" placeholder="请输入任务成功率 (%)" />
        </el-form-item>
        <el-form-item label="场均带出资产价值" prop="avgAssetValue">
          <el-input v-model="form.avgAssetValue" placeholder="请输入场均带出资产价值" />
        </el-form-item>
        <el-form-item label="擅长领域 (如: 电视台专家, 北山指挥官, 跑刀王, 逗号分隔)" prop="specialties">
          <el-input v-model="form.specialties" placeholder="请输入擅长领域 (如: 电视台专家, 北山指挥官, 跑刀王, 逗号分隔)" />
        </el-form-item>
        <el-form-item label="个人简介 (展示战绩和能力)" prop="profileBio">
          <el-input v-model="form.profileBio" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="接单所需定金比例 (%)" prop="depositRatio">
          <el-input v-model="form.depositRatio" placeholder="请输入接单所需定金比例 (%)" />
        </el-form-item>
        <el-form-item label="删除标志 (0=存在, 1=删除)" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标志 (0=存在, 1=删除)" />
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

<script setup name="Specialist">
import { listSpecialist, getSpecialist, delSpecialist, addSpecialist, updateSpecialist } from "@/api/hanghang/specialist"

const { proxy } = getCurrentInstance()

const specialistList = ref([])
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
    nickname: null,
    gameRank: null,
    specialties: null,
  },
  rules: {
    nickname: [
      { required: true, message: "打手代号不能为空", trigger: "blur" }
    ],
    status: [
      { required: true, message: "状态不能为空", trigger: "change" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询护航专区-打手列表 */
function getList() {
  loading.value = true
  listSpecialist(queryParams.value).then(response => {
    specialistList.value = response.rows
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
    specialistId: null,
    nickname: null,
    avatar: null,
    gameRank: null,
    successRate: null,
    avgAssetValue: null,
    specialties: null,
    profileBio: null,
    depositRatio: null,
    status: null,
    delFlag: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null
  }
  proxy.resetForm("specialistRef")
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

/** 获取打手状态文字 */
function getSpecialistStatusText(status) {
  const statusMap = {
    '0': '可接单',
    '1': '任务中',
    '2': '休息'
  }
  return statusMap[status] || status
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.specialistId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加护航专区-打手"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _specialistId = row.specialistId || ids.value
  getSpecialist(_specialistId).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改护航专区-打手"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["specialistRef"].validate(valid => {
    if (valid) {
      if (form.value.specialistId != null) {
        updateSpecialist(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addSpecialist(form.value).then(response => {
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
  const _specialistIds = row.specialistId || ids.value
  proxy.$modal.confirm('是否确认删除护航专区-打手编号为"' + _specialistIds + '"的数据项？').then(function() {
    return delSpecialist(_specialistIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('hanghang/specialist/export', {
    ...queryParams.value
  }, `specialist_${new Date().getTime()}.xlsx`)
}

getList()
</script>
