import request from '@/utils/request'

// 查询检查组列表
export function listCheckgroup(query) {
  return request({
    url: '/reservation/checkgroup/list',
    method: 'get',
    params: query
  })
}

// 查询检查组详细
export function getCheckgroup(id) {
  return request({
    url: '/reservation/checkgroup/' + id,
    method: 'get'
  })
}

// 新增检查组
export function addCheckgroup(data) {
  return request({
    url: '/reservation/checkgroup',
    method: 'post',
    data: data
  })
}

// 修改检查组
export function updateCheckgroup(data) {
  return request({
    url: '/reservation/checkgroup',
    method: 'put',
    data: data
  })
}

// 删除检查组
export function delCheckgroup(id) {
  return request({
    url: '/reservation/checkgroup/' + id,
    method: 'delete'
  })
}

// 获取所有检查组
export function getAllCheckgroups(){
  return request({
    url: '/reservation/checkgroup/all',
    method: 'get'
  })
}