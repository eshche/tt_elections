package net.thumbtack.school.elections.dto.response;

import org.hibernate.validator.constraints.NotEmpty;



public class RegisterVoterDtoResult {
    @NotEmpty(message = "Token cannot be null")
    private String token;

    public RegisterVoterDtoResult(@NotEmpty(message = "Token cannot be null") String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}


