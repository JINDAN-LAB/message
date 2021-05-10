package com.jindan.jdy.service.purchase;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.mapper.JdyPurchaseArriveTestDao;
import com.jindan.jdy.common.pojo.JdyPurchaseArriveTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(化验室委托单服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class JdyPurchaseArriveTestServiceImpl  extends ServiceImpl<JdyPurchaseArriveTestDao,JdyPurchaseArriveTest> implements JdyPurchaseArriveTestService  {

    @Autowired
    JdyPurchaseArriveTestDao jdyPurchaseArriveTestDao;

    @Override
    public boolean insertSave(JdyPurchaseArriveTest jdyPurchaseDto) {
        return jdyPurchaseArriveTestDao.insert(jdyPurchaseDto) > 0;
    }


    @Override
    public List<JdyPurchaseArriveTest> seletelist(JdyPurchaseArriveTest jdyPurchaseDto) {

        QueryWrapper<JdyPurchaseArriveTest> queryWrapper =new QueryWrapper<>();
        if(jdyPurchaseDto.getHid() !=null && !jdyPurchaseDto.getHid().isEmpty()){
            queryWrapper.eq("hid",jdyPurchaseDto.getHid());
        }
        if(jdyPurchaseDto.getHname() !=null && !jdyPurchaseDto.getHname().isEmpty()){
            queryWrapper.eq("hname",jdyPurchaseDto.getHname());
        }
        if(jdyPurchaseDto.getHpihao() !=null && !jdyPurchaseDto.getHpihao().isEmpty()){
            queryWrapper.eq("hpihao",jdyPurchaseDto.getHpihao());
        }
        if(jdyPurchaseDto.getGuige() !=null && !jdyPurchaseDto.getGuige().isEmpty()){
            queryWrapper.eq("guige",jdyPurchaseDto.getGuige());
        }
        if(jdyPurchaseDto.getNums() !=null && !jdyPurchaseDto.getNums().isEmpty()){
            queryWrapper.eq("nums",jdyPurchaseDto.getNums());
        }
        if(jdyPurchaseDto.getWdepartments() !=null && !jdyPurchaseDto.getWdepartments().isEmpty()){
            queryWrapper.eq("wdepartments",jdyPurchaseDto.getWdepartments());
        }
        if(jdyPurchaseDto.getWpersons() !=null && !jdyPurchaseDto.getWpersons().isEmpty()){
            queryWrapper.eq("wpersons",jdyPurchaseDto.getWpersons());
        }
        if(jdyPurchaseDto.getWtime() !=null && !jdyPurchaseDto.getWtime().isEmpty()){
            queryWrapper.eq("wtime",jdyPurchaseDto.getWtime());
        }
        if(jdyPurchaseDto.getParentId() !=null && !jdyPurchaseDto.getParentId().isEmpty()){
            queryWrapper.eq("parent_id",jdyPurchaseDto.getParentId());
        }
        List<JdyPurchaseArriveTest> jdyPurchases = jdyPurchaseArriveTestDao.selectList(queryWrapper);
        return jdyPurchases;
    }


}