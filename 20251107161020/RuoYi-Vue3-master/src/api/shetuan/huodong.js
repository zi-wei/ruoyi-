import request from '@/utils/request'

// 查询活动信息列表
export function listHuodong(query) {
  return request({
    url: '/shetuan/huodong/list',
    method: 'get',
    params: query
  })
}

// 查询活动信息详细
export function getHuodong(activityId) {
  return request({
    url: '/shetuan/huodong/' + activityId,
    method: 'get'
  })
}

// 新增活动信息
export function addHuodong(data) {
  return request({
    url: '/shetuan/huodong',
    method: 'post',
    data: data
  })
}

// 修改活动信息
export function updateHuodong(data) {
  return request({
    url: '/shetuan/huodong',
    method: 'put',
    data: data
  })
}

// 删除活动信息
export function delHuodong(activityId) {
  return request({
    url: '/shetuan/huodong/' + activityId,
    method: 'delete'
  })
}
