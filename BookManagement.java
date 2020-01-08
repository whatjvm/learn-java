//import com.sun.java.util.jar.pack.Instruction;

import java.awt.*;
import java.awt.image.SinglePixelPackedSampleModel;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.SimpleFormatter;

public class BookManagement {
    String[] content ={"书名","作者","价格","ID","时间"};
    Scanner sca = new Scanner(System.in);
    ArrayList<String[]> bookdata = new ArrayList<>();
    String[] book_byte_arry = {"字母","数字","文字","符号"};
    //    StringBuffer BookSB = new StringBuffer();
    int conlso;//1代表主菜单，2代表次级菜单

    public BookManagement(){
        conlso = 1;
        bookdata.add(new String[]{"吴","杰mr","32","20202497","2020/01/10"});
        bookdata.add(new String[]{"是心动啊","老大","300","20202787","2011/11/18"});
        bookdata.add(new String[]{"是ma??","梓杰","556","20202666","2002/08/24"});
        bookdata.add(new String[]{"b动啊","高菲","495","20202621","1998/07/11"});
        bookdata.add(new String[]{"我是吴","梓杰","332","20202888","2017/07/11"});
        bookdata.add(new String[]{"[bbb]","菲","495","20202571","2015/09/20"});
        bookdata.add(new String[]{"AA","哈大哥","128","20202984","2009/09/05"});
        bookdata.add(new String[]{"3Dtouch","老李","64","20202167","2015/05/18"});
        bookdata.add(new String[]{"M&H","张三","105","20202998","2020/12/21"});
        bookdata.add(new String[]{"3012世界末日","李四","800","20202544","2020/01/08"});
        bookdata.add(new String[]{"ABC儿童图书","王五","170","20202673","2011/12/21"});
        bookdata.add(new String[]{"上海故事","周杰伦","102.5","20202337","2024/06/07"});
        bookdata.add(new String[]{"###！","杰伦","103","20202237","2019/05/09"});
        System_Book(conlso);
    }

    /*
    操作系统
     */
    private void System_Book(int conlso){
        String str = " ";//字符串控指令

        do {
            switch (conlso){
                case 10:
                    System.out.println("请正确输入！");
                    conlso =1;//默认载入主菜单
                    break;
                case 1:
                    System.out.println("******v0.0.1*********\n*输入对应文字选择菜单*\n*********************\n（1）创建图书\n（2）查看图书列表\n（3）退出");
                    break;
                case 5:
                    System.out.println("（1）时间排序\n（2）价格排序\n（3）书名排序\n（4）编辑图书\n（5）删除图书\n（6）查看数据库\n（7）返回主菜单");
                    break;
            };

            System.out.print("user:");
            str = sca.next();//读取输入后判断

            if(conlso==1){//主菜单
                if(str.equals("创建图书")){
                    System_Book(this.CreateBooks(str));//CreateBooks返回1，重载"主菜单"
                }else if(str.equals("查看图书列表")){
                    System_Book(this.LookBooksList(str));//LookBooksList返回2，System_Book收到2后打印2的菜单，也进入了2的判断
                }else if(str.equals("退出")==false){
                    conlso = 10;
                }else if(str.equals("退出")){//主菜单退出系统
                    System.out.println("system:EXIT");
                    break;
                }
            }

            if(conlso==5){//5是图书二级菜单
                System_Book(LookBooksList(str));//返回的数字可直接控制
            }

        }while (true);

    }

    /*
     查看图书列表
     s是car输入的操作，TowConlso是操作执行后的反馈，最后传到一级菜单的conlso。
     datacon是二级菜单的数据临时内存。
     TowConlso二级菜单控制,控制底层冒泡排序 0代表书名
  */
    public int LookBooksList(String s){
        int TowConlso ;//二级控制 1是返回一级菜单 2是价格排序，0是书名排序，5是留着二级菜单
        if(s.equals("查看图书列表")){
            TowConlso = 5;
            return TowConlso;//正式进入二级菜单
        }

        String[][] datacon = new String[bookdata.size()][5];
        System.out.println("你已输入："+s.toString()+"!");//进入提示，选择不同的操作重载不同的提示语
        for(int i=0;i<bookdata.size();i++) {//数据载入
            datacon[i] = bookdata.get(i);
        }
        if(s.equals("时间排序")){
            TowConlso = 4;
            this.LookDataPrint(datacon,TowConlso);
        }else if(s.equals("价格排序")){
            TowConlso = 2;
            this.LookPricePrint(datacon,TowConlso);
        }else if(s.equals("书名排序")){
            TowConlso = 0;
            this.BookNamePrint(datacon,TowConlso);
        }else if(s.equals("删除图书")){
            System.out.print("输入ID：");
            this.removeBook(datacon,sca.nextInt());
        }else if(s.equals("编辑图书")){
            System.out.print("输入ID：");
            beidBook(datacon,s);
        }else if(s.equals("返回主菜单")){
            return TowConlso = 1;
        }else if(s.equals("查看数据库")){
            LookPrint(datacon);
        }

        return TowConlso=5;
    }


    /*
        创建图书
        content
    */
    public int CreateBooks(String s){
        String[] CBContent = new String[5];//分批录入信息。

        bookdata.add(beid(CBContent,s));
        System.out.print(s.toString()+"成功：《"+CBContent[0]+"》\n输入\"继续\"或\"任意输入字符\"进行继续创建或返回主菜单！");
        if(Judge(sca.next())){
            CreateBooks("继续");
        }
        return 1;
    }

    /*
    编辑图书的地方
     */
    private String[] beid(String[] CBContent,String s){
        Calendar time =Calendar.getInstance();//时间
        int year = time.get(Calendar.YEAR);
        int month = time.get(Calendar.MONTH)+1;
        int day = time.get(Calendar.DATE);

        System.out.println("system:你选择了"+s.toString()+"。\n请对应输入:书名，作者，价格~");

        for(int i=0;i<CBContent.length;i++){
            if(i<3) {
                System.out.print(content[i] + ":");
                CBContent[i] = sca.next();//不能输入空格

                if(content[i].equals("价格")){//对比价格的valu是否为数字
                    try {
                        int price = Integer.parseInt(CBContent[i].toString());
                    }catch (NumberFormatException e) {
                        System.out.println("必须输入数字！");
                        i--;
                    }
                }

            }
            switch(i){
                case 3:CBContent[i]=year+""+RamdomID();break;
                case 4:
                    String Month = month+"";
                    String Day = day+"";
                    if(month<10){
                        Month = "0"+month;
                    }
                    if(day<10){
                        Day = "0"+day;
                }
                    CBContent[i]=year+"/"+Month+"/"+Day;
                    break;
            }
        }

        return CBContent;
    }


    /*
        询问是否继续查看或创建图书
     */
    private boolean Judge(String sca){
        if(sca.equals("继续")){
             return true;
        }
        return false;
    }

    /*
    打印
     */
    private void LookPrint(String[][] str){
        for(int i = 0;i<str.length;i++){
            System.out.printf("%02d%s",i+1,".");
            for(int j=0;j<str[i].length;j++){
                try {
                    System.out.printf("%s：%-15s",content[j].toString(),str[i][j].toString());
                }catch (Exception e){
                    System.out.print(content[j]+":"+"NULL");
                }
            }
            System.out.println(" ");
        }
        System.out.println("system：系统查找到有"+str.length+"条结果。");
    }

    /*
    根据Strlength来打印次数
    */
    private void LookPrint(String[][] str, int Strlength,int array_byte){
        if(Strlength>0){
            System.out.println("\nsystem：属于"+book_byte_arry[array_byte].toString()+"查找到有"+Strlength+"条结果。");
            array_byte++;
        }
        for(int i = 0;i<Strlength;i++){
            System.out.print(i+1+".");
            for(int j=0;j<str[i].length;j++){
                try {
                    System.out.printf("%s：%-10s",content[j].toString(),str[i][j].toString());
                }catch (Exception e){
//                    System.out.print("000");
                }
            }
            System.out.println(" ");
        }
    }

    /*
    价钱打印
     */
    private void LookPricePrint(String[][] str,int TowConlso){
        LookPrint(BubbleSort(str,TowConlso));
    }

    /*
    时间由大到小进行排序打印
     */
    private void LookDataPrint(String[][] str,int TowConlso){
        LookPrint(BubbleSort(str,TowConlso));
    }


    /*
    书名排序
     */
    private void BookNamePrint(String[][] str,int TowConlso){
        byte array_byte = 0;
        int size = str.length/4;
        String [][] number_copy_array = new String[size][str[0].length];//新的数字排序拷贝
        String [][] letter_copy_array = new String[size][str[0].length];//新的字母排序拷贝
        String [][] symbol_copy_array = new String[size][str[0].length];//新的符号排序拷贝
        String [][] chinese_copy_array = new String[size][str[0].length];//新的中文排序拷贝
        int case_timer = 0;//同步字母排序的操作
        int number_timer = 0;//同步数字排序的操作
        int symbol_timer = 0;//同步符号排序的操作
        int chinese_timer = 0;//同步中文排序的操作
        String[] Surplus_book_name = new String[str.length];//记录剩下的字母名字

        for(int i=0;i<str.length;i++){
            char one_book_name = str[i][0].charAt(0);//获取第一个字母
            int vaule = Integer.valueOf(one_book_name);//获取第一个字母的ascii值

            //实现动态扩容
            if(case_timer>letter_copy_array.length-1||number_timer>number_copy_array.length-1||chinese_timer>chinese_copy_array.length-1||symbol_timer>symbol_copy_array.length-1){
                String[][] new_plus_array = new String[size*2][5];
//                System.out.println("开始的：字母容量="+letter_copy_array.length+"中文容量="+chinese_copy_array.length);
                if(case_timer>letter_copy_array.length-1){//给字母数组扩容
                    System.arraycopy(letter_copy_array,0,new_plus_array,0,letter_copy_array.length);//拷贝到新数组,size++代表size+1》》size*2》》新的只多一点新数组的下标
                    letter_copy_array = new_plus_array.clone();
//                    size = case_timer*2;
                    size ++;
//                    System.out.println("--字母计算器="+case_timer+"容量="+letter_copy_array.length+"size="+size);
                }else if(number_timer>number_copy_array.length-1){//数字数组扩容
                    System.arraycopy(number_copy_array,0,new_plus_array,0,number_copy_array.length);
                    number_copy_array = new_plus_array.clone();
                  size ++;
//                    size = number_timer*2;
//                    System.out.println("数字计算器="+number_timer+"容量="+number_copy_array.length+"size="+size);
                }else if(chinese_timer>chinese_copy_array.length-1){//中文数组扩容
                    System.arraycopy(chinese_copy_array,0,new_plus_array,0,chinese_copy_array.length);
                    chinese_copy_array = new_plus_array.clone();
                    size ++;
//                    size = chinese_timer*2;
//                    System.out.println("--中文计算器="+chinese_timer+"容量="+chinese_copy_array.length+"size="+size);
                }else if(symbol_timer>symbol_copy_array.length-1){//符号数组扩容
                    System.arraycopy(symbol_copy_array,0,new_plus_array,0,symbol_copy_array.length);
                    symbol_copy_array = new_plus_array.clone();
//                    size = symbol_timer*2;
                    size ++;
//                    System.out.println("符号计算器="+symbol_timer+"容量="+symbol_copy_array.length+"size="+size);
                }
//                System.out.println("后来的：字母容量="+letter_copy_array.length+"中文容量="+chinese_copy_array.length);
            }

            if(vaule>=65&&vaule<=90||vaule>=97&&vaule<=122){//判断是不是字母
                Surplus_book_name[case_timer] = str[i][0];//记录书名字母

                if(Character.isUpperCase(one_book_name)){//书名第一个字母如果是大写就转化为小写
                    char[] c = str[i][0].toCharArray();
                    c[0] = Character.toLowerCase(str[i][0].charAt(0));//把str[i][0]转为小写字母,赋给c[0]
                    str[i][0] = String.valueOf(c);//第一个字母是小写其余不变的书名，全部还给str[i][0]
                }

                letter_copy_array[case_timer] = str[i].clone();//字母全部是小写的书名赋给letter
                letter_copy_array = BubbleSort(letter_copy_array,TowConlso);//冒泡排序字母部分
                case_timer++;//字母计算器++
                for(int z=0;z<case_timer;z++){//还原对应书名大写首字母
                    for(int j=0;j<case_timer;j++){
                        if(letter_copy_array[z]!=null&&Surplus_book_name[j]!=null){
                            if(letter_copy_array[z][0].substring(1).equals(Surplus_book_name[j].substring(1))){
                                //System.out.println("letter_copy_array="+letter_copy_array[z][0].toString()+",name="+Surplus_book_name[j].toString());
                                char[] c = Surplus_book_name[j].toCharArray();
                                letter_copy_array[z][0] = String.copyValueOf(c);
                            }
                        }
                    }
                }

            }else if(Character.isDigit(one_book_name)){//判断是不是数字
                number_copy_array[number_timer] = str[i].clone();
                number_copy_array = BubbleSort(number_copy_array,TowConlso);
                number_timer++;
            }
            else if(vaule<48&&vaule>32||vaule<65&&vaule>57||vaule>90&&vaule<97||vaule>122&&vaule<127){//ascii表里避开大小字母后的大概区域，标识符号 65 90 97 126
                symbol_copy_array[symbol_timer] = str[i].clone();
                symbol_timer++;
            }else if(str!=null){
                chinese_copy_array[chinese_timer] = str[i].clone();//剩下的都归类中文
                chinese_copy_array = BubbleSort(chinese_copy_array,TowConlso);
                chinese_timer++;
            }

        }

            String[][][] copy = new String[4][str.length][5];//顺序输出
             for(int i=0;i<copy.length;i++){
                 int sum = case_timer;
                for(int j=0;j<sum;j++){
                    if(i==0){
                        copy[i][j] = letter_copy_array[j].clone();
                    }else if(i==1){
                        sum = number_timer;
                        copy[i][j] = number_copy_array[j].clone();
                    }else if(i==2){
                        sum = chinese_timer;
                        copy[i][j] = chinese_copy_array[j].clone();
                    }else if(i==3){
                        sum = symbol_timer;
                        copy[i][j] = symbol_copy_array[j].clone();
                    }
                }
                LookPrint(copy[i],sum,array_byte);//暂时使用顺序输出
            }
    }


    public void removeBook(String[][] s,int indexID){
        int a = IndexBookID(s,indexID);
        if(a!=-1){
            bookdata.remove(a);
            System.out.println("书名为：《"+s[a][0].toString()+"》 "+"作者："+s[a][1].toString()+",ID: "+indexID+" 已在数据库第"+(a+1)+"行删除。");
        }else{
            System.out.println("警告！该ID："+indexID+"不存数据库，请注意是否输入错误！");
        }

    }

    public void beidBook(String[][] datacon,String s){
        int a = IndexBookID(datacon,sca.nextInt());
        if(a!=-1){
            String[] CBContent = new String[5];//分批录入信息。
            String[] sa = bookdata.get(a);
            bookdata.set(a,beid(CBContent,s));//通过ID放回之前的地方
            System.out.println("system:《"+sa[0].toString()+"》已更变成《"+CBContent[0].toString()+"》,ID由："+sa[3].toString()+" 替换成:"+CBContent[3].toString()+"。\n编辑时间："+new Date().toString());
        }else{
            System.out.println("警告！该ID不存数据库，请注意是否输入错误！");
        }
    }

    /*
    ID必须是唯一的
    输入指定的数组和需要索引的ID
    通过遍历数组得到对应ID存在数组的位置
    1.只返回第一个匹配的ID
    2.如果没有找到则返回-1
     */
    private int IndexBookID(String[][] str,int indexID){
        for(int i=0;i<str.length;i++){
            int Number = Integer.parseInt(str[i][3].toString());
            if(Number == indexID){
                return i;
            }
        }
        return -1;
    }


      /*
        随机id
     */
    private int RamdomID(){
    int ram = (int)(Math.random()*(2200-3200)+3200);
    return ram;
    }

    /*
str1与str2用十进制数字或参照ascii表进行比较，str1大则返回true
正常执行a，b为double进行比较。
如果不是数字则调出value 进行 ASCII表的进行比较。
 */
    private boolean Contrase(String str1,String str2){
        if(str1!=null&&str2!=null){
            double a,b;
            try{
//                System.out.println("s1="+str1.length()+"s2="+str2.length());
                a = Double.parseDouble(str1);
                b = Double.parseDouble(str2);
            }catch (Exception e){
                if(str1.length()==10&&str2.length()==10){
                    String as = str1.substring(0,4)+str1.substring(5,7)+str1.substring(8);
                    String bs = str2.substring(0,4)+str1.substring(5,7)+str2.substring(8);
//                    System.out.println("s1="+as.toString()+"s2="+bs.toString());
                    a = Double.parseDouble(as);
                    b = Double.parseDouble(bs);
                }else{
//                    System.out.println("报错！");
                    a = Double.valueOf(str1.charAt(0));
                    b = Double.valueOf(str2.charAt(0));
                }
            }
            if(a>b){
                return true;
            }
        }
        return false;
    }

    /*
冒泡排序
str为空或长度小于-1则返回本身。
str >> datacon >> bookdata
TowConlso 是二级菜单的控制
*/
    private String[][] BubbleSort(String[][] str ,int TowConlso){
        if(str!=null&&str.length>-1){
            String[][] zz = new String[str.length][str[0].length];
            for(int i =0 ;i<str.length-1;i++){
                for(int j =0;j<str.length-1-i;j++){
                    if(Contrase(str[j][TowConlso],str[j+1][TowConlso])){
                        zz[j] = str[j];
                        str[j] = str[j+1];
                        str[j+1] = zz[j];
                    }
                }
            }
            return str;
        }
        return str;
    }
}












