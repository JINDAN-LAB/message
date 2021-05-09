package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchOrganizationAccessDto;
import com.jindan.jdy.common.dto.StarchOrganizationPutConsumableDto;
import com.jindan.jdy.common.mapper.StarchAccessPutDao;
import com.jindan.jdy.common.mapper.StarchOrganizationPutConsumableChuruDao;
import com.jindan.jdy.common.mapper.StarchOrganizationPutMapper;
import com.jindan.jdy.common.pojo.StarchAccessPut;
import com.jindan.jdy.common.pojo.StarchOrganizationAccess;
import com.jindan.jdy.common.mapper.StarchOrganizationAccessMapper;
import com.jindan.jdy.common.pojo.StarchOrganizationPutConsumable;
import com.jindan.jdy.common.pojo.StarchOrganizationPutConsumableChuru;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**   
 * @Description:TODO(资产领用与退库服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class StarchOrganizationAccessServiceImpl  extends ServiceImpl<StarchOrganizationAccessMapper,StarchOrganizationAccess> implements StarchOrganizationAccessService  {

    @Autowired
    StarchOrganizationAccessMapper starchManageCheckDao;

    @Autowired
    StarchAccessPutDao starchAccessPutDao;

    @Autowired
    StarchOrganizationPutMapper starchOrganizationPutDao;

    @Autowired
    StarchOrganizationPutConsumableChuruDao starchOrganizationPutConsumableChuru;

    @Transactional
    @Override
    public boolean addJdyFlowCatalog(StarchOrganizationAccessDto departmentSuggestDto) {
        StarchOrganizationAccess  starchOrganizationAccess =new StarchOrganizationAccess();
        try{
            BeanUtils.copyProperties(departmentSuggestDto,starchOrganizationAccess);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(starchManageCheckDao.insert(starchOrganizationAccess) > 0){
            for (int i = 0; i < departmentSuggestDto.getPutList().size(); i++) {
                starchOrganizationPutDao.updateById(departmentSuggestDto.getPutList().get(i));
                StarchAccessPut  starchAccessPut =new StarchAccessPut();
                starchAccessPut.setAccessId(starchOrganizationAccess.getLid());
                starchAccessPut.setPutId(departmentSuggestDto.getPutList().get(i).getOid());
                starchAccessPutDao.insert(starchAccessPut);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean updateStarchMaintainRegister(StarchOrganizationAccessDto warehouseDepository) {
        StarchOrganizationAccess  starchOrganizationAccess =new StarchOrganizationAccess();
        try{
            BeanUtils.copyProperties(warehouseDepository,starchOrganizationAccess);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(starchManageCheckDao.updateById(starchOrganizationAccess) > 0){
            for (int i = 0; i < warehouseDepository.getPutList().size(); i++) {
                starchOrganizationPutDao.updateById(warehouseDepository.getPutList().get(i));
                Map<String,Object> map =new HashMap<>();
                map.put("put_id",warehouseDepository.getLid());
                starchAccessPutDao.deleteByMap(map);
                StarchAccessPut  starchAccessPut =new StarchAccessPut();
                starchAccessPut.setAccessId(starchOrganizationAccess.getLid());
                starchAccessPut.setPutId(warehouseDepository.getPutList().get(i).getOid());
                starchAccessPutDao.updateById(starchAccessPut);
            }
            return true;
        }
        return false;

    }

    @Override
    public Page<StarchOrganizationAccess> queryCatList(StarchOrganizationAccessDto jdyRole) {
        Page<StarchOrganizationAccess> iPage =new Page<>(jdyRole.getCurrentPage(),jdyRole.getPageSize());
        QueryWrapper<StarchOrganizationAccess> queryWrapper = new QueryWrapper<>();
        if( jdyRole.getLid() !=null &&  !jdyRole.getLid().isEmpty()){
            queryWrapper.eq("lid",jdyRole.getLid());
        }
        if( jdyRole.getStatus() !=null && !jdyRole.getStatus().isEmpty() ){
            queryWrapper.like("status",jdyRole.getStatus());
        }
        if( jdyRole.getRecipientsNumber() !=null && !jdyRole.getRecipientsNumber().isEmpty() ){
            queryWrapper.like("recipients_number",jdyRole.getRecipientsNumber());
        }
        if( jdyRole.getReceiveTime() !=null && !jdyRole.getReceiveTime().isEmpty() ){
            queryWrapper.like("receive_time",jdyRole.getReceiveTime());
        }
        if( jdyRole.getReceivePerson() !=null && !jdyRole.getReceivePerson().isEmpty() ){
            queryWrapper.like("receive_person",jdyRole.getReceivePerson());
        }
        if( jdyRole.getReceiveCompany() !=null ){
            queryWrapper.like("receive_company",jdyRole.getReceiveCompany());
        }
        if( jdyRole.getReceiveDepartment() !=null && !jdyRole.getReceiveDepartment().isEmpty() ){
            queryWrapper.like("receive_department",jdyRole.getReceiveDepartment());
        }
        if( jdyRole.getReceiveArea() !=null && !jdyRole.getReceiveArea().isEmpty() ){
            queryWrapper.like("receive_area",jdyRole.getReceiveArea());
        }
        if( jdyRole.getReceivePlace() !=null && !jdyRole.getReceivePlace().isEmpty() ){
            queryWrapper.like("receive_place",jdyRole.getReceivePlace());
        }
        if( jdyRole.getReceiveRemarks() !=null && !jdyRole.getReceiveRemarks().isEmpty() ){
            queryWrapper.like("receive_remarks",jdyRole.getReceiveRemarks());
        }
        if( jdyRole.getCancellingStocksTime() !=null && !jdyRole.getCancellingStocksTime().isEmpty() ){
            queryWrapper.like("cancelling_stocks_time",jdyRole.getCancellingStocksTime());
        }
        if( jdyRole.getManagePerson() !=null && !jdyRole.getManagePerson().isEmpty() ){
            queryWrapper.like("manage_person",jdyRole.getManagePerson());
        }
        if( jdyRole.getAssetNumber() !=null && !jdyRole.getAssetNumber().isEmpty() ){
            queryWrapper.like("asset_number",jdyRole.getAssetNumber());
        }

        if( jdyRole.getAssetName() !=null && !jdyRole.getAssetName().isEmpty() ){
            queryWrapper.like("asset_name",jdyRole.getAssetName());
        }
        if( jdyRole.getAssetModel() !=null && !jdyRole.getAssetModel().isEmpty() ){
            queryWrapper.like("asset_model",jdyRole.getAssetModel());
        }
        if( jdyRole.getAssetSn() !=null && !jdyRole.getAssetSn().isEmpty() ){
            queryWrapper.like("asset_sn",jdyRole.getAssetSn());
        }
        if( jdyRole.getModelJine() !=null && !jdyRole.getModelJine().isEmpty() ){
            queryWrapper.like("model_jine",jdyRole.getModelJine());
        }
        if( jdyRole.getUseCompany() !=null && !jdyRole.getUseCompany().isEmpty() ){
            queryWrapper.like("use_company",jdyRole.getUseCompany());
        }
        if( jdyRole.getUseDepartment() !=null && !jdyRole.getUseDepartment().isEmpty() ){
            queryWrapper.like("use_department",jdyRole.getUseDepartment());
        }
        if( jdyRole.getUsePerson() !=null && !jdyRole.getUsePerson().isEmpty() ){
            queryWrapper.like("use_person",jdyRole.getUsePerson());
        }
        if( jdyRole.getManagePerson() !=null && !jdyRole.getManagePerson().isEmpty() ){
            queryWrapper.like("manager_person",jdyRole.getManagePerson());
        }
        if( jdyRole.getBelongCompany() !=null && !jdyRole.getBelongCompany().isEmpty() ){
            queryWrapper.like("belong_company",jdyRole.getBelongCompany());
        }
        if( jdyRole.getBelongEreas() !=null && !jdyRole.getBelongEreas().isEmpty() ){
            queryWrapper.like("belong_ereas",jdyRole.getBelongEreas());
        }
        if( jdyRole.getBelongPlace() !=null && !jdyRole.getBelongPlace().isEmpty() ){
            queryWrapper.like("belong_place",jdyRole.getBelongPlace());
        }
        if( jdyRole.getParentId() !=null && !jdyRole.getParentId().isEmpty() ){
            queryWrapper.like("parent_id",jdyRole.getParentId());
        }
        return (Page<StarchOrganizationAccess>) starchManageCheckDao.selectPage(iPage,queryWrapper);
    }

    public PageInfo<StarchOrganizationAccessDto> queryRelevanceCatList(StarchOrganizationAccessDto jdyFlowCatalog) {
        PageHelper.startPage(jdyFlowCatalog.getCurrentPage(), jdyFlowCatalog.getPageSize());
        List<StarchOrganizationAccessDto> iPage =  starchManageCheckDao.queryRelevanceCatList(jdyFlowCatalog);
        PageInfo<StarchOrganizationAccessDto> pageInfo = new PageInfo<>(iPage);
        return pageInfo;
    }

    @Override
    public boolean removeDetailsById(String id) {
        starchManageCheckDao.deleteById(id);
        Map hap =new HashMap();
        hap.put("access_id",id);
        starchAccessPutDao.deleteByMap(hap);
        return true;
    }

    @Override
    public boolean updateZhuangtaiStarchMaintainRegister(StarchOrganizationAccessDto warehouseDepository) {
        StarchOrganizationAccess  starchOrganizationAccess =new StarchOrganizationAccess();
        try{
            BeanUtils.copyProperties(warehouseDepository,starchOrganizationAccess);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(starchManageCheckDao.updateById(starchOrganizationAccess) > 0){
            for (int i = 0; i < warehouseDepository.getPutList().size(); i++) {
                starchOrganizationPutDao.updateById(warehouseDepository.getPutList().get(i));
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean updateSinglesStarchManageCheck(StarchOrganizationPutConsumableChuru warehouseDepository) {
        return starchOrganizationPutConsumableChuru.updateById(warehouseDepository) > 0;
    }


}