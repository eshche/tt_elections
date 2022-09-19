package net.thumbtack.school.elections.dto.response;

import net.thumbtack.school.elections.model.Proposal;

public class AddProposalDtoResult {
   private Proposal proposal;

    public AddProposalDtoResult(Proposal proposal) {
        this.proposal = proposal;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }
}
