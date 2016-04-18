package org.itechart.entity.jpa.company;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.itechart.entity.jpa.Transport;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("CARRIER")
public class CarrierCompany extends Company {
    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "carrier_id")
    private List<Transport> transports;

    public List<Transport> getTransports() {
        return transports;
    }

    public void setTransports(List<Transport> transports) {
        this.transports = transports;
    }
}
