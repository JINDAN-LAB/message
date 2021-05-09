package com.jindan.jdy.service.purchase;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jindan.jdy.common.pojo.JdyCommodityCatalog;
import com.jindan.jdy.common.pojo.JdyDetailsGoods;
import com.jindan.jdy.common.mapper.JdyDetailsGoodsDao;
import com.jindan.jdy.service.purchase.JdyDetailsGoodsService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(仓库基本信息服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class JdyDetailsGoodsServiceImpl  extends ServiceImpl<JdyDetailsGoodsDao,JdyDetailsGoods> implements JdyDetailsGoodsService  {

    @Autowired
    JdyDetailsGoodsDao jdyDetailsGoodsDao;

    @Override
    public List<JdyDetailsGoods> seletelist(JdyDetailsGoods jdyPurchaseDto) {
        QueryWrapper<JdyDetailsGoods> queryWrapper =new QueryWrapper<>();
        if(jdyPurchaseDto.getMcid() !=null &&  !jdyPurchaseDto.getMcid().isEmpty()){
            queryWrapper.eq("mcid",jdyPurchaseDto.getMcid());
        }
        if(jdyPurchaseDto.getWuliaoCode() !=null && !jdyPurchaseDto.getWuliaoCode().isEmpty()){
            queryWrapper.eq("wuliao_code",jdyPurchaseDto.getWuliaoCode());
        }
        if(jdyPurchaseDto.getWuliaoName() !=null &&  !jdyPurchaseDto.getWuliaoName().isEmpty()){
            queryWrapper.eq("wuliao_name",jdyPurchaseDto.getWuliaoName());
        }
        if(jdyPurchaseDto.getWuliaoType() !=null && !jdyPurchaseDto.getWuliaoType().isEmpty()){
            queryWrapper.eq("wuliao_type",jdyPurchaseDto.getWuliaoType());
        }
        if(jdyPurchaseDto.getNuits() !=null &&  !jdyPurchaseDto.getNuits().isEmpty()){
            queryWrapper.eq("nuits",jdyPurchaseDto.getNuits());
        }
        if(jdyPurchaseDto.getPihao() !=null &&  !jdyPurchaseDto.getPihao().isEmpty()){
            queryWrapper.eq("pihao",jdyPurchaseDto.getPihao());
        }
        if(jdyPurchaseDto.getNums() !=null && !jdyPurchaseDto.getNums().isEmpty()){
            queryWrapper.eq("nums",jdyPurchaseDto.getNums());
        }
        if(jdyPurchaseDto.getParentId() !=null &&  !jdyPurchaseDto.getParentId().isEmpty()){
            queryWrapper.eq("parent_id",jdyPurchaseDto.getParentId());
        }
        if(jdyPurchaseDto.getWuliaoMode() !=null &&  !jdyPurchaseDto.getWuliaoMode().isEmpty()){
            queryWrapper.eq("wuliao_mode",jdyPurchaseDto.getWuliaoMode());
        }
        if(jdyPurchaseDto.getWuliaoPrice() !=null && !jdyPurchaseDto.getWuliaoPrice().isEmpty()){
            queryWrapper.eq("wuliao_price",jdyPurchaseDto.getWuliaoPrice());
        }
        return jdyDetailsGoodsDao.selectList(queryWrapper);
     }

    @Override
    public boolean insertSave(JdyDetailsGoods jdyPurchaseDto) {
        return jdyDetailsGoodsDao.insert(jdyPurchaseDto) > 0;
    }
}