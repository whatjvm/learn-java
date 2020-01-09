package com.diamondfsd.learnjava.controller;

import com.diamondfsd.learnjava.entity.Book;
import com.diamondfsd.learnjava.service.BookService;

import java.util.*;

public class BookController {
    public static final String MAIN_MENU_CREATE_BOOK = "1";
    public static final String MAIN_MENU_BOOK_QUERY = "2";
    public static final String MAIN_MENU_EXIT = "3";


    public static final String SECOND_MENU_TIME_SORT = "1";
    public static final String SECOND_MENU_PRICE_SORT = "2";
    public static final String SECOND_MENU_NAME_SORT = "3";
    public static final String SECOND_MENU_EDIT_BOOK = "4";
    public static final String SECOND_MENU_DELETE_BOOK = "5";
    public static final String SECOND_MENU_ALL_BOOK = "6";
    public static final String SECOND_MENU_RETURN_MAIN_MENU = "7";

    /**
     * 主菜单数据
     * 使用 `LinkedHashMap` 的原因是因为他是有序的，我们的菜单是需要有序的
     */
    static Map<String, String> MAIN_MENU_MAP = new LinkedHashMap<>();

    /**
     * 二级菜单
     */
    static Map<String, String> SECOND_MENU_MAP = new LinkedHashMap<>();

    static Map<String, Map<String, String>> MENU_GROUP_MAP = new HashMap<>();

    final static String GROUP_MAIN = "1";
    final static String GROUP_SECOND = "2";

    static String OUT_PUT_TEXT_MAIN = "输入菜单序号进行操作: ";
    static String OUT_PUT_TEXT_PROCESS_SUCCESS = "操作成功!";
    static String OUT_PUT_TEXT_NO_MENU_INDEX = "没有此菜单";

    static String OUT_PUT_TEXT_NOT_NUMBER = "请输入正确的数字: ";
    static String OUT_PUT_TEXT_BOOK_NAME = "书名: ";
    static String OUT_PUT_TEXT_BOOK_AUTHOR = "作者: ";
    static String OUT_PUT_TEXT_BOOK_PRICE = "价格: ";

    /**
     * 初始化数据
     */
    static {
        MAIN_MENU_MAP.put(MAIN_MENU_CREATE_BOOK, "创建图书");
        MAIN_MENU_MAP.put(MAIN_MENU_BOOK_QUERY, "图书查询");
        MAIN_MENU_MAP.put(MAIN_MENU_EXIT, "退出");

        SECOND_MENU_MAP.put(SECOND_MENU_TIME_SORT, "时间排序");
        SECOND_MENU_MAP.put(SECOND_MENU_PRICE_SORT, "价格排序");
        SECOND_MENU_MAP.put(SECOND_MENU_NAME_SORT, "名称排序");
        SECOND_MENU_MAP.put(SECOND_MENU_EDIT_BOOK, "编辑图书");
        SECOND_MENU_MAP.put(SECOND_MENU_DELETE_BOOK, "删除图书");
        SECOND_MENU_MAP.put(SECOND_MENU_ALL_BOOK, "全部图书");
        SECOND_MENU_MAP.put(SECOND_MENU_RETURN_MAIN_MENU, "返回主菜单");

        MENU_GROUP_MAP.put(GROUP_MAIN, MAIN_MENU_MAP);
        MENU_GROUP_MAP.put(GROUP_SECOND, SECOND_MENU_MAP);

    }

    private BookService bookService;

    public Map<String, String> mainMenu;

    /**
     * 当前菜单组
     */
    private String currentMenuGroup;

    private Scanner scanner = new Scanner(System.in);

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * 打印主菜单
     */
    public void switchToMainMenu() {
        this.currentMenuGroup = GROUP_MAIN;
        printMenuAndWaitInput();
    }

    /**
     * 处理菜单输入
     * @param selectMenuIndex
     */
    private void doMenuSelect(String selectMenuIndex) {
        switch (currentMenuGroup) {
            case GROUP_MAIN:
                mainMenuProcess(selectMenuIndex);
                break;
            case GROUP_SECOND:
                secondMenuProcess(selectMenuIndex);
                break;
            default:
                break;
        }
    }

    private void secondMenuProcess(String selectMenuIndex) {
        switch (selectMenuIndex) {
            case SECOND_MENU_TIME_SORT:
                // TODO 按照时间排序
                break;
            case SECOND_MENU_PRICE_SORT:
                // TODO 按照价格排序
                break;
            case SECOND_MENU_NAME_SORT:
                List<Book> bookSortByName = bookService.listBookSortByName();
                printBookAndWaitInput(bookSortByName);
                break;
            case SECOND_MENU_EDIT_BOOK:
                // TODO 编辑图书
                break;
            case SECOND_MENU_DELETE_BOOK:
                // TODO 删除图书
                break;
            case SECOND_MENU_ALL_BOOK:
                List<Book> books = bookService.allBook();
                printBookAndWaitInput(books);
                break;
            case SECOND_MENU_RETURN_MAIN_MENU:
                switchToMainMenu();
                break;
            default:
                noMenuIndex();
                break;
        }
    }

    private void printBookAndWaitInput(List<Book> books) {
        printBook(books);
        printMenuAndWaitInput();
    }

    private void printBook(List<Book> books) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Book book : books) {
            stringBuilder.append("书名: ").append(book.getBookName()).append("\t");
            stringBuilder.append("作者: ").append(book.getAuthor()).append("\t");
            stringBuilder.append("价格: ").append(book.getPrice()).append("\t");
            stringBuilder.append("\n");
        }
        printString(stringBuilder.toString());
    }

    private void noMenuIndex() {
        printString(OUT_PUT_TEXT_NO_MENU_INDEX);
        String menuIndex = printTextAndGetInput(OUT_PUT_TEXT_MAIN);
        mainMenuProcess(menuIndex);
    }

    /**
     * 主菜单逻辑
     * @param selectMenuIndex
     */
    private void mainMenuProcess(String selectMenuIndex) {
        switch (selectMenuIndex) {
            case MAIN_MENU_CREATE_BOOK:
                createBook();
                printMenuAndWaitInput();
                break;
            case MAIN_MENU_BOOK_QUERY:
                switchToSecondMenu();
                break;
            case MAIN_MENU_EXIT:
                System.exit(0);
                break;
            default:
                noMenuIndex();
                break;
        }
    }

    private void createBook() {
        String bookName = printTextAndGetInput(OUT_PUT_TEXT_BOOK_NAME);
        Double price = printTextAndGetInputDouble(OUT_PUT_TEXT_BOOK_PRICE);
        String author = printTextAndGetInput(OUT_PUT_TEXT_BOOK_AUTHOR);
        Book book = new Book();
        book.setBookName(bookName);
        book.setAuthor(author);
        book.setPrice(price);
        bookService.saveBook(book);
        printString(OUT_PUT_TEXT_PROCESS_SUCCESS);
    }

    private Double printTextAndGetInputDouble(String outPutTextBookPrice) {
        String result = printTextAndGetInput(outPutTextBookPrice);
        try {
            return Double.parseDouble(result);
        } catch (NumberFormatException e) {
            return this.printTextAndGetInputDouble(OUT_PUT_TEXT_NOT_NUMBER);
        }
    }

    private void switchToSecondMenu() {
        this.currentMenuGroup = GROUP_SECOND;
        printMenuAndWaitInput();
    }

    private void printMenuAndWaitInput() {
        printMenu(MENU_GROUP_MAP.get(this.currentMenuGroup));
        String selectMenuIndex =  printTextAndGetInput(OUT_PUT_TEXT_MAIN);
        doMenuSelect(selectMenuIndex);
    }

    private String printTextAndGetInput(String outPutTextMain) {
        System.out.print(outPutTextMain);
        return scanner.next();
    }

    private void printMenu(Map<String, String> menuMap) {
        menuMap.forEach((k, name) -> {
            printString("(" + k+") " + name);
        });
    }

    private void printString(String string) {
        System.out.println(string);
    }
}
