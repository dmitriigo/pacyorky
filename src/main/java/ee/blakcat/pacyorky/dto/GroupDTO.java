package ee.blakcat.pacyorky.dto;

public class GroupDTO {
    private String text;
    private String value;

    public GroupDTO(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public GroupDTO() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "GroupDTO{" +
                "text='" + text + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
