package com.jindan.jdy.service.neimao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.DomesticHuikuanDto;
import com.jindan.jdy.common.pojo.DomesticHuikuan;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;
import java.util.List;

/**   
 * @Description:TODO(内贸提成服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface DomesticHuikuanService extends IService<DomesticHuikuan> {

    Page<DomesticHuikuan> seletepage(DomesticHuikuanDto domesticFahuoDto);

    List<DomesticHuikuan> listHuikuan(String ids);

    List<DomesticHuikuan> seleteInHui(ArrayList<String> strings);

    List<DomesticHuikuan> seleteDetailsInHui(ArrayList<String> strings);

//  查询回款信息
    List<DomesticHuikuan> seleteHuikuan(DomesticHuikuan domesticHuikuan);

    List<DomesticHuikuan> seleteInBiaoshi(List<String> stringList);
//    根据回款信息获取回款列表
    List<DomesticHuikuan> seleteExclelist(DomesticHuikuan param);

//    已计算的回款信息
    List<DomesticHuikuan> seleteYijisuanexcle();

    void saveAllHuiBatch(List<DomesticHuikuan> jijiabiaos);
//  客户回款余额
    List<DomesticHuikuan> seleteYuebiaoHuikuan();
}