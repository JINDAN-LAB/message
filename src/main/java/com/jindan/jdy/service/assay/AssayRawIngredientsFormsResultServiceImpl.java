package com.jindan.jdy.service.assay;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.mapper.AssayRawIngredientsFormsResultDao;
import com.jindan.jdy.common.pojo.AssayRawIngredientsFormsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(报表结果发货报表的结果集服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class AssayRawIngredientsFormsResultServiceImpl  extends ServiceImpl<AssayRawIngredientsFormsResultDao,AssayRawIngredientsFormsResult> implements AssayRawIngredientsFormsResultService  {

    @Autowired
    AssayRawIngredientsFormsResultDao assayPutReportFormsDao;

    @Override
    public List<AssayRawIngredientsFormsResult> seleteDepartmentSubfacility(AssayRawIngredientsFormsResult departmentSuggestDto) {
        QueryWrapper<AssayRawIngredientsFormsResult> queryWrapper =new QueryWrapper<>();
        if( departmentSuggestDto.getYulb() !=null &&  !departmentSuggestDto.getYulb().isEmpty()  ){
            queryWrapper.eq("yulb",departmentSuggestDto.getYulb());
        }
        if( departmentSuggestDto.getDatev() !=null && !departmentSuggestDto.getDatev().isEmpty() ){
            queryWrapper.like("datev",departmentSuggestDto.getDatev());
        }
        if( departmentSuggestDto.getValues1() !=null && !departmentSuggestDto.getValues1().isEmpty() ){
            queryWrapper.like("values1",departmentSuggestDto.getValues1());
        }
        if( departmentSuggestDto.getParentId() !=null && !departmentSuggestDto.getParentId().isEmpty() ){
            queryWrapper.like("parent_id",departmentSuggestDto.getParentId());
        }
        return assayPutReportFormsDao.selectList(queryWrapper);
    }


}