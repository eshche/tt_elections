package net.thumbtack.school.elections.service;

import com.google.gson.Gson;

import net.thumbtack.school.elections.dao.VoterDao;

import net.thumbtack.school.elections.dao.RankDao;
import net.thumbtack.school.elections.daoimpl.RankDaoImpl;
import net.thumbtack.school.elections.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.dto.requests.AddProposaleDtoRequest;
import net.thumbtack.school.elections.dto.requests.RankProposaleDtoRequest;
import net.thumbtack.school.elections.dto.response.AddProposalDtoResult;
import net.thumbtack.school.elections.dto.response.ErrorDtoResult;
import net.thumbtack.school.elections.dto.response.RankPropDtoResult;
import net.thumbtack.school.elections.elValidators.ElectException;
import net.thumbtack.school.elections.elValidators.ValidatorSetup;
import net.thumbtack.school.elections.model.Proposal;
import net.thumbtack.school.elections.model.Rank;
import net.thumbtack.school.elections.model.Voter;

import javax.validation.ConstraintViolation;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;

public class ProposaleService {
    static VoterDao voterDao = new VoterDaoImpl();
    static RankDao rankDao = new RankDaoImpl();

    public static String addProposal(String requestJsonString) { //внести предложение
        Gson gson = new Gson();
        try {
            AddProposaleDtoRequest apdr = gson.fromJson(requestJsonString, AddProposaleDtoRequest.class);
            Optional.ofNullable(voterDao.getVoter(apdr.getToken())).orElseThrow(() -> new ElectException("Неверный токен"));
            String userId = voterDao.getVoter(apdr.getToken()).getLogin();
            Proposal prop = new Proposal(userId, apdr.getText());
            rankDao.addProposalWithRank(prop, new Rank(userId, 5));// добавляем в предложения
            return gson.toJson(new AddProposalDtoResult(prop)); //
        } catch (ElectException e) {
            return gson.toJson(new ErrorDtoResult(e.getError()));
        }
    }


    public static String rankProposal(String requestJsonString) { //оценить предложение
        Gson gson = new Gson();
        try {
            RankProposaleDtoRequest rpdr = gson.fromJson(requestJsonString, RankProposaleDtoRequest.class);
            Optional.ofNullable(voterDao.getVoter(rpdr.getToken())).orElseThrow(() -> new ElectException("Неверный токен"));
            String userId = new VoterDaoImpl().getVoter(rpdr.getToken()).getLogin();
            Proposal propForRank = new Proposal(rpdr.getAuthorId(), rpdr.getText());
            Rank proprank = new Rank(userId, rpdr.getRank());
            Set<ConstraintViolation<Rank>> constraintViolations = ValidatorSetup.setUpValidator().validate(proprank);
            if (constraintViolations.size() > 0) {
                StringJoiner error = new StringJoiner(". ");
                for (ConstraintViolation constraintViolation : constraintViolations) {
                    error.add(constraintViolation.getMessage());
                }
                throw new ElectException(error.toString());
            }
            if (propForRank.getAuthorId().equals(userId)) {
                 throw new ElectException("Нельзя менять свою оценку");
            } else {
                if (rankDao.getPropRanks(propForRank).contains(proprank)) {
                    rankDao.removeRank(propForRank, proprank);
                }
            }
            rankDao.addProposalWithRank(propForRank, proprank);
            return gson.toJson(new RankPropDtoResult(propForRank.getAuthorId(), propForRank.getText(), proprank.getRank()));
        } catch (ElectException ex) {
            return gson.toJson(new ErrorDtoResult(ex.getError()));
        }
    }
}
