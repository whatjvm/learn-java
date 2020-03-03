package com.diamondfsd.learnjava;

import com.diamondfsd.learnjava.controller.BookController;
import com.diamondfsd.learnjava.service.BookService;

public class BookAppMain {
    public static void main(String[] args) {
        BookService bookService = new BookService();
        BookController bookController = new BookController(bookService);
        bookController.switchToMainMenu();
    }
}
