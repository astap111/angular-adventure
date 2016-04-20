package org.itechart.entity.mongo;

import org.itechart.entity.jpa.LifecycleStatus;
import org.itechart.entity.mongo.company.SenderCompany;
import org.itechart.entity.mongo.company.WarehouseCompany;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Document
public class Consignment {
    @Id
    private Long id;

    private String number;

    private Date registerDate;

    @DBRef
    private SenderCompany senderCompany;

    @DBRef
    private WarehouseCompany warehouseCompany;

    @Enumerated(EnumType.STRING)
    private LifecycleStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public SenderCompany getSenderCompany() {
        return senderCompany;
    }

    public void setSenderCompany(SenderCompany senderCompany) {
        this.senderCompany = senderCompany;
    }

    public WarehouseCompany getWarehouseCompany() {
        return warehouseCompany;
    }

    public void setWarehouseCompany(WarehouseCompany warehouseCompany) {
        this.warehouseCompany = warehouseCompany;
    }

    public LifecycleStatus getStatus() {
        return status;
    }

    public void setStatus(LifecycleStatus status) {
        this.status = status;
    }
}
