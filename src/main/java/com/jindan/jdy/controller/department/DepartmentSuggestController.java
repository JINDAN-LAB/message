package com.jindan.jdy.controller.department;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.DepartmentSuggestDto;
import com.jindan.jdy.common.pojo.DepartmentSuggest;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.department.DepartmentSuggestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
*
* <p>说明： API应用KEYAPI接口层</P>
* @version: V1.0
* @author: kong
* @time    2019年10月16日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "意见箱")
@RestController
@RequestMapping("/departmentSuggest")
public class DepartmentSuggestController{

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    DepartmentSuggestService departmentSuggestService;

    @ApiOperation(value = "意见箱查询", notes = "参数:意见箱查询条件")
    @PostMapping("/seleteUserPermission")
    public ResultVo seleteDepartment( @ApiParam(name = "departmentSuggestDto", required = false)
                                        @RequestBody DepartmentSuggestDto departmentSuggestDto ){
        if(departmentSuggestDto.getSuggestId() != null){
            if(redisUtil.get(departmentSuggestDto.getSuggestId()) == null){
                Page<DepartmentSuggest> list =  departmentSuggestService.seletePagelist(departmentSuggestDto);
                redisUtil.set(departmentSuggestDto.getSuggestId(),list);
                return  ResultVo.success(list);
            }else{
                return  ResultVo.success(redisUtil.get(departmentSuggestDto.getSuggestId()));
            }
        }else{
            Page<DepartmentSuggest> list =  departmentSuggestService.seletePagelist(departmentSuggestDto);
            return  ResultVo.success(list) ;
        }

    }
    @ApiOperation("更新建议信息")
    @PostMapping("updateUserPermission")
    public ResultVo updatefacility(@ApiParam(name = "userPermission", required = true)
                                   @RequestBody DepartmentSuggest userPermission){
        boolean b = departmentSuggestService.updateById(userPermission);
        if(b){
            redisUtil.del(userPermission.getSuggestId());
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增建议信息")
    @PostMapping("addUserPermission")
    public ResultVo addsubset( @ApiParam(name = "userPermission", required = true)
                               @RequestBody  DepartmentSuggest userPermission){
        boolean save = departmentSuggestService.save(userPermission);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除建议信息")
    @DeleteMapping("deleteUserPermission/{id}")
    public ResultVo deletefacility(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){
        boolean b = departmentSuggestService.removeById(id);
        if(b){
            redisUtil.del(id);
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


    @ApiOperation("意见箱导出文本")
    @PostMapping(value = "/addexcleSuggest")
    public void  downloadAllClassmate(HttpServletResponse response, DepartmentSuggestDto param) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("数据导出");
        List<DepartmentSuggest> classmatefa = departmentSuggestService.seletelist(param);
        String fileName = "部门设备导出"+".xls";//设置要导出的文件的名字
//        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = { "提交人", "提交内容", "部门",
                "提交时间","状态","反馈人","反馈内容",  "反馈时间" };
//        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
//        //在excel表中添加表头
//        nRow = sheet.createRow(pageRowNo++);
//        Workbook wb = new SXSSFWorkbook(100);
        for(int i=0;i<headers.length;i++){

//            Cell cell_tem = nRow.createCell(i);
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        HSSFRow row1;
//        //在表中存放查询到的数据放入对应的列
        for (int i = 0; i <classmatefa.size() ; i++){
            row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(classmatefa.get(i).getSuggestName());
            row1.createCell(1).setCellValue(classmatefa.get(i).getSuggestContent());
            row1.createCell(2).setCellValue(classmatefa.get(i).getDeaprtment());
            row1.createCell(3).setCellValue(classmatefa.get(i).getInsertTime());
            row1.createCell(4).setCellValue(classmatefa.get(i).getStatus() == null ?"0":classmatefa.get(i).getStatus().toString());
            row1.createCell(5).setCellValue(classmatefa.get(i).getResultPre());
            row1.createCell(6).setCellValue(classmatefa.get(i).getResultContent());
            row1.createCell(7).setCellValue(classmatefa.get(i).getResultTime());
            rowNum++;
        }
//        response.setContentType("application/octet-stream");
//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        response.setContentType("application/vnd.ms-excel");
//        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
//        //刷新缓冲
//        response.flushBuffer();
//        //workbook将Excel写入到response的输出流中，供页面下载
//        workbook.write(response.getOutputStream());

//        OutputStream outputStream = response.getOutputStream();
//        workbook.write(outputStream);
//        outputStream.flush();
//        outputStream.close();
//        response.reset();
//        response.setContentType("application/vnd.ms-excel;charset=utf-8");
//        response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
//        response.setHeader("Content-disposition", "attachment;filename=" + fileName);

//        response.flushBuffer();
//        workbook.write(response.getOutputStream());
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }


    @ApiOperation("导出文本")
    @GetMapping(value = "/downloadGetClassmate")
    public void  downloadGetClassmate(HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("数据导出");
        List<DepartmentSuggest> classmatefa = departmentSuggestService.list(null);
        String fileName = "部门设备导出"+".xls";//设置要导出的文件的名字
        System.out.println("--------------------");
        System.out.println(classmatefa);
        System.out.println("==========================");
//        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = { "提交人", "提交内容", "部门",
                "提交时间","状态","反馈人","反馈内容",  "反馈时间" };
//        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
//        //在excel表中添加表头
//        nRow = sheet.createRow(pageRowNo++);
//        Workbook wb = new SXSSFWorkbook(100);
        for(int i=0;i<headers.length;i++){
//            Cell cell_tem = nRow.createCell(i);
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        HSSFRow row1;
//        //在表中存放查询到的数据放入对应的列
        for (int i = 0; i <classmatefa.size() ; i++){
            row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(classmatefa.get(i).getSuggestName());
            row1.createCell(1).setCellValue(classmatefa.get(i).getSuggestContent());
            row1.createCell(2).setCellValue(classmatefa.get(i).getDeaprtment());
            row1.createCell(3).setCellValue(classmatefa.get(i).getInsertTime());
            row1.createCell(4).setCellValue(classmatefa.get(i).getStatus() == null ?"0":classmatefa.get(i).getStatus().toString());
            row1.createCell(5).setCellValue(classmatefa.get(i).getResultPre());
            row1.createCell(6).setCellValue(classmatefa.get(i).getResultContent());
            row1.createCell(7).setCellValue(classmatefa.get(i).getResultTime());
            rowNum++;
        }
        response.setContentType("application/octet-stream");
//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
        //刷新缓冲
        response.flushBuffer();
        //workbook将Excel写入到response的输出流中，供页面下载
        workbook.write(response.getOutputStream());

//        OutputStream outputStream = response.getOutputStream();
//        workbook.write(outputStream);
//        outputStream.flush();
//        outputStream.close();
//        response.reset();
//        response.setContentType("application/vnd.ms-excel;charset=utf-8");
//        response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
//        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
//        response.flushBuffer();
//        workbook.write(response.getOutputStream());
    }




}