package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jindan.jdy.common.pojo.StarchOrganizationPutSubtype;
import com.jindan.jdy.common.pojo.StarchOrganizationPutSubtypeAsset;
import com.jindan.jdy.common.mapper.StarchOrganizationPutSubtypeAssetDao;
import com.jindan.jdy.service.stock.StarchOrganizationPutSubtypeAssetService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(资产子设备信息服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class StarchOrganizationPutSubtypeAssetServiceImpl  extends ServiceImpl<StarchOrganizationPutSubtypeAssetDao,StarchOrganizationPutSubtypeAsset> implements StarchOrganizationPutSubtypeAssetService  {

    @Autowired
    StarchOrganizationPutSubtypeAssetDao starchOrganizationPutSubtypeAssetDao;

    @Override
    public boolean updateStarchMaintainRegister(StarchOrganizationPutSubtypeAsset warehouseDepository) {
        return starchOrganizationPutSubtypeAssetDao.updateById(warehouseDepository) > 0;
    }

    @Override
    public boolean addJdyFlowCatalog(StarchOrganizationPutSubtypeAsset departmentSuggestDto) {
        return starchOrganizationPutSubtypeAssetDao.insert(departmentSuggestDto) > 0;
    }

    @Override
    public List<StarchOrganizationPutSubtypeAsset> queryCatList(StarchOrganizationPutSubtypeAsset jdyFlowCatalog) {

        QueryWrapper<StarchOrganizationPutSubtypeAsset> queryWrapper = new QueryWrapper<StarchOrganizationPutSubtypeAsset>();
        if( jdyFlowCatalog.getTsid() !=null &&  !jdyFlowCatalog.getTsid().isEmpty()){
            queryWrapper.eq("tsid",jdyFlowCatalog.getTsid());
        }
        if( jdyFlowCatalog.getParentIds() !=null && !jdyFlowCatalog.getParentIds().isEmpty() ){
            queryWrapper.like("parent_ids",jdyFlowCatalog.getParentIds());
        }
        if( jdyFlowCatalog.getSnCode() !=null && !jdyFlowCatalog.getSnCode().isEmpty() ){
            queryWrapper.like("sn_code",jdyFlowCatalog.getSnCode());
        }
        if( jdyFlowCatalog.getCanshu() !=null && !jdyFlowCatalog.getCanshu().isEmpty() ){
            queryWrapper.like("canshu",jdyFlowCatalog.getCanshu());
        }
        if( jdyFlowCatalog.getNames() !=null && !jdyFlowCatalog.getNames().isEmpty() ){
            queryWrapper.like("names",jdyFlowCatalog.getNames());
        }
        if( jdyFlowCatalog.getRemarks() !=null && !jdyFlowCatalog.getRemarks().isEmpty() ){
            queryWrapper.like("remarks",jdyFlowCatalog.getRemarks());
        }
        return starchOrganizationPutSubtypeAssetDao.selectList(queryWrapper);

    }
}