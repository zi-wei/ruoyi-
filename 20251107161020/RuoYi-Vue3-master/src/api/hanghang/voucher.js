import request from '@/utils/request'

// 查询护航专区-任务凭证列表
export function listVoucher(query) {
  return request({
    url: '/hanghang/voucher/list',
    method: 'get',
    params: query
  })
}

// 查询护航专区-任务凭证详细
export function getVoucher(voucherId) {
  return request({
    url: '/hanghang/voucher/' + voucherId,
    method: 'get'
  })
}

// 新增护航专区-任务凭证
export function addVoucher(data) {
  return request({
    url: '/hanghang/voucher',
    method: 'post',
    data: data
  })
}

// 修改护航专区-任务凭证
export function updateVoucher(data) {
  return request({
    url: '/hanghang/voucher',
    method: 'put',
    data: data
  })
}

// 删除护航专区-任务凭证
export function delVoucher(voucherId) {
  return request({
    url: '/hanghang/voucher/' + voucherId,
    method: 'delete'
  })
}
