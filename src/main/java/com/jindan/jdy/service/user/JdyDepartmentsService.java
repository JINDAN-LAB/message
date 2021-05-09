package com.jindan.jdy.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.dto.JdyCompanyDto;
import com.jindan.jdy.common.pojo.JdyDepartments;

import java.util.List;

/**   
 * @Description:TODO(API应用KEY服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdyDepartmentsService extends IService<JdyDepartments> {

    List<JdyDepartments> seletelist(JdyDepartments jdyDepartments);

    List<JdyCompanyDto> seleteJdyCompanylist(JdyCompanyDto jdyDepartments);
}