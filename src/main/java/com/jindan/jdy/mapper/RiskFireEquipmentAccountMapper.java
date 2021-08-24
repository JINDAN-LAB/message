package com.jindan.jdy.mapper;

import com.jindan.jdy.common.pojo.RiskFireEquipmentAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liangfang
 * @since 2021-07-16
 */
@Mapper
public interface RiskFireEquipmentAccountMapper extends BaseMapper<RiskFireEquipmentAccount> {

    /*消防设备台账导出*/
    List<RiskFireEquipmentAccount> getRiskFEAExcel();

    /*消防设备台账导入*/
    void saveAllBatch(List<RiskFireEquipmentAccount> list);
}
