package com.jindan.jdy.service.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jindan.jdy.common.pojo.JdyAppletManagers;
import com.jindan.jdy.common.mapper.JdyAppletManagersDao;
import com.jindan.jdy.common.pojo.JdyClassroom;
import com.jindan.jdy.service.sys.JdyAppletManagersService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(设备维修申报服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class JdyAppletManagersServiceImpl  extends ServiceImpl<JdyAppletManagersDao,JdyAppletManagers> implements JdyAppletManagersService  {

    @Autowired
    JdyAppletManagersDao jdyAppletManagersDao;

    @Override
    public List<JdyAppletManagers> seletedslist(JdyAppletManagers jdyClassroom) {

        QueryWrapper<JdyAppletManagers> queryWrapper =new QueryWrapper<>();
        if(jdyClassroom.getJapid() !=null &&  !jdyClassroom.getJapid().isEmpty()  ){
            queryWrapper.eq("japid",jdyClassroom.getJapid());
        }
        if(jdyClassroom.getLocations() !=null && !jdyClassroom.getLocations().isEmpty() ){
            queryWrapper.eq("locations",jdyClassroom.getLocations());
        }
        if(jdyClassroom.getPersons() !=null && !jdyClassroom.getPersons().isEmpty() ){
            queryWrapper.eq("persons",jdyClassroom.getPersons());
        }
        if(jdyClassroom.getPhones() !=null && !jdyClassroom.getPhones().isEmpty() ){
            queryWrapper.eq("phones",jdyClassroom.getPhones());
        }
        if(jdyClassroom.getInsertTime() !=null ){
            queryWrapper.eq("insert_time",jdyClassroom.getInsertTime());
        }
        if(jdyClassroom.getDeleteId() !=null   ){
            queryWrapper.ge("delete_id",jdyClassroom.getDeleteId());
        }
        if(jdyClassroom.getUpdateTime() !=null){
            queryWrapper.eq("update_time",jdyClassroom.getUpdateTime());
        }
        return jdyAppletManagersDao.selectList(queryWrapper);
    }



}