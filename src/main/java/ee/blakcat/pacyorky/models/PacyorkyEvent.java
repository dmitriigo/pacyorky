package ee.blakcat.pacyorky.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PacyorkyEvent {
    @Id
    private String id;
    private String place, name;
    @Column(length = 2048)
    private String description;
    private Date startTime, endTime;
    @ManyToOne(targetEntity = PacyorkyEventOwner.class, cascade = CascadeType.ALL)
    private PacyorkyEventOwner pacyorkyEventOwner;
    private District district;
    private double lat, lng;
    private String cover;

    public PacyorkyEvent(String place, String description, String name, String facebookId, Date startTime, Date endTime, PacyorkyEventOwner pacyorkyEventOwner, String cover) {
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

    public PacyorkyEvent(String name, Date startTime, String facebookId) {
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


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public PacyorkyEventOwner getPacyorkyEventOwner() {
        return pacyorkyEventOwner;
    }

    public void setPacyorkyEventOwner(PacyorkyEventOwner pacyorkyEventOwner) {
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
