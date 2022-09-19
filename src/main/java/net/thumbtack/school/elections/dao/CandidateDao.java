package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.model.Proposal;
import org.apache.commons.collections4.MultiMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface CandidateDao {
    Set<String> getCands();

    void deleteCand(String login);

    void addCand(String user);

    void addPropos(String cand, Proposal pr);

    MultiMap<String, Proposal> getAll();

    void removeProgram(String cand, Proposal delProp);

    Collection<Proposal> getCandProp(String cand);

    public String checkCand(String cand);
}
