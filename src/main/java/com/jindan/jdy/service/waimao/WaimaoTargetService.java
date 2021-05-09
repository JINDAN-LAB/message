package com.jindan.jdy.service.waimao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.pojo.WaimaoTarget;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description:TODO(设备维修申报服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface WaimaoTargetService extends IService<WaimaoTarget> {

    List<WaimaoTarget> seletelist(WaimaoTarget waimaoHuikuan);
}