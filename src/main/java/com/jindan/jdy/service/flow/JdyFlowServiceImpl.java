package com.jindan.jdy.service.flow;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.JdyFlowDto;
import com.jindan.jdy.common.mapper.JdyFlowMapper;
import com.jindan.jdy.common.pojo.JdyFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(规则服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class JdyFlowServiceImpl  extends ServiceImpl<JdyFlowMapper,JdyFlow> implements JdyFlowService  {

    @Autowired
    JdyFlowMapper jdyFlowDao;
    @Override
    public List<JdyFlow> seleteJdyFlowDto(JdyFlowDto departmentSuggestDto) {

        if(departmentSuggestDto.getCurrentPage() <= 0   || departmentSuggestDto.getPageSize()  <= 0){
            departmentSuggestDto.setCurrentPage(1);
        }
        QueryWrapper<JdyFlow> queryWrapper =new QueryWrapper<>();
        if(departmentSuggestDto.getFlowId() !=null &&  !departmentSuggestDto.getFlowId().isEmpty()  ){
            queryWrapper.eq("flow_id",departmentSuggestDto.getFlowId());
        }
        if(departmentSuggestDto.getFlowPersom() !=null && !departmentSuggestDto.getFlowPersom().isEmpty() ){
            queryWrapper.like("flow_persom",departmentSuggestDto.getFlowPersom());
        }
        if(departmentSuggestDto.getFlowRemarks() !=null && !departmentSuggestDto.getFlowRemarks().isEmpty() ){
            queryWrapper.like("flow_remarks",departmentSuggestDto.getFlowRemarks());
        }
        if(departmentSuggestDto.getFlowResult() !=null && !departmentSuggestDto.getFlowResult().isEmpty() ){
            queryWrapper.like("flow_result",departmentSuggestDto.getFlowResult());
        }
        if(departmentSuggestDto.getFlowTime() !=null && !departmentSuggestDto.getFlowTime().isEmpty() ){
            queryWrapper.like("flow_time",departmentSuggestDto.getFlowTime());
        }
        if(departmentSuggestDto.getParentId() !=null &&  !departmentSuggestDto.getParentId().isEmpty()  ){
            queryWrapper.eq("parent_id",departmentSuggestDto.getParentId());
        }
        return   jdyFlowDao.selectList(queryWrapper);
    }

    @Override
    public List<JdyFlowDto> seleteJdyFlowperson(JdyFlowDto departmentSuggestDto) {
        return jdyFlowDao.seleteJdyFlowperson(departmentSuggestDto);
    }

}