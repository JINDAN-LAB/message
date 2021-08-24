package com.jindan.jdy.controller.risk;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.RiskFireEquipmentAccountDto;
import com.jindan.jdy.common.excelrowname.ExcelRowName;
import com.jindan.jdy.common.pojo.RiskFireEquipmentAccount;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.risk.RiskFireEquipmentAccountService;
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
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liangfang
 * @since 2021-07-16
 */
@Slf4j
@RestController
@RequestMapping("/riskFireEquipmentAccount")
@Api(tags ="消防设备台账")
public class RiskFireEquipmentAccountController {

    @Autowired
    private RiskFireEquipmentAccountService riskFireEquipmentAccountService;

    @ApiOperation(value = "新增消防设备台账",notes = "参数：消防设备台账实体类")
    @PostMapping("/addRiskFEA")
    public ResultVo addRiskFEA(@ApiParam(name = "riskFireEquipmentAccount", required = true)
                                   @RequestBody RiskFireEquipmentAccount riskFireEquipmentAccount){

        riskFireEquipmentAccount.setInsertTime(new Date());
        boolean save = riskFireEquipmentAccountService.save(riskFireEquipmentAccount);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation(value = "分页查询消防设备台账",notes = "参数：消防设备台账实体类")
    @PostMapping("/selectRiskFEAByPage")
    public ResultVo selectRiskFEAByPage(@ApiParam(name = "riskFireEquipmentAccountDto",required =false)
                                            @RequestBody RiskFireEquipmentAccountDto riskFireEquipmentAccountDto){

        log.info("riskSpecialCertificateAccountDto.getPageSize()的值为："+riskFireEquipmentAccountDto.getPageSize());
        Page<RiskFireEquipmentAccount> pageList = riskFireEquipmentAccountService.selectRiskFEAByPage(riskFireEquipmentAccountDto);
        return ResultVo.success(pageList);
    }

    @ApiOperation(value = "查询一条消防设备台账",notes = "参数：消防设备台账实体类")
    @PostMapping("/selectRiskFEA")
    public ResultVo selectRiskFEA(@ApiParam(name = "riskFireEquipmentAccountDto",required =false)
                                  @RequestBody RiskFireEquipmentAccountDto riskFireEquipmentAccountDto){

        log.info("riskSpecialCertificateAccountDto.getPageSize()的值为："+riskFireEquipmentAccountDto.getPageSize());
        RiskFireEquipmentAccount riskFireEquipmentAccount = riskFireEquipmentAccountService.selectRiskFEA(riskFireEquipmentAccountDto);
        return ResultVo.success(riskFireEquipmentAccount);
    }

    @ApiOperation(value = "更新消防设备台账",notes = "参数：消防设备实体类")
    @PostMapping("/updateRiskFEA")
    public ResultVo updateRiskFEA(@ApiParam(name = "riskFireEquipmentAccount", required = true)
                                  @RequestBody  RiskFireEquipmentAccount riskFireEquipmentAccount){

        boolean update = riskFireEquipmentAccountService.updateById(riskFireEquipmentAccount);
        if(update){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("批量删除消防设备台账")
    @PostMapping("/deleteRiskFEA")
    public ResultVo deleteRiskFEA(@ApiParam(name = "list", value = "权限ID", required = true)
                                  @RequestBody List<String> list){

        log.info("参数list.get(0)的值为："+list.get(0));
        boolean listDelete = riskFireEquipmentAccountService.removeByIds(list);
        if(listDelete){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    /*消防设备台账导出*/
    @ApiOperation(value = "消防设备台账导出", notes = "消防设备台账导出")
    @PostMapping(value = "/exportRiskFEAExcel")
    public void exportRiskFEAExcel(HttpServletResponse httpServletResponse) throws Exception{

        log.info("接收到excel导出请求");
        /*文件路径*/
        /*String fileName="C:\\Users\\HXS\\Desktop\\TestJavaCode\\"+"消防设备台账导出"+".xls";*/
        String fileName="消防设备台账导出"+".xls";

        /*创建Excel文件(Workbook)*/
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

        /*excel表格的列名称*/
        String[] headers = {
                "设备名称", "规格型号", "生产厂家", "出厂日期",
                "入场日期", "过期时间", "运行场所", "场内编号",
                "负责人姓名", "负责人手机号", "上次检测时间",
                "下次检查时间"
        };

        /*創建工作表(Sheet),设置表单和表单名*/
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("消防设备台账导出");

        /*生成表单的第一行，即表头*/
        HSSFRow hssfRow = hssfSheet.createRow(0);
        for (int i = 0;i<headers.length;i++){
            HSSFCell hssfCell = hssfRow.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            hssfCell.setCellValue(text);
        }

        /*获取到消防设备台账总数据对象，并存到list中*/
        List<RiskFireEquipmentAccount> listRiskFEAExcel = riskFireEquipmentAccountService.getRiskFEAExcel();

        log.info("listRiskFEAExcel的值为："+listRiskFEAExcel);

        /*循环*/
        for (int i =0 ;i < listRiskFEAExcel.size();i++){

                /*创建每一行单元格*/
            HSSFRow hssfRow1 = hssfSheet.createRow(i+1);

            /*从list中取出第i个特种证书台账总数据对象*/
            RiskFireEquipmentAccount riskFEAExcel = listRiskFEAExcel.get(i);

            hssfRow1.createCell(0).setCellValue(riskFEAExcel.getEquipmentName());                                                //设备名称
            hssfRow1.createCell(1).setCellValue(riskFEAExcel.getSpecificationAndModel());                                        //规格型号
            hssfRow1.createCell(2).setCellValue(riskFEAExcel.getManufacturer());                                                 //生产厂家

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            hssfRow1.createCell(3).setCellValue(simpleDateFormat.format(riskFEAExcel.getDateOfProduction()));                    //出厂日期
            hssfRow1.createCell(4).setCellValue(simpleDateFormat.format(riskFEAExcel.getOperationTime()));                       //入场日期
            hssfRow1.createCell(5).setCellValue(simpleDateFormat.format(riskFEAExcel.getExpirationTime()));                      //过期时间
            hssfRow1.createCell(6).setCellValue(riskFEAExcel.getOperationSite());                                                //运行场所
            hssfRow1.createCell(7).setCellValue(riskFEAExcel.getSiteNumber());                                                   //场内编号
            hssfRow1.createCell(8).setCellValue(riskFEAExcel.getPersonInCharge());                                               //负责人姓名
            hssfRow1.createCell(9).setCellValue(riskFEAExcel.getPhone());                                                        //负责人手机号
            hssfRow1.createCell(10).setCellValue(simpleDateFormat.format(riskFEAExcel.getExpirationTime()));                     //上次检修时间
            hssfRow1.createCell(11).setCellValue(simpleDateFormat.format(riskFEAExcel.getExpirationTime()));                     //下次检修时间

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

    //消防设备台账导入
    @ApiOperation(value = "消防设备台账导入", notes = "消防设备台账导入")
    @PostMapping(value = "/importRiskFEAExcel")
    public ResultVo importRiskFEAExcel(@RequestParam("file") MultipartFile file) throws Exception{

        log.info("file.getOriginalFilename()的值为："+file.getOriginalFilename());

        List<String[]> resultString = ExcelImportOperation.getDataFromExcel(file.getInputStream(),file.getOriginalFilename(), ExcelHeaders.getHeadersList(ExcelRowName.riskFireEquipmentAccount));

        log.info("resultString.size()的值为："+resultString.size());
        List<RiskFireEquipmentAccount> riskFireEquipmentAccountList = new ArrayList<>();

        resultString.forEach(exportArray->{

            RiskFireEquipmentAccount riskFireEquipmentAccount = new RiskFireEquipmentAccount();
            riskFireEquipmentAccount.setRfeaId(RandomId.getUUID());
            riskFireEquipmentAccount.setEquipmentName(exportArray[0]);
            riskFireEquipmentAccount.setSpecificationAndModel(exportArray[1]);
            riskFireEquipmentAccount.setManufacturer(exportArray[2]);
            riskFireEquipmentAccount.setDateOfProduction(DateUtils.parse(exportArray[3]+" 00:00:00"));
            riskFireEquipmentAccount.setOperationTime(DateUtils.parse(exportArray[4]+" 00:00:00"));
            riskFireEquipmentAccount.setExpirationTime(DateUtils.parse(exportArray[5]+" 00:00:00"));
            riskFireEquipmentAccount.setOperationSite(exportArray[6]);
            riskFireEquipmentAccount.setSiteNumber(exportArray[7]);
            riskFireEquipmentAccount.setDepartment(exportArray[8]);
            riskFireEquipmentAccount.setPersonInCharge(exportArray[9]);
            riskFireEquipmentAccount.setPhone(exportArray[10]);
            riskFireEquipmentAccount.setLastOverhaulTime(DateUtils.parse(exportArray[11]+" 00:00:00"));
            riskFireEquipmentAccount.setNextOverhaulTime(DateUtils.parse(exportArray[12]+" 00:00:00"));
            riskFireEquipmentAccount.setInsertTime(new Date());
            riskFireEquipmentAccountList.add(riskFireEquipmentAccount);
        });

        riskFireEquipmentAccountService.saveAllBatch(riskFireEquipmentAccountList);
        return ResultVo.success();
    }
}
