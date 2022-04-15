import java.text.ParseException;
import java.util.List;

import static com.test.utils.DateUtil.getDateList;

public class testDate {

    // test
    public static void main(String[] args) throws ParseException {
        List<String> dateList = getDateList("20211215", "20220119");
        System.out.println(dateList.get(0));
        }

}
