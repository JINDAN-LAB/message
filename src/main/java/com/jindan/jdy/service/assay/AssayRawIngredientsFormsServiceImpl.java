package com.jindan.jdy.service.assay;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.mapper.AssayRawIngredientsFormsDao;
import com.jindan.jdy.common.pojo.AssayRawIngredientsForms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(报表发货报表的结果集服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class AssayRawIngredientsFormsServiceImpl  extends ServiceImpl<AssayRawIngredientsFormsDao,AssayRawIngredientsForms> implements AssayRawIngredientsFormsService  {

    @Autowired
    AssayRawIngredientsFormsDao assayEntrustBillsDao;


    @Override
    public List<AssayRawIngredientsForms> seleteDepartmentSubfacility(AssayRawIngredientsForms departmentSuggestDto) {

        QueryWrapper<AssayRawIngredientsForms> queryWrapper =new QueryWrapper<>();
        if( departmentSuggestDto.getYfid() !=null &&  !departmentSuggestDto.getYfid().isEmpty()  ){
            queryWrapper.eq("yfid",departmentSuggestDto.getYfid());
        }
        if( departmentSuggestDto.getName() !=null && !departmentSuggestDto.getName().isEmpty() ){
            queryWrapper.like("name",departmentSuggestDto.getName());
        }
        if( departmentSuggestDto.getTitl1() !=null && !departmentSuggestDto.getTitl1().isEmpty() ){
            queryWrapper.like("titl1",departmentSuggestDto.getTitl1());
        }
        if( departmentSuggestDto.getDates() !=null && !departmentSuggestDto.getDates().isEmpty() ){
            queryWrapper.like("dates",departmentSuggestDto.getDates());
        }
        if( departmentSuggestDto.getRemarks() !=null &&  !departmentSuggestDto.getDates().isEmpty() ){
            queryWrapper.like("remarks",departmentSuggestDto.getDates());
        }
        return assayEntrustBillsDao.selectList(queryWrapper);
    }


}