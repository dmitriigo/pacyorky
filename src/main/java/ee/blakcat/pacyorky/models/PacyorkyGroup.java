package ee.blakcat.pacyorky.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PacyorkyGroup {
    @Id
    private String id;
    private boolean allowed;
    private String name;
    @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private FacebookUser facebookUser;
    private boolean page;
    private boolean hidden;


    public PacyorkyGroup() {
    }

    public boolean isPage() {
        return page;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public void setPage(boolean page) {
        this.page = page;
    }

    public PacyorkyGroup(String name) {
        this.name=name;
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

    public void setId(String id) {
        this.id = id;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

    public FacebookUser getFacebookUser() {
        return facebookUser;
    }

    public void setFacebookUser(FacebookUser facebookUser) {
        this.facebookUser = facebookUser;
    }
}
