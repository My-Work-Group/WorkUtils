import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class JSONArrayToExcel {
    public static void main(String[] args) {

        Student student1 = new Student("张三", "男", 18);
        Student student2 = new Student("李四", "男", 19);
        Student student3 = new Student("小花", "女", 20);

        //实体List
        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);

        //List转为JSONArray
        JSONArray jsonArray = JSONArray.fromObject(studentList);

        //实体属性名称数组
        Field[] fields = null;
        try {
            Class clazz = Class.forName("Student");
            fields = clazz.getDeclaredFields();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] title = {"姓名", "年龄", "性别"};

        if (fields != null) {
            String fileName = "jsonarrayToExcelTest.xls"; //文件名
            File myFile = new File("E:\\" + fileName);

            try {
                WritableWorkbook writableWorkbook = Workbook.createWorkbook(myFile); //定义工作簿对象
                WritableSheet writableSheet = writableWorkbook.createSheet("sheet1", 0); //定义sheet对象

                for (int i = 0; i < title.length; i++) { //加入表头单元格内容
                    writableSheet.addCell(new Label(i, 0, title[i]));
                }

                for (int i = 0; i < jsonArray.size(); i++) { //加入数据单元格内容
                    JSONObject json = jsonArray.getJSONObject(i);
                    for (int j = 0; j < fields.length; j++) {
                        writableSheet.addCell(new Label(j, i + 1, json.get(fields[j].getName()).toString()));
                    }
                }

                writableWorkbook.write();
                writableWorkbook.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
