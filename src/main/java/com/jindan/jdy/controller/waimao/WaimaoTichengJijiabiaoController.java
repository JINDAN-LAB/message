package com.jindan.jdy.controller.waimao;

import com.jindan.jdy.common.pojo.WaimaoTichengJijiabiao;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.CommonUtils;
import com.jindan.jdy.controller.utils.WorkbookUtils;
import com.jindan.jdy.service.waimao.WaimaoTichengJijiabiaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "外贸提成基价表管理")
@RestController
@RequestMapping("/waimaoTichengJijiabiao")
public class WaimaoTichengJijiabiaoController{

    @Autowired
    WaimaoTichengJijiabiaoService waimaoTichengJijiabiaoService;

    @ApiOperation(value = "回款率批量导入", notes = "参数:发货信息批量导入")
    @PostMapping("addBatchTichengFahuo")
    public ResultVo addTichengHuilv(@RequestParam("file") MultipartFile file) throws Exception {
        String presenttime = CommonUtils.getPresenttime();
        //创建Excel工作薄
        Workbook work = WorkbookUtils.getWorkbook(file.getInputStream(),file.getOriginalFilename());
        if(null == work){
            throw new Exception("创建Excel工作薄为空！");
        }
        System.out.println("work.getNumberOfSheets();"+ work.getNumberOfSheets());
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

    @ApiOperation(value = "查询回款率", notes = "参数:查询包装信息")
    @PostMapping("/seleteTichengHuilv")
    public ResultVo seleteTichengHuilv(@ApiParam(value = "jdyRole", required = false)
                                       @RequestBody WaimaoTichengJijiabiao jdyRole){
        List<WaimaoTichengJijiabiao> list = waimaoTichengJijiabiaoService.seletelist(jdyRole);
        return  ResultVo.success(list);
    }

    @ApiOperation("更新回款率")
    @PostMapping("/updateTichengHuilv")
    public ResultVo updateTichengHuilv(@ApiParam(value = "jdyRole", required = true)
                                       @RequestBody WaimaoTichengJijiabiao jdyRole){
        boolean b = waimaoTichengJijiabiaoService.updateById(jdyRole);
        if(b){
            return ResultVo.success(jdyRole);
        }
        return ResultVo.failed();
    }


    @ApiOperation("新增回款率")
    @PostMapping("/addTichengHuilv")
    public ResultVo addTichengHuilv( @ApiParam(name = "jdyRole", required = true)
                                     @RequestBody WaimaoTichengJijiabiao jdyRole){
        boolean save = waimaoTichengJijiabiaoService.save(jdyRole);
        if(save){
            return ResultVo.success(jdyRole);
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除回款率")
    @DeleteMapping("/deleteTichengHuilv/{id}")
    public ResultVo deleteTichengHuilv(@ApiParam(value = "id", name = "角色ID", required = true) @PathVariable String  id){
        boolean b = waimaoTichengJijiabiaoService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

}