package com.diamondfsd.learnjava.dao;

import com.diamondfsd.learnjava.entity.Book;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryBookDAOImpl implements BookDAO {
    Map<Integer, Book> bookStore = new HashMap<>();
    MemoryTextDate userData = new MemoryTextDate();

    @Override
    public void insert(Book book) {
        // book -> ID+1 ，对应ID和值 put进HashMap。
        book.setId(bookStore.size() + 1);
        bookStore.put(book.getId(), book);
    }

    @Override
    public Book getById(Integer id) {
        // 获取ID对应的值
        return bookStore.get(id);
    }

    @Override
    public List<Book> allBook() {
        // 获取图书全部值
        return new ArrayList<>(bookStore.values());
    }

    @Override
    public Book inIDofBookDeled(Integer id){
        return bookStore.remove(id);
    }

    @Override
    public Book inIDofBookEdit(Integer id , Book book){
        return bookStore.put(id,book);
    }

    @Override
    public void outputFile(){
        try{
            userData.outputFileAllData(bookStore);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void inputFile(){
        try{
            bookStore.putAll(userData.getFileAllData());
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
