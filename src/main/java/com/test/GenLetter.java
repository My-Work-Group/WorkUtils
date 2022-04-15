package com.test;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import com.test.entity.LetterEntity;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import static com.test.utils.DateUtil.*;
import static com.test.utils.ExcelUtil.readExcel;
import static com.test.utils.StringUtil.reName;

// 这个脚本的目的，根据车牌所属单位（联系人），车牌号，日期，检测站点自动生成函告单
public class GenLetter {
    public static void main(String[] args) throws DocumentException, IOException {
        String fontPath = "E:\\OneDrive - vip.henu.edu.cn\\Codes\\WorkUtils\\src\\main\\resources\\font\\FZ-FangSong_GBK.TTF";
        // 审核通过车辆信息的Excel文件
        String file = "C:\\Users\\Pangpd\\Desktop\\1.xlsx";
        // 源母版pdf路径
        String sourcePdf = "C:\\Users\\Pangpd\\Desktop\\母板_超限超载运输违法行为联合函告单.pdf";
        // 生成pdf函告单的保存路径
        String destPath = "C:\\Users\\Pangpd\\Desktop\\函告单\\";

        //设置编码
        //BaseFont baseFont = BaseFont.createFont(fontPath, "IDENTITY_H",BaseFont.NOT_EMBEDDED);
        //BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);

        List<LetterEntity> list = readExcel(file);
        for (LetterEntity letterEntity : list) {
            PdfReader reader = new PdfReader(sourcePdf);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            /* 将要生成的目标PDF文件名称 */
            PdfStamper ps = new PdfStamper(reader, bos);

            /* 使用中文字体 */
            //BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            BaseFont bf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            ArrayList<BaseFont> fontList = new ArrayList<BaseFont>();
            fontList.add(bf);

            /* 取出报表模板中的所有字段 */
            AcroFields fields = ps.getAcroFields();
            fields.setSubstitutionFonts(fontList);
            Map<String, String> data = data(letterEntity);
            fillData(fields, data);

            /* 必须要调用这个，否则文档不会生成的 */
            String checkTime = reName(letterEntity.getCheckTime());
            //String name = letterEntity.getPlateNum() + "(" + checkTime + ")";
            //以函告单编号命名
            String name = letterEntity.getNumberId();
            String destPdfName = destPath + name + ".pdf";
            ps.setFormFlattening(true);
            ps.close();
            OutputStream fos = new FileOutputStream(destPdfName);
            fos.write(bos.toByteArray());
            fos.flush();
            fos.close();
            bos.close();
        }
    }

    public static Map<String, String> data(LetterEntity letterEntity) {
        // 获取当前日期
        String date = getCurrentDate();
        Map<String, String> map = new HashMap<>();//要插入的数据
        String siteCode = letterEntity.getSiteCode();
        String checkTime = letterEntity.getCheckTime();
        String numberId = letterEntity.getNumberId();
        String owner_name = letterEntity.getOwnerName();
        String plateNum = letterEntity.getPlateNum();
        map.put("number", numberId);
        map.put("owner_name", owner_name);
        map.put("plate_num", plateNum);
        map.put("year", String.valueOf(getYear(checkTime)));
        map.put("month", String.valueOf(getMonth(checkTime)));
        map.put("day", String.valueOf(getDay(checkTime)));
        map.put("site_code", siteCode);
        map.put("current_year", String.valueOf(getYear(date)));
        map.put("current_month", String.valueOf(getMonth(date)));
        map.put("current_day", String.valueOf(getDay(date)));
        return map;
    }

    public static void fillData(AcroFields fields, Map<String, String> data)
            throws IOException, DocumentException {
        for (String key : data.keySet()) {
            String value = data.get(key);
            fields.setField(key, value); // 为字段赋值,注意字段名称是区分大小写的
        }
    }

}
