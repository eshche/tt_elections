package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.database.model.Group;

import net.thumbtack.school.elections.dao.VoterDao;
import net.thumbtack.school.elections.database.Database;
import net.thumbtack.school.elections.database.mappers.VoterMapper;
import net.thumbtack.school.elections.model.Voter;
import org.apache.commons.collections4.BidiMap;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class VoterDaoImpl extends DaoImplBase implements VoterDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(Voter.class);

    @Override
    public void put(String token, Voter voter) {
        LOGGER.debug("DAO insert Voter {}", voter);
        try (SqlSession sqlSession = getSession()) {
            try {
                VoterMapper voterMapper = getVoterMapper(sqlSession);
                voterMapper.insert(voter);
                Integer id = voterMapper.insert(voter);
                voterMapper.insert(token, id);

            } catch (RuntimeException ex) {
                LOGGER.info("Can't insert Voter {}", voter, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public Voter getVoter(String token) {
        LOGGER.debug("DAO get Voter");
        try (SqlSession sqlSession = getSession()) {
            return getVoterMapper(sqlSession).get(token);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get Voter", ex);
            throw ex;
        }
    }

    @Override
    public void remove(String token) {  LOGGER.debug("DAO delete token {}", token);
        try (
                SqlSession sqlSession = getSession()) {
            try {
                getVoterMapper(sqlSession).delete(token);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete token {}", token, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

/*    @Override
    public BidiMap<String, Voter> getAllVoters() {
        LOGGER.debug("DAO get Voters");
        try (SqlSession sqlSession = getSession()) {
            return getVoterMapper(sqlSession).getAll();
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get Voters", ex);
            throw ex;
        }
    }*/

    @Override
    public String getTokenByVoter(Voter voter) {
        LOGGER.debug("DAO get token");
        try (SqlSession sqlSession = getSession()) {
            return getVoterMapper(sqlSession).getToken(voter.getLogin());
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get token", voter, ex);
            throw ex;
        }
    }


}