package net.thumbtack.school.elections.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class Rank implements Serializable {

    @NotEmpty
    private String voterId;

    @NotNull
    @Min(value = 1, message = "Должно быть не меньше 1")
    @Max(value = 5, message = "Должно быть не больше 5")
    private int rank;

    public Rank(String voterId, int rank) {
        this.voterId = voterId;
        this.rank = rank;
    }

    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rank rank = (Rank) o;
        return voterId.equals(rank.voterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voterId);
    }
}
