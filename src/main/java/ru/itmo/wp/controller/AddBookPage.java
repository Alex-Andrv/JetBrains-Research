package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Book;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.AddBookForm;
import ru.itmo.wp.form.AddUserForm;
import ru.itmo.wp.form.validator.AddBookFormValidator;
import ru.itmo.wp.service.BookService;
import ru.itmo.wp.service.LibrarianService;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AddBookPage extends Page {
    private final BookService bookService;
    private final AddBookFormValidator addBookFormValidator;

    public AddBookPage(BookService bookService, AddBookFormValidator addBookFormValidator) {
        this.bookService = bookService;
        this.addBookFormValidator = addBookFormValidator;
    }

    @InitBinder("addBookForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(addBookFormValidator);
    }

    @GetMapping("/addBook")
    public String addBookGet(Model model) {
        model.addAttribute("addBookForm", new AddBookForm());
        return "AddBookPage";
    }

    @PostMapping("/addBook")
    public String addBookPost(@Valid @ModelAttribute("addBookForm") AddBookForm addBookForm,
                              BindingResult bindingResult,
                              HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "AddBookPage";
        }
        Book book = new Book();
        book.setAuthor(addBookForm.getAuthor());
        book.setCipher(addBookForm.getCipher());
        book.setName(addBookForm.getName());
        bookService.addBook(book);
        putMessage(httpSession, "You add new book with cipher "  + book.getCipher());

        return "redirect:/";
    }
}
