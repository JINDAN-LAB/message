package com.jindan.jdy.service.waimao;

import com.jindan.jdy.common.pojo.WaimaoArea;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.pojo.WaimaoFahuo;

import java.util.List;

/**   
 * @Description:TODO(设备维修申报服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface WaimaoAreaService extends IService<WaimaoArea> {

    List<WaimaoArea> seletelist(WaimaoArea jdyRole);

    List<WaimaoArea> seleteQuyulist();
}