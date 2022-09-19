package net.thumbtack.school.elections.dto.requests;

import net.thumbtack.school.elections.model.Voter;
import org.hibernate.validator.constraints.NotEmpty;


public class InviteCandDtoRequest {
    private String token;
    private String mycandidateLogin;

    public InviteCandDtoRequest(@NotEmpty(message = "Token cannot be null") String token, String mycandidateLogin) {
        this.token = token;
        this.mycandidateLogin = mycandidateLogin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMycandidateLogin() {
        return mycandidateLogin;
    }

    public void setMycandidateLogin(String mycandidateLogin) {
        this.mycandidateLogin = mycandidateLogin;
    }
}
