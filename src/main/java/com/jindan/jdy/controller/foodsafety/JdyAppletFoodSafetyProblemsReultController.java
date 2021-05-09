package com.jindan.jdy.controller.foodsafety;

import com.jindan.jdy.common.dto.JdyAppletFoodSafetyProblemsExcle;
import com.jindan.jdy.common.dto.JdyAppletFoodSafetyProblemsReultDto;
import com.jindan.jdy.common.pojo.JdyAppletFoodSafetyProblemsReult;
import com.jindan.jdy.common.pojo.JdyAppletFootSafetyPerson;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.CommonUtils;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.foodsafety.JdyAppletFoodSafetyProblemsReultService;
import com.jindan.jdy.service.foodsafety.JdyAppletFootSafetyPersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
*
* <p>说明： 食品安全小程序API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年7月27日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "食品安全小程序")
@RestController
@RequestMapping("/jdyAppletFoodSafetyProblemsReult")
public class JdyAppletFoodSafetyProblemsReultController{

    @Autowired
    JdyAppletFoodSafetyProblemsReultService jdyAppletFoodSafetyProblemsReultService;

    @Autowired
    JdyAppletFootSafetyPersonService jdyAppletFootSafetyPersonService;

    @Autowired
    RedisUtil redisUtil;

    @ApiOperation(value = "食品处理", notes = "参数:食品处理")
    @PostMapping("/seletejdyAppletFoodSafetyProblemsReult")
    public ResultVo seleteDepartment( @ApiParam(name = "jdyAppletFoodSafetyDto", required = false)
                                      @RequestBody JdyAppletFoodSafetyProblemsReult jdyAppletFoodSafetyDto){

        List<JdyAppletFoodSafetyProblemsReult> list = jdyAppletFoodSafetyProblemsReultService.seleteList(jdyAppletFoodSafetyDto);
        return  ResultVo.success(list);
    }

    @ApiOperation(value = "车间问题结束查询处理", notes = "参数:车间问题结束查询处理")
    @PostMapping("/seletejdyApplSafetyProblemsReult")
    public ResultVo seleteDartment( @ApiParam(name = "jdyAppletFoodSafetyDto", required = false)
                                      @RequestBody JdyAppletFoodSafetyProblemsReult jdyAppletFoodSafetyDto){
        if(jdyAppletFoodSafetyDto != null){
            List<JdyAppletFoodSafetyProblemsReult> list = jdyAppletFoodSafetyProblemsReultService.seleteWentiList(jdyAppletFoodSafetyDto);
            return  ResultVo.success(list);
        }else{
            if( redisUtil.get("seletejdyAppletFoodSatyProblemsReult") == null){
                List<JdyAppletFoodSafetyProblemsReult> list = jdyAppletFoodSafetyProblemsReultService.seleteWentiList(jdyAppletFoodSafetyDto);
                redisUtil.set("seletejdyAppletFoodSatyProblemsReult",list);
                return  ResultVo.success(list);
            }else{
                return  ResultVo.success(redisUtil.get("seletejdyAppletFoodSatyProblemsReult"));
            }
        }
    }

    @ApiOperation("更新食品处理")
    @PostMapping("updatejdyAppletFoodSafetyProblemsReult")
    public ResultVo updatekeyPointPracticableService(@ApiParam(name = "userPermission", required = true)
                                                     @RequestBody JdyAppletFoodSafetyProblemsReult userPermission){
        boolean b = jdyAppletFoodSafetyProblemsReultService.updateById(userPermission);
        if(b){
            redisUtil.del("seletejdyAppletFoodSafetyProblemsReult");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

//    @ApiOperation("===============-----------============")
//    @PostMapping("addjdyAppletFafetyProblemsReult")
//    public ResultVo addkeyacticableService( @ApiParam(name = "users", required = true)
//                                                   @RequestBody  JdyAppletFoodSafetyProblemsReult users){
//
//            jdyAppletFoodSafetyProblemsReultService.sentSms(users);
//
//        return ResultVo.failed();
//    }

    @ApiOperation("新增食品处理")
    @PostMapping("addjdyAppletFoodSafetyProblemsReult")
    public ResultVo addkeyPointPracticableService( @ApiParam(name = "users", required = true)
                                                   @RequestBody  JdyAppletFoodSafetyProblemsReult users){
        if(users.getParentId() != null){
            jdyAppletFoodSafetyProblemsReultService.sentSms(users);
        }
        boolean save = jdyAppletFoodSafetyProblemsReultService.save(users);
        if(save){
            redisUtil.del("seletejdyAppletFoodSafetyProblemsReult");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除食品处理")
    @DeleteMapping("deletejdyAppletFoodSafetyProblemsReult/{id}")
    public ResultVo deletekeyPointPracticableService(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){
        boolean b = jdyAppletFoodSafetyProblemsReultService.removeById(id);
        if(b){
            redisUtil.del("seletejdyAppletFoodSafetyProblemsReult");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

//    @ApiOperation("领导给下级提交的问题项导出excle")
//    @PostMapping(value = "/debtBanzuExcle")
//    public void debtBanzuExcle(HttpServletResponse response,
//                                JdyAppletFootSafetyPerson param) throws IOException {
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        HSSFSheet sheet = workbook.createSheet("数据导出");
//        List<WaimaoFahuoHuikuan>  lists = new ArrayList<>();
//        List<JdyAppletFoodSafetyProblemsExcle> classmatefa = jdyAppletFoodSafetyProblemsReultService.seleteAlllist(param);  // 查询出来的是发货明细表和回款明细表
//        System.out.println("index===============================");
//        System.out.println(classmatefa);
//    }



    @ApiOperation("外部提交给车间提交的问题项导出excle")
    @PostMapping(value = "/debtwaibuchejianExcle")
    public void debtwaibuchejianExcle(HttpServletResponse response, JdyAppletFootSafetyPerson param) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("数据导出");
        List<String>  list   = new ArrayList<>();
//        JdyAppletFootSafety
        List<JdyAppletFootSafetyPerson> jdyAppletFootSafetyPeople = jdyAppletFootSafetyPersonService.seleteList(param);
        for (int i = 0; i < jdyAppletFootSafetyPeople.size(); i++) {
            if(jdyAppletFootSafetyPeople.get(i).getPassword() != null){
                list.add(jdyAppletFootSafetyPeople.get(i).getPassword());
            }
        }
        List<JdyAppletFoodSafetyProblemsExcle> classmatefa = jdyAppletFoodSafetyProblemsReultService.seleteAllWaibuChejianSinglelist(list);  // 查询出来的是发货明细表和回款明细表
        String fileName = df.format(new Date())+"问题项导出"+".xls";//设置要导出的文件的名字
//        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = {"级别","车间信息","分类信息", "问题内容",
                "提交问题时间","处理周期","处理时间","处理内容"};
//        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
//      //在excel表中添加表头
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        System.out.println("========================---------------=========================");
        System.out.println(classmatefa);
        HSSFRow row1;
        //在表中存放查询到的数据放入对应的列
        for (int i = 0; i <classmatefa.size() ; i++){
            int k=0;
            for (int j = 0; j <classmatefa.get(i).getJdyAppletFoodSafetyProblemsReultList().size() ; j++) {
                if(null == classmatefa.get(i).getJdyAppletFoodSafetyProblemsReultList().get(j).getContents() || classmatefa.get(i).getJdyAppletFoodSafetyProblemsReultList().get(j).getContents().equals("")){
                    continue;
                }
                if(k == 0){
                    row1 = sheet.createRow(rowNum);
                    row1.createCell(0).setCellValue("外部提价给车间问题");
                    row1.createCell(1).setCellValue(classmatefa.get(i).getJdyAppletFoodSafetyProblemsReultList().get(j).getChejianName());
                    row1.createCell(2).setCellValue(classmatefa.get(i).getJdyAppletFoodSafetyProblemsReultList().get(j).getLeibie());
                    row1.createCell(3).setCellValue(classmatefa.get(i).getJdyAppletFoodSafetyProblemsReultList().get(j).getContents());
                    row1.createCell(4).setCellValue(classmatefa.get(i).getJdyAppletFoodSafetyProblemsReultList().get(j).getTijiaoTime());
                    row1.createCell(5).setCellValue(CommonUtils.getDistanceDays(classmatefa.get(i).getJdyAppletFoodSafetyProblemsReultList().get(j).getResultTimes(),classmatefa.get(i).getJdyAppletFoodSafetyProblemsReultList().get(j).getTijiaoTime()));
                    row1.createCell(6).setCellValue(classmatefa.get(i).getJdyAppletFoodSafetyProblemsReultList().get(j).getResultTimes());
                    row1.createCell(7).setCellValue(classmatefa.get(i).getJdyAppletFoodSafetyProblemsReultList().get(j).getResultContent());
                    rowNum++;
                }else{
                    row1 = sheet.createRow(rowNum);
                    row1.createCell(1).setCellValue(classmatefa.get(i).getJdyAppletFoodSafetyProblemsReultList().get(j).getChejianName());
                    row1.createCell(2).setCellValue(classmatefa.get(i).getJdyAppletFoodSafetyProblemsReultList().get(j).getLeibie());
                    row1.createCell(3).setCellValue(classmatefa.get(i).getJdyAppletFoodSafetyProblemsReultList().get(j).getContents());
                    row1.createCell(4).setCellValue(classmatefa.get(i).getJdyAppletFoodSafetyProblemsReultList().get(j).getTijiaoTime());
                    row1.createCell(5).setCellValue(CommonUtils.getDistanceDays(classmatefa.get(i).getJdyAppletFoodSafetyProblemsReultList().get(j).getResultTimes(),classmatefa.get(i).getJdyAppletFoodSafetyProblemsReultList().get(j).getTijiaoTime()));
                    row1.createCell(6).setCellValue(classmatefa.get(i).getJdyAppletFoodSafetyProblemsReultList().get(j).getResultTimes());
                    row1.createCell(7).setCellValue(classmatefa.get(i).getJdyAppletFoodSafetyProblemsReultList().get(j).getResultContent());
                    rowNum++;
                }
                k++;
            }
            rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }
























//    //已解决
//    @ApiOperation("班组需要处理的问题项导出excle")
//    @PostMapping(value = "/debtbanzhuchuliExcle")
//    public void debtbanzhuchuliExcle(HttpServletResponse response, @RequestBody JdyAppletFoodSafetyProblemsReult param) throws Exception {
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        HSSFSheet sheet = workbook.createSheet("数据导出");
//        List<JdyAppletFoodSafetyProblemsReultDto> classmatefa = jdyAppletFoodSafetyProblemsReultService.seleteAllZognjianSinglelist(param);  // 查询出来的是发货明细表和回款明细表
//        String fileName = df.format(new Date())+"问题项导出"+".xls";//设置要导出的文件的名字
////        //新增数据行，并且设置单元格数据
//        int rowNum = 1;
//        String[] headers = {"级别","车间信息","分类信息", "问题内容",
//                "提交问题时间","处理周期","处理时间","处理内容"};
////        //headers表示excel表中第一行的表头
//        HSSFRow row = sheet.createRow(0);
////      //在excel表中添加表头
//        for(int i=0;i<headers.length;i++){
//            HSSFCell cell = row.createCell(i);
//            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
//            cell.setCellValue(text);
//        }
//        System.out.println("========================---------------=========================");
//        System.out.println(classmatefa);
//        HSSFRow row1;
//        //在表中存放查询到的数据放入对应的列
//        for (int i = 0; i <classmatefa.size() ; i++){
//            int k=0;
//            for (int j = 0; j <classmatefa.size() ; j++) {
//                if(null == classmatefa.get(i).getContents() || classmatefa.get(i).getContents().equals("")){
//                    continue;
//                }
//                if(k == 0){
//                    row1 = sheet.createRow(rowNum);
//                    row1.createCell(0).setCellValue("公司级");
//                    row1.createCell(1).setCellValue(classmatefa.get(i).getJdyAppletFoodSafety().getName());
//                    row1.createCell(2).setCellValue(classmatefa.get(i).getLeibie());
//                    row1.createCell(3).setCellValue(classmatefa.get(i).getContents());
//                    row1.createCell(4).setCellValue(classmatefa.get(i).getTijiaoTime());
//                    row1.createCell(5).setCellValue(CommonUtils.getDistanceDays(classmatefa.get(j).getResultTimes(),classmatefa.get(i).getTijiaoTime()));
//                    row1.createCell(6).setCellValue(classmatefa.get(i).getResultTimes());
//                    row1.createCell(7).setCellValue(classmatefa.get(i).getResultContent());
//                    rowNum++;
//                }else{
//                    row1 = sheet.createRow(rowNum);
//                    row1.createCell(1).setCellValue(classmatefa.get(i).getJdyAppletFoodSafety().getName());
//                    row1.createCell(2).setCellValue(classmatefa.get(i).getLeibie());
//                    row1.createCell(3).setCellValue(classmatefa.get(i).getContents());
//                    row1.createCell(4).setCellValue(classmatefa.get(i).getTijiaoTime());
//                    row1.createCell(5).setCellValue(CommonUtils.getDistanceDays(classmatefa.get(j).getResultTimes(),classmatefa.get(i).getTijiaoTime()));
//                    row1.createCell(6).setCellValue(classmatefa.get(i).getResultTimes());
//                    row1.createCell(7).setCellValue(classmatefa.get(i).getResultContent());
//                    rowNum++;
//                }
//                k++;
//            }
//            rowNum++;
//        }
//        response.setContentType("application/octet-stream");
//        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
//        response.flushBuffer();
//        workbook.write(response.getOutputStream());
//    }















    //已解决
    @ApiOperation("班组提交的问题项导出excle")
    @PostMapping(value = "/debtbanzhuExcle")
    public void debtbanzhuExcle(HttpServletResponse response, @RequestBody JdyAppletFoodSafetyProblemsReult param) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("数据导出");
        List<JdyAppletFoodSafetyProblemsReultDto> classmatefa = jdyAppletFoodSafetyProblemsReultService.seleteAllZognjianSinglelist(param);  // 查询出来的是发货明细表和回款明细表
        String fileName = df.format(new Date())+"问题项导出"+".xls";//设置要导出的文件的名字
//        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = {"级别","车间信息","分类信息", "问题内容",
                "提交问题时间","处理周期","处理时间","处理内容"};
//        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
//      //在excel表中添加表头
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        System.out.println("========================---------------=========================");
        System.out.println(classmatefa);
        HSSFRow row1;
        //在表中存放查询到的数据放入对应的列
        for (int i = 0; i <classmatefa.size() ; i++){
            int k=0;
            for (int j = 0; j <classmatefa.size() ; j++) {
                if(null == classmatefa.get(i).getContents() || classmatefa.get(i).getContents().equals("")){
                    continue;
                }
                if(k == 0){
                    row1 = sheet.createRow(rowNum);
                    row1.createCell(0).setCellValue("班组级");
                    row1.createCell(1).setCellValue(classmatefa.get(i).getJdyAppletFoodSafety().getName());
                    row1.createCell(2).setCellValue(classmatefa.get(i).getLeibie());
                    row1.createCell(3).setCellValue(classmatefa.get(i).getContents());
                    row1.createCell(4).setCellValue(classmatefa.get(i).getTijiaoTime());
                    row1.createCell(5).setCellValue(CommonUtils.getDistanceDays(classmatefa.get(j).getResultTimes(),classmatefa.get(i).getTijiaoTime()));
                    row1.createCell(6).setCellValue(classmatefa.get(i).getResultTimes());
                    row1.createCell(7).setCellValue(classmatefa.get(i).getResultContent());
                    rowNum++;
                }else{
                    row1 = sheet.createRow(rowNum);
                    row1.createCell(1).setCellValue(classmatefa.get(i).getJdyAppletFoodSafety().getName());
                    row1.createCell(2).setCellValue(classmatefa.get(i).getLeibie());
                    row1.createCell(3).setCellValue(classmatefa.get(i).getContents());
                    row1.createCell(4).setCellValue(classmatefa.get(i).getTijiaoTime());
                    row1.createCell(5).setCellValue(CommonUtils.getDistanceDays(classmatefa.get(j).getResultTimes(),classmatefa.get(i).getTijiaoTime()));
                    row1.createCell(6).setCellValue(classmatefa.get(i).getResultTimes());
                    row1.createCell(7).setCellValue(classmatefa.get(i).getResultContent());
                    rowNum++;
                }
                k++;
            }
            rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    //  已解决
    @ApiOperation("车间提交的问题项导出excle")
    @PostMapping(value = "/debtchejianExcle")
    public void debchejianExcle(HttpServletResponse response, @RequestBody JdyAppletFoodSafetyProblemsReult param) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("数据导出");
        List<JdyAppletFoodSafetyProblemsReultDto> classmatefa = jdyAppletFoodSafetyProblemsReultService.seleteAllZognjianSinglelist(param);  // 查询出来的是发货明细表和回款明细表
        String fileName = df.format(new Date())+"问题项导出"+".xls";//设置要导出的文件的名字
//        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = {"级别","车间信息","分类信息", "问题内容",
                "提交问题时间","处理周期","处理时间","处理内容"};
//        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
//      //在excel表中添加表头
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        System.out.println("========================---------------=========================");
        System.out.println(classmatefa);
        HSSFRow row1;
        //在表中存放查询到的数据放入对应的列
        for (int i = 0; i <classmatefa.size() ; i++){
            int k=0;
            for (int j = 0; j <classmatefa.size() ; j++) {
                if(null == classmatefa.get(i).getContents() || classmatefa.get(i).getContents().equals("")){
                    continue;
                }
                if(k == 0){
                    row1 = sheet.createRow(rowNum);
                    row1.createCell(0).setCellValue("班组级");
                    row1.createCell(1).setCellValue(classmatefa.get(i).getJdyAppletFoodSafety().getName());
                    row1.createCell(2).setCellValue(classmatefa.get(i).getLeibie());
                    row1.createCell(3).setCellValue(classmatefa.get(i).getContents());
                    row1.createCell(4).setCellValue(classmatefa.get(i).getTijiaoTime());
                    row1.createCell(5).setCellValue(CommonUtils.getDistanceDays(classmatefa.get(j).getResultTimes(),classmatefa.get(i).getTijiaoTime()));
                    row1.createCell(6).setCellValue(classmatefa.get(i).getResultTimes());
                    row1.createCell(7).setCellValue(classmatefa.get(i).getResultContent());
                    rowNum++;
                }else{
                    row1 = sheet.createRow(rowNum);
                    row1.createCell(1).setCellValue(classmatefa.get(i).getJdyAppletFoodSafety().getName());
                    row1.createCell(2).setCellValue(classmatefa.get(i).getLeibie());
                    row1.createCell(3).setCellValue(classmatefa.get(i).getContents());
                    row1.createCell(4).setCellValue(classmatefa.get(i).getTijiaoTime());
                    row1.createCell(5).setCellValue(CommonUtils.getDistanceDays(classmatefa.get(j).getResultTimes(),classmatefa.get(i).getTijiaoTime()));
                    row1.createCell(6).setCellValue(classmatefa.get(i).getResultTimes());
                    row1.createCell(7).setCellValue(classmatefa.get(i).getResultContent());
                    rowNum++;
                }
                k++;
            }
            rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }


    //已解决
    @ApiOperation("总监车间提交的问题项导出excle")
    @PostMapping(value = "/debzongjianExcle")
    public void debzongjianExcle(HttpServletResponse response, @RequestBody JdyAppletFoodSafetyProblemsReult param) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("数据导出");
        List<JdyAppletFoodSafetyProblemsReultDto> classmatefa = jdyAppletFoodSafetyProblemsReultService.seleteAllGongsiSinglelist(param);  // 查询出来的是发货明细表和回款明细表
        System.out.println("==================================classmatefa=========================");
        System.out.println(classmatefa);
        String fileName = df.format(new Date())+"问题项导出"+".xls";//设置要导出的文件的名字
//        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = {"级别","车间信息","分类信息", "问题内容",
                "提交问题时间","处理周期","处理时间","处理内容"};
//        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
//      //在excel表中添加表头
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        System.out.println("========================---------------=========================");
        System.out.println(classmatefa);
        HSSFRow row1;
        //在表中存放查询到的数据放入对应的列
        for (int i = 0; i <classmatefa.size() ; i++){
            int k=0;
            if(null == classmatefa.get(i).getContents() || classmatefa.get(i).getContents().equals("")){
                continue;
            }
            if(rowNum == 1){
                row1 = sheet.createRow(rowNum);
                row1.createCell(0).setCellValue("公司级");
                row1.createCell(1).setCellValue(classmatefa.get(i).getJdyAppletFoodSafety().getName());
                row1.createCell(2).setCellValue(classmatefa.get(i).getLeibie());
                row1.createCell(3).setCellValue(classmatefa.get(i).getContents());
                row1.createCell(4).setCellValue(classmatefa.get(i).getTijiaoTime());
                row1.createCell(5).setCellValue(CommonUtils.getDistanceDays(classmatefa.get(i).getResultTimes(),classmatefa.get(i).getTijiaoTime()));
                row1.createCell(6).setCellValue(classmatefa.get(i).getResultTimes());
                row1.createCell(7).setCellValue(classmatefa.get(i).getResultContent());
                rowNum++;
            }else{
                row1 = sheet.createRow(rowNum);
                row1.createCell(1).setCellValue(classmatefa.get(i).getJdyAppletFoodSafety().getName());
                row1.createCell(2).setCellValue(classmatefa.get(i).getLeibie());
                row1.createCell(3).setCellValue(classmatefa.get(i).getContents());
                row1.createCell(4).setCellValue(classmatefa.get(i).getTijiaoTime());
                row1.createCell(5).setCellValue(CommonUtils.getDistanceDays(classmatefa.get(i).getResultTimes(),classmatefa.get(i).getTijiaoTime()));
                row1.createCell(6).setCellValue(classmatefa.get(i).getResultTimes());
                row1.createCell(7).setCellValue(classmatefa.get(i).getResultContent());
                rowNum++;
            }

        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

//已解决
    @ApiOperation("公司车间提交的问题项导出excle")
    @PostMapping(value = "/debgognsiExcle")
    public void debgognsiExcle(HttpServletResponse response, @RequestBody JdyAppletFoodSafetyProblemsReult param) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("数据导出");
        List<JdyAppletFoodSafetyProblemsReultDto> classmatefa = jdyAppletFoodSafetyProblemsReultService.seleteAllGongsiSinglelist(param);  // 查询出来的是发货明细表和回款明细表
        System.out.println("==================================classmatefa=========================");
        System.out.println(classmatefa);
        String fileName = df.format(new Date())+"问题项导出"+".xls";//设置要导出的文件的名字
//        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = {"级别","车间信息","分类信息", "问题内容",
                "提交问题时间","处理周期","处理时间","处理内容","处理人","提交人"};
//        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
//      //在excel表中添加表头
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        System.out.println("========================---------------=========================");
        System.out.println(classmatefa);
        HSSFRow row1;
        //在表中存放查询到的数据放入对应的列
        for (int i = 0; i <classmatefa.size() ; i++){
                if(null == classmatefa.get(i).getContents() || classmatefa.get(i).getContents().equals("")){
                    continue;
                }
                if(rowNum == 1){
                    row1 = sheet.createRow(rowNum);
                    row1.createCell(0).setCellValue("公司级");
                    row1.createCell(1).setCellValue(classmatefa.get(i).getJdyAppletFoodSafety().getName());
                    row1.createCell(2).setCellValue(classmatefa.get(i).getLeibie());
                    row1.createCell(3).setCellValue(classmatefa.get(i).getContents());
                    row1.createCell(4).setCellValue(classmatefa.get(i).getTijiaoTime());
                    row1.createCell(5).setCellValue(CommonUtils.getDistanceDays(classmatefa.get(i).getResultTimes(),classmatefa.get(i).getTijiaoTime()));
                    row1.createCell(6).setCellValue(classmatefa.get(i).getResultTimes());
                    row1.createCell(7).setCellValue(classmatefa.get(i).getResultContent());
                    row1.createCell(8).setCellValue(classmatefa.get(i).getResultPerson());
                    row1.createCell(9).setCellValue(classmatefa.get(i).getRpersons());
                    rowNum++;
                }else{
                    row1 = sheet.createRow(rowNum);
                    row1.createCell(1).setCellValue(classmatefa.get(i).getJdyAppletFoodSafety().getName());
                    row1.createCell(2).setCellValue(classmatefa.get(i).getLeibie());
                    row1.createCell(3).setCellValue(classmatefa.get(i).getContents());
                    row1.createCell(4).setCellValue(classmatefa.get(i).getTijiaoTime());
                    row1.createCell(5).setCellValue(CommonUtils.getDistanceDays(classmatefa.get(i).getResultTimes(),classmatefa.get(i).getTijiaoTime()));
                    row1.createCell(6).setCellValue(classmatefa.get(i).getResultTimes());
                    row1.createCell(7).setCellValue(classmatefa.get(i).getResultContent());
                    row1.createCell(8).setCellValue(classmatefa.get(i).getResultPerson());
                    row1.createCell(9).setCellValue(classmatefa.get(i).getRpersons());
                    rowNum++;
                }
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }







}