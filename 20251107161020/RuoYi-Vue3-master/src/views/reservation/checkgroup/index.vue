<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item >
        <el-input
            v-model="queryParams.keyword"
            placeholder="编码/名称/助记码"
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
            v-hasPermi="['reservation:checkgroup:add']"
        >新增
        </el-button>
      </el-col>
    </el-row>

  <el-table v-loading="loading" :data="checkgroupList" @selection-change="handleSelectionChange">
      <el-table-column type="index" align="center" label="序号" width="150"/>
      <el-table-column label="编码" align="center" prop="code"/>
      <el-table-column label="名称" align="center" prop="name"/>
      <el-table-column label="助记码" align="center" prop="helpCode"/>
      <el-table-column label="性别" align="center" prop="sex">
        <template #default="scope">
          <dict-tag :options="(health_sex && health_sex.length) ? health_sex : sys_user_sex" :value="scope.row.sex"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['reservation:checkgroup:edit']">修改
          </el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['reservation:checkgroup:remove']">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
        v-show="total > 0"
        :total="total"
        v-model:page="queryParams.pageNum"
        v-model:limit="queryParams.pageSize"
        @pagination="getList"
    />

    <!-- 添加或修改检查组对话框 -->
    <el-dialog :title="title" v-model="open" width="600px" append-to-body>
      <el-form ref="checkgroupRef" :model="form" :rules="rules" label-width="80px">
        <el-tabs v-model="activeTab" type="card">
          <el-tab-pane label="基本信息" name="first">
            <el-row>
              <el-col :span="12">
                <el-form-item label="编码" prop="code">
                  <el-input v-model="form.code" placeholder="请输入编码"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="名称" prop="name">
                  <el-input v-model="form.name" placeholder="请输入名称"/>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="助记码" prop="helpCode">
                  <el-input v-model="form.helpCode" placeholder="请输入助记码"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="性别" prop="sex">
                  <el-select v-model="form.sex" placeholder="请选择性别" clearable>
                    <template v-if="health_sex && health_sex.length">
                      <el-option
                          v-for="dict in health_sex"
                          :key="dict.value"
                          :label="dict.label"
                          :value="dict.value"
                      ></el-option>
                    </template>
                    <template v-else-if="sys_user_sex && sys_user_sex.length">
                      <el-option
                          v-for="dict in sys_user_sex"
                          :key="dict.value"
                          :label="dict.label"
                          :value="dict.value"
                      ></el-option>
                    </template>
                    <template v-else>
                      <el-option :label="'男'" :value="0" />
                      <el-option :label="'女'" :value="1" />
                      <el-option :label="'未知'" :value="2" />
                    </template>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <el-form-item label="注意事项" prop="attention">
                  <el-input v-model="form.attention" placeholder="请输入注意事项"/>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <el-form-item label="说明" prop="remark">
                  <el-input v-model="form.remark" type="textarea" placeholder="请输入说明"/>
                </el-form-item>
              </el-col>
            </el-row>
          </el-tab-pane>

          <el-tab-pane label="检查项信息" name="second">
            <div class="check-scroll">
              <el-table
                  :data="checkItemList"
                  height="400"
                  ref="checkItemTableRef"
                  row-key="id"
                  @selection-change="handleCheckItemSelection"
              >
                <el-table-column type="selection" width="55"></el-table-column>
                <el-table-column label="项目编码" prop="code"/>
                <el-table-column label="项目名称" prop="name"/>
                <el-table-column label="项目说明" prop="remark"/>
              </el-table>
            </div>
          </el-tab-pane>
        </el-tabs>
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

<script setup name="Checkgroup">
import {
  listCheckgroup,
  getCheckgroup,
  delCheckgroup,
  addCheckgroup,
  updateCheckgroup
} from "@/api/reservation/checkgroup"
import { getAllCheckitems } from "@/api/reservation/checkitem"

const { proxy } = getCurrentInstance()
// 尝试加载 health_sex，若后端未配置则回退到系统用户性别字典 sys_user_sex
const { health_sex, sys_user_sex } = proxy.useDict('health_sex', 'sys_user_sex')

// refs
const checkgroupList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")
const activeTab = ref("first")
const checkItemList = ref([])
const selectedCheckItems = ref([])
const checkItemTableRef = ref()

// reactive data
const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    keyword: null,
  },
  rules: {
    code: [{ required: true, message: "编码不能为空", trigger: "blur" }],
    name: [{ required: true, message: "名称不能为空", trigger: "blur" }]
  }
})

const { queryParams, form, rules } = toRefs(data)

// 工具函数：安全转 number
function safeNumber(val) {
  if (val == null) return null
  const num = Number(val)
  return isNaN(num) ? val : num
}

// 获取所有检查项（确保 id 为 number）
function getAllCheckItems() {
  return getAllCheckitems({}).then(response => {
    checkItemList.value = response.data;
  })
}

// 查询列表
function getList() {
  loading.value = true
  // 构造查询参数：将 keyword 转为模糊匹配字符串（前后加 %），后端 Mapper 使用 `like #{}` 接收
  const params = { ...queryParams.value }
  if (params.keyword) params.keyword = `%${params.keyword}%`

  listCheckgroup(params).then(response => {
    checkgroupList.value = response.rows.map(item => ({
      ...item,
      id: safeNumber(item.id)
    }))
    total.value = response.total
    loading.value = false
  })
}

// 重置表单
function reset() {
  form.value = {
    id: null,
    code: null,
    name: null,
    helpCode: null,
    sex: null,
    remark: null,
    attention: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null
  }
  proxy.resetForm("checkgroupRef")
  activeTab.value = "first"
  selectedCheckItems.value = []
}

// 取消
function cancel() {
  open.value = false
  reset()
}

// 搜索 & 重置
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}
function resetQuery() {
  proxy.resetForm("queryRef")
  handleQuery()
}

// 表格多选
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id)
  single.value = selection.length !== 1
  multiple.value = selection.length === 0
}

// 检查项选择变化
function handleCheckItemSelection(selection) {
  selectedCheckItems.value = selection.map(item => safeNumber(item.id))
}

// 新增
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加检查组"
  getAllCheckItems()
}

// 修改（修复回显）
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value[0]
  getCheckgroup(_id).then(response => {
    const data = response.data
    form.value = {
      ...data,
      id: safeNumber(data.id)
    }
    open.value = true
    title.value = "修改检查组"

    getAllCheckItems().then(() => {
      // 处理 checkItemIds，统一转为 number
      let checkItemIds = data.checkItemIds || []
      if (!Array.isArray(checkItemIds)) checkItemIds = []
      const targetIds = checkItemIds.map(id => safeNumber(id)).filter(id => id != null)

      selectedCheckItems.value = [...targetIds]

      nextTick(() => {
        const table = checkItemTableRef.value
        if (!table) return

        table.clearSelection()

        // 批量选中
        const idSet = new Set(targetIds)
        checkItemList.value.forEach(item => {
          if (idSet.has(item.id)) {
            table.toggleRowSelection(item, true)
          }
        })
      })
    })
  })
}

// 提交
function submitForm() {
  proxy.$refs["checkgroupRef"].validate(valid => {
    if (valid) {
      const formData = { ...form.value }
      formData.checkItemIds = selectedCheckItems.value

      const api = formData.id != null ? updateCheckgroup : addCheckgroup
      api(formData).then(() => {
        proxy.$modal.msgSuccess(formData.id ? "修改成功" : "新增成功")
        open.value = false
        getList()
      })
    }
  })
}

// 删除
function handleDelete(row) {
  const _ids = row.id || ids.value
  proxy.$modal.confirm('是否确认删除检查组编号为"' + _ids + '"的数据项？').then(function () {
    return delCheckgroup(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

// 初始化
getList()
</script>

<style scoped>
.check-scroll {
  max-height: 300px;
  overflow-y: auto;
}
</style>