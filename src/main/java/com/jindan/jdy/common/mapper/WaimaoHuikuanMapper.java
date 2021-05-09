package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.pojo.WaimaoFahuo;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.WaimaoHuikuan;

import java.util.List;

/**   
 * @Description:TODO(规则数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface WaimaoHuikuanMapper extends BaseMapper<WaimaoHuikuan> {

    List<WaimaoHuikuan> seleteHuikuan(WaimaoFahuo jdyRole);

    List<WaimaoHuikuan> seleteHuikuanDanjuhao(WaimaoFahuo param);

    List<WaimaoHuikuan> seleteHuikuanDangyueAllhao(WaimaoFahuo param);

    List<WaimaoHuikuan> seleteHuikuanDangyuehuikuan(WaimaoFahuo jdyRole);

    List<WaimaoHuikuan> seleteHuikuanyewuyuanDanjuhao(WaimaoFahuo jdyRole);

    List<WaimaoHuikuan> seleteQuannianHuikuanDangyuehuikuan(WaimaoFahuo jdyRole);

    List<WaimaoHuikuan> seBenyuehuikuanjine(WaimaoFahuo jdyRole);
}
