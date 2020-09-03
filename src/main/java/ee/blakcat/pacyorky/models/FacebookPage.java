package ee.blakcat.pacyorky.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FacebookPage {
    @Id
    private String id;
    private boolean allowed;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
