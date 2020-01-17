package com.diamondfsd.learnjava.dao;

import com.diamondfsd.learnjava.entity.Book;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class MemoryTextDate {
    public static final String fileRoute = "D:/section-1-book-management/src/com/diamondfsd/learnjava/data";
    private static String sonRoute = "user01.txt";
    private static File file = new File(fileRoute,sonRoute);

    public Map<Integer , Book> getFileAllData() throws FileNotFoundException , IOException {
        InputStream inputFile = null;
        inputFile = new BufferedInputStream(new FileInputStream(file));
        byte[] fileByteStream = new byte[(int)file.length()];
        Map<Integer , Book> map = new HashMap<>();
        if(fileByteStream.length != 0){
            int len = 0;
            while (-1 != (len = inputFile.read(fileByteStream))){
                String inof = new String(fileByteStream,"GBK");
                String[] bigArray = inof.split("/");
                for(int i = 1;i <= bigArray.length;i++){
                    if((i % 2) != 0 ){
                        String[] towArrya = bigArray[i].split(",");
                        Book book = new Book();
                        book.setBookName(towArrya[0]);
                        book.setAuthor(towArrya[1]);
                        book.setPrice(Double.parseDouble(towArrya[2]));
                        try{
                            SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.US);
                            book.setCreateTime(sdf.parse(towArrya[3]));
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        book.setId(Integer.parseInt(bigArray[i-1].toString()));
                        map.put(Integer.parseInt(bigArray[i-1].toString()) , book);
                    }
                }
            }
        }
        if(inputFile != null){
            inputFile.close();
        }
        return map;
    }

    public void outputFileAllData(Map<Integer, Book> bookStore) throws FileNotFoundException , IOException{
        OutputStream outputFile = null;
        outputFile = new BufferedOutputStream(new FileOutputStream(file));

            for(Integer keyNumber :  bookStore.keySet()){
                if(bookStore.get(keyNumber) != null){
                    StringBuffer bookDataText = new StringBuffer();
                    bookDataText.append(bookStore.get(keyNumber).getId()).append("/")
                            .append(bookStore.get(keyNumber).getBookName()).append(",")
                            .append(bookStore.get(keyNumber).getAuthor()).append(",")
                            .append(bookStore.get(keyNumber).getPrice()).append(",")
                            .append(bookStore.get(keyNumber).getCreateTime()).append(",")
                            .append(bookStore.get(keyNumber).getId()).append("/");
                    String string = bookDataText.toString();
                    outputFile.write(string.getBytes());
                }
            }
        outputFile.flush();
        if(outputFile != null){
            outputFile.close();
        }
    }
}
