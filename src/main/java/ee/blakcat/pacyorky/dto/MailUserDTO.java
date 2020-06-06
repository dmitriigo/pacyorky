package ee.blakcat.pacyorky.dto;

import ee.blakcat.pacyorky.models.MailLang;
import ee.blakcat.pacyorky.models.MailSendPeriod;

public class MailUserDTO {
    private MailLang mailLang;
    private MailSendPeriod mailSendPeriod;
    private String eMail;

    public MailUserDTO() {
    }

    public MailUserDTO(MailLang mailLang, MailSendPeriod mailSendPeriod, String eMail) {
        this.mailLang = mailLang;
        this.mailSendPeriod = mailSendPeriod;
        this.eMail = eMail;
    }

    @Override
    public String toString() {
        return "MailUserDTO{" +
                "mailLang=" + mailLang +
                ", mailSendPeriod=" + mailSendPeriod +
                ", eMail='" + eMail + '\'' +
                '}';
    }

    public MailLang getMailLang() {
        return mailLang;
    }

    public void setMailLang(MailLang mailLang) {
        this.mailLang = mailLang;
    }

    public MailSendPeriod getMailSendPeriod() {
        return mailSendPeriod;
    }

    public void setMailSendPeriod(MailSendPeriod mailSendPeriod) {
        this.mailSendPeriod = mailSendPeriod;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
}
