package com.jindan.jdy.service.flow;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.JdyFlowDto;
import com.jindan.jdy.common.pojo.JdyFlow;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description:TODO(规则服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdyFlowService extends IService<JdyFlow> {

    List<JdyFlow> seleteJdyFlowDto(JdyFlowDto departmentSuggestDto);

    List<JdyFlowDto> seleteJdyFlowperson(JdyFlowDto departmentSuggestDto);
}