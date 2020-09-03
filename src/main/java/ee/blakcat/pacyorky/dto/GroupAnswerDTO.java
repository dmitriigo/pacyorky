package ee.blakcat.pacyorky.dto;

public class GroupAnswerDTO {
    private String groupId;
    private String userId;
    private String token;

    public GroupAnswerDTO(String groupId, String userId, String token) {
        this.groupId = groupId;
        this.userId = userId;
        this.token = token;
    }

    public GroupAnswerDTO() {
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "GroupAnswerDTO{" +
                "groupId='" + groupId + '\'' +
                ", userId='" + userId + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
