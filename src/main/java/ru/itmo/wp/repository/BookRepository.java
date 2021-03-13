package ru.itmo.wp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.wp.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByOrderByCipherDesc();


    int countByCipher(Long Cipher);

    Optional<Book> findByCipher(Long cipher);

}
