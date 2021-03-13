package ru.itmo.wp.form;

import ru.itmo.wp.domain.User;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AddBookForm {
    @NotEmpty
    @Size(min = 2, max = 40)
    @Pattern(regexp = "^[A-Za-z ]+", message = "Expected lowercase Latin letters starting with uppercase latter")
    private String name;


    @NotEmpty
    @Size(min = 2, max = 40)
    @Pattern(regexp = "^[A-Z][a-z]+", message = "Expected lowercase Latin letters starting with uppercase latter")
    private String author;



    @NotNull
    private Long cipher;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getCipher() {
        return cipher;
    }

    public void setCipher(Long cipher) {
        this.cipher = cipher;
    }
}
