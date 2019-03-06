package com.zws.excel;


import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/3/5
 */
public interface ExcelUtils {


     static void main(String[] args) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("/Users/zws/Downloads/Banner/c796bc8369dcdb53.xlsx");
        try {
            // 解析每行结果在listener中处理
            ExcelListener listener = new ExcelListener();

            ExcelReader excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX, null, listener);
            excelReader.read();
            List<Object> objectList =   listener.getDatas();
            System.out.println(objectList.size());
        } catch (Exception e) {

        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
