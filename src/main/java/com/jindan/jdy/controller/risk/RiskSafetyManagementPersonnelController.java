package com.jindan.jdy.controller.risk;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.RiskSafetyManagementPersonnelDto;
import com.jindan.jdy.common.excelrowname.ExcelRowName;
import com.jindan.jdy.common.pojo.RiskSafetyManagementPersonnel;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.risk.RiskSafetyManagementPersonnelService;
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
 * @since 2021-08-06
 */
@Slf4j
@RestController
@RequestMapping("/riskSafetyManagementPersonnel")
@Api(tags ="安全管理人员台账")
public class RiskSafetyManagementPersonnelController {

    @Autowired
    private RiskSafetyManagementPersonnelService riskSafetyManagementPersonnelService;

    @ApiOperation(value = "新增安全管理人员台账",notes = "参数：安全管理人员实体类")
    @PostMapping("/addRiskSMP")
    public ResultVo addRiskSMP(@ApiParam(name = "riskSafetyManagementPersonnel", required = true)
                               @RequestBody RiskSafetyManagementPersonnel riskSafetyManagementPersonnel){

        riskSafetyManagementPersonnel.setInsertTime(new Date());
        boolean save = riskSafetyManagementPersonnelService.save(riskSafetyManagementPersonnel);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation(value = "分页查询安全管理人员台账",notes = "参数：安全管理人员台账实体类")
    @PostMapping("/selectRiskSMPByPage")
    public ResultVo selectRiskSMPByPage(@ApiParam(name = "riskSafetyManagementPersonnelDto",required =false)
                                        @RequestBody RiskSafetyManagementPersonnelDto riskSafetyManagementPersonnelDto){

        log.info("riskSafetyManagementPersonnelDto.getPageSize()的值为："+riskSafetyManagementPersonnelDto.getPageSize());
        Page<RiskSafetyManagementPersonnel> pageList = riskSafetyManagementPersonnelService.selectRiskSMPByPage(riskSafetyManagementPersonnelDto);
        return ResultVo.success(pageList);
    }

    @ApiOperation(value = "查询一条安全管理人员台账",notes = "参数：安全管理人员台账实体类")
    @PostMapping("/selectRiskSMP")
    public ResultVo selectRiskSMP(@ApiParam(name = "riskSafetyManagementPersonnelDto",required =false)
                                  @RequestBody RiskSafetyManagementPersonnelDto riskSafetyManagementPersonnelDto){

        log.info("riskSafetyManagementPersonnelDto.getPageSize()的值为："+riskSafetyManagementPersonnelDto.getPageSize());
        RiskSafetyManagementPersonnel riskSafetyManagementPersonnel = riskSafetyManagementPersonnelService.selectRiskSMP(riskSafetyManagementPersonnelDto);
        return ResultVo.success(riskSafetyManagementPersonnel);
    }

    @ApiOperation(value = "更新安全管理人员台账",notes = "参数：安全管理人员实体类")
    @PostMapping("/updateRiskSMP")
    public ResultVo updateRiskSMP(@ApiParam(name = "riskSafetyManagementPersonnel", required = true)
                                  @RequestBody  RiskSafetyManagementPersonnel riskSafetyManagementPersonnel){

        boolean update = riskSafetyManagementPersonnelService.updateById(riskSafetyManagementPersonnel);
        if(update){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("批量删除特种证书台账")
    @PostMapping("/deleteRiskSMP")
    public ResultVo deleteRiskSMP(@ApiParam(name = "list", value = "权限ID", required = true)
                                  @RequestBody List<String> list){

        log.info("参数list.get(0)的值为："+list.get(0));
        boolean listDelete = riskSafetyManagementPersonnelService.removeByIds(list);
        if(listDelete){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    /*安全管理人员台账导出*/
    @ApiOperation(value = "安全管理人员台账导出", notes = "安全管理人员台账导出")
    @PostMapping(value = "/exportRiskSMPExcel")
    public void exportRiskSMPExcel(HttpServletResponse httpServletResponse) throws Exception{

        log.info("接收到excel导出请求");
        /*文件路径*/
        /*String fileName="C:\\Users\\HXS\\Desktop\\TestJavaCode\\"+"安全管理人员台账导出"+".xls";*/
        String fileName="安全管理人员台账导出"+".xls";

        /*创建Excel文件(Workbook)*/
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

        /*excel表格的列名称*/
        String[] headers = {
                "姓名", "行业类别", "发证机关", "初领日期",
                "复训日期", "证号", "有效期限", "手机号"
        };

        /*創建工作表(Sheet),设置表单和表单名*/
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("安全管理人员台账导出");

        /*生成表单的第一行，即表头*/
        HSSFRow hssfRow = hssfSheet.createRow(0);
        for (int i = 0;i<headers.length;i++){
            HSSFCell hssfCell = hssfRow.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            hssfCell.setCellValue(text);
        }

        /*获取到总数据对象，并存到list中*/
        List<RiskSafetyManagementPersonnel> listRiskSMPExcel = riskSafetyManagementPersonnelService.getRiskSMPExcel();

        log.info("listRiskSMPExcel的值为："+listRiskSMPExcel);

        /*循环*/
        for (int i =0 ;i < listRiskSMPExcel.size();i++){

            /*创建每一行单元格*/
            HSSFRow hssfRow1 = hssfSheet.createRow(i+1);

            /*从list中取出第i个总数据对象*/
            RiskSafetyManagementPersonnel riskSMPExcel = listRiskSMPExcel.get(i);

            hssfRow1.createCell(0).setCellValue(riskSMPExcel.getSafetyManagementPersonnel());                                    //安全管理人员姓名
            hssfRow1.createCell(1).setCellValue(riskSMPExcel.getIndustryCategory());                                             //行业类别
            hssfRow1.createCell(2).setCellValue(riskSMPExcel.getIssuingAuthority());                                             //发证机关

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            hssfRow1.createCell(3).setCellValue(simpleDateFormat.format(riskSMPExcel.getEffectiveStartTime()));                  //初领日期
            hssfRow1.createCell(4).setCellValue(simpleDateFormat.format(riskSMPExcel.getRetrainingTime()));                      //复训日期
            hssfRow1.createCell(5).setCellValue(riskSMPExcel.getCertificateNo());                                                //证号
            hssfRow1.createCell(6).setCellValue(simpleDateFormat.format(riskSMPExcel.getEffectiveEndTime()));                    //有效期限
            hssfRow1.createCell(7).setCellValue(riskSMPExcel.getPhone());                               //手机号

        }

        log.info("已经准备好数据，即将发送至前端接收");
        httpServletResponse.setContentType("application/octet-stream");
        httpServletResponse.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
        httpServletResponse.flushBuffer();
        hssfWorkbook.write(httpServletResponse.getOutputStream());
    }

    //安全管理人员台账导入
    @ApiOperation(value = "安全管理人员台账导入", notes = "安全管理人员台账导入")
    @PostMapping(value = "/importRiskSMPExcel")
    public ResultVo importRiskSMPExcel(@RequestParam("file") MultipartFile file) throws Exception{

        log.info("file.getOriginalFilename()的值为："+file.getOriginalFilename());

        List<String[]> resultString = ExcelImportOperation.getDataFromExcel(file.getInputStream(),file.getOriginalFilename(), ExcelHeaders.getHeadersList(ExcelRowName.riskSafetyManagementPersonnel));

        List<RiskSafetyManagementPersonnel> riskSafetyManagementPersonnelList = new ArrayList<>();

        resultString.forEach(exportArray->{

            RiskSafetyManagementPersonnel riskSafetyManagementPersonnel = new RiskSafetyManagementPersonnel();
            riskSafetyManagementPersonnel.setRsmpId(RandomId.getUUID());
            riskSafetyManagementPersonnel.setSafetyManagementPersonnel(exportArray[0]);
            riskSafetyManagementPersonnel.setIndustryCategory(exportArray[1]);
            riskSafetyManagementPersonnel.setIssuingAuthority(exportArray[2]);
            riskSafetyManagementPersonnel.setEffectiveStartTime(DateUtils.parse(exportArray[3]+" 00:00:00"));
            riskSafetyManagementPersonnel.setRetrainingTime(DateUtils.parse(exportArray[4]+" 00:00:00"));
            riskSafetyManagementPersonnel.setCertificateNo(exportArray[5]);
            riskSafetyManagementPersonnel.setEffectiveEndTime(DateUtils.parse(exportArray[6]+" 00:00:00"));
            riskSafetyManagementPersonnel.setPhone(exportArray[7]);
            riskSafetyManagementPersonnel.setInsertTime(new Date());
            riskSafetyManagementPersonnelList.add(riskSafetyManagementPersonnel);
        });

        riskSafetyManagementPersonnelService.saveAllBatch(riskSafetyManagementPersonnelList);
        return ResultVo.success();
    }
}
