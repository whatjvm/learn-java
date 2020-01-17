package com.diamondfsd.learnjava.service;

import com.diamondfsd.learnjava.dao.BookDAO;
import com.diamondfsd.learnjava.dao.MemoryBookDAOImpl;
import com.diamondfsd.learnjava.entity.Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class BookService {
    private BookDAO bookDAO = new MemoryBookDAOImpl();

    private static final byte BOOK_TIME_SORT_CONSOL = 1;
    private static final byte BOOK_PRICE_SORT_CONSOL = 2;
    private static final byte BOOK_NAME_SORT_CONSOL = 3;

    // 创建图书的时候调用
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
        return this.bubbleSort(allBook(),BOOK_NAME_SORT_CONSOL);
    }

    public List<Book> listBookSortByPrice(){
        return this.bubbleSort(allBook(),BOOK_PRICE_SORT_CONSOL);
    }

    public List<Book> listBookSortByTime(){
        return this.bubbleSort(allBook(),BOOK_TIME_SORT_CONSOL);
    }

    public void inIDofDeledBook(Integer id){
            bookDAO.inIDofBookDeled(id);
    }

    public void inIDofEditBook(Integer id , Book book){
            bookDAO.inIDofBookEdit(id,book);
    }

    /**
     * 保存数据到文本
     */
    public void storeDataOutFile(){
        if(bookDAO.allBook().size() != 0){
            bookDAO.outputFile();
        }
    }

    /**
     * 载入数据
     */
    public void inputFileData(){
            bookDAO.inputFile();
    }

    public Boolean bookIDisTrue(Integer id){
        if(id.compareTo(bookDAO.getById(id).getId()) == 0){
            return true;
        }
        return false;
    }

    private List<Book> bubbleSort(List<Book> books,byte consol){
        for(int i = 0;i < books.size();i++){
            for(int j = 0;j < books.size();j++){
                if(decideConsol(books.get(i) , books.get(j) , consol)){
                    Book copyBook = books.set(i,books.get(i));
                    books.set(i,books.get(j));
                    books.set(j,copyBook);
                }
            }
        }
        return books;
    }

    /**
     * @param bookOne 冒泡排序的第一个book
     * @param bookNext冒泡排序的第二个book
     * @param consolByte 操作该进入哪个case进行转换的字符串。
     * @return 返回 bookOne 与 bookNext 的对比结果.
     */
    private Boolean decideConsol(Book bookOne , Book bookNext , byte consolByte){
        double bookOneValue = 0;
        double bookNextValue = 0;
        switch(consolByte){
            case 1:
                bookOneValue = bookOne.getCreateTime().getTime();
                bookNextValue = bookNext.getCreateTime().getTime();
                break;
            case 2:
                bookOneValue = bookOne.getPrice();
                bookNextValue =bookNext.getPrice();
                break;
            case 3:
                char oneCahr = Character.toLowerCase(bookOne.getBookName().charAt(0));
                char nextChar = Character.toLowerCase(bookNext.getBookName().charAt(0));
                bookOneValue = Double.valueOf(oneCahr);
                bookNextValue = Double.valueOf(nextChar);
                break;
            default:
        }
        if(bookOneValue > bookNextValue){
            return true;
        }
        return false;

    }

}






