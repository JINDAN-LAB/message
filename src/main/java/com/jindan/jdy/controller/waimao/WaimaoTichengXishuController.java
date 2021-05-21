package com.jindan.jdy.controller.waimao;

import com.jindan.jdy.common.pojo.WaimaoTichengXishu;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.CommonUtils;
import com.jindan.jdy.controller.utils.WorkbookUtils;
import com.jindan.jdy.service.waimao.WaimaoTichengXishuService;
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
@Api(tags = "外贸提成系数管理")
@RestController
@RequestMapping("/waimaoTichengXishu")
public class WaimaoTichengXishuController{

    @Autowired
    WaimaoTichengXishuService waimaoTichengXishuService;

    @ApiOperation(value = "回款率批量导入", notes = "参数:发货信息批量导入")
    @PostMapping("addBatchTichengXishu")
    public ResultVo addTichengXishu(@RequestParam("file") MultipartFile file) throws Exception {
        log.info("======“回款率批量导入接口”开始执行======");
        String presenttime = CommonUtils.getPresenttime();
        Workbook work = WorkbookUtils.getWorkbook(file.getInputStream(),file.getOriginalFilename());
        if(null == work){
            throw new Exception("创建Excel工作薄为空！");
        }
        log.info("work.getNumberOfSheets()的值为："+ work.getNumberOfSheets());
        Sheet sheet  = work.getSheetAt(0);
        if(sheet==null){
            throw new Exception("创建Excel工作薄为空！");
        }
        List<WaimaoTichengXishu> jijiabiaos = new ArrayList<>();
        for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++){
            Row row = sheet.getRow(j);
            if(row==null||row.getFirstCellNum()==j){continue;}
            WaimaoTichengXishu jijiabiao = new WaimaoTichengXishu();
            jijiabiao.setXiankuan(row.getCell(0).getStringCellValue());
            jijiabiao.setQiyunxian(row.getCell(1).getStringCellValue());
            jijiabiao.setChaoqiqiyunxian(row.getCell(2).getStringCellValue());
            jijiabiao.setHuoyunxian(row.getCell(3).getStringCellValue());
            jijiabiao.setChaoqihuoyunxian(row.getCell(4).getStringCellValue());
            jijiabiao.setBuchaoqi1(row.getCell(5).getStringCellValue());
            jijiabiao.setBuchaoqi2(row.getCell(6).getStringCellValue());
            jijiabiao.setChaoqi1(row.getCell(7).getStringCellValue());
            jijiabiao.setChaoqi2(row.getCell(8).getStringCellValue());
            jijiabiao.setJingshui1(row.getCell(9).getStringCellValue());
            jijiabiao.setJingshui2(row.getCell(10).getStringCellValue());
            jijiabiao.setJingshui3(row.getCell(11).getStringCellValue());
            jijiabiao.setJingshui3b(row.getCell(12).getStringCellValue());
            jijiabiao.setXinkehu1(row.getCell(13).getStringCellValue());
            jijiabiao.setXinkehu2(row.getCell(14).getStringCellValue());
            jijiabiao.setXinkehu3(row.getCell(15).getStringCellValue());
            jijiabiao.setXinkehu3b(row.getCell(16).getStringCellValue());
            jijiabiao.setWanwu(row.getCell(17).getStringCellValue());
            jijiabiao.setWaner(row.getCell(18).getStringCellValue());
            jijiabiao.setYuefen(row.getCell(19).getStringCellValue());
            jijiabiao.setChenglixibuchaoqi(row.getCell(20).getStringCellValue());
            jijiabiao.setChenglixichaoqi(row.getCell(21).getStringCellValue());
            jijiabiaos.add(jijiabiao);
        }
        waimaoTichengXishuService.saveBatch(jijiabiaos);
        return ResultVo.success();
    }

    @ApiOperation(value = "查询回款率", notes = "参数:查询包装信息")
    @PostMapping("/seleteTichengXishu")
    public ResultVo seleteTichengXishu(@ApiParam(value = "jdyRole", required = false)
                                       @RequestBody WaimaoTichengXishu jdyRole){
        List<WaimaoTichengXishu> list = waimaoTichengXishuService.seletelist(jdyRole);
        return  ResultVo.success(list);
    }

    @ApiOperation("更新回款率")
    @PostMapping("/updateTichengXishu")
    public ResultVo updateTichengXishu(@ApiParam(value = "jdyRole", required = true)
                                       @RequestBody WaimaoTichengXishu jdyRole){
        boolean b = waimaoTichengXishuService.updateById(jdyRole);
        if(b){
            return ResultVo.success(jdyRole);
        }
        return ResultVo.failed();
    }


    @ApiOperation("新增回款率")
    @PostMapping("/addTichengXishu")
    public ResultVo addTichengXishu( @ApiParam(name = "jdyRole", required = true)
                                     @RequestBody WaimaoTichengXishu jdyRole){
        boolean save = waimaoTichengXishuService.save(jdyRole);
        if(save){
            return ResultVo.success(jdyRole);
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除回款率")
    @DeleteMapping("/deleteTichengXishu/{id}")
    public ResultVo deleteTichengXishu(@ApiParam(value = "id", name = "角色ID", required = true) @PathVariable String  id){
        log.info("======“删除回款率接口”开始执行======");
        log.info("删除回款系数id的值为："+id);
        boolean b = waimaoTichengXishuService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

}