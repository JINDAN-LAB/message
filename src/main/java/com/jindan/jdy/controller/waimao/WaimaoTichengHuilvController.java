package com.jindan.jdy.controller.waimao;

import com.jindan.jdy.common.pojo.WaimaoTichengHuilv;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.CommonUtils;
import com.jindan.jdy.controller.utils.WorkbookUtils;
import com.jindan.jdy.service.waimao.WaimaoTichengHuilvService;
import freemarker.ext.beans.HashAdapter;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*
* <p>说明： 外贸提成API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年5月28日
*
*/
@Slf4j
@Api(tags = "外贸提成汇率管理")
@RestController
@RequestMapping("/waimaoTichengHuilv")
public class WaimaoTichengHuilvController{

    @Autowired
    WaimaoTichengHuilvService waimaoTichengHuilvService;


    @ApiOperation(value = "汇率批量导入", notes = "参数:汇率批量导入")
    @PostMapping("addBatchTichengFahuo")
    public ResultVo addTichengHuilv(@RequestParam("file") MultipartFile file) throws Exception {

        String presenttime = CommonUtils.getPresenttime();
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
        List<WaimaoTichengHuilv> jijiabiaos = new ArrayList<>();
        for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++){
            Row row = sheet.getRow(j);
            if(row==null||row.getFirstCellNum()==j){continue;}
            WaimaoTichengHuilv jijiabiao = new WaimaoTichengHuilv();
            jijiabiao.setBz(row.getCell(0).getStringCellValue());
            jijiabiao.setBili(Float.valueOf(row.getCell(1).getStringCellValue()));
            jijiabiaos.add(jijiabiao);
        }
        waimaoTichengHuilvService.saveBatch(jijiabiaos);
        return ResultVo.success();
    }


    @ApiOperation(value = "查询汇率", notes = "参数:外贸提成汇率")
    @PostMapping("/seleteTichengHuilv")
    public ResultVo seleteTichengHuilv(@ApiParam(value = "jdyRole", required = false)
                                       @RequestBody WaimaoTichengHuilv waimaoTichengHuilv){
        List<WaimaoTichengHuilv> list = waimaoTichengHuilvService.seletelist(waimaoTichengHuilv);
        return  ResultVo.success(list);
    }


    @ApiOperation("更新汇率")
    @PostMapping("/updateTichengHuilv")
    public ResultVo updateTichengHuilv(@ApiParam(value = "jdyRole", required = true)
                                       @RequestBody WaimaoTichengHuilv jdyRole){
        boolean b = waimaoTichengHuilvService.updateById(jdyRole);
        if(b){
            return ResultVo.success(jdyRole);
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增汇率")
    @PostMapping("/addTichengHuilv")
    public ResultVo addTichengHuilv( @ApiParam(name = "jdyRole", required = true)
                                     @RequestBody WaimaoTichengHuilv jdyRole){
        boolean save = waimaoTichengHuilvService.save(jdyRole);
        if(save){
            return ResultVo.success(jdyRole);
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除汇率")
    @DeleteMapping("/deleteTichengHuilv/{bz}")
    public ResultVo deleteTichengHuilv(@ApiParam(value = "bz", name = "汇率名称", required = true) @PathVariable String  bz){
        boolean b = waimaoTichengHuilvService.removeById(bz);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }



}