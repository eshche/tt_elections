package net.thumbtack.school.elections.dto.response;

import net.thumbtack.school.elections.model.Voter;

public class VoteDtoResult {
    private String result;

    public VoteDtoResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
