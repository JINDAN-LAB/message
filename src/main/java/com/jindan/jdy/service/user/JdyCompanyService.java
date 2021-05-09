package com.jindan.jdy.service.user;

import com.jindan.jdy.common.pojo.JdyCompany;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(资产子设备信息服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdyCompanyService extends IService<JdyCompany> {

    List<JdyCompany> seletelist(JdyCompany jdyDepartments);
}