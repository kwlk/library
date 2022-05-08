package com.example.library.controllers;

import com.example.library.beans.Book;
import com.example.library.exceptions.DataException;
import com.example.library.requests.CreateBookRequest;
import com.example.library.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dashboard")
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Book> getAllBooks() {
        return dashboardService.getAllBooks();
    }

    @RequestMapping(value = "/{bookId}", method = RequestMethod.GET)
    public Book getBookById(@PathVariable Integer bookId) throws DataException {
        return dashboardService.getBookById(bookId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public List<Book> addNewBook(@RequestBody CreateBookRequest createBookRequest, BindingResult brs) throws DataException {
        Book book = new Book(createBookRequest.getTitle(), createBookRequest.getAuthor(),
                createBookRequest.getYear());
        return dashboardService.addNewBook(book);
    }

    @RequestMapping(value = "/{bookId}", method = RequestMethod.DELETE)
    public Book deleteBook(@PathVariable Integer bookId) throws Exception {
        return dashboardService.deleteBook(bookId);
    }
}
