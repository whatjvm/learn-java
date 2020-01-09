package com.diamondfsd.learnjava.dao;

import com.diamondfsd.learnjava.entity.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryBookDAOImpl implements BookDAO {
    Map<Integer, Book> bookStore = new HashMap<>();

    @Override
    public void insert(Book book) {
        book.setId(bookStore.size() + 1);
        bookStore.put(book.getId(), book);
    }

    @Override
    public Book getById(Integer id) {
        return bookStore.get(id);
    }

    @Override
    public List<Book> allBook() {
        return new ArrayList<>(bookStore.values());
    }
}
