package net.thumbtack.school.elections.dto.requests;

public class DeleteProgramDtoRequest {

    private String token;
    private String AuthorId;
    private String text;

    public DeleteProgramDtoRequest(String token, String authorId, String text) {
        this.token = token;
        AuthorId = authorId;
        this.text = text;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAuthorId() {
        return AuthorId;
    }

    public void setAuthorId(String authorId) {
        AuthorId = authorId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
