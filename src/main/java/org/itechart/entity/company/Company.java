package org.itechart.entity.company;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.itechart.entity.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "COMPANY_TYPE")
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CarrierCompany.class, name = "CARRIER_COMPANY"),
        @JsonSubTypes.Type(value = WarehouseCompany.class, name = "WAREHOUSE_COMPANY"),
        @JsonSubTypes.Type(value = SenderCompany.class, name = "SENDER_COMPANY"),
        @JsonSubTypes.Type(value = ReceiverCompany.class, name = "RECEIVER_COMPANY")
})
public abstract class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_id_seq")
    @SequenceGenerator(name = "company_id_seq", sequenceName = "company_id_seq")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<User> users = new ArrayList<>();

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
