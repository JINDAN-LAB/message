package com.jindan.jdy.service.consumable;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.StarchClassifyPutConsumableDto;
import com.jindan.jdy.mapper.StarchClassifyPutConsumableMapper;
import com.jindan.jdy.common.pojo.StarchClassifyPutConsumable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**   
 * @Description:TODO(耗材资产分类服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class StarchClassifyPutConsumableServiceImpl  extends ServiceImpl<StarchClassifyPutConsumableMapper,StarchClassifyPutConsumable> implements StarchClassifyPutConsumableService  {

    @Autowired
    StarchClassifyPutConsumableMapper starchClassifyPutConsumableDao;

    @Override
    public boolean updateStarchMaintainRegister(StarchClassifyPutConsumable warehouseDepository) {
        return starchClassifyPutConsumableDao.updateById(warehouseDepository) > 0;
    }

    @Override
    public boolean addJdyFlowCatalog(StarchClassifyPutConsumable departmggestDto) {
        return starchClassifyPutConsumableDao.insert(departmggestDto) >0 ;
    }

    @Override
    public boolean removeDetailsById(String id) {
        return starchClassifyPutConsumableDao.deleteById(id)>0;
    }

    @Override
    public List<StarchClassifyPutConsumableDto> queryRelevanceCatList(StarchClassifyPutConsumable departmentSuggestDto) {

        List<StarchClassifyPutConsumableDto> data = starchClassifyPutConsumableDao.selectAllList();

        List<StarchClassifyPutConsumableDto> categoriesList = new ArrayList<>();
        for(StarchClassifyPutConsumableDto  category : data){
            if(category.getParentId().equals("0")){
                categoriesList.add(category);
            }
        }
        for(StarchClassifyPutConsumableDto category : categoriesList){
            category.setList(getChilde(category.getIds(), data));
        }
        return categoriesList;
    }

    private List<StarchClassifyPutConsumableDto> getChilde(String id, List<StarchClassifyPutConsumableDto> rootList){
        //子菜单
        List<StarchClassifyPutConsumableDto> childList = new ArrayList<>();
        for(StarchClassifyPutConsumableDto category : rootList){
            if(category.getParentId().equals(id)){
                childList.add(category);
            }
        }
        for(StarchClassifyPutConsumableDto category : childList){
            category.setList(getChilde(category.getIds(), rootList));
        }
        if (childList.size() == 0){
            return null;
        }
        return childList;
    }


}