package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchOrganizationPutDto;
import com.jindan.jdy.common.pojo.StarchMaintainRegister;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.dto.StarchMaintainRegisterDto;
import com.jindan.jdy.common.pojo.StarchOrganizationPut;

import java.util.List;

/**   
 * @Description:TODO(资产维修登记服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface StarchMaintainRegisterService extends IService<StarchMaintainRegister> {

    Page<StarchMaintainRegister> queryCatList(StarchMaintainRegisterDto jdyFlowCatalog);

//    修改信息
    boolean updateStarchMaintainRegister(StarchMaintainRegisterDto warehouseDepository);

//    增加资产维修信息
    boolean addJdyFlowCatalog(StarchMaintainRegisterDto departmentSuggestDto);

    PageInfo<StarchMaintainRegisterDto> queryRelevanceCatList(StarchMaintainRegisterDto jdyFlowCatalog);

    boolean removeDetailsById(String id);

    List<StarchMaintainRegister> seletByZichan(String id);

    List<StarchOrganizationPut> seleteOrganizationPut(StarchOrganizationPut starchOrganizationPut) throws Exception;

    boolean addJdyaddFlowCatalog(StarchMaintainRegisterDto departmentSuggestDto);


    PageInfo<StarchMaintainRegister> queryWBaoxiurenRelevanceCatList(StarchMaintainRegisterDto jdyFlowCatalog);

    PageInfo<StarchMaintainRegister> queryWeixiurenRelevanceCatList(StarchMaintainRegisterDto jdyFlowCatalog);

    boolean updateSingleJdyFlowCatalog(StarchMaintainRegister departmentSuggestDto);

//    修改保修信息 分配机修工
    boolean updateFenpeiMaintainRegister(StarchMaintainRegister warehouseDepository);
}