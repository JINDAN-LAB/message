package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchMaintainRegisterDto;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.StarchMaintainRegister;

import java.util.List;

/**   
 * @Description:TODO(资产维修登记数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface StarchMaintainRegisterMapper extends BaseMapper<StarchMaintainRegister> {

    List<StarchMaintainRegisterDto> queryRelevanceCatList(StarchMaintainRegisterDto jdyFlowCatalog);

    List<StarchMaintainRegister> seletByZichan(String id);

//    获取修信息
    List<StarchMaintainRegister> seleteJidanxiu();
}
