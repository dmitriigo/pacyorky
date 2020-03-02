package ee.blakcat.pacyorky.models;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class PacyorkyEventOwner {
    @Id
    private String id;
    private String name;
    @OneToMany (cascade = CascadeType.ALL, targetEntity = PacyorkyEvent.class, mappedBy = "pacyorkyEventOwner")
    private List <PacyorkyEvent> pacyorkyEvents;

    public PacyorkyEventOwner(String name, String facebookId) {
        this.id= facebookId;
        this.name = name;
        pacyorkyEvents=new ArrayList<>();
    }

    public PacyorkyEventOwner() {

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


    public List<PacyorkyEvent> getPacyorkyEvents() {
        return pacyorkyEvents;
    }

    public void addEvent (PacyorkyEvent pacyorkyEvent) {
        pacyorkyEvents.add(pacyorkyEvent);
    }

    public void deleteEvent (PacyorkyEvent pacyorkyEvent) {
        pacyorkyEvents.remove(pacyorkyEvent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PacyorkyEventOwner that = (PacyorkyEventOwner) o;
        return id.equals(that.id) &&
                name.equals(that.name) &&
                Objects.equals(pacyorkyEvents, that.pacyorkyEvents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, pacyorkyEvents);
    }

    @Override
    public String toString() {
        return "PacyorkyEventOwner{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
