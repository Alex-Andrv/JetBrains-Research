package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.Book;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.AddUserForm;
import ru.itmo.wp.form.EditBookForm;
import ru.itmo.wp.form.validator.EditBookFormValidator;
import ru.itmo.wp.service.BookService;
import ru.itmo.wp.service.LibrarianService;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@Controller
public class EditBookPage extends Page {
    private final BookService bookService;
    private final UserService userService;
    private final EditBookFormValidator editBookFormValidator;


    public EditBookPage(BookService bookService, UserService userService, EditBookFormValidator editBookFormValidator) {
        this.bookService = bookService;
        this.userService = userService;
        this.editBookFormValidator = editBookFormValidator;
    }

    @InitBinder("editBookForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(editBookFormValidator);
    }

    @GetMapping("/editBook")
    public String editBookGet(HttpSession httpSession, Model model) {
        model.addAttribute("editBookForm", new EditBookForm());
        unsetBook(httpSession);
        model.addAttribute("book", getBook(httpSession));
        return "EditBookPage";
    }

    @PostMapping("/editBook")
    public String editBookPost(@Valid @ModelAttribute("editBookForm") EditBookForm editBookForm,
                              BindingResult bindingResult,
                              HttpSession httpSession, Model model) {
        if (bindingResult.hasErrors()) {
            return "EditBookPage";
        }
        Book book = bookService.findByCipher(editBookForm.getCipher());
        setBook(httpSession, book);
        model.addAttribute("book", book);
        return "EditBookPage";
    }

}
