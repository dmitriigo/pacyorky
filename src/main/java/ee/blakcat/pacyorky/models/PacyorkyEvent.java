package ee.blakcat.pacyorky.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class PacyorkyEvent {
    @Id
    private String id;
    private String place, name;
    @Column(length = 2048)
    private String description;
    private LocalDateTime startTime, endTime;
    @ManyToOne(targetEntity = PacyorkyGroup.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PacyorkyGroup pacyorkyEventOwner;
    private District district;
    private double lat, lng;
    @Column(length = 2048)
    private String cover;

    public PacyorkyEvent(String place, String description, String name, String facebookId, LocalDateTime startTime, LocalDateTime endTime, PacyorkyGroup pacyorkyEventOwner, String cover) {
        this.id = facebookId;
        this.place = place;
        this.description = description;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.pacyorkyEventOwner = pacyorkyEventOwner;
        //TODO обновление дистрикта при изменении
        this.district = District.getDistrict(place);
        this.cover = cover;
    }

    public PacyorkyEvent(String name, LocalDateTime startTime, String facebookId) {
        this.id = facebookId;
        this.name = name;
        this.startTime = startTime;

    }

    public PacyorkyEvent() {
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String pictureURL) {
        this.cover = pictureURL;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }


    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public PacyorkyGroup getPacyorkyEventOwner() {
        return pacyorkyEventOwner;
    }

    public void setPacyorkyEventOwner(PacyorkyGroup pacyorkyEventOwner) {
        this.pacyorkyEventOwner = pacyorkyEventOwner;
    }

    @Override
    public String toString() {
        return "PacyorkyEvent{" +
                "id='" + id + '\'' +
                ", place='" + place + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", pacyorkyEventOwner=" + pacyorkyEventOwner +
                '}';
    }
}
