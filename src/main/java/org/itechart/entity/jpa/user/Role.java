package org.itechart.entity.jpa.user;

import javax.persistence.*;

@Embeddable
@Table(name = "ROLES")
public class Role {
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", nullable = false)
    private UserRole roleName;

    public UserRole getRoleName() {
        return roleName;
    }

    public void setRoleName(UserRole roleName) {
        this.roleName = roleName;
    }
}