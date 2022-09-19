package net.thumbtack.school.elections.dto.requests;

import javax.validation.constraints.*;

public class RankProposaleDtoRequest {

    private String token;
    private String authorId;
    private String text;//

    @Max(value = 5, message = "Rank could be 1-5")
    @NotNull(message = "Rank could be 1-5")
    private int rank;

    public RankProposaleDtoRequest(String token, String authorId, String text, @Max(value = 5, message = "Rank could be 1-5") @NotNull(message = "Rank could be 1-5") int rank) {
        this.token = token;
        this.authorId = authorId;
        this.text = text;
        this.rank = rank;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
