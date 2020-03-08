package ee.blakcat.pacyorky.models;


import javax.persistence.*;
import java.util.Date;

@Entity
public class PacyorkyEvent {
    @Id
    private String id;
    private String place, description, name;
    private Date startTime, endTime;
    @ManyToOne(targetEntity = PacyorkyEventOwner.class, cascade = CascadeType.ALL)
    private PacyorkyEventOwner pacyorkyEventOwner;

    public PacyorkyEvent(String place, String description, String name, String facebookId, Date startTime, Date endTime, PacyorkyEventOwner pacyorkyEventOwner) {
        this.id = facebookId;
        this.place = place;
        this.description = description;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.pacyorkyEventOwner = pacyorkyEventOwner;
    }

    public PacyorkyEvent(String name, Date startTime, String facebookId) {
        this.id = facebookId;
        this.name = name;
        this.startTime = startTime;

    }

    public PacyorkyEvent() {
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
