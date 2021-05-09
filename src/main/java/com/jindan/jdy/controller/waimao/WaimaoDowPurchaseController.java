package com.jindan.jdy.controller.waimao;

import com.jindan.jdy.common.dto.WaimaoDowBankExpendDto;
import com.jindan.jdy.common.pojo.WaimaoDowBankExpend;
import com.jindan.jdy.common.pojo.WaimaoDowPurchase;
import com.jindan.jdy.controller.utils.WorkbookUtils;
import com.jindan.jdy.service.waimao.WaimaoDowBankExpendService;
import com.jindan.jdy.service.waimao.WaimaoDowPurchaseService;
import com.jindan.jdy.common.utils.api.ResultVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.springframework.web.multipart.MultipartFile;

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