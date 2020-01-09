package com.diamondfsd.learnjava.dao;

import com.diamondfsd.learnjava.entity.Book;

import java.util.List;

/**
 * @author Diamond
 */
public interface BookDAO {
    void insert(Book book);

    Book getById(Integer id);

    List<Book> allBook();
}
