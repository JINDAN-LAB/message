package com.jindan.jdy.service.waimao;

import com.jindan.jdy.common.pojo.WaimaoTichengXishu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(外贸提成服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface WaimaoTichengXishuService extends IService<WaimaoTichengXishu> {

    List<WaimaoTichengXishu> seletelist(WaimaoTichengXishu jdyRole);
}