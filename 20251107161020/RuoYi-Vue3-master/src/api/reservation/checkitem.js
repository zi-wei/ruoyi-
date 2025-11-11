import request from '@/utils/request'

// 查询检查项信息列表
export function listCheckitem(query) {
  return request({
    url: '/reservation/checkitem/list',
    method: 'get',
    params: query
  })
}

// 查询检查项信息详细
export function getCheckitem(id) {
  return request({
    url: '/reservation/checkitem/' + id,
    method: 'get'
  })
}

// 新增检查项信息
export function addCheckitem(data) {
  return request({
    url: '/reservation/checkitem',
    method: 'post',
    data: data
  })
}

// 修改检查项信息
export function updateCheckitem(data) {
  return request({
    url: '/reservation/checkitem',
    method: 'put',
    data: data
  })
}

// 删除检查项信息
export function delCheckitem(id) {
  return request({
    url: '/reservation/checkitem/' + id,
    method: 'delete'
  })
  
}
//查询所有护理项目
export function getAllCheckitems() {
  return request({
    url: '/reservation/checkitem/all',
    method: 'get'
  })
}

