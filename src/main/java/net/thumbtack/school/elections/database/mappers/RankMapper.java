package net.thumbtack.school.elections.database.mappers;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.elections.model.Proposal;
import net.thumbtack.school.elections.model.Rank;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface RankMapper {
    @Insert("INSERT INTO user_rank (proposalId, userLogin, userRank) VALUES (#{propId}, #{rank.voterId}, #{rank.rank})")
    @Options(useGeneratedKeys = true, keyProperty = "user.id")
    Integer insert(@Param("rank") Rank rank, @Param("propId") Integer propId);

    @Select("Select id, name, room FROM `group` WHERE schoolid = #{id}")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "trainees", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.TraineeMapper.getTraineesByGroup", fetchType = FetchType.LAZY)),
            @Result(property = "subjects", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.SubjectMapper.getSubjectsByGroup", fetchType = FetchType.LAZY))
    })
    List<Rank> getPropRanks(Integer propId);
}
