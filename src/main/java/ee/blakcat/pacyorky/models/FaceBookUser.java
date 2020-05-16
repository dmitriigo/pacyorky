package ee.blakcat.pacyorky.models;

import com.restfb.types.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FaceBookUser {

    @Id
    private String id;
    private String name;
    private boolean access;

    public FaceBookUser() {
    }

    public FaceBookUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
    }

    @Override
    public String toString() {
        return "FaceBookUser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", access=" + access +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }
}
