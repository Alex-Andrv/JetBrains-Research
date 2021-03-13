package ru.itmo.wp.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AddUserForm {
    @NotEmpty
    @Size(min = 2, max = 40)
    @Pattern(regexp = "^[A-Z][a-z]+", message = "Expected lowercase Latin letters starting with uppercase latter")
    private String name;


    @NotEmpty
    @Size(min = 2, max = 40)
    @Pattern(regexp = "^[A-Z][a-z]+", message = "Expected lowercase Latin letters starting with uppercase latter")
    private String surname;



    @NotEmpty
    @Size(min = 2, max = 40)
    @Pattern(regexp = "^[A-Z][a-z]+", message = "Expected lowercase Latin letters starting with uppercase latter")
    private String patronymic;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
}
