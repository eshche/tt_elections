package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.dao.CandidateDao;
import net.thumbtack.school.elections.dao.RankDao;
import net.thumbtack.school.elections.daoimpl.CandidateDaoImpl;
import net.thumbtack.school.elections.daoimpl.RankDaoImpl;
import net.thumbtack.school.elections.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.database.Database;
import net.thumbtack.school.elections.dto.requests.AddProgramDtoRequest;
import net.thumbtack.school.elections.dto.requests.DeleteProgramDtoRequest;
import net.thumbtack.school.elections.dto.response.AddProgramDtoResult;
import net.thumbtack.school.elections.dto.response.DeleteProgramDtoResult;
import net.thumbtack.school.elections.dto.response.ErrorDtoResult;
import net.thumbtack.school.elections.elValidators.EValidator;
import net.thumbtack.school.elections.elValidators.ElectException;
import net.thumbtack.school.elections.model.Proposal;
import net.thumbtack.school.elections.model.Voter;

import java.util.Optional;


public class CandidateService extends VoterService {
    static CandidateDao candidateDao = new CandidateDaoImpl();
    static RankDao rankDao = new RankDaoImpl();

    public static String addProgram(String requestJsonString) throws ElectException {//добавление в программу из базы предложений по строке
        Gson gson = new Gson();
        try {
            EValidator.checkElectionsNotStarted();
            String result = null;
            AddProgramDtoRequest apdr = gson.fromJson(requestJsonString, AddProgramDtoRequest.class);
            Optional.ofNullable(voterDao.getVoter(apdr.getToken())).orElseThrow(() -> new ElectException("Неверный токен"));
            String cand = new VoterDaoImpl().getVoter(apdr.getToken()).getLogin();
            Proposal prop = new Proposal(apdr.getAuthorLogin(), apdr.getText());
            if (rankDao.getAllProposals().contains(prop)) {
                candidateDao.addPropos(cand, prop);
                result = "You added program " + apdr.getText();
            }
            Optional.ofNullable(Optional.ofNullable(result).orElseThrow(() -> new ElectException("Неверная программа")));
            return gson.toJson(new AddProgramDtoResult(result));
        } catch (
                ElectException e) {
            return gson.toJson(new ErrorDtoResult(e.getError()));
        }
    }


    public static String deleteProgram(String requestJsonString) {
        Gson gson = new Gson();
        try {
            EValidator.checkElectionsNotStarted();
            DeleteProgramDtoRequest dpdr = gson.fromJson(requestJsonString, DeleteProgramDtoRequest.class);
            Optional<Voter> optionalUser = Optional.ofNullable(voterDao.getVoter(dpdr.getToken()));
            Voter user = optionalUser.orElseThrow(() -> new ElectException("Неверный токен"));
            String cand = user.getLogin();
            Proposal delProp = new Proposal(dpdr.getAuthorId(), dpdr.getText());
            if (dpdr.getAuthorId().equals(user.getLogin())) {
                throw new ElectException("Нельзя удалять свои предложения");
            } else {
                candidateDao.removeProgram(cand, delProp);
                return gson.toJson(new DeleteProgramDtoResult("You deleted " + dpdr.getText()));
            }
        } catch (ElectException e) {
            return gson.toJson(new ErrorDtoResult(e.getError()));
        }
    }
}