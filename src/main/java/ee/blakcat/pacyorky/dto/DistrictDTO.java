package ee.blakcat.pacyorky.dto;

import ee.blakcat.pacyorky.models.District;

public class DistrictDTO {
    private String estName;
    private String ukrName;
    private String rusName;
    private int id;

    public DistrictDTO(District district) {
        this.estName = district.estName;
        this.ukrName = district.ukrName;
        this.rusName = district.rusName;
        this.id = district.ordinal();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
