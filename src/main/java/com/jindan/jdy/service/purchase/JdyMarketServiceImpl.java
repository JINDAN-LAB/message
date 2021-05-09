package com.jindan.jdy.service.purchase;

import com.jindan.jdy.common.pojo.JdyMarket;
import com.jindan.jdy.common.mapper.JdyMarketDao;
import com.jindan.jdy.service.purchase.JdyMarketService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

/**   
 * @Description:TODO(销售表头信息服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Service(version = "service.version")
@Component
public class JdyMarketServiceImpl  extends ServiceImpl<JdyMarketDao,JdyMarket> implements JdyMarketService  {
	
}