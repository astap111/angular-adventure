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

    public static CompanyType getTypeByClass(Class clazz) {
        if (clazz.equals(CarrierCompany.class)) {
            return CARRIER_COMPANY;
        } else if (clazz.equals(WarehouseCompany.class)) {
            return WAREHOUSE_COMPANY;
        } else if (clazz.equals(SenderCompany.class)) {
            return SENDER_COMPANY;
        } else if (clazz.equals(ReceiverCompany.class)) {
            return RECEIVER_COMPANY;
        }
        return null;
    }
}
