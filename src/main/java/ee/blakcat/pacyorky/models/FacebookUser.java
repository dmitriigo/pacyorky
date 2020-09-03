package ee.blakcat.pacyorky.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class FacebookUser {

    @Id
    private String id;
    private String name;
    private boolean access;
    @OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AccessToken accessToken;
    private boolean page;

    public FacebookUser() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacebookUser that = (FacebookUser) o;
        return access == that.access &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, access);
    }

    @Override
    public String toString() {
        return "FacebookUser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", access=" + access +
                '}';
    }

    public boolean isPage() {
        return page;
    }

    public void setPage(boolean page) {
        this.page = page;
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

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }
}
