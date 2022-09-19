package net.thumbtack.school.elections.dto.requests;

public class BecomeCandidateDtoRequest {
    private String token;

    public BecomeCandidateDtoRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
