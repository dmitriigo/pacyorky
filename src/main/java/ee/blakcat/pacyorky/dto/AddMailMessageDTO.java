package ee.blakcat.pacyorky.dto;

import ee.blakcat.pacyorky.models.MailLang;

public class AddMailMessageDTO {
    private boolean result;
    private String message;
    private MailLang mailLang;
    private String mail;

    public AddMailMessageDTO() {
    }

    public AddMailMessageDTO(boolean result, MailLang mailLang, String mail) {
        this.result = result;
        this.mailLang = mailLang;
        if (mailLang.equals(MailLang.EN) && !result) this.message = "Oops! Something wrong";
        else if (mailLang.equals(MailLang.EN) && result) this.message = "Email sent to " + mail;
        else if (mailLang.equals(MailLang.RU) && !result) this.message = "Упс! что-то пошло не так";
        else if (mailLang.equals(MailLang.RU) && result) this.message = "Письмо отправлено на " + mail;
        else if (mailLang.equals(MailLang.EE) && !result) this.message = "Vabandust! Midagi läks valesti";
        else if (mailLang.equals(MailLang.EE) && result) this.message = "E-post on saadetud aadressile " + mail;
        else if (mailLang.equals(MailLang.UA) && !result) this.message = "Упс! щось пішло не так";
        else if (mailLang.equals(MailLang.UA) && result) this.message = "Лист відправлено на " + mail;
        else this.message = "default error message";
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AddMailMessageDTO{" +
                "result=" + result +
                ", message='" + message + '\'' +
                '}';
    }
}
