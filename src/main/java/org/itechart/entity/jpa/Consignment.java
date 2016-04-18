package org.itechart.entity.jpa;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.itechart.entity.jpa.company.WarehouseCompany;
import org.itechart.entity.jpa.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Consignment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "consignment_id_seq")
    @SequenceGenerator(name = "consignment_id_seq", sequenceName = "consignment_id_seq")
    private Long id;

    private String number;

    @ManyToOne
    @JoinColumn(name = "SENDER_ID")
    private WarehouseCompany sender;

    @ManyToOne
    @JoinColumn(name = "RECEIVER_ID")
    private WarehouseCompany receiver;

    @ManyToOne
    @JoinColumn(name = "CARRIER_ID")
    private WarehouseCompany carrier;

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "CONSIGNMENT_ID")
    private List<Product> products = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "DISPATCHER_ID")
    private User dispatcher;

    @ManyToOne
    @JoinColumn(name = "CONTROLLER_ID")
    private User controller;

    private Date signDate;

    private Date registerDate;

    private Date controlDate;

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

    public WarehouseCompany getSender() {
        return sender;
    }

    public void setSender(WarehouseCompany sender) {
        this.sender = sender;
    }

    public WarehouseCompany getReceiver() {
        return receiver;
    }

    public void setReceiver(WarehouseCompany receiver) {
        this.receiver = receiver;
    }

    public WarehouseCompany getCarrier() {
        return carrier;
    }

    public void setCarrier(WarehouseCompany carrier) {
        this.carrier = carrier;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public User getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(User dispatcher) {
        this.dispatcher = dispatcher;
    }

    public User getController() {
        return controller;
    }

    public void setController(User controller) {
        this.controller = controller;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getControlDate() {
        return controlDate;
    }

    public void setControlDate(Date controlDate) {
        this.controlDate = controlDate;
    }

    public LifecycleStatus getStatus() {
        return status;
    }

    public void setStatus(LifecycleStatus status) {
        this.status = status;
    }
}
