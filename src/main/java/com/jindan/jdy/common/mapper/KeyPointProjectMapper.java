package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.KeyPointProjectDto;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.KeyPointProject;

import java.util.List;

/**   
 * @Description:TODO(重点项目数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface KeyPointProjectMapper extends BaseMapper<KeyPointProject> {

    List<KeyPointProjectDto> seleteKeyPointProjectexcle(KeyPointProject keyPointProject);
}
