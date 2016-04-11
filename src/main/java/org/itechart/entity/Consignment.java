package org.itechart.entity;

import org.itechart.entity.company.ClientCompany;
import org.itechart.entity.user.User;

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

    @ManyToOne
    @JoinColumn(name = "SENDER_ID")
    private ClientCompany sender;

    @ManyToOne
    @JoinColumn(name = "RECEIVER_ID")
    private ClientCompany receiver;

    @ManyToOne
    @JoinColumn(name = "CARRIER_ID")
    private ClientCompany carrier;

    private Date sendDate;

    private Date receiveDate;

    @OneToMany
    @JoinColumn(name = "CONSIGNMENT_ID")
    private List<Product> products = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "DISPATCHER_ID")
    private User dispatcher;

    @ManyToOne
    @JoinColumn(name = "CONTROLLER_ID")
    private User controller;

    private Date consignmentDate;

    private Date registrationDate;

    private Date controllerDate;

    @Enumerated(EnumType.STRING)
    private LifecycleStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientCompany getSender() {
        return sender;
    }

    public void setSender(ClientCompany sender) {
        this.sender = sender;
    }

    public ClientCompany getReceiver() {
        return receiver;
    }

    public void setReceiver(ClientCompany receiver) {
        this.receiver = receiver;
    }

    public ClientCompany getCarrier() {
        return carrier;
    }

    public void setCarrier(ClientCompany carrier) {
        this.carrier = carrier;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
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

    public Date getConsignmentDate() {
        return consignmentDate;
    }

    public void setConsignmentDate(Date consignmentDate) {
        this.consignmentDate = consignmentDate;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getControllerDate() {
        return controllerDate;
    }

    public void setControllerDate(Date controllerDate) {
        this.controllerDate = controllerDate;
    }

    public LifecycleStatus getStatus() {
        return status;
    }

    public void setStatus(LifecycleStatus status) {
        this.status = status;
    }
}
