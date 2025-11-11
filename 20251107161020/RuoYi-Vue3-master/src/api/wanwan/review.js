import request from '@/utils/request'

// 查询陪玩专区-评价列表
export function listReview(query) {
  return request({
    url: '/wanwan/review/list',
    method: 'get',
    params: query
  })
}

// 查询陪玩专区-评价详细
export function getReview(reviewId) {
  return request({
    url: '/wanwan/review/' + reviewId,
    method: 'get'
  })
}

// 新增陪玩专区-评价
export function addReview(data) {
  return request({
    url: '/wanwan/review',
    method: 'post',
    data: data
  })
}

// 修改陪玩专区-评价
export function updateReview(data) {
  return request({
    url: '/wanwan/review',
    method: 'put',
    data: data
  })
}

// 删除陪玩专区-评价
export function delReview(reviewId) {
  return request({
    url: '/wanwan/review/' + reviewId,
    method: 'delete'
  })
}
