package net.thumbtack.school.elections.dto.response;

public class AddProgramDtoResult {
    public String text;

    public AddProgramDtoResult(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
