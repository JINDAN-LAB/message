package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchClassifyPutConsumableDto;
import com.jindan.jdy.common.dto.StarchOrganizationPutDto;
import com.jindan.jdy.mapper.StarchAlterationPutDao;
import com.jindan.jdy.mapper.StarchMaintenanceAlterationMapper;
import com.jindan.jdy.mapper.StarchOrganizationAlterationMapper;
import com.jindan.jdy.mapper.StarchOrganizationPutMapper;
import com.jindan.jdy.common.pojo.StarchAlterationPut;
import com.jindan.jdy.common.pojo.StarchOrganizationPut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**   
 * @Description:TODO(资产入库服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Slf4j
@Component
public class StarchOrganizationPutServiceImpl  extends ServiceImpl<StarchOrganizationPutMapper,StarchOrganizationPut> implements StarchOrganizationPutService  {

    @Autowired
    StarchOrganizationPutMapper starchManageCheckDao;

    @Autowired
    StarchMaintenanceAlterationMapper starchMaintenanceAlterationDao;

    @Autowired
    StarchOrganizationAlterationMapper   starchOrganizationAlterationMapper;

    @Autowired
    StarchAlterationPutDao  starchAlterationPutDao;

    @Override
    public PageInfo<StarchOrganizationPutDto> queryCatList(StarchOrganizationPutDto jdyRole) {
        PageHelper.startPage(jdyRole.getCurrentPage(), jdyRole.getPageSize());
        List<StarchOrganizationPutDto> iPage =  starchManageCheckDao.queryRelevanceCatList(jdyRole);

        log.info("iPage的值为："+iPage);
        PageInfo<StarchOrganizationPutDto> pageInfo = new PageInfo<StarchOrganizationPutDto>(iPage);
        return pageInfo;
    }

    @Override
    public boolean updateStarchMaintainRegister(StarchOrganizationPutDto warehouseDepository) {
        StarchOrganizationPut starchOrganizationPut =new StarchOrganizationPut();
        BeanUtils.copyProperties(warehouseDepository,starchOrganizationPut);
        starchManageCheckDao.updateById(starchOrganizationPut);
         if(warehouseDepository.getStarchOrganizationAlteration().getWid() != null && warehouseDepository.getStarchOrganizationAlteration().getWid() != " "){
            return starchMaintenanceAlterationDao.updateById(warehouseDepository.getStarchOrganizationAlteration())>0;
         }else{
             return starchMaintenanceAlterationDao.insert(warehouseDepository.getStarchOrganizationAlteration()) > 0;
         }
    }

    @Override
    public StarchOrganizationPut  addJdyFlowCatalog(StarchOrganizationPutDto departmentSuggestDto) {
        StarchOrganizationPut starchOrganizationPut =new StarchOrganizationPut();
        BeanUtils.copyProperties(departmentSuggestDto,starchOrganizationPut);
        if(starchManageCheckDao.insert(starchOrganizationPut)> 0){
            if(departmentSuggestDto.getStarchOrganizationAlteration() != null){
              if(starchMaintenanceAlterationDao.insert(departmentSuggestDto.getStarchOrganizationAlteration())>0){
                  StarchAlterationPut  starchAlterationPut = new StarchAlterationPut();
                  starchAlterationPut.setAlterationId(departmentSuggestDto.getStarchOrganizationAlteration().getWid());
                  starchAlterationPut.setPutId(starchOrganizationPut.getOid());
                  int insert = starchAlterationPutDao.insert(starchAlterationPut);
                  return  starchOrganizationPut;
              }
            }
          return starchOrganizationPut;
        }
       return null;
    }

    @Override
    public List<StarchOrganizationPutDto> queryDetailsWeibaoCatList(StarchOrganizationPut jdyFlowCatalog) {
        return starchManageCheckDao.queryDetailsWeibaoCatList(jdyFlowCatalog);
    }

    @Override
    public List<StarchClassifyPutConsumableDto> queryDetailsHuizongCatList(StarchOrganizationPutDto jdyFlowCatalog) {
        return starchManageCheckDao.queryDetailsHuizongCatList(jdyFlowCatalog);
    }

    @Override
    public  Map<String,List<StarchClassifyPutConsumableDto>> querShiyongFenlei() {
        List<StarchClassifyPutConsumableDto> list = starchManageCheckDao.querShiyongFenlei();
        Map<String,List<StarchClassifyPutConsumableDto>> map =new HashMap<>();
        for (int i = 0; i < list.size(); i++){
            if(list.get(i) != null && null != list.get(i).getNames() ){
             if(null != list.get(i).getNames() || !list.get(i).getNames().equals(" ") || !list.get(i).getNames().equals("")){
                if(map.containsKey(list.get(i).getNames())){
                    map.get(list.get(i).getNames()).add(list.get(i));
                }else{
                    List<StarchClassifyPutConsumableDto> list1 =new ArrayList<>();
                    list1.add(list.get(i));
                    map.put(list.get(i).getNames(),list1);
                }
             }
           }
         }
        return map;
    }

    @Override
    public List<StarchOrganizationPutDto> queryZichandaoqi(StarchOrganizationPutDto jdyFlowCatalog) {
        return starchManageCheckDao.queryZichandaoqi(jdyFlowCatalog);
    }

    @Override
    public List<StarchOrganizationPutDto> queryWeibaodaoqi(StarchOrganizationPutDto jdyFlowCatalog) {
        return starchManageCheckDao.queryWeibaodaoqi(jdyFlowCatalog);
    }


    @Override
    public List<StarchOrganizationPutDto> querZIchanShoiuye() {
        return starchManageCheckDao.querZIchanShoiuye();
    }

    @Override
    public PageInfo<StarchOrganizationPutDto> queryYiquerenCatList(StarchOrganizationPutDto jdyFlowCatalog) {
        PageHelper.startPage(jdyFlowCatalog.getCurrentPage(), jdyFlowCatalog.getPageSize());
        List<StarchOrganizationPutDto> iPage =  starchManageCheckDao.queryYiquerenCatList(jdyFlowCatalog);

        PageInfo<StarchOrganizationPutDto> pageInfo = new PageInfo<StarchOrganizationPutDto>(iPage);
        return pageInfo;
    }

    @Override
    public Map<String, List<StarchClassifyPutConsumableDto>> seletFenleiBumenShiyong() {
        List<StarchClassifyPutConsumableDto> list = starchManageCheckDao.seletFenleiBumenShiyong();
        Map<String,List<StarchClassifyPutConsumableDto>> map =new HashMap<>();
        for (int i = 0; i < list.size(); i++){
            if(list.get(i) != null && null != list.get(i).getNames() ){
                if(null != list.get(i).getNames() || !list.get(i).getNames().equals(" ") || !list.get(i).getNames().equals("")){
                    if(map.containsKey(list.get(i).getNames())){
                        map.get(list.get(i).getNames()).add(list.get(i));
                    }else{
                        List<StarchClassifyPutConsumableDto> list1 =new ArrayList<>();
                        list1.add(list.get(i));
                        map.put(list.get(i).getNames(),list1);
                    }
                }
            }
        }
        return map;
    }

    @Override
    public Map<String, List<StarchClassifyPutConsumableDto>> seletFenleiGeBumenShiyong() {

        List<StarchClassifyPutConsumableDto> list = starchManageCheckDao.seletFenleiGeBumenShiyong();
        Map<String,List<StarchClassifyPutConsumableDto>> map =new HashMap<>();
        for (int i = 0; i < list.size(); i++){
            if(list.get(i) != null && null != list.get(i).getNames() ){
                if(null != list.get(i).getNames() || !list.get(i).getNames().equals(" ") || !list.get(i).getNames().equals("")){
                    if(map.containsKey(list.get(i).getNames())){
                        map.get(list.get(i).getNames()).add(list.get(i));
                    }else{
                        List<StarchClassifyPutConsumableDto> list1 =new ArrayList<>();
                        list1.add(list.get(i));
                        map.put(list.get(i).getNames(),list1);
                    }
                }
            }
        }
        return map;

    }


}