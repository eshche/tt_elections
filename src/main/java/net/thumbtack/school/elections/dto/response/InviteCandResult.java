package net.thumbtack.school.elections.dto.response;

import net.thumbtack.school.elections.model.Voter;

public class InviteCandResult {
    private Voter myCand;

    public InviteCandResult(Voter myCand) {
        this.myCand = myCand;
    }

    public Voter getMyCand() {
        return myCand;
    }

    public void setMyCand(Voter myCand) {
        this.myCand = myCand;
    }
}
