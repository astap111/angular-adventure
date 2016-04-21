package org.itechart.entity.mongo.company;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "company")
public class WarehouseCompany extends Company {
    private Double latitude;

    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
