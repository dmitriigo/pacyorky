package ee.blakcat.pacyorky.dto;

import ee.blakcat.pacyorky.models.MailLang;
import ee.blakcat.pacyorky.models.MailSendPeriod;

public class VariantDTO {
    private final MailSendPeriod[] mailSendPeriods;
    private final MailLang[] mailLangs;

    public VariantDTO() {
        this.mailSendPeriods = MailSendPeriod.values();
        this.mailLangs = MailLang.values();
    }

    public MailSendPeriod[] getMailSendPeriods() {
        return mailSendPeriods;
    }

    public MailLang[] getMailLangs() {
        return mailLangs;
    }
}
