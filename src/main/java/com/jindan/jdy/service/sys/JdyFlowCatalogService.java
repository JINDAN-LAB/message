package com.jindan.jdy.service.sys;

import com.jindan.jdy.common.dto.JdyFlowCatalogDto;
import com.jindan.jdy.common.pojo.JdyFlowCatalog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(流程目录信息服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdyFlowCatalogService extends IService<JdyFlowCatalog> {

    List<JdyFlowCatalogDto> queryCatList(JdyFlowCatalog jdyFlowCatalog);
}