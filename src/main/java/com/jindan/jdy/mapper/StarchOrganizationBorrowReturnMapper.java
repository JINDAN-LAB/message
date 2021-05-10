package com.jindan.jdy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.StarchOrganizationBorrowReturnDto;
import com.jindan.jdy.common.pojo.StarchOrganizationBorrowReturn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**   
 * @Description:TODO(资产借用与归还数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface StarchOrganizationBorrowReturnMapper extends BaseMapper<StarchOrganizationBorrowReturn> {

    List<StarchOrganizationBorrowReturnDto> queryRelevanceCatList(StarchOrganizationBorrowReturnDto jdyFlowCatalog);
}
