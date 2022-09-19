package net.thumbtack.school.elections.dto.response;

public class ManageVotingDtoResult {
    private String result;

    public ManageVotingDtoResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
