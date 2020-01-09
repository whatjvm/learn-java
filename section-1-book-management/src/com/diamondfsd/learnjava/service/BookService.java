package com.diamondfsd.learnjava.service;

import com.diamondfsd.learnjava.dao.BookDAO;
import com.diamondfsd.learnjava.dao.MemoryBookDAOImpl;
import com.diamondfsd.learnjava.entity.Book;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class BookService {
    private BookDAO bookDAO = new MemoryBookDAOImpl();

    public void saveBook(Book book) {
        book.setCreateTime(new Date());
        bookDAO.insert(book);
    }

    public List<Book> allBook() {
        return bookDAO.allBook();
    }

    public Book getById(Integer id) {
        return bookDAO.getById(id);
    }

    public List<Book> listBookSortByName() {
        List<Book> books = allBook();
        // TODO 按照名称排序
        return books;
    }
}
