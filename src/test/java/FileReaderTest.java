import com.common.poi.excel.Feedback;
import com.common.poi.excel.FileReader;
import com.common.poi.excel.FileWriter;
import com.common.poi.excel.constant.FileType;
import com.common.poi.excel.model.CellValueModel;
import com.common.poi.excel.util.CellDateUtil;
import com.common.poi.excel.util.ValidatorUtil;
import org.junit.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Luopc on 2016-9-8-0008.
 */
public class FileReaderTest {

    @Test
    public void test01(){
        Feedback feedback = FileReader.getInstance().read("1111", new File("D:\\java-output\\2016-09-08.xlsx"), FileType.EXCEL2007);
        List<Map<String,Object>> result = feedback.getResult();
        //错误提示信息
        System.out.println(feedback.getErrorTip());
        //出错的数据
        Map<String, List<Map<String, CellValueModel>>> errorData = feedback.getErrorData();

        if(!result.isEmpty()) {
            System.out.println(result.size());
            for (Map<String, Object> data : result) {
                for (String key : data.keySet()) {
                    System.out.print(key + "=" + String.valueOf(data.get(key)) + "|");
                }
                System.out.println();
                System.out.println("-------------------------------------------");

            }
        }

        FileWriter.getInstance().writeFile("1111", "D:\\java-output\\2016-09-08-result.xlsx", result, FileType.EXCEL2007);
        //FileWriter.getInstance().writeFile("1111", "D:\\java-output\\2016-09-08-error.xlsx", result, FileType.EXCEL2007);
    }



    @Test
    public void test02(){
        System.out.print(ValidatorUtil.isValidDate("2018.04.04 12:35:12"));
        System.out.print(ValidatorUtil.isValidDate("2018.04.04"));
        System.out.print(ValidatorUtil.isValidDate("12:35:12"));
    }

    @Test
    public void test03(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date rs  = CellDateUtil.cellValueToDate("2014年12月11日");
        System.out.println(formatter.format(rs));

        rs  = CellDateUtil.cellValueToDate("2014年12月11日 12时30分");
        System.out.println(formatter.format(rs));

        rs  = CellDateUtil.cellValueToDate("2014年12月11日 12时30分56秒");
        System.out.println(formatter.format(rs));

        rs  = CellDateUtil.cellValueToDate("2014-12-11");
        System.out.println(formatter.format(rs));

        rs  = CellDateUtil.cellValueToDate("2014-12-11 12:30:56");
        System.out.println(formatter.format(rs));

        rs  = CellDateUtil.cellValueToDate("12:30:56");
        System.out.println(formatter.format(rs));

        rs  = CellDateUtil.cellValueToDate("12:30");
        System.out.println(formatter.format(rs));

    }

}
