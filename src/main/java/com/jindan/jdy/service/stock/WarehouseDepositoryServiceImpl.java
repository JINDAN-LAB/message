package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.WarehouseDepositoryDto;
import com.jindan.jdy.mapper.WarehouseDepositoryMapper;
import com.jindan.jdy.common.pojo.WarehouseDepository;
import com.jindan.jdy.common.pojo.WarehouseSpecs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**   
 * @Description:TODO(仓库管理服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Slf4j
@Component
public class WarehouseDepositoryServiceImpl  extends ServiceImpl<WarehouseDepositoryMapper,WarehouseDepository> implements WarehouseDepositoryService  {
    List<WarehouseDepositoryDto> categoriesList2= null;

    @Autowired
    WarehouseDepositoryMapper warehouseDepositoryDao;

    @Override
    public List<WarehouseDepository> seleteWarehouseDepository(WarehouseDepositoryDto departmentSuggestDto) {
        QueryWrapper<WarehouseDepository> queryWrapper = new QueryWrapper<>();
        return warehouseDepositoryDao.selectList(queryWrapper);
    }

    @Override
    public List<WarehouseDepositoryDto> seleteWarehouseDepositoryDto(WarehouseDepositoryDto departmentSuggestDto) {
        return warehouseDepositoryDao.selectListWarehouseDepositoryDao(departmentSuggestDto);
    }


    @Override
    public List<WarehouseSpecs> queryFenleiIDCatList(WarehouseDepository warehouseDepository) {
        log.info("======“WarehouseDepositoryServiceImpl.queryFenleiIDCatList方法”开始执行======");
        List<WarehouseDepositoryDto> warehouseDepositories = warehouseDepositoryDao.selectAllTiaojianList(warehouseDepository);
        List<WarehouseDepositoryDto> data = warehouseDepositoryDao.selectAllList();
        Map<String,String> map = new HashMap<>();
        List<WarehouseDepositoryDto> categoriesList = new ArrayList<>();
         categoriesList2 = new ArrayList<>();
        for(WarehouseDepositoryDto   category : warehouseDepositories){
          categoriesList.add(category);
        }
        for(WarehouseDepositoryDto category : categoriesList){
            log.info("循环一遍");
             categoriesList2  = getChildeindex(category.getId(), data, map );
        }
        return null;
    }

    @Override
    public List<WarehouseDepository> queryList() {
        return warehouseDepositoryDao.selectList(null);
    }

    private List<WarehouseDepositoryDto> getChildeindex(String id, List<WarehouseDepositoryDto> rootList,Map<String,String> map){
        List<WarehouseDepositoryDto> childList = new ArrayList<>();
        List<WarehouseDepositoryDto> categoriesList2 = new ArrayList<>();
        //子菜单
        for(WarehouseDepositoryDto category : rootList){
            if(category.getParentId().equals(id) && category.getParentId() != "0" && !map.containsKey(category.getId())){
                childList.add(category);
                categoriesList2.add(category);
                map.put(category.getId(),category.getId());
            }
        }
        // 把子菜单的子菜单再循环一遍
        for(WarehouseDepositoryDto category : childList){
            category.setDepositoryDtoList(getChildeindex(category.getId(), rootList, map ));
        }
        // 递归退出条件
        if (childList.size() == 0){
            return null;
        }
        return categoriesList2;
    }

    @Override
    public List<WarehouseDepositoryDto> queryFenleiCatList(){
        List<WarehouseDepositoryDto> data = warehouseDepositoryDao.selectAllList();
        List<WarehouseDepositoryDto> categoriesList = new ArrayList<>();
        for(WarehouseDepositoryDto  category : data){
            if(category.getParentId().equals("0")){
                categoriesList.add(category);
            }
        }
        for(WarehouseDepositoryDto category : categoriesList){
            category.setDepositoryDtoList(getChilde(category.getId(), data));
        }
        return categoriesList;
    }

    private List<WarehouseDepositoryDto> getChilde(String id, List<WarehouseDepositoryDto> rootList){
        List<WarehouseDepositoryDto> childList = new ArrayList<>();
        for(WarehouseDepositoryDto category : rootList){
            if(category.getParentId().equals(id)){
                childList.add(category);
            }
        }
        for(WarehouseDepositoryDto category : childList){
            category.setDepositoryDtoList(getChilde(category.getId(), rootList));
        }
        if (childList.size() == 0){
            return null;
        }
        return childList;
    }



}