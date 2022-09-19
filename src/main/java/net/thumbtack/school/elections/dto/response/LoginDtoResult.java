package net.thumbtack.school.elections.dto.response;

import net.thumbtack.school.elections.model.Voter;

public class LoginDtoResult {
    private Voter result;

    public LoginDtoResult(Voter result) {
        this.result = result;
    }

    public Voter getResult() {
        return result;
    }

    public void setResult(Voter result) {
        this.result = result;
    }
}
