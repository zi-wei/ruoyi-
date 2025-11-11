<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="预约资源ID" prop="resourceId">
        <el-input
          v-model="queryParams.resourceId"
          placeholder="请输入预约资源ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关联活动ID (可选)" prop="activityId">
        <el-input
          v-model="queryParams.activityId"
          placeholder="请输入关联活动ID (可选)"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预约人ID (关联 sys_user)" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入预约人ID (关联 sys_user)"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预约开始时间" prop="startTime">
        <el-date-picker clearable
          v-model="queryParams.startTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择预约开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="预约结束时间" prop="endTime">
        <el-date-picker clearable
          v-model="queryParams.endTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择预约结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="预约数量" prop="bookingQuantity">
        <el-input
          v-model="queryParams.bookingQuantity"
          placeholder="请输入预约数量"
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
          v-hasPermi="['shetuan:booking:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['shetuan:booking:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['shetuan:booking:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['shetuan:booking:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="bookingList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="预约ID" align="center" prop="bookingId" />
      <el-table-column label="预约资源ID" align="center" prop="resourceId" />
      <el-table-column label="关联活动ID (可选)" align="center" prop="activityId" />
      <el-table-column label="预约人ID (关联 sys_user)" align="center" prop="userId" />
      <el-table-column label="预约开始时间" align="center" prop="startTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预约结束时间" align="center" prop="endTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预约数量" align="center" prop="bookingQuantity" />
      <el-table-column label="预约状态 (0:待审, 1:通过, 2:驳回)" align="center" prop="status" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['shetuan:booking:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['shetuan:booking:remove']">删除</el-button>
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

    <!-- 添加或修改资源预约对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="bookingRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="预约资源ID" prop="resourceId">
          <el-input v-model="form.resourceId" placeholder="请输入预约资源ID" />
        </el-form-item>
        <el-form-item label="关联活动ID (可选)" prop="activityId">
          <el-input v-model="form.activityId" placeholder="请输入关联活动ID (可选)" />
        </el-form-item>
        <el-form-item label="预约人ID (关联 sys_user)" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入预约人ID (关联 sys_user)" />
        </el-form-item>
        <el-form-item label="预约开始时间" prop="startTime">
          <el-date-picker clearable
            v-model="form.startTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择预约开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="预约结束时间" prop="endTime">
          <el-date-picker clearable
            v-model="form.endTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择预约结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="预约数量" prop="bookingQuantity">
          <el-input v-model="form.bookingQuantity" placeholder="请输入预约数量" />
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

<script setup name="Booking">
import { listBooking, getBooking, delBooking, addBooking, updateBooking } from "@/api/shetuan/booking"

const { proxy } = getCurrentInstance()

const bookingList = ref([])
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
    resourceId: null,
    activityId: null,
    userId: null,
    startTime: null,
    endTime: null,
    bookingQuantity: null,
    status: null,
  },
  rules: {
    resourceId: [
      { required: true, message: "预约资源ID不能为空", trigger: "blur" }
    ],
    userId: [
      { required: true, message: "预约人ID (关联 sys_user)不能为空", trigger: "blur" }
    ],
    startTime: [
      { required: true, message: "预约开始时间不能为空", trigger: "blur" }
    ],
    endTime: [
      { required: true, message: "预约结束时间不能为空", trigger: "blur" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询资源预约列表 */
function getList() {
  loading.value = true
  listBooking(queryParams.value).then(response => {
    bookingList.value = response.rows
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
    bookingId: null,
    resourceId: null,
    activityId: null,
    userId: null,
    startTime: null,
    endTime: null,
    bookingQuantity: null,
    status: null,
    delFlag: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null
  }
  proxy.resetForm("bookingRef")
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
  ids.value = selection.map(item => item.bookingId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加资源预约"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _bookingId = row.bookingId || ids.value
  getBooking(_bookingId).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改资源预约"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["bookingRef"].validate(valid => {
    if (valid) {
      if (form.value.bookingId != null) {
        updateBooking(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addBooking(form.value).then(response => {
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
  const _bookingIds = row.bookingId || ids.value
  proxy.$modal.confirm('是否确认删除资源预约编号为"' + _bookingIds + '"的数据项？').then(function() {
    return delBooking(_bookingIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('shetuan/booking/export', {
    ...queryParams.value
  }, `booking_${new Date().getTime()}.xlsx`)
}

getList()
</script>
