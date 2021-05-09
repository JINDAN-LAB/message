package com.jindan.jdy.service.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.JdyUserFileDto;
import com.jindan.jdy.common.mapper.JdyUserFileDao;
import com.jindan.jdy.common.pojo.JdyUserFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(API应用KEY服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class JdyUserFileServiceImpl  extends ServiceImpl<JdyUserFileDao,JdyUserFile> implements JdyUserFileService  {

    @Autowired
    JdyUserFileDao  jdyUserFileDtos;

    @Override
    public List<JdyUserFile> seletelist(JdyUserFileDto jdyUserFileDto) {

        QueryWrapper<JdyUserFile> queryWrapper =new QueryWrapper<>();
        if( jdyUserFileDto.getUserId() !=null &&  !jdyUserFileDto.getUserId().isEmpty()  ){
            queryWrapper.eq("user_id",jdyUserFileDto.getUserId());
        }
        if( jdyUserFileDto.getFileDescribe() !=null && !jdyUserFileDto.getFileDescribe().isEmpty() ){
            queryWrapper.like("file_describe",jdyUserFileDto.getFileDescribe());
        }
        if( jdyUserFileDto.getParentId() !=null && !jdyUserFileDto.getParentId().isEmpty() ){
            queryWrapper.eq("parent_id",jdyUserFileDto.getParentId());
        }
        if( jdyUserFileDto.getFileId() !=null && !jdyUserFileDto.getFileId().isEmpty() ){
            queryWrapper.eq("file_id",jdyUserFileDto.getFileId());
        }
        if( jdyUserFileDto.getUserFileName() !=null && !jdyUserFileDto.getUserFileName().isEmpty() ){
            queryWrapper.eq("user_file_name",jdyUserFileDto.getUserFileName());
        }
        if( jdyUserFileDto.getFileName() !=null && !jdyUserFileDto.getFileName().isEmpty() ){
            queryWrapper.eq("file_name",jdyUserFileDto.getFileName());
        }
        return jdyUserFileDtos.selectList(queryWrapper);
    }

}