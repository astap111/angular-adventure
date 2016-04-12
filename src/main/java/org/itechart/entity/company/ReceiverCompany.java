package org.itechart.entity.company;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("RECEIVER")
public class ReceiverCompany extends Company {

}
