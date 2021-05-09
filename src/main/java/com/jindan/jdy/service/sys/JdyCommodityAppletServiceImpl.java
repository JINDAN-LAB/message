package com.jindan.jdy.service.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.mapper.JdyCommodityAppletDao;
import com.jindan.jdy.common.pojo.JdyCommodityApplet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(小程序商品服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class JdyCommodityAppletServiceImpl  extends ServiceImpl<JdyCommodityAppletDao,JdyCommodityApplet> implements JdyCommodityAppletService  {

    @Autowired
    JdyCommodityAppletDao jdyCommodityAppletDao;


    @Override
    public boolean saveJdyDomain(JdyCommodityApplet jdyDomain) {
        return jdyCommodityAppletDao.insert(jdyDomain) > 0;
    }

    @Override
    public List<JdyCommodityApplet> seleteAlllist(JdyCommodityApplet jdyDomain) {

        QueryWrapper<JdyCommodityApplet> queryWrapper =new QueryWrapper<>();
        if(jdyDomain.getXid() !=null &&  !jdyDomain.getXid().isEmpty()){
            queryWrapper.eq("xid",jdyDomain.getXid());
        }
        if(jdyDomain.getXtitle() !=null && !jdyDomain.getXtitle().isEmpty()){
            queryWrapper.eq("xtitle",jdyDomain.getXtitle());
        }
        if(jdyDomain.getXcontent() !=null && !jdyDomain.getXcontent().isEmpty()){
            queryWrapper.eq("xcontent",jdyDomain.getXcontent());
        }
        if(jdyDomain.getXtime() !=null && !jdyDomain.getXtime().isEmpty()){
            queryWrapper.eq("xtime",jdyDomain.getXtime());
        }
        if(jdyDomain.getXperson() !=null && !jdyDomain.getXperson().isEmpty()){
            queryWrapper.eq("xperson",jdyDomain.getXperson());
        }
      return jdyCommodityAppletDao.selectList(queryWrapper);
    }
}