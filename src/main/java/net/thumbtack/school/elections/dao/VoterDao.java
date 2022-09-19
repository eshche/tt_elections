package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.model.Voter;
import org.apache.commons.collections4.BidiMap;

public interface VoterDao {

    void put(String token, Voter voter);

    Voter getVoter(String token);

    void remove(String token);

  //  BidiMap<String, Voter> getAllVoters();

    String getTokenByVoter(Voter voter);
}
