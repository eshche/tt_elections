package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.database.mybatis.mappers.SubjectMapper;
import net.thumbtack.school.database.mybatis.utils.MyBatisUtils;
import net.thumbtack.school.elections.database.mappers.ProposalMapper;
import net.thumbtack.school.elections.database.mappers.RankMapper;
import net.thumbtack.school.elections.database.mappers.VoterMapper;
import org.apache.ibatis.session.SqlSession;

public class DaoImplBase {

    protected SqlSession getSession() {
        return MyBatisUtils.getSqlSessionFactory().openSession();
    }

    protected VoterMapper getVoterMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(VoterMapper.class);
    }

    protected ProposalMapper getProposalMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(ProposalMapper.class);
    }

    protected RankMapper getRankMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(RankMapper.class);
    }

    protected SubjectMapper getSubjectMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(SubjectMapper.class);
    }

}