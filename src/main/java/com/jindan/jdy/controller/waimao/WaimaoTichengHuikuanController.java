package com.jindan.jdy.controller.waimao;

import com.jindan.jdy.common.pojo.WaimaoTichengHuikuan;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.CommonUtils;
import com.jindan.jdy.controller.utils.WorkbookUtils;
import com.jindan.jdy.service.waimao.WaimaoTichengHuikuanService;
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
@Api(tags = "外贸提成回款管理")
@RestController
@RequestMapping("/waimaoTichengHuikuan")
public class WaimaoTichengHuikuanController{

    @Autowired
    WaimaoTichengHuikuanService waimaoTichengHuikuanService;

    @ApiOperation(value = "外贸提成回款批量导入", notes = "参数:发货信息批量导入")
    @PostMapping("addBatchTichengFahuo")
    public ResultVo addTichengFahuo(@RequestParam("file") MultipartFile file) throws Exception {
        log.info("======“外贸提成回款批量导入接口”开始执行======");
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
        List<WaimaoTichengHuikuan> jijiabiaos = new ArrayList<>();
        for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++){
            Row row = sheet.getRow(j);
            if(row==null||row.getFirstCellNum()==j){continue;}
            WaimaoTichengHuikuan jijiabiao = new WaimaoTichengHuikuan();
            jijiabiao.setFapiaohao(row.getCell(0).getStringCellValue());
            jijiabiao.setHuikuanriqi(row.getCell(1).getStringCellValue());
            jijiabiao.setHuikuanjine( (row.getCell(2).getStringCellValue()));
            jijiabiao.setJiehuiyinhang(row.getCell(3).getStringCellValue());
            jijiabiao.setZhoubie( (row.getCell(4).getStringCellValue()));
            jijiabiao.setJine(row.getCell(5).getStringCellValue());
            jijiabiao.setShijishiyong( (row.getCell(6).getStringCellValue()));
            jijiabiao.setYuliu3(row.getCell(7).getStringCellValue());
            jijiabiaos.add(jijiabiao);
        }
        waimaoTichengHuikuanService.saveBatch(jijiabiaos);
        return ResultVo.success();
    }

    @ApiOperation(value = "查询外贸提成回款", notes = "参数:查询包装信息")
    @PostMapping("/seleteTichengFahuo")
    public ResultVo seleteTichengFahuo(@ApiParam(value = "jdyRole", required = false)
                                       @RequestBody WaimaoTichengHuikuan jdyRole){
        List<WaimaoTichengHuikuan> list = waimaoTichengHuikuanService.seletelist(jdyRole);
        return  ResultVo.success(list);
    }

    @ApiOperation("更新外贸提成回款")
    @PostMapping("/updateTichengFahuo")
    public ResultVo updateTichengFahuo(@ApiParam(value = "jdyRole", required = true)
                                       @RequestBody WaimaoTichengHuikuan jdyRole){
        boolean b = waimaoTichengHuikuanService.updateById(jdyRole);
        if(b){
            return ResultVo.success(jdyRole);
        }
        return ResultVo.failed();
    }


    @ApiOperation("新增外贸提成回款")
    @PostMapping("/addTichengFahuo")
    public ResultVo addTichengFahuo( @ApiParam(name = "jdyRole", required = true)
                                     @RequestBody WaimaoTichengHuikuan jdyRole){
        log.info("======“新增外贸提成回款接口”开始执行======");
        log.info("jdyRole的值为："+jdyRole);
        boolean save = waimaoTichengHuikuanService.save(jdyRole);
        if(save){
            return ResultVo.success(jdyRole);
        }
        return ResultVo.failed();
    }


    @ApiOperation("删除外贸提成回款")
    @DeleteMapping("/deleteTichengFahuo/{id}")
    public ResultVo deleteTichengFahuo(@ApiParam(value = "id", name = "角色ID", required = true) @PathVariable String  id){
        boolean b = waimaoTichengHuikuanService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }




}