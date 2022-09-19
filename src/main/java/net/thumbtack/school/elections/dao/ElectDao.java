package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.database.Database;

public interface ElectDao {
    void setVoteActive(boolean active);

    boolean isVoteActive();
}
