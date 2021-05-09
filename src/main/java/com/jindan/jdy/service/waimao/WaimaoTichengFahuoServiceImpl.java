package com.jindan.jdy.service.waimao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.WaimaoTichengFahuoDto;
import com.jindan.jdy.common.mapper.WaimaoTichengFahuoMapper;
import com.jindan.jdy.common.pojo.DomesticFahuo;
import com.jindan.jdy.common.pojo.WaimaoTichengFahuo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public Page<WaimaoTichengFahuo> seletelist(WaimaoTichengFahuoDto jdyRole) {
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
        queryWrapper.eq("yijisuan","未计算");
        return (Page<WaimaoTichengFahuo>) waimaoTichengFahuoDao.selectPage(page,queryWrapper);
    }

    @Override
    public List<WaimaoTichengFahuoDto> seleteInFaHui(List<String> list) {
        return waimaoTichengFahuoDao.seleteInFaHui(list);
    }

    @Override
    public List<WaimaoTichengFahuo> seleteYuebiaoFa(DomesticFahuo fahuo) {
        return waimaoTichengFahuoDao.seleteYuebiaoFa(fahuo);
    }


}