package com.jindan.jdy.service.keypoint;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.mapper.KeyPointEvaluateDao;
import com.jindan.jdy.common.pojo.KeyPointEvaluate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(重点工作评价服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class KeyPointEvaluateServiceImpl  extends ServiceImpl<KeyPointEvaluateDao,KeyPointEvaluate> implements KeyPointEvaluateService  {

    @Autowired
    KeyPointEvaluateDao keyPointEvaluateDao;


    @Override
    public List<KeyPointEvaluate> seleteDepartmentFacility(KeyPointEvaluate departmentSuggestDto) {

        QueryWrapper<KeyPointEvaluate> queryWrapper =new QueryWrapper<>();
        if( departmentSuggestDto.getEvid() !=null &&  departmentSuggestDto.getEvid()!= ""  ){
            queryWrapper.eq("evid",departmentSuggestDto.getEvid());
        }
        if( departmentSuggestDto.getProintId() !=null && !departmentSuggestDto.getProintId().isEmpty() ){
            queryWrapper.like("proint_id",departmentSuggestDto.getProintId());
        }
        if( departmentSuggestDto.getContent() !=null &&  departmentSuggestDto.getContent()!= ""  ){
            queryWrapper.like("content",departmentSuggestDto.getContent());
        }
        if( departmentSuggestDto.getResult() !=null && !departmentSuggestDto.getResult().isEmpty() ){
            queryWrapper.eq("result",departmentSuggestDto.getResult());
        }
        if( departmentSuggestDto.getEvalresult() !=null &&  departmentSuggestDto.getEvalresult()!= ""  ){
            queryWrapper.like("evalresult",departmentSuggestDto.getEvalresult());
        }
        if( departmentSuggestDto.getInsertTime() !=null ){
            queryWrapper.like("insert_time",departmentSuggestDto.getInsertTime());
        }
        return keyPointEvaluateDao.selectList(queryWrapper);
    }
}