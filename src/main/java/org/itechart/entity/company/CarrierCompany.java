package org.itechart.entity.company;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
@DiscriminatorValue("CLIENT")
public class CarrierCompany extends Company {
    private ArrayList<String> transports = new ArrayList<>();

    public ArrayList<String> getTransports() {
        return transports;
    }

    public void setTransports(ArrayList<String> transports) {
        this.transports = transports;
    }
}
