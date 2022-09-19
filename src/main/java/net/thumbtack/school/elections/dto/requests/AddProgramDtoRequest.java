package net.thumbtack.school.elections.dto.requests;

public class AddProgramDtoRequest {

    private String token;
    private String authorLogin;
    private String text;

    public AddProgramDtoRequest(String token, String authorLogin, String text) {
        this.token = token;
        this.authorLogin = authorLogin;
        this.text = text;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAuthorLogin() {
        return authorLogin;
    }

    public void setAuthorLogin(String authorLogin) {
        this.authorLogin = authorLogin;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
