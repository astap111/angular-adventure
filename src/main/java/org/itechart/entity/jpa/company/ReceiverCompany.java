package org.itechart.entity.jpa.company;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("RECEIVER")
public class ReceiverCompany extends Company {

}
