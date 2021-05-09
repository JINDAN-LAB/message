package com.jindan.jdy.service.assay;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.AssaySampleInformDto;
import com.jindan.jdy.common.mapper.AssayFacilityRecordDao;
import com.jindan.jdy.common.pojo.AssayFacilityRecord;
import com.jindan.jdy.common.pojo.AssaySampleInform;
import com.jindan.jdy.common.mapper.AssaySampleInformDao;
import com.jindan.jdy.service.assay.AssaySampleInformService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**   
 * @Description:TODO(检测抽样样品服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class AssaySampleInformServiceImpl  extends ServiceImpl<AssaySampleInformDao,AssaySampleInform> implements AssaySampleInformService  {

    @Autowired
    AssaySampleInformDao assayEntrustBillsDao;

    @Override
    public Page<AssaySampleInform> seleteDepartmentSubfacility(AssaySampleInformDto departmentSuggestDto) {
        if(departmentSuggestDto.getCurrentPage() <= 0  ){
            departmentSuggestDto.setCurrentPage(1);
        }
        Page<AssaySampleInform> page =new Page<>(departmentSuggestDto.getCurrentPage(),departmentSuggestDto.getPageSize());
        QueryWrapper<AssaySampleInform> queryWrapper =new QueryWrapper<>();
        if( departmentSuggestDto.getSampleId() !=null &&  !departmentSuggestDto.getSampleId().isEmpty()  ){
            queryWrapper.eq("sample_id",departmentSuggestDto.getSampleId());
        }
        if( departmentSuggestDto.getProductName() !=null && !departmentSuggestDto.getProductName().isEmpty() ){
            queryWrapper.like("product_name",departmentSuggestDto.getProductName());
        }
        if( departmentSuggestDto.getProductPihao() !=null && !departmentSuggestDto.getProductPihao().isEmpty() ){
            queryWrapper.like("product_pihao",departmentSuggestDto.getProductPihao());
        }
        if( departmentSuggestDto.getProductGuige() !=null && !departmentSuggestDto.getProductGuige().isEmpty() ){
            queryWrapper.like("product_guige",departmentSuggestDto.getProductGuige());
        }
        if( departmentSuggestDto.getProductNums() !=null &&  !departmentSuggestDto.getProductNums().isEmpty() ){
            queryWrapper.like("product_nums",departmentSuggestDto.getProductNums());
        }
        if( departmentSuggestDto.getProductUnit() !=null &&  !departmentSuggestDto.getProductUnit().isEmpty() ){
            queryWrapper.like("product_unit",departmentSuggestDto.getProductUnit());
        }
        if( departmentSuggestDto.getProductPerson() !=null &&  !departmentSuggestDto.getProductPerson().isEmpty() ){
            queryWrapper.like("product_person",departmentSuggestDto.getProductPerson());
        }
        if( departmentSuggestDto.getProductTime() !=null  &&  !departmentSuggestDto.getProductTime().isEmpty() ){
            queryWrapper.like("product_time",departmentSuggestDto.getProductTime());
        }
        return (Page<AssaySampleInform>) assayEntrustBillsDao.selectPage(page,queryWrapper);
    }
}