package net.thumbtack.school.elections.dto.response;


import java.util.HashMap;


public class GetAllCandidatesResult {
    private HashMap<String, String> candsWithProgram;

    public GetAllCandidatesResult(HashMap<String, String> candsWithProgram) {
        this.candsWithProgram = candsWithProgram;
    }

    public HashMap<String, String> getCandsWithProgram() {
        return candsWithProgram;
    }

    public void setCandsWithProgram(HashMap<String, String> candsWithProgram) {
        this.candsWithProgram = candsWithProgram;
    }
}
