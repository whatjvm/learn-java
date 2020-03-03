package com.diamondfsd.learnjava.dao;

import com.diamondfsd.learnjava.entity.Book;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

public class MemoryInFileBookDAOImpl implements BookDAO {

    private static final String FILE_NAME = "/user01.txt";
    private final File file ;
    private final BufferedReader bufferedReader;
    private final BufferedWriter bufferedWriterAppend;
    private final Map<Integer , Book> bookStore = new HashMap<>();

    public MemoryInFileBookDAOImpl() throws IOException {
        file = new File(this.getClass().getResource(FILE_NAME).getFile());
        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file) , StandardCharsets.UTF_8));
        bufferedWriterAppend = new BufferedWriter(new FileWriter(file ,true));
    }

    @Override
    public void insert(Book book) {
        putBookMap();
        book.setId(onlyID());
        bookStore.put(book.getId(), book);
        addCodeBook(bookStore.get(book.getId()));
    }

    @Override
    public Book getById(Integer id) {
        return bookStore.get(id);
    }

    @Override
    public List<Book> allBook() {
        return new ArrayList<>(putBookMap().values());
    }

    @Override
    public Book BookDelete(Integer id){
        Book b = bookStore.remove(id);
        reloadMap(bookStore);
        return b;
    }

    @Override
    public Book BookEdit(Integer id , Book book){
        Book b = bookStore.put(id , book);
        reloadMap(bookStore);
        return b;
    }

    /**
     * 把 Book 对象转成为文本形态
     * @param book 按照 ID.Name,Author,Price 的顺序转为文本
     * @return 返回 book 的文本形态
     */
    private String codeBook(Book book) throws IOException{
            StringBuilder bookLine = new StringBuilder();
            bookLine.append(book.getBookName()).append(",")
                    .append(book.getId()).append(",")
                    .append(book.getAuthor()).append(",")
                    .append(book.getPrice()).append(",")
                    .append(book.getCreateTime()).append("\r\n");
            return new String(bookLine.toString().getBytes(),"UTF-8");
    }


    /**
     * 把文本形态的数据还原成 Book 对象。
     * @param jsonBook Book 对象的文本，通过String.split(",") 方法将 Book 的属性切割为数组
     *                 新建一个 Book 对象，将值按 ID.Name,Author,Price 的顺序添加进 Book
     * @return  返回赋值后的 Book
     */
    private Book decodeBook(String jsonBook){
        String[] array = jsonBook.split(",");
        System.out.println(Arrays.toString(array));
        Book book = new Book();
        book.setBookName(array[0]);
        book.setId(Integer.parseInt(array[1]));
        book.setAuthor(array[2]);
        book.setPrice(Double.parseDouble(array[3]));
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.US);
            book.setCreateTime(sdf.parse(array[4]));
        }catch (Exception e){
            e.printStackTrace();
        }
        return book;
    }


    /**
     *  在此类中用于 insert(Book book) 在新增图书时调用该方法，用于追加录入数据。
     *  将 Book 使用 bufferedWriterAppend.append() 方法将已经通过codeBook() 转为文本的 book 追加存储。
     * @param book 行参 book 是录完数据的图书对象
     */
    private void addCodeBook(Book book){
        try {
            bufferedWriterAppend.append(codeBook(book));
            bufferedWriterAppend.flush();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 主要为 bookStore 载入历史数据
     * bufferedReader.readLine() 读取文本一行内容，赋值给 line ，当line为null时，while停止循环
     * 读取到的 line 通过 decodeBook() 方法转为 Book 对象，然后bookStore.put() 存入内存。
     * @return 返回已经读取完文本数据的 bookStore 对象。
     */
    private Map<Integer , Book> putBookMap(){
        String line = null;
        try {
            while ((line = bufferedReader.readLine()) != null){
                if (line.equals("") == false){
                    Book decode = decodeBook(line);
                    bookStore.put(decode.getId() , decode);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return bookStore;
    }


    /**
     * 用于 删除（BookDelete）和 修改（BookEdit）两个功能的写入文本操作。
     * bufferedWriter 是写入文本流，默认不可追加写入，可以覆盖原先存储的内容。
     * @param map 行参 map 在此类里主要接收成员变量 bookStore
     */
    private void reloadMap(Map<Integer , Book> map){
        try {
            BufferedWriter bufferedWriter = bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (Integer i: putBookMap().keySet()) {
                bufferedWriter.write(codeBook(putBookMap().get(i)));
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 返回一个不重复的 ID 值。
     * 遍历MAP，获取所有Key值，使用Arrays.sort()排序，返回最大的ID并加上1.
     * @return  1.如果一次都没有遍历则表示Map是空的，返回默认初始ID 1 。
     *          2.返回Map最大的Key值，并加 1 、
     */
    private int onlyID(){
        int sum = 0;
        int[] allKey = new int[bookStore.size()];
        for(Integer i : bookStore.keySet()){
            allKey[sum] = i;
            sum++;
        }
        if(sum == 0){
            return 1;
        }
        Arrays.sort(allKey);
        return allKey[sum-1] + 1;
    }

}





