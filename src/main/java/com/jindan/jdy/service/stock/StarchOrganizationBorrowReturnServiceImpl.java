package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchOrganizationBorrowReturnDto;
import com.jindan.jdy.mapper.StarchBorrowPutDao;
import com.jindan.jdy.mapper.StarchOrganizationBorrowReturnMapper;
import com.jindan.jdy.mapper.StarchOrganizationPutMapper;
import com.jindan.jdy.common.pojo.StarchBorrowPut;
import com.jindan.jdy.common.pojo.StarchOrganizationBorrowReturn;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**   
 * @Description:TODO(资产借用与归还服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class StarchOrganizationBorrowReturnServiceImpl  extends ServiceImpl<StarchOrganizationBorrowReturnMapper,StarchOrganizationBorrowReturn> implements StarchOrganizationBorrowReturnService  {

    @Autowired
    StarchOrganizationBorrowReturnMapper starchManageCheckDao;

    @Autowired
    StarchOrganizationPutMapper starchOrganizationPutMapper;

    @Autowired
    StarchBorrowPutDao  starchBorrowPutDao;

    @Override
    public Page<StarchOrganizationBorrowReturn> queryCatList(StarchOrganizationBorrowReturnDto jdyRole) {

        Page<StarchOrganizationBorrowReturn> iPage =new Page<>(jdyRole.getCurrentPage(),jdyRole.getPageSize());
        QueryWrapper<StarchOrganizationBorrowReturn> queryWrapper = new QueryWrapper<>();
        if( jdyRole.getBrid() !=null &&  !jdyRole.getBrid().isEmpty()){
            queryWrapper.eq("brid",jdyRole.getBrid());
        }
        if( jdyRole.getStatus() !=null && !jdyRole.getStatus().isEmpty() ){
            queryWrapper.like("status",jdyRole.getStatus());
        }
        if( jdyRole.getBorrowNumber() !=null && !jdyRole.getBorrowNumber().isEmpty() ){
            queryWrapper.like("borrow_number",jdyRole.getBorrowNumber());
        }
        if( jdyRole.getBorrowTime() !=null && !jdyRole.getBorrowTime().isEmpty() ){
            queryWrapper.like("borrow_time",jdyRole.getBorrowTime());
        }
        if( jdyRole.getBorrowPerson() !=null && !jdyRole.getBorrowPerson().isEmpty() ){
            queryWrapper.like("borrow_person",jdyRole.getBorrowPerson());
        }
        if( jdyRole.getPredictReturnTime() !=null ){
            queryWrapper.like("predict_return_time",jdyRole.getPredictReturnTime());
        }
        if( jdyRole.getReturnTime() !=null && !jdyRole.getReturnTime().isEmpty() ){
            queryWrapper.like("return_time",jdyRole.getReturnTime());
        }
        if( jdyRole.getPropertyParentId() !=null && !jdyRole.getPropertyParentId().isEmpty() ){
            queryWrapper.like("property_parent_id",jdyRole.getPropertyParentId());
        }
        if( jdyRole.getParentId() !=null && !jdyRole.getParentId().isEmpty() ){
            queryWrapper.like("parent_id",jdyRole.getParentId());
        }
        return (Page<StarchOrganizationBorrowReturn>) starchManageCheckDao.selectPage(iPage,queryWrapper);
    }

    @Override
    public boolean updateStarchMaintainRegister(StarchOrganizationBorrowReturnDto departmentSuggestDto) {

        StarchOrganizationBorrowReturn starchOrganizationAccess =new StarchOrganizationBorrowReturn();
        try{
            BeanUtils.copyProperties(departmentSuggestDto,starchOrganizationAccess);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(starchManageCheckDao.updateById(starchOrganizationAccess)> 0){
            for (int i = 0; i < departmentSuggestDto.getPutList().size(); i++) {
                Map<String,Object> map =new HashMap<>();
                map.put("put_id",departmentSuggestDto.getBrid());
                starchBorrowPutDao.deleteByMap(map);
                StarchBorrowPut starchAccessPut =new StarchBorrowPut();
                starchAccessPut.setBorrowId(starchOrganizationAccess.getBrid());
                starchAccessPut.setPutId(departmentSuggestDto.getPutList().get(i).getOid());
                starchBorrowPutDao.insert(starchAccessPut);
            }
            return true;
        }
        return false;

    }


    @Transactional
    @Override
    public boolean addJdyFlowCatalog(StarchOrganizationBorrowReturnDto departmentSuggestDto) {

        StarchOrganizationBorrowReturn starchOrganizationAccess =new StarchOrganizationBorrowReturn();
        try{
            BeanUtils.copyProperties(departmentSuggestDto,starchOrganizationAccess);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(starchManageCheckDao.insert(starchOrganizationAccess)> 0){
            for (int i = 0; i < departmentSuggestDto.getPutList().size(); i++) {
                StarchBorrowPut starchAccessPut =new StarchBorrowPut();
                starchAccessPut.setBorrowId(starchOrganizationAccess.getBrid());
                starchAccessPut.setPutId(departmentSuggestDto.getPutList().get(i).getOid());
                starchBorrowPutDao.insert(starchAccessPut);
                starchOrganizationPutMapper.updateById(departmentSuggestDto.getPutList().get(i));
            }
            return true;
        }
        return false;
    }

    @Override
    public PageInfo<StarchOrganizationBorrowReturnDto> queryRelevanceCatList(StarchOrganizationBorrowReturnDto jdyFlowCatalog) {
        PageHelper.startPage(jdyFlowCatalog.getCurrentPage(), jdyFlowCatalog.getPageSize());
        List<StarchOrganizationBorrowReturnDto> iPage =  starchManageCheckDao.queryRelevanceCatList(jdyFlowCatalog);
        PageInfo<StarchOrganizationBorrowReturnDto> pageInfo = new PageInfo<StarchOrganizationBorrowReturnDto>(iPage);
        return pageInfo;
    }

    @Override
    public boolean removePointPracticableById(String id) {
        starchManageCheckDao.deleteById(id);
        Map hap =new HashMap();
        hap.put("borrow_id",id);
        starchBorrowPutDao.deleteByMap(hap);
        return true;
    }

    @Override
    public boolean updateStarchGuihuanMaintainRegister(StarchOrganizationBorrowReturnDto warehouseDepository) {
        StarchOrganizationBorrowReturn starchOrganizationAccess =new StarchOrganizationBorrowReturn();
        try{
            BeanUtils.copyProperties(warehouseDepository,starchOrganizationAccess);
        }catch(Exception e){
            e.printStackTrace();
        }
        if( starchManageCheckDao.updateById(starchOrganizationAccess) > 0){
            for (int i = 0; i <warehouseDepository.getPutList().size() ; i++) {
                starchOrganizationPutMapper.updateById(warehouseDepository.getPutList().get(i)) ;
            }
            return true;
        }
        return false ;
    }

}