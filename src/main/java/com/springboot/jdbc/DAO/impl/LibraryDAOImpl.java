package com.springboot.jdbc.DAO.impl;

import com.springboot.jdbc.DAO.LibraryDao;
import com.springboot.jdbc.Entity.Library;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@PropertySource("classpath:dbqueries.properties")
@Slf4j
public class LibraryDAOImpl implements LibraryDao {

    private final JdbcTemplate jdbcTemplate;

    @Value("${db.query.getAllBooks}")
    private String getAllBooksQuery;

    @Value("${db.query.getBookById}")
    private String getBookByIdQuery;

    @Value("${db.query.insertBook}")
    private String insertBookQuery;

    @Value("${db.query.updateBook}")
    private String updateBookQuery;

    @Value("${db.query.deleteBook}")
    private String deleteBookQuery;

    public LibraryDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Library> getAllBooks() {
        log.info("Fetching all books from the database...");
        List<Library> books = jdbcTemplate.query(getAllBooksQuery, new LibraryRowMapper());
        log.debug("Books fetched: {}", books);
        return books;
    }

    @Override
    public Library getBookById(int bookId) {
        log.info("Fetching book with ID: {}", bookId);
        Library book = jdbcTemplate.queryForObject(getBookByIdQuery, new LibraryRowMapper(), bookId);
        log.debug("Book details: {}", book);
        return book;
    }

    @Override
    public void insertBook(Library book) {
        log.info("Inserting book: {}", book);
        jdbcTemplate.update(insertBookQuery, book.getBookName(), book.getBookCategory());
        log.info("Book inserted successfully.");
    }

    @Override
    public void updateBook(Library book) {
        log.info("Updating book with ID: {}", book.getBookId());
        jdbcTemplate.update(updateBookQuery, book.getBookName(), book.getBookCategory(), book.getBookId());
        log.info("Book updated successfully.");
    }

    @Override
    public void deleteBook(int bookId) {
        log.warn("Deleting book with ID: {}", bookId);
        jdbcTemplate.update(deleteBookQuery, bookId);
        log.info("Book deleted successfully.");
    }

    private static class LibraryRowMapper implements RowMapper<Library> {
        @Override
        public Library mapRow(ResultSet rs, int rowNum) throws SQLException {
            Library book = new Library();
            book.setBookId(rs.getInt("bookId"));
            book.setBookName(rs.getString("Book_Name"));
            book.setBookCategory(rs.getString("Book_Category"));
            return book;
        }
    }
}
