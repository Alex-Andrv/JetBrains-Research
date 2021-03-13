package ru.itmo.wp.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EditBookForm {

    @NotNull
    private Long cipher;

    public Long getCipher() {
        return cipher;
    }

    public void setCipher(Long cipher) {
        this.cipher = cipher;
    }
}
