package com.yc.demo.model;

/**
 * @author: yinchao
 * @date 2019/7/11
 */
public class LinkPermssion {
    private Long id;
    private Long roleId;
    private Long permssionId;

    public LinkPermssion(Long id, Long roleId, Long permssionId) {
        this.id = id;
        this.roleId = roleId;
        this.permssionId = permssionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermssionId() {
        return permssionId;
    }

    public void setPermssionId(Long permssionId) {
        this.permssionId = permssionId;
    }

    @Override
    public String toString() {
        return "LinkPermssion{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", permssionId=" + permssionId +
                '}';
    }
}
