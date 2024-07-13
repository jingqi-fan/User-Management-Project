import request from '../utils/request.js'

export default {
    // 添加用户接口
    addAttendee(attendee) {
        return request({
            url: '/attendee',
            method: 'post',
            data: attendee
        })
    },
    // 删除用户接口
    deleteAttendee(id) {
        return request({
            url: `/attendee/${id}`,
            method: 'delete'
        })
    },
    // 删除多个用户接口
    deleteMoreAttendee(ids) {
        return request({
            url: '/attendee',
            method: 'delete',
            data: ids
        })
    },
    // 编辑用户接口
    updateAttendee(attendee) {
        return request({
            url: `/attendee`,
            method: 'put',
            data: attendee
        })
    },
    // 查询用户列表接口
    findAttendeeList(index,size, attendeeQuery) {
        return request({
            url: `/attendee/${index}/${size}`,
            method: 'post',
            data: attendeeQuery
        })
    },
    // 根据用户编号获取用户信息接口
    getAttendeeInfo(id) {
        return request({
            url: `/attendee/${id}`,
            method: 'get'
        })
    }
}
