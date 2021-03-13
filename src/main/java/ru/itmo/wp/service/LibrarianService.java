package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Librarian;
import ru.itmo.wp.form.LibrarianCredentials;
import ru.itmo.wp.repository.LibrarianRepository;

import java.util.List;

@Service
public class LibrarianService {
    private final LibrarianRepository librarianRepository;

    public LibrarianService(LibrarianRepository librarianRepository) {
        this.librarianRepository = librarianRepository;
    }

    public Librarian register(LibrarianCredentials librarianCredentials) {
        Librarian librarian = new Librarian();
        librarian.setLogin(librarianCredentials.getLogin());
        librarianRepository.save(librarian);
        librarianRepository.updatePasswordSha(librarian.getId(), librarianCredentials.getLogin(), librarianCredentials.getPassword());
        return librarian;
    }

    public boolean isLoginVacant(String login) {
        return librarianRepository.countByLogin(login) == 0;
    }

    public Librarian findByLoginAndPassword(String login, String password) {
        return login == null || password == null ? null : librarianRepository.findByLoginAndPassword(login, password);
    }

    public Librarian findById(Long id) {
        return id == null ? null : librarianRepository.findById(id).orElse(null);
    }

    public List<Librarian> findAll() {
        return librarianRepository.findAllByOrderByIdDesc();
    }

}
