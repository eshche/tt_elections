package net.thumbtack.school.elections.dto.requests;

import net.thumbtack.school.elections.model.Voter;
import org.hibernate.validator.constraints.NotEmpty;



public class VoteDtoRequest {
    @NotEmpty(message = "Token cannot be null")
    private String token;

    private String cand;

    public VoteDtoRequest(@NotEmpty(message = "Token cannot be null") String token, String cand) {
        this.token = token;
        this.cand = cand;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCand() {
        return cand;
    }

    public void setCand(String cand) {
        this.cand = cand;
    }
}
