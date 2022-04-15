import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.test.utils.EnumSiteCode.getSiteCodeList;

public class TestEnum {

    public static void main(String[] args) {
        List<Map<String, String>> list;
        list = getSiteCodeList();
        for (Map<String, String> mapList : list) {
            for (String key : mapList.keySet()) {
                System.out.println(mapList.get(key));
            }
        }
    }
}
