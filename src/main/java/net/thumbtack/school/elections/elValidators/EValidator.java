package net.thumbtack.school.elections.elValidators;

import net.thumbtack.school.elections.dao.ElectDao;
import net.thumbtack.school.elections.dao.VoterDao;
import net.thumbtack.school.elections.daoimpl.ElectDaoImpl;
import net.thumbtack.school.elections.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.model.Voter;


public class EValidator {
    static VoterDao voterDao = new VoterDaoImpl();
    static ElectDao electDao = new ElectDaoImpl();

   public static void checkElectionsStarted() throws ElectException { //действия во время выборов
        if (!electDao.isVoteActive()) {
            throw new ElectException("You can't vote");
        }
    }

    public static void checkElectionsNotStarted() throws ElectException {//действия до выборов
        if (electDao.isVoteActive()) {
            throw new ElectException("Действие запрещено во время выборов");
        }
    }

    public static void checkToken(String token) throws ElectException {
    /*    if (!voterDao.getAllVoters().containsKey(token)) {
            throw new ElectException("Wrong token");
        }*/
    }

    public static void checkLogin(String login) throws ElectException {
      /*  if (!voterDao.getAllVoters().containsValue(new Voter(login, "Fulbgyu567f","ghv", "gvfhg", "fuygfvj","fyhfgy","gyu", "ghjhg"))) {
            throw new ElectException("Login is not avaible");
        }*/
    }

}
