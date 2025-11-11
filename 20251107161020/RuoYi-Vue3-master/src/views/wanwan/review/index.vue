<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="陪玩ID" prop="talentId">
        <el-input
          v-model="queryParams.talentId"
          placeholder="请输入陪玩ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="服务评分" prop="ratingScore">
        <el-input
          v-model="queryParams.ratingScore"
          placeholder="请输入服务评分 (1-5星)"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="评价标签" prop="reviewTags">
        <el-input
          v-model="queryParams.reviewTags"
          placeholder="请输入评价标签"
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
          v-hasPermi="['wanwan:review:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['wanwan:review:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['wanwan:review:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['wanwan:review:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="reviewList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="评价ID" align="center" prop="reviewId" />
      <el-table-column label="关联的陪玩订单ID" align="center" prop="orderId" />
      <el-table-column label="被评价的陪玩ID" align="center" prop="talentId" />
      <el-table-column label="给出评价的客户ID" align="center" prop="customerId" />
      <el-table-column label="服务评分 (1-5星)" align="center" prop="ratingScore" />
      <el-table-column label="评价标签 " align="center" prop="reviewTags" />
      <el-table-column label="文字评价内容" align="center" prop="comment" />
      <el-table-column label="是否有回复" align="center" prop="hasReply">
        <template #default="scope">
          {{ getReplyStatusText(scope.row.hasReply) }}
        </template>
      </el-table-column>
      <el-table-column label="陪玩的回复内容" align="center" prop="replyContent" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['wanwan:review:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['wanwan:review:remove']">删除</el-button>
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

    <!-- 添加或修改陪玩专区-评价对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="reviewRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="关联的陪玩订单ID" prop="orderId">
          <el-input v-model="form.orderId" placeholder="请输入关联的陪玩订单ID" />
        </el-form-item>
        <el-form-item label="被评价的陪玩ID" prop="talentId">
          <el-input v-model="form.talentId" placeholder="请输入被评价的陪玩ID" />
        </el-form-item>
        <el-form-item label="给出评价的客户ID" prop="customerId">
          <el-input v-model="form.customerId" placeholder="请输入给出评价的客户ID" />
        </el-form-item>
        <el-form-item label="服务评分 (1-5星)" prop="ratingScore">
          <el-input v-model="form.ratingScore" placeholder="请输入服务评分 (1-5星)" />
        </el-form-item>
        <el-form-item label="评价标签 (如: 声音好听, 很有耐心, 逗号分隔)" prop="reviewTags">
          <el-input v-model="form.reviewTags" placeholder="请输入评价标签 (如: 声音好听, 很有耐心, 逗号分隔)" />
        </el-form-item>
        <el-form-item label="文字评价内容" prop="comment">
          <el-input v-model="form.comment" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="是否有回复 (0=无, 1=有)" prop="hasReply">
          <el-input v-model="form.hasReply" placeholder="请输入是否有回复 (0=无, 1=有)" />
        </el-form-item>
        <el-form-item label="陪玩的回复内容">
          <editor v-model="form.replyContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="删除标志 (0=存在, 1=删除)" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标志 (0=存在, 1=删除)" />
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

<script setup name="Review">
import { listReview, getReview, delReview, addReview, updateReview } from "@/api/wanwan/review"

const { proxy } = getCurrentInstance()

const reviewList = ref([])
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
    talentId: null,
    ratingScore: null,
    reviewTags: null,
  },
  rules: {
    orderId: [
      { required: true, message: "关联的陪玩订单ID不能为空", trigger: "blur" }
    ],
    talentId: [
      { required: true, message: "被评价的陪玩ID不能为空", trigger: "blur" }
    ],
    customerId: [
      { required: true, message: "给出评价的客户ID不能为空", trigger: "blur" }
    ],
    ratingScore: [
      { required: true, message: "服务评分 (1-5星)不能为空", trigger: "blur" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询陪玩专区-评价列表 */
function getList() {
  loading.value = true
  listReview(queryParams.value).then(response => {
    reviewList.value = response.rows
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
    reviewId: null,
    orderId: null,
    talentId: null,
    customerId: null,
    ratingScore: null,
    reviewTags: null,
    comment: null,
    hasReply: null,
    replyContent: null,
    delFlag: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null
  }
  proxy.resetForm("reviewRef")
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

/** 获取回复状态文字 */
function getReplyStatusText(status) {
  const statusMap = {
    '0': '无回复',
    '1': '已回复'
  }
  return statusMap[status] || status
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.reviewId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加陪玩专区-评价"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _reviewId = row.reviewId || ids.value
  getReview(_reviewId).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改陪玩专区-评价"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["reviewRef"].validate(valid => {
    if (valid) {
      if (form.value.reviewId != null) {
        updateReview(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addReview(form.value).then(response => {
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
  const _reviewIds = row.reviewId || ids.value
  proxy.$modal.confirm('是否确认删除陪玩专区-评价编号为"' + _reviewIds + '"的数据项？').then(function() {
    return delReview(_reviewIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('wanwan/review/export', {
    ...queryParams.value
  }, `review_${new Date().getTime()}.xlsx`)
}

getList()
</script>
