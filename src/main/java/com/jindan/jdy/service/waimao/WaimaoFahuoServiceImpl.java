package com.jindan.jdy.service.waimao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.WaimaoFahuoDto;
import com.jindan.jdy.common.dto.WaimaoFahuoHuikuan;
import com.jindan.jdy.common.mapper.WaimaoHuikuanMapper;
import com.jindan.jdy.common.pojo.WaimaoFahuo;
import com.jindan.jdy.common.mapper.WaimaoFahuoMapper;
import com.jindan.jdy.common.pojo.WaimaoHuikuan;
import com.jindan.jdy.common.pojo.WaimaoTargetAccomplish;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**   
 * @Description:TODO(规则服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class WaimaoFahuoServiceImpl  extends ServiceImpl<WaimaoFahuoMapper,WaimaoFahuo> implements WaimaoFahuoService  {

    @Autowired
    WaimaoFahuoMapper waimaoFahuoDao;
    @Autowired
    WaimaoTargetAccomplishService waimaoTargetAccomplishService;

    @Autowired
    WaimaoHuikuanMapper waimaoHuikuanMapper;


    @Override
    public Page<WaimaoFahuo> seletelist(WaimaoFahuoDto jdyRole) {

        Page<WaimaoFahuo> iPage =new Page<>(jdyRole.getCurrentPage(),jdyRole.getPageSize());
        QueryWrapper<WaimaoFahuo> queryWrapper = new QueryWrapper<>();
        if( jdyRole.getId() !=null &&  jdyRole.getId() > 0 ){
            queryWrapper.eq("id",jdyRole.getId());
        }
        if( jdyRole.getDanjuhao() !=null && !jdyRole.getDanjuhao().isEmpty() ){
            queryWrapper.like("danjuhao",jdyRole.getDanjuhao());
        }
        if( jdyRole.getFahuotime() !=null && !jdyRole.getFahuotime().isEmpty() ){
            queryWrapper.like("fahuotime",jdyRole.getFahuotime());
        }
        if( jdyRole.getFapiaohao() !=null && !jdyRole.getFapiaohao().isEmpty() ){
            queryWrapper.like("fapiaohao",jdyRole.getFapiaohao());
        }
        if( jdyRole.getYewuyuan() !=null && !jdyRole.getYewuyuan().isEmpty() ){
            queryWrapper.like("yewuyuan",jdyRole.getYewuyuan());
        }
        if( jdyRole.getShouhuokehu() !=null && !jdyRole.getShouhuokehu().isEmpty() ){
            queryWrapper.like("shouhuokehu",jdyRole.getShouhuokehu());
        }
        return (Page<WaimaoFahuo>) waimaoFahuoDao.selectPage(iPage,queryWrapper);
    }

    @Override
    public List<WaimaoFahuoHuikuan> seleteAlllist(WaimaoFahuo param) {
        return waimaoFahuoDao.seleteAlllist(param);
    }



    @Override
    public List<WaimaoFahuo> seleteAlllistgroupby(WaimaoFahuoDto param) {
        return waimaoFahuoDao.seleteAlllistgroupby(param);
    }


    @Override
    public List<WaimaoFahuoDto> seleteMarketgroupby(WaimaoFahuoDto jdyRole) {
        return waimaoFahuoDao.seleteMarketgroupby(jdyRole);
    }

    @Override
    public List<WaimaoFahuoDto> seleteTranking(WaimaoFahuoDto jdyRole) {
        return waimaoFahuoDao.seleteTranking(jdyRole);
    }


//    业务员销售完成情况表
    @Override
    public List<WaimaoFahuoDto> seleteCompletion(WaimaoFahuoDto jdyRole) {
        return waimaoFahuoDao.seleteCompletion(jdyRole);
    }

//  更具年份进行查询数据信息  seleteYearCompletion
    @Override
    public List<WaimaoFahuoDto> seleteYearCompletion(WaimaoFahuoDto jdyRole) {
        return waimaoFahuoDao.seleteYearCompletion(jdyRole);
    }

    @Override
    public List<WaimaoFahuo> seleteYuefenTongji(WaimaoFahuo jdyRole) {
        return waimaoFahuoDao.seleteYuefenTongji(jdyRole);
    }

//    按照目的国进行统计分析表
    @Override
    public List<WaimaoFahuo> seleteAnzhaoQuyuYuefenTongji(WaimaoFahuoDto jdyRole) {
        return waimaoFahuoDao.seleteAnzhaoQuyuYuefenTongji(jdyRole);
    }

    @Override
    public List<WaimaoFahuo> seleteYuefenDayuAlllist(WaimaoFahuo param) {
        return waimaoFahuoDao.seleteYuefenDayuAlllist(param);
    }

    @Override
    public List<WaimaoFahuo> seleteDangyuefenDayuAlllist(WaimaoFahuo param) {
        return waimaoFahuoDao.seleteDangyuefenDayuAlllist(param);
    }

    @Override
    public List<WaimaoFahuoDto> seleteDangyueCompletion(WaimaoFahuo  jdyRole) throws ParseException{
//        本月销售金额
        Map<String,WaimaoFahuoDto> map  =new HashMap<>();
        Float huilv = 0f;
        List<WaimaoFahuoDto>  list =new ArrayList<>();
        List<WaimaoFahuoDto>  lists =  waimaoFahuoDao.seleteMubiao(jdyRole);
        for (int i = 0; i < lists.size(); i++) {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(lists.get(i).getFahuotime() );
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            huilv = init(String.valueOf(ca.get(Calendar.MONTH)));
            if(lists.get(i).getBizhong().equals("人民币")){
                lists.get(i).setFobzongjia(String.valueOf(Float.valueOf(list.get(i).getFobzongjia())/huilv));
            }
            if(map.containsKey( lists.get(i).getYewuyuan())){
                map.get(lists.get(i).getYewuyuan()).setShuliang(Float.valueOf(map.get(lists.get(i).getYewuyuan()).getShuliang())+Float.valueOf(lists.get(i).getShuliang()));
                map.get(lists.get(i).getYewuyuan()).setFobzongjia(String.valueOf(Float.valueOf(map.get(lists.get(i).getYewuyuan()).getFobzongjia())+Float.valueOf(lists.get(i).getFobzongjia())));
            }else{
                map.put(lists.get(i).getYewuyuan(),lists.get(i));
            }
            for(WaimaoFahuoDto key : map.values()){
                list.add(key);
            }
        }
        return list;
    }


    @Override
    public List<WaimaoFahuoDto> seleteShangyueCompletion(WaimaoFahuo  jdyRole) {
        return waimaoFahuoDao.seleteShangyueCompletion(jdyRole);
    }

    @Override
    public List<WaimaoFahuoDto> seleteLeijiCompletion(WaimaoFahuo  jdyRole) {
        return waimaoFahuoDao.seleteLeijiCompletion(jdyRole);
    }

    @Override
    public List<WaimaoFahuo> seletePaimingTranking(WaimaoFahuo jdyRole) {
        return waimaoFahuoDao.seletePaimingTranking(jdyRole);
    }

    @Override
    public List<WaimaoFahuo> seleteFahuoDanjuhao(WaimaoFahuo param) {
        return waimaoFahuoDao.seleteFahuoDanjuhao(param);
    }

    @Override
    public  Map<String, WaimaoFahuo> selelteLeijixiaoshou(WaimaoFahuo jdyRole) throws ParseException {
        Float huilv ;
        Map<String, WaimaoFahuo> map = new HashMap<>();
//        List<WaimaoFahuo>  list = waimaoFahuoDao.selelteLeijixiaoshou(jdyRole);
        List<WaimaoFahuo>  lists = waimaoFahuoDao.seleteYuefenDayuAlllist(jdyRole);
        for (int i = 0; i < lists.size(); i++) {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(lists.get(i).getFahuotime() );
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            huilv = init(String.valueOf(ca.get(Calendar.MONTH)));
            if(lists.get(i).getBizhong().equals("人民币")){
                lists.get(i).setFobzongjia(String.valueOf(Float.valueOf(lists.get(i).getFobzongjia())/huilv));
            }
            if(map.containsKey( lists.get(i).getYewuyuan())){
                map.get(lists.get(i).getYewuyuan()).setShuliang(String.valueOf(Float.valueOf(map.get(lists.get(i).getYewuyuan()).getShuliang())+Float.valueOf(lists.get(i).getShuliang())));
                map.get(lists.get(i).getYewuyuan()).setJiashuiheji(String.valueOf(Float.valueOf(map.get(lists.get(i).getYewuyuan()).getJiashuiheji())+Float.valueOf(lists.get(i).getJiashuiheji())));
            }else{
                map.put(lists.get(i).getYewuyuan(),lists.get(i));
            }
        }
        return map ;
    }

    @Override
    public Map<String, Float> selelteLeiqiankuan(WaimaoFahuo jdyRole) throws ParseException {
        Float huilv;
        Map<String,Float> map =new HashMap<>(); 
        List<WaimaoFahuo> waimaoFahuoHuikuans = waimaoFahuoDao.seleteYuefenTongji(jdyRole);
        List<WaimaoHuikuan> list = waimaoHuikuanMapper.seleteHuikuanDangyueAllhao(jdyRole);
        for (int i = 0; i < waimaoFahuoHuikuans.size(); i++) {
            for (int j = 0; j <list.size() ; j++) {
                    if(waimaoFahuoHuikuans.get(i).getFapiaohao().equals(list.get(j).getFapiaohao())){
                        if(waimaoFahuoHuikuans.get(i).getBizhong().equals("人民币")){
//                          提出里面的月份差汇率
                            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(waimaoFahuoHuikuans.get(i).getFahuotime() );
                            Calendar ca = Calendar.getInstance();
                            ca.setTime(date);
                            huilv = init(String.valueOf(ca.get(Calendar.MONTH)));
                            waimaoFahuoHuikuans.get(i).setJiashuiheji(String.valueOf((Float.valueOf(waimaoFahuoHuikuans.get(i).getJiashuiheji())-Float.valueOf(list.get(j).getHuikuanjine()))/huilv));
                        }else{
                            waimaoFahuoHuikuans.get(i).setJiashuiheji(String.valueOf((Float.valueOf(waimaoFahuoHuikuans.get(i).getJiashuiheji())-Float.valueOf(list.get(j).getHuikuanjine()))));
                        }
                        if(map.containsKey(waimaoFahuoHuikuans.get(i).getYewuyuan())){
                            map.put(waimaoFahuoHuikuans.get(i).getYewuyuan(),map.get(waimaoFahuoHuikuans.get(i).getYewuyuan())+Float.valueOf(waimaoFahuoHuikuans.get(i).getFobzongjia()));
                        }else{
                            map.put(waimaoFahuoHuikuans.get(i).getYewuyuan(),Float.valueOf(waimaoFahuoHuikuans.get(i).getJiashuiheji()));
                        }
                    }
            }
        }
        return map;
    }


    public Float init(String jdyRole){
        List<WaimaoTargetAccomplish> seletelist = waimaoTargetAccomplishService.seletehuilvlist();
        if(seletelist.size() > 0){
            switch (jdyRole){
                case "01":
                    return  Float.valueOf(seletelist.get(0).getYiyue());
                case "02":
                    return Float.valueOf(seletelist.get(0).getEryue());
                case "03":
                    return Float.valueOf(seletelist.get(0).getSanyue());
                case "04":
                    return Float.valueOf(seletelist.get(0).getSiyue());
                case "05":
                    return Float.valueOf(seletelist.get(0).getWuyue());
                case "06":
                    return Float.valueOf(seletelist.get(0).getLiuyue());
                case "07":
                    return Float.valueOf(seletelist.get(0).getQiyue());
                case "08":
                    return Float.valueOf(seletelist.get(0).getBayue());
                case "09":
                    return Float.valueOf(seletelist.get(0).getJiuyue());
                case "10":
                    return Float.valueOf(seletelist.get(0).getShiyue());
                case "11":
                    return Float.valueOf(seletelist.get(0).getShiyiyue());
                case "12":
                    return Float.valueOf(seletelist.get(0).getShieryue());
                default:
                    return 1.0f;
            }
        }else{
            return 1.0f;
        }

    }

}