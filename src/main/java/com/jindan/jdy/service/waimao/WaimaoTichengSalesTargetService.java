package com.jindan.jdy.service.waimao;

import com.jindan.jdy.common.pojo.WaimaoTichengSalesTarget;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangfang
 * @since 2021-08-15
 */
public interface WaimaoTichengSalesTargetService extends IService<WaimaoTichengSalesTarget> {

    List<WaimaoTichengSalesTarget> list(String name);

}
