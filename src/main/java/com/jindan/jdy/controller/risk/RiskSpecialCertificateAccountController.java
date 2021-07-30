package com.jindan.jdy.controller.risk;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.RiskSpecialCertificateAccountDto;
import com.jindan.jdy.common.excelrowname.ExcelRowName;
import com.jindan.jdy.common.pojo.RiskSpecialCertificateAccount;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.risk.RiskSpecialCertificateAccountService;
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
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liangfang
 * @since 2021-07-02
 */
@Slf4j
@RestController
@RequestMapping("/riskSpecialCertificateAccount")
@Api(tags ="特种证书台账")
public class RiskSpecialCertificateAccountController {

    @Autowired
    private RiskSpecialCertificateAccountService riskSpecialCertificateAccountService;

    @ApiOperation(value = "新增特种证书台账",notes = "参数：特种证书台账实体类")
    @PostMapping("/addRiskSCA")
    public ResultVo addRiskSCA(@ApiParam(name = "riskSpecialCertificateAccount", required = true)
                                                  @RequestBody RiskSpecialCertificateAccount riskSpecialCertificateAccount){

        riskSpecialCertificateAccount.setInsertTime(new Date());
        boolean save = riskSpecialCertificateAccountService.save(riskSpecialCertificateAccount);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation(value = "分页查询特种证书台账",notes = "参数：特种证书台账实体类")
    @PostMapping("/selectRiskSCAByPage")
    public ResultVo selectRiskSCAByPage(@ApiParam(name = "riskSpecialCertificateAccountDto",required =false)
                                                       @RequestBody RiskSpecialCertificateAccountDto riskSpecialCertificateAccountDto){

        log.info("riskSpecialCertificateAccountDto.getPageSize()的值为："+riskSpecialCertificateAccountDto.getPageSize());
        Page<RiskSpecialCertificateAccount> pageList = riskSpecialCertificateAccountService.selectRiskSCAByPage(riskSpecialCertificateAccountDto);
        return ResultVo.success(pageList);
    }

    @ApiOperation(value = "查询一条特种证书台账",notes = "参数：特种证书台账实体类")
    @PostMapping("/selectRiskSCA")
    public ResultVo selectRiskSCA(@ApiParam(name = "riskSpecialCertificateAccountDto",required =false)
                                                              @RequestBody RiskSpecialCertificateAccountDto riskSpecialCertificateAccountDto){

        log.info("riskSpecialCertificateAccountDto.getPageSize()的值为："+riskSpecialCertificateAccountDto.getPageSize());
        RiskSpecialCertificateAccount riskSpecialCertificateAccount = riskSpecialCertificateAccountService.selectRiskSCA(riskSpecialCertificateAccountDto);
        return ResultVo.success(riskSpecialCertificateAccount);
    }

    @ApiOperation(value = "更新特种证书台账",notes = "参数：特种证书实体类")
    @PostMapping("/updateRiskSCA")
    public ResultVo updateRiskSCA(@ApiParam(name = "riskSpecialCertificateAccount", required = true)
                                                  @RequestBody  RiskSpecialCertificateAccount riskSpecialCertificateAccount){

        boolean update = riskSpecialCertificateAccountService.updateById(riskSpecialCertificateAccount);
        if(update){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("批量删除特种证书台账")
    @PostMapping("/deleteRiskSCA")
    public ResultVo deleteRiskSCA(@ApiParam(name = "list", value = "权限ID", required = true)
                                        @RequestBody  List<String> list){

        log.info("参数list.get(0)的值为："+list.get(0));
        boolean listDelete = riskSpecialCertificateAccountService.removeByIds(list);
        if(listDelete){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    /*特种证书台账导出*/
    @ApiOperation(value = "特种证书台账导出", notes = "特种证书台账导出")
    @PostMapping(value = "/exportRiskSCAExcel")
    public void exportRiskSCAExcel(HttpServletResponse httpServletResponse) throws Exception{

        log.info("接收到excel导出请求");
        /*文件路径*/
        /*String fileName="C:\\Users\\HXS\\Desktop\\TestJavaCode\\"+"特种证书台账导出"+".xls";*/
        String fileName="特种证书台账导出"+".xls";

        /*创建Excel文件(Workbook)*/
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

        /*excel表格的列名称*/
        String[] headers = {
                "证书编号", "持证人", "作业类别", "准操项目",
                "初次领证日期", "领证日期", "有效期", "复审期限",
                "复审日期", "发证单位", "检测状态"
        };

        /*創建工作表(Sheet),设置表单和表单名*/
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("特种证书台账导出");

        /*生成表单的第一行，即表头*/
        HSSFRow hssfRow = hssfSheet.createRow(0);
        for (int i = 0;i<headers.length;i++){
            HSSFCell hssfCell = hssfRow.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            hssfCell.setCellValue(text);
        }

        /*获取到特种证书台账总数据对象，并存到list中*/
        List<RiskSpecialCertificateAccount> listRiskSCAExcel = riskSpecialCertificateAccountService.getRiskSCAExcel();

        log.info("listRiskSCAExcel的值为："+listRiskSCAExcel);

        /*循环*/
        for (int i =0 ;i < listRiskSCAExcel.size();i++){

            /*创建每一行单元格*/
            HSSFRow hssfRow1 = hssfSheet.createRow(i+1);

            /*从list中取出第i个特种证书台账总数据对象*/
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
    }

    //特种证书台账导入
    @ApiOperation(value = "特种证书台账导入", notes = "特种证书台账导入")
    @PostMapping(value = "/importRiskSCAExcel")
    public ResultVo importRiskSCAExcel(@RequestParam("file") MultipartFile file) throws Exception{

        log.info("file.getOriginalFilename()的值为："+file.getOriginalFilename());

        List<String[]> resultString = ExcelImportOperation.getDataFromExcel(file.getInputStream(),file.getOriginalFilename(), ExcelHeaders.getHeadersList(ExcelRowName.riskSpecialCertificateAccount));

        List<RiskSpecialCertificateAccount> riskSpecialCertificateAccountList = new ArrayList<>();

        resultString.forEach(exportArray->{

            RiskSpecialCertificateAccount riskSpecialCertificateAccount = new RiskSpecialCertificateAccount();
            riskSpecialCertificateAccount.setRscaId(RandomId.getUUID());
            riskSpecialCertificateAccount.setCertificateNo(exportArray[0]);
            riskSpecialCertificateAccount.setCertificateHolder(exportArray[1]);
            riskSpecialCertificateAccount.setJobCategory(exportArray[2]);
            riskSpecialCertificateAccount.setStandardOperationItems(exportArray[3]);
            riskSpecialCertificateAccount.setIssueDate(DateUtils.parse(exportArray[4]+" 00:00:00"));
            riskSpecialCertificateAccount.setDateOfIssue(DateUtils.parse(exportArray[5]+" 00:00:00"));
            riskSpecialCertificateAccount.setTermOfValidity(DateUtils.parse(exportArray[6]+" 00:00:00"));
            riskSpecialCertificateAccount.setReviewPeriod(DateUtils.parse(exportArray[7]+" 00:00:00"));
            riskSpecialCertificateAccount.setReviewDate(DateUtils.parse(exportArray[8]+" 00:00:00"));
            riskSpecialCertificateAccount.setCertificateIssuingUnit(exportArray[9]);
            riskSpecialCertificateAccount.setInsertTime(new Date());
            riskSpecialCertificateAccountList.add(riskSpecialCertificateAccount);
        });

        riskSpecialCertificateAccountService.saveAllBatch(riskSpecialCertificateAccountList);
        return ResultVo.success();
    }
}
