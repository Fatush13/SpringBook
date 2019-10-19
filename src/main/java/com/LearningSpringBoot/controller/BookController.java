package com.LearningSpringBoot.controller;

import com.LearningSpringBoot.exception.BookIdMismatchException;
import com.LearningSpringBoot.exception.BookNotFoundException;
import com.LearningSpringBoot.models.Book;
import com.LearningSpringBoot.repos.BookRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookRepo bookRepo;

    @GetMapping
    public Iterable findAll() {
        return bookRepo.findAll();
    }

    @GetMapping("/title/{bookTitle}")
    public List findByTitle(@PathVariable String bookTitle) {
        return bookRepo.findByTitle(bookTitle);
    }

    @GetMapping("/{id}")
    public Book findOne(@PathVariable long id) {
        return bookRepo.findById(id)
                .orElseThrow(BookNotFoundException::new);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book Create(@RequestBody Book book) {
        return bookRepo.save(book);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        bookRepo.findById(id)
                .orElseThrow(BookNotFoundException::new);
        bookRepo.deleteById(id);
    }

    @PutMapping("{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
        if (!book.getId().equals(id)) {
            throw new BookIdMismatchException();
        }
        bookRepo.findById(id)
                .orElseThrow(BookNotFoundException::new);
        return bookRepo.save(book);
    }
}
