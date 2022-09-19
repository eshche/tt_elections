package net.thumbtack.school.elections.service;


import com.google.gson.Gson;
import net.thumbtack.school.elections.dao.CandidateDao;
import net.thumbtack.school.elections.dao.RankDao;
import net.thumbtack.school.elections.dao.VoterDao;
import net.thumbtack.school.elections.dao.VotesDao;
import net.thumbtack.school.elections.daoimpl.CandidateDaoImpl;
import net.thumbtack.school.elections.daoimpl.RankDaoImpl;
import net.thumbtack.school.elections.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.daoimpl.VotesDaoImpl;
import net.thumbtack.school.elections.dto.requests.*;
import net.thumbtack.school.elections.dto.response.*;
import net.thumbtack.school.elections.elValidators.EValidator;
import net.thumbtack.school.elections.elValidators.ElectException;
import net.thumbtack.school.elections.model.Proposal;
import net.thumbtack.school.elections.model.Rank;
import net.thumbtack.school.elections.model.Voter;

import java.util.*;

public class VoterService {
    static VoterDao voterDao = new VoterDaoImpl();
    static CandidateDao candidateDao = new CandidateDaoImpl();
    static RankDao rankDao = new RankDaoImpl();
    static VotesDao votesDao = new VotesDaoImpl();

    public static String becomeCandidate(String requestJsonString) {
        Gson gson = new Gson();
        try {
            EValidator.checkElectionsNotStarted();
            BecomeCandidateDtoRequest bcdr = gson.fromJson(requestJsonString, BecomeCandidateDtoRequest.class);
            Optional.ofNullable(voterDao.getVoter(bcdr.getToken())).orElseThrow(() -> new ElectException("Неверный токен"));
            String userId = voterDao.getVoter(bcdr.getToken()).getLogin();//юзер из базы
            candidateDao.addCand(userId);// добавляем в базу кандидатов
            rankDao.getAllProposals().stream().filter(p->p.getAuthorId().equals(userId)).forEach(p->candidateDao.addPropos(userId, p));
            return gson.toJson(new BecomeCandDtoRResult(userId + " is a candidate"));
        } catch (ElectException e) {
            return gson.toJson(new ErrorDtoResult(e.getError()));
        }
    }

    public static String stopCandidate(String requestJsonString) {//снять кандидатуру
        Gson gson = new Gson();
        try {
            EValidator.checkElectionsNotStarted();
            StopCandidateDtoRequest scdr = gson.fromJson(requestJsonString, StopCandidateDtoRequest.class);
            Optional.ofNullable(voterDao.getVoter(scdr.getToken())).orElseThrow(() -> new ElectException("Неверный токен"));
            String user = voterDao.getVoter(scdr.getToken()).getLogin();//юзер из базы
            candidateDao.deleteCand(user);
            return gson.toJson(new StopCandDtoResult(user + " is not a candidate"));
        } catch (ElectException e) {
            return gson.toJson(new ErrorDtoResult(e.getError()));
        }
    }

    public static String getAllProposals(String requestJsonString) {// список всех предложений
        Gson gson = new Gson();
        try {
            GetAllProposalsDtoRequest gapr = gson.fromJson(requestJsonString, GetAllProposalsDtoRequest.class);
            Optional.ofNullable(voterDao.getVoter(gapr.getToken())).orElseThrow(() -> new ElectException("Неверный токен"));
            TreeMap<Double, String> result = new TreeMap<>();//     сюда пишем результат
            Set<Proposal> proposals = rankDao.getAllProposals();
            for (Proposal prop : proposals) {
                Double rank = Double.valueOf(0);
                for (Rank r : rankDao.getPropRanks(prop)) {
                    rank += r.getRank();
                }
                rank = rank / rankDao.getPropRanks(prop).size();
                result.put(rank, prop.getText());
            }
            return gson.toJson(new GetAllProposalsResult(result));
        } catch (ElectException e) {
            return gson.toJson(new ErrorDtoResult(e.getError()));
        }
    }

    public static String getAllCands(String requestJsonString) { //все кандидаты с предложениями
        Gson gson = new Gson();
        try {
            GetCandidatesDtoRequest gcdr = gson.fromJson(requestJsonString, GetCandidatesDtoRequest.class);
            Optional.ofNullable(voterDao.getVoter(gcdr.getToken())).orElseThrow(() -> new ElectException("Неверный токен"));
            HashMap<String, String> result = new HashMap<>(); //candidates with program
            Set<String> cands = candidateDao.getCands();
            for (String cand : cands) {
                StringJoiner program = new StringJoiner("; ");
                Collection<Proposal> candProps = candidateDao.getCandProp(cand);
                for (Proposal p : candProps) {
                    if (p != null) {
                        program.add(p.getText());
                    }
                }
                result.put(cand, program.toString());
            }
            return gson.toJson(new GetAllCandidatesResult(result));
        } catch (ElectException e) {
            return gson.toJson(new ErrorDtoResult(e.getError()));
        }
    }

    public static String getVotersProposals(String requestJsonString) {//список предложений неск.пользователей
        Gson gson = new Gson();
        try {
            GetVotersPropDtoRequest gvpdr = gson.fromJson(requestJsonString, GetVotersPropDtoRequest.class);
            Optional.ofNullable(voterDao.getVoter(gvpdr.getToken())).orElseThrow(() -> new ElectException("Неверный токен"));
            HashMap<String, String> result = new HashMap<>();// предложения заданных пользователей
            for (String login : gvpdr.getLogins()) {
                StringJoiner props = new StringJoiner("; ");
                for (Proposal p : rankDao.getAllProposals())
                    if (p.getAuthorId().equals(login)) {
                        props.add(p.getText());
                    }
                result.put(login, props.toString());
            }
            return gson.toJson(new GetVotersPropDtoResult(result));
        } catch (ElectException e) {
            return gson.toJson(new ErrorDtoResult(e.getError()));
        }
    }

    public static String vote(String requestJsonString) {
        Gson gson = new Gson();
        try {
            VoteDtoRequest vdr = gson.fromJson(requestJsonString, VoteDtoRequest.class);
            EValidator.checkElectionsStarted();
            Optional<Voter> optionalUser = Optional.ofNullable(voterDao.getVoter(vdr.getToken()));
            Voter user = optionalUser.orElseThrow(() -> new ElectException("Неверный токен"));
            String voterLogin = user.getLogin();
            Optional<String> optionalCand = Optional.ofNullable(candidateDao.checkCand(vdr.getCand()));
            String voteCand = optionalCand.orElseThrow(() -> new ElectException("Неверный кандидат"));
            votesDao.vote(voterLogin, voteCand);
            String votedCand = votesDao.getUserVote(voterLogin);
            return gson.toJson(new VoteDtoResult("вы проголосовали за " + votedCand));
        } catch (ElectException e) {
            return gson.toJson(new ErrorDtoResult(e.getError()));
        }

    }
}


