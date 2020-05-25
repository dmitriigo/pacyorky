package ee.blakcat.pacyorky.models;

public enum MailLang {
   EN ("English"),
    RU ("Russian"),
    EE ("Eesti"),
    UA ("Ukrainian");

   public String name;

    MailLang(String name) {
        this.name = name;
    }
}
