<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名称" prop="name">
        <el-input
            v-model="queryParams.name"
            placeholder="请输入名称"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="编码" prop="code">
        <el-input
            v-model="queryParams.code"
            placeholder="请输入编码"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="助记码" prop="helpCode">
        <el-input
            v-model="queryParams.helpCode"
            placeholder="请输入助记码"
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
            v-hasPermi="['reservation:setmeal:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="Download"
            @click="handleExport"
            v-hasPermi="['reservation:setmeal:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="setmealList" @selection-change="handleSelectionChange">
      <el-table-column type="index" align="center" label="序号" width="150"/>
      <el-table-column label="名称" align="center" prop="name" />
      <el-table-column label="编码" align="center" prop="code" />
      <el-table-column label="助记码" align="center" prop="helpCode" />
      <el-table-column label="性别" align="center" prop="sex">
        <template #default="scope">
          <dict-tag :options="sys_user_sex" :value="scope.row.sex"/>
        </template>
      </el-table-column>
      <el-table-column label="年龄范围" align="center" prop="age" />
      <el-table-column label="价格" align="center" prop="price" />
      <el-table-column label="注意事项" align="center" prop="attention" />
      <el-table-column label="图片路径" align="center" prop="img" width="100">
        <template #default="scope">
          <image-preview :src="scope.row.img" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['reservation:setmeal:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['reservation:setmeal:remove']">删除</el-button>
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

    <!-- 添加或修改套餐对话框 -->
    <el-dialog :title="title" v-model="open" width="600px" append-to-body>
      <el-form ref="setmealRef" :model="form" :rules="rules" label-width="80px">
        <el-tabs v-model="activeTab" type="card">
          <el-tab-pane label="基本信息" name="first">
            <el-row>
              <el-col :span="12">
                <el-form-item label="名称" prop="name">
                  <el-input v-model="form.name" placeholder="请输入名称" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="编码" prop="code">
                  <el-input v-model="form.code" placeholder="请输入编码" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="助记码" prop="helpCode">
                  <el-input v-model="form.helpCode" placeholder="请输入助记码" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="性别" prop="sex">
                  <el-select v-model="form.sex" placeholder="请选择性别">
                    <el-option
                        v-for="dict in sys_user_sex"
                        :key="dict.value"
                        :label="dict.label"
                        :value="dict.value"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="年龄范围" prop="age">
                  <el-input v-model="form.age" placeholder="请输入年龄范围" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="价格" prop="price">
                  <el-input v-model="form.price" placeholder="请输入价格" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <el-form-item label="注意事项" prop="attention">
                  <el-input v-model="form.attention" placeholder="请输入注意事项" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <el-form-item label="说明" prop="remark">
                  <el-input v-model="form.remark" type="textarea" placeholder="请输入说明" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <el-form-item label="图片路径" prop="img">
                  <image-upload v-model="form.img"/>
                </el-form-item>
              </el-col>
            </el-row>
          </el-tab-pane>

          <el-tab-pane label="检查组信息" name="second">
            <div class="check-scroll">
              <el-table
                  :data="checkgroupList"
                  height="400"
                  ref="checkgroupTableRef"
                  row-key="id"
                  @selection-change="handleCheckgroupSelection"
              >
                <el-table-column type="selection" width="55"></el-table-column>
                <el-table-column label="编码" prop="code"/>
                <el-table-column label="名称" prop="name"/>
                <el-table-column label="助记码" prop="helpCode"/>
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

<script setup name="Setmeal">
import { listSetmeal, getSetmeal, delSetmeal, addSetmeal, updateSetmeal, exportSetmeal } from "@/api/reservation/setmeal"
import { getAllCheckgroups } from "@/api/reservation/checkgroup"

const { proxy } = getCurrentInstance()
const { sys_user_sex } = proxy.useDict('sys_user_sex')

const setmealList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")
const activeTab = ref("first")
const checkgroupList = ref([])
const selectedCheckgroups = ref([])
const checkgroupTableRef = ref()
const queryRef = ref()
const setmealRef = ref()

// reactive data
const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    searchValue: null,
    name: null,
    code: null,
    helpCode: null,
  },
  rules: {
    name: [{ required: true, message: "名称不能为空", trigger: "blur" }],
    code: [{ required: true, message: "编码不能为空", trigger: "blur" }]
  }
})

const { queryParams, form, rules } = toRefs(data)

// 工具函数：安全转 number
function safeNumber(val) {
  if (val == null) return null
  const num = Number(val)
  return isNaN(num) ? val : num
}

// 获取所有检查组（确保 id 为 number）
function getAllCheckGroups() {
  return getAllCheckgroups({}).then(response => {
    checkgroupList.value = response.data.map(item => ({
      ...item,
      id: safeNumber(item.id)
    }));
  })
}

/** 查询套餐列表 */
function getList() {
  loading.value = true
  listSetmeal(queryParams.value).then(response => {
    setmealList.value = response.rows.map(item => ({
      ...item,
      id: safeNumber(item.id)
    }))
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
    id: null,
    name: null,
    code: null,
    helpCode: null,
    sex: null,
    age: null,
    price: null,
    remark: null,
    attention: null,
    img: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null
  }
  proxy.resetForm("setmealRef")
  activeTab.value = "first"
  selectedCheckgroups.value = []
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

// 表格多选
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id)
  single.value = selection.length !== 1
  multiple.value = selection.length === 0
}

// 检查组选择变化
function handleCheckgroupSelection(selection) {
  selectedCheckgroups.value = selection.map(item => safeNumber(item.id))
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加套餐"
  getAllCheckGroups()
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value[0]
  getSetmeal(_id).then(response => {
    const data = response.data
    form.value = {
      ...data,
      id: safeNumber(data.id)
    }
    open.value = true
    title.value = "修改套餐"

    getAllCheckGroups().then(() => {
      // 处理 checkgroupIds，统一转为 number
      let checkgroupIds = data.checkgroupIds || []
      if (!Array.isArray(checkgroupIds)) checkgroupIds = []
      const targetIds = checkgroupIds.map(id => safeNumber(id)).filter(id => id != null)

      selectedCheckgroups.value = [...targetIds]

      nextTick(() => {
        const table = checkgroupTableRef.value
        if (!table) return

        table.clearSelection()

        // 批量选中
        const idSet = new Set(targetIds)
        checkgroupList.value.forEach(item => {
          if (idSet.has(item.id)) {
            table.toggleRowSelection(item, true)
          }
        })
      })
    })
  })
}

/** 提交按钮 */
function submitForm() {
  setmealRef.value.validate(valid => {
    if (valid) {
      const formData = { ...form.value }
      formData.checkgroupIds = selectedCheckgroups.value

      const api = formData.id != null ? updateSetmeal : addSetmeal
      api(formData).then(() => {
        proxy.$modal.msgSuccess(formData.id ? "修改成功" : "新增成功")
        open.value = false
        getList()
      })
    }
  })
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value
  proxy.$modal.confirm('是否确认删除套餐编号为"' + _ids + '"的数据项？').then(function() {
    return delSetmeal(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('reservation/setmeal/export', {
    ...queryParams.value
  }, `setmeal_${new Date().getTime()}.xlsx`)
}

getList()
</script>

<style scoped>
.check-scroll {
  max-height: 300px;
  overflow-y: auto;
}
</style>