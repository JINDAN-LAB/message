package com.jindan.jdy.service.foodsafety;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.JdyAppletFoodSafetyDto;
import com.jindan.jdy.mapper.JdyAppletFoodSafetyMapper;
import com.jindan.jdy.mapper.JdyAppletFootSafetyPersonMapper;
import com.jindan.jdy.common.pojo.JdyAppletFoodSafety;
import com.jindan.jdy.common.pojo.JdyAppletFootSafetyPerson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**   
 * @Description:TODO(视频安全小程序服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Slf4j
@Component
public class JdyAppletFoodSafetyServiceImpl  extends ServiceImpl<JdyAppletFoodSafetyMapper,JdyAppletFoodSafety> implements JdyAppletFoodSafetyService  {

    @Autowired
    JdyAppletFoodSafetyMapper jdyAppletFoodSafetyDao;

    @Autowired
    JdyAppletFootSafetyPersonMapper jdyAppletFootSafetyPersonDao;

    @Override
    public List<JdyAppletFoodSafetyDto> seleChejianProblems(String pwd) {
        log.info("“seleChejianProblems方法的参数pwd=："+"pwd");
        QueryWrapper<JdyAppletFootSafetyPerson> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("password",pwd);
        List<JdyAppletFootSafetyPerson> jdyAppletFootSafetyPeople = jdyAppletFootSafetyPersonDao.selectList(queryWrapper);
        List<JdyAppletFoodSafetyDto> data = jdyAppletFoodSafetyDao.seleteList(null);
        List<JdyAppletFoodSafetyDto> categoriesList = new ArrayList<>();
        switch (jdyAppletFootSafetyPeople.get(0).getQuanxian()){
            case "车间":
                log.info("车间履职情况");
                for(JdyAppletFoodSafetyDto  category : data){
                    if(category.getParentId().equals("0") && jdyAppletFootSafetyPeople.get(0).getChejian().equals(category.getId())){
                        categoriesList.add(category);
                    }
                }
                break;
            default:
                log.info("其他内容履职情况");
                for(JdyAppletFoodSafetyDto  category : data){
                    switch (jdyAppletFootSafetyPeople.get(0).getQuanxian()){
                        case "总监":
                            if(category.getParentId().equals("0") && Float.valueOf(category.getOrders()) >= 3){
                                categoriesList.add(category);
                            }
                            break;
                        case "公司":
                            if(category.getParentId().equals("0") && Float.valueOf(category.getOrders()) >= 4){
                                categoriesList.add(category);
                            }
                            break;
                        default:
                            if(category.getParentId().equals("0")){
                                categoriesList.add(category);
                            }
                            break;
                        }
                   }
                  break;
            }
        return categoriesList;
    }

    @Override
    public List<JdyAppletFoodSafetyDto> seleteAllChejianDrtment(JdyAppletFoodSafetyDto jdyAppletFoodSafetyDto) {
        QueryWrapper<JdyAppletFoodSafetyDto> queryWrapper =new QueryWrapper<>();
        if( jdyAppletFoodSafetyDto.getId() !=null &&  jdyAppletFoodSafetyDto.getId()!= ""){
            queryWrapper.eq("id",jdyAppletFoodSafetyDto.getId());
        }
        if( jdyAppletFoodSafetyDto.getName() !=null && !jdyAppletFoodSafetyDto.getName().isEmpty() ){
            queryWrapper.eq("name",jdyAppletFoodSafetyDto.getName());
        }
        if( jdyAppletFoodSafetyDto.getParentId() !=null && !jdyAppletFoodSafetyDto.getParentId().isEmpty() ){
            queryWrapper.eq("parent_id",jdyAppletFoodSafetyDto.getParentId());
        }
        if( jdyAppletFoodSafetyDto.getOrders() !=null && !jdyAppletFoodSafetyDto.getOrders().isEmpty() ){
            queryWrapper.eq("order",jdyAppletFoodSafetyDto.getOrders());
        }
        List<JdyAppletFoodSafetyDto> data = jdyAppletFoodSafetyDao.seleteList(null);
        List<JdyAppletFoodSafetyDto> categoriesList = new ArrayList<>();
        //先找到所有的一级分类
        for(JdyAppletFoodSafetyDto  category : data){
            if(category.getParentId().equals("0")){
                categoriesList.add(category);
            }
       }
      return categoriesList;
    }

    @Override
    public boolean insertsave(JdyAppletFoodSafety users) {
        return jdyAppletFoodSafetyDao.insert(users) > 0;
    }


    @Override
    public List<JdyAppletFoodSafetyDto> seleteAllList(JdyAppletFoodSafetyDto jdyAppletFoodSafetyDto) {
        List<JdyAppletFoodSafetyDto> data = jdyAppletFoodSafetyDao.seleteList(jdyAppletFoodSafetyDto);
        List<JdyAppletFoodSafetyDto> categoriesList = new ArrayList<>();
        for(JdyAppletFoodSafetyDto  category : data){
            if(category.getParentId().equals("0")){
                categoriesList.add(category);
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for(JdyAppletFoodSafetyDto category : categoriesList){
            category.setChildsList(getChilde(category.getId(), data));
        }

        return categoriesList;
    }

    @Override
    public List<JdyAppletFoodSafetyDto> seletePersonList(String jdyAppletFoodSafetyDto) {
        QueryWrapper<JdyAppletFootSafetyPerson> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("password",jdyAppletFoodSafetyDto);
        List<JdyAppletFootSafetyPerson> jdyAppletFootSafetyPeople = jdyAppletFootSafetyPersonDao.selectList(queryWrapper);
        int  i = 0;
        List<JdyAppletFoodSafetyDto> data = jdyAppletFoodSafetyDao.seleteList(null);
        //定义新的list
        List<JdyAppletFoodSafetyDto> categoriesList = new ArrayList<>();
        //先找到所有的一级分类
        for(JdyAppletFoodSafetyDto  category : data){
        // 一级菜单的parentId是0
            switch (jdyAppletFootSafetyPeople.get(0).getQuanxian()){
                case "车间":
                    if(category.getParentId().equals("0") &&  jdyAppletFootSafetyPeople.get(0).getChejian().equals(category.getId()) ){
                        categoriesList.add(category);
                    }
                    break;
                case "总监":
                    if(category.getParentId().equals("0")){
                        categoriesList.add(category);
                    }
                    break;
                case "公司":
                    if(category.getParentId().equals("0")){
                        categoriesList.add(category);
                    }
                    break;
                default:
                    if(category.getParentId().equals("0") && jdyAppletFootSafetyPeople.get(0).getChejian().equals(category.getId())){
                        categoriesList.add(category);
                    }
                    break;
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for(JdyAppletFoodSafetyDto category : categoriesList){
            category.setChildsList(getPersonChilde(category.getId(), data,i,jdyAppletFootSafetyPeople));
        }
        return  categoriesList;
    }

    private List<JdyAppletFoodSafetyDto> getPersonChilde(String id, List<JdyAppletFoodSafetyDto> rootList, int i, List<JdyAppletFootSafetyPerson> jdyAppletFootSafetyPeople ){
        //工段的获取菜单
        log.info("“JdyAppletFoodSafetyServiceImpl.getPersonChilde方法”开始执行======");
        i++;
        List<JdyAppletFoodSafetyDto> childList = new ArrayList<>();
        for(JdyAppletFoodSafetyDto category : rootList){

            switch (jdyAppletFootSafetyPeople.get(0).getQuanxian()){
                case "车间":
                    if(category.getParentId().equals(id)  ){
                        childList.add(category);
                    }
                    break;
                case "总监":
                    if(category.getParentId().equals(id) ){
                        childList.add(category);
                    }
                    break;
                case "公司":
                    if(category.getParentId().equals(id)  ){
                        childList.add(category);
                    }
                    break;
                default:
                    switch (i){
                        case 1:
//                            log.info("获取工段信息");
                            if(category.getParentId().equals(id) && category.getId().equals(jdyAppletFootSafetyPeople.get(0).getBanzu())){
                                childList.add(category);
                            }
                            break;
                        default :
//                            log.info("获取履职11111信息");
                            if(category.getParentId().equals(id)){
                                childList.add(category);
                            }
                            break;
                    }
                    break;
            }
        }
        // 把子菜单的子菜单再循环一遍
        for(JdyAppletFoodSafetyDto category : childList){
            category.setChildsList(getPersonChilde(category.getId(), rootList,i,jdyAppletFootSafetyPeople));
        }
        // 递归退出条件
        if(childList.size() == 0){
            return null;
        }
        return childList;
    }


    @Override
    public List<JdyAppletFoodSafetyDto> seleteList(JdyAppletFoodSafetyDto jdyAppletFoodSafetyDto) {
        List<JdyAppletFoodSafetyDto> data = jdyAppletFoodSafetyDao.seleteList(jdyAppletFoodSafetyDto);
        List<JdyAppletFoodSafetyDto> categoriesList = new ArrayList<>();
        for(JdyAppletFoodSafetyDto  category : data){
            if(category.getParentId().equals("0")){
                categoriesList.add(category);
            }
        }
        for(JdyAppletFoodSafetyDto category : categoriesList){
            category.setChildsList(getChilde(category.getId(), data));
        }
        return categoriesList;
    }

    private List<JdyAppletFoodSafetyDto> getChilde(String id, List<JdyAppletFoodSafetyDto> rootList){
        //子菜单
        List<JdyAppletFoodSafetyDto> childList = new ArrayList<>();
        for(JdyAppletFoodSafetyDto category : rootList){
            if(category.getParentId().equals(id)){
                childList.add(category);
            }
        }
        for(JdyAppletFoodSafetyDto category : childList){
            category.setChildsList(getChilde(category.getId(), rootList));
        }
        if (childList.size() == 0){
            return null;
        }
        return childList;
    }

}