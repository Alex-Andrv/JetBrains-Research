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

@RestController
public class EditBookPageJson extends Page {
    private final BookService bookService;
    private final UserService userService;
    private final EditBookFormValidator editBookFormValidator;


    public EditBookPageJson(BookService bookService, UserService userService, EditBookFormValidator editBookFormValidator) {
        this.bookService = bookService;
        this.userService = userService;
        this.editBookFormValidator = editBookFormValidator;
    }

    @PostMapping("/editBook/deleteBook")
    public Map deleteBook(HttpSession httpSession, Model model) {
        Book book = (Book)model.getAttribute("book");
        if (book != null)
            bookService.delete(book);
        unsetBook(httpSession);
        putMessage(httpSession, "The book delete");
        return Map.of("status","302", "location", "/");
    }

    @PostMapping("/editBook/pickUpBook")
    public Map pickUpBook(HttpSession httpSession, Model model) {
        Book book = (Book)model.getAttribute("book");
        if (book != null) {
            book.setUser(null);
            bookService.addBook(book);
        }
        unsetBook(httpSession);
        putMessage(httpSession, "The book is free");
        return Map.of("status","302", "location", "/");
    }

    @PostMapping("/editBook/editCipher")
    public Map editBook(@RequestParam Long cipher, HttpSession httpSession, Model model) {
        Book book = (Book)model.getAttribute("book");
        if (book != null) {
            if (bookService.findByCipher(cipher) == null) {
                bookService.updataCipher(cipher, book.getCipher());
                putMessage(httpSession, "The book with new cipher");
            }
            else
                putMessage(httpSession, "The book with this cipher is exists");
        }
        unsetBook(httpSession);
        return Map.of("status","302", "location", "/");
    }


    @PostMapping("/editBook/giveOutBook")
    public Map giveOutBook(@RequestParam Long userId, Long cipher, HttpSession httpSession, Model model) {
        Book book = (Book)model.getAttribute("book");
        if (book != null) {
            book.setUser(userService.findById(userId));
            bookService.addBook(book);
        }
        putMessage(httpSession, "The book is not free");
        unsetBook(httpSession);
        return Map.of("status","302", "location", "/");
    }
}
