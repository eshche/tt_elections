package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.RankDao;
import net.thumbtack.school.elections.database.Database;
import net.thumbtack.school.elections.database.mappers.ProposalMapper;
import net.thumbtack.school.elections.database.mappers.RankMapper;
import net.thumbtack.school.elections.model.Proposal;
import net.thumbtack.school.elections.model.Rank;
import net.thumbtack.school.elections.model.Voter;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.*;

public class RankDaoImpl extends DaoImplBase implements RankDao {
    private static final Logger LOGGER1 = LoggerFactory.getLogger(Proposal.class);
    private static final Logger LOGGER2 = LoggerFactory.getLogger(Rank.class);

    @Override
    public void addProposalWithRank(Proposal prop, Rank rank) {
        LOGGER1.debug("DAO insert Proposal {}", prop);
        try (SqlSession sqlSession = getSession()) {
            try {
                Integer propId = getProposalMapper(sqlSession).insert(prop);
              //  getRankMapper(sqlSession).insert(rank, propId);
            } catch (RuntimeException ex) {
                LOGGER1.info("Can't insert Proposal {}", prop, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public ArrayList<Rank> getPropRanks(Proposal propForRank) {
        return (ArrayList<Rank>) Database.getInstance().getProposalsData().get(propForRank);
    }

    @Override
    public void removeRank(Proposal propForRank, Rank proprank) {
        Database.getInstance().getProposalsData().removeMapping(propForRank, proprank);
    }

    @Override
    public Set<Proposal> getAllProposals() {
        return Database.getInstance().getProposalsData().keySet();
    }

    @Override
    public void removeProp(Proposal pr) {
        Database.getInstance().getProposalsData().remove(pr);
    }

//   @Override
//    public Optional<String> getVoterProp(String voter) {
//        return Database.getInstance().getProposalsData().
//
//    }

}
