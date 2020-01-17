package com.diamondfsd.learnjava;

import com.diamondfsd.learnjava.controller.BookController;
import com.diamondfsd.learnjava.entity.Book;
import com.diamondfsd.learnjava.service.BookService;
import com.sun.xml.internal.ws.util.ReadAllStream;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.channels.FileLockInterruptionException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class BookAppMain {
    public static void main(String[] args) throws FileLockInterruptionException , IOException {
        BookService bookService = new BookService();
        BookController bookController = new BookController(bookService);
        bookController.switchToMainMenu();
    }
}







