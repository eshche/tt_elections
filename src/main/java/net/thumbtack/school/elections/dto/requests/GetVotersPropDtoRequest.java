package net.thumbtack.school.elections.dto.requests;

import net.thumbtack.school.elections.model.Voter;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

public class GetVotersPropDtoRequest {
    @NotEmpty(message = "Token cannot be null")
    private String token;
    @NotEmpty (message = "Enter voters")
    private List<String> logins;

    public GetVotersPropDtoRequest(@NotEmpty(message = "Token cannot be null") String token, List logins) {
        this.token = token;
        this.logins = logins;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getLogins() {
        return logins;
    }

    public void setLogins(ArrayList<String> logins) {
        this.logins = logins;
    }
}
