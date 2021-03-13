package ru.itmo.wp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.wp.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByOrderByCipherDesc();


    int countByCipher(Long Cipher);

    Optional<Book> findByCipher(Long cipher);

    @Transactional
    @Modifying
    @Query(value = "UPDATE book SET cipher=?1 WHERE cipher=?2", nativeQuery = true)
    void updateBookCipher(Long cipher, Long oldCipher);

}
