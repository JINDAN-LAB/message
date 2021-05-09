package com.jindan.jdy.controller.stock;

import com.jindan.jdy.common.dto.JdyPurchaseDto;
import com.jindan.jdy.common.dto.WarehouseSpecsDto;
import com.jindan.jdy.common.pojo.DepartmentFacility;
import com.jindan.jdy.common.pojo.JdyPurchase;
import com.jindan.jdy.common.pojo.WarehouseSpecs;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.enumerate.Status;
import com.jindan.jdy.service.department.DepartmentFacilityService;
import com.jindan.jdy.service.purchase.JdyPurchaseService;
import com.jindan.jdy.service.stock.WarehouseDepositoryService;
import com.jindan.jdy.service.stock.WarehouseSpecsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
*
* <p>说明： 仓库管理API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年4月20日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags =  "重构货物管理")
@RestController
@RequestMapping("/warehouseSpecs")
public class WarehouseSpecsController{

    @Autowired
    WarehouseSpecsService warehouseSpecsService;

    @Autowired
    WarehouseDepositoryService warehouseDepositoryService;

    @Autowired
    JdyPurchaseService jdyPurchaseService;

    @Autowired
    DepartmentFacilityService departmentFacilityService;

    @ApiOperation(value = "仓库货物查询", notes = "参数:仓库货物查询")
    @PostMapping("/seleteWarehouseAccess")
    public ResultVo seleteDepartment( @ApiParam(name = "departmentSuggestDto", required = false)
                                      @RequestBody WarehouseSpecsDto departmentSuggestDto ){
        List<WarehouseSpecs> list = warehouseSpecsService.seleteWarehouseSpecs(departmentSuggestDto);
        return  ResultVo.success(list);
    }

    @ApiOperation("更新建议信息")
    @PostMapping("updateWarehouseAccess")
    public ResultVo updatekeyPointPracticableService(@ApiParam(name = "userPermission", required = true)
                                                     @RequestBody WarehouseSpecs userPermission){
        boolean b = warehouseSpecsService.updateById(userPermission);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增信息")
    @PostMapping("addWarehouseAccess")
    public ResultVo addkeyPointPracticableService( @ApiParam(name = "userPermission", required = true)
                                                   @RequestBody   WarehouseSpecs userPermission){
        boolean save = warehouseSpecsService.save(userPermission);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除信息")
    @DeleteMapping("deleteWarehouseAccess/{id}")
    public ResultVo deletekeyPointPracticableService(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){
        boolean b = warehouseSpecsService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("设备入库")
    @PostMapping("inputWarehouseAccess")
    public ResultVo inputWarehouseAccess(@ApiParam(name = "userPermission", required = true)
                                         @RequestBody JdyPurchaseDto userPermission)  {
        JdyPurchase jdyPurchase = new JdyPurchase();
        jdyPurchase.setPurchaseId(userPermission.getPurchaseId());
        jdyPurchase.setStatics(Status.INPUTS.getStatus().toString());
        boolean b = jdyPurchaseService.updateById(jdyPurchase);
        if(b){
            for (int i = 0; i < userPermission.getListsCommodity().size() ; i++) {
                if(userPermission.getListsCommodity().get(i).getNum() !=null  && userPermission.getListsCommodity().get(i).getWarehouseId() != null){
                    int is =  warehouseSpecsService.updateNumSpecs(userPermission.getListsCommodity().get(i));

                }
            }
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


    @ApiOperation("设备出库")
    @PostMapping("outputWarehouseAccess")
    public ResultVo outputWarehouseAccess(@ApiParam(name = "userPermission", required = true)
                                          @RequestBody JdyPurchaseDto userPermission){
        List<DepartmentFacility> facilityList =new ArrayList<>();
        try {
            int s = 0;
            for (int i = 0; i <userPermission.getListsCommodity().size() ; i++) {
                if(userPermission.getListsCommodity().get(i).getNum() > 0 && userPermission.getListsCommodity().get(i).getWarehouseId() != null){
                    WarehouseSpecs byId = warehouseSpecsService.getById(userPermission.getListsCommodity().get(i).getWarehouseId());
                    if(byId.getNum() >= userPermission.getListsCommodity().get(i).getNum()){
                        DepartmentFacility  departmentFacility =new DepartmentFacility();
                        departmentFacility.setModel(userPermission.getListsCommodity().get(i).getModel());
                        departmentFacility.setCreateName(userPermission.getListsCommodity().get(i).getFacilityName());
                        departmentFacility.setCommodityName(userPermission.getListsCommodity().get(i).getCommodityName());
                        departmentFacility.setNumber(userPermission.getListsCommodity().get(i).getNum());
                        departmentFacility.setGoodsId(userPermission.getListsCommodity().get(i).getGoodsId());
                        departmentFacility.setDepartments(userPermission.getDepartments());
                        departmentFacility.setRateOfWork(userPermission.getListsCommodity().get(i).getPowers());
                        departmentFacility.setStates(userPermission.getListsCommodity().get(i).getStatus().toString());
                        departmentFacility.setRemarks(userPermission.getListsCommodity().get(i).getRemarks());
                        departmentFacility.setFrequencys(userPermission.getListsCommodity().get(i).getFrequencys());
                        departmentFacility.setRevolutionsPerMinute(userPermission.getListsCommodity().get(i).getRevolutionsPerMinute());
                        departmentFacility.setAllWeight(userPermission.getListsCommodity().get(i).getAllWeight().toString());
                        departmentFacility.setTexture(userPermission.getListsCommodity().get(i).getTexture());
                        departmentFacility.setLubrication(userPermission.getListsCommodity().get(i).getLubrication());
                        departmentFacility.setParameters(userPermission.getListsCommodity().get(i).getParameters());
                        departmentFacility.setParentId(userPermission.getListsCommodity().get(i).getParentId());
                        departmentFacility.setWarehouseId(userPermission.getListsCommodity().get(i).getWarehouseId());
                        departmentFacility.setBrand(userPermission.getListsCommodity().get(i).getBrand());
                        departmentFacility.setNuit(userPermission.getListsCommodity().get(i).getNuit());
                        facilityList.add(departmentFacility);
                        int is =  warehouseSpecsService.updateOutNumSpecs(userPermission.getListsCommodity().get(i));
                        s++;
                    }
                }
            }

            if(s == userPermission.getListsCommodity().size() && s > 0){
                boolean b = departmentFacilityService.saveBatch(facilityList);
                if(b){
                    JdyPurchase jdyPurchase =new JdyPurchase();
                    jdyPurchase.setPurchaseId(userPermission.getPurchaseId());
                    jdyPurchase.setStatics(Status.OUTPUTS.getStatus().toString());
                    jdyPurchaseService.updateById(jdyPurchase);
                    return ResultVo.success();
                }
                return ResultVo.failed();
            }else{
                throw  new Exception();
            }
        }catch (Exception e){
          e.printStackTrace();
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return ResultVo.failed();
    }

}