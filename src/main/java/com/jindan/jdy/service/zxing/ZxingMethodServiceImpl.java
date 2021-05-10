package com.jindan.jdy.service.zxing;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.mapper.ZxingMethodDao;
import com.jindan.jdy.common.pojo.ZxingMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(自定义验证二维码域名目录服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class ZxingMethodServiceImpl  extends ServiceImpl<ZxingMethodDao,ZxingMethod> implements ZxingMethodService  {


    @Autowired
    ZxingMethodDao zxingMethodDao;


    @Override
    public List<ZxingMethod> seletelist(ZxingMethod zxingMethod) {
        QueryWrapper<ZxingMethod> queryWrapper =new QueryWrapper<>();
        if( zxingMethod.getMethod() !=null &&  zxingMethod.getMethod()!= ""  ){
            queryWrapper.like("method",zxingMethod.getMethod());
        }
        if( zxingMethod.getMethodname() !=null &&  zxingMethod.getMethodname()!= ""  ){
            queryWrapper.like("methodname",zxingMethod.getMethodname());
        }
        if( zxingMethod.getId() !=null &&  zxingMethod.getId()!= ""  ){
            queryWrapper.eq("id",zxingMethod.getId());
        }
        if( zxingMethod.getInsertTime() !=null){
            queryWrapper.eq("insert_time",zxingMethod.getInsertTime());
        }
        return zxingMethodDao.selectList(queryWrapper);
    }
}