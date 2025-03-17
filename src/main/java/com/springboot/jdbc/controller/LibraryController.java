package com.springboot.jdbc.controller;

import com.springboot.jdbc.DAO.LibraryDao;
import com.springboot.jdbc.Entity.Library;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
@Slf4j
public class LibraryController {

    @Autowired
    private LibraryDao libraryDao;

    @GetMapping("/books")
    public List<Library> getAllBooks() {
        log.info("Fetching all books...");
        return libraryDao.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Library getBookById(@PathVariable int id) {
        log.info("Fetching book with ID: {}", id);
        return libraryDao.getBookById(id);
    }

    @PostMapping("/books")
    public String insertBook(@RequestBody Library book) {
        log.info("Inserting book: {}", book);
        libraryDao.insertBook(book);
        return "Book inserted successfully!";
    }

    @PutMapping("/books")
    public String updateBook(@RequestBody Library book) {
        log.info("Updating book with ID: {}", book.getBookId());
        libraryDao.updateBook(book);
        return "Book updated successfully!";
    }

    @DeleteMapping("/books/{id}")
    public String deleteBook(@PathVariable int id) {
        log.warn("Deleting book with ID: {}", id);
        libraryDao.deleteBook(id);
        return "Book deleted successfully!";
    }
}
