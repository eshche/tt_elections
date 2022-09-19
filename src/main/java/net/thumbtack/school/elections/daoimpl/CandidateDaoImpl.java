package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.CandidateDao;
import net.thumbtack.school.elections.database.Database;
import net.thumbtack.school.elections.model.Proposal;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;


public class CandidateDaoImpl implements CandidateDao {

    @Override
    public Set<String> getCands() {
        return Database.getInstance().getCandidatesData().keySet();
    }

    @Override
    public void deleteCand(String login) {
        Database.getInstance().getCandidatesData().remove(login);
    }

    @Override
    public void addCand(String user) {
        Database.getInstance().getCandidatesData().put(user, null);
    }

    @Override
    public void addPropos(String cand, Proposal pr) {
        Database.getInstance().getCandidatesData().put(cand, pr);
    }

    //@Override
    public MultiValueMap<String, Proposal> getAll() {
        return Database.getInstance().getCandidatesData();
    }

    @Override
    public void removeProgram(String cand, Proposal delProp) {
        Database.getInstance().getCandidatesData().removeMapping(cand, delProp);
    }

    @Override
    public Collection<Proposal> getCandProp(String cand) {
        return Database.getInstance().getCandidatesData().getCollection(cand);
    }
    @Override
    public String checkCand(String cand) {
        if (Database.getInstance().getCandidatesData().containsKey(cand)){
            return cand;
        }else{
            return null;
        }
    }

}
