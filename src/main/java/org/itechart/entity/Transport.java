package org.itechart.entity;

import javax.persistence.*;

@Entity
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transport_id_seq")
    @SequenceGenerator(name = "transport_id_seq", sequenceName = "transport_id_seq")
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransportType type;

    private String name;

    @Enumerated(EnumType.STRING)
    private StorageType storageType;

    public TransportType getType() {
        return type;
    }

    public void setType(TransportType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StorageType getStorageType() {
        return storageType;
    }

    public void setStorageType(StorageType storageType) {
        this.storageType = storageType;
    }
}
