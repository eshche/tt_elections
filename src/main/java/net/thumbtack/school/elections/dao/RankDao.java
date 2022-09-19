package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.model.Proposal;
import net.thumbtack.school.elections.model.Rank;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public interface RankDao {

    ArrayList<Rank> getPropRanks(Proposal propForRank);

    void removeRank(Proposal propForRank, Rank proprank);

    Set<Proposal> getAllProposals();


    void removeProp(Proposal pr);

    void addProposalWithRank(Proposal cityProp, Rank r);

}
