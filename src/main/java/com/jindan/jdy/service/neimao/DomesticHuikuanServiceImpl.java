package com.jindan.jdy.service.neimao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.DomesticHuikuanDto;
import com.jindan.jdy.mapper.DomesticHuikuanMapper;
import com.jindan.jdy.common.pojo.DomesticHuikuan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**   
 * @Description:TODO(内贸提成服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class DomesticHuikuanServiceImpl  extends ServiceImpl<DomesticHuikuanMapper,DomesticHuikuan> implements DomesticHuikuanService  {

    @Autowired
    DomesticHuikuanMapper domesticHuikuandao;

    @Override
    public Page<DomesticHuikuan> seletepage(DomesticHuikuanDto domesticFahuoDto) {
        if(domesticFahuoDto.getCurrentPage() <= 1 ){
            domesticFahuoDto.setCurrentPage(1);
        }
        IPage<DomesticHuikuan> page = new Page<>(domesticFahuoDto.getCurrentPage(),domesticFahuoDto.getPageSize());
        QueryWrapper<DomesticHuikuan> queryWrapper =new QueryWrapper<>();
        if( domesticFahuoDto.getId() !=null &&  !domesticFahuoDto.getId().isEmpty() ){
            queryWrapper.eq("id",domesticFahuoDto.getId());
        }
        if( domesticFahuoDto.getHuikuanriqi() !=null && !domesticFahuoDto.getHuikuanriqi().isEmpty()  ){
            queryWrapper.eq("huikuanriqi",domesticFahuoDto.getHuikuanriqi());
        }
        if( domesticFahuoDto.getXingming() !=null && !domesticFahuoDto.getXingming().isEmpty() ){
            queryWrapper.eq("xingming",domesticFahuoDto.getXingming());
        }
        if(  domesticFahuoDto.getKehumingcheng() !=null && !domesticFahuoDto.getKehumingcheng().isEmpty()){
            queryWrapper.eq("kehumingcheng",domesticFahuoDto.getKehumingcheng());
        }
        if(  domesticFahuoDto.getDaorutime() !=null && !domesticFahuoDto.getDaorutime().isEmpty()){
            queryWrapper.eq("daoruriqi",domesticFahuoDto.getDaorutime());
        }
        queryWrapper.notIn("jine","0.0","0").orderByAsc("xingming","kehumingcheng","huikuanriqi");
        return (Page)domesticHuikuandao.selectPage(page,queryWrapper);
    }

    @Override
    public List<DomesticHuikuan> listHuikuan(String ids) {
        QueryWrapper<DomesticHuikuan> queryWrapperq =new QueryWrapper<>();
        queryWrapperq.like("biaoshi",ids);
        return domesticHuikuandao.selectList(queryWrapperq);
    }

    @Override
    public List<DomesticHuikuan> seleteInHui(ArrayList<String> list) {
        return domesticHuikuandao.seleteInHui(list);
    }

    @Override
    public List<DomesticHuikuan> seleteDetailsInHui(ArrayList<String> list) {
        return domesticHuikuandao.seleteDetailsInHui(list);
    }

    @Override
    public List<DomesticHuikuan> seleteHuikuan(DomesticHuikuan domesticFahuoDto) {
        QueryWrapper<DomesticHuikuan> queryWrapper =new QueryWrapper<>();
        if( domesticFahuoDto.getId() !=null   ){
            queryWrapper.eq("id",domesticFahuoDto.getId());
        }
        if( domesticFahuoDto.getHuikuanriqi() !=null && !domesticFahuoDto.getHuikuanriqi().isEmpty()  ){
            queryWrapper.eq("huikuanriqi",domesticFahuoDto.getHuikuanriqi());
        }
        if( domesticFahuoDto.getXingming() !=null && !domesticFahuoDto.getXingming().isEmpty() ){
            queryWrapper.eq("xingming",domesticFahuoDto.getXingming());
        }
        if(  domesticFahuoDto.getKehumingcheng() !=null && !domesticFahuoDto.getKehumingcheng().isEmpty()){
            queryWrapper.eq("kehumingcheng",domesticFahuoDto.getKehumingcheng());
        }
        if(  domesticFahuoDto.getDaorutime() !=null && !domesticFahuoDto.getDaorutime().isEmpty()){
            queryWrapper.eq("daoruriqi",domesticFahuoDto.getDaorutime());
        }
        if(  domesticFahuoDto.getBiaoshi() !=null && !domesticFahuoDto.getBiaoshi().isEmpty()){
            queryWrapper.like("biaoshi",domesticFahuoDto.getBiaoshi());
        }
        return domesticHuikuandao.selectList(queryWrapper);
    }

    @Override
    public List<DomesticHuikuan> seleteInBiaoshi(List<String> stringList) {
        return domesticHuikuandao.seleteInBiaoshi(stringList);
    }

    @Override
    public List<DomesticHuikuan> seleteExclelist(DomesticHuikuan domesticFahuoDto) {
        QueryWrapper<DomesticHuikuan> queryWrapper =new QueryWrapper<>();
        if( domesticFahuoDto.getId() !=null   ){
            queryWrapper.eq("id",domesticFahuoDto.getId());
        }
        if( domesticFahuoDto.getHuikuanriqi() !=null && !domesticFahuoDto.getHuikuanriqi().isEmpty()  ){
            queryWrapper.ge("huikuanriqi",domesticFahuoDto.getHuikuanriqi());
        }
        if( domesticFahuoDto.getXingming() !=null && !domesticFahuoDto.getXingming().isEmpty() ){
            queryWrapper.eq("xingming",domesticFahuoDto.getXingming());
        }
        if(  domesticFahuoDto.getKehumingcheng() !=null && !domesticFahuoDto.getKehumingcheng().isEmpty()){
            queryWrapper.eq("kehumingcheng",domesticFahuoDto.getKehumingcheng());
        }
        if(domesticFahuoDto.getDaorutime() !=null && !domesticFahuoDto.getDaorutime().isEmpty()){
            queryWrapper.eq("daoruriqi",domesticFahuoDto.getDaorutime());
        }
        if(domesticFahuoDto.getBiaoshi() !=null && !domesticFahuoDto.getBiaoshi().isEmpty()){
            queryWrapper.like("biaoshi",domesticFahuoDto.getBiaoshi());
        }
         queryWrapper.gt("jine","0.0");
        return domesticHuikuandao.selectList(queryWrapper);
    }

    @Override
    public List<DomesticHuikuan> seleteYijisuanexcle() {
        return domesticHuikuandao.seleteYijisuanexcle();
    }

    @Override
    public void saveAllHuiBatch(List<DomesticHuikuan> list) {
        domesticHuikuandao.saveAllHuiBatch(list);
    }

    @Override
    public List<DomesticHuikuan> seleteYuebiaoHuikuan() {
        return domesticHuikuandao.seleteYuebiaoHuikuan();
    }


}