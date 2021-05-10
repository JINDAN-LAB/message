package com.jindan.jdy.service.foodsafety;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.mapper.JdyAppletFootSafetyPersonMapper;
import com.jindan.jdy.common.pojo.JdyAppletFootSafetyPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(食品安全人员管理服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class JdyAppletFootSafetyPersonServiceImpl  extends ServiceImpl<JdyAppletFootSafetyPersonMapper,JdyAppletFootSafetyPerson> implements JdyAppletFootSafetyPersonService  {

    @Autowired
    JdyAppletFootSafetyPersonMapper jdyAppletFootSafetyPersonDao;

    @Override
    public List<JdyAppletFootSafetyPerson> seleteList(JdyAppletFootSafetyPerson departmentSuggestDto) {

        QueryWrapper<JdyAppletFootSafetyPerson> queryWrapper =new QueryWrapper<>();
        if( departmentSuggestDto.getPid() !=null &&  departmentSuggestDto.getPid()!= ""  ){
            queryWrapper.eq("pid",departmentSuggestDto.getPid());
        }
        if( departmentSuggestDto.getUsername() !=null && !departmentSuggestDto.getUsername().isEmpty() ){
            queryWrapper.like("username",departmentSuggestDto.getUsername());
        }
        if( departmentSuggestDto.getPassword() !=null &&  departmentSuggestDto.getPassword()!= ""  ){
            queryWrapper.like("password",departmentSuggestDto.getPassword());
        }
        if( departmentSuggestDto.getDepartments() !=null && !departmentSuggestDto.getDepartments().isEmpty() ){
            queryWrapper.eq("departments",departmentSuggestDto.getDepartments());
        }
        if( departmentSuggestDto.getChejian() !=null &&  departmentSuggestDto.getChejian()!= ""  ){
            queryWrapper.like("chejian",departmentSuggestDto.getChejian());
        }
        if( departmentSuggestDto.getGongduan() !=null &&  departmentSuggestDto.getGongduan()!= ""  ){
            queryWrapper.like("gongduan",departmentSuggestDto.getGongduan());
        }
        if( departmentSuggestDto.getBanzu() !=null && !departmentSuggestDto.getBanzu().isEmpty() ){
            queryWrapper.like("banzu",departmentSuggestDto.getBanzu());
        }
        if( departmentSuggestDto.getQuanxian() !=null &&  departmentSuggestDto.getQuanxian()!= ""  ){
            queryWrapper.like("quanxian",departmentSuggestDto.getQuanxian());
        }
        if( departmentSuggestDto.getZongjianid() !=null &&  departmentSuggestDto.getZongjianid()!= ""  ){
            queryWrapper.like("zongjianid",departmentSuggestDto.getZongjianid());
        }
        if( departmentSuggestDto.getGongsi() !=null &&  departmentSuggestDto.getGongsi()!= ""  ){
            queryWrapper.like("gongsi",departmentSuggestDto.getGongsi());
        }
        return jdyAppletFootSafetyPersonDao.selectList(queryWrapper);
    }


}