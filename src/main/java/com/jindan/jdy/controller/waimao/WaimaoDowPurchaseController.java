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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
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
        String fileName="采购信息导出"+".xlsx";

        /*String fileName="C:\\Users\\HXS\\Desktop\\TestJavaCode\\采购信息.xlsx";*/

        /*创建Excel文件(Workbook)*/
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();

        /*excel表格的列名称*/
        String[] headers = {
                "合同号", "发票号", "物料名", "供应商", "价税合计", "金额", "数量","税额","单位", "备注",
                "合同号", "物料名", "收货客户", "退税", "退款率", "数量", "税额", "单位", "备注",
                "合同号", "贷款或费用", "收款人", "付款时间", "银行支出金额", "银行费用金额", "性质", "备注",
                "合同号或发票号", "退款或退税", "收款人", "收款时间", "银行收入金额", "银行退款金额", "性质", "备注"
        };

        /*将数组存入list中*//*
        List<String> headersList= new ArrayList<String>();
        headersList =  Arrays.asList(headers);*/

        /*創建工作表(Sheet),设置表单和表单名*/
        XSSFSheet xssfSheet = xssfWorkbook.createSheet("数据导出");

        /*生成表单的第一行*/
        XSSFRow xssfRow = xssfSheet.createRow(0);
        for (int i = 0;i<headers.length;i++){
            XSSFCell xssfCell = xssfRow.createCell(i);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            xssfCell.setCellValue(text);
        }

        List<WaimaoDowBankExpendDto> exportExcel = waimaoAreaService.getExportExcel();

        for (int i =0 ;i < exportExcel.size();i++){
            XSSFRow xssfRow1 = xssfSheet.createRow(i+1);

            /*XSSFCell xssfCell = xssfRow1.createCell(i);

            for (int j = 0;j<exportExcel.get(i).)

            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            xssfCell.setCellValue(text);

            xssfCell.setCellValue(exportExcel.get(i).get);*/


            WaimaoDowBankExpendDto waimaoDowBankExpendDto = exportExcel.get(i);

            List<WaimaoDowPurchase> listWaimaoDowPurchase = waimaoDowBankExpendDto.getWaimaoDowPurchaselist();
            List<WaimaoDowMarket> listWaimaoDowMarket = waimaoDowBankExpendDto.getWaimaoDowMarketlist();
            List<WaimaoDowBankExpend> listWaimaoDowBankExpend = waimaoDowBankExpendDto.getWaimaoDowBankExpendList();
            List<WaimaoDowBankIncome> listWaimaoDowBankIncome = waimaoDowBankExpendDto.getWaimaoDowBankIncomelist();

            /*采购信息表*/
            if(listWaimaoDowPurchase!=null && listWaimaoDowPurchase.size()>0){
                xssfRow1.createCell(0).setCellValue(listWaimaoDowPurchase.get(0).getChetonghao());
                xssfRow1.createCell(1).setCellValue(listWaimaoDowPurchase.get(0).getCfapiaohao());
                xssfRow1.createCell(2).setCellValue(listWaimaoDowPurchase.get(0).getMaterialName());
                xssfRow1.createCell(3).setCellValue(listWaimaoDowPurchase.get(0).getSuppliers());
                xssfRow1.createCell(4).setCellValue(listWaimaoDowPurchase.get(0).getJiahsuiheji());
                xssfRow1.createCell(5).setCellValue(listWaimaoDowPurchase.get(0).getJine());
                xssfRow1.createCell(6).setCellValue(listWaimaoDowPurchase.get(0).getNums());
                xssfRow1.createCell(7).setCellValue(listWaimaoDowPurchase.get(0).getTax());
                xssfRow1.createCell(8).setCellValue(listWaimaoDowPurchase.get(0).getUnits());
                xssfRow1.createCell(9).setCellValue(listWaimaoDowPurchase.get(0).getRemarks());
            }

            /*销售信息表*/
            if(listWaimaoDowMarket!=null && listWaimaoDowMarket.size()>0){
                xssfRow1.createCell(10).setCellValue(listWaimaoDowMarket.get(0).getXhetonghao());
                xssfRow1.createCell(11).setCellValue(listWaimaoDowMarket.get(0).getMaterialNames());
                xssfRow1.createCell(12).setCellValue(listWaimaoDowMarket.get(0).getShouhuokehu());
                xssfRow1.createCell(13).setCellValue(listWaimaoDowMarket.get(0).getDrawback());
                xssfRow1.createCell(14).setCellValue(listWaimaoDowMarket.get(0).getRefundRates());
                xssfRow1.createCell(15).setCellValue(listWaimaoDowMarket.get(0).getNums());
                xssfRow1.createCell(16).setCellValue(listWaimaoDowMarket.get(0).getXtax());
                xssfRow1.createCell(17).setCellValue(listWaimaoDowMarket.get(0).getUnitx());
                xssfRow1.createCell(18).setCellValue(listWaimaoDowMarket.get(0).getRemarksx());
            }


            /*银行支出信息表*/
            if(listWaimaoDowBankExpend!=null && listWaimaoDowBankExpend.size()>0){
                xssfRow1.createCell(19).setCellValue(listWaimaoDowBankExpend.get(0).getContractNumber());
                xssfRow1.createCell(20).setCellValue(listWaimaoDowBankExpend.get(0).getPaymentForGoods());
                xssfRow1.createCell(21).setCellValue(listWaimaoDowBankExpend.get(0).getShoukuanren());
                xssfRow1.createCell(22).setCellValue(listWaimaoDowBankExpend.get(0).getPaymentTime());
                xssfRow1.createCell(23).setCellValue(listWaimaoDowBankExpend.get(0).getYjine());
                xssfRow1.createCell(24).setCellValue(listWaimaoDowBankExpend.get(0).getYjine2());
                xssfRow1.createCell(25).setCellValue(listWaimaoDowBankExpend.get(0).getYnature());
                xssfRow1.createCell(26).setCellValue(listWaimaoDowBankExpend.get(0).getYremarks());
            }


            /*银行收入信息表*/
            if(listWaimaoDowBankIncome!=null && listWaimaoDowBankIncome.size()>0){
                xssfRow1.createCell(27).setCellValue(listWaimaoDowBankIncome.get(0).getContractFapiao());
                xssfRow1.createCell(28).setCellValue(listWaimaoDowBankIncome.get(0).getHuoTuishui());
                xssfRow1.createCell(29).setCellValue(listWaimaoDowBankIncome.get(0).getShouRen());
                xssfRow1.createCell(30).setCellValue(listWaimaoDowBankIncome.get(0).getShouTime());
                xssfRow1.createCell(31).setCellValue(listWaimaoDowBankIncome.get(0).getShouJine());
                xssfRow1.createCell(32).setCellValue(listWaimaoDowBankIncome.get(0).getShouJine2());
                xssfRow1.createCell(33).setCellValue(listWaimaoDowBankIncome.get(0).getShouNature());
                xssfRow1.createCell(34).setCellValue(listWaimaoDowBankIncome.get(0).getYsremarks());
            }

        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
        response.flushBuffer();
        xssfWorkbook.write(response.getOutputStream());

        /*創建文件輸出流*//*
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        *//*保存Excel文件*//*
        xssfWorkbook.write(fileOutputStream);

        *//*關閉文件輸出流*//*
        fileOutputStream.close();*/

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
            System.out.println(mapKey+":"+mapValue);
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