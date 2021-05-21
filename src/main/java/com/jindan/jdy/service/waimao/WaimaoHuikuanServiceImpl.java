package com.jindan.jdy.service.waimao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.WaimaoHuikuanDto;
import com.jindan.jdy.mapper.WaimaoFahuoMapper;
import com.jindan.jdy.mapper.WaimaoHuikuanMapper;
import com.jindan.jdy.common.pojo.WaimaoFahuo;
import com.jindan.jdy.common.pojo.WaimaoHuikuan;
import com.jindan.jdy.common.pojo.WaimaoTargetAccomplish;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**   
 * @Description:TODO(规则服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Slf4j
@Component
public class WaimaoHuikuanServiceImpl  extends ServiceImpl<WaimaoHuikuanMapper,WaimaoHuikuan> implements WaimaoHuikuanService  {
    @Autowired
    WaimaoTargetAccomplishService waimaoTargetAccomplishService;

    @Autowired
    WaimaoHuikuanMapper waimaoFahuoDao;

    @Autowired
    WaimaoFahuoMapper  waimaoFahuoMapper;

    @Override
    public Page<WaimaoHuikuan> seletelist(WaimaoHuikuanDto waimaoHuikuan) {
        if(waimaoHuikuan.getCurrentPage() <= 0  ){
            waimaoHuikuan.setCurrentPage(1);
        }
        Page<WaimaoHuikuan> iPage =new Page<>(waimaoHuikuan.getCurrentPage(),waimaoHuikuan.getPageSize());
        QueryWrapper<WaimaoHuikuan> queryWrapper = new QueryWrapper<>();
        if( waimaoHuikuan.getId() !=null &&  waimaoHuikuan.getId() > 0){
            queryWrapper.eq("id",waimaoHuikuan.getId());
        }
        if( waimaoHuikuan.getFapiaohao() !=null && !waimaoHuikuan.getFapiaohao().isEmpty() ){
            queryWrapper.like("fapiaohao",waimaoHuikuan.getFapiaohao());
        }
        if( waimaoHuikuan.getHuikuanriqi() !=null && !waimaoHuikuan.getHuikuanriqi().isEmpty() ){
            queryWrapper.like("huikuanriqi",waimaoHuikuan.getHuikuanriqi());
        }
        if( waimaoHuikuan.getHuikuanjine() !=null && !waimaoHuikuan.getHuikuanjine().isEmpty() ){
            queryWrapper.like("huikuanjine",waimaoHuikuan.getHuikuanjine());
        }
        if( waimaoHuikuan.getJiehuiyinhang() !=null && !waimaoHuikuan.getJiehuiyinhang().isEmpty() ){
            queryWrapper.like("jiehuiyinhang",waimaoHuikuan.getJiehuiyinhang());
        }
        if( waimaoHuikuan.getJine() !=null && !waimaoHuikuan.getJine().isEmpty() ){
            queryWrapper.like("jine",waimaoHuikuan.getJine());
        }
        return (Page<WaimaoHuikuan>) waimaoFahuoDao.selectPage(iPage,queryWrapper);
    }

    @Override
    public List<WaimaoHuikuan> seleteHuikuan(WaimaoFahuo jdyRole) {
        return waimaoFahuoDao.seleteHuikuan(jdyRole);
    }

    @Override
    public List<WaimaoHuikuan> seleteHuikuanDanjuhao(WaimaoFahuo param) {
        return waimaoFahuoDao.seleteHuikuanDanjuhao(param);
    }

    @Override
    public List<WaimaoHuikuan> seleteHuikuanDangyueAllhao(WaimaoFahuo param) {
        return waimaoFahuoDao.seleteHuikuanDangyueAllhao(param);
    }

    @Override
    public Map<String,WaimaoHuikuan>  seleteHuikuanDangyuehuikuan(WaimaoFahuo jdyRole) {
        Map<String,WaimaoHuikuan>  map =new HashMap<>();
        List<WaimaoHuikuan>  list = waimaoFahuoDao.seleteHuikuanDangyuehuikuan(jdyRole);
        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i).getXiaoshouren(),list.get(i));
        }
        return map;
    }

    @Override
    public Map<String,WaimaoHuikuan> seleteHuikuanyewuyuanDanjuhao(WaimaoFahuo jdyRole) throws ParseException {
//            本月回款金额
        Map<String,WaimaoHuikuan>  map =new HashMap<>();
        Float huilv = 0f;
        List<WaimaoHuikuan>  lists = waimaoFahuoDao.seBenyuehuikuanjine(jdyRole);

        for (int i = 0; i < lists.size(); i++) {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(lists.get(i).getHuikuanriqi() );
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            huilv = init(String.valueOf(ca.get(Calendar.MONTH)));
            if(lists.get(i).getBizhong().equals("人民币")){
                lists.get(i).setHuikuanjine(String.valueOf(Float.valueOf(lists.get(i).getHuikuanjine())/huilv));
            }
            if(map.containsKey( lists.get(i).getXiaoshouren())){
                map.get(lists.get(i).getXiaoshouren()).setHuikuanjine(String.valueOf(Float.valueOf(map.get(lists.get(i).getXiaoshouren()).getHuikuanjine())+Float.valueOf(lists.get(i).getHuikuanjine())));
             }else{
                map.put(lists.get(i).getXiaoshouren(),lists.get(i));
            }
        }
        return map ;
    }

    @Override
    public Map<String,Float> seleteQiankuan(WaimaoFahuo jdyRole) throws ParseException {
        log.info("======“WaimaoHuikuanServiceImpl.seleteQiankuan方法”开始执行======");
        Float huilv ;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    //   本月逾期欠款按照业务员进行分析
       Map<String,Float> huikuanMap =new HashMap<>();
       List<WaimaoFahuo>  fahuoList =  waimaoFahuoMapper.seleteWaimaoAlllist(jdyRole);
       List<WaimaoHuikuan> waimaoHuikuans = waimaoFahuoDao.seleteHuikuanDangyuehuikuan(jdyRole);

        for (int i = 0; i < fahuoList.size(); i++) {
            for (int j = 0; j < waimaoHuikuans.size(); j++) {
                if(fahuoList.get(i).getYewuyuan().equals(waimaoHuikuans.get(j).getXiaoshouren())){
                    String regex="()[^\\d\r\n]*?(\\d+)[^\r\n]*?";
                    Pattern p=Pattern.compile(regex);
                    Matcher m=p.matcher(fahuoList.get(i).getHuikuanxieyi());
                    String in1 = "0";
                    String in2 = "0";
                    while(m.find()){
                        in1 = m.group(2);
                        in2  = in1;
                    }
                    Date date =  sdf.parse( fahuoList.get(i).getYujizhuangtime());
                    BigDecimal time = BigDecimal.valueOf(date.getTime()); // 得到指定日期的毫秒数
                    BigDecimal day = BigDecimal.valueOf(Integer.valueOf(in2)).multiply(BigDecimal.valueOf(86400000)) ;
                    log.info("day的值为："+day);
                    time = time.add( day);
                    Date dates = new Date();
                    dates.setTime(time.longValue());
//                    String riqi  = (new SimpleDateFormat("yyyy-MM-dd").format(dates));
                    Calendar ca = Calendar.getInstance();
                    ca.setTime(dates);
                    huilv = init(String.valueOf(Calendar.MONTH));
                    if(ca.get(Calendar.MONTH) <= Float.valueOf(jdyRole.getFahuotime())){
                        huikuanMap.put(fahuoList.get(i).getYewuyuan(), Float.valueOf(fahuoList.get(i).getFobzongjia())-Float.valueOf(waimaoHuikuans.get(j).getHuikuanjine()));
                    }
                }
            }
        }


        return huikuanMap;
    }

    @Override
    public Map<String, Float> seleteQuannianQiankuan(WaimaoFahuo jdyRole) throws ParseException {
        log.info("======“WaimaoHuikuanServiceImpl.seleteQuannianQiankuan方法”开始执行======");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //   本月逾期欠款按照业务员进行分析
        Map<String,Float> huikuanMap =new HashMap<>();
        List<WaimaoFahuo>  fahuoList =  waimaoFahuoMapper.seleteQuannianWaimaoAlllist(jdyRole);
        List<WaimaoHuikuan> waimaoHuikuans = waimaoFahuoDao.seleteQuannianHuikuanDangyuehuikuan(jdyRole);

        for (int i = 0; i < fahuoList.size(); i++) {
            for (int j = 0; j < waimaoHuikuans.size(); j++){
                if(!StringUtils.isEmpty(waimaoHuikuans.get(j).getXiaoshouren()) && fahuoList.get(i).getYewuyuan().equals(waimaoHuikuans.get(j).getXiaoshouren())){
                    String regex="()[^\\d\r\n]*?(\\d+)[^\r\n]*?";
                    Pattern p=Pattern.compile(regex);
                    Matcher m=p.matcher(fahuoList.get(i).getHuikuanxieyi());
                    String in1 = "0";
                    String in2 = "0";
                    while(m.find()){
                        in1 = m.group(2);
                        in2  = in1;
                    }
                    Date date =  sdf.parse( fahuoList.get(i).getYujizhuangtime());
                    BigDecimal time = BigDecimal.valueOf(date.getTime()); // 得到指定日期的毫秒数
                    BigDecimal day = BigDecimal.valueOf(Integer.valueOf(in2)).multiply(BigDecimal.valueOf(86400000)) ;
                    log.info("day的值为："+day);
                    time = time.add( day);
                    Date dates = new Date();
                    dates.setTime(time.longValue());
                    Calendar ca = Calendar.getInstance();
                    ca.setTime(dates);
                    if(ca.get(Calendar.MONTH) <= Float.valueOf(jdyRole.getFahuotime())){
                        huikuanMap.put(fahuoList.get(i).getYewuyuan(), Float.valueOf(fahuoList.get(i).getFobzongjia())-Float.valueOf(waimaoHuikuans.get(j).getHuikuanjine()));
                    }
                }
            }
        }
        return huikuanMap;
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