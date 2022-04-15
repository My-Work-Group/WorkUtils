import java.util.ArrayList;
import java.util.List;

public class TestString {
    public static void main(String[] args) {
        String name = ",1,2,,3,4";
        String newName[];
        newName  = name.split(",+");
        newName = replaceNull(newName);
        for(String str:newName){
            System.out.println(str);
        }

    }

//    public  static String[] demo(String[] aa){
//        String[] newStr;
//        List<String> tmp = new ArrayList<String>();
//        for (String str:aa) {
//            if (str!=null && str.length()!=0) {
//                tmp.add(str);
//            }
//        }
//        newStr = tmp.toArray(new String[0]);
//
//        return newStr;
//    }

    public static String[] replaceNull(String[] str){
        //用StringBuffer来存放数组中的非空元素，用“;”分隔
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<str.length; i++) {
            if("".equals(str[i])) {
                continue;
            }
            sb.append(str[i]);
            if(i != str.length - 1) {
                sb.append(";");
            }
        }
        //用String的split方法分割，得到数组
        str = sb.toString().split(";");
        return str;
    }
}
