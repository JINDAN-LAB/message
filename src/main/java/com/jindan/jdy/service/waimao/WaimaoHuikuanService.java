package com.jindan.jdy.service.waimao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.WaimaoHuikuanDto;
import com.jindan.jdy.common.pojo.WaimaoFahuo;
import com.jindan.jdy.common.pojo.WaimaoHuikuan;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**   
 * @Description:TODO(规则服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface WaimaoHuikuanService extends IService<WaimaoHuikuan> {

    Page<WaimaoHuikuan> seletelist(WaimaoHuikuanDto waimaoHuikuan);

    List<WaimaoHuikuan> seleteHuikuan(WaimaoFahuo jdyRole);

    List<WaimaoHuikuan> seleteHuikuanDanjuhao(WaimaoFahuo param);

//    一直几月份的回款信息
    List<WaimaoHuikuan> seleteHuikuanDangyueAllhao(WaimaoFahuo param);

//    当月的回款信息
     Map<String,WaimaoHuikuan>  seleteHuikuanDangyuehuikuan(WaimaoFahuo jdyRole);

    Map<String,WaimaoHuikuan> seleteHuikuanyewuyuanDanjuhao(WaimaoFahuo jdyRole) throws ParseException;

    Map<String,Float> seleteQiankuan(WaimaoFahuo jdyRole) throws ParseException;

//    累计欠款情况
    Map<String, Float> seleteQuannianQiankuan(WaimaoFahuo jdyRole) throws ParseException;
}