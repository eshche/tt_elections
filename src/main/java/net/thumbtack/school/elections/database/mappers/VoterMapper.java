package net.thumbtack.school.elections.database.mappers;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.elections.model.Voter;
import org.apache.commons.collections4.BidiMap;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface VoterMapper {
    @Insert("INSERT INTO user (login, password, lastName, firstName, patronymic, street, house, flat) VALUES (#{voter.login}, #{voter.password}, #{voter.lastName}, #{voter.firstName}, #{voter.patronymic}, #{voter.street}, #{voter.house}, #{voter.flat})")
    @Options(useGeneratedKeys = true, keyProperty = "user.id")
    Integer insert(@Param("voter") Voter voter);

    @Insert("INSERT INTO token_user (token, userId) VALUES (#{token}, #{id})")
    @Options(useGeneratedKeys = true, keyProperty = "user.id")
    Integer insert(@Param("token") String token, @Param("id") Integer id);

    @Select("Select login, password, lastName, firstName, patronymic, street, house, flat FROM user WHERE token = #{token}")
    Voter get(String token);

    @Update("UPDATE user SET token = null WHERE token = #{token}")
    void delete(String token);

    @Select("Select login, password, lastName, firstName, patronymic, street, house, flat WHERE schoolid = #{id}")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "trainees", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.TraineeMapper.getTraineesByGroup", fetchType = FetchType.LAZY))})
    BidiMap<String, Voter> getAll();

    @Select("Select id, name, room FROM `group` WHERE schoolid = #{id}")
    String getToken(String login);
}
