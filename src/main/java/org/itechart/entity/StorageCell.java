package org.itechart.entity;

import org.itechart.entity.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public class StorageCell {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "storage_cell_id_seq")
    @SequenceGenerator(name = "storage_cell_id_seq", sequenceName = "storage_cell_id_seq")
    private Long id;

    private Integer index;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "ACCEPTOR_ID")
    private User acceptor;

    private Date acceptanceDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getAcceptor() {
        return acceptor;
    }

    public void setAcceptor(User acceptor) {
        this.acceptor = acceptor;
    }

    public Date getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAcceptanceDate(Date acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }
}
