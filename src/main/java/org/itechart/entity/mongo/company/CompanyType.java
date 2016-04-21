package org.itechart.entity.mongo.company;

public enum CompanyType {
    CARRIER_COMPANY, WAREHOUSE_COMPANY, SENDER_COMPANY, RECEIVER_COMPANY;

    public String getClassByType() {
        switch (this) {
            case CARRIER_COMPANY:
                return CarrierCompany.class.getName();
            case WAREHOUSE_COMPANY:
                return WarehouseCompany.class.getName();
            case SENDER_COMPANY:
                return SenderCompany.class.getName();
            case RECEIVER_COMPANY:
                return ReceiverCompany.class.getName();
        }
        return null;
    }
}
