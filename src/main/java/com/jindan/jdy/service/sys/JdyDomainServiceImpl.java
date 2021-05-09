package com.jindan.jdy.service.sys;

import com.jindan.jdy.common.pojo.JdyDomain;
import com.jindan.jdy.common.mapper.JdyDomainMapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class JdyDomainServiceImpl  extends ServiceImpl<JdyDomainMapper,JdyDomain> implements JdyDomainService  {

     @Autowired
     JdyDomainMapper jdyDomainDao ;

    @Override
    public boolean saveJdyDomain(JdyDomain jdyDomain) {
        return jdyDomainDao.insert(jdyDomain) > 0 ;
    }

    @Override
    public List<JdyDomain> seletelist(Object o) {
        return jdyDomainDao.selectList(null);
    }
}