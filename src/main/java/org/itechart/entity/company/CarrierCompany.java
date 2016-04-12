package org.itechart.entity.company;

import org.itechart.entity.Transport;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue("CARRIER")
public class CarrierCompany extends Company {
    @OneToMany
    @JoinColumn(name = "carrier_id")
    private List<Transport> transports;

    public List<Transport> getTransports() {
        return transports;
    }

    public void setTransports(List<Transport> transports) {
        this.transports = transports;
    }
}
