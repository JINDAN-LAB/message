package com.jindan.jdy.service.purchase;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.mapper.JdyPutCheckDetailsDao;
import com.jindan.jdy.common.pojo.JdyPutCheckDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(风险控制任务超期服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class JdyPutCheckDetailsServiceImpl  extends ServiceImpl<JdyPutCheckDetailsDao,JdyPutCheckDetails> implements JdyPutCheckDetailsService  {

    @Autowired
    JdyPutCheckDetailsDao jdyPutCheckDetailsDao;


    @Override
    public boolean insertSave(JdyPutCheckDetails jdyPurchaseDto) {
        return jdyPutCheckDetailsDao.insert(jdyPurchaseDto) > 0;
    }

    @Override
    public List<JdyPutCheckDetails> seletelist(JdyPutCheckDetails jdyPurchaseDto) {

        QueryWrapper<JdyPutCheckDetails> queryWrapper =new QueryWrapper<>();
        if(jdyPurchaseDto.getCid() !=null && !jdyPurchaseDto.getCid().isEmpty()){
            queryWrapper.eq("cid",jdyPurchaseDto.getCid());
        }
        if(jdyPurchaseDto.getCname() !=null && !jdyPurchaseDto.getCname().isEmpty()){
            queryWrapper.eq("cname",jdyPurchaseDto.getCname());
        }
        if(jdyPurchaseDto.getCnums() !=null && jdyPurchaseDto.getCnums() > 0){
            queryWrapper.eq("cnums",jdyPurchaseDto.getCnums());
        }
        if(jdyPurchaseDto.getCnResultNum() !=null && jdyPurchaseDto.getCnResultNum() > 0){
            queryWrapper.eq("cn_result_num",jdyPurchaseDto.getCnResultNum());
        }
        if(jdyPurchaseDto.getDifferenceNum() !=null && jdyPurchaseDto.getDifferenceNum() > 0){
            queryWrapper.eq("difference_num",jdyPurchaseDto.getDifferenceNum());
        }
        List<JdyPutCheckDetails> jdyPurchases = jdyPutCheckDetailsDao.selectList(queryWrapper);
        return jdyPurchases;
    }

}