import request from '@/utils/request'

// 查询护航专区-打手列表
export function listSpecialist(query) {
  return request({
    url: '/hanghang/specialist/list',
    method: 'get',
    params: query
  })
}

// 查询护航专区-打手详细
export function getSpecialist(specialistId) {
  return request({
    url: '/hanghang/specialist/' + specialistId,
    method: 'get'
  })
}

// 新增护航专区-打手
export function addSpecialist(data) {
  return request({
    url: '/hanghang/specialist',
    method: 'post',
    data: data
  })
}

// 修改护航专区-打手
export function updateSpecialist(data) {
  return request({
    url: '/hanghang/specialist',
    method: 'put',
    data: data
  })
}

// 删除护航专区-打手
export function delSpecialist(specialistId) {
  return request({
    url: '/hanghang/specialist/' + specialistId,
    method: 'delete'
  })
}
