package com.Task1.gateway.repo;

import com.Task1.gateway.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
