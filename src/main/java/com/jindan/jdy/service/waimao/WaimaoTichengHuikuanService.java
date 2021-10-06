package com.jindan.jdy.service.waimao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.dto.WaimaoTichengHuikuanDto;
import com.jindan.jdy.common.pojo.WaimaoTichengHuikuan;

import java.util.List;

/**   
 * @Description:TODO(外贸提成服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface WaimaoTichengHuikuanService extends IService<WaimaoTichengHuikuan> {

    Page<WaimaoTichengHuikuan> seletelist(WaimaoTichengHuikuanDto jdyRole);

    List<WaimaoTichengHuikuan> seleteYuebiaoHuikuan();

    List<WaimaoTichengHuikuan> selectHuikuanByBiaoshi(String biaoshi);

    List<WaimaoTichengHuikuan> selectHuikuanByFaopiaohao(String fapiaohao);
}