import request from '../utils/request.js'

export default {

    // 添加用户接口
    addTenant(tenant) {
        return request({
            url: '/tenant',
            method: 'post',
            data: tenant
        })
    },

    // 删除用户接口
    deleteTenant(id) {
        return request({
            url: `/tenant/${id}`,
            method: 'delete'
        })
    },

    // 删除多个用户接口
    deleteMoreTenant(ids) {
        return request({
            url: '/tenant',
            method: 'delete',
            data: ids
        })
    },

    // 编辑用户接口
    updateTenant(tenant) {
        return request({
            url: `/tenant`,
            method: 'put',
            data: tenant
        })
    },

    // 查询用户列表接口
    findTenantList(index,size, attendeeQuery) {
        return request({
            url: `/tenant/${index}/${size}`,
            method: 'post',
            data: attendeeQuery
        })
    },

    // 根据用户编号获取用户信息接口
    getTenantInfo(id) {
        return request({
            url: `/tenant/${id}`,
            method: 'get'
        })
    }
}
