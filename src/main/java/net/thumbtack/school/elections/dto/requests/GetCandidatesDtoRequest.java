package net.thumbtack.school.elections.dto.requests;

public class GetCandidatesDtoRequest {
    private String token;

    public GetCandidatesDtoRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
