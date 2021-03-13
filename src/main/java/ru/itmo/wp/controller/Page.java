package ru.itmo.wp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.itmo.wp.domain.Book;
import ru.itmo.wp.domain.Librarian;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.service.BookService;
import ru.itmo.wp.service.LibrarianService;

import javax.servlet.http.HttpSession;

public class Page {
    private static final String USER_ID_SESSION_KEY = "userId";
    private static final String MESSAGE_SESSION_KEY = "message";
    private static final String BOOK_SESSION_KEY = "book";

    @Autowired
    private LibrarianService librarianService;
    @Autowired
    private BookService bookService;

    @ModelAttribute("user")
    public Librarian getLibrarian(HttpSession httpSession) {
        return librarianService.findById((Long) httpSession.getAttribute(USER_ID_SESSION_KEY));
    }

    @ModelAttribute("book")
    public Book getBook(HttpSession httpSession) {
        return bookService.findByCipher((Long) httpSession.getAttribute(BOOK_SESSION_KEY));
    }

    @ModelAttribute("message")
    public String getMessage(HttpSession httpSession) {
        String message = (String) httpSession.getAttribute(MESSAGE_SESSION_KEY);
        httpSession.removeAttribute(MESSAGE_SESSION_KEY);
        return message;
    }

    void setLibrarian(HttpSession httpSession, Librarian librarian) {
        if (librarian != null) {
            httpSession.setAttribute(USER_ID_SESSION_KEY, librarian.getId());
        } else {
            unsetLibrarian(httpSession);
        }
    }

    void unsetLibrarian(HttpSession httpSession) {
        httpSession.removeAttribute(USER_ID_SESSION_KEY);
    }

    void setBook(HttpSession httpSession, Book book) {
        if (book != null) {
            httpSession.setAttribute(BOOK_SESSION_KEY, book.getCipher());
        } else {
            unsetBook(httpSession);
        }
    }

    void unsetBook(HttpSession httpSession) {
        httpSession.removeAttribute(BOOK_SESSION_KEY);
    }

    public void putMessage(HttpSession httpSession, String message) {
        httpSession.setAttribute(MESSAGE_SESSION_KEY, message);
    }
}
