package com.jindan.jdy.utils.exceloperation;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelImportOperation {

    public static List<String[]> getDataFromExcel(InputStream inputStream, String filePath, List<String> headersList) throws Exception{

        /*创建一个存放导入结果的list*/
        List<String[]> resultList = new ArrayList<>();

        /*实例化一个本地excel操作类*/
        ExcelOperation excelOperation = new ExcelOperation();

        /*创建一个工作簿*/
        Workbook workbook = excelOperation.getWorkbook(inputStream,filePath);

        /*得到一个工作表*/
        Sheet sheet = workbook.getSheetAt(0);

        /*获得表头*/
        Row headerRow = sheet.getRow(0);

        /*判断表头列数是否与预期列数一致*/
        if (headerRow.getPhysicalNumberOfCells()!=headersList.size()){
            throw new Exception("表头列数与要导入的数据库不对应！");
        }

        /*判断表头与预期数据是否一致*/
        int index = 0;
        while (index < headersList.size()){
            Cell cell = headerRow.getCell(index);
            String header = headersList.get(index);
            if (excelOperation.getRightTypeCell(cell).equals(header)){

            }else {
                throw new Exception("表头["+excelOperation.getRightTypeCell(cell)+"]不合规范，请修改后重新导入");
            }
            index++;
        }

        /*获得数据的总行数*/
        int totalRowNum = sheet.getLastRowNum();
        if (0 == totalRowNum){
            throw new Exception("Excel内没有数据");
        }

        /*要获得属性*/
        String name = "";

        /*获得所有数据*/
        for (int i = 1;i <= totalRowNum;i++){
            /*获得第i行对象*/
            Row row = sheet.getRow(i);
            String[] cells = new String[headersList.size()];
            for (int j = 0;j < headersList.size();j++){
                name = excelOperation.getRightTypeCell(row.getCell(j));
                cells[j] = name;
            }
            resultList.add(cells);
        }
        return resultList;
    }
}
