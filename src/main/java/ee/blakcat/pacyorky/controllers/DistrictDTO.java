package ee.blakcat.pacyorky.controllers;

import ee.blakcat.pacyorky.models.District;

import java.io.Serializable;

public class DistrictDTO {
    String estName, ukrName, rusName;

    public DistrictDTO(District district) {
        this.estName = district.estName;
        this.ukrName = district.ukrName;
        this.rusName = district.rusName;
    }

    public String getEstName() {
        return estName;
    }

    public void setEstName(String estName) {
        this.estName = estName;
    }

    public String getUkrName() {
        return ukrName;
    }

    public void setUkrName(String ukrName) {
        this.ukrName = ukrName;
    }

    public String getRusName() {
        return rusName;
    }

    public void setRusName(String rusName) {
        this.rusName = rusName;
    }
}
