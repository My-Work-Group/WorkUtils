import com.test.entity.LetterEntity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.test.utils.DateUtil.*;
import static com.test.utils.ExcelUtil.readExcel;


public class test {

    public static void main(String[] args) {
//        String string = "2022-01-21 17:41:14";
//        System.out.println(getYear(string));
//        System.out.println(getMonth(string));
//        System.out.println(getDay(string));

        Date d = new Date();
        SimpleDateFormat sbf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sbf.format(d);
        System.out.println(str);
    }


}
