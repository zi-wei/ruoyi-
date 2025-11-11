<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="业务任务编号" prop="missionSn">
        <el-input
          v-model="queryParams.missionSn"
          placeholder="请输入业务任务编号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="目标地图" prop="gameMap">
        <el-input
          v-model="queryParams.gameMap"
          placeholder="请输入目标地图"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务酬劳" prop="rewardAmount">
        <el-input
          v-model="queryParams.rewardAmount"
          placeholder="请输入任务酬劳"
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
          v-hasPermi="['hanghang:mission:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['hanghang:mission:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['hanghang:mission:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['hanghang:mission:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="missionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="任务ID" align="center" prop="missionId" />
      <el-table-column label="业务任务编号" align="center" prop="missionSn" />
      <el-table-column label="发布任务的客户ID (老板)" align="center" prop="publisherId" />
      <el-table-column label="接取任务的专家ID (悬赏时可为空)" align="center" prop="specialistId" />
      <el-table-column label="任务状态" align="center" prop="missionStatus">
        <template #default="scope">
          <span>{{ getMissionStatusText(scope.row.missionStatus) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="目标地图 (字典项)" align="center" prop="gameMap" />
      <el-table-column label="核心目标描述 (如：从电视台安全撤离)" align="center" prop="objective" />
      <el-table-column label="保底物资价值" align="center" prop="guaranteedAssetValue" />
      <el-table-column label="任务酬劳" align="center" prop="rewardAmount" />
      <el-table-column label="已支付定金" align="center" prop="depositAmount" />
      <el-table-column label="任务最晚完成时间" align="center" prop="deadline" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.deadline, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="任务补充说明" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['hanghang:mission:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['hanghang:mission:remove']">删除</el-button>
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

    <!-- 添加或修改护航专区-任务对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="missionRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="业务任务编号" prop="missionSn">
          <el-input v-model="form.missionSn" placeholder="请输入业务任务编号" />
        </el-form-item>
        <el-form-item label="发布任务的客户ID (老板)" prop="publisherId">
          <el-input v-model="form.publisherId" placeholder="请输入发布任务的客户ID (老板)" />
        </el-form-item>
        <el-form-item label="接取任务的专家ID (悬赏时可为空)" prop="specialistId">
          <el-input v-model="form.specialistId" placeholder="请输入接取任务的专家ID (悬赏时可为空)" />
        </el-form-item>
        <el-form-item label="目标地图 (字典项)" prop="gameMap">
          <el-input v-model="form.gameMap" placeholder="请输入目标地图 (字典项)" />
        </el-form-item>
        <el-form-item label="核心目标描述 (如：从电视台安全撤离)" prop="objective">
          <el-input v-model="form.objective" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="保底物资价值" prop="guaranteedAssetValue">
          <el-input v-model="form.guaranteedAssetValue" placeholder="请输入保底物资价值" />
        </el-form-item>
        <el-form-item label="任务酬劳" prop="rewardAmount">
          <el-input v-model="form.rewardAmount" placeholder="请输入任务酬劳" />
        </el-form-item>
        <el-form-item label="已支付定金" prop="depositAmount">
          <el-input v-model="form.depositAmount" placeholder="请输入已支付定金" />
        </el-form-item>
        <el-form-item label="任务最晚完成时间" prop="deadline">
          <el-date-picker clearable
            v-model="form.deadline"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择任务最晚完成时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="删除标志 (0=存在, 1=删除)" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标志 (0=存在, 1=删除)" />
        </el-form-item>
        <el-form-item label="任务补充说明" prop="remark">
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

<script setup name="Mission">
import { listMission, getMission, delMission, addMission, updateMission } from "@/api/hanghang/mission"

const { proxy } = getCurrentInstance()

const missionList = ref([])
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
    missionSn: null,
    gameMap: null,
    rewardAmount: null,
  },
  rules: {
    missionSn: [
      { required: true, message: "业务任务编号不能为空", trigger: "blur" }
    ],
    publisherId: [
      { required: true, message: "发布任务的客户ID (老板)不能为空", trigger: "blur" }
    ],
    missionStatus: [
      { required: true, message: "任务状态不能为空", trigger: "change" }
    ],
    gameMap: [
      { required: true, message: "目标地图 (字典项)不能为空", trigger: "blur" }
    ],
    objective: [
      { required: true, message: "核心目标描述 (如：从电视台安全撤离)不能为空", trigger: "blur" }
    ],
    rewardAmount: [
      { required: true, message: "任务酬劳不能为空", trigger: "blur" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询护航专区-任务列表 */
function getList() {
  loading.value = true
  listMission(queryParams.value).then(response => {
    missionList.value = response.rows
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
    missionId: null,
    missionSn: null,
    publisherId: null,
    specialistId: null,
    missionStatus: null,
    gameMap: null,
    objective: null,
    guaranteedAssetValue: null,
    rewardAmount: null,
    depositAmount: null,
    deadline: null,
    delFlag: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null
  }
  proxy.resetForm("missionRef")
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

/** 获取任务状态文字 */
function getMissionStatusText(status) {
  const statusMap = {
    '10': '悬赏中',
    '20': '进行中',
    '30': '待验收',
    '40': '已完成',
    '50': '已失败',
    '60': '已取消'
  }
  return statusMap[status] || status
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.missionId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加护航专区-任务"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _missionId = row.missionId || ids.value
  getMission(_missionId).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改护航专区-任务"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["missionRef"].validate(valid => {
    if (valid) {
      if (form.value.missionId != null) {
        updateMission(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addMission(form.value).then(response => {
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
  const _missionIds = row.missionId || ids.value
  proxy.$modal.confirm('是否确认删除护航专区-任务编号为"' + _missionIds + '"的数据项？').then(function() {
    return delMission(_missionIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('hanghang/mission/export', {
    ...queryParams.value
  }, `mission_${new Date().getTime()}.xlsx`)
}

getList()
</script>
