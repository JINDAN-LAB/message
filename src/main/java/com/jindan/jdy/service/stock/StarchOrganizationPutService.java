package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchClassifyPutConsumableDto;
import com.jindan.jdy.common.dto.StarchOrganizationPutDto;
import com.jindan.jdy.common.pojo.StarchOrganizationPut;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**   
 * @Description:TODO(资产入库服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface StarchOrganizationPutService extends IService<StarchOrganizationPut> {

    PageInfo<StarchOrganizationPutDto> queryCatList(StarchOrganizationPutDto jdyFlowCatalog);

    boolean updateStarchMaintainRegister(StarchOrganizationPutDto warehouseDepository);

    StarchOrganizationPut  addJdyFlowCatalog(StarchOrganizationPutDto departmentSuggestDto);

    List<StarchOrganizationPutDto> queryDetailsWeibaoCatList(StarchOrganizationPut jdyFlowCatalog);

//    资产分类汇总表
    List<StarchClassifyPutConsumableDto> queryDetailsHuizongCatList(StarchOrganizationPutDto jdyFlowCatalog);

//    分类查询
    Map<String,List<StarchClassifyPutConsumableDto>> querShiyongFenlei();
//    资产到期
    List<StarchOrganizationPutDto> queryZichandaoqi(StarchOrganizationPutDto jdyFlowCatalog);

    List<StarchOrganizationPutDto> queryWeibaodaoqi(StarchOrganizationPutDto jdyFlowCatalog);

//    首页信息的信息
    List<StarchOrganizationPutDto> querZIchanShoiuye();

    PageInfo<StarchOrganizationPutDto> queryYiquerenCatList(StarchOrganizationPutDto jdyFlowCatalog);

    Map<String, List<StarchClassifyPutConsumableDto>> seletFenleiBumenShiyong();

    Map<String, List<StarchClassifyPutConsumableDto>> seletFenleiGeBumenShiyong();
}