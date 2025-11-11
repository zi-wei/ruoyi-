import request from '@/utils/request'

// 查询护航专区-任务列表
export function listMission(query) {
  return request({
    url: '/hanghang/mission/list',
    method: 'get',
    params: query
  })
}

// 查询护航专区-任务详细
export function getMission(missionId) {
  return request({
    url: '/hanghang/mission/' + missionId,
    method: 'get'
  })
}

// 新增护航专区-任务
export function addMission(data) {
  return request({
    url: '/hanghang/mission',
    method: 'post',
    data: data
  })
}

// 修改护航专区-任务
export function updateMission(data) {
  return request({
    url: '/hanghang/mission',
    method: 'put',
    data: data
  })
}

// 删除护航专区-任务
export function delMission(missionId) {
  return request({
    url: '/hanghang/mission/' + missionId,
    method: 'delete'
  })
}
