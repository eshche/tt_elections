package net.thumbtack.school.elections.model;

import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Objects;

public class Proposal implements Serializable {
    @NotEmpty (message = "Не должно быть пустым")
    private String authorId;

    @NotEmpty (message = "Не должно быть пустым")
    private String text;

    public String getAuthorId() {
        return authorId;
    }

    public Proposal(String authorId, String text) {
        this.authorId = authorId;
        this.text = text;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proposal proposal = (Proposal) o;
        return authorId.equals(proposal.authorId) &&
                text.equals(proposal.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, text);
    }
}
