<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="业务订单编号" prop="orderSn">
        <el-input
          v-model="queryParams.orderSn"
          placeholder="请输入业务订单编号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单总金额" prop="totalAmount">
        <el-input
          v-model="queryParams.totalAmount"
          placeholder="请输入订单总金额"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预约开始时间" prop="scheduledStartTime">
        <el-date-picker clearable
          v-model="queryParams.scheduledStartTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择预约开始时间">
        </el-date-picker>
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
          v-hasPermi="['wanwan:order:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['wanwan:order:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['wanwan:order:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['wanwan:order:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="订单ID" align="center" prop="orderId" />
      <el-table-column label="业务订单编号" align="center" prop="orderSn" />
      <el-table-column label="下单客户ID (老板)" align="center" prop="customerId" />
      <el-table-column label="接单陪玩ID" align="center" prop="talentId" />
      <el-table-column label="计价方式" align="center" prop="pricingType">
        <template #default="scope">
          <span>{{ scope.row.pricingType === '1' ? '按小时' : scope.row.pricingType === '2' ? '按局数' : scope.row.pricingType }}</span>
        </template>
      </el-table-column>
      <el-table-column label="下单时单价" align="center" prop="unitPrice" />
      <el-table-column label="购买数量 (小时数或局数)" align="center" prop="quantity" />
      <el-table-column label="订单总金额" align="center" prop="totalAmount" />
      <el-table-column label="订单状态" align="center" prop="orderStatus">
        <template #default="scope">
          <span>{{ getOrderStatusText(scope.row.orderStatus) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预约开始时间" align="center" prop="scheduledStartTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.scheduledStartTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="实际开始服务时间" align="center" prop="actualStartTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.actualStartTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="服务结束时间" align="center" prop="actualEndTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.actualEndTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="订单备注 (老板填写的特殊要求)" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['wanwan:order:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['wanwan:order:remove']">删除</el-button>
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

    <!-- 添加或修改陪玩专区-订单对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="orderRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="业务订单编号" prop="orderSn">
          <el-input v-model="form.orderSn" placeholder="请输入业务订单编号" />
        </el-form-item>
        <el-form-item label="下单客户ID (老板)" prop="customerId">
          <el-input v-model="form.customerId" placeholder="请输入下单客户ID (老板)" />
        </el-form-item>
        <el-form-item label="接单陪玩ID" prop="talentId">
          <el-input v-model="form.talentId" placeholder="请输入接单陪玩ID" />
        </el-form-item>
        <el-form-item label="下单时单价" prop="unitPrice">
          <el-input v-model="form.unitPrice" placeholder="请输入下单时单价" />
        </el-form-item>
        <el-form-item label="购买数量 (小时数或局数)" prop="quantity">
          <el-input v-model="form.quantity" placeholder="请输入购买数量 (小时数或局数)" />
        </el-form-item>
        <el-form-item label="订单总金额" prop="totalAmount">
          <el-input v-model="form.totalAmount" placeholder="请输入订单总金额" />
        </el-form-item>
        <el-form-item label="预约开始时间" prop="scheduledStartTime">
          <el-date-picker clearable
            v-model="form.scheduledStartTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择预约开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="实际开始服务时间" prop="actualStartTime">
          <el-date-picker clearable
            v-model="form.actualStartTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择实际开始服务时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="服务结束时间" prop="actualEndTime">
          <el-date-picker clearable
            v-model="form.actualEndTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择服务结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="删除标志 (0=存在, 1=删除)" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标志 (0=存在, 1=删除)" />
        </el-form-item>
        <el-form-item label="订单备注 (老板填写的特殊要求)" prop="remark">
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

<script setup name="Order">
import { listOrder, getOrder, delOrder, addOrder, updateOrder } from "@/api/wanwan/order"

const { proxy } = getCurrentInstance()

const orderList = ref([])
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
    orderSn: null,
    totalAmount: null,
    scheduledStartTime: null,
  },
  rules: {
    orderSn: [
      { required: true, message: "业务订单编号不能为空", trigger: "blur" }
    ],
    customerId: [
      { required: true, message: "下单客户ID (老板)不能为空", trigger: "blur" }
    ],
    talentId: [
      { required: true, message: "接单陪玩ID不能为空", trigger: "blur" }
    ],
    pricingType: [
      { required: true, message: "计价方式不能为空", trigger: "change" }
    ],
    unitPrice: [
      { required: true, message: "下单时单价不能为空", trigger: "blur" }
    ],
    quantity: [
      { required: true, message: "购买数量 (小时数或局数)不能为空", trigger: "blur" }
    ],
    totalAmount: [
      { required: true, message: "订单总金额不能为空", trigger: "blur" }
    ],
    orderStatus: [
      { required: true, message: "订单状态不能为空", trigger: "change" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询陪玩专区-订单列表 */
function getList() {
  loading.value = true
  listOrder(queryParams.value).then(response => {
    orderList.value = response.rows
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
    orderId: null,
    orderSn: null,
    customerId: null,
    talentId: null,
    pricingType: null,
    unitPrice: null,
    quantity: null,
    totalAmount: null,
    orderStatus: null,
    scheduledStartTime: null,
    actualStartTime: null,
    actualEndTime: null,
    delFlag: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null
  }
  proxy.resetForm("orderRef")
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

/** 获取订单状态文字 */
function getOrderStatusText(status) {
  const statusMap = {
    '10': '待支付',
    '20': '待服务',
    '30': '服务中',
    '40': '待确认',
    '50': '已完成',
    '60': '已取消'
  }
  return statusMap[status] || status
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.orderId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加陪玩专区-订单"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _orderId = row.orderId || ids.value
  getOrder(_orderId).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改陪玩专区-订单"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["orderRef"].validate(valid => {
    if (valid) {
      if (form.value.orderId != null) {
        updateOrder(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addOrder(form.value).then(response => {
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
  const _orderIds = row.orderId || ids.value
  proxy.$modal.confirm('是否确认删除陪玩专区-订单编号为"' + _orderIds + '"的数据项？').then(function() {
    return delOrder(_orderIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('wanwan/order/export', {
    ...queryParams.value
  }, `order_${new Date().getTime()}.xlsx`)
}

getList()
</script>
