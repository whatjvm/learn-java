package com.diamondfsd.learnjava;

import com.diamondfsd.learnjava.controller.BookController;
import com.diamondfsd.learnjava.service.BookService;

import java.io.IOException;

public class BookAppMain {
    public static void main(String[] args) throws IOException {
        BookService bookService = new BookService();
        BookController bookController = new BookController(bookService);
        bookController.switchToMainMenu();
    }
}
