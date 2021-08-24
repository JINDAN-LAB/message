package com.jindan.jdy.mapper;

import com.jindan.jdy.common.pojo.RiskSafetyManagementPersonnel;
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
public interface RiskSafetyManagementPersonnelMapper extends BaseMapper<RiskSafetyManagementPersonnel> {

    int updateSave(RiskSafetyManagementPersonnel riskSafetyManagementPersonnel);

    /*特种证书台账导出*/
    List<RiskSafetyManagementPersonnel> getRiskSMPExcel();

    /*特种证书台账导入*/
    void saveAllBatch(List<RiskSafetyManagementPersonnel> list);
}
