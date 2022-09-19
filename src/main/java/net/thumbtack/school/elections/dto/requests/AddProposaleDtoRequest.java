package net.thumbtack.school.elections.dto.requests;


import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class AddProposaleDtoRequest {
   // @NotEmpty (message = "Token cannot be null")
    private String token;
//    @NotEmpty (message = "Proposal cannot be null")
    private String text;

    public AddProposaleDtoRequest(@NotBlank @NotNull String token, @NotBlank @NotNull String text) {
        this.token = token;
        this.text = text;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
