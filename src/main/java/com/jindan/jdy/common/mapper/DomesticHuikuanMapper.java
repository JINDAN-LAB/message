package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.DomesticHuikuan;

import java.util.ArrayList;
import java.util.List;

/**   
 * @Description:TODO(内贸提成数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface DomesticHuikuanMapper extends BaseMapper<DomesticHuikuan> {

    List<DomesticHuikuan> seleteInHui(ArrayList<String> list);

    List<DomesticHuikuan> seleteDetailsInHui(ArrayList<String> list);

    List<DomesticHuikuan> seleteInBiaoshi(List<String> stringList);

    List<DomesticHuikuan> seleteYijisuanexcle();

    void saveAllHuiBatch(List<DomesticHuikuan> list);

    // 客户回款信息
    List<DomesticHuikuan> seleteYuebiaoHuikuan();
}
