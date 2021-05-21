package com.jindan.jdy.controller.waimao;

import com.jindan.jdy.common.dto.WaimaoDowBankExpendDto;
import com.jindan.jdy.common.pojo.WaimaoDowBankExpend;
import com.jindan.jdy.common.pojo.WaimaoDowBankIncome;
import com.jindan.jdy.common.pojo.WaimaoDowMarket;
import com.jindan.jdy.common.pojo.WaimaoDowPurchase;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.WorkbookUtils;
import com.jindan.jdy.service.waimao.WaimaoDowPurchaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*
* <p>说明： 外贸道氏API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年7月29日
*
*/
@Slf4j
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "外贸道氏采购信息")
@RestController
@RequestMapping("/waimaoDowPurchase")
public class WaimaoDowPurchaseController{

    @Autowired
    WaimaoDowPurchaseService waimaoAreaService;

    private static final Logger logger = LoggerFactory.getLogger(WaimaoDowPurchaseController.class);

    @ApiOperation(value = "道氏采购信息导入", notes = "参数:道氏采购信息")
    @PostMapping("addexcleWaimaoDowPurchase")
    public ResultVo addWaimaoDowPurchase(@RequestParam("file") MultipartFile file) throws Exception {
        //创建Excel工作薄
        Workbook work = WorkbookUtils.getWorkbook(file.getInputStream(),file.getOriginalFilename());
        if(null == work){
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet  = work.getSheetAt(0);
        if(sheet==null){
            throw new Exception("创建Excel工作薄为空！");
        }
        List<WaimaoDowPurchase> jijiabiaos = new ArrayList<>();
        for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
            Row row = sheet.getRow(j);
            if(row==null||row.getFirstCellNum()==j){continue;}
            WaimaoDowPurchase jijiabiao = new WaimaoDowPurchase();
            jijiabiaos.add(jijiabiao);
        }
        waimaoAreaService.saveBatch(jijiabiaos);
        return ResultVo.success();
    }

    @ApiOperation(value = "查询道氏采购信息", notes = "参数:查询道氏采购信息")
    @PostMapping("/seleteWaimaoDowPurchase")
    public ResultVo seleteWaimaoDowPurchase(@ApiParam(value = "jdyRole", required = false)
                                              @RequestBody WaimaoDowPurchase jdyRole){
        List<WaimaoDowPurchase> list = waimaoAreaService.seletelist(jdyRole);
        return  ResultVo.success(list);
    }


    @ApiOperation("更新道氏采购信息")
    @PostMapping("/updateWaimaoDowPurchase")
    public ResultVo updateWaimaoDowPurchase(@ApiParam(value = "jdyRole", required = true)
                                              @RequestBody WaimaoDowPurchase jdyRole){
        boolean b = waimaoAreaService.updateById(jdyRole);
        if(b){
            return ResultVo.success(jdyRole);
        }
        return ResultVo.failed();
    }


    @ApiOperation("新增道氏采购信息")
    @PostMapping("/addWaimaoDowPurchase")
    public ResultVo addWaimaoDowPurchase( @ApiParam(name = "jdyRole", required = true)
                                            @RequestBody WaimaoDowPurchase jdyRole){
        boolean save = waimaoAreaService.save(jdyRole);
        if(save){
            return ResultVo.success(jdyRole);
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除道氏采购信息")
    @DeleteMapping("/deleteWaimaoDowPurchase/{seid}")
    public ResultVo deleteWaimaoDowPurchase(@ApiParam(value = "seid", name = "seid", required = true) @PathVariable String  seid){

        boolean b = waimaoAreaService.removeById(seid);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("根据发票号获取详细信息")
    @GetMapping("/deleteWaimaoFahuo/{id}")
    public ResultVo deleteWaimaoFahuo(@ApiParam(value = "id", name = "合同号", required = true) @PathVariable("id") String  id){
         List<WaimaoDowBankExpendDto> list = waimaoAreaService.getDetails(id);
         return ResultVo.success(list);
    }

    /*外贸道氏采购信息信息导出*/
    @ApiOperation(value = "外贸道氏采购信息信息导出", notes = "外贸道氏采购信息信息导出")
    @PostMapping(value = "/getExportExcel")
    public void getExportExcel(HttpServletResponse response) throws Exception{

        logger.info("外贸道氏采购信息导出打印开始");

        /*文件路径*/
        String fileName="外贸道氏采购信息导出"+".xls";

        /*创建Excel文件(Workbook)*/
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

        /*excel表格的列名称*/
        String[] headers = {
                "合同号", "发票号", "物料名", "供应商", "价税合计", "金额", "数量", "税额", "单位", "备注",
                "合同号", "物料名", "收货客户", "退税", "退款率", "数量", "税额", "单位", "备注",
                "合同号", "贷款或费用", "收款人", "付款时间", "银行支出金额", "银行费用金额", "性质", "备注",
                "合同号或发票号", "退款或退税", "收款人", "收款时间", "银行收入金额", "银行退款金额", "性质", "备注"
        };

        /*創建工作表(Sheet),设置表单和表单名*/
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("外贸采购信息导出");

        /*生成表单的第一行，即表头*/
        HSSFRow hssfRow = hssfSheet.createRow(0);
        for (int i = 0;i<headers.length;i++){
            HSSFCell hssfCell = hssfRow.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            hssfCell.setCellValue(text);
        }

        /*获取到外贸采购信息总数据对象，并存到list中*/
        List<WaimaoDowBankExpendDto> waimaoDowPurchaseDetailExportExcel = waimaoAreaService.getWaimaoDowPurchaseDetailExportExcel();

        /*循环*/
        for (int i =0 ;i < waimaoDowPurchaseDetailExportExcel.size();i++){

            /*创建每一行单元格*/
            HSSFRow hssfRow1 = hssfSheet.createRow(i+1);

            /*从list中取出第i个外贸采购信息总数据对象*/
            WaimaoDowBankExpendDto waimaoDowBankExpendDto = waimaoDowPurchaseDetailExportExcel.get(i);

            /*从外贸采购信息总数据对象中取出单个list对象*/
            List<WaimaoDowPurchase> listWaimaoDowPurchase = waimaoDowBankExpendDto.getWaimaoDowPurchaselist();
            List<WaimaoDowMarket> listWaimaoDowMarket = waimaoDowBankExpendDto.getWaimaoDowMarketlist();
            List<WaimaoDowBankExpend> listWaimaoDowBankExpend = waimaoDowBankExpendDto.getWaimaoDowBankExpendList();
            List<WaimaoDowBankIncome> listWaimaoDowBankIncome = waimaoDowBankExpendDto.getWaimaoDowBankIncomelist();

            /*向每个单元格赋值*/
            /*采购信息表*/
            if(listWaimaoDowPurchase!=null && listWaimaoDowPurchase.size()>0){
                /*从单个list对象中取出唯一的一个对象*/
                WaimaoDowPurchase waimaoDowPurchase = listWaimaoDowPurchase.get(0);
                hssfRow1.createCell(0).setCellValue(waimaoDowPurchase.getChetonghao());
                hssfRow1.createCell(1).setCellValue(waimaoDowPurchase.getCfapiaohao());
                hssfRow1.createCell(2).setCellValue(waimaoDowPurchase.getMaterialName());
                hssfRow1.createCell(3).setCellValue(waimaoDowPurchase.getSuppliers());
                hssfRow1.createCell(4).setCellValue(waimaoDowPurchase.getJiahsuiheji());
                hssfRow1.createCell(5).setCellValue(waimaoDowPurchase.getJine());
                hssfRow1.createCell(6).setCellValue(waimaoDowPurchase.getNums());
                hssfRow1.createCell(7).setCellValue(waimaoDowPurchase.getTax());
                hssfRow1.createCell(8).setCellValue(waimaoDowPurchase.getUnits());
                hssfRow1.createCell(9).setCellValue(waimaoDowPurchase.getRemarks());
            }

            /*销售信息表*/
            if(listWaimaoDowMarket!=null && listWaimaoDowMarket.size()>0){
                /*从单个list对象中取出唯一的一个对象*/
                WaimaoDowMarket waimaoDowMarket = listWaimaoDowMarket.get(0);
                hssfRow1.createCell(10).setCellValue(waimaoDowMarket.getXhetonghao());
                hssfRow1.createCell(11).setCellValue(waimaoDowMarket.getMaterialNames());
                hssfRow1.createCell(12).setCellValue(waimaoDowMarket.getShouhuokehu());
                hssfRow1.createCell(13).setCellValue(waimaoDowMarket.getDrawback());
                hssfRow1.createCell(14).setCellValue(waimaoDowMarket.getRefundRates());
                hssfRow1.createCell(15).setCellValue(waimaoDowMarket.getNums());
                hssfRow1.createCell(16).setCellValue(waimaoDowMarket.getXtax());
                hssfRow1.createCell(17).setCellValue(waimaoDowMarket.getUnitx());
                hssfRow1.createCell(18).setCellValue(waimaoDowMarket.getRemarksx());
            }

            /*银行支出信息表*/
            if(listWaimaoDowBankExpend!=null && listWaimaoDowBankExpend.size()>0){
                /*从单个list对象中取出唯一的一个对象*/
                WaimaoDowBankExpend waimaoDowBankExpend = listWaimaoDowBankExpend.get(0);
                hssfRow1.createCell(19).setCellValue(waimaoDowBankExpend.getContractNumber());
                hssfRow1.createCell(20).setCellValue(waimaoDowBankExpend.getPaymentForGoods());
                hssfRow1.createCell(21).setCellValue(waimaoDowBankExpend.getShoukuanren());
                hssfRow1.createCell(22).setCellValue(waimaoDowBankExpend.getPaymentTime());
                hssfRow1.createCell(23).setCellValue(waimaoDowBankExpend.getYjine());
                hssfRow1.createCell(24).setCellValue(waimaoDowBankExpend.getYjine2());
                hssfRow1.createCell(25).setCellValue(waimaoDowBankExpend.getYnature());
                hssfRow1.createCell(26).setCellValue(waimaoDowBankExpend.getYremarks());
            }

            /*银行收入信息表*/
            if(listWaimaoDowBankIncome!=null && listWaimaoDowBankIncome.size()>0){
                /*从单个list对象中取出唯一的一个对象*/
                WaimaoDowBankIncome waimaoDowBankIncome = listWaimaoDowBankIncome.get(0);
                hssfRow1.createCell(27).setCellValue(waimaoDowBankIncome.getContractFapiao());
                hssfRow1.createCell(28).setCellValue(waimaoDowBankIncome.getHuoTuishui());
                hssfRow1.createCell(29).setCellValue(waimaoDowBankIncome.getShouRen());
                hssfRow1.createCell(30).setCellValue(waimaoDowBankIncome.getShouTime());
                hssfRow1.createCell(31).setCellValue(waimaoDowBankIncome.getShouJine());
                hssfRow1.createCell(32).setCellValue(waimaoDowBankIncome.getShouJine2());
                hssfRow1.createCell(33).setCellValue(waimaoDowBankIncome.getShouNature());
                hssfRow1.createCell(34).setCellValue(waimaoDowBankIncome.getYsremarks());
            }

        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
        response.flushBuffer();
        hssfWorkbook.write(response.getOutputStream());

        logger.info("外贸道氏采购信息导出打印结束");
    }


    @ApiOperation(value = "毛利表", notes = "参数:毛利表")
    @PostMapping("/seleteMaoli")
    public ResultVo seleteMaoli(@ApiParam(value = "jdyRole", required = false)
                                            @RequestBody WaimaoDowPurchase jdyRole){
    List<WaimaoDowBankExpendDto> list = waimaoAreaService.getDetailsAll();
    Map<String,Float> hbe =new HashMap<>();
    Map<String,Float> hbl =new HashMap<>();
    for (int i = 0; i < list.size(); i++){
        for (int j = 0; j < list.get(i).getWaimaoDowBankExpendList().size(); j++) {
            if(hbe.containsKey(list.get(i).getWaimaoDowBankExpendList().get(j).getContractNumber())){
                if(list.get(i).getWaimaoDowBankExpendList().get(j).getYjine2() !=null &&!list.get(i).getWaimaoDowBankExpendList().get(j).getYjine2().isEmpty()){
                    hbe.put(list.get(i).getWaimaoDowBankExpendList().get(j).getContractNumber(),hbe.get(list.get(i).getWaimaoDowBankExpendList().get(j).getContractNumber())+Float.valueOf(list.get(i).getWaimaoDowBankExpendList().get(j).getYjine2()));
                 }else{
                    hbe.put(list.get(i).getWaimaoDowBankExpendList().get(j).getContractNumber(),hbe.get(list.get(i).getWaimaoDowBankExpendList().get(j).getContractNumber())+Float.valueOf(list.get(i).getWaimaoDowBankExpendList().get(j).getYjine()));
                }
            }else{
                if( list.get(i).getWaimaoDowBankExpendList().get(j).getYjine2() !=null && !list.get(i).getWaimaoDowBankExpendList().get(j).getYjine2().isEmpty()){
                    hbe.put(list.get(i).getWaimaoDowBankExpendList().get(j).getContractNumber(), Float.valueOf(list.get(i).getWaimaoDowBankExpendList().get(j).getYjine2()));
                 }else{
                    hbe.put(list.get(i).getWaimaoDowBankExpendList().get(j).getContractNumber(),Float.valueOf(list.get(i).getWaimaoDowBankExpendList().get(j).getYjine()));
                }
            }
        }
        for (int j = 0; j < list.get(i).getWaimaoDowBankIncomelist().size() ; j++){
            if(hbl.containsKey(list.get(i).getWaimaoDowBankIncomelist().get(j).getContractFapiao())){
                if(list.get(i).getWaimaoDowBankIncomelist().get(j).getShouJine() !=null && !list.get(i).getWaimaoDowBankIncomelist().get(j).getShouJine().isEmpty()){
                    hbl.put(list.get(i).getWaimaoDowBankIncomelist().get(j).getContractFapiao(),hbl.get(list.get(i).getWaimaoDowBankIncomelist().get(j).getContractFapiao())-Float.valueOf(list.get(i).getWaimaoDowBankIncomelist().get(j).getShouJine2()));
                }else{
                    hbl.put(list.get(i).getWaimaoDowBankIncomelist().get(j).getContractFapiao(),hbl.get(list.get(i).getWaimaoDowBankIncomelist().get(j).getContractFapiao())+Float.valueOf(list.get(i).getWaimaoDowBankIncomelist().get(j).getShouJine()));
                }
            }else{
                if(list.get(i).getWaimaoDowBankIncomelist().get(j).getShouJine2() !=null  && !list.get(i).getWaimaoDowBankIncomelist().get(j).getShouJine2().isEmpty()){
                    hbl.put(list.get(i).getWaimaoDowBankIncomelist().get(j).getContractFapiao(), Float.valueOf(list.get(i).getWaimaoDowBankIncomelist().get(j).getShouJine2()));
                }else{
                    hbl.put(list.get(i).getWaimaoDowBankIncomelist().get(j).getContractFapiao(),Float.valueOf(list.get(i).getWaimaoDowBankIncomelist().get(j).getShouJine()));
                }
            }
        }
      }
      Map<String,Float> resultmap =new HashMap<>();
      for(Map.Entry<String, Float> entry : hbe.entrySet()){
            String mapKey = entry.getKey();
            Float mapValue = entry.getValue();
            log.info("mapKey+mapValue的值为："+mapKey+":"+mapValue);
          for(Map.Entry<String, Float> entrys : hbl.entrySet()){
              if(hbl.containsKey(entry.getKey())){
                   resultmap.put(entry.getKey(),entrys.getValue()-entry.getValue());
              }else{
                  resultmap.put(entry.getKey(),-entry.getValue());
              }
          }
      }
   return  ResultVo.success(resultmap);
}





}