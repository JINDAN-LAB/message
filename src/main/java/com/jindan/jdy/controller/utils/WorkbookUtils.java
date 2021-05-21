package com.jindan.jdy.controller.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;

@Slf4j
public class WorkbookUtils {

    private final static String excel2003 =".xls";
    private final static String excel2007 =".xlsx";

    public static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception{
        log.info("======“getWorkbook静态方法”开始执行======");
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if(excel2003.equals(fileType)){
            wb = new HSSFWorkbook(inStr);
        }else if(excel2007.equals(fileType)){
            wb = new XSSFWorkbook(inStr);
        }else{
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }


}
