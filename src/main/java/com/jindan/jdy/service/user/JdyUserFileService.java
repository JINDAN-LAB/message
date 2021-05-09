package com.jindan.jdy.service.user;

import com.jindan.jdy.common.dto.JdyUserFileDto;
import com.jindan.jdy.common.pojo.JdyUserFile;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(API应用KEY服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdyUserFileService extends IService<JdyUserFile> {

    List<JdyUserFile> seletelist(JdyUserFileDto jdyUserFileDto);
}