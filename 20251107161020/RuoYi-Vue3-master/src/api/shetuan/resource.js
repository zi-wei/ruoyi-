import request from '@/utils/request'

// 查询资源信息列表
export function listResource(query) {
  return request({
    url: '/shetuan/resource/list',
    method: 'get',
    params: query
  })
}

// 查询资源信息详细
export function getResource(resourceId) {
  return request({
    url: '/shetuan/resource/' + resourceId,
    method: 'get'
  })
}

// 新增资源信息
export function addResource(data) {
  return request({
    url: '/shetuan/resource',
    method: 'post',
    data: data
  })
}

// 修改资源信息
export function updateResource(data) {
  return request({
    url: '/shetuan/resource',
    method: 'put',
    data: data
  })
}

// 删除资源信息
export function delResource(resourceId) {
  return request({
    url: '/shetuan/resource/' + resourceId,
    method: 'delete'
  })
}
