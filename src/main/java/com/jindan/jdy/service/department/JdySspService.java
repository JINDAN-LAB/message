package com.jindan.jdy.service.department;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.JdySspDto;
import com.jindan.jdy.common.pojo.JdySsp;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(二维码目录服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdySspService extends IService<JdySsp> {


    List<JdySsp> seletelist(JdySspDto jdySsp);

    boolean deleteById(String ids);

    Page<JdySsp> seletePagelist(JdySspDto jdySsp);
}