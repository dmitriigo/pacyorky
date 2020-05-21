package ee.blakcat.pacyorky.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class PacyorkyUser {

    @Id
    private long id;
    private String eMail;
    private MailLang mailLang;
    private MailSendPeriod mailSendPeriod;
    @ManyToMany
    private Set<PacyorkyEvent> pacyorkyEventsToSend;
    private boolean confirmed;
    @Column (unique = true)
    private String controlString;

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
}
