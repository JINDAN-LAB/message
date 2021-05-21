package com.jindan.jdy.controller.stock;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchMaintainRegisterDto;
import com.jindan.jdy.common.pojo.JdyUser;
import com.jindan.jdy.common.pojo.StarchMaintainRegister;
import com.jindan.jdy.common.pojo.StarchOrganizationPut;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.stock.StarchMaintainRegisterService;
import com.jindan.jdy.service.stock.StarchOrganizationPutService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
*
* <p>说明： 资产维修登记API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年8月24日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Slf4j
@Api(tags = "资产维修登记")
@RestController
@RequestMapping("/starchMaintainRegister")
public class StarchMaintainRegisterController{

    @Autowired
    private StarchMaintainRegisterService stockDepositoryService;

    @Autowired
    private StarchOrganizationPutService starchOrganizationPutService;

    @Autowired
    RedisUtil redisUtil;

    @ApiOperation("根据设备ID查询维修记录信息")
    @GetMapping("/seletByZichan/{id}")
    public ResultVo seletByZichan(@ApiParam(value = "id", required = true)
                                  @PathVariable String id){
        List<StarchMaintainRegister> list  = stockDepositoryService.seletByZichan(id);
        return  ResultVo.success(list);
    }


    @ApiOperation("资产维修登记")
    @PostMapping("/seletRelevanceStarchMaintainRegisterDto")
    public ResultVo seletRelevanceStarchMaintainRegisterDto(@ApiParam(value = "jdyFlowCatalog", required = true)
                                                    @RequestBody StarchMaintainRegisterDto jdyFlowCatalog){
        PageInfo<StarchMaintainRegisterDto> list  = stockDepositoryService.queryRelevanceCatList(jdyFlowCatalog);
        return  ResultVo.success(list);
    }


    @ApiOperation("查询报修人待评价信息")
    @PostMapping("/seletBaoxiurenDto")
    public ResultVo seletBaoxiurenDto(@ApiParam(value = "jdyFlowCatalog", required = true)
                             @RequestBody StarchMaintainRegisterDto jdyFlowCatalog){
        PageInfo<StarchMaintainRegister> list  = stockDepositoryService.queryWBaoxiurenRelevanceCatList(jdyFlowCatalog);
        return  ResultVo.success(list);
    }


    @ApiOperation("查询维修人待评价信息")
    @PostMapping("/seletWeixiurenDta")
    public ResultVo seletWeixiurenDta(@ApiParam(value = "jdyFlowCatalog", required = true)
                                                            @RequestBody StarchMaintainRegisterDto jdyFlowCatalog){
        PageInfo<StarchMaintainRegister> list  = stockDepositoryService.queryWeixiurenRelevanceCatList(jdyFlowCatalog);
        return  ResultVo.success(list);
    }


    @ApiOperation("查询资产维修登记")
    @PostMapping("/seleteJdyFlowCatalog")
    public ResultVo seleteJdyFlowCatalog(@ApiParam(value = "jdyFlowCatalog", required = true)
                                         @RequestBody StarchMaintainRegisterDto jdyFlowCatalog){
        Page<StarchMaintainRegister> list  = stockDepositoryService.queryCatList(jdyFlowCatalog);
        return  ResultVo.success(list);
    }


    @ApiOperation("更新资产维修登记")
    @PostMapping("/updateStarchMaintainRegister")
    public ResultVo updateStarchMaintainRegister( @ApiParam(value = "warehouseDepository", required = true)
                                                      @RequestBody  StarchMaintainRegisterDto warehouseDepository){
        boolean index = stockDepositoryService.updateStarchMaintainRegister(warehouseDepository);
        return  ResultVo.success(index);
    }

    @ApiOperation(value = "增加更新资产维修登记", notes = "参数:增加资产维修登记")
    @PostMapping("/addJdyFlowCatalog")
    public ResultVo seleteCatalog(@ApiParam(name = "departmentSuggestDto", required = false)
                                  @RequestBody StarchMaintainRegisterDto departmentSuggestDto){
        log.info("======“增加更新资产维修登记接口”开始执行======");
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        log.info("传递的参数departmentSuggestDto值为："+departmentSuggestDto);
        if(departmentSuggestDto.getWxstatus().equals("正常")){
            for (int i = 0; i <departmentSuggestDto.getPutList().size() ; i++) {
                StarchOrganizationPut  starchOrganizationPut =new StarchOrganizationPut();
                starchOrganizationPut.setOid(departmentSuggestDto.getPutList().get(i).getOid());
                starchOrganizationPut.setStatus("维修中");
                starchOrganizationPut.setShangcibaoyang(formatter.format(currentTime));
                starchOrganizationPutService.updateById(starchOrganizationPut);
            }
        }
        boolean list  = stockDepositoryService.addJdyFlowCatalog(departmentSuggestDto);
        return  ResultVo.success(list);
    }


    @ApiOperation("分配机修工维修登记")
    @PostMapping("/updateFenpeiMaintainRegister")
    public ResultVo updateFenpeiMaintainRegister(@ApiParam(value = "warehouseDepository", required = true)
                                                 @RequestBody  StarchMaintainRegister warehouseDepository){
        boolean index = stockDepositoryService.updateFenpeiMaintainRegister(warehouseDepository);
        return  ResultVo.success(index);
    }

    @ApiOperation(value = "单一更新维修登记", notes = "参数:单一更新维修登记")
    @PostMapping("/updatesSingleJdyFlowCatalog")
    public ResultVo updatesSingleJdyFlowCatalog(@ApiParam(name = "departmentSuggestDto", required = false)
                                  @RequestBody StarchMaintainRegister  departmentSuggestDto){
        log.info("======“单一更新维修登记接口”开始执行======");
        log.info("传递的参数departmentSuggestDto值为："+departmentSuggestDto);
        boolean list  = stockDepositoryService.updateSingleJdyFlowCatalog(departmentSuggestDto);
        return  ResultVo.success(list);
    }

    @ApiOperation(value = "增加资产维修记录", notes = "参数:增加资产维修记录")
    @PostMapping("/addJdyaddFlowCatalog")
    public ResultVo seleteSddCatalog(@ApiParam(name = "departmentSuggestDto", required = false)
                                  @RequestBody StarchMaintainRegisterDto departmentSuggestDto){
        log.info("======“增加资产维修记录接口”开始执行======");
        log.info("传递的参数departmentSuggestDto值为："+departmentSuggestDto);
        boolean list  = stockDepositoryService.addJdyaddFlowCatalog(departmentSuggestDto);
        return  ResultVo.success(list);
    }


    @ApiOperation("删除资产维修登记")
    @DeleteMapping("deleteStockDepository/{id}")
    public ResultVo deletekeyPointPracticableService(@ApiParam(name = "id", value = "ID", required = true) @PathVariable String  id){
        boolean b = stockDepositoryService.removeDetailsById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("根据人员查询需要检修的设备")
    @PostMapping("/seletJianxiuMaintainRegisterDto")
    public ResultVo seletJianxiuMaintainRegisterDto(@ApiParam(value = "jdyFlowCatalog", required = true)
                                                    @RequestBody  JdyUser  jdyUser) throws Exception {
        StarchOrganizationPut starchOrganizationPut = new StarchOrganizationPut();
        starchOrganizationPut.setJianxiuren(jdyUser.getUsername());
        List<StarchOrganizationPut> list  =  stockDepositoryService.seleteOrganizationPut(starchOrganizationPut);
        return  ResultVo.success(list);
    }





}