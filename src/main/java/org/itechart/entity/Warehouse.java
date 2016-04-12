package org.itechart.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.itechart.entity.company.Company;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "warehouse_id_seq")
    @SequenceGenerator(name = "warehouse_id_seq", sequenceName = "warehouse_id_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    @Enumerated(EnumType.STRING)
    private StorageType storageType;

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "WAREHOUSE_ID")
    private List<StorageCell> storage = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public StorageType getStorageType() {
        return storageType;
    }

    public void setStorageType(StorageType storageType) {
        this.storageType = storageType;
    }

    public List<StorageCell> getStorage() {
        return storage;
    }

    public void setStorage(List<StorageCell> storage) {
        this.storage = storage;
    }
}
