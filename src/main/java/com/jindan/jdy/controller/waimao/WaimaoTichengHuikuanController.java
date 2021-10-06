package com.jindan.jdy.controller.waimao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.WaimaoTichengHuikuanDto;
import com.jindan.jdy.common.pojo.WaimaoTichengHuikuan;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.CommonUtils;
import com.jindan.jdy.controller.utils.WorkbookUtils;
import com.jindan.jdy.service.waimao.WaimaoTichengHuikuanService;
import com.jindan.jdy.utils.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
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
        for (int j = 1; j <= sheet.getLastRowNum(); j++){
            Row row = sheet.getRow(j);
            if(row.getCell(0)==null || StringUtils.isEmpty(row.getCell(0).getStringCellValue())){
                break;
            }
            WaimaoTichengHuikuan jijiabiao = new WaimaoTichengHuikuan();
            if(row.getCell(0) != null){
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setFapiaohao(row.getCell(0).getStringCellValue());
            }

            if(row.getCell(1) != null){
                switch (row.getCell(1).getCellType()){
                    case HSSFCell.CELL_TYPE_NUMERIC:
                        if(row.getCell(1) != null){
                            Cell cell = row.getCell(1);
                            cell.setCellType(1);
                            String huankuanDate = cell.getStringCellValue() + "";
                            huankuanDate = DateUtils.getFormatDate(huankuanDate);
                            jijiabiao.setHuikuanriqi( huankuanDate);
                        }
                        break;
                    case HSSFCell.CELL_TYPE_STRING:
                        if(row.getCell(1) != null){
                            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                            // 转换
                            String huankuanDate = row.getCell(1).getStringCellValue();
                            huankuanDate = DateUtils.getFormatDate(huankuanDate);
                            jijiabiao.setHuikuanriqi(huankuanDate);
                        }
                        break;
                }
            }
            if(row.getCell(2) != null){
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setHuikuanjine(row.getCell(2).getStringCellValue());
            }
            if(row.getCell(3) != null){
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setJiehuiyinhang(row.getCell(3).getStringCellValue());
            }
            if(row.getCell(4) != null){
                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setZhoubie(row.getCell(4).getStringCellValue());
            }
            if(row.getCell(5) != null){
                row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setJine(row.getCell(5).getStringCellValue());
            }
            if(row.getCell(6) != null){
                row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setShijishiyong(row.getCell(6).getStringCellValue());
            }
            if(row.getCell(7) != null){
                row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setYuliu3(row.getCell(7).getStringCellValue());
            }
            jijiabiaos.add(jijiabiao);
        }
        waimaoTichengHuikuanService.saveBatch(jijiabiaos);
        return ResultVo.success();
    }

    @ApiOperation(value = "查询外贸提成回款", notes = "参数:查询包装信息")
    @PostMapping("/selectTichengHuikuan")
    public ResultVo seleteTichengFahuo(@ApiParam(value = "jdyRole", required = false)
                                       @RequestBody WaimaoTichengHuikuanDto huikuanDto){
        Page<WaimaoTichengHuikuan> list = waimaoTichengHuikuanService.seletelist(huikuanDto);
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