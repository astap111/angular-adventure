package org.itechart.entity.jpa.company;

import org.itechart.entity.jpa.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("CLIENT")
public class WarehouseCompany extends Company {
    @ManyToOne
    @JoinColumn(name = "ADMIN_ID")
    private User admin;

    @Enumerated(EnumType.STRING)
    private CompanyStatus status;

    private Date creationDate;

    private Date lastStatusUpdateDate;

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public CompanyStatus getStatus() {
        return status;
    }

    public void setStatus(CompanyStatus status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastStatusUpdateDate() {
        return lastStatusUpdateDate;
    }

    public void setLastStatusUpdateDate(Date lastStatusUpdateDate) {
        this.lastStatusUpdateDate = lastStatusUpdateDate;
    }
}
