package com.test.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    /**
     * 提取字符串中的数字
     * @param string
     * @return
     */
    public static String ExtractnNum(String string) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(string);
        return m.replaceAll("").trim();
    }

    /**
     * 重命名时间格式  如2021-09-02 04:46:30  ->  2021-09-02 04-46-30
     *
     * @param string
     * @return
     */
    public static String reName(String string) {
        return string.replaceAll(":", "-").trim();
    }

    /**
     * 剔除字符串数组中的空值
     * @param str
     * @return
     */
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
