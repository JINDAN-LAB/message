package com.jindan.jdy.service.sys;

import com.jindan.jdy.common.dto.JdyFlowCatalogDto;
import com.jindan.jdy.common.pojo.JdyFlowCatalog;
import com.jindan.jdy.common.mapper.JdyFlowCatalogMapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**   
 * @Description:TODO(流程目录信息服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class JdyFlowCatalogServiceImpl  extends ServiceImpl<JdyFlowCatalogMapper,JdyFlowCatalog> implements JdyFlowCatalogService  {

    @Autowired
    JdyFlowCatalogMapper jdyFlowCatalogDao;


    @Override
    public List<JdyFlowCatalogDto> queryCatList(JdyFlowCatalog jdyFlowCatalog) {

   List<JdyFlowCatalogDto> data = jdyFlowCatalogDao.selectAll(jdyFlowCatalog);
   List<JdyFlowCatalogDto> categoriesList = new ArrayList<>();
        for(JdyFlowCatalogDto  category : data){
            if(category.getParentId() == 0){
                categoriesList.add(category);
            }
        }
        for(JdyFlowCatalogDto category : categoriesList){
            category.setLists(getChilde(category.getId(), data));
        }
        return categoriesList;
    }


    private List<JdyFlowCatalogDto> getChilde(int id, List<JdyFlowCatalogDto> rootList){
        List<JdyFlowCatalogDto> childList = new ArrayList<>();
        for(JdyFlowCatalogDto category : rootList){
            if(category.getParentId().equals(id)){
                childList.add(category);
            }
        }
        for(JdyFlowCatalogDto category : childList){
            category.setLists(getChilde(category.getId(), rootList));
        }
        if (childList.size() == 0){
            return null;
        }
        return childList;
    }

}