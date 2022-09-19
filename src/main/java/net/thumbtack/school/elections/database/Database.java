package net.thumbtack.school.elections.database;

import net.thumbtack.school.elections.model.Proposal;
import net.thumbtack.school.elections.model.Rank;
import net.thumbtack.school.elections.model.Voter;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.apache.commons.collections4.map.MultiValueMap;


import java.io.Serializable;
import java.util.HashMap;

public class Database implements Serializable {
    private static Database instance; //экземпляр

    private DualHashBidiMap<String, Voter> votersData;//  токен, горожанин
    private MultiValueMap<Proposal, Rank> proposalsData;//  все оценки всех предложений
    private MultiValueMap<String, Proposal> candidatesData;// логин кандидат, предложение
    private HashMap<String, String> votesData;// cdi  избиратель, кандидат,за которого голосует
    private boolean voteActive; // cостояние выборов

    public Database(DualHashBidiMap dualHashBidiMap, MultiValueMap multiValueMap, MultiValueMap multiValueMap2, HashMap hashMap, boolean isvoteactive) {
        this.votersData = dualHashBidiMap;
        this.proposalsData = multiValueMap;
        this.candidatesData = multiValueMap2;
        this.votesData = hashMap;
        this.voteActive = isvoteactive;
    }

    public BidiMap<String, Voter> getVotersData() {
        return votersData;
    }


    public MultiValueMap<Proposal, Rank> getProposalsData() {
        return proposalsData;
    }

    public MultiValueMap<String, Proposal> getCandidatesData() {
        return candidatesData;
    }

    public HashMap<String, String> getVotesData() {
        return votesData;
    }

    public boolean isVoteActive() {
        return voteActive;
    }

    public void setVoteActive(boolean voteActive) {
        this.voteActive = voteActive;
    }

    public static Database getInstance(BidiMap<String, Voter> votersData, MultiValueMap<Proposal, Rank> proposalsData, MultiValueMap<String, Proposal> candidatesData, HashMap<String, String> votesData, boolean voteActive) {
        if (instance == null) {
            instance = new Database(new DualHashBidiMap(), new MultiValueMap<>(), new MultiValueMap<>(), new HashMap<>(), false);
        }
        return instance;
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database(new DualHashBidiMap(), new MultiValueMap<>(), new MultiValueMap<>(), new HashMap<>(), false);
        }
        return instance;
    }

}
