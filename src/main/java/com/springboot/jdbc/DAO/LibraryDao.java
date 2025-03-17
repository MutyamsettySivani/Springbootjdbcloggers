package com.springboot.jdbc.DAO;

import java.util.List;
import com.springboot.jdbc.Entity.Library;

public interface LibraryDao {
    List<Library> getAllBooks();
    Library getBookById(int bookId);
    void insertBook(Library book);
    void updateBook(Library book);
    void deleteBook(int bookId);
}
