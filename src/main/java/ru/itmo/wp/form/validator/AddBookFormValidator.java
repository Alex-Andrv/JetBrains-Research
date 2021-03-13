package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.form.AddBookForm;
import ru.itmo.wp.form.LibrarianCredentials;
import ru.itmo.wp.service.BookService;
import ru.itmo.wp.service.LibrarianService;

@Component
public class AddBookFormValidator implements Validator {
    private final BookService bookService;

    public AddBookFormValidator(BookService bookService) {
        this.bookService = bookService;
    }

    public boolean supports(Class<?> clazz) {
        return AddBookForm.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            AddBookForm addBookForm = (AddBookForm) target;
            if (bookService.findByCipher(addBookForm.getCipher()) != null) {
                errors.rejectValue("cipher", "cipher.invalid-cipher", "invalid cipher");
            }
        }
    }
}
