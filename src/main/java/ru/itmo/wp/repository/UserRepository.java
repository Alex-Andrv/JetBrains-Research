package ru.itmo.wp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.wp.domain.Librarian;
import ru.itmo.wp.domain.User;

import java.util.List;

public interface UserRepository  extends JpaRepository<User, Long> {

    int countByFullName(String fullName);

    List<User> findAllByOrderByIdDesc();
}
