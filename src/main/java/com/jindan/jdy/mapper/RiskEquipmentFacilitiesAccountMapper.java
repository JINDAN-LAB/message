package com.jindan.jdy.mapper;

import com.jindan.jdy.common.pojo.RiskEquipmentFacilitiesAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liangfang
 * @since 2021-08-06
 */
@Mapper
public interface RiskEquipmentFacilitiesAccountMapper extends BaseMapper<RiskEquipmentFacilitiesAccount> {

    /*设备设施台账导出*/
    List<RiskEquipmentFacilitiesAccount> getRiskEFAExcel();

    /*设备设施台账导入*/
    void saveAllBatch(List<RiskEquipmentFacilitiesAccount> list);

    /*批量设置负责人*/
    int updateSave(List<RiskEquipmentFacilitiesAccount> list);
}
