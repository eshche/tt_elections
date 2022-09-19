package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.ElectDao;
import net.thumbtack.school.elections.database.Database;

public class ElectDaoImpl implements ElectDao {
    public void setVoteActive(boolean active) {
        Database.getInstance().setVoteActive(active);
    }

    @Override
    public boolean isVoteActive() {
        return Database.getInstance().isVoteActive();
    }
}
