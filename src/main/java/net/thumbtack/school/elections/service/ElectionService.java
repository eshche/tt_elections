package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.dao.CandidateDao;
import net.thumbtack.school.elections.dao.ElectDao;
import net.thumbtack.school.elections.dao.VoterDao;
import net.thumbtack.school.elections.dao.VotesDao;
import net.thumbtack.school.elections.daoimpl.CandidateDaoImpl;
import net.thumbtack.school.elections.daoimpl.ElectDaoImpl;
import net.thumbtack.school.elections.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.daoimpl.VotesDaoImpl;
import net.thumbtack.school.elections.dto.requests.ManageVotingDtoRequest;
import net.thumbtack.school.elections.dto.requests.VoteDtoRequest;
import net.thumbtack.school.elections.dto.response.ErrorDtoResult;
import net.thumbtack.school.elections.dto.response.ManageVotingDtoResult;
import net.thumbtack.school.elections.dto.response.VoteDtoResult;
import net.thumbtack.school.elections.elValidators.EValidator;
import net.thumbtack.school.elections.elValidators.ElectException;
import net.thumbtack.school.elections.model.Voter;

import java.util.HashMap;
import java.util.Optional;

public class ElectionService {  // действия на выборах
    static ElectDao electDao = new ElectDaoImpl();

    public static String startElections(String requestJsonString) {
        Gson gson = new Gson();
        CandidateDao candidateDao = new CandidateDaoImpl();
        try {
            final String STARTER = "StartVote";
            ManageVotingDtoRequest manageVotingDtoRequest = gson.fromJson(requestJsonString, ManageVotingDtoRequest.class);
            if (manageVotingDtoRequest.getText().equals(STARTER)) {
                for (String cand : candidateDao.getCands()) {
                    candidateDao.removeProgram(cand, null);// удаляем пустую программу, созданную при регистрации кандидата
                }
                electDao.setVoteActive(true);
            }
            return gson.toJson("Voting is started");
        } catch (Exception ex) {
            return gson.toJson(new ErrorDtoResult("Wrong request"));
        }
    }

    public static String stopElections(String requestJsonString) {
        final String STOPPER = "StopVote";
        Gson gson = new Gson();
        try {
            ManageVotingDtoRequest mvdr = gson.fromJson(requestJsonString, ManageVotingDtoRequest.class);
            if (mvdr.getText().equals(STOPPER)) {
                electDao.setVoteActive(false);
            }
            return gson.toJson(new ManageVotingDtoResult("Election closed"));
        } catch (Exception ex) {
            return gson.toJson(new ErrorDtoResult("Wrong request"));
        }
    }
}
