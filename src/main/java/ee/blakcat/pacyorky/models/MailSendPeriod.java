package ee.blakcat.pacyorky.models;

public enum MailSendPeriod {
    HOURLY ("Hourly"),
    WEEKLY ("Weekly");

   public String name;

    MailSendPeriod(String name) {
        this.name = name;
    }
}
