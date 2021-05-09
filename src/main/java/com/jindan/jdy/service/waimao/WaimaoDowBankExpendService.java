package com.jindan.jdy.service.waimao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.pojo.WaimaoDowBankExpend;

import java.util.List;

/**   
 * @Description:TODO(外贸道氏服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface WaimaoDowBankExpendService extends IService<WaimaoDowBankExpend> {

    List<WaimaoDowBankExpend> seletelist(WaimaoDowBankExpend jdyRole);
}