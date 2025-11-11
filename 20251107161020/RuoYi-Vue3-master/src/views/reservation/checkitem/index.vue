<template>
  <div class="app-container">
    <!--搜索开始-->
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="" prop="keyword">
        <el-input
          v-model="queryParams.keyword"
          placeholder="请输入编码或名称"
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
          v-hasPermi="['reservation:checkitem:add']"
        >新增</el-button>
      </el-col>
      <!--
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['reservation:checkitem:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['reservation:checkitem:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['reservation:checkitem:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    -->
    </el-row>

    <el-table v-loading="loading" :data="checkitemList">
      
      <el-table-column label="序号" type="index" align="center" width="50"/>
      <el-table-column label="编码" align="center" prop="code" />
      <el-table-column label="名称" align="center" prop="name" />
      <!--<el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>-->
      <el-table-column label="性别" align="center" prop="sex">
        <template #default="scope">
          <!-- 优先从字典中查找展示文本 -->
          <el-tag v-if="getLabel(sys_user_sex, scope.row.sex)" type="primary">{{ getLabel(sys_user_sex, scope.row.sex) }}</el-tag>
          <el-tag v-else-if="typeof getLabel(health_sex, scope.row.sex) === 'string'" type="info">{{ getLabel(health_sex, scope.row.sex) }}</el-tag>
          <!-- 回退显示 -->
          <template v-else>
            <el-tag type="success" v-if="scope.row.sex == 0">男</el-tag>
            <el-tag type="danger" v-else-if="scope.row.sex == 1">女</el-tag>
            <el-tag type="info" v-else>不限</el-tag>
          </template>
        </template>
      </el-table-column>
      <el-table-column label="年龄范围" align="center" prop="age" />
      <el-table-column label="价格" align="center" prop="price" />
      <el-table-column label="类型" align="center" prop="type" >
        <template #default="scope">
          <el-tag v-if="getLabel(health_type, scope.row.type)" type="warning">{{ getLabel(health_type, scope.row.type) }}</el-tag>
          <el-tag v-else :type="scope.row.type == 1 ? 'success' : 'danger'">{{ scope.row.type == 1 ? '检查' : '检验' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="注意事项" align="center" prop="attention" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" fixed="right" width="200" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['reservation:checkitem:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['reservation:checkitem:remove']">删除</el-button>
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

    <!-- 添加或修改检查项对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="checkitemRef" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="项目编码" prop="code">
              <el-input v-model="form.code" placeholder="请输入编码"/>
            </el-form-item>
          </el-col> 
          <el-col :span="12">
            <el-form-item label="项目名称" prop="name">
              <el-input v-model="form.name" placeholder="请输入名称"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="性别">
              <el-select v-model="form.sex" placeholder="请选择性别" clearable>
                <el-option
                    v-for="item in health_sex"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value" />
              </el-select>
            </el-form-item>  
          </el-col>
          <el-col :span="12">
            <el-form-item label="适用年龄">
              <el-input v-model="form.age" placeholder="请输入年龄"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="类型">
              <el-radio-group v-model="form.type">
                <!-- 优先使用 health_type 字典渲染 -->
                <template v-if="health_type && health_type.length">
                  <el-radio v-for="dict in health_type"
                            :key="dict.value"
                            :label="dict.value"
                  >{{ dict.label }}</el-radio>
                </template>
                <!-- 回退：若字典为空，使用默认的 检查/检验 -->
                <template v-else>
                  <el-radio :label="1">检查</el-radio>
                  <el-radio :label="2">检验</el-radio>
                </template>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="价格">
              <el-input v-model="form.price" placeholder="请输入价格"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="项目说明">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入备注"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="注意事项">
              <el-input v-model="form.attention" type="textarea" placeholder="请输入注意事项"/>
            </el-form-item>
          </el-col>
        </el-row>
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

<script setup name="Checkitem">
import { listCheckitem, getCheckitem, delCheckitem, addCheckitem, updateCheckitem } from "@/api/reservation/checkitem"

const { proxy } = getCurrentInstance()
// 引入字典：sys_user_sex（系统用户性别），可选的 health_sex 与 health_type
const { sys_user_sex, health_sex, health_type } = proxy.useDict('sys_user_sex','health_sex','health_type')

// 从字典列表中查找 label（用于表格显示）
function getLabel(list, val) {
  if (!list || !list.length) return null
  const found = list.find(item => item.value == val || item.value === val || item.value === Number(val))
  return found ? found.label : null
}

const checkitemList = ref([])
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
    keyword: null,
  },
  rules: {
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询检查项信息列表 */
function getList() {
  loading.value = true
  // 构造查询参数：将 keyword 转为模糊匹配字符串（前后加 %），后端 Mapper 使用 `like #{}` 接收
  const params = { ...queryParams.value }
  if (params.keyword) params.keyword = `%${params.keyword}%`

  listCheckitem(params).then(response => {
    checkitemList.value = response.rows
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
    code: null,
    name: null,
    sex: null,
    age: null,
    price: null,
    type: null,
    attention: null,
    remark: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null
  }
  proxy.resetForm("checkitemRef")
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
  ids.value = selection.map(item => item.id)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加检查项信息"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getCheckitem(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改检查项信息"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["checkitemRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateCheckitem(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addCheckitem(form.value).then(response => {
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
  const _ids = row.id || ids.value
  proxy.$modal.confirm('是否确认删除检查项信息编号为"' + _ids + '"的数据项？').then(function() {
    return delCheckitem(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  const params = { ...queryParams.value }
  if (params.keyword) params.keyword = `%${params.keyword}%`

  proxy.download('reservation/checkitem/export', params, `checkitem_${new Date().getTime()}.xlsx`)
}

getList()
</script>
