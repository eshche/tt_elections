package net.thumbtack.school.elections.dto.requests;

public class StopCandidateDtoRequest {
    private String token;

    public StopCandidateDtoRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
