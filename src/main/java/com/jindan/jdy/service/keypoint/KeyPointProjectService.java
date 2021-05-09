package com.jindan.jdy.service.keypoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.KeyPointProjectDto;
import com.jindan.jdy.common.pojo.KeyPointProject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(重点项目服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface KeyPointProjectService extends IService<KeyPointProject> {

    Page<KeyPointProject> seleteDepartmentFacility(KeyPointProjectDto departmentSuggestDto);

//    详细查询 落实 重点工作 进度完成情况
    List<KeyPointProjectDto> seleteKeyPointProjectexcle(KeyPointProject keyPointProject);
}