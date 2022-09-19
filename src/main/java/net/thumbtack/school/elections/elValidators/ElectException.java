package net.thumbtack.school.elections.elValidators;


import java.util.StringJoiner;

public class ElectException extends Exception {
    private String error;

    public ElectException(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

}

