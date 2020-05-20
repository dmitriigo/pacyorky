package ee.blakcat.pacyorky.dto;

import ee.blakcat.pacyorky.models.PacyorkyEvent;

class LocationDTO {

    private double[] locationPoint;

    public LocationDTO(PacyorkyEvent pacyorkyEvent) {
        locationPoint = new double[2];
        locationPoint[1] = pacyorkyEvent.getLat();
        locationPoint[0] = pacyorkyEvent.getLng();
    }

    public double[] getLocationPoint() {
        return locationPoint;
    }

    public void setLocationPoint(double[] locationPoint) {
        this.locationPoint = locationPoint;
    }
}
