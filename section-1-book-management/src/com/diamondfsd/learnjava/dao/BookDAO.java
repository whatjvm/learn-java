package com.diamondfsd.learnjava.dao;

import com.diamondfsd.learnjava.entity.Book;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author Diamond
 */
public interface BookDAO {
    void insert(Book book);

    Book getById(Integer id);

    List<Book> allBook();

    Book BookDelete(Integer id);

    Book BookEdit(Integer id , Book book);

}
