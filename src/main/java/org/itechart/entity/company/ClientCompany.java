package org.itechart.entity.company;

import org.itechart.entity.user.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("CLIENT")
public class ClientCompany extends Company {
    @ManyToOne
    @JoinColumn(name = "ADMIN_ID")
    private User admin;

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }
}
