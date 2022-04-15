package com.test.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 定义枚举类，七个检测站点
public enum EnumSiteCode {

    CODE_1(1, "G346 K194+400璜土滨江西路西向东"),
    CODE_2(2, "S232 K310+335利港省庄大道南向北"),
    CODE_3(3, "S259 K15+475华士红旗路北向南"),
    CODE_4(4, "S357 K10+005顾山暨南大道东向西"), //flag
    CODE_5(5, "S229 K181+450申港海港大道北向南"),
    CODE_6(6, "S229 K181+520申港海港大道南向北"),
    CODE_7(7, "S122 K156+50云亭江阴大道西向东"),
    CODE_8(8, "S122 K159+500云亭江阴大道东向西");
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private EnumSiteCode(int index, String name) {
        this.index = index;
        this.name = name;
    }

    // 普通方法
    public static String getName(int index) {
        for (EnumSiteCode c : EnumSiteCode.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public static List<Map<String, String>> getSiteCodeList() {
        List<Map<String, String>> list = new ArrayList();
        for (EnumSiteCode item : EnumSiteCode.values()) {
            Map<String, String> map = new HashMap<String, String>();
            //map.put("index",item.getIndex());
            map.put("name", item.getName());
            list.add(map);
        }
        return list;
    }
}

