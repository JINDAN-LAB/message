package com.jindan.jdy.service.activiti;

import com.jindan.jdy.common.pojo.FlowBillDefinition;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(流程定义表服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface FlowBillDefinitionService extends IService<FlowBillDefinition> {

    void updateIndexBatchById(List<FlowBillDefinition> lis1t);

 }