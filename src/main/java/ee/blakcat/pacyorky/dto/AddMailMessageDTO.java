package ee.blakcat.pacyorky.dto;

import ee.blakcat.pacyorky.models.MailLang;

public class AddMailMessageDTO {
    private boolean result;
    private String message;
    private String mail;

    public AddMailMessageDTO() {
    }

    public AddMailMessageDTO(boolean result, String mail) {
        this.result = result;
        if (result) {
            this.message = "success";
        } else {
            this .message = "errormsg";
        }
        this.mail = mail;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
        if (result) {
            this.message = "success";
        } else {
            this .message = "errormsg";
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "AddMailMessageDTO{" +
                "result=" + result +
                ", message='" + message + '\'' +
                '}';
    }
}
