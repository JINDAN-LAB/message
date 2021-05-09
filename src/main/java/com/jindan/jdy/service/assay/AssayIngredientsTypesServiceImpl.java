package com.jindan.jdy.service.assay;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.AssayFacilityRecordDto;
import com.jindan.jdy.common.dto.AssayIngredientsTypesDto;
import com.jindan.jdy.common.mapper.AssayPutReportFormsResultDao;
import com.jindan.jdy.common.pojo.AssayIngredientsTypes;
import com.jindan.jdy.common.mapper.AssayIngredientsTypesDao;
import com.jindan.jdy.common.pojo.StarchOrganizationAlteration;
import com.jindan.jdy.service.assay.AssayIngredientsTypesService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**   
 * @Description:TODO(检测-分类表服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class AssayIngredientsTypesServiceImpl  extends ServiceImpl<AssayIngredientsTypesDao,AssayIngredientsTypes> implements AssayIngredientsTypesService  {

    @Autowired
    AssayIngredientsTypesDao assayIngredientsTypesDao;


    @Override
    public Page<AssayIngredientsTypes> seleteAssayIngredientsTypes(AssayIngredientsTypesDto jdyRole) {

        Page<AssayIngredientsTypes> iPage =new Page<>(jdyRole.getCurrentPage(),jdyRole.getPageSize());
        QueryWrapper<AssayIngredientsTypes> queryWrapper = new QueryWrapper<>();
        if( jdyRole.getYfid() !=null &&  !jdyRole.getYfid().isEmpty()){
            queryWrapper.eq("yfid",jdyRole.getYfid());
        }
        if( jdyRole.getOrders() !=null && !jdyRole.getOrders().isEmpty() ){
            queryWrapper.like("orders",jdyRole.getOrders());
        }
        if( jdyRole.getTitles() !=null && !jdyRole.getTitles().isEmpty() ){
            queryWrapper.like("titles",jdyRole.getTitles());
        }
        if( jdyRole.getParentId() !=null &&  !jdyRole.getParentId().isEmpty()){
            queryWrapper.eq("parent_id",jdyRole.getParentId());
        }
        if( jdyRole.getIcon() !=null && !jdyRole.getIcon().isEmpty() ){
            queryWrapper.like("icon",jdyRole.getIcon());
        }
        if( jdyRole.getUrls() !=null && !jdyRole.getUrls().isEmpty() ){
            queryWrapper.like("urls",jdyRole.getUrls());
        }
        if( jdyRole.getStatus() !=null && !jdyRole.getStatus().isEmpty() ){
            queryWrapper.like("status",jdyRole.getStatus());
        }
        return (Page<AssayIngredientsTypes>) assayIngredientsTypesDao.selectPage(iPage,queryWrapper);
    }


    @Override
    public boolean updateDetailsById(AssayIngredientsTypes userPermission) {
        return assayIngredientsTypesDao.updateById(userPermission) > 0;
    }

    @Override
    public boolean saveInsert(AssayIngredientsTypes userPermission) {
        return assayIngredientsTypesDao.insert(userPermission) > 0;
    }

    @Override
    public boolean removeDetailsById(String id) {
        return assayIngredientsTypesDao.deleteById(id) > 0;
    }


}