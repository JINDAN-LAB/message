package com.jindan.jdy.common.dto;


import com.jindan.jdy.common.pojo.WaimaoDowBankExpend;
import com.jindan.jdy.common.pojo.WaimaoDowBankIncome;
import com.jindan.jdy.common.pojo.WaimaoDowMarket;
import com.jindan.jdy.common.pojo.WaimaoDowPurchase;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WaimaoDowBankExpendDto implements Serializable {

    List<WaimaoDowBankExpend> waimaoDowBankExpendList;

    List<WaimaoDowBankIncome> waimaoDowBankIncomelist;

    List<WaimaoDowMarket> waimaoDowMarketlist;

    List<WaimaoDowPurchase> waimaoDowPurchaselist;

}
