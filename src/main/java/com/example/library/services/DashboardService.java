package com.example.library.services;

import com.example.library.beans.Book;
import com.example.library.exceptions.DataException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.library.beans.Constants.ERROR_BOOK_IN_DATABASE;
import static com.example.library.beans.Constants.ERROR_BOOK_NOT_IN_DATABASE;

@Service
public class DashboardService {
    private List<Book> books;

    DashboardService () {
        books = new ArrayList<>();
        for(int i = 0; i< 10; i++) books.add(new Book(i));
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Book getBookById(Integer bookId) throws DataException {
        Book book = new Book(bookId);
        book.setId(bookId);
        if (books.contains(book)) {
            int index = books.indexOf(book);
            return books.get(index);
        }
        throw new DataException(ERROR_BOOK_NOT_IN_DATABASE);
    }

    public List<Book> addNewBook(Book book) throws DataException {
        if (books.contains(book)) throw new DataException(ERROR_BOOK_IN_DATABASE);
        books.add(book);
        return getAllBooks();
    }

    public Book deleteBook(Integer bookId) throws DataException {
        Book book = new Book(bookId);
        book.setId(bookId);
        if (books.contains(book)) {
            int index = books.indexOf(book);
            Book deletedBook = books.get(index);
            return books.remove(index);
        }
        throw new DataException(ERROR_BOOK_NOT_IN_DATABASE);
    }

}
