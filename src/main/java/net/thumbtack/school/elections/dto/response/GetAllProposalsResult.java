package net.thumbtack.school.elections.dto.response;

import java.util.TreeMap;

public class GetAllProposalsResult {
   private TreeMap<Double, String> proposals;

    public GetAllProposalsResult(TreeMap<Double, String> proposals) {
        this.proposals = proposals;
    }

    public TreeMap<Double, String> getProposals() {
        return proposals;
    }

    public void setProposals(TreeMap<Double, String> proposals) {
        this.proposals = proposals;
    }
}
