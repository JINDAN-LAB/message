package com.jindan.jdy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.WaimaoDowBankExpendDto;
import com.jindan.jdy.common.pojo.WaimaoDowPurchase;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**   
 * @Description:TODO(外贸道氏数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface WaimaoDowPurchaseMapper extends BaseMapper<WaimaoDowPurchase> {

    List<WaimaoDowBankExpendDto> getDetails(String id);

    List<WaimaoDowBankExpendDto> getDetailsAll();

}
