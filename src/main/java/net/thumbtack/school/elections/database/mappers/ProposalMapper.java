package net.thumbtack.school.elections.database.mappers;

import net.thumbtack.school.elections.model.Proposal;
import net.thumbtack.school.elections.model.Voter;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

public interface ProposalMapper {
    @Insert("INSERT INTO proposal (userLogin, text) VALUES (#{proposal.authorId}, #{proposal.text})")
    @Options(useGeneratedKeys = true, keyProperty = "user.id")
    Integer insert(@Param("proposal") Proposal proposal);

}
