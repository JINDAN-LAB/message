package com.jindan.jdy.controller.waimao;

import com.jindan.jdy.common.pojo.WaimaoTichengJijiabiao;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.CommonUtils;
import com.jindan.jdy.controller.utils.WorkbookUtils;
import com.jindan.jdy.service.waimao.WaimaoTichengJijiabiaoService;
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
* <p>说明： 外贸提成API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年5月28日
*
*/
@Slf4j
@Api(tags = "外贸提成基价表管理")
@RestController
@RequestMapping("/waimaoTichengJijiabiao")
public class WaimaoTichengJijiabiaoController{

    @Autowired
    WaimaoTichengJijiabiaoService waimaoTichengJijiabiaoService;

    @ApiOperation(value = "基价表批量导入", notes = "参数:基价表批量导入")
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
        List<WaimaoTichengJijiabiao> jijiabiaos = new ArrayList<>();
        for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++){
            Row row = sheet.getRow(j);
            if(row==null||row.getFirstCellNum()==j){continue;}
            WaimaoTichengJijiabiao jijiabiao = new WaimaoTichengJijiabiao();
            jijiabiao.setTitlename(row.getCell(0).getStringCellValue());
            jijiabiao.setFifth(row.getCell(1).getStringCellValue());
            jijiabiao.setFourthly(row.getCell(2).getStringCellValue());
            jijiabiao.setThirdly(row.getCell(3).getStringCellValue());
            jijiabiao.setSecond(row.getCell(4).getStringCellValue());
            jijiabiao.setFirst(row.getCell(5).getStringCellValue());
            jijiabiaos.add(jijiabiao);
        }
        waimaoTichengJijiabiaoService.saveBatch(jijiabiaos);
        return ResultVo.success();
    }

    @ApiOperation(value = "查询基价表", notes = "参数:基价表信息")
    @PostMapping("/seleteTichengHuilv")
    public ResultVo seleteTichengHuilv(@ApiParam(value = "jdyRole", required = false)
                                       @RequestBody WaimaoTichengJijiabiao waimaoTichengJijiabiao){
        List<WaimaoTichengJijiabiao> list = waimaoTichengJijiabiaoService.seletelist(waimaoTichengJijiabiao);
        return  ResultVo.success(list);
    }

    @ApiOperation("更新基价表")
    @PostMapping("/updateTichengHuilv")
    public ResultVo updateTichengHuilv(@ApiParam(value = "waimaoTichengJijiabiao", required = true)
                                       @RequestBody WaimaoTichengJijiabiao waimaoTichengJijiabiao){
        boolean b = waimaoTichengJijiabiaoService.updateById(waimaoTichengJijiabiao);
        if(b){
            return ResultVo.success(waimaoTichengJijiabiao);
        }
        return ResultVo.failed();
    }


    @ApiOperation("新增基价表")
    @PostMapping("/addTichengHuilv")
    public ResultVo addTichengHuilv( @ApiParam(name = "waimaoTichengJijiabiao", required = true)
                                     @RequestBody WaimaoTichengJijiabiao waimaoTichengJijiabiao){
        boolean save = waimaoTichengJijiabiaoService.save(waimaoTichengJijiabiao);
        if(save){
            return ResultVo.success(waimaoTichengJijiabiao);
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除基价表")
    @DeleteMapping("/deleteTichengHuilv/{id}")
    public ResultVo deleteTichengHuilv(@ApiParam(value = "id", name = "基价表ID", required = true) @PathVariable String  id){
        boolean b = waimaoTichengJijiabiaoService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

}