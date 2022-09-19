package net.thumbtack.school.elections.dto.response;

public class RankPropDtoResult {
   private String AuthorId;
   private String text;
   private int rank15;

    public RankPropDtoResult(String authorId, String text, Integer rank15) {
        AuthorId = authorId;
        this.text = text;
        this.rank15 = rank15;
    }

    public String getAuthorId() {
        return AuthorId;
    }

    public void setAuthorId(String authorId) {
        AuthorId = authorId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRank15() {
        return rank15;
    }

    public void setRank15(int rank15) {
        this.rank15 = rank15;
    }
}
