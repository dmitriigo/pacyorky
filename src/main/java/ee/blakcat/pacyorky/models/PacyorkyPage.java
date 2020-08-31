package ee.blakcat.pacyorky.models;

import javax.persistence.*;

@Entity
public class PacyorkyPage {
    @Id
    private String id;
    private boolean allowed;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private FacebookUser facebookUser;

    public PacyorkyPage() {
        this.allowed = true;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FacebookUser getFacebookUser() {
        return facebookUser;
    }

    public void setFacebookUser(FacebookUser facebookUser) {
        this.facebookUser = facebookUser;
    }
}
