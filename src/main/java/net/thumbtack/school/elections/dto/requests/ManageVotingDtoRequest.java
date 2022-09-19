package net.thumbtack.school.elections.dto.requests;



/**
 * текст - ключ для администрирования
 */


public class ManageVotingDtoRequest {
    private String text;

    public ManageVotingDtoRequest(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
