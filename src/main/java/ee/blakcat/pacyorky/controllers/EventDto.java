package ee.blakcat.pacyorky.controllers;

import ee.blakcat.pacyorky.models.PacyorkyEvent;

import java.text.SimpleDateFormat;
import java.util.Objects;

public class EventDto {
    private String id;
    private String location, description, title;
    private String date, endTime;
    private String eventOwner;
    private String link;
    private String district;
    private double[] locationPoint;

    public EventDto(PacyorkyEvent pacyorkyEvent) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.setId(pacyorkyEvent.getId());
        this.setDistrict(pacyorkyEvent.getDistrict().name);
        this.setDate(formatter.format(pacyorkyEvent.getStartTime()));
        this.setDescription(pacyorkyEvent.getDescription());
        this.setEndTime(Objects.isNull(pacyorkyEvent.getEndTime()) ? "" : formatter.format(pacyorkyEvent.getEndTime()));
        this.setLocation(pacyorkyEvent.getPlace());
        this.setTitle(pacyorkyEvent.getName());
        this.setLink("https://www.facebook.com/events/" + pacyorkyEvent.getId());
        this.setEventOwner(pacyorkyEvent.getPacyorkyEventOwner().getName());
        this.locationPoint=new LocationDTO (pacyorkyEvent).getLocationPoint();
    }


    public double[] getLocationPoint() {
        return locationPoint;
    }

    public void setLocationPoint(double[] locationPoint) {
        this.locationPoint = locationPoint;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEventOwner() {
        return eventOwner;
    }

    public void setEventOwner(String eventOwner) {
        this.eventOwner = eventOwner;
    }
}
