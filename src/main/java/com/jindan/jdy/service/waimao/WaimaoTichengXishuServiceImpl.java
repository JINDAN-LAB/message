package com.jindan.jdy.service.waimao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.mapper.WaimaoTichengXishuDao;
import com.jindan.jdy.common.pojo.WaimaoTichengXishu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(外贸提成服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class WaimaoTichengXishuServiceImpl  extends ServiceImpl<WaimaoTichengXishuDao,WaimaoTichengXishu> implements WaimaoTichengXishuService  {


    @Autowired
    WaimaoTichengXishuDao waimaoTichengXishuDao;


    @Override
    public List<WaimaoTichengXishu> seletelist(WaimaoTichengXishu jdyRole) {
        QueryWrapper<WaimaoTichengXishu> queryWrapper =new QueryWrapper<>();
        if( jdyRole.getId() !=null){
            queryWrapper.like("id",jdyRole.getId());
        }
        return waimaoTichengXishuDao.selectList(queryWrapper);
    }
}