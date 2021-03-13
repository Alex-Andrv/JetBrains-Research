package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.form.AddBookForm;
import ru.itmo.wp.form.EditBookForm;
import ru.itmo.wp.service.BookService;

@Component
public class EditBookFormValidator implements Validator {
    private final BookService bookService;

    public EditBookFormValidator(BookService bookService) {
        this.bookService = bookService;
    }

    public boolean supports(Class<?> clazz) {
        return EditBookForm.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            EditBookForm editBookForm = (EditBookForm) target;
            if (bookService.findByCipher(editBookForm.getCipher()) == null) {
                errors.rejectValue("cipher", "cipher.invalid-cipher", "invalid cipher");
            }
        }
    }
}
