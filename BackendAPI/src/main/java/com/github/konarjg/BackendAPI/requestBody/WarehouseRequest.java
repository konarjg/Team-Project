package com.github.konarjg.BackendAPI.requestBody;

import java.util.List;

public class WarehouseRequest {
    private String name;
    private long locationId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }
}
