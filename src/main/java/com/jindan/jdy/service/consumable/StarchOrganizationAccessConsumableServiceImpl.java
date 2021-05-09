package com.jindan.jdy.service.consumable;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchOrganizationAccessConsumableDto;
import com.jindan.jdy.common.mapper.StarchOrganizationPutConsumableChuruDao;
import com.jindan.jdy.common.mapper.StarchOrganizationPutConsumableMapper;
import com.jindan.jdy.common.mapper.StarchOrganizationPutMapper;
import com.jindan.jdy.common.pojo.StarchOrganizationAccessConsumable;
import com.jindan.jdy.common.mapper.StarchOrganizationAccessConsumableMapper;
import com.jindan.jdy.common.pojo.StarchOrganizationPut;
import com.jindan.jdy.common.pojo.StarchOrganizationPutConsumable;
import com.jindan.jdy.common.pojo.StarchOrganizationPutConsumableChuru;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**   
 * @Description:TODO(耗材管理服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class StarchOrganizationAccessConsumableServiceImpl  extends ServiceImpl<StarchOrganizationAccessConsumableMapper,StarchOrganizationAccessConsumable> implements StarchOrganizationAccessConsumableService  {

    @Autowired
    StarchOrganizationAccessConsumableMapper starchOrganizationAccessConsumableDao;

    @Autowired
    StarchOrganizationPutConsumableMapper starchOrganizationPutConsumableDao;

    @Autowired
    StarchOrganizationPutMapper starchOrganizationPutMapper;

    @Autowired
    StarchOrganizationPutConsumableChuruDao starchOrganizationPutConsumableChuruDao;

    @Override
    public PageInfo<StarchOrganizationAccessConsumableDto> queryRelevanceCatList(StarchOrganizationAccessConsumableDto jdyFlatalog) {
        PageHelper.startPage(jdyFlatalog.getCurrentPage(), jdyFlatalog.getPageSize());
        List<StarchOrganizationAccessConsumableDto> iPage =  starchOrganizationAccessConsumableDao.queryRelevanceCatList(jdyFlatalog);

        PageInfo<StarchOrganizationAccessConsumableDto> pageInfo = new PageInfo<StarchOrganizationAccessConsumableDto>(iPage);
        return  pageInfo;
    }

    @Override
    public boolean removeDetailsById(String id) {

        int i = starchOrganizationAccessConsumableDao.deleteById(id);
        if(i > 0){
            Map<String,Object> map  =new HashMap<>();
            map.put("parent_id",id);
            starchOrganizationPutConsumableChuruDao.deleteByMap(map);
        }
        return true;
    }

    @Override
    public boolean updateStarchMaintainRegister(StarchOrganizationAccessConsumableDto warehouseDepository) {
        StarchOrganizationAccessConsumable starchOrganizationAccessConsumable =new StarchOrganizationAccessConsumable();
        BeanUtils.copyProperties(warehouseDepository,starchOrganizationAccessConsumable);
        int i = starchOrganizationAccessConsumableDao.updateById(starchOrganizationAccessConsumable);
        if(i > 0 ){
            Map<String,Object> map  =new HashMap<>();
            map.put("parent_id",warehouseDepository.getLid());
            starchOrganizationPutConsumableChuruDao.deleteByMap(map);
            for (int j = 0; j < warehouseDepository.getLists().size(); j++) {
                warehouseDepository.getLists().get(j).setParentId(warehouseDepository.getLid());
                starchOrganizationPutConsumableChuruDao.insert(warehouseDepository.getLists().get(j));
            }
        }
        return true;
    }


    @Override
    public boolean addJdyFlowCatalog(StarchOrganizationAccessConsumableDto departmggestDto) {
        StarchOrganizationAccessConsumable starchOrganizationAccessConsumable =new StarchOrganizationAccessConsumable();
        BeanUtils.copyProperties(departmggestDto,starchOrganizationAccessConsumable);
        starchOrganizationAccessConsumable.setPtype("未确认");
        int i = starchOrganizationAccessConsumableDao.insert(starchOrganizationAccessConsumable);
        if(i > 0){
            for(int j = 0; j < departmggestDto.getLists().size(); j++){
                departmggestDto.getLists().get(j).setParentIds(starchOrganizationAccessConsumable.getLid());
                departmggestDto.getLists().get(j).setPtype("未确认");
                starchOrganizationPutConsumableChuruDao.insert(departmggestDto.getLists().get(j));
            }
        }
        return true;
    }


    @Override
    public boolean addJdyQuerenFlowCatalog(StarchOrganizationAccessConsumableDto departmggestDto) {
        StarchOrganizationAccessConsumable starchOrganizationAccessConsumable = new StarchOrganizationAccessConsumable();
        starchOrganizationAccessConsumable.setPtype("已确认");
        starchOrganizationAccessConsumable.setLid(departmggestDto.getLid());
        int i = starchOrganizationAccessConsumableDao.updateById(starchOrganizationAccessConsumable);
        if(i > 0){
            for (int j = 0; j < departmggestDto.getLists().size(); j++) {
                StarchOrganizationPutConsumable starchOrganizationPut =new StarchOrganizationPutConsumable();
                starchOrganizationPut.setCid(departmggestDto.getLists().get(j).getChuru());
                starchOrganizationPut.setNum(String.valueOf(departmggestDto.getLists().get(j).getNum()));
                if(departmggestDto.getPtypes().equals("2")){
                    boolean index = starchOrganizationPutMapper.updateAddDetailsPut(starchOrganizationPut);
                }else{
                    boolean index = starchOrganizationPutMapper.updateDeleteDetailsPut(starchOrganizationPut);
                }
                StarchOrganizationPutConsumableChuru starchOrganizationPutConsumableChuru = new StarchOrganizationPutConsumableChuru();
                starchOrganizationPutConsumableChuru.setChuru(departmggestDto.getLists().get(j).getChuru());
                starchOrganizationPutConsumableChuru.setPtype("已确认");
                starchOrganizationPutConsumableChuru.setCids(departmggestDto.getLists().get(j).getCids());
                starchOrganizationPutConsumableChuruDao.updateById(starchOrganizationPutConsumableChuru);
            }
        }
        return true;
    }

}