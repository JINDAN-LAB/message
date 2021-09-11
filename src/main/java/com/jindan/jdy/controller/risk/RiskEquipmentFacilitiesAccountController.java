package com.jindan.jdy.controller.risk;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.RiskEquipmentFacilitiesAccountDto;
import com.jindan.jdy.common.excelrowname.ExcelRowName;
import com.jindan.jdy.common.pojo.RiskEquipmentFacilitiesAccount;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.risk.RiskEquipmentFacilitiesAccountService;
import com.jindan.jdy.utils.DateUtils;
import com.jindan.jdy.utils.RandomId;
import com.jindan.jdy.utils.exceloperation.ExcelHeaders;
import com.jindan.jdy.utils.exceloperation.ExcelImportOperation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liangfang
 * @since 2021-08-06
 */
@Slf4j
@RestController
@RequestMapping("/riskEquipmentFacilitiesAccount")
@Api(tags ="设备设施台账")
public class RiskEquipmentFacilitiesAccountController {

    @Autowired
    private RiskEquipmentFacilitiesAccountService riskEquipmentFacilitiesAccountService;

    @ApiOperation(value = "新增设备设施台账",notes = "参数：设备设施台账实体类")
    @PostMapping("/addRiskEFA")
    public ResultVo addRiskEFA(@ApiParam(name = "riskEquipmentFacilitiesAccount", required = true)
                               @RequestBody RiskEquipmentFacilitiesAccount riskEquipmentFacilitiesAccount){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        log.info("riskEquipmentFacilitiesAccount.getDateOfProduction()的值为："+riskEquipmentFacilitiesAccount.getDateOfProduction());
        riskEquipmentFacilitiesAccount.setDateOfProduction(DateUtils.parse(simpleDateFormat.format(riskEquipmentFacilitiesAccount.getDateOfProduction())+" 00:00:00"));
        riskEquipmentFacilitiesAccount.setOperationTime(DateUtils.parse(simpleDateFormat.format(riskEquipmentFacilitiesAccount.getOperationTime())+" 00:00:00"));
        riskEquipmentFacilitiesAccount.setLastOverhaulTime(DateUtils.parse(simpleDateFormat.format(riskEquipmentFacilitiesAccount.getLastOverhaulTime())+" 00:00:00"));
        riskEquipmentFacilitiesAccount.setNextOverhaulTime(DateUtils.parse(simpleDateFormat.format(riskEquipmentFacilitiesAccount.getNextOverhaulTime())+" 00:00:00"));

        //log.info("初始检测：riskEquipmentFacilitiesAccount.getNextOverhaulTime()的值为："+riskEquipmentFacilitiesAccount.getNextOverhaulTime());

        Date newDate = new Date();                                  //参数：当前日期
        Date lastOverhaulTime = riskEquipmentFacilitiesAccount.getLastOverhaulTime();     //参数：上次检测时间
        Date nextOverhaulTime = new Date();

        //log.info("初始检测：riskEquipmentFacilitiesAccount.getLastOverhaulTime()的值为："+riskEquipmentFacilitiesAccount.getLastOverhaulTime());

        int num = Integer.parseInt(riskEquipmentFacilitiesAccount.getPeriodicInspectionCycle());    //参数：定检周期，月/次

        nextOverhaulTime.setTime(lastOverhaulTime.getTime()+(30/num)*(1000 * 60 * 60 * 24));

        //log.info("nextOverhaulTime的值为："+nextOverhaulTime);
        //log.info("第二次检测：riskEquipmentFacilitiesAccount.getLastOverhaulTime()的值为："+riskEquipmentFacilitiesAccount.getLastOverhaulTime());

        riskEquipmentFacilitiesAccount.setNextOverhaulTime(nextOverhaulTime);       //换算之后的下次检测时间

        //log.info("第二次检测：riskEquipmentFacilitiesAccount.getNextOverhaulTime()的值为："+riskEquipmentFacilitiesAccount.getNextOverhaulTime());

        long diff = (nextOverhaulTime.getTime()-newDate.getTime())/ (1000 * 60 * 60 * 24);      //相差的天数

        //log.info("日期的差值diff的值为："+diff);

        if (diff >= 0 && diff <= 3){
            riskEquipmentFacilitiesAccount.setDetectionStatus("即将超期");
            //log.info("即将超期");
        }
        if (diff > 3){
            riskEquipmentFacilitiesAccount.setDetectionStatus("正常检测");
            //log.info("正常检测");
        }
        if (diff < 0){
            riskEquipmentFacilitiesAccount.setDetectionStatus("逾期未检测");
            //log.info("逾期未检测");
        }

        riskEquipmentFacilitiesAccount.setInsertTime(new Date());
        boolean save = riskEquipmentFacilitiesAccountService.save(riskEquipmentFacilitiesAccount);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation(value = "分页查询设备设施台账",notes = "参数：设备设施台账实体类")
    @PostMapping("/selectRiskEFAByPage")
    public ResultVo selectRiskEFAByPage(@ApiParam(name = "riskFireEquipmentAccountDto",required =false)
                                        @RequestBody RiskEquipmentFacilitiesAccountDto riskEquipmentFacilitiesAccountDto){

        log.info("riskEquipmentFacilitiesAccountDto.getPageSize()的值为："+riskEquipmentFacilitiesAccountDto.getPageSize());
        Page<RiskEquipmentFacilitiesAccount> pageList = riskEquipmentFacilitiesAccountService.selectRiskEFAByPage(riskEquipmentFacilitiesAccountDto);
        return ResultVo.success(pageList);
    }

    @ApiOperation(value = "查询一条设备设施台账",notes = "参数：设备设施台账实体类")
    @PostMapping("/selectRiskEFA")
    public ResultVo selectRiskEFA(@ApiParam(name = "riskEquipmentFacilitiesAccountDto",required =false)
                                  @RequestBody RiskEquipmentFacilitiesAccountDto riskEquipmentFacilitiesAccountDto){

        log.info("riskEquipmentFacilitiesAccountDto.getPageSize()的值为："+riskEquipmentFacilitiesAccountDto.getPageSize());
        RiskEquipmentFacilitiesAccount riskEquipmentFacilitiesAccount = riskEquipmentFacilitiesAccountService.selectRiskEFA(riskEquipmentFacilitiesAccountDto);
        return ResultVo.success(riskEquipmentFacilitiesAccount);
    }

    @ApiOperation(value = "更新设备设施台账",notes = "参数：设备设施实体类")
    @PostMapping("/updateRiskEFA")
    public ResultVo updateRiskEFA(@ApiParam(name = "riskEquipmentFacilitiesAccount", required = true)
                                  @RequestBody  RiskEquipmentFacilitiesAccount riskEquipmentFacilitiesAccount){

        boolean update = riskEquipmentFacilitiesAccountService.updateById(riskEquipmentFacilitiesAccount);
        if(update){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("批量删除设备设施台账")
    @PostMapping("/deleteRiskEFA")
    public ResultVo deleteRiskEFA(@ApiParam(name = "list", value = "权限ID", required = true)
                                  @RequestBody List<String> list){

        log.info("参数list.get(0)的值为："+list.get(0));
        boolean listDelete = riskEquipmentFacilitiesAccountService.removeByIds(list);
        if(listDelete){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    /*设备设施台账导出*/
    @ApiOperation(value = "设备设施台账导出", notes = "设备设施台账导出")
    @PostMapping(value = "/exportRiskEFAExcel")
    public void exportRiskEFAExcel(HttpServletResponse httpServletResponse) throws Exception{

        log.info("接收到excel导出请求");
        /*文件路径*/
        /*String fileName="C:\\Users\\HXS\\Desktop\\TestJavaCode\\"+"设备设施台账导出"+".xls";*/
        String fileName="设备设施台账导出"+".xls";

        /*创建Excel文件(Workbook)*/
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

        /*excel表格的列名称*/
        String[] headers = {
                "设备名称", "责任人", "注册证编号", "厂内编号",
                "上次检测时间", "下次检测时间", "定检周期", "使用证名称",
                "生产厂家", "规格型号", "运行场所", "使用证号", "出厂日期", "投入使用时间"
        };

        /*創建工作表(Sheet),设置表单和表单名*/
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("设备设施台账导出");

        /*生成表单的第一行，即表头*/
        HSSFRow hssfRow = hssfSheet.createRow(0);
        for (int i = 0;i<headers.length;i++){
            HSSFCell hssfCell = hssfRow.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            hssfCell.setCellValue(text);
        }

        /*获取到总数据对象，并存到list中*/
        List<RiskEquipmentFacilitiesAccount> listRiskEFAExcel = riskEquipmentFacilitiesAccountService.getRiskEFAExcel();

        log.info("listRiskEFAExcel的值为："+listRiskEFAExcel);

        /*循环*/
        for (int i =0 ;i < listRiskEFAExcel.size();i++){

            /*创建每一行单元格*/
            HSSFRow hssfRow1 = hssfSheet.createRow(i+1);

            /*从list中取出第i个总数据对象*/
            RiskEquipmentFacilitiesAccount riskEFAExcel = listRiskEFAExcel.get(i);

            hssfRow1.createCell(0).setCellValue(riskEFAExcel.getEquipmentName());                       //设备名称
            hssfRow1.createCell(1).setCellValue(riskEFAExcel.getPersonInCharge());                      //责任人
            hssfRow1.createCell(2).setCellValue(riskEFAExcel.getRegistrationCertificateNo());           //注册证编号
            hssfRow1.createCell(3).setCellValue(riskEFAExcel.getFactoryNumber());                       //厂内编号

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            hssfRow1.createCell(4).setCellValue(simpleDateFormat.format(riskEFAExcel.getLastOverhaulTime()));                    //上次检测时间
            hssfRow1.createCell(5).setCellValue(simpleDateFormat.format(riskEFAExcel.getNextOverhaulTime()));                    //下次检测时间
            hssfRow1.createCell(6).setCellValue(riskEFAExcel.getPeriodicInspectionCycle());                                      //定检周期
            hssfRow1.createCell(7).setCellValue(riskEFAExcel.getLicenseName());                                                  //使用证名称
            hssfRow1.createCell(8).setCellValue(riskEFAExcel.getManufacturer());                                                 //生产厂家
            hssfRow1.createCell(9).setCellValue(riskEFAExcel.getSpecificationAndModel());                                        //规格型号
            hssfRow1.createCell(10).setCellValue(riskEFAExcel.getOperationSite());                                               //运行场所
            hssfRow1.createCell(11).setCellValue(riskEFAExcel.getLicenseNo());                                                   //使用证号
            hssfRow1.createCell(12).setCellValue(simpleDateFormat.format(riskEFAExcel.getDateOfProduction()));                   //出厂日期
            hssfRow1.createCell(13).setCellValue(simpleDateFormat.format(riskEFAExcel.getOperationTime()));                      //投入使用时间

        }

        /*創建文件輸出流*//*
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        *//*保存Excel文件*//*
        hssfWorkbook.write(fileOutputStream);

        *//*關閉文件輸出流*//*
        fileOutputStream.close();*/

        log.info("已经准备好数据，即将发送至前端接收");
        httpServletResponse.setContentType("application/octet-stream");
        httpServletResponse.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
        httpServletResponse.flushBuffer();
        hssfWorkbook.write(httpServletResponse.getOutputStream());
    }

    //设备设施台账导入
    @ApiOperation(value = "设备设施台账导入", notes = "设备设施台账导入")
    @PostMapping(value = "/importRiskEFAExcel")
    public ResultVo importRiskEFAExcel(@RequestParam("file") MultipartFile file) throws Exception{

        log.info("file.getOriginalFilename()的值为："+file.getOriginalFilename());

        List<String[]> resultString = ExcelImportOperation.getDataFromExcel(file.getInputStream(),file.getOriginalFilename(), ExcelHeaders.getHeadersList(ExcelRowName.riskEquipmentFacilitiesAccount));

        List<RiskEquipmentFacilitiesAccount> riskEquipmentFacilitiesAccountList = new ArrayList<>();

        Date newDate = new Date();                                  //参数：当前日期


        resultString.forEach(exportArray->{

            RiskEquipmentFacilitiesAccount riskEquipmentFacilitiesAccount = new RiskEquipmentFacilitiesAccount();
            Date nextOverhaulTime = new Date();

            Date lastOverhaulTime = DateUtils.parse(exportArray[4]+" 00:00:00");     //参数：上次检测时间


            //log.info("初始检测：riskEquipmentFacilitiesAccount.getLastOverhaulTime()的值为："+DateUtils.parse(exportArray[4]+" 00:00:00"));

            int num = Integer.parseInt(exportArray[6]);    //参数：定检周期，月/次

            nextOverhaulTime.setTime(lastOverhaulTime.getTime()+(30/num)*(1000 * 60 * 60 * 24));

            //log.info("nextOverhaulTime的值为："+nextOverhaulTime);
            //log.info("第二次检测：riskEquipmentFacilitiesAccount.getLastOverhaulTime()的值为："+riskEquipmentFacilitiesAccount.getLastOverhaulTime());

            //riskEquipmentFacilitiesAccount.setNextOverhaulTime(nextOverhaulTime);       //换算之后的下次检测时间

            //log.info("第二次检测：riskEquipmentFacilitiesAccount.getNextOverhaulTime()的值为："+riskEquipmentFacilitiesAccount.getNextOverhaulTime());

            long diff = (nextOverhaulTime.getTime()-newDate.getTime())/ (1000 * 60 * 60 * 24);      //相差的天数

            //log.info("日期的差值diff的值为："+diff);

            if (diff >= 0 && diff <= 3){
                riskEquipmentFacilitiesAccount.setDetectionStatus("即将超期");
                //log.info("即将超期");
            }
            if (diff > 3){
                riskEquipmentFacilitiesAccount.setDetectionStatus("正常检测");
                //log.info("正常检测");
            }
            if (diff < 0){
                riskEquipmentFacilitiesAccount.setDetectionStatus("逾期未检测");
                //log.info("逾期未检测");
            }


            riskEquipmentFacilitiesAccount.setRefaId(RandomId.getUUID());
            riskEquipmentFacilitiesAccount.setEquipmentName(exportArray[0]);
            riskEquipmentFacilitiesAccount.setPersonInCharge(exportArray[1]);
            riskEquipmentFacilitiesAccount.setRegistrationCertificateNo(exportArray[2]);
            riskEquipmentFacilitiesAccount.setFactoryNumber(exportArray[3]);
            riskEquipmentFacilitiesAccount.setLastOverhaulTime(DateUtils.parse(exportArray[4]+" 00:00:00"));
            riskEquipmentFacilitiesAccount.setNextOverhaulTime(nextOverhaulTime);
            riskEquipmentFacilitiesAccount.setPeriodicInspectionCycle(exportArray[6]);
            riskEquipmentFacilitiesAccount.setLicenseName(exportArray[7]);
            riskEquipmentFacilitiesAccount.setManufacturer(exportArray[8]);
            riskEquipmentFacilitiesAccount.setSpecificationAndModel(exportArray[9]);
            riskEquipmentFacilitiesAccount.setOperationSite(exportArray[10]);
            riskEquipmentFacilitiesAccount.setLicenseNo(exportArray[11]);
            riskEquipmentFacilitiesAccount.setDateOfProduction(DateUtils.parse(exportArray[12]+" 00:00:00"));
            riskEquipmentFacilitiesAccount.setOperationTime(DateUtils.parse(exportArray[13]+" 00:00:00"));
            riskEquipmentFacilitiesAccount.setInsertTime(new Date());
            riskEquipmentFacilitiesAccountList.add(riskEquipmentFacilitiesAccount);
        });

        riskEquipmentFacilitiesAccountService.saveAllBatch(riskEquipmentFacilitiesAccountList);
        return ResultVo.success();
    }

    @ApiOperation(value = "批量设置责任人",notes = "参数：设备设施实体类")
    @PostMapping("/setRiskEFAPersonInCharge")
    public ResultVo setRiskEFAPersonInCharge(@ApiParam(name = "riskEquipmentFacilitiesAccount", required = true)
                                  @RequestBody  List<RiskEquipmentFacilitiesAccount> riskEquipmentFacilitiesAccountList){

        boolean update = riskEquipmentFacilitiesAccountService.setRiskEFAPersonInCharge(riskEquipmentFacilitiesAccountList);
        if(update){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }
}
