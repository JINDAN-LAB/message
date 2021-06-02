package com.jindan.jdy.controller.waimao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.WaimaoHuikuanDto;
import com.jindan.jdy.common.pojo.WaimaoHuikuan;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.WorkbookUtils;
import com.jindan.jdy.service.waimao.WaimaoHuikuanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
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
* <p>说明： 规则API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年4月20日
*
*/
@Slf4j
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "外贸报表回款源数据")
@RestController
@RequestMapping("/waimaoHuikuan")
public class WaimaoHuikuanController{

    @Autowired
    WaimaoHuikuanService waimaoHuikuanService;

    @ApiOperation(value = "回款信息导入", notes = "参数:回款信息导入")
    @PostMapping("/addhuikuan")
    public ResultVo addhuikuan(@RequestParam("file") MultipartFile file) throws Exception {
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
        List<WaimaoHuikuan> jijiabiaos = new ArrayList<>();
        for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
            Row row = sheet.getRow(j);
            if(row==null||row.getFirstCellNum()==j){continue;}
            WaimaoHuikuan jijiabiao = new WaimaoHuikuan();
            if(row.getCell(0)!=null) {
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setFapiaohao(row.getCell(0).getStringCellValue());
            }
            if(row.getCell(1)!=null) {
                row.getCell(1).setCellType(Cell.CELL_TYPE_FORMULA);
                jijiabiao.setHuikuanriqi(row.getCell(1).getStringCellValue());
            }
            if(row.getCell(2)!=null) {
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setHuikuanjine((row.getCell(2).getStringCellValue()));
            }
            if(row.getCell(3)!=null) {
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setJiehuiyinhang((row.getCell(3).getStringCellValue()));
            }
            if(row.getCell(4)!=null) {
                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setZhoubie(row.getCell(4).getStringCellValue());
            }
            if(row.getCell(5)!=null) {
                row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setJine(row.getCell(5).getStringCellValue());
            }
            jijiabiaos.add(jijiabiao);
        }
        waimaoHuikuanService.saveBatch(jijiabiaos);
        return ResultVo.success();
    }

    @ApiOperation(value = "回款管理", notes = "参数:回款信息")
    @PostMapping("/seleteHuikuan")
    public ResultVo seleteHuikuan(@ApiParam(value = "waimaoHuikuan", required = false)
                                     @RequestBody WaimaoHuikuanDto waimaoHuikuan){
        Page<WaimaoHuikuan> list = waimaoHuikuanService.seletelist(waimaoHuikuan);
        return  ResultVo.success(list);
    }


    @ApiOperation("更新角色信息")
    @PostMapping("/updatehuikuan")
    public ResultVo updatehuikuan(@ApiParam(value = "waimaoHuikuan", required = true)
                                   @RequestBody WaimaoHuikuan waimaoHuikuan){
        boolean b = waimaoHuikuanService.updateById(waimaoHuikuan);
        if(b){
            return ResultVo.success(waimaoHuikuan);
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增角色信息")
    @PostMapping("/addhuikuans")
    public ResultVo addhuikuans(@ApiParam(name = "waimaoHuikuan", required = true)
                               @RequestBody WaimaoHuikuan waimaoHuikuan){
        boolean save = waimaoHuikuanService.save(waimaoHuikuan);
        if(save){
            return ResultVo.success(waimaoHuikuan);
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除角色信息")
    @DeleteMapping("/deletehuikuan/{id}")
    public ResultVo deletehuikuan(@ApiParam(value = "id", name = "角色ID", required = true) @PathVariable String  id){
        boolean b = waimaoHuikuanService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }



}