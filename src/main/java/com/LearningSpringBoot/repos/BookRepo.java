package com.LearningSpringBoot.repos;

import com.LearningSpringBoot.models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepo extends CrudRepository<Book, Long> {
    List<Book> findByTitle(String title);
}
