package ee.blakcat.pacyorky.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PacyorkyGroup {
    @Id
    private String id;
    private boolean allowed;
    private String name;
    @OneToMany
    private Set<FacebookUser> facebookUsers;


    public PacyorkyGroup() {
    }

    public PacyorkyGroup(String name) {
        this.allowed=false;
        this.facebookUsers=new HashSet<>();
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

    public void addUser (FacebookUser facebookUser) {
        facebookUsers.add(facebookUser);
    }

    public Set<FacebookUser> getFacebookUsers() {
        return facebookUsers;
    }

    public void setFacebookUsers(Set<FacebookUser> facebookUsers) {
        this.facebookUsers = facebookUsers;
    }
}
