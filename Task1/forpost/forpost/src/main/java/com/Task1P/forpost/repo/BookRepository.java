package com.Task1P.forpost.repo;

import com.Task1P.forpost.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book,Long> {
}
