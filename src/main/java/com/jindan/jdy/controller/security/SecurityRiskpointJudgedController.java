package com.jindan.jdy.controller.security;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.SecurityPatrolClassificationDto;
import com.jindan.jdy.common.dto.SecurityRiskpointJudgedDto;
import com.jindan.jdy.common.pojo.SecurityPatrolClassification;
import com.jindan.jdy.common.pojo.SecurityRiskcontent;
import com.jindan.jdy.common.pojo.SecurityRiskpointJudged;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.security.SecurityPatrolClassificationService;
import com.jindan.jdy.service.security.SecurityRiskcontentService;
import com.jindan.jdy.service.security.SecurityRiskpointJudgedService;
import com.jindan.jdy.utils.RandomId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
 * @since 2021-10-12
 */
@Slf4j
@RestController
@RequestMapping("/securityRiskpointJudged")
@Api(tags ="风险点研判管理")
public class SecurityRiskpointJudgedController {

    @Autowired
    private SecurityRiskpointJudgedService securityRiskpointJudgedService;

    @Autowired
    private SecurityPatrolClassificationService securityPatrolClassificationService;

    @Autowired
    private SecurityRiskcontentService securityRiskcontentService;

    @ApiOperation(value = "新增风险点研判",notes = "参数：风险点Dto类")
    @PostMapping("/addRiskpointJudged")
    public ResultVo addRiskpointJudged(@ApiParam(name = "securityRiskpointJudgedDto", required = true)
                               @RequestBody SecurityRiskpointJudgedDto securityRiskpointJudgedDto){
        log.info("securityRiskpointJudgedDto的值为："+securityRiskpointJudgedDto);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        /*风险点id*/
        String riskpointId = RandomId.getUUID();
        /*巡检分类id*/
        String patrolClassificationId = RandomId.getUUID();

        SecurityRiskpointJudged securityRiskpointJudged = new SecurityRiskpointJudged();
        BeanUtils.copyProperties(securityRiskpointJudgedDto,  securityRiskpointJudged);
        /*自己的id*/
        securityRiskpointJudged.setRiskpointId(riskpointId);
        securityRiskpointJudged.setLecDanger(securityRiskpointJudgedDto.getDangerDegree()+"("+securityRiskpointJudgedDto.getLecDanger()+")");
        securityRiskpointJudged.setLsRiskValue(securityRiskpointJudgedDto.getDangerDegree()+"("+securityRiskpointJudgedDto.getLsRiskValue()+")");
        securityRiskpointJudged.setJudgmentTime(new Date());
        securityRiskpointJudged.setInsertTime(new Date());
        boolean save1 = securityRiskpointJudgedService.save(securityRiskpointJudged);

        SecurityPatrolClassification securityPatrolClassification = new SecurityPatrolClassification();
        /*自己的id*/
        securityPatrolClassification.setPatrolClassificationId(patrolClassificationId);
        /*风险点的id*/
        securityPatrolClassification.setRiskpointId(riskpointId);
        securityPatrolClassification.setControllevel("巡检人员");
        securityPatrolClassification.setJudgmentTime(new Date());
        securityPatrolClassification.setInsertTime(new Date());
        boolean save2 = securityPatrolClassificationService.save(securityPatrolClassification);

        List<SecurityRiskcontent> securityRiskcontentList = securityRiskpointJudgedDto.getSecurityRiskcontentList();
        for (int i = 0;i<securityRiskcontentList.size();i++){
            SecurityRiskcontent securityRiskcontent = securityRiskcontentList.get(i);
            /*风险点id*/
            securityRiskcontent.setRiskpointId(riskpointId);
            /*巡检类别id*/
            securityRiskcontent.setPatrolClassificationId(patrolClassificationId);
            securityRiskcontent.setJudgmentTime(new Date());
            securityRiskcontent.setInsertTime(new Date());
        }
        boolean save3 = securityRiskcontentService.saveBatch(securityRiskcontentList);

        if(save1 && save2 && save3){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation(value = "分页查询风险点研判",notes = "参数：风险点Dto类")
    @PostMapping("/selectRiskpointJudgedByPage")
    public ResultVo selectRiskpointJudgedByPage(@ApiParam(name = "securityRiskpointJudgedDto",required =false)
                                        @RequestBody SecurityRiskpointJudgedDto securityRiskpointJudgedDto){

        log.info("securityRiskpointJudgedDto.getPageSize()的值为："+securityRiskpointJudgedDto.getPageSize());
        Page<SecurityRiskpointJudged> pageList = securityRiskpointJudgedService.selectRiskpointJudgedByPage(securityRiskpointJudgedDto);
        return ResultVo.success(pageList);
    }

    @ApiOperation(value = "查询一条风险点研判",notes = "参数：风险点Dto类")
    @PostMapping("/selectRiskpointJudgedByOne")
    public ResultVo selectRiskpointJudgedByOne(@ApiParam(name = "securityRiskpointJudgedDto",required =false)
                                  @RequestBody SecurityRiskpointJudgedDto securityRiskpointJudgedDto){

        SecurityRiskpointJudged securityRiskpointJudged = new SecurityRiskpointJudged();
        BeanUtils.copyProperties(securityRiskpointJudgedDto,  securityRiskpointJudged);
        SecurityRiskpointJudgedDto securityRiskpointJudgedDtoByOne = securityRiskpointJudgedService.selectRiskpointJudgedByOne(securityRiskpointJudged);
        /*查询风险点信息*/
        log.info("securityRiskpointJudgedDtoByOne的值为："+securityRiskpointJudgedDtoByOne);

        String riskPointId = securityRiskpointJudgedDtoByOne.getRiskpointId();
        SecurityPatrolClassification securityPatrolClassification = new SecurityPatrolClassification();
        securityPatrolClassification.setRiskpointId(riskPointId);
        List<SecurityPatrolClassificationDto> securityPatrolClassificationDtoList = securityPatrolClassificationService.selectPatrolClassificationList(securityPatrolClassification);
        /*查询巡检分类信息*/
        log.info("securityPatrolClassificationDtoList的值为："+securityPatrolClassificationDtoList);

        List<SecurityRiskcontent> securityRiskcontentList = new ArrayList<>();
        for (int i = 0;i<securityPatrolClassificationDtoList.size();i++){
            String patrolClassificationId = securityPatrolClassificationDtoList.get(i).getPatrolClassificationId();
            SecurityRiskcontent securityRiskcontent = new SecurityRiskcontent();
            log.info("riskPointId和patrolClassificationId的值为："+riskPointId+","+patrolClassificationId);
            securityRiskcontent.setRiskpointId(riskPointId);
            securityRiskcontent.setPatrolClassificationId(patrolClassificationId);
            securityRiskcontentList.add(securityRiskcontent);
        }
        List<SecurityRiskcontent> securityRiskcontentListResult = securityRiskcontentService.selectRiskcontentList(securityRiskcontentList);
        /*查询风险内容信息*/
        log.info("securityRiskcontentListResult的值为："+securityRiskcontentListResult);

        for (int i = 0;i<securityPatrolClassificationDtoList.size();i++){
            log.info("securityPatrolClassificationDtoList.size()的值为："+securityPatrolClassificationDtoList.size());
            SecurityPatrolClassificationDto securityPatrolClassificationDto = securityPatrolClassificationDtoList.get(i);
            String patrolClassificationId = securityPatrolClassificationDto.getPatrolClassificationId();
            log.info("patrolClassificationId的值为："+patrolClassificationId);
            List<SecurityRiskcontent> securityRiskcontentList1 = new ArrayList<>();
            for (int j = 0;j<securityRiskcontentListResult.size();j++){
                log.info("securityRiskcontentListResult.size()的值为："+securityRiskcontentListResult.size());
                SecurityRiskcontent securityRiskcontent = securityRiskcontentListResult.get(j);
                String riskcontentPatrolClassificationId = securityRiskcontent.getPatrolClassificationId();
                log.info("riskcontentPatrolClassificationId的值为："+riskcontentPatrolClassificationId);
                if (patrolClassificationId.equals(riskcontentPatrolClassificationId)){
                    securityRiskcontentList1.add(securityRiskcontent);
                    log.info("if语句已执行到第（ "+j+" ）条");
                }
            }
            securityPatrolClassificationDto.setSecurityRiskcontentList(securityRiskcontentList1);
            securityPatrolClassificationDtoList.set(i,securityPatrolClassificationDto);

        }
        securityRiskpointJudgedDtoByOne.setSecurityPatrolClassificationDtoList(securityPatrolClassificationDtoList);
        return ResultVo.success(securityRiskpointJudgedDtoByOne);
    }

    @ApiOperation(value = "更新风险点研判",notes = "参数：风险点Dto类")
    @PostMapping("/updateRiskpointJudged")
    public ResultVo updateRiskpointJudged(@ApiParam(name = "securityRiskpointJudgedDto", required = true)
                                       @RequestBody SecurityRiskpointJudgedDto securityRiskpointJudgedDto){



        boolean updateB = false;
        boolean updateC = false;

        SecurityRiskpointJudged securityRiskpointJudged = new SecurityRiskpointJudged();
        BeanUtils.copyProperties(securityRiskpointJudgedDto,  securityRiskpointJudged);
        securityRiskpointJudged.setLecDanger(securityRiskpointJudgedDto.getDangerDegree()+"("+securityRiskpointJudgedDto.getLecDanger()+")");
        securityRiskpointJudged.setLsRiskValue(securityRiskpointJudgedDto.getDangerDegree()+"("+securityRiskpointJudgedDto.getLsRiskValue()+")");
        securityRiskpointJudged.setJudgmentTime(new Date());
        /*更改风险点基本信息*/
        boolean updateA = securityRiskpointJudgedService.updateById(securityRiskpointJudged);


        /*该风险点的id*/
        String riskPointId = securityRiskpointJudgedDto.getRiskpointId();
        SecurityPatrolClassification securityPatrolClassification = new SecurityPatrolClassification();
        securityPatrolClassification.setRiskpointId(riskPointId);
        /*根据风险点id从数据库中查询到的全部巡检分类信息*/
        List<SecurityPatrolClassificationDto> securityPatrolClassificationDtoResultList = securityPatrolClassificationService.selectPatrolClassificationList(securityPatrolClassification);
        log.info("securityPatrolClassificationDtoList的值为："+securityPatrolClassificationDtoResultList);

        /*前台传的全部的巡检分类信息*/
        List<SecurityPatrolClassificationDto> securityPatrolClassificationDtoList = securityRiskpointJudgedDto.getSecurityPatrolClassificationDtoList();
        for (int i = 0;i<securityPatrolClassificationDtoResultList.size();i++){
            /*根据风险点id从数据库中查询到的一个巡检分类信息*/
            SecurityPatrolClassificationDto securityPatrolClassificationDtoResult = securityPatrolClassificationDtoResultList.get(i);
            log.info("第"+i+"次securityPatrolClassificationDtoResult的值为："+securityPatrolClassificationDtoResult);
            for (int j = 0;j<securityPatrolClassificationDtoList.size();j++){
                int num = 0;
                boolean saveA = false;
                boolean saveB = false;
                boolean save0 = false;
                /*新增时巡检分类id*/
                String newPatrolClassificationId = RandomId.getUUID();
                /*前台传的一个巡检分类信息*/
                SecurityPatrolClassificationDto securityPatrolClassificationDto = securityPatrolClassificationDtoList.get(j);
                SecurityPatrolClassificationDto securityPatrolClassificationDto1 = new SecurityPatrolClassificationDto();
                BeanUtils.copyProperties(securityPatrolClassificationDto,securityPatrolClassificationDto1);
                log.info("第"+j+"securityPatrolClassificationDto1："+securityPatrolClassificationDto1);
                /*判断前台传的巡检分类名称与数据库中的巡检分类名称是否一致*/
                if (securityPatrolClassificationDtoResult.getControllevel().equals(securityPatrolClassificationDto1.getControllevel())){
                    /*如果一致则*/
                    /*数据库中存在的巡检分类的id*/
                    String patrolClassificationId = securityPatrolClassificationDtoResult.getPatrolClassificationId();
                    /*从前台传的风险内容*/
                    List<SecurityRiskcontent> securityRiskcontentList = securityPatrolClassificationDto1.getSecurityRiskcontentList();
                    for (int t = 0;t<securityRiskcontentList.size();t++){
                        SecurityRiskcontent securityRiskcontent = securityRiskcontentList.get(t);
                        if (securityRiskcontent.getRiskcontentId() == null || securityRiskcontent.getRiskcontentId().equals("")){
                            securityRiskcontent.setRiskpointId(riskPointId);
                            securityRiskcontent.setPatrolClassificationId(patrolClassificationId);
                            securityRiskcontent.setJudgmentTime(new Date());
                            securityRiskcontent.setInsertTime(new Date());
                            boolean save = securityRiskcontentService.save(securityRiskcontent);
                            if (save){
                                num++;
                            }

                        }else {
                            securityRiskcontent.setJudgmentTime(new Date());
                           boolean update = securityRiskcontentService.updateById(securityRiskcontent);
                            if (update){
                                num++;
                            }
                        }
                    }

                    log.info("巡检分类相同时securityPatrolClassificationDto1的值为："+securityPatrolClassificationDto1);
                }else {
                    if (securityPatrolClassificationDto1.getPatrolClassificationId() == null || securityPatrolClassificationDto1.getPatrolClassificationId().equals("")){
                        /*否则*/
                        SecurityPatrolClassification securityPatrolClassificationElse = new SecurityPatrolClassification();
                        /*自己的id*/
                        securityPatrolClassificationElse.setPatrolClassificationId(newPatrolClassificationId);
                        /*风险点的id*/
                        securityPatrolClassificationElse.setRiskpointId(riskPointId);
                        securityPatrolClassificationElse.setControllevel(securityPatrolClassificationDto1.getControllevel());
                        securityPatrolClassificationElse.setJudgmentTime(new Date());
                        securityPatrolClassificationElse.setInsertTime(new Date());
                        save0 = securityPatrolClassificationService.save(securityPatrolClassificationElse);

                        /*从前台传的风险内容*/
                        List<SecurityRiskcontent> securityRiskcontentList = securityPatrolClassificationDto1.getSecurityRiskcontentList();
                        for (int t = 0;t<securityRiskcontentList.size();t++){
                            SecurityRiskcontent securityRiskcontent = securityRiskcontentList.get(t);
                            SecurityRiskcontent securityRiskcontent1 = new SecurityRiskcontent();
                            BeanUtils.copyProperties(securityRiskcontent,securityRiskcontent1);
                            securityRiskcontent1.setRiskpointId(riskPointId);
                            securityRiskcontent1.setPatrolClassificationId(newPatrolClassificationId);
                            securityRiskcontent1.setJudgmentTime(new Date());
                            securityRiskcontent1.setInsertTime(new Date());
                            boolean save = securityRiskcontentService.save(securityRiskcontent1);
                            if (save){
                                num++;
                            }
                        }
                    }

                    log.info("巡检分类不相同时securityPatrolClassificationDto1的值为："+securityPatrolClassificationDto1);
                }
                if (num == securityPatrolClassificationDto1.getSecurityRiskcontentList().size()){
                    log.info("num的值为："+num);
                    log.info("执行了saveA = true语句");
                    saveA = true;
                }
                if (num == securityPatrolClassificationDto1.getSecurityRiskcontentList().size() && save0){
                    log.info("num的值为："+num);
                    log.info("执行了saveB = true语句");
                    saveB = true;
                }
                if (saveA || saveB){
                    log.info("执行了saveA || saveB判断语句");
                    securityPatrolClassificationDtoList.remove(j);
                }
            }
        }

        if(updateA){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    /*excel表格导出*/
    @ApiOperation(value = "excel表格导出", notes = "")
    @PostMapping(value = "/exportRiskpointJudgedExcel")
    public void exportRiskpointJudgedExcel(HttpServletResponse httpServletResponse,
                                           @ApiParam(name = "securityRiskpointJudgedDto",required =false)
                                           @RequestBody List<SecurityRiskpointJudgedDto> securityRiskpointJudgedDtoList) throws Exception{

        log.info("接收到excel导出请求");
        /*文件路径*/
        String fileName="C:\\Users\\HXS\\Desktop\\TestJavaCode\\"+"研判清单"+".xls";
        //String fileName="研判清单"+".xls";

        /*创建Excel文件(Workbook)*/
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

        /*设置字体*/
        HSSFFont font = hssfWorkbook.createFont();
        // font.setColor(HSSFFont.COLOR_RED);
        font.setFontHeightInPoints((short) 10);
        font.setFontName("新宋体");
        font.setColor(HSSFColor.BLUE.index);
        font.setBoldweight((short) 0.8);

        /*设置样式*/
        HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setFont(font); // 调用字体样式对象
        cellStyle.setWrapText(true);

        /*excel表格的列名称*/
        String[] headers = {
                "风险点名称", "风险内容", "导致事故原因", "可能导致的事故",
                "风险内容应急处置措施", "影响范围", "潜在后果", "巡检点区域",
                "负责人", "风险点描述", "风险等级", "事故发生的可能性LS（L）",
                "事故后果严重性LS（S）", "风险值LS（R=L*S）", "发生事故的可能性LEC（L）",
                "暴露于危险环境的频繁程度LEC（E）", "事故可能造成的后果LEC（C）",
                "危险性LEC（D=L*E*C）", "风险点类型", "可能造成的后果", "损失预测",
                "管控措施", "存在隐患情况", "应急处置措施", "技术保障措施"
        };

        /*創建工作表(Sheet),设置表单和表单名*/
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("研判清单");

        hssfSheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 24));

        //第一行
        // 在索引0的位置创建行（最顶端的行）
        HSSFRow hssfRow0 = hssfSheet.createRow(0);
        // 在索引0的位置创建单元格（左上端）
        HSSFCell cell00 = hssfRow0.createCell(0);
        // 定义单元格为字符串类型
        cell00.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell00.setCellStyle(cellStyle);
        cell00.setCellValue(new HSSFRichTextString("研判清单"));


        /*生成表单的第二行，即表头*/
        HSSFRow hssfRow = hssfSheet.createRow(1);
        for (int i = 0;i<headers.length;i++){
            HSSFCell hssfCell = hssfRow.createCell(i);
            hssfCell.setCellType(HSSFCell.CELL_TYPE_STRING);
            hssfCell.setCellStyle(cellStyle);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            hssfCell.setCellValue(text);
        }

        List<SecurityRiskpointJudged> securityRiskpointJudgedList = new ArrayList<>();
        for (int i = 0;i<securityRiskpointJudgedDtoList.size();i++){
            SecurityRiskpointJudgedDto securityRiskpointJudgedDto = securityRiskpointJudgedDtoList.get(i);
            SecurityRiskpointJudged securityRiskpointJudged = new SecurityRiskpointJudged();
            BeanUtils.copyProperties(securityRiskpointJudgedDto,  securityRiskpointJudged);
            securityRiskpointJudgedList.add(securityRiskpointJudged);
        }


        log.info("securityRiskpointJudgedDtoList："+securityRiskpointJudgedDtoList);
        log.info("securityRiskpointJudgedList的值为："+securityRiskpointJudgedList);

        /*获取总数据对象*/
        List<SecurityRiskpointJudgedDto> riskpointJudgedDtoExcelList = securityRiskpointJudgedService.getRiskpointJudgedExcel(securityRiskpointJudgedList);

        log.info("riskpointJudgedDtoExcel的值为："+riskpointJudgedDtoExcelList);

        SecurityRiskpointJudgedDto securityRiskpointJudgedDto ;
        int flag = 2;
        for (int t =0;t<riskpointJudgedDtoExcelList.size();t++){

            /*取出链表中的每个大对象*/
            securityRiskpointJudgedDto = riskpointJudgedDtoExcelList.get(t);

            /*取出每个大对象中的小对象*/
            List<SecurityRiskcontent> securityRiskcontentList = securityRiskpointJudgedDto.getSecurityRiskcontentList();

            log.info("securityRiskcontentList的值为："+securityRiskcontentList);

            int height = securityRiskpointJudgedDto.getSecurityRiskcontentList().size();

            HSSFRow hssfRow2 = hssfSheet.createRow(flag);

            for (int i = 0;i < securityRiskcontentList.size();i++){
                SecurityRiskcontent securityRiskcontent = securityRiskcontentList.get(i);

                log.info("securityRiskcontent.getRiskControlContent()："+securityRiskcontent.getRiskControlContent());

                HSSFRow hssfRowi = hssfSheet.createRow(i+flag);

                hssfRowi.createCell(1).setCellValue(securityRiskcontent.getRiskControlContent());
                hssfRowi.createCell(2).setCellValue(securityRiskcontent.getAccidentCause());
                hssfRowi.createCell(3).setCellValue(securityRiskcontent.getPossibleAccidents());
                hssfRowi.createCell(4).setCellValue(securityRiskcontent.getEmergencyMeasures());
                hssfRowi.createCell(5).setCellValue(securityRiskcontent.getInfluenceScope());
                hssfRowi.createCell(6).setCellValue(securityRiskcontent.getPotentialConsequences());

                hssfRowi.createCell(23).setCellValue(securityRiskcontent.getEmergencyMeasures());
                hssfRowi.createCell(24).setCellValue(securityRiskcontent.getTechnicalMeasures());
            }

            hssfRow2.createCell(0).setCellValue(securityRiskpointJudgedDto.getRiskpointName());
            hssfRow2.createCell(7).setCellValue(securityRiskpointJudgedDto.getRiskpointLocation());
            hssfRow2.createCell(8).setCellValue(securityRiskpointJudgedDto.getPersonInCharge());
            hssfRow2.createCell(9).setCellValue(securityRiskpointJudgedDto.getRiskpointDescription());
            hssfRow2.createCell(10).setCellValue(securityRiskpointJudgedDto.getRiskLevel());
            hssfRow2.createCell(11).setCellValue(securityRiskpointJudgedDto.getLsAccidentPossibility());
            hssfRow2.createCell(12).setCellValue(securityRiskpointJudgedDto.getLsConsequenceSeriousness());
            hssfRow2.createCell(13).setCellValue(securityRiskpointJudgedDto.getLsRiskValue());
            hssfRow2.createCell(14).setCellValue(securityRiskpointJudgedDto.getLecAccidentPossibility());
            hssfRow2.createCell(15).setCellValue(securityRiskpointJudgedDto.getLecDangerDegree());
            hssfRow2.createCell(16).setCellValue(securityRiskpointJudgedDto.getLecPossibleConsequence());
            hssfRow2.createCell(17).setCellValue(securityRiskpointJudgedDto.getLecDanger());
            hssfRow2.createCell(18).setCellValue(securityRiskpointJudgedDto.getRiskpointClassification());
            hssfRow2.createCell(19).setCellValue(securityRiskpointJudgedDto.getPossibleConsequences());
            hssfRow2.createCell(20).setCellValue(securityRiskpointJudgedDto.getLossPrediction());
            hssfRow2.createCell(21).setCellValue(securityRiskpointJudgedDto.getControlMeasures());
            hssfRow2.createCell(22).setCellValue(securityRiskpointJudgedDto.getHiddenDanger());

            hssfSheet.addMergedRegion(new CellRangeAddress(flag, flag+height-1, 0, 0));
            hssfSheet.addMergedRegion(new CellRangeAddress(flag, flag+height-1, 7, 7));
            hssfSheet.addMergedRegion(new CellRangeAddress(flag, flag+height-1, 8, 8));
            hssfSheet.addMergedRegion(new CellRangeAddress(flag, flag+height-1, 9, 9));
            hssfSheet.addMergedRegion(new CellRangeAddress(flag, flag+height-1, 10, 10));
            hssfSheet.addMergedRegion(new CellRangeAddress(flag, flag+height-1, 11, 11));
            hssfSheet.addMergedRegion(new CellRangeAddress(flag, flag+height-1, 12, 12));
            hssfSheet.addMergedRegion(new CellRangeAddress(flag, flag+height-1, 13, 13));
            hssfSheet.addMergedRegion(new CellRangeAddress(flag, flag+height-1, 14, 14));
            hssfSheet.addMergedRegion(new CellRangeAddress(flag, flag+height-1, 15, 15));
            hssfSheet.addMergedRegion(new CellRangeAddress(flag, flag+height-1, 16, 16));
            hssfSheet.addMergedRegion(new CellRangeAddress(flag, flag+height-1, 17, 17));
            hssfSheet.addMergedRegion(new CellRangeAddress(flag, flag+height-1, 18, 18));
            hssfSheet.addMergedRegion(new CellRangeAddress(flag, flag+height-1, 19, 19));
            hssfSheet.addMergedRegion(new CellRangeAddress(flag, flag+height-1, 20, 20));
            hssfSheet.addMergedRegion(new CellRangeAddress(flag, flag+height-1, 21, 21));
            hssfSheet.addMergedRegion(new CellRangeAddress(flag, flag+height-1, 22, 22));

            flag = flag + height;
        }

        log.info("已经准备好数据，即将发送至前端接收");
        /*httpServletResponse.setContentType("application/octet-stream");
        httpServletResponse.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
        httpServletResponse.flushBuffer();
        hssfWorkbook.write(httpServletResponse.getOutputStream());*/

        /*創建文件輸出流*/
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        /*保存Excel文件*/
        hssfWorkbook.write(fileOutputStream);

        /*關閉文件輸出流*/
        fileOutputStream.close();
    }

    @ApiOperation("批量删除")
    @PostMapping("/deleteRiskpointJudged")
    public ResultVo deleteRiskpointJudged(@ApiParam(name = "list", value = "权限ID", required = false)
                                  @RequestBody List<String> list){

        log.info("参数list.size()的值为："+list.size());
        log.info("参数list.get(0)的值为："+list.get(0));
        boolean listDelete = securityRiskpointJudgedService.removeByIds(list);
        if(listDelete){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation(value = "批量设置风险点状态",notes = "参数：风险点Dto类")
    @PostMapping("/setRiskpointStatus")
    public ResultVo setRiskpointStatus(@ApiParam(name = "securityRiskpointJudgedDtoList", required = true)
                                             @RequestBody  List<String> list){
        List<SecurityRiskpointJudgedDto> securityRiskpointJudgedDtoList = new ArrayList<>();

        for (int i = 0;i<list.size();i++){
            SecurityRiskpointJudgedDto securityRiskpointJudgedDto = new SecurityRiskpointJudgedDto();
            securityRiskpointJudgedDto.setRiskpointId(list.get(i));
            securityRiskpointJudgedDtoList.add(securityRiskpointJudgedDto);
        }

        List<SecurityRiskpointJudged> securityRiskpointJudgedList = new ArrayList<>();
        for (int i = 0;i<securityRiskpointJudgedDtoList.size();i++){
            SecurityRiskpointJudgedDto securityRiskpointJudgedDto = securityRiskpointJudgedDtoList.get(i);
            SecurityRiskpointJudged securityRiskpointJudged = new SecurityRiskpointJudged();
            BeanUtils.copyProperties(securityRiskpointJudgedDto,  securityRiskpointJudged);
            securityRiskpointJudgedList.add(securityRiskpointJudged);
        }

        log.info("securityRiskpointJudgedDtoList："+securityRiskpointJudgedDtoList);
        log.info("securityRiskpointJudgedList的值为："+securityRiskpointJudgedList);
        boolean update = securityRiskpointJudgedService.setRiskpointStatus(securityRiskpointJudgedList);
        if(update){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }
}
