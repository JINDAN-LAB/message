package com.jindan.jdy.service.waimao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.dto.WaimaoDowBankExpendDto;
import com.jindan.jdy.common.pojo.WaimaoDowPurchase;

import java.util.List;

/**   
 * @Description:TODO(外贸道氏服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface WaimaoDowPurchaseService extends IService<WaimaoDowPurchase> {

    List<WaimaoDowPurchase> seletelist(WaimaoDowPurchase jdyRole);

    List<WaimaoDowBankExpendDto> getDetails(String id);

    List<WaimaoDowBankExpendDto> getDetailsAll();

    /*外贸道氏采购信息导出*/
    List<WaimaoDowBankExpendDto> getWaimaoDowPurchaseDetailExportExcel();
}