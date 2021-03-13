package ru.itmo.wp.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** @noinspection unused*/
@Entity
@Table
public class Book {
    @Id
    @NotNull
    private long cipher;

    @ManyToOne(fetch=FetchType.EAGER, optional = true)
    private User user;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 60)
    private String name;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 60)
    private String author;

    @CreationTimestamp
    private Date creationTime;

    public long getCipher() {
        return cipher;
    }

    public void setCipher(long cipher) {
        this.cipher = cipher;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
