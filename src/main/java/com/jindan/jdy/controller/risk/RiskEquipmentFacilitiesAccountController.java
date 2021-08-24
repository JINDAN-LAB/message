package com.jindan.jdy.controller.risk;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.RiskEquipmentFacilitiesAccountDto;
import com.jindan.jdy.common.pojo.RiskEquipmentFacilitiesAccount;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.risk.RiskEquipmentFacilitiesAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
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

        riskEquipmentFacilitiesAccount.setInsertTime(new Date());
        boolean save = riskEquipmentFacilitiesAccountService.save(riskEquipmentFacilitiesAccount);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    /*@ApiOperation(value = "分页查询设备设施台账",notes = "参数：设备设施台账实体类")
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
    }*/

    /*设备设施台账导出*//*
    @ApiOperation(value = "设备设施台账导出", notes = "设备设施台账导出")
    @PostMapping(value = "/exportRiskEFAExcel")
    public void exportRiskEFAExcel(HttpServletResponse httpServletResponse) throws Exception{

        log.info("接收到excel导出请求");
        *//*文件路径*//*
        *//*String fileName="C:\\Users\\HXS\\Desktop\\TestJavaCode\\"+"设备设施台账导出"+".xls";*//*
        String fileName="设备设施台账导出"+".xls";

        *//*创建Excel文件(Workbook)*//*
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

        *//*excel表格的列名称*//*
        String[] headers = {
                "证书编号", "持证人", "作业类别", "准操项目",
                "初次领证日期", "领证日期", "有效期", "复审期限",
                "复审日期", "发证单位", "检测状态"
        };

        *//*創建工作表(Sheet),设置表单和表单名*//*
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("设备设施台账导出");

        *//*生成表单的第一行，即表头*//*
        HSSFRow hssfRow = hssfSheet.createRow(0);
        for (int i = 0;i<headers.length;i++){
            HSSFCell hssfCell = hssfRow.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            hssfCell.setCellValue(text);
        }

        *//*获取到特种证书台账总数据对象，并存到list中*//*
        List<RiskSpecialCertificateAccount> listRiskSCAExcel = riskSpecialCertificateAccountService.getRiskSCAExcel();

        log.info("listRiskSCAExcel的值为："+listRiskSCAExcel);

        *//*循环*//*
        for (int i =0 ;i < listRiskSCAExcel.size();i++){

            *//*创建每一行单元格*//*
            HSSFRow hssfRow1 = hssfSheet.createRow(i+1);

            *//*从list中取出第i个特种证书台账总数据对象*//*
            RiskSpecialCertificateAccount riskSCAExcel = listRiskSCAExcel.get(i);

            hssfRow1.createCell(0).setCellValue(riskSCAExcel.getCertificateNo());                       //证书编号
            hssfRow1.createCell(1).setCellValue(riskSCAExcel.getCertificateHolder());                   //持证人
            hssfRow1.createCell(2).setCellValue(riskSCAExcel.getJobCategory());                         //作业类型
            hssfRow1.createCell(3).setCellValue(riskSCAExcel.getStandardOperationItems());              //准操项目

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            hssfRow1.createCell(4).setCellValue(simpleDateFormat.format(riskSCAExcel.getIssueDate()));                           //初次领证日期
            hssfRow1.createCell(5).setCellValue(simpleDateFormat.format(riskSCAExcel.getDateOfIssue()));                         //领证日期
            hssfRow1.createCell(6).setCellValue(simpleDateFormat.format(riskSCAExcel.getTermOfValidity()));                      //有效期
            hssfRow1.createCell(7).setCellValue(simpleDateFormat.format(riskSCAExcel.getReviewPeriod()));                        //复审期限
            hssfRow1.createCell(8).setCellValue(simpleDateFormat.format(riskSCAExcel.getReviewDate()));                          //复审日期
            hssfRow1.createCell(9).setCellValue(riskSCAExcel.getCertificateIssuingUnit());              //发证单位

            Date newDate = new Date();                                  //参数：当前日期
            Date termOfValidity = riskSCAExcel.getTermOfValidity();     //参数：有效期
            //Date reviewPeriod = riskSCAExcel.getReviewPeriod();         //参数：复审期限

            log.info("newDate的值为："+newDate);

            int isDetectionStatusOne = newDate.compareTo(termOfValidity);
            //int isDetectionStatusTwo = newDate.compareTo(reviewPeriod);

            switch (isDetectionStatusOne){
                case 1:
                    hssfRow1.createCell(10).setCellValue("超期未验审"); //检测状态
                    break;
                case 0:
                    hssfRow1.createCell(10).setCellValue("正常"); //检测状态
                    break;
                case -1:
                    hssfRow1.createCell(10).setCellValue("正常"); //检测状态
                    break;
                default:
                    hssfRow1.createCell(10).setCellValue("超期未验审"); //检测状态
            }

        }

        log.info("已经准备好数据，即将发送至前端接收");
        httpServletResponse.setContentType("application/octet-stream");
        httpServletResponse.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
        httpServletResponse.flushBuffer();
        hssfWorkbook.write(httpServletResponse.getOutputStream());
    }*/
}
