package net.thumbtack.school.elections.dao;

import java.util.HashMap;

public interface VotesDao {
    void vote(String voter, String cand);

    HashMap<String, String> getVotes();

    public String getUserVote(String user);
}
