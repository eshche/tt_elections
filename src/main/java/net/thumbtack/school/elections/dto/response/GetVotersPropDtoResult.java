package net.thumbtack.school.elections.dto.response;

import org.apache.commons.collections4.map.MultiValueMap;

import java.util.HashMap;
import java.util.HashSet;

public class GetVotersPropDtoResult {
    private HashMap <String, String> votersProp;

    public GetVotersPropDtoResult(HashMap<String, String> votersProp) {
        this.votersProp = votersProp;
    }

    public HashMap<String, String> getVotersProp() {
        return votersProp;
    }

    public void setVotersProp(HashMap<String, String> votersProp) {
        this.votersProp = votersProp;
    }
}
