package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.StarchClassifyPutConsumableDto;
import com.jindan.jdy.common.dto.StarchOrganizationPutDto;
import com.jindan.jdy.common.pojo.StarchOrganizationPutConsumable;
import com.jindan.jdy.common.pojo.StarchOrganizationPutConsumableChuru;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.StarchOrganizationPut;

import java.util.List;

/**   
 * @Description:TODO(资产入库数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface StarchOrganizationPutMapper extends BaseMapper<StarchOrganizationPut> {

    List<StarchOrganizationPutDto> queryRelevanceCatList(StarchOrganizationPutDto jdyRole);

    List<StarchOrganizationPutDto> queryDetailsWeibaoCatList(StarchOrganizationPut jdyFlowCatalog);

    List<StarchClassifyPutConsumableDto> queryDetailsHuizongCatList(StarchOrganizationPutDto jdyFlowCatalog);

    List<StarchClassifyPutConsumableDto> querShiyongFenlei();

    List<StarchOrganizationPutDto> queryZichandaoqi(StarchOrganizationPutDto jdyFlowCatalog);

    List<StarchOrganizationPutDto> queryWeibaodaoqi(StarchOrganizationPutDto jdyFlowCatalog);

    List<StarchOrganizationPutDto> querZIchanShoiuye();

    boolean updateAddDetailsPut(StarchOrganizationPutConsumable starchOrganizationPutConsumableChuru);

    boolean updateDeleteDetailsPut(StarchOrganizationPutConsumable starchOrganizationPut);

    List<StarchOrganizationPut> seleteOrganizationPut(StarchOrganizationPut starchOrganizationPut);

    List<StarchOrganizationPutDto> queryYiquerenCatList(StarchOrganizationPutDto jdyFlowCatalog);

    List<StarchClassifyPutConsumableDto> seletFenleiBumenShiyong();

    List<StarchClassifyPutConsumableDto> seletFenleiGeBumenShiyong();
}
