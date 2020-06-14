package ee.blakcat.pacyorky.models;

import org.springframework.data.jpa.repository.Modifying;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class PacyorkyUser {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;
    @Column (unique = true)
    private String eMail;
    private MailLang mailLang;
    private MailSendPeriod mailSendPeriod;
    @ManyToMany
    private Set<PacyorkyEvent> pacyorkyEventsToSend;
    private boolean confirmed;
    @Column (unique = true)
    private String controlString;

    public PacyorkyUser() {
    }


    public String getControlString() {
        return controlString;
    }

    public void setControlString(String controlString) {
        this.controlString = controlString;
    }

    public Set<PacyorkyEvent> getPacyorkyEventsToSend() {
        return pacyorkyEventsToSend;
    }

    public void setPacyorkyEventsToSend(Set<PacyorkyEvent> pacyorkyEventsToSend) {
        this.pacyorkyEventsToSend = pacyorkyEventsToSend;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public MailSendPeriod getMailSendPeriod() {
        return mailSendPeriod;
    }

    public void setMailSendPeriod(MailSendPeriod mailSendPeriod) {
        this.mailSendPeriod = mailSendPeriod;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public MailLang getMailLang() {
        return mailLang;
    }

    public void setMailLang(MailLang mailLang) {
        this.mailLang = mailLang;
    }

    @Override
    public String toString() {
        return "PacyorkyUser{" +
                "id=" + id +
                ", eMail='" + eMail + '\'' +
                ", mailLang=" + mailLang +
                ", mailSendPeriod=" + mailSendPeriod +
                ", pacyorkyEventsToSend=" + pacyorkyEventsToSend +
                ", confirmed=" + confirmed +
                ", controlString='" + controlString + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PacyorkyUser that = (PacyorkyUser) o;
        return id == that.id &&
                confirmed == that.confirmed &&
                Objects.equals(eMail, that.eMail) &&
                mailLang == that.mailLang &&
                mailSendPeriod == that.mailSendPeriod &&
                Objects.equals(pacyorkyEventsToSend, that.pacyorkyEventsToSend) &&
                Objects.equals(controlString, that.controlString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eMail, mailLang, mailSendPeriod, pacyorkyEventsToSend, confirmed, controlString);
    }
}
