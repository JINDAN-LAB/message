package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.pojo.StarchOrganizationPut;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.StarchRegisterPut;
import org.apache.ibatis.annotations.Param;

/**   
 * @Description:TODO(维修登记商品关系表数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface StarchRegisterPutMapper extends BaseMapper<StarchRegisterPut> {

    StarchOrganizationPut getStringputDetails(@Param("s") String s);

}
