package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.form.LibrarianCredentials;
import ru.itmo.wp.service.LibrarianService;

@Component
public class LibrarianCredentialsRegisterValidator implements Validator {
    private final LibrarianService librarianService;

    public LibrarianCredentialsRegisterValidator(LibrarianService librarianService) {
        this.librarianService = librarianService;
    }

    public boolean supports(Class<?> clazz) {
        return LibrarianCredentials.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            LibrarianCredentials registerForm = (LibrarianCredentials) target;
            if (!librarianService.isLoginVacant(registerForm.getLogin())) {
                errors.rejectValue("login", "login.is-in-use", "login is in use already");
            }
        }
    }
}
