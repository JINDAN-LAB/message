package com.jindan.jdy.service.assay;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.mapper.AssayPutReportFormsResultDao;
import com.jindan.jdy.common.pojo.AssayPutReportFormsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(每天发货报表的结果集服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class AssayPutReportFormsResultServiceImpl  extends ServiceImpl<AssayPutReportFormsResultDao,AssayPutReportFormsResult> implements AssayPutReportFormsResultService  {

    @Autowired
    AssayPutReportFormsResultDao assayPutReportFormsDao;


    @Override
    public List<AssayPutReportFormsResult> seleteDepartmentSubfacility(AssayPutReportFormsResult departmentSuggestDto) {

        QueryWrapper<AssayPutReportFormsResult> queryWrapper =new QueryWrapper<>();
        if( departmentSuggestDto.getFhrid() !=null &&  !departmentSuggestDto.getFhrid().isEmpty()  ){
            queryWrapper.eq("fhrid",departmentSuggestDto.getFhrid());
        }
        if( departmentSuggestDto.getValue1() !=null && !departmentSuggestDto.getValue1().isEmpty() ){
            queryWrapper.like("value1",departmentSuggestDto.getValue1());
        }
        if( departmentSuggestDto.getParentId() !=null && !departmentSuggestDto.getParentId().isEmpty()  ){
            queryWrapper.like("parent_id",departmentSuggestDto.getParentId());
        }
        if( departmentSuggestDto.getRemarks() !=null && !departmentSuggestDto.getRemarks().isEmpty() ){
            queryWrapper.like("remarks",departmentSuggestDto.getRemarks());
        }
        return assayPutReportFormsDao.selectList(queryWrapper);
    }
}