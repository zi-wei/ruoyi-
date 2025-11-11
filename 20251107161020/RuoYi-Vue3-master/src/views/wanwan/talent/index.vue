<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="陪玩昵称" prop="nickname">
        <el-input
          v-model="queryParams.nickname"
          placeholder="请输入陪玩昵称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-input
          v-model="queryParams.gender"
          placeholder="请输入性别"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="个人标签" prop="profileTags">
        <el-input
          v-model="queryParams.profileTags"
          placeholder="请输入个人标签"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="时薪" prop="hourlyRate">
        <el-input
          v-model="queryParams.hourlyRate"
          placeholder="请输入时薪"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="综合评分" prop="averageRating">
        <el-input
          v-model="queryParams.averageRating"
          placeholder="请输入综合评分"
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
          v-hasPermi="['wanwan:talent:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['wanwan:talent:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['wanwan:talent:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['wanwan:talent:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="talentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="人才ID" align="center" prop="talentId" />
      <el-table-column v-if="false" label="用户ID" align="center" prop="userId" />
      <el-table-column label="陪玩昵称" align="center" prop="nickname" />
      <el-table-column label="个人形象照片URL" align="center" prop="avatar" width="100">
        <template #default="scope">
          <image-preview :src="scope.row.avatar" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="性别" align="center" prop="gender" />
      <el-table-column label="声音样本文件URL" align="center" prop="voiceSampleUrl" />
      <el-table-column label="个人标签 " align="center" prop="profileTags" />
      <el-table-column label="时薪 (单位: 元/小时)" align="center" prop="hourlyRate" />
      <el-table-column label="局薪 (单位: 元/局)" align="center" prop="perGameRate" />
      <el-table-column label="综合评分 (1.00-5.00)" align="center" prop="averageRating" />
      <el-table-column label="累计完成订单数" align="center" prop="totalOrdersCompleted" />
      <el-table-column label="在线状态" align="center" prop="onlineStatus">
        <template #default="scope">
          <span>{{ getOnlineStatusText(scope.row.onlineStatus) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['wanwan:talent:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['wanwan:talent:remove']">删除</el-button>
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

    <!-- 添加或修改陪玩专区-人才库对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="talentRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="陪玩昵称" prop="nickname">
          <el-input v-model="form.nickname" placeholder="请输入陪玩昵称" />
        </el-form-item>
        <el-form-item v-if="false" label="用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户ID" />
        </el-form-item>
        <el-form-item label="个人形象照片URL" prop="avatar">
          <image-upload v-model="form.avatar"/>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-input v-model="form.gender" placeholder="请输入性别" />
        </el-form-item>
        <el-form-item label="声音样本文件URL (吸引用户的个人介绍)" prop="voiceSampleUrl">
          <el-input v-model="form.voiceSampleUrl" placeholder="请输入声音样本文件URL (吸引用户的个人介绍)" />
        </el-form-item>
        <el-form-item label="个人标签 (如：话痨, 搞笑, 技术流, 逗号分隔)" prop="profileTags">
          <el-input v-model="form.profileTags" placeholder="请输入个人标签 (如：话痨, 搞笑, 技术流, 逗号分隔)" />
        </el-form-item>
        <el-form-item label="时薪 (单位: 元/小时)" prop="hourlyRate">
          <el-input v-model="form.hourlyRate" placeholder="请输入时薪 (单位: 元/小时)" />
        </el-form-item>
        <el-form-item label="局薪 (单位: 元/局)" prop="perGameRate">
          <el-input v-model="form.perGameRate" placeholder="请输入局薪 (单位: 元/局)" />
        </el-form-item>
        <el-form-item label="综合评分 (1.00-5.00)" prop="averageRating">
          <el-input v-model="form.averageRating" placeholder="请输入综合评分 (1.00-5.00)" />
        </el-form-item>
        <el-form-item label="累计完成订单数" prop="totalOrdersCompleted">
          <el-input v-model="form.totalOrdersCompleted" placeholder="请输入累计完成订单数" />
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

<script setup name="Talent">
import { listTalent, getTalent, delTalent, addTalent, updateTalent } from "@/api/wanwan/talent"

const { proxy } = getCurrentInstance()

const talentList = ref([])
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
    gender: null,
    profileTags: null,
    hourlyRate: null,
    averageRating: null,
  },
  rules: {
    nickname: [
      { required: true, message: "陪玩昵称不能为空", trigger: "blur" }
    ],
    onlineStatus: [
      { required: true, message: "在线状态不能为空", trigger: "change" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询陪玩专区-人才库列表 */
function getList() {
  loading.value = true
  listTalent(queryParams.value).then(response => {
    talentList.value = response.rows
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
    talentId: null,
    nickname: null,
    avatar: null,
    gender: null,
    voiceSampleUrl: null,
    profileTags: null,
    hourlyRate: null,
    perGameRate: null,
    averageRating: null,
    totalOrdersCompleted: null,
    onlineStatus: null,
    delFlag: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null
  }
  proxy.resetForm("talentRef")
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

/** 获取在线状态文字 */
function getOnlineStatusText(status) {
  const statusMap = {
    '0': '离线',
    '1': '在线空闲',
    '2': '服务中'
  }
  return statusMap[status] || status
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.talentId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加陪玩专区-人才库"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _talentId = row.talentId || ids.value
  getTalent(_talentId).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改陪玩专区-人才库"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["talentRef"].validate(valid => {
    if (valid) {
      if (form.value.talentId != null) {
        updateTalent(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addTalent(form.value).then(response => {
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
  const _talentIds = row.talentId || ids.value
  proxy.$modal.confirm('是否确认删除陪玩专区-人才库编号为"' + _talentIds + '"的数据项？').then(function() {
    return delTalent(_talentIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('wanwan/talent/export', {
    ...queryParams.value
  }, `talent_${new Date().getTime()}.xlsx`)
}

getList()
</script>
