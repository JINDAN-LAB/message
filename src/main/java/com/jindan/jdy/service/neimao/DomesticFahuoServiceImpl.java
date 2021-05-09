package com.jindan.jdy.service.neimao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.DomesticFahuoDto;
import com.jindan.jdy.common.mapper.DomesticHuikuanMapper;
import com.jindan.jdy.common.pojo.DomesticFahuo;
import com.jindan.jdy.common.mapper.DomesticFahuoMapper;
import com.jindan.jdy.common.pojo.DomesticHuikuan;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**   
 * @Description:TODO(内贸提成服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Component
public class DomesticFahuoServiceImpl  extends ServiceImpl<DomesticFahuoMapper,DomesticFahuo> implements DomesticFahuoService  {

    @Autowired
    DomesticFahuoMapper domesticFahuoDao;

    @Autowired
    DomesticHuikuanMapper domesticHuikuanDao;

//    发货信息列表
    @Override
    public Page<DomesticFahuo> seletepage(DomesticFahuoDto domesticFahuoDto) {
        System.out.println("----");
        if(domesticFahuoDto.getCurrentPage() < 0  ){
            domesticFahuoDto.setCurrentPage(0);
        }
        System.out.println("--111--");
        IPage<DomesticFahuo> page = new Page<>(domesticFahuoDto.getCurrentPage(),100);
        QueryWrapper<DomesticFahuo> queryWrapper =new QueryWrapper<>();
        if( !(domesticFahuoDto.getId() == null) &&  domesticFahuoDto.getId() > 0   ){
            queryWrapper.eq("id",domesticFahuoDto.getId());
        }
        if( !(domesticFahuoDto.getDanjuhao() == null) &&  !domesticFahuoDto.getDanjuhao().isEmpty()  ){
            queryWrapper.eq("danjuhao",domesticFahuoDto.getDanjuhao());
        }
        if(!(domesticFahuoDto.getYewuyuan() == null) &&  !domesticFahuoDto.getYewuyuan().isEmpty() ){
            queryWrapper.eq("yewuyuan",domesticFahuoDto.getYewuyuan());
        }
        if( !(domesticFahuoDto.getDanjuriqi() == null) &&  !domesticFahuoDto.getDanjuriqi().isEmpty()){
            queryWrapper.eq("danjuriqi",domesticFahuoDto.getDanjuriqi());
        }
        if(!(domesticFahuoDto.getShouhuokehu() == null) &&  !domesticFahuoDto.getShouhuokehu().isEmpty()){
            queryWrapper.eq("shouhuokehu",domesticFahuoDto.getShouhuokehu());
        }
        if(!(domesticFahuoDto.getDayintime() == null) &&  !domesticFahuoDto.getDayintime().isEmpty()){
            queryWrapper.eq("dayintime",domesticFahuoDto.getDayintime());
        }
        queryWrapper.eq("queren","未计算").orderByAsc("yewuyuan","shouhuokehu","danjuriqi");

        return (Page)domesticFahuoDao.selectPage(page,queryWrapper);
    }

//    已计算列表
    @Override
    public Page<DomesticFahuo> seleteyijisuanpage(DomesticFahuoDto domesticFahuoDto) {
        System.out.println("22222222222222222222222222222");
        System.out.println(domesticFahuoDto.toString());
        if(domesticFahuoDto.getCurrentPage() < 0 || domesticFahuoDto.getCurrentPage() < 1){
            domesticFahuoDto.setCurrentPage(0);
        }
        IPage<DomesticFahuo> page = new Page<>(domesticFahuoDto.getCurrentPage(),domesticFahuoDto.getPageSize());
        QueryWrapper<DomesticFahuo> queryWrapper =new QueryWrapper<>();
        if( domesticFahuoDto.getId() !=null &&  domesticFahuoDto.getId() >0  ){
            queryWrapper.eq("id",domesticFahuoDto.getId());
        }
        if( domesticFahuoDto.getDanjuhao() !=null && !domesticFahuoDto.getDanjuhao().isEmpty()  ){
            queryWrapper.eq("danjuhao",domesticFahuoDto.getDanjuhao());
        }
        if( domesticFahuoDto.getYewuyuan() !=null && !domesticFahuoDto.getYewuyuan().isEmpty() ){
            queryWrapper.eq("yewuyuan",domesticFahuoDto.getYewuyuan());
        }
        if(  domesticFahuoDto.getDanjuriqi() !=null && !domesticFahuoDto.getDanjuriqi().isEmpty() ){
            queryWrapper.eq("danjuriqi",domesticFahuoDto.getDanjuriqi());
        }
        if(  domesticFahuoDto.getShouhuokehu() !=null && !domesticFahuoDto.getShouhuokehu().isEmpty() ){
            queryWrapper.eq("shouhuokehu",domesticFahuoDto.getShouhuokehu());
        }
        if(  domesticFahuoDto.getDayintime() !=null && !domesticFahuoDto.getDayintime().isEmpty()  ){
            queryWrapper.eq("dayintime",domesticFahuoDto.getDayintime());
        }
        if(  domesticFahuoDto.getJisuanriqi() !=null && !domesticFahuoDto.getJisuanriqi().isEmpty()  ){
            queryWrapper.eq("jisuanriqi",domesticFahuoDto.getJisuanriqi());
        }
        queryWrapper.eq("queren","已计算").orderByAsc("yewuyuan","shouhuokehu","danjuriqi");
        return (Page)domesticFahuoDao.selectPage(page,queryWrapper);
    }

    @Override
    public  Page<DomesticFahuo> selebudayinpage(DomesticFahuoDto domesticFahuoDto) {
        System.out.println("22222222222222222222222222222");
        System.out.println(domesticFahuoDto.toString());
        if(domesticFahuoDto.getCurrentPage() < 0 || domesticFahuoDto.getCurrentPage() < 1){
            domesticFahuoDto.setCurrentPage(1);
        }
        IPage<DomesticFahuo> page = new Page<>(domesticFahuoDto.getCurrentPage(),domesticFahuoDto.getPageSize());
        QueryWrapper<DomesticFahuo> queryWrapper =new QueryWrapper<>();
        if( domesticFahuoDto.getId() !=null &&  domesticFahuoDto.getId()> 0){
            queryWrapper.eq("id",domesticFahuoDto.getId());
        }
        if( domesticFahuoDto.getDanjuhao() !=null && !domesticFahuoDto.getDanjuhao().isEmpty()){
            queryWrapper.eq("danjuhao",domesticFahuoDto.getDanjuhao());
        }
        if( domesticFahuoDto.getYewuyuan() !=null && domesticFahuoDto.getYewuyuan() != null ){
            queryWrapper.eq("yewuyuan",domesticFahuoDto.getYewuyuan());
        }
        if(domesticFahuoDto.getDanjuriqi() !=null && domesticFahuoDto.getDanjuriqi() != null ){
            queryWrapper.eq("danjuriqi",domesticFahuoDto.getDanjuriqi());
        }
        if(domesticFahuoDto.getShouhuokehu() !=null && domesticFahuoDto.getShouhuokehu() != null ){
            queryWrapper.eq("shouhuokehu",domesticFahuoDto.getShouhuokehu());
        }
        if(domesticFahuoDto.getDayintime() !=null && domesticFahuoDto.getDayintime() != null ){
            queryWrapper.eq("dayintime",domesticFahuoDto.getDayintime());
        }
        queryWrapper.eq("queren","不打印").orderByAsc("yewuyuan","shouhuokehu","danjuriqi");
        return (Page)domesticFahuoDao.selectPage(page,queryWrapper);
    }

    @Override
    public Page<DomesticFahuo> seleteyidayinpage(DomesticFahuoDto domesticFahuoDto) {
        System.out.println("22222222222222222222222222222");
        System.out.println(domesticFahuoDto.toString());
        if(domesticFahuoDto.getCurrentPage() < 0 || domesticFahuoDto.getCurrentPage() < 1){
            domesticFahuoDto.setCurrentPage(1);
        }
        IPage<DomesticFahuo> page = new Page<>(domesticFahuoDto.getCurrentPage(),domesticFahuoDto.getPageSize());
        QueryWrapper<DomesticFahuo> queryWrapper =new QueryWrapper<>();
        if(domesticFahuoDto.getId() !=null &&  domesticFahuoDto.getId() > 0){
            queryWrapper.eq("id",domesticFahuoDto.getId());
        }
        if(domesticFahuoDto.getDanjuhao() !=null && !domesticFahuoDto.getDanjuhao().isEmpty()  ){
            queryWrapper.eq("danjuhao",domesticFahuoDto.getDanjuhao());
        }
        if(domesticFahuoDto.getYewuyuan() !=null && domesticFahuoDto.getYewuyuan() != null ){
            queryWrapper.eq("yewuyuan",domesticFahuoDto.getYewuyuan());
        }
        if(domesticFahuoDto.getDanjuriqi() !=null && domesticFahuoDto.getDanjuriqi() != null ){
            queryWrapper.eq("danjuriqi",domesticFahuoDto.getDanjuriqi());
        }
        if(domesticFahuoDto.getShouhuokehu() !=null && domesticFahuoDto.getShouhuokehu() != null ){
            queryWrapper.eq("shouhuokehu",domesticFahuoDto.getShouhuokehu());
        }
        if(domesticFahuoDto.getDayintime() !=null && domesticFahuoDto.getDayintime() != null ){
            queryWrapper.eq("dayintime",domesticFahuoDto.getDayintime());
        }
        queryWrapper.eq("queren","已打印").orderByAsc("yewuyuan","shouhuokehu","danjuriqi");

       return  (Page)domesticFahuoDao.selectPage(page,queryWrapper);
    }

//    按照客户发货汇总信息
    @Override
    public List<DomesticFahuo> seleGroupBy(DomesticFahuo domesticFahuo) {
        QueryWrapper<DomesticFahuo> queryWrapper =new QueryWrapper<>();
        queryWrapper.orderByDesc("shouhuokehu");
        return domesticFahuoDao.selectList(queryWrapper);
    }

//    按照客户回款汇总信息
    @Override
    public List<DomesticHuikuan> seleGrouphuikuanBy(DomesticHuikuan huikuan) {
        QueryWrapper<DomesticHuikuan> queryWrapper =new QueryWrapper<>();
        queryWrapper.orderByDesc("kehumingcheng");
        return domesticHuikuanDao.selectList(queryWrapper);
    }

    @Override
    public List<DomesticFahuoDto> seleteInFaHui(List<String> list) {
        return domesticFahuoDao.seleteInFaHui(list);
    }


    @Override
    public List<DomesticFahuo> seleteFhuolist(DomesticFahuo domesticFahuoDto) {
        QueryWrapper<DomesticFahuo> queryWrapper =new QueryWrapper<>();
        if(domesticFahuoDto.getId() !=null){
            queryWrapper.eq("id",domesticFahuoDto.getId());
        }
        if(domesticFahuoDto.getDanjuhao() !=null && !domesticFahuoDto.getDanjuhao().isEmpty()  ){
            queryWrapper.eq("danjuhao",domesticFahuoDto.getDanjuhao());
        }
        if(domesticFahuoDto.getYewuyuan() !=null && domesticFahuoDto.getYewuyuan() != null ){
            queryWrapper.eq("yewuyuan",domesticFahuoDto.getYewuyuan());
        }
        if(domesticFahuoDto.getDanjuriqi() !=null && domesticFahuoDto.getDanjuriqi() != null ){
            queryWrapper.eq("danjuriqi",domesticFahuoDto.getDanjuriqi());
        }
        if(domesticFahuoDto.getShouhuokehu() !=null && domesticFahuoDto.getShouhuokehu() != null ){
            queryWrapper.eq("shouhuokehu",domesticFahuoDto.getShouhuokehu());
        }
        if(domesticFahuoDto.getDayintime() !=null && domesticFahuoDto.getDayintime() != null ){
            queryWrapper.eq("dayintime",domesticFahuoDto.getDayintime());
        }
        return domesticFahuoDao.selectList(queryWrapper);
    }

    @Override
    public List<DomesticFahuo> seleteExclelist(DomesticFahuoDto domesticFahuoDto) {
        QueryWrapper<DomesticFahuo> queryWrapper =new QueryWrapper<>();
        if( domesticFahuoDto.getId() !=null && domesticFahuoDto.getId() > 0){
            queryWrapper.eq("id",domesticFahuoDto.getId());
        }
        if( domesticFahuoDto.getDanjuhao() !=null && !domesticFahuoDto.getDanjuhao().isEmpty()  ){
            queryWrapper.eq("danjuhao",domesticFahuoDto.getDanjuhao());
        }
        if( domesticFahuoDto.getYewuyuan() !=null && domesticFahuoDto.getYewuyuan() != null ){
            queryWrapper.eq("yewuyuan",domesticFahuoDto.getYewuyuan());
        }
        if(  domesticFahuoDto.getDanjuriqi() !=null && domesticFahuoDto.getDanjuriqi() != null ){
            queryWrapper.ge("danjuriqi",domesticFahuoDto.getDanjuriqi());
        }
        if(  domesticFahuoDto.getShouhuokehu() !=null && domesticFahuoDto.getShouhuokehu() != null ){
            queryWrapper.eq("shouhuokehu",domesticFahuoDto.getShouhuokehu());
        }
        if(  domesticFahuoDto.getDayintime() !=null && domesticFahuoDto.getDayintime() != null ){
            queryWrapper.eq("dayintime",domesticFahuoDto.getDayintime());
        }
        if(  domesticFahuoDto.getQueren() !=null && domesticFahuoDto.getQueren() != "" ){
            queryWrapper.eq("queren",domesticFahuoDto.getQueren());
        }
        queryWrapper.eq("delete_id","0");
        return  domesticFahuoDao.selectList(queryWrapper);
    }

    @Override
    public List<DomesticFahuoDto> seleteYijisuanexcle(DomesticFahuoDto param) {
        System.out.println("=========================");
        return domesticFahuoDao.seleteYijisuanexcle(param);
    }

    @Override
    public void saveAllBatch(List<DomesticFahuo> list) {
        domesticFahuoDao.saveAllBatch(list);
    }

    @Override
    public List<DomesticFahuo> seleteYuebiaoFa(DomesticFahuo fahuo) {
        return domesticFahuoDao.seleteYuebiaoFa(fahuo);
    }

    @Override
    public  List<DomesticFahuoDto> getOneById(Integer id) {
        DomesticFahuo domesticFahuo =new DomesticFahuo();
        domesticFahuo.setId(id);
        return domesticFahuoDao.getOneById(domesticFahuo);
    }

    @Override
    public List<DomesticFahuoDto> seleteInFaDetails(Set<String> filterList) {
        return domesticFahuoDao.seleteInFaDetails(filterList);
    }


}