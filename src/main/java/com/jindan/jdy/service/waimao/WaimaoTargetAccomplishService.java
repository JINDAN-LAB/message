package com.jindan.jdy.service.waimao;

import com.jindan.jdy.common.pojo.WaimaoTargetAccomplish;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(外贸目标完成服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface WaimaoTargetAccomplishService extends IService<WaimaoTargetAccomplish> {

    List<WaimaoTargetAccomplish> seletelist(WaimaoTargetAccomplish waimaoTargetAccomplish);

    List<WaimaoTargetAccomplish> seletehuilvlist();
}