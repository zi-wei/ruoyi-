import request from '@/utils/request'

// 查询陪玩专区-订单列表
export function listOrder(query) {
  return request({
    url: '/wanwan/order/list',
    method: 'get',
    params: query
  })
}

// 查询陪玩专区-订单详细
export function getOrder(orderId) {
  return request({
    url: '/wanwan/order/' + orderId,
    method: 'get'
  })
}

// 新增陪玩专区-订单
export function addOrder(data) {
  return request({
    url: '/wanwan/order',
    method: 'post',
    data: data
  })
}

// 修改陪玩专区-订单
export function updateOrder(data) {
  return request({
    url: '/wanwan/order',
    method: 'put',
    data: data
  })
}

// 删除陪玩专区-订单
export function delOrder(orderId) {
  return request({
    url: '/wanwan/order/' + orderId,
    method: 'delete'
  })
}
