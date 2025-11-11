import request from '@/utils/request'

// 查询资源预约列表
export function listBooking(query) {
  return request({
    url: '/shetuan/booking/list',
    method: 'get',
    params: query
  })
}

// 查询资源预约详细
export function getBooking(bookingId) {
  return request({
    url: '/shetuan/booking/' + bookingId,
    method: 'get'
  })
}

// 新增资源预约
export function addBooking(data) {
  return request({
    url: '/shetuan/booking',
    method: 'post',
    data: data
  })
}

// 修改资源预约
export function updateBooking(data) {
  return request({
    url: '/shetuan/booking',
    method: 'put',
    data: data
  })
}

// 删除资源预约
export function delBooking(bookingId) {
  return request({
    url: '/shetuan/booking/' + bookingId,
    method: 'delete'
  })
}
