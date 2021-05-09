package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.mapper.StarchOrganizationPutSubtypeDao;
import com.jindan.jdy.common.pojo.StarchOrganizationPutSubtype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(资产子标题分类服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class StarchOrganizationPutSubtypeServiceImpl  extends ServiceImpl<StarchOrganizationPutSubtypeDao,StarchOrganizationPutSubtype> implements StarchOrganizationPutSubtypeService  {

    @Autowired
    StarchOrganizationPutSubtypeDao  starchOrganizationPutSubtypeDao;


    @Override
    public List<StarchOrganizationPutSubtype> queryCatList(StarchOrganizationPutSubtype jdyFlowCatalog) {
        QueryWrapper<StarchOrganizationPutSubtype> queryWrapper = new QueryWrapper<>();
        if( jdyFlowCatalog.getTid() !=null &&  !jdyFlowCatalog.getTid().isEmpty()){
            queryWrapper.eq("tid",jdyFlowCatalog.getTid());
        }
        if( jdyFlowCatalog.getNames() !=null && !jdyFlowCatalog.getNames().isEmpty() ){
            queryWrapper.like("names",jdyFlowCatalog.getNames());
        }
        if( jdyFlowCatalog.getParentId() !=null && !jdyFlowCatalog.getParentId().isEmpty() ){
            queryWrapper.like("parent_id",jdyFlowCatalog.getParentId());
        }
        return starchOrganizationPutSubtypeDao.selectList(queryWrapper);
    }

    @Override
    public boolean updateStarchMaintainRegister(StarchOrganizationPutSubtype warehouseDepository) {
        return starchOrganizationPutSubtypeDao.updateById(warehouseDepository) > 0;
    }

    @Override
    public boolean addJdyFlowCatalog(StarchOrganizationPutSubtype departmentSuggestDto) {
        return starchOrganizationPutSubtypeDao.insert(departmentSuggestDto) > 0;
    }
}