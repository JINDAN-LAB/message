package com.jindan.jdy.service.waimao;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.WaimaoTichengFahuoDto;
import com.jindan.jdy.common.dto.WaimaoTichengFahuoTargetDto;
import com.jindan.jdy.mapper.WaimaoTichengFahuoMapper;
import com.jindan.jdy.common.pojo.DomesticFahuo;
import com.jindan.jdy.common.pojo.WaimaoTichengFahuo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**   
 * @Description:TODO(外贸提成服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class WaimaoTichengFahuoServiceImpl extends ServiceImpl<WaimaoTichengFahuoMapper,WaimaoTichengFahuo> implements WaimaoTichengFahuoService  {

    @Autowired
    WaimaoTichengFahuoMapper waimaoTichengFahuoDao;

    @Override
    public Page<WaimaoTichengFahuo> seletelistByPage(WaimaoTichengFahuoDto jdyRole) {
        if(jdyRole.getCurrentPage() < 0  ){
            jdyRole.setCurrentPage(0);
        }
        IPage<WaimaoTichengFahuo> page = new Page<>(jdyRole.getCurrentPage(),jdyRole.getPageSize());
        QueryWrapper<WaimaoTichengFahuo> queryWrapper =new QueryWrapper<>();
        if( jdyRole.getId() !=null){
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
        if( jdyRole.getHetonghao() !=null && !jdyRole.getHetonghao().isEmpty() ){
            queryWrapper.like("hetonghao",jdyRole.getHetonghao());
        }
        if( jdyRole.getPici() !=null && !jdyRole.getPici().isEmpty() ){
            queryWrapper.like("pici",jdyRole.getPici());
        }
        if( jdyRole.getYewuyuan() !=null && !jdyRole.getYewuyuan().isEmpty()){
            queryWrapper.like("yewuyuan",jdyRole.getYewuyuan());
        }
        if( jdyRole.getShouhuokehu() !=null && !jdyRole.getShouhuokehu().isEmpty() ){
            queryWrapper.like("shouhuokehu",jdyRole.getShouhuokehu());
        }
        if( jdyRole.getDiqufenlei() !=null && !jdyRole.getDiqufenlei().isEmpty() ){
            queryWrapper.like("diqufenlei",jdyRole.getDiqufenlei());
        }
        if( jdyRole.getYujizhuangtime() !=null && !jdyRole.getYujizhuangtime().isEmpty() ){
            queryWrapper.like("yujizhuangtime",jdyRole.getYujizhuangtime());
        }
        if( jdyRole.getWuliaoming() !=null && !jdyRole.getWuliaoming().isEmpty()){
            queryWrapper.like("wuliaoming",jdyRole.getWuliaoming());
        }
        if( jdyRole.getShuliang() !=null &&  jdyRole.getShuliang() >0 ){
            queryWrapper.like("shuliang",jdyRole.getShuliang());
        }
        if( jdyRole.getHanshuidanjia() !=null && jdyRole.getHanshuidanjia() > 0 ){
            queryWrapper.like("hanshuidanjia",jdyRole.getHanshuidanjia());
        }
        if( jdyRole.getBizhong() !=null && !jdyRole.getBizhong().isEmpty() ){
            queryWrapper.like("bizhong",jdyRole.getBizhong());
        }
        if( jdyRole.getJiagetiaokuan() !=null && !jdyRole.getJiagetiaokuan().isEmpty() ){
            queryWrapper.like("jiagetiaokuan",jdyRole.getJiagetiaokuan());
        }
        if( jdyRole.getJiashuiheji() !=null && !jdyRole.getJiashuiheji().isEmpty()  ){
            queryWrapper.like("jiashuiheji",jdyRole.getJiashuiheji());
        }
        if(StringUtils.isNotEmpty(jdyRole.getYijisuan())){
            queryWrapper.eq("yijisuan",jdyRole.getYijisuan());
        }
        return (Page<WaimaoTichengFahuo>) waimaoTichengFahuoDao.selectPage(page,queryWrapper);
    }

    @Override
    public List<WaimaoTichengFahuo> selectFahuolist(WaimaoTichengFahuoDto param) {
        QueryWrapper<WaimaoTichengFahuo> queryWrapper =new QueryWrapper<>();
        if( param.getId() !=null){
            queryWrapper.eq("id",param.getId());
        }
        if( param.getDanjuhao() !=null && !param.getDanjuhao().isEmpty() ){
            queryWrapper.like("danjuhao",param.getDanjuhao());
        }
        if( param.getFahuotime() !=null && !param.getFahuotime().isEmpty() ){
            queryWrapper.like("fahuotime",param.getFahuotime());
        }
        if( param.getFapiaohao() !=null && !param.getFapiaohao().isEmpty() ){
            queryWrapper.like("fapiaohao",param.getFapiaohao());
        }
        if( param.getHetonghao() !=null && !param.getHetonghao().isEmpty() ){
            queryWrapper.like("hetonghao",param.getHetonghao());
        }
        if( param.getPici() !=null && !param.getPici().isEmpty() ){
            queryWrapper.like("pici",param.getPici());
        }
        if( param.getYewuyuan() !=null && !param.getYewuyuan().isEmpty()){
            queryWrapper.like("yewuyuan",param.getYewuyuan());
        }
        if( param.getShouhuokehu() !=null && !param.getShouhuokehu().isEmpty() ){
            queryWrapper.like("shouhuokehu",param.getShouhuokehu());
        }
        if( param.getDiqufenlei() !=null && !param.getDiqufenlei().isEmpty() ){
            queryWrapper.like("diqufenlei",param.getDiqufenlei());
        }
        if( param.getYujizhuangtime() !=null && !param.getYujizhuangtime().isEmpty() ){
            queryWrapper.like("yujizhuangtime",param.getYujizhuangtime());
        }
        if( param.getWuliaoming() !=null && !param.getWuliaoming().isEmpty()){
            queryWrapper.like("wuliaoming",param.getWuliaoming());
        }
        if( param.getShuliang() !=null &&  param.getShuliang() >0 ){
            queryWrapper.like("shuliang",param.getShuliang());
        }
        if( param.getHanshuidanjia() !=null && param.getHanshuidanjia() > 0 ){
            queryWrapper.like("hanshuidanjia",param.getHanshuidanjia());
        }
        if( param.getBizhong() !=null && !param.getBizhong().isEmpty() ){
            queryWrapper.like("bizhong",param.getBizhong());
        }
        if( param.getJiagetiaokuan() !=null && !param.getJiagetiaokuan().isEmpty() ){
            queryWrapper.like("jiagetiaokuan",param.getJiagetiaokuan());
        }
        if( param.getJiashuiheji() !=null && !param.getJiashuiheji().isEmpty()  ){
            queryWrapper.like("jiashuiheji",param.getJiashuiheji());
        }
//        queryWrapper.eq("yijisuan","未计算");
        return waimaoTichengFahuoDao.selectList(queryWrapper);
    }

    @Override
    public List<WaimaoTichengFahuoTargetDto> selectFahuoShuliang() {
        return waimaoTichengFahuoDao.selectFahuoShuliang();
    }

    @Override
    public List<WaimaoTichengFahuoDto> seleteInFaHui(List<String> list) {
        return waimaoTichengFahuoDao.seleteInFaHui(list);
    }

    @Override
    public List<WaimaoTichengFahuoDto> selectFaHuiCal(String fahuoDate) {
        return waimaoTichengFahuoDao.selectFaHuiCal(fahuoDate);
    }

    @Override
    public List<WaimaoTichengFahuo> seleteYuebiaoFa(DomesticFahuo fahuo) {
        return waimaoTichengFahuoDao.seleteYuebiaoFa(fahuo);
    }

//    public static void main(String[] args) {
//        WaimaoTichengFahuoServiceImpl waimao = new WaimaoTichengFahuoServiceImpl();
//        Map<String, Map<String, Float>> map = new HashMap<>();
//        Map<String, Float> countMap = new HashMap<>();
//        map.put("孙少彬",countMap);
//        countMap.put("2021-01",100f);
//        countMap.put("2021-02",100f);
//        countMap.put("2021-03",100f);
//        countMap.put("2021-04",100f);
//        countMap.put("2021-05",100f);
//        countMap.put("2021-06",100f);
//        countMap.put("2021-07",100f);
//        countMap.put("2021-08",100f);
//        countMap.put("2021-09",100f);
//        countMap.put("2021-10",100f);
//        countMap.put("2021-11",100f);
//        countMap.put("2021-12",100f);
//
//        waimao.sumFahuoshuliang(map);
//        System.out.println(JSONObject.toJSONString(map));
//    }

    @Override
    public void sumFahuoshuliang(Map<String, Map<String, Float>> map) {
        // 将业务员发货数量信息 按月累加
        for(Map.Entry entry : map.entrySet()){
            Map<String,Float> countMap = (Map<String, Float>) entry.getValue();
            Set<String> dates = new HashSet<>();
            for(Map.Entry<String,Float> countEntry : countMap.entrySet()){
                String dateStr = countEntry.getKey();
                dates.add(dateStr.substring(0,dateStr.length()-2));
            }
            for(String year:dates){
                float totalCount = 0f;
                for(int i=1; i<=12; i++){
                    String dateFlag = year+(i < 10 ? "0"+i:i); // 月份如果是个位数，就在前面补0
                    Float aFloat = countMap.get(dateFlag);
                    if(aFloat == null){
                        break;
                    }
                    totalCount = totalCount + aFloat;
                    countMap.put(dateFlag,totalCount);
                }
            }
        }
    }


    @Override
    public void sumTargetshuliang(Map<String, Map<String, Float>> map) {
        // 将业务员发货数量信息 按月累加
        for(Map.Entry entry : map.entrySet()){
            Map<String,Float> countMap = (Map<String, Float>) entry.getValue();
            Set<String> dates = new HashSet<>();
            for(Map.Entry<String,Float> countEntry : countMap.entrySet()){
                String dateStr = countEntry.getKey();
                dates.add(dateStr.substring(0,dateStr.length()-2));
            }
            for(String year:dates){
                float totalCount = 0f;
                for(int i=1; i<=12; i++){
                    String dateFlag = year+(i < 10 ? "0"+i:i); // 月份如果是个位数，就在前面补0
                    Float aFloat = countMap.get(dateFlag);
                    totalCount = totalCount + aFloat;
                    countMap.put(dateFlag,totalCount);
                }
            }
        }
    }


}