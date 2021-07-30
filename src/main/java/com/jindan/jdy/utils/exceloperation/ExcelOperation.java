package com.jindan.jdy.utils.exceloperation;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelOperation {

    private final static String excel_2003 = ".xls";
    private final static String excel_2007 = ".xlsx";

    public Workbook getWorkbook(InputStream inputStream, String fileName) throws Exception{

        /*新建一个空工工作簿*/
        Workbook workbook = null;

        /*获取文件后缀*/
        String fileType = fileName.substring(fileName.lastIndexOf("."));

        /*判断文件类型*/
        if (excel_2003.equals(fileType)){
            workbook = new HSSFWorkbook(inputStream);
        }else if (excel_2007.equals(fileType)){
            workbook = new XSSFWorkbook(inputStream);
        }else {
            throw new Exception("解析的文件格式有错误！");
        }

        /*返回工作簿*/
        return workbook;
    }

    public String getRightTypeCell(Cell cell){

        /*判断Cell是否为空*/
        if (cell == null){
            return "";
        }

        /*设置单元格的数值为空*/
        String cellString = "";

        /*判断单元格Cell的类型*/
        switch (cell.getCellType()){
            case HSSFCell.CELL_TYPE_STRING:  //字符串
                cellString = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:  //数字
                if (HSSFDateUtil.isCellDateFormatted(cell)){
                    /*用于转化为日期格式*/
                    Date date = cell.getDateCellValue();
                    DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                    cellString= formater.format(date);
                }else {
                    /*用于格式化数字，只保留数字的整数部分*/
                    DecimalFormat decimalFormat = new DecimalFormat("########");
                    cellString = decimalFormat.format(cell.getNumericCellValue());
                }
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:  //Boolean
                cellString=String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_FORMULA:  //公式
                cellString=String.valueOf(cell.getCellFormula());
                break;
            case HSSFCell.CELL_TYPE_BLANK:  //空值
                cellString="";
                break;
            case HSSFCell.CELL_TYPE_ERROR:  //故障
                cellString="";
                break;
            default:
                cellString="ERROR";
                break;
        }

        /*返回单元格数值*/
        return cellString;
    }
}
