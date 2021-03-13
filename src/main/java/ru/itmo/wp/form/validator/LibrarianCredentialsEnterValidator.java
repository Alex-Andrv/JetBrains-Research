package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.form.LibrarianCredentials;
import ru.itmo.wp.service.LibrarianService;

@Component
public class LibrarianCredentialsEnterValidator implements Validator {
    private final LibrarianService librarianService;

    public LibrarianCredentialsEnterValidator(LibrarianService librarianService) {
        this.librarianService = librarianService;
    }

    public boolean supports(Class<?> clazz) {
        return LibrarianCredentials.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            LibrarianCredentials enterForm = (LibrarianCredentials) target;
            if (librarianService.findByLoginAndPassword(enterForm.getLogin(), enterForm.getPassword()) == null) {
                errors.rejectValue("password", "password.invalid-login-or-password", "invalid login or password");
            }
        }
    }
}
