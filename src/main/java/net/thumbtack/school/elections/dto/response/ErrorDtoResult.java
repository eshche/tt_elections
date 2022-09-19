package net.thumbtack.school.elections.dto.response;

public class ErrorDtoResult {
    private String error;

    public ErrorDtoResult(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
