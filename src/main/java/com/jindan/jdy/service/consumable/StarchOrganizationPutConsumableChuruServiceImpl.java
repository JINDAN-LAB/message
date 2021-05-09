package com.jindan.jdy.service.consumable;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchOrganizationPutConsumableChuruDto;
import com.jindan.jdy.common.mapper.StarchOrganizationPutConsumableChuruDao;
import com.jindan.jdy.common.pojo.StarchOrganizationPutConsumableChuru;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(耗材资产出入库内容服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class StarchOrganizationPutConsumableChuruServiceImpl  extends ServiceImpl<StarchOrganizationPutConsumableChuruDao,StarchOrganizationPutConsumableChuru> implements StarchOrganizationPutConsumableChuruService {


    @Autowired
    StarchOrganizationPutConsumableChuruDao starchOrganizationPutConsumableDao;

    @Override
    public PageInfo<StarchOrganizationPutConsumableChuru> queryRelevanceCatList(StarchOrganizationPutConsumableChuruDto jdyFlowCatalog) {

        QueryWrapper<StarchOrganizationPutConsumableChuru> queryWrapper = new QueryWrapper<>();
        if( jdyFlowCatalog.getPtype() !=null &&  !jdyFlowCatalog.getPtype().isEmpty()){
            queryWrapper.eq("ptype",jdyFlowCatalog.getPtype());
        }
        if( jdyFlowCatalog.getDepartments() !=null && !jdyFlowCatalog.getDepartments().isEmpty() ){
            queryWrapper.like("departments",jdyFlowCatalog.getDepartments());
        }
        if( jdyFlowCatalog.getPtypes() !=null && !jdyFlowCatalog.getPtypes().isEmpty() ){
            queryWrapper.like("ptypes",jdyFlowCatalog.getPtypes());
        }
        if( jdyFlowCatalog.getGuige() !=null && !jdyFlowCatalog.getGuige().isEmpty() ){
            queryWrapper.like("guige",jdyFlowCatalog.getGuige());
        }
        if( jdyFlowCatalog.getDanwei() !=null && !jdyFlowCatalog.getDanwei().isEmpty() ){
            queryWrapper.like("danwei",jdyFlowCatalog.getDanwei());
        }
        if( jdyFlowCatalog.getNum() !=null ){
            queryWrapper.like("num",jdyFlowCatalog.getNum());
        }
        if( jdyFlowCatalog.getBianma() !=null && !jdyFlowCatalog.getBianma().isEmpty() ){
            queryWrapper.like("bianma",jdyFlowCatalog.getBianma());
        }
        if( jdyFlowCatalog.getNames() !=null && !jdyFlowCatalog.getNames().isEmpty() ){
            queryWrapper.like("names",jdyFlowCatalog.getNames());
        }
        if( jdyFlowCatalog.getPici() !=null && !jdyFlowCatalog.getPici().isEmpty() ){
            queryWrapper.like("pici",jdyFlowCatalog.getPici());
        }
        if( jdyFlowCatalog.getPtype() !=null && !jdyFlowCatalog.getPtype().isEmpty() ){
            queryWrapper.like("ptype",jdyFlowCatalog.getPtype());
        }
        if( jdyFlowCatalog.getParentIds() !=null && !jdyFlowCatalog.getParentIds().isEmpty() ){
            queryWrapper.like("parent_ids",jdyFlowCatalog.getParentIds());
        }
        if( jdyFlowCatalog.getParentId() !=null && !jdyFlowCatalog.getParentId().isEmpty() ){
            queryWrapper.like("parent_id",jdyFlowCatalog.getParentId());
        }
        if( jdyFlowCatalog.getCids() !=null && !jdyFlowCatalog.getCids().isEmpty() ){
            queryWrapper.like("cids",jdyFlowCatalog.getCids());
        }
        PageHelper.startPage(jdyFlowCatalog.getCurrentPage(), jdyFlowCatalog.getPageSize());
        List<StarchOrganizationPutConsumableChuru> iPage = starchOrganizationPutConsumableDao.selectList(queryWrapper);
        PageInfo<StarchOrganizationPutConsumableChuru> pageInfo = new PageInfo<StarchOrganizationPutConsumableChuru>(iPage);
        return pageInfo;
    }

}
