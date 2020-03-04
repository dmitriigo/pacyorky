package ee.blakcat.pacyorky.controllers;

public class EventDto {
    private String location, description, title;
    private String date, endTime;
    private String pacyorkyEventOwnerName;
    private String link;


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

    public String getPacyorkyEventOwnerName() {
        return pacyorkyEventOwnerName;
    }

    public void setPacyorkyEventOwnerName(String pacyorkyEventOwnerName) {
        this.pacyorkyEventOwnerName = pacyorkyEventOwnerName;
    }
}
