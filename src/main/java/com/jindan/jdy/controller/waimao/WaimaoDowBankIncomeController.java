package com.jindan.jdy.controller.waimao;

import com.jindan.jdy.common.pojo.WaimaoDowBankIncome;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.WorkbookUtils;
import com.jindan.jdy.service.waimao.WaimaoDowBankIncomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

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
@Api(tags = "外贸道氏银行收入信息")
@RestController
@RequestMapping("/waimaoDowBankIncome")
public class WaimaoDowBankIncomeController{

    @Autowired
    WaimaoDowBankIncomeService waimaoAreaService;


    @ApiOperation(value = "道氏银行收入信息导入", notes = "参数:道氏银行收入信息导入")
    @PostMapping("addexcleDowBankExpend")
    public ResultVo addfahuo(@RequestParam("file") MultipartFile file) throws Exception {
        log.info("======“道氏银行收入信息导入接口”开始执行======");
        //创建Excel工作薄
        Workbook work = WorkbookUtils.getWorkbook(file.getInputStream(),file.getOriginalFilename());
        if(null == work){
            throw new Exception("创建Excel工作薄为空！");
        }
        log.info("work.getNumberOfSheets()的值为："+ work.getNumberOfSheets());
        Sheet sheet  = work.getSheetAt(0);
        if(sheet==null){
            throw new Exception("创建Excel工作薄为空！");
        }
        List<WaimaoDowBankIncome> jijiabiaos = new ArrayList<>();
        for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
            Row row = sheet.getRow(j);
            if(row==null||row.getFirstCellNum()==j){continue;}
            WaimaoDowBankIncome jijiabiao = new WaimaoDowBankIncome();
            jijiabiaos.add(jijiabiao);
        }
        waimaoAreaService.saveBatch(jijiabiaos);
        return ResultVo.success();
    }

    @ApiOperation(value = "查询道氏银行收入信息", notes = "参数:查询道氏银行收入信息")
    @PostMapping("/seleteWaimaoDowBankExpend")
    public ResultVo seleteWaimaoDowBankExpend(@ApiParam(value = "jdyRole", required = false)
                                              @RequestBody WaimaoDowBankIncome jdyRole){
        List<WaimaoDowBankIncome> list = waimaoAreaService.seletelist(jdyRole);
        return  ResultVo.success(list);
    }


    @ApiOperation("更新道氏银行收入信息")
    @PostMapping("/updateWaimaoDowBankExpend")
    public ResultVo updateWaimaoDowBankExpend(@ApiParam(value = "jdyRole", required = true)
                                              @RequestBody WaimaoDowBankIncome jdyRole){
        boolean b = waimaoAreaService.updateById(jdyRole);
        if(b){
            return ResultVo.success(jdyRole);
        }
        return ResultVo.failed();
    }


    @ApiOperation("新增道氏银行收入信息")
    @PostMapping("/addWaimaoDowBankExpend")
    public ResultVo addWaimaoDowBankExpend( @ApiParam(name = "jdyRole", required = true)
                                            @RequestBody WaimaoDowBankIncome jdyRole){
        boolean save = waimaoAreaService.save(jdyRole);
        if(save){
            return ResultVo.success(jdyRole);
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除道氏银行收入信息")
    @DeleteMapping("/deleteWaimaoDowBankExpend/{seid}")
    public ResultVo deleteTichengXishu(@ApiParam(value = "seid", name = "seid", required = true) @PathVariable String  seid){
        boolean b = waimaoAreaService.removeById(seid);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


}