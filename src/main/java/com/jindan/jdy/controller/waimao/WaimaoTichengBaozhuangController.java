package com.jindan.jdy.controller.waimao;

import com.jindan.jdy.common.pojo.WaimaoTichengBaozhuang;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.WorkbookUtils;
import com.jindan.jdy.service.waimao.WaimaoTichengBaozhuangService;
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
@Api(tags = "外贸提成包装管理")
@RestController
@RequestMapping("/waimaoTichengBaozhuang")
public class WaimaoTichengBaozhuangController{

    @Autowired
    WaimaoTichengBaozhuangService waimaoTichengBaozhuangService;

    @ApiOperation(value = "包装批量导入", notes = "参数:包装批量导入")
    @PostMapping("addBatchTichengBaozhuang")
    public ResultVo addfahuo(@RequestParam("file") MultipartFile file) throws Exception {

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
        List<WaimaoTichengBaozhuang> jijiabiaos = new ArrayList<>();
        for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
            Row row = sheet.getRow(j);
            if(row==null||row.getFirstCellNum()==j){continue;}
            WaimaoTichengBaozhuang jijiabiao = new WaimaoTichengBaozhuang();
            jijiabiao.setBzw(row.getCell(0).getStringCellValue());
            jijiabiao.setBaozhuangjia(Float.valueOf(row.getCell(1).getStringCellValue()));
            jijiabiaos.add(jijiabiao);
        }
        waimaoTichengBaozhuangService.saveBatch(jijiabiaos);
        return ResultVo.success();
    }

    @ApiOperation(value = "查询包装信息", notes = "参数:查询包装信息")
    @PostMapping("/seleteTichengBaozhuang")
    public ResultVo seleteTichengBaozhuang(@ApiParam(value = "waimaoTichengBaozhuang", required = false)
                                      @RequestBody WaimaoTichengBaozhuang waimaoTichengBaozhuang){
        List<WaimaoTichengBaozhuang> list = waimaoTichengBaozhuangService.seletelist(waimaoTichengBaozhuang);
        return  ResultVo.success(list);
    }


    @ApiOperation("更新包装信息")
    @PostMapping("/updateTichengBaozhuang")
    public ResultVo updateTichengBaozhuang(@ApiParam(value = "waimaoTichengBaozhuang", required = true)
                                   @RequestBody WaimaoTichengBaozhuang waimaoTichengBaozhuang){
        boolean b = waimaoTichengBaozhuangService.updateById(waimaoTichengBaozhuang);
        if(b){
            return ResultVo.success(waimaoTichengBaozhuang);
        }
        return ResultVo.failed();
    }


    @ApiOperation("新增外贸提成包装信息")
    @PostMapping("/addTichengBaozhuang")
    public ResultVo addTichengBaozhuang( @ApiParam(name = "waimaoTichengBaozhuang", required = true)
                                    @RequestBody WaimaoTichengBaozhuang waimaoTichengBaozhuang){
        boolean save = waimaoTichengBaozhuangService.save(waimaoTichengBaozhuang);
        if(save){
            return ResultVo.success(waimaoTichengBaozhuang);
        }
        return ResultVo.failed();
    }


    @ApiOperation("删除外贸包装信息")
    @DeleteMapping("/deleteTichengBaozhuang/{id}")
    public ResultVo deleteTichengBaozhuang(@ApiParam(value = "id", name = "包装ID", required = true) @PathVariable String  id){
        boolean b = waimaoTichengBaozhuangService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }



}