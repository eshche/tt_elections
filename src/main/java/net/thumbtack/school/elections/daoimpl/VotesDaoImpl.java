package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.VotesDao;
import net.thumbtack.school.elections.database.Database;

import java.util.HashMap;

public class VotesDaoImpl implements VotesDao {
    private HashMap<String, String> votes = Database.getInstance().getVotesData();

    @Override
    public void vote(String voter, String cand) {
        votes.putIfAbsent(voter, cand);
    }

    @Override
    public HashMap<String, String> getVotes() {
        return votes;
    }

    @Override
    public String getUserVote(String user) {
        return votes.getOrDefault(user, "No votes");
    }
}
