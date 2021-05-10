package com.jindan.jdy.service.activiti;

import com.jindan.jdy.common.pojo.FlowBillDefinition;
import com.jindan.jdy.mapper.FlowBillDefinitionDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(流程定义表服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class FlowBillDefinitionServiceImpl  extends ServiceImpl<FlowBillDefinitionDao,FlowBillDefinition> implements FlowBillDefinitionService  {

    @Autowired
    FlowBillDefinitionDao flowBillDefinitionDao;


    @Override
    public void updateIndexBatchById(List<FlowBillDefinition> lis1t) {
        for (int i = 0; i < lis1t.size(); i++) {
            flowBillDefinitionDao.updateById(lis1t.get(i));
        }
    }





}