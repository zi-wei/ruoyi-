import request from '@/utils/request'

// 查询社团信息列表
export function listInfo(query) {
  return request({
    url: '/shetuan/info/list',
    method: 'get',
    params: query
  })
}

// 查询社团信息详细
export function getInfo(clubId) {
  return request({
    url: '/shetuan/info/' + clubId,
    method: 'get'
  })
}

// 新增社团信息
export function addInfo(data) {
  return request({
    url: '/shetuan/info',
    method: 'post',
    data: data
  })
}

// 修改社团信息
export function updateInfo(data) {
  return request({
    url: '/shetuan/info',
    method: 'put',
    data: data
  })
}

// 删除社团信息
export function delInfo(clubId) {
  return request({
    url: '/shetuan/info/' + clubId,
    method: 'delete'
  })
}
