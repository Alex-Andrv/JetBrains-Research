package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Book;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.repository.BookRepository;
import ru.itmo.wp.repository.UserRepository;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        bookRepository.save(book);
        return book;
    }

    public void updataCipher(long cipher, long oldCipher) {
        bookRepository.updateBookCipher(cipher, oldCipher);
    }



    public void delete(Book book) {
        bookRepository.delete(book);
    }

    public boolean isLoginVacant(Long cipher) {
        return bookRepository.countByCipher(cipher) == 0;
    }

    public Book findByCipher(Long cipher) {
        return cipher == null ? null : bookRepository.findByCipher(cipher).orElse(null);
    }

    public List<Book> findAll() {
        return bookRepository.findAllByOrderByCipherDesc();
    }
}
