package net.thumbtack.school.elections;




import com.google.gson.Gson;
import net.thumbtack.school.elections.database.Database;
import net.thumbtack.school.elections.elValidators.ElectException;
import net.thumbtack.school.elections.service.*;

import java.io.*;

public class Server {

    public static void main(String[] args) throws IOException {
        startServer(String.valueOf(args));
    }

   public static void startServer(String savedDataFileName) throws IOException, IllegalArgumentException, NullPointerException { Gson gson = new Gson();
        String path = savedDataFileName + ".txt";
        File file = new File(path);
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                Database base = gson.fromJson(br, Database.class);
                if (base != null)
                    Database.getInstance(base.getVotersData(), base.getProposalsData(), base.getCandidatesData(), base.getVotesData(), base.isVoteActive());
            }
        } else {
            Database.getInstance();
        }
    }
    //Производит всю необходимую инициализацию и запускает сервер.
    //   savedDataFileName - имя файла, в котором было сохранено состояние сервера.  Если savedDataFileName == null, восстановление состояния не производится, сервер стартует “с нуля”.

    public static void stopServer(String saveDataFileName) throws IOException {
        Gson gson = new Gson();
        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter(saveDataFileName + ".txt", false))) {
            gson.toJson(Database.getInstance(), bw);
            // bw.close();
        }
    }
    //  Останавливает сервер и записывает все его содержимое в файл сохранения с именем savedDataFileName. Если savedDataFileName == null, запись содержимого не производится.

    public static String registerVoter(String requestJsonString) throws ElectException{
        return RegVoterService.registerVoter(requestJsonString);
    }

    public static String login(String requestJsonString) throws ElectException {
        return RegVoterService.login(requestJsonString);
    }

    public static String logout(String requestJsonString) {
        return RegVoterService.logout(requestJsonString);
    }

    public static String inviteCand(String requestJsonString) throws ElectException {
        return RegVoterService.inviteCand(requestJsonString);
    }

    public static String becomeCandidate(String requestJsonString) {
        return VoterService.becomeCandidate(requestJsonString);
    }

    public static String stopCandidate(String requestJsonString) {
        return VoterService.stopCandidate(requestJsonString);
    }

    public static String getAllProposals(String requestJsonString) {
        return VoterService.getAllProposals(requestJsonString);
    }

    public static String getAllCands(String requestJsonString) {
        return VoterService.getAllCands(requestJsonString);
    }

    public static String getVotersProposals(String requestJsonString) {
        return getVotersProposals(requestJsonString);
    }

    public static String addProposal(String requestJsonString) {
        return ProposaleService.addProposal(requestJsonString);
    }

    public static String rankProposal(String requestJsonString) {
        return ProposaleService.addProposal(requestJsonString);
    }

    public static String addProgram(String requestJsonString) throws ElectException {
        return CandidateService.addProgram(requestJsonString);
    }

    public static String deleteProgram(String requestJsonString) {
        return CandidateService.deleteProgram(requestJsonString);
    }

    public static String startElections(String requestJsonString) {
        return ElectionService.startElections(requestJsonString);
    }

    public static String stopElections(String requestJsonString){
        return ElectionService.stopElections(requestJsonString);
    }

    public static String vote(String requestJsonString){
        return VoterService.vote(requestJsonString);
    }
}
