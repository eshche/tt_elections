package net.thumbtack.school.elections.dto.requests;

public class GetAllProposalsDtoRequest {
    private String token;

    public GetAllProposalsDtoRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
