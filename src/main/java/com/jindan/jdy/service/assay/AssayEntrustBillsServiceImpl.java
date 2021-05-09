package com.jindan.jdy.service.assay;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.AssayEntrustBillsDto;
import com.jindan.jdy.common.mapper.AssayEntrustBillsDao;
import com.jindan.jdy.common.pojo.AssayEntrustBills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**   
 * @Description:TODO(检测委托账单服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class AssayEntrustBillsServiceImpl  extends ServiceImpl<AssayEntrustBillsDao,AssayEntrustBills> implements AssayEntrustBillsService  {

    @Autowired
    AssayEntrustBillsDao assayEntrustBillsDao;


    @Override
    public Page<AssayEntrustBills> seleteDepartmentSubfacility(AssayEntrustBillsDto departmentSuggestDto) {
        if(departmentSuggestDto.getCurrentPage() <= 0  ){
            departmentSuggestDto.setCurrentPage(1);
        }
        Page<AssayEntrustBills> page =new Page<>(departmentSuggestDto.getCurrentPage(),departmentSuggestDto.getPageSize());

        QueryWrapper<AssayEntrustBills> queryWrapper =new QueryWrapper<>();
        if( departmentSuggestDto.getEid() !=null &&  !departmentSuggestDto.getEid().isEmpty()  ){
            queryWrapper.eq("eid",departmentSuggestDto.getEid());
        }
        if( departmentSuggestDto.getEname() !=null && !departmentSuggestDto.getEname().isEmpty() ){
            queryWrapper.like("ename",departmentSuggestDto.getEname());
        }
        if( departmentSuggestDto.getEpihao() !=null && !departmentSuggestDto.getEpihao().isEmpty() ){
            queryWrapper.like("epihao",departmentSuggestDto.getEpihao());
        }
        if( departmentSuggestDto.getEguige() !=null && !departmentSuggestDto.getEguige().isEmpty() ){
            queryWrapper.like("eguige",departmentSuggestDto.getEguige());
        }
        if( departmentSuggestDto.getEnums() !=null &&  !departmentSuggestDto.getEnums().isEmpty() ){
            queryWrapper.like("enums",departmentSuggestDto.getEnums());
        }
        if( departmentSuggestDto.getEunit() !=null &&  !departmentSuggestDto.getEunit().isEmpty() ){
            queryWrapper.like("eunit",departmentSuggestDto.getEunit());
        }
        if( departmentSuggestDto.getEperson() !=null &&  !departmentSuggestDto.getEperson().isEmpty() ){
            queryWrapper.like("eperson",departmentSuggestDto.getEperson());
        }

        if( departmentSuggestDto.getDateTime() !=null ){
            queryWrapper.like("date_time",departmentSuggestDto.getDateTime());
        }
        if( departmentSuggestDto.getRemarks() !=null && !departmentSuggestDto.getRemarks().isEmpty() ){
            queryWrapper.like("remarks",departmentSuggestDto.getRemarks());
        }
        return (Page<AssayEntrustBills>) assayEntrustBillsDao.selectPage(page,queryWrapper);
    }
}