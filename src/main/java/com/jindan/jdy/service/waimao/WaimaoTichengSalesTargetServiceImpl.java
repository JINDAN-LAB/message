package com.jindan.jdy.service.waimao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jindan.jdy.common.pojo.WaimaoArea;
import com.jindan.jdy.common.pojo.WaimaoTichengSalesTarget;
import com.jindan.jdy.mapper.WaimaoTichengSalesTargetMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangfang
 * @since 2021-08-15
 */
@Service
public class WaimaoTichengSalesTargetServiceImpl extends ServiceImpl<WaimaoTichengSalesTargetMapper, WaimaoTichengSalesTarget> implements WaimaoTichengSalesTargetService {

    @Autowired
    WaimaoTichengSalesTargetMapper waimaoTichengSalesTargetMapper;

    public List<WaimaoTichengSalesTarget> list(String name){
        QueryWrapper<WaimaoTichengSalesTarget> queryWrapper =new QueryWrapper();
        if(StringUtils.isNotEmpty(name)){
            queryWrapper.eq("salesman",name);
        }
        List<WaimaoTichengSalesTarget> waimaoTichengSalesTargets = waimaoTichengSalesTargetMapper.selectList(queryWrapper);
        return  waimaoTichengSalesTargets;
    }
}
