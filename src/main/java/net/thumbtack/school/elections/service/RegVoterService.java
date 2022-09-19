package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import net.thumbtack.school.elections.dao.CandidateDao;
import net.thumbtack.school.elections.dao.RankDao;
import net.thumbtack.school.elections.dao.VoterDao;
import net.thumbtack.school.elections.dao.VotesDao;
import net.thumbtack.school.elections.daoimpl.CandidateDaoImpl;
import net.thumbtack.school.elections.daoimpl.RankDaoImpl;
import net.thumbtack.school.elections.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.daoimpl.VotesDaoImpl;
import net.thumbtack.school.elections.dto.requests.*;
import net.thumbtack.school.elections.elValidators.EValidator;
import net.thumbtack.school.elections.elValidators.ElectException;
import net.thumbtack.school.elections.dto.response.*;
import net.thumbtack.school.elections.elValidators.ValidatorSetup;
import net.thumbtack.school.elections.model.Proposal;
import net.thumbtack.school.elections.model.Rank;
import net.thumbtack.school.elections.model.Voter;
import org.apache.commons.collections4.BidiMap;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

public class RegVoterService {
    static VoterDao voterDao = new VoterDaoImpl();
    static CandidateDao candidateDao = new CandidateDaoImpl();
    static RankDao rankDao = new RankDaoImpl();


    public static String registerVoter(String requestJsonString) {
        Gson gson = new Gson();
        try {
            EValidator.checkElectionsNotStarted();
            RegisterVoterDtoRequest rvdr = gson.fromJson(requestJsonString, RegisterVoterDtoRequest.class);
            String token = UUID.randomUUID().toString();
            Voter voter = new Voter(rvdr.getLogin(), rvdr.getPassword(), rvdr.getLastName(), rvdr.getFirstName(), rvdr.getPatronimyc(), rvdr.getStreet(), rvdr.getHouse(), rvdr.getFlat());
            Set<ConstraintViolation<Voter>> constraintViolations = ValidatorSetup.setUpValidator().validate(voter);
            if (constraintViolations.size() > 0) {
                StringJoiner error = new StringJoiner(". ");
                for (ConstraintViolation constraintViolation : constraintViolations) {
                    error.add(constraintViolation.getMessage());
                }
                throw new ElectException(error.toString());
            }
            voterDao.put(token, voter);
            return gson.toJson(new RegisterVoterDtoResult(token));
        } catch (ElectException ex) {
            return gson.toJson(new ErrorDtoResult(ex.getError()));
        }
    }

    public static String login(String requestJsonString) {//
        Gson gson = new Gson();
        try {
            EValidator.checkElectionsNotStarted();
            LoginDtoRequest lidr = gson.fromJson(requestJsonString, LoginDtoRequest.class);
            Optional<Voter> optionalUser = Optional.ofNullable(voterDao.getVoter(lidr.getToken()));
            Voter user = optionalUser.orElseThrow(() -> new ElectException("Неверный токен"));
            if (!user.getLogin().equals(lidr.getLogin())) {
                return gson.toJson(new ErrorDtoResult("Wrong login"));
            } else if (
                    !user.getPassword().equals(lidr.getPassword())) {
                return gson.toJson(new ErrorDtoResult("Wrong password"));
            }
            return gson.toJson(new LoginDtoResult(user));
        } catch (ElectException e) {
            return gson.toJson(new ErrorDtoResult(e.getError()));
        } catch (JsonSyntaxException e) {
            return gson.toJson(new ErrorDtoResult("jsonMistake"));
        }
    }

    public static String inviteCand(String requestJsonString) throws ElectException {
        Gson gson = new Gson();
        try {
            EValidator.checkElectionsNotStarted();
            InviteCandDtoRequest icdr = gson.fromJson(requestJsonString, InviteCandDtoRequest.class);
            Optional.ofNullable(voterDao.getVoter(icdr.getToken())).orElseThrow(() -> new ElectException("Неверный токен"));
            String myCandId = icdr.getMycandidateLogin();
         //   BidiMap<String, Voter> users = voterDao.getAllVoters();

            Voter emptyVoter = new Voter(myCandId, "", "0", "0", "0", "0", "0", "0");
            String candToken = voterDao.getTokenByVoter(emptyVoter);
            Optional<Voter> optionalUser = Optional.ofNullable(voterDao.getVoter(candToken));
            Voter cand = optionalUser.orElseThrow(() -> new ElectException("Неверный кандидат"));
            cand.setInvited();
            voterDao.put(candToken, cand);
            return gson.toJson(new InviteCandResult(cand));

        } catch (ElectException e) {
            return gson.toJson(new ErrorDtoResult(e.getError()));
        }
    }

    public static String logout(String requestJsonString) {
        Gson gson = new Gson();
        try {
            EValidator.checkElectionsNotStarted();
            LogoutDtoRequest ldr = gson.fromJson(requestJsonString, LogoutDtoRequest.class);
            String token = ldr.getToken();
            Optional<Voter> optionalUser = Optional.ofNullable(voterDao.getVoter(ldr.getToken()));
            Voter user = optionalUser.orElseThrow(() -> new ElectException("Неверный токен"));
            String login = user.getLogin();
            candidateDao.deleteCand(login); // снять кандидатуру

            for (Proposal pr : rankDao.getAllProposals()) {
                Rank emptyRank = new Rank(login, 1);
                if (rankDao.getPropRanks(pr).contains(emptyRank)) {
                    rankDao.removeRank(pr, emptyRank);
                }
            }//Удалить оценки

            for (Proposal pr : rankDao.getAllProposals()) {
                if (pr.getAuthorId().equals(login)) {
                    ArrayList<Rank> propRanks = rankDao.getPropRanks(pr);
                    rankDao.removeProp(pr);
                    Proposal cityProp = new Proposal("CITY", pr.getText());
                    for (Rank r : propRanks) {
                        rankDao.addProposalWithRank(cityProp, r);
                    }
                }
            }// предложения от города

            Voter logoutVoter = voterDao.getVoter(token);
            voterDao.remove(token);
            voterDao.put("0", logoutVoter);
            return gson.toJson(new LogOutDtoResult("Logout success"));
        } catch (NullPointerException e) {
            return gson.toJson(new ErrorDtoResult("Неверный запрос"));
        } catch (ElectException e) {
            return gson.toJson(new ErrorDtoResult(e.getError()));
        }
    }

}
