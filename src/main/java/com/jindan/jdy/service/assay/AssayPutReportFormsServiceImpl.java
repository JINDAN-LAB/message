package com.jindan.jdy.service.assay;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jindan.jdy.common.pojo.AssayPutReportForms;
import com.jindan.jdy.common.mapper.AssayPutReportFormsDao;
import com.jindan.jdy.common.pojo.AssaySampleInform;
import com.jindan.jdy.service.assay.AssayPutReportFormsService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(产品发货信息服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class AssayPutReportFormsServiceImpl  extends ServiceImpl<AssayPutReportFormsDao,AssayPutReportForms> implements AssayPutReportFormsService  {

    @Autowired
    AssayPutReportFormsDao assayPutReportFormsDao;

    @Override
    public List<AssayPutReportForms> seleteDepartmentSubfacility(AssayPutReportForms departmentSuggestDto) {
        QueryWrapper<AssayPutReportForms> queryWrapper =new QueryWrapper<>();
        if( departmentSuggestDto.getFhid() !=null &&  !departmentSuggestDto.getFhid().isEmpty()  ){
            queryWrapper.eq("fhid",departmentSuggestDto.getFhid());
        }
        if( departmentSuggestDto.getTitle1() !=null && !departmentSuggestDto.getTitle1().isEmpty() ){
            queryWrapper.like("title1",departmentSuggestDto.getTitle1());
        }
        if( departmentSuggestDto.getTitle2() !=null && !departmentSuggestDto.getTitle2().isEmpty() ){
            queryWrapper.like("title2",departmentSuggestDto.getTitle2());
        }
        if( departmentSuggestDto.getTitle3() !=null && !departmentSuggestDto.getTitle3().isEmpty() ){
            queryWrapper.like("title3",departmentSuggestDto.getTitle3());
        }
        if( departmentSuggestDto.getTitle4() !=null &&  !departmentSuggestDto.getTitle4().isEmpty() ){
            queryWrapper.like("title4",departmentSuggestDto.getTitle4());
        }
        if( departmentSuggestDto.getNames() !=null &&  !departmentSuggestDto.getNames().isEmpty() ){
            queryWrapper.like("names",departmentSuggestDto.getNames());
        }
        if( departmentSuggestDto.getStatus() !=null &&  !departmentSuggestDto.getStatus().isEmpty() ){
            queryWrapper.like("status",departmentSuggestDto.getStatus());
        }
        return assayPutReportFormsDao.selectList(queryWrapper);
    }
}