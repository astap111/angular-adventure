package org.itechart.entity.mongo.company;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

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
@Document
public class Company {
    @Id
    private Long id;

    private String name;

    private Date date;

    private String _class;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String get_class() {
        return _class;
    }
}
