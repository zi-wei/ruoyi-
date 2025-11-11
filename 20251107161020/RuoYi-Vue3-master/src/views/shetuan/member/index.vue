<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="社团ID" prop="clubId">
        <el-input
          v-model="queryParams.clubId"
          placeholder="请输入社团ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="成员用户ID (关联 sys_user)" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入成员用户ID (关联 sys_user)"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="加入日期" prop="joinDate">
        <el-date-picker clearable
          v-model="queryParams.joinDate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择加入日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="在社团内的角色" prop="roleInClub">
        <el-input
          v-model="queryParams.roleInClub"
          placeholder="请输入在社团内的角色"
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
          v-hasPermi="['shetuan:member:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['shetuan:member:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['shetuan:member:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['shetuan:member:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="memberList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="成员ID" align="center" prop="memberId" />
      <el-table-column label="社团ID" align="center" prop="clubId" />
      <el-table-column label="成员用户ID (关联 sys_user)" align="center" prop="userId" />
      <el-table-column label="加入日期" align="center" prop="joinDate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.joinDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="在社团内的角色" align="center" prop="roleInClub" />
      <el-table-column label="申请状态 (0:待审批, 1:已通过, 2:已拒绝)" align="center" prop="applyStatus" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['shetuan:member:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['shetuan:member:remove']">删除</el-button>
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

    <!-- 添加或修改社团成员对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="memberRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="社团ID" prop="clubId">
          <el-input v-model="form.clubId" placeholder="请输入社团ID" />
        </el-form-item>
        <el-form-item label="成员用户ID (关联 sys_user)" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入成员用户ID (关联 sys_user)" />
        </el-form-item>
        <el-form-item label="加入日期" prop="joinDate">
          <el-date-picker clearable
            v-model="form.joinDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择加入日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="在社团内的角色" prop="roleInClub">
          <el-input v-model="form.roleInClub" placeholder="请输入在社团内的角色" />
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

<script setup name="Member">
import { listMember, getMember, delMember, addMember, updateMember } from "@/api/shetuan/member"

const { proxy } = getCurrentInstance()

const memberList = ref([])
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
    userId: null,
    joinDate: null,
    roleInClub: null,
    applyStatus: null,
  },
  rules: {
    clubId: [
      { required: true, message: "社团ID不能为空", trigger: "blur" }
    ],
    userId: [
      { required: true, message: "成员用户ID (关联 sys_user)不能为空", trigger: "blur" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询社团成员列表 */
function getList() {
  loading.value = true
  listMember(queryParams.value).then(response => {
    memberList.value = response.rows
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
    memberId: null,
    clubId: null,
    userId: null,
    joinDate: null,
    roleInClub: null,
    applyStatus: null,
    delFlag: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null
  }
  proxy.resetForm("memberRef")
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
  ids.value = selection.map(item => item.memberId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加社团成员"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _memberId = row.memberId || ids.value
  getMember(_memberId).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改社团成员"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["memberRef"].validate(valid => {
    if (valid) {
      if (form.value.memberId != null) {
        updateMember(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addMember(form.value).then(response => {
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
  const _memberIds = row.memberId || ids.value
  proxy.$modal.confirm('是否确认删除社团成员编号为"' + _memberIds + '"的数据项？').then(function() {
    return delMember(_memberIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('shetuan/member/export', {
    ...queryParams.value
  }, `member_${new Date().getTime()}.xlsx`)
}

getList()
</script>
