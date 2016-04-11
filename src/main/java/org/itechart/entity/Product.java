package org.itechart.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq")
    private Long id;

    private String name;

    private Integer quantity;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private LifecycleStatus status;

    @Enumerated(EnumType.STRING)
    private StorageType storageType;

    private Date lastStatusUpdateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LifecycleStatus getStatus() {
        return status;
    }

    public void setStatus(LifecycleStatus status) {
        this.status = status;
    }

    public StorageType getStorageType() {
        return storageType;
    }

    public void setStorageType(StorageType storageType) {
        this.storageType = storageType;
    }

    public Date getLastStatusUpdateDate() {
        return lastStatusUpdateDate;
    }

    public void setLastStatusUpdateDate(Date lastStatusUpdateDate) {
        this.lastStatusUpdateDate = lastStatusUpdateDate;
    }
}
