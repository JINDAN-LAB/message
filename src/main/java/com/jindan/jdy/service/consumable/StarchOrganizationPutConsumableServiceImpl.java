package com.jindan.jdy.service.consumable;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchOrganizationPutConsumableDto;
import com.jindan.jdy.common.pojo.StarchOrganizationPutConsumable;
import com.jindan.jdy.common.mapper.StarchOrganizationPutConsumableMapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(耗材资产服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class StarchOrganizationPutConsumableServiceImpl  extends ServiceImpl<StarchOrganizationPutConsumableMapper,StarchOrganizationPutConsumable> implements StarchOrganizationPutConsumableService  {

    @Autowired
    StarchOrganizationPutConsumableMapper starchOrganizationPutConsumableDao;

    @Override
    public boolean addJdyFlowCatalog(StarchOrganizationPutConsumable departmentSuggestDto) {
        return starchOrganizationPutConsumableDao.insert(departmentSuggestDto) > 0;
    }

    @Override
    public boolean updateStarchMaintainRegister(StarchOrganizationPutConsumable warehouseDepository) {
        return starchOrganizationPutConsumableDao.updateById(warehouseDepository) > 0 ;
    }

    @Override
    public PageInfo<StarchOrganizationPutConsumableDto> queryCatList(StarchOrganizationPutConsumableDto jdyFlowCatalog) {
        PageHelper.startPage(jdyFlowCatalog.getCurrentPage(), jdyFlowCatalog.getPageSize());
        List<StarchOrganizationPutConsumableDto> iPage =  starchOrganizationPutConsumableDao.queryRelevanceCatList(jdyFlowCatalog);
        PageInfo<StarchOrganizationPutConsumableDto> pageInfo = new PageInfo<StarchOrganizationPutConsumableDto>(iPage);
        return  pageInfo;
    }
}