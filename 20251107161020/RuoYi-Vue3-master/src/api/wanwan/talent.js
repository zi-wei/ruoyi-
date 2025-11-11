import request from '@/utils/request'

// 查询陪玩专区-人才库列表
export function listTalent(query) {
  return request({
    url: '/wanwan/talent/list',
    method: 'get',
    params: query
  })
}

// 查询陪玩专区-人才库详细
export function getTalent(talentId) {
  return request({
    url: '/wanwan/talent/' + talentId,
    method: 'get'
  })
}

// 新增陪玩专区-人才库
export function addTalent(data) {
  return request({
    url: '/wanwan/talent',
    method: 'post',
    data: data
  })
}

// 修改陪玩专区-人才库
export function updateTalent(data) {
  return request({
    url: '/wanwan/talent',
    method: 'put',
    data: data
  })
}

// 删除陪玩专区-人才库
export function delTalent(talentId) {
  return request({
    url: '/wanwan/talent/' + talentId,
    method: 'delete'
  })
}
