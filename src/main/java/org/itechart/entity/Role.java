package org.itechart.entity;

import javax.persistence.*;

@Embeddable
@Table(name = "ROLES")
public class Role {
    @Column(name = "role_name", nullable = false)
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}