package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.WarehouseCheckDto;
import com.jindan.jdy.common.dto.WarehouseCheckTitleDto;
import com.jindan.jdy.common.mapper.WarehouseCheckDao;
import com.jindan.jdy.common.mapper.WarehouseCheckTitleMapper;
import com.jindan.jdy.common.pojo.WarehouseCheck;
import com.jindan.jdy.common.pojo.WarehouseCheckTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(规则服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class WarehouseCheckTitleServiceImpl  extends ServiceImpl<WarehouseCheckTitleMapper,WarehouseCheckTitle> implements WarehouseCheckTitleService  {

    @Autowired
    WarehouseCheckTitleMapper warehouseCheckTitleDao;

    @Autowired
    WarehouseCheckDao warehouseCheckDao;

    @Override
    public List<WarehouseCheckDto> seletelist(WarehouseCheck  departmentSuggestDto) {
        System.out.println(departmentSuggestDto);
        return warehouseCheckTitleDao.seletelist(departmentSuggestDto);
    }

    @Override
    public boolean removeAllAccess(String id) {
        QueryWrapper<WarehouseCheckTitle> queryWrapper =new QueryWrapper<>();
        if(id !=null &&  !id.isEmpty()  ){
            queryWrapper.eq("check_uuid",id);
        }
        QueryWrapper<WarehouseCheck> queryWrapper2 =new QueryWrapper<>();
        if(id !=null &&  !id.isEmpty()  ){
            queryWrapper2.eq("check_uuid",id);
        }
        int delete = warehouseCheckTitleDao.delete(queryWrapper);
        int delete1 = warehouseCheckDao.delete(queryWrapper2);
        if(delete > 0 && delete1 > 0 ){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<WarehouseCheckTitleDto> seletelistDepartmentSuggestDto(WarehouseCheckTitle departmentSuggestDto) {
        return warehouseCheckTitleDao.seletelistDepartmentSuggestDto(departmentSuggestDto);
    }

    @Override
    public List<WarehouseCheckTitle> seleteTile(WarehouseCheckTitle departmentSuggestDto) {
        QueryWrapper<WarehouseCheckTitle>  queryWrapper =new QueryWrapper<>();

        if(departmentSuggestDto.getCheckId() !=null &&  !departmentSuggestDto.getCheckId().isEmpty()  ){
            queryWrapper.eq("check_id",departmentSuggestDto.getCheckId());
        }
        if(departmentSuggestDto.getParentId() !=null && !departmentSuggestDto.getParentId().isEmpty() ){
            queryWrapper.eq("parent_id",departmentSuggestDto.getParentId());
        }
               if(departmentSuggestDto.getCheckNum() !=null && departmentSuggestDto.getCheckNum() > 0 ){
            queryWrapper.eq("check_num",departmentSuggestDto.getCheckNum());
        }
        if(departmentSuggestDto.getCheckTime() !=null && !departmentSuggestDto.getCheckTime().isEmpty() ){
            queryWrapper.eq("check_time",departmentSuggestDto.getCheckTime());
        }
        if(departmentSuggestDto.getCheckPersons() !=null && !departmentSuggestDto.getCheckPersons().isEmpty() ){
            queryWrapper.eq("check_persons",departmentSuggestDto.getCheckPersons());
        }
        if(departmentSuggestDto.getInsertTime() !=null ){
            queryWrapper.eq("insert_time",departmentSuggestDto.getInsertTime());
        }
        if(departmentSuggestDto.getRemarks() !=null && !departmentSuggestDto.getRemarks().isEmpty() ){
            queryWrapper.eq("remarks",departmentSuggestDto.getRemarks());
        }
        return warehouseCheckTitleDao.selectList(queryWrapper);
    }


}