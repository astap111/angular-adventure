package org.itechart.entity.company;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SENDER")
public class SenderCompany extends Company {

}
