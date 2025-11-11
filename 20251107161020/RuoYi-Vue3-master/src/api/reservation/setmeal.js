import request from '@/utils/request'

// 查询套餐列表
export function listSetmeal(query) {
  return request({
    url: '/reservation/setmeal/list',
    method: 'get',
    params: query
  })
}

// 查询套餐详细
export function getSetmeal(id) {
  return request({
    url: '/reservation/setmeal/' + id,
    method: 'get'
  })
}

// 新增套餐
export function addSetmeal(data) {
  return request({
    url: '/reservation/setmeal',
    method: 'post',
    data: data
  })
}

// 修改套餐
export function updateSetmeal(data) {
  return request({
    url: '/reservation/setmeal',
    method: 'put',
    data: data
  })
}

// 删除套餐
export function delSetmeal(id) {
  return request({
    url: '/reservation/setmeal/' + id,
    method: 'delete'
  })
}

// 导出套餐
export function exportSetmeal(query) {
  return request({
    url: '/reservation/setmeal/export',
    method: 'post',
    params: query
  })
}