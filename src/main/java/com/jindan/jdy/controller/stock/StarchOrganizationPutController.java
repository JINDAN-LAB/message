package com.jindan.jdy.controller.stock;

import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchClassifyPutConsumableDto;
import com.jindan.jdy.common.dto.StarchOrganizationPutDto;
import com.jindan.jdy.common.pojo.StarchOrganizationPut;
import com.jindan.jdy.common.pojo.WarehouseDepository;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.controller.utils.WorkbookUtils;
import com.jindan.jdy.service.stock.StarchOrganizationPutService;
import com.jindan.jdy.service.stock.WarehouseDepositoryService;
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
import java.util.Map;

/**
*
* <p>说明： 资产入库API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年8月24日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Slf4j
@Api(tags = "资产信息")
@RestController
@RequestMapping("/starchOrganizationPut")
public class StarchOrganizationPutController{

    @Autowired
    private StarchOrganizationPutService stockDepositoryService;

    @Autowired
    private WarehouseDepositoryService warehouseDepositoryService;

    @Autowired
    RedisUtil redisUtil;

    @ApiOperation(value = "资产信息导入", notes = "参数:资产信息导入")
    @PostMapping("/addFacilityexcle")
    public ResultVo addfahuo(@RequestParam("file") MultipartFile file) throws Exception {

        //创建Excel工作薄
        Workbook work = WorkbookUtils.getWorkbook(file.getInputStream(),file.getOriginalFilename());
        if(null == work){
            throw new Exception("创建Excel工作薄为空！");
        }
        List<WarehouseDepository> list =   warehouseDepositoryService.queryList();
        log.info("======“资产信息导入接口”开始执行======");
        log.info("work.getNumberOfSheets()的值为："+ work.getNumberOfSheets());
        Sheet sheet  = work.getSheetAt(0);
        if(sheet==null){
            throw new Exception("创建Excel工作薄为空！");
        }
        List<StarchOrganizationPut> jijiabiaos = new ArrayList<>();
        for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
            Row row = sheet.getRow(j);
            if(row==null||row.getFirstCellNum()==j){continue;}
            StarchOrganizationPut jijiabiao = new StarchOrganizationPut();
            if(row.getCell(0)!=null){
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setPropertyName(row.getCell(0).getStringCellValue());
            }
            if(row.getCell(1)!=null){
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            }
            if(row.getCell(2)!=null){
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setPropertyGuige((row.getCell(2).getStringCellValue()));
            }
            if(row.getCell(3)!=null){
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setPropertySn(String.valueOf(row.getCell(3).getStringCellValue()));
            }
            if(row.getCell(4)!=null){
                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setPropertyJine(row.getCell(4).getStringCellValue());
            }
            if(row.getCell(5)!=null){
                row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setCompanys(row.getCell(5).getStringCellValue());
            }
            if(row.getCell(6)!=null){
                row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setDepartments(row.getCell(6).getStringCellValue());
            }
            if(row.getCell(7)!=null){
                row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setAreas(row.getCell(7).getStringCellValue());
            }
            if(row.getCell(8)!=null){
                row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setStoreAreas((row.getCell(8).getStringCellValue()));
            }
            if(row.getCell(9)!=null){
                row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setManagerPerson((row.getCell(9).getStringCellValue()));
            }
            if(row.getCell(10)!=null){
                row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setBelongCompany((row.getCell(10).getStringCellValue()));
            }
            if(row.getCell(11)!=null){
                row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setPurchaseTime(row.getCell(11).getStringCellValue());
            }
            if(row.getCell(12)!=null){
                row.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setSuppliers(row.getCell(12).getStringCellValue());
            }
            if(row.getCell(13)!=null){
                row.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setServiceLife(row.getCell(13).getStringCellValue());
            }
            if(row.getCell(14)!=null){
                row.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setCreatorPerson((row.getCell(14).getStringCellValue()));
            }
            if(row.getCell(15)!=null){
                row.getCell(15).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setSourceOf((row.getCell(15).getStringCellValue()));
            }
            if(row.getCell(16)!=null){
                row.getCell(16).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setMaintainCycle(row.getCell(16).getStringCellValue());
            }
            if(row.getCell(17)!=null){
                row.getCell(17).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setSpareIs(row.getCell(17).getStringCellValue());
            }
            if(row.getCell(18)!=null){
                row.getCell(18).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setWeihao(row.getCell(18).getStringCellValue());
            }
            if(row.getCell(19)!=null){
                row.getCell(19).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setXuhao(row.getCell(19).getStringCellValue()); //润滑方式
            }
            if(row.getCell(20)!=null){
                row.getCell(20).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setBianma((row.getCell(20).getStringCellValue()));  //保养类型
            }
            if(row.getCell(21)!=null){
                row.getCell(21).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setCanshu((row.getCell(21).getStringCellValue()));
            }
            if(row.getCell(22)!=null){
                row.getCell(22).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setRunhua(row.getCell(22).getStringCellValue());
            }
            if(row.getCell(23)!=null){
                row.getCell(23).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setBaoyang(row.getCell(23).getStringCellValue());
            }
            if(row.getCell(24)!=null){
                row.getCell(24).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setRemarks(row.getCell(24).getStringCellValue());
            }
            if(row.getCell(25)!=null){
                row.getCell(25).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setWeixiuren((row.getCell(25).getStringCellValue()));
            }
            if(row.getCell(26)!=null){
                row.getCell(26).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setJianxiuren((row.getCell(26).getStringCellValue()));
            }
            if(row.getCell(27)!=null){
                row.getCell(27).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setJixiugong(row.getCell(27).getStringCellValue());
            }
//            list
            for (int i = 0; i <list.size() ; i++) {
                 if(list.get(i).getName().equals(row.getCell(1).getStringCellValue()) && row.getCell(1).getStringCellValue() != null){
                     jijiabiao.setPropertyType(list.get(i).getId());
                 }
            }
            jijiabiao.setStatus("闲置");
            jijiabiaos.add(jijiabiao);
        }
        stockDepositoryService.saveBatch(jijiabiaos);
        return ResultVo.success();
    }


    @ApiOperation("资产详细查询")
    @PostMapping("/seletStarchManageCheck")
    public ResultVo seleteJdyFlowCatalog(@ApiParam(value = "jdyFlowCatalog", required = true)
                                         @RequestBody StarchOrganizationPutDto jdyFlowCatalog){
        if(jdyFlowCatalog == null){
            if( redisUtil.hasKey("seletStarchManageCheck")){
                return  ResultVo.success(redisUtil.get("seletStarchManageCheck"));
            }
        }
        PageInfo<StarchOrganizationPutDto> starchOrganizationPutDtoPageInfo = stockDepositoryService.queryCatList(jdyFlowCatalog);
        return  ResultVo.success(starchOrganizationPutDtoPageInfo);
    }


    @ApiOperation("盘点查询查询未使用信息")
    @PostMapping("/seletStarcYIQuerenhManageCheck")
    public ResultVo seletStarcYIQuerenhManageCheck(@ApiParam(value = "jdyFlowCatalog", required = true)
                                         @RequestBody StarchOrganizationPutDto jdyFlowCatalog){
        if(jdyFlowCatalog == null){
            if( redisUtil.hasKey("seletStarchManageCheck")){
                return  ResultVo.success(redisUtil.get("seletStarchManageCheck"));
            }
        }
        PageInfo<StarchOrganizationPutDto> starchOrganizationPutDtoPageInfo = stockDepositoryService.queryYiquerenCatList(jdyFlowCatalog);
        return  ResultVo.success(starchOrganizationPutDtoPageInfo);
    }

    @ApiOperation("更新盘点管理")
    @PostMapping("/updateStarchManageCheck")
    public ResultVo updateStarchMaintainRegister( @ApiParam(value = "warehouseDepository", required = true)
                                                  @RequestBody StarchOrganizationPutDto warehouseDepository){
        boolean index = stockDepositoryService.updateStarchMaintainRegister(warehouseDepository);
        redisUtil.del("seletStarchManageCheck");
        return  ResultVo.success(index);
    }


    @ApiOperation(value = "增加盘点管理", notes = "参数:增加盘点管理")
    @PostMapping("/addStarchManageCheck")
    public ResultVo seleteCatalog(@ApiParam(name = "departmentSuggestDto", required = false)
                                  @RequestBody StarchOrganizationPutDto departmentSuggestDto){
        StarchOrganizationPut   departmentSuggest  = stockDepositoryService.addJdyFlowCatalog(departmentSuggestDto);
        redisUtil.del("seletStarchManageCheck");
        return  ResultVo.success(departmentSuggest);
    }


    @ApiOperation("删除盘点管理")
    @DeleteMapping("deleteStarchManageCheck/{id}")
    public ResultVo deletekeyPointPracticableService(@ApiParam(name = "id", value = "ID", required = true) @PathVariable String  id){
        boolean b = stockDepositoryService.removeById(id);
        if(b){
            redisUtil.del("seletStarchManageCheck");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


    @ApiOperation("根据设备ID查询维保信息")
    @PostMapping("/seletseleteShebeiID")
    public ResultVo seleteShebeiID(@ApiParam(value = "jdyFlowCatalog", required = true)
                                         @RequestBody StarchOrganizationPut  jdyFlowCatalog){
        List<StarchOrganizationPutDto> starchOrganizationPutDtoPageInfo = stockDepositoryService.queryDetailsWeibaoCatList(jdyFlowCatalog);
        return  ResultVo.success(starchOrganizationPutDtoPageInfo);
    }


    @ApiOperation("根据设备")
    @PostMapping("/selet")
    public ResultVo selet(@ApiParam(value = "jdyFlowCatalog", required = true)
                                   @RequestBody StarchOrganizationPutDto  jdyFlowCatalog){
        List<StarchClassifyPutConsumableDto> starchOrganizationPutDtoPageInfo = stockDepositoryService.queryDetailsHuizongCatList(jdyFlowCatalog);
        return  ResultVo.success(starchOrganizationPutDtoPageInfo);
    }

    @ApiOperation("查询资产超期")
    @PostMapping("/seletZichandaoqi")
    public ResultVo seletZichandaoqi(@ApiParam(value = "jdyFlowCatalog", required = true)
                                         @RequestBody StarchOrganizationPutDto jdyFlowCatalog){
        List<StarchOrganizationPutDto> starchOrganizationPutDtoPageInfo = stockDepositoryService.queryZichandaoqi(jdyFlowCatalog);
        return  ResultVo.success(starchOrganizationPutDtoPageInfo);
    }

    @ApiOperation("查询维保到期")
    @PostMapping("/seletWeibaodaoqi")
    public ResultVo seletWeibaodaoqi(@ApiParam(value = "jdyFlowCatalog", required = true)
                                     @RequestBody StarchOrganizationPutDto jdyFlowCatalog){
        List<StarchOrganizationPutDto> starchOrganizationPutDtoPageInfo = stockDepositoryService.queryWeibaodaoqi(jdyFlowCatalog);
        return  ResultVo.success(starchOrganizationPutDtoPageInfo);
    }


//  首先显示使用情况信息
    @ApiOperation("首先显示使用情况信息")
    @GetMapping("/seletShouyeDetails")
    public ResultVo seletShouyeDetails(){
        List<StarchOrganizationPutDto> starchOrganizationPutDtoPageInfo = stockDepositoryService.querZIchanShoiuye();
        return  ResultVo.success(starchOrganizationPutDtoPageInfo);
    }


    @ApiOperation("分类仓库资产情况表")
    @GetMapping("/seletFenleiShiyong")
    public ResultVo seletFenleiShiyong(){
        Map<String,List<StarchClassifyPutConsumableDto>> starchOrganizationPutDtoPageInfo = stockDepositoryService.querShiyongFenlei();
        return  ResultVo.success(starchOrganizationPutDtoPageInfo);
    }

    @ApiOperation("分类部门资产情况表")
    @GetMapping("/seletFenleiBumenShiyong")
    public ResultVo seletFenleiBumenShiyong(){
        Map<String,List<StarchClassifyPutConsumableDto>> starchOrganizationPutDtoPageInfo = stockDepositoryService.seletFenleiBumenShiyong();
        return  ResultVo.success(starchOrganizationPutDtoPageInfo);
    }


    @ApiOperation("各部门资产情况表")
    @GetMapping("/seletFenleiGeBumenShiyong")
    public ResultVo seletFenleiGeBumenShiyong(){
        Map<String,List<StarchClassifyPutConsumableDto>> starchOrganizationPutDtoPageInfo = stockDepositoryService.seletFenleiGeBumenShiyong();
        return  ResultVo.success(starchOrganizationPutDtoPageInfo);
    }




}