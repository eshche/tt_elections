

package net.thumbtack.school.elections.model;

import javax.validation.constraints.*;


import java.io.Serializable;
import java.util.Objects;

public class Voter implements Serializable {
    @NotEmpty(message = "Введите логин")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9-_]{2,20}$", message = "буквы и цифры, первый символ обязательно буква, минимум 3 символа")
    private String login;

    @NotEmpty (message = "Введите пароль")
    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message = "Пароль - строчные и прописные латинские буквы, цифры, спецсимволы. Минимум 8 символов")
    private String password;

    @NotEmpty(message = "Введите фамилию")
    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z]+$", message = "Фамилия задается только буквами")
    private String lastName;

    @NotEmpty (message = "Укажите имя")
    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z]+$", message = "Имя задается только буквами")
    private String firstName;

    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z]+$", message = "Отчество задается только буквами")
    private String patronymic;

    @NotEmpty (message = "Укажите улицу")
    private String street;

    @NotEmpty (message = "Укажите дом")
    private String house;

    private String flat;

    private boolean isInvited;


    public Voter(String login, String password, String lastName, String firstName, String patronimyc, String street, String house, String flat) {
        this.login = login;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronimyc;
        this.street = street;
        this.house = house;
        this.flat = flat;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public boolean isInvited() {
        return isInvited;
    }

    public void setInvited() {
        isInvited = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voter voter = (Voter) o;
        return login.equals(voter.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}
