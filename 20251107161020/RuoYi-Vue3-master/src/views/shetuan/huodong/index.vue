<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="所属社团ID" prop="clubId">
        <el-input
          v-model="queryParams.clubId"
          placeholder="请输入所属社团ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动名称" prop="activityName">
        <el-input
          v-model="queryParams.activityName"
          placeholder="请输入活动名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开始时间" prop="startTime">
        <el-date-picker clearable
          v-model="queryParams.startTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="endTime">
        <el-date-picker clearable
          v-model="queryParams.endTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="活动地点描述" prop="location">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入活动地点描述"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="当前审批人ID (关联 sys_user)" prop="approvalUserId">
        <el-input
          v-model="queryParams.approvalUserId"
          placeholder="请输入当前审批人ID (关联 sys_user)"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最大参与人数" prop="maxParticipants">
        <el-input
          v-model="queryParams.maxParticipants"
          placeholder="请输入最大参与人数"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="实际参与人数" prop="actualParticipants">
        <el-input
          v-model="queryParams.actualParticipants"
          placeholder="请输入实际参与人数"
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
          v-hasPermi="['shetuan:huodong:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['shetuan:huodong:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['shetuan:huodong:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['shetuan:huodong:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="huodongList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="活动ID" align="center" prop="activityId" />
      <el-table-column label="所属社团ID" align="center" prop="clubId" />
      <el-table-column label="活动名称" align="center" prop="activityName" />
      <el-table-column label="开始时间" align="center" prop="startTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="endTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="活动地点描述" align="center" prop="location" />
      <el-table-column label="审批状态 (0:待审, 1:通过, 2:驳回)" align="center" prop="status" />
      <el-table-column label="当前审批人ID (关联 sys_user)" align="center" prop="approvalUserId" />
      <el-table-column label="最大参与人数" align="center" prop="maxParticipants" />
      <el-table-column label="实际参与人数" align="center" prop="actualParticipants" />
      <el-table-column label="备注 (可用于存储审批意见)" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['shetuan:huodong:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['shetuan:huodong:remove']">删除</el-button>
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

    <!-- 添加或修改活动信息对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="huodongRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="所属社团ID" prop="clubId">
          <el-input v-model="form.clubId" placeholder="请输入所属社团ID" />
        </el-form-item>
        <el-form-item label="活动名称" prop="activityName">
          <el-input v-model="form.activityName" placeholder="请输入活动名称" />
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-date-picker clearable
            v-model="form.startTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-date-picker clearable
            v-model="form.endTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="活动地点描述" prop="location">
          <el-input v-model="form.location" placeholder="请输入活动地点描述" />
        </el-form-item>
        <el-form-item label="当前审批人ID (关联 sys_user)" prop="approvalUserId">
          <el-input v-model="form.approvalUserId" placeholder="请输入当前审批人ID (关联 sys_user)" />
        </el-form-item>
        <el-form-item label="最大参与人数" prop="maxParticipants">
          <el-input v-model="form.maxParticipants" placeholder="请输入最大参与人数" />
        </el-form-item>
        <el-form-item label="实际参与人数" prop="actualParticipants">
          <el-input v-model="form.actualParticipants" placeholder="请输入实际参与人数" />
        </el-form-item>
        <el-form-item label="删除标志" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标志" />
        </el-form-item>
        <el-form-item label="备注 (可用于存储审批意见)" prop="remark">
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

<script setup name="Huodong">
import { listHuodong, getHuodong, delHuodong, addHuodong, updateHuodong } from "@/api/shetuan/huodong"

const { proxy } = getCurrentInstance()

const huodongList = ref([])
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
    clubId: null,
    activityName: null,
    startTime: null,
    endTime: null,
    location: null,
    status: null,
    approvalUserId: null,
    maxParticipants: null,
    actualParticipants: null,
  },
  rules: {
    clubId: [
      { required: true, message: "所属社团ID不能为空", trigger: "blur" }
    ],
    activityName: [
      { required: true, message: "活动名称不能为空", trigger: "blur" }
    ],
    startTime: [
      { required: true, message: "开始时间不能为空", trigger: "blur" }
    ],
    endTime: [
      { required: true, message: "结束时间不能为空", trigger: "blur" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询活动信息列表 */
function getList() {
  loading.value = true
  listHuodong(queryParams.value).then(response => {
    huodongList.value = response.rows
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
    activityId: null,
    clubId: null,
    activityName: null,
    startTime: null,
    endTime: null,
    location: null,
    status: null,
    approvalUserId: null,
    maxParticipants: null,
    actualParticipants: null,
    delFlag: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null
  }
  proxy.resetForm("huodongRef")
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
  ids.value = selection.map(item => item.activityId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加活动信息"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _activityId = row.activityId || ids.value
  getHuodong(_activityId).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改活动信息"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["huodongRef"].validate(valid => {
    if (valid) {
      if (form.value.activityId != null) {
        updateHuodong(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addHuodong(form.value).then(response => {
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
  const _activityIds = row.activityId || ids.value
  proxy.$modal.confirm('是否确认删除活动信息编号为"' + _activityIds + '"的数据项？').then(function() {
    return delHuodong(_activityIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('shetuan/huodong/export', {
    ...queryParams.value
  }, `huodong_${new Date().getTime()}.xlsx`)
}

getList()
</script>
