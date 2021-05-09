package com.jindan.jdy.controller.neimao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.jindan.jdy.common.dto.DomesticFahuoDto;
import com.jindan.jdy.common.pojo.*;
import com.jindan.jdy.controller.utils.CommonUtils;
import com.jindan.jdy.service.neimao.*;
import com.jindan.jdy.common.utils.api.ResultVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
*
* <p>说明： 内贸提成API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年4月20日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "内贸提成回款信息")
@RestController
@RequestMapping("/domesticHuikuan")
public class DomesticHuikuanController{

    @Autowired
    DomesticFahuoService domesticFahuoService;

    @Autowired
    DomesticHuikuanService domesticHuikuanService;

    @Autowired
    DomesticBaozhuangService domesticBaozhuangService;

    @Autowired
    DomesticJijiabiao2Service domesticJijiabiao2Service;

    @Autowired
    DomesticJijiabiaoService domesticJijiabiaoService;

    @Autowired
    DomesticXishuService domesticXishuService;

    @ApiOperation(value = "计算详情列表", notes = "参数:计算详情列表")
    @PostMapping("detailslist")
    public ResultVo detailslist(@ApiParam(name = "list", required = false)
                                @RequestBody List<DomesticFahuo> list){
        List<DomesticFahuoDto> lists = new ArrayList<>();
        Map<String ,Float> mapfahuos  =new HashMap<>();
        for (int i = 0; i < list.size() ; i++) {
            if(mapfahuos.containsKey(list.get(i).getShouhuokehu())){
            }else{
                mapfahuos.put(list.get(i).getShouhuokehu(),0.0f);
            }
        }
        if(mapfahuos.keySet().size() > 0){
             lists = domesticFahuoService.seleteInFaDetails(mapfahuos.keySet());
        }else{
            return ResultVo.success(null,"客户：发货信息不符合要求");
        }
        try {
            Map<String ,Float> map  =new HashMap<>();
            Map<String ,Float> maps  =new HashMap<>();
            List<DomesticFahuoDto> list1 =new ArrayList<>();
        for(int i = 0; i < lists.size(); i++){
            try {
                if(lists.get(i).getDomesticBaozhuang().getBuhanbaozhuang() !=null ){
                    if(lists.get(i).getDomesticJijiabiao().getDiwudang() !=null){
                        if(map.containsKey(lists.get(i).getShouhuokehu())){
                            map.put(lists.get(i).getShouhuokehu(),map.get(lists.get(i).getShouhuokehu())+Float.valueOf(lists.get(i).getJiashuiheji()));
                        }else{
                            map.put(lists.get(i).getShouhuokehu(),Float.valueOf(lists.get(i).getJiashuiheji()));
                        }
                    }else{
                        return ResultVo.success(null,"客户："+lists.get(i).getShouhuokehu()+"物料名称："+lists.get(i).getWuliaomingcheng());
                    }
                }else{
                    return ResultVo.success(null,"客户："+lists.get(i).getShouhuokehu()+"包装物："+lists.get(i).getBzw());
               }
            }catch (Exception e){
                e.printStackTrace();
                return ResultVo.success(null,"客户："+lists.get(i).getShouhuokehu()+"包装物："+lists.get(i).getBzw()+"物料名称："+lists.get(i).getWuliaomingcheng());
            }
        }
      List<DomesticHuikuan> huilist = domesticHuikuanService.seleteInHui(new ArrayList<String>(map.keySet()));
            for(int i = 0; i < lists.size(); i++){
                try {
                    if(lists.get(i).getDomesticBaozhuang().getBuhanbaozhuang() !=null && lists.get(i).getDomesticJijiabiao().getDiwudang() !=null ){
                        if(maps.containsKey(lists.get(i).getShouhuokehu())){
                            maps.put(lists.get(i).getShouhuokehu(),maps.get(lists.get(i).getShouhuokehu())+Float.valueOf(lists.get(i).getJiashuiheji()));
                        }else{
                            maps.put(lists.get(i).getShouhuokehu(),Float.valueOf(lists.get(i).getJiashuiheji()));
                        }
                        for (int j = 0; j < huilist.size() ; j++) {
                            maps.get(huilist.get(j).getKehumingcheng());
                            if(lists.get(i).getShouhuokehu().equals(huilist.get(j).getKehumingcheng()) && maps.get(huilist.get(j).getKehumingcheng()) <=  Float.valueOf(huilist.get(j).getJine())){
//                                回款加上金额加上
                                list1.add(lists.get(i));
                                break;
                            }
                        }
                    }else{
                        continue;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    return ResultVo.success(null,"客户："+lists.get(i).getShouhuokehu()+"包装物："+lists.get(i).getBzw()+"物料名称："+lists.get(i).getWuliaomingcheng());
                }
            }

         return ResultVo.success(list1);
        }catch (Exception e) {
                e.printStackTrace();
              return ResultVo.success(null,"基价或者包装出现未找到");
         }
    }

    @Transactional(rollbackFor=Exception.class)
    @ApiOperation(value = "开始计算系数列表", notes = "参数:开始计算系数列表")
    @PostMapping("/list")
    public ResultVo addjijia2post(@ApiParam(name = "list", required = false)
                                  @RequestBody List<DomesticFahuoDto> fahuoDtoList) throws Exception {
        DomesticXishu xishu= null;
        List<DomesticXishu> list1 = domesticXishuService.list(null);
        if(list1.size() > 0){
              xishu = list1.get(0);
        }else{
            return ResultVo.failed("计算系数未获取。");
        }
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strdater = sdf.format(d);
        Map<String ,Float> map  =new HashMap<>();
        for(int i = 0; i < fahuoDtoList.size(); i++){
            if(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang() !=null && fahuoDtoList.get(i).getDomesticJijiabiao().getDiwudang() !=null ){
                if(map.containsKey(fahuoDtoList.get(i).getShouhuokehu())){
                    map.put(fahuoDtoList.get(i).getShouhuokehu(),map.get(fahuoDtoList.get(i).getShouhuokehu())+Float.valueOf(fahuoDtoList.get(i).getJiashuiheji()));
                }else{
                    map.put(fahuoDtoList.get(i).getShouhuokehu(),Float.valueOf(fahuoDtoList.get(i).getJiashuiheji()));
                }
            }
            if("".equals(fahuoDtoList.get(i).getYunshudanjia())){
                fahuoDtoList.get(i).setYunshudanjia("0");
            }
        }
  List<DomesticHuikuan> list3 = new ArrayList<>();
  List<DomesticHuikuan> huikuanlists = domesticHuikuanService.seleteDetailsInHui(new ArrayList<String>(map.keySet()));
  for (int i = 0; i < fahuoDtoList.size() ; i++){
      double huikuan = 0; // 累计回款信息
      String biaoshi="";
      double xianjiang=0.00; //先款奖
//    用于设置佣金
      double yongjins =  0; //得到真实价税合计
//    发货信息设置好计算日期
      fahuoDtoList.get(i).setJisuanriqi(strdater);
//    佣金进行计算
      if(fahuoDtoList.get(i).getYongjin() != null &&  fahuoDtoList.get(i).getYongjin().length() > 0){
          yongjins = yongjins+Float.valueOf(fahuoDtoList.get(i).getYongjin());
      }
      if(fahuoDtoList.get(i).getYongjinbili() != null &&  fahuoDtoList.get(i).getYongjinbili().length() > 0){
          yongjins = (yongjins+(Float.valueOf(fahuoDtoList.get(i).getHanshuidanjia())* Float.valueOf(fahuoDtoList.get(i).getYongjinbili())));
      }
      if(fahuoDtoList.get(i).getChanmofeiyong() != null &&  fahuoDtoList.get(i).getChanmofeiyong().length() > 0){
          yongjins = (yongjins+(Float.valueOf(fahuoDtoList.get(i).getChanmofeiyong())));
      }
      for (int j = 0; j < huikuanlists.size(); j++){
          if((huikuanlists.get(j).getKehumingcheng().equals(fahuoDtoList.get(i).getShouhuokehu()) && fahuoDtoList.get(i).getShouhuokehu() !=null) && Float.valueOf(huikuanlists.get(j).getJine()) > 0 ){
          int nums=0;
          long chengjianfa = 0;
          long fajianhui = 0;
          double lijiang = 0;
          double jijiang = 0;
          long chengjianhui = 0;
          double huisheng = 0.00; //回款剩余
          double yongs = 0.00; // 实际使用金额
          fahuoDtoList.get(i).setDanhao(fahuoDtoList.get(i).getDanhao() + "," + huikuanlists.get(j).getId());
          fahuoDtoList.get(i).setQueren("已计算");
           biaoshi = huikuanlists.get(j).getBiaoshi() + "," + fahuoDtoList.get(i).getId();  // 标识
          huikuan = huikuan + Double.valueOf(huikuanlists.get(j).getJine());
          if (huikuan >= Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())){
              DomesticFahuo domesticFahuo = new DomesticFahuo();
              BeanUtils.copyProperties(fahuoDtoList.get(i), domesticFahuo);
              huisheng = huikuan - Double.valueOf(fahuoDtoList.get(i).getJiashuiheji());
              yongs = Double.valueOf(huikuanlists.get(j).getJine()) - huisheng;
              huikuanlists.get(j).setJine(String.valueOf(huisheng));
              huikuanlists.get(j).setShijishiyongjine(huikuanlists.get(j).getShijishiyongjine() + "," + yongs);
              // fahuo-huikuan 现款将
              if (CommonUtils.getDistanceDays(fahuoDtoList.get(i).getDanjuriqi(), huikuanlists.get(j).getHuikuanriqi()) >= 0) {
                  if (!StringUtils.isEmpty(huikuanlists.get(j).getChengduiriqi())) {
                      chengjianfa = CommonUtils.getDistanceDays(huikuanlists.get(j).getChengduiriqi(), fahuoDtoList.get(i).getDanjuriqi());
                      chengjianhui = CommonUtils.getDistanceDays(huikuanlists.get(j).getChengduiriqi(), huikuanlists.get(j).getHuikuanriqi());
                  }else{
                      chengjianfa = -1;
                      chengjianhui = -1;
                  }
                  if (chengjianfa <= 0) {  //承兑减发货
                      xianjiang = (Double.parseDouble(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang())
                              - yongjins) * (yongs / Double.parseDouble(fahuoDtoList.get(i).getJiashuiheji())) * Double.valueOf(xishu.getXiankuan()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                      BigDecimal b = new BigDecimal(xianjiang);
                      double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                      huikuanlists.get(j).setXiankuanjiangbili(huikuanlists.get(j).getXiankuanjiangbili() + "," + xishu.getXiankuan());
                      huikuanlists.get(j).setXiankuanjiang(huikuanlists.get(j).getXiankuanjiang() + "," + f1);
                      huikuanlists.get(j).setBiaoshi(biaoshi);
                  } else {
                      if (chengjianhui > 0 && chengjianhui <= 90) {
                          xianjiang = (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang())
                                  - yongjins) * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * Double.valueOf(xishu.getWanwu()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                          BigDecimal b = new BigDecimal(xianjiang);
                          double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                          huikuanlists.get(j).setXiankuanjiangbili(huikuanlists.get(j).getXiankuanjiangbili() + "," + xishu.getWanwu());
                          huikuanlists.get(j).setXiankuanjiang(huikuanlists.get(j).getXiankuanjiang() + "," + f1);
                          huikuanlists.get(j).setBiaoshi(biaoshi);
                      } else if (chengjianhui > 90) {
                          xianjiang = (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang())
                                  - yongjins) * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * Double.valueOf(xishu.getWaner()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                          huikuanlists.get(j).setXiankuanjiangbili(huikuanlists.get(j).getXiankuanjiangbili() + "," + xishu.getWaner());
                          BigDecimal b = new BigDecimal(xianjiang);
                          double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                          huikuanlists.get(j).setXiankuanjiang(huikuanlists.get(j).getXiankuanjiang() + "," + f1);
                          huikuanlists.get(j).setBiaoshi(biaoshi);
                      }
                  }
              }else{ // 不是先款奖的情况
                  huikuanlists.get(j).setXiankuanjiang(huikuanlists.get(j).getXiankuanjiang() + "," + "0");
                  huikuanlists.get(j).setXiankuanjiangbili(huikuanlists.get(j).getXiankuanjiangbili() + "," + "0");
              } // 先款奖结束
              String str = fahuoDtoList.get(i).getShoukuanxieyi();
              //正则表达式，用于匹配非数字串，+号用于匹配出多个非数字串
              String regEx = "[^0-9]+";
              Pattern pattern = Pattern.compile(regEx);
              //用定义好的正则表达式拆分字符串，把字符串中的数字留出来
              if (str != null && !str.equals("")) {
                  String[] cs = pattern.split(str);
                  if (cs.length >= 2) {
                      nums = Integer.parseInt(cs[1]);
                  }else{
                      nums = 0;
                  }
               }else{
                  nums = 0;
              }
              if (Double.valueOf(nums) <= 10) {
                  if (!fahuoDtoList.get(i).getFangshi().equals("公路运输")) {
                      nums = Integer.parseInt(xishu.getHuoyunxian());
                  } else {
                      nums = 10;
                  }
              } else if (Double.valueOf(nums) > 10 ) {
                  if (!fahuoDtoList.get(i).getFangshi().equals("公路运输")) {
                      if(Double.valueOf(nums) < 30){
                          nums = Integer.parseInt(xishu.getHuoyunxian());
                      }
                  }
              }
              fajianhui = CommonUtils.getDistanceDays(fahuoDtoList.get(i).getDanjuriqi(), huikuanlists.get(j).getHuikuanriqi());
              if((fajianhui + Double.valueOf(nums)) >= 0){
                  if (!StringUtils.isEmpty(huikuanlists.get(j).getChengduiriqi())){
                      if(Double.valueOf(nums) <= 10){
                          if (fahuoDtoList.get(i).getFangshi().equals("公路运输")) {
                              lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                      (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                      * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getChenglixibuchaoqi()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                              BigDecimal b = new BigDecimal(lijiang);
                              double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                              huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                              huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," + "0.01/30*" + xishu.getChenglixibuchaoqi());
                              huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                              huikuanlists.get(j).setBiaoshi(biaoshi);
                          } else {
                              lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                      (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                      * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getChenglixibuchaoqi()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                              BigDecimal b = new BigDecimal(lijiang);
                              double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                              huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                              huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," + "0.01/30*" + xishu.getChenglixibuchaoqi());
                              huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                              huikuanlists.get(j).setBiaoshi(biaoshi);
                          }
                      } else if (Double.valueOf(nums) > 10) {
                          lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                  (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                  * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getChenglixibuchaoqi()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                          BigDecimal b = new BigDecimal(lijiang);
                          double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                          huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                          huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," + "0.01/30*" + xishu.getChenglixibuchaoqi());
                          huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                          huikuanlists.get(j).setBiaoshi(biaoshi);
                      }
                  } else {
                      if (Double.valueOf(nums) <= 10) {
                          if (fahuoDtoList.get(i).getFangshi().equals("公路运输")) {
                              lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                      (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                      * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getBuchaoqi1()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                              BigDecimal b = new BigDecimal(lijiang);
                              double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                              huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                              huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," + "0.01/30*" + xishu.getBuchaoqi1());
                              huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                              huikuanlists.get(j).setBiaoshi(biaoshi);
                          } else {
                              lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                      (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                      * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getBuchaoqi2()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                              BigDecimal b = new BigDecimal(lijiang);
                              double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                              huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                              huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," + "0.01/30*" + xishu.getBuchaoqi2());
                              huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                              huikuanlists.get(j).setBiaoshi(biaoshi);
                          }
                      } else if (Double.valueOf(nums) > 10) {
                          lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                  (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                  * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getBuchaoqi1()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                          BigDecimal b = new BigDecimal(lijiang);
                          double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                          huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                          huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," + "0.01/30*" + xishu.getBuchaoqi1());
                          huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                          huikuanlists.get(j).setBiaoshi(biaoshi);
                      }
                  }
              } else {
//                   超期中的不超期现象
                  if (!StringUtils.isEmpty(huikuanlists.get(j).getChengduiriqi())) {
                      if (chengjianhui <= 0 ) {   //  承兑减发货
                          if (Double.valueOf(nums) <= 10) {  // 超期
                              if (fahuoDtoList.get(i).getFangshi().equals("公路运输")) {
                                  lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                          (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                          * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getChenglixichaoqi()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                                  BigDecimal b = new BigDecimal(lijiang);
                                  double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                                  huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                                  huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," + "0.01/30*" + xishu.getChenglixichaoqi());
                                  huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                                  huikuanlists.get(j).setBiaoshi(biaoshi);
                              } else {
                                  lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                          (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                          * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getChenglixichaoqi()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                                  BigDecimal b = new BigDecimal(lijiang);
                                  double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                                  huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                                  huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," + "0.01/30*" + xishu.getChenglixichaoqi());
                                  huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                                  huikuanlists.get(j).setBiaoshi(biaoshi);
                              }
                          } else if (Double.valueOf(nums) > 10) {
                              lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                      (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                      * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getChenglixibuchaoqi()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                              BigDecimal b = new BigDecimal(lijiang);
                              double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                              huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                              huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," + "0.01/30*" + xishu.getChenglixibuchaoqi());
                              huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu()+ ","+(nums+fajianhui));
                              huikuanlists.get(j).setBiaoshi(biaoshi);
                          }
                      } else {
                          //真超期部分 超期部分
                          if (Double.valueOf(nums) <= 10) {
                              if (fahuoDtoList.get(i).getFangshi().equals("公路运输")) {
                                  lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                          (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                          * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getChenglixichaoqi()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                                  BigDecimal b = new BigDecimal(lijiang);
                                  double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                                  huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                                  huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," + "0.01/30*" + xishu.getChenglixichaoqi());
                                  huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                                  huikuanlists.get(j).setBiaoshi(biaoshi);
                              } else {
                                  lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                          (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                          * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getChenglixichaoqi()) * Double.valueOf(fahuoDtoList.get(i).getShuliang()) ;
                                  BigDecimal b = new BigDecimal(lijiang);
                                  double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                                  huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                                  huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," + "0.01/30*" + xishu.getChenglixichaoqi());
                                  huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                                  huikuanlists.get(j).setBiaoshi(biaoshi);
                              }
                          } else if (Double.valueOf(nums) > 10) {
                              if (Double.valueOf(nums) >= 0) {
                                  lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                          (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                          * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getChenglixichaoqi()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                                  BigDecimal b = new BigDecimal(lijiang);
                                  double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                                  huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                                  huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," + "0.01/30*" + xishu.getChenglixichaoqi());
                                  huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                                  huikuanlists.get(j).setBiaoshi(biaoshi);
                              } else {
                                  lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                          (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                          * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getChenglixichaoqi()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                                  BigDecimal b = new BigDecimal(lijiang);
                                  double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                                  huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                                  huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," + "0.01/30*" + xishu.getChenglixichaoqi());
                                  huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                                  huikuanlists.get(j).setBiaoshi(biaoshi);
                              }
                          }
                      }
                  } else {
                      if (chengjianfa <= 0){
                          //真超期部分 超期部分
                          if (Double.valueOf(nums) <= 10) {
                              if (fahuoDtoList.get(i).getFangshi().equals("公路运输")) {
                                  lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                          (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                          * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getBuchaoqi1()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                                  BigDecimal b = new BigDecimal(lijiang);
                                  double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                                  huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                                  huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," + "0.01/30*" + xishu.getBuchaoqi1());
                                  huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                                  huikuanlists.get(j).setBiaoshi(biaoshi);
                              } else {
                                  lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                          (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                          * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getBuchaoqi2()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                                  BigDecimal b = new BigDecimal(lijiang);
                                  double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                                  huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                                  huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," + "0.01/30*" + xishu.getBuchaoqi2());
                                  huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                                  huikuanlists.get(j).setBiaoshi(biaoshi);
                              }
                          } else if (Double.valueOf(nums) > 10) {
                              if (fajianhui >= 0) {
                                  lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                          (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                          * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getBuchaoqi1()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                                  BigDecimal b = new BigDecimal(lijiang);
                                  double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                                  huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                                  huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," + "0.01/30*" + xishu.getBuchaoqi1());
                                  huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                                  huikuanlists.get(j).setBiaoshi(biaoshi);
                              } else {
                                  lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                          (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                          * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getBuchaoqi1()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                                  BigDecimal b = new BigDecimal(lijiang);
                                  double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                                  huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                                  huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," + "0.01/30*" + xishu.getBuchaoqi1());
                                  huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                                  huikuanlists.get(j).setBiaoshi(biaoshi);
                              }
                          }
                      }
                  }
              }

              double jingshuixiaoshoujia1 = Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins;
              BigDecimal b21 = new BigDecimal(jingshuixiaoshoujia1);
              double jingshuixiaoshoujia = b21.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
              if (fahuoDtoList.get(i).getShifouweixinkehu() != null && fahuoDtoList.get(i).getShifouweixinkehu().equals("是")) {
                  fahuoDtoList.get(i).setJijiaticheng("0");
                  fahuoDtoList.get(i).setJijiatichengbili("0");
                  if (jingshuixiaoshoujia >= Float.valueOf(fahuoDtoList.get(i).getDomesticJijiabiao().getDisandang()) && jingshuixiaoshoujia < Float.valueOf(fahuoDtoList.get(i).getDomesticJijiabiao().getDisidang())){
                      jijiang = (jingshuixiaoshoujia * Double.valueOf(fahuoDtoList.get(i).getShuliang()) * Double.valueOf(xishu.getXinkehu1()));
                      BigDecimal b = new BigDecimal(jijiang);
                      double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                      fahuoDtoList.get(i).setJijiaticheng(String.valueOf(f1));
                      fahuoDtoList.get(i).setJijiatichengbili(xishu.getXinkehu1());
                  }
                  if (jingshuixiaoshoujia >= Float.valueOf(fahuoDtoList.get(i).getDomesticJijiabiao().getDisidang()) && jingshuixiaoshoujia < Float.valueOf(fahuoDtoList.get(i).getDomesticJijiabiao().getDiwudang())) {
                      jijiang = (jingshuixiaoshoujia * Double.valueOf(fahuoDtoList.get(i).getShuliang()) * Double.valueOf(xishu.getXinkehu2()));
                      BigDecimal b = new BigDecimal(jijiang);
                      double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                      fahuoDtoList.get(i).setJijiaticheng(String.valueOf(f1));
                      fahuoDtoList.get(i).setJijiatichengbili(xishu.getXinkehu2());
                  }
                  if (jingshuixiaoshoujia >= Float.valueOf(fahuoDtoList.get(i).getDomesticJijiabiao().getDiwudang())) {
                      jijiang = (Double.valueOf(xishu.getXinkehu3()) * Float.valueOf(fahuoDtoList.get(i).getDomesticJijiabiao().getDiwudang()) + (Double.valueOf(xishu.getXinkehu3b()) * ((jingshuixiaoshoujia - Double.valueOf(fahuoDtoList.get(i).getDomesticJijiabiao().getDiwudang()))))) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                      BigDecimal b = new BigDecimal(jijiang);
                      System.out.println("净水销售");
                      double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                      fahuoDtoList.get(i).setJijiaticheng(String.valueOf(f1));
                      fahuoDtoList.get(i).setJijiatichengbili(xishu.getXinkehu3());
                  }
                  jingshuixiaoshoujia = 0;
                 } else { //是新客户情况结束
                  fahuoDtoList.get(i).setJijiaticheng("0");
                  fahuoDtoList.get(i).setJijiatichengbili("0");
                  if (jingshuixiaoshoujia >= Float.valueOf(fahuoDtoList.get(i).getDomesticJijiabiao().getDisandang()) && jingshuixiaoshoujia < Float.valueOf(fahuoDtoList.get(i).getDomesticJijiabiao().getDisidang())) {
                      jijiang = (Double.valueOf(xishu.getJingshui1()) * jingshuixiaoshoujia * Double.valueOf(fahuoDtoList.get(i).getShuliang()));
                      BigDecimal b = new BigDecimal(jijiang);
                      double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                      fahuoDtoList.get(i).setJijiaticheng(String.valueOf(f1));
                      fahuoDtoList.get(i).setJijiatichengbili(xishu.getJingshui1());
                  }
                  if (jingshuixiaoshoujia >= Float.valueOf(fahuoDtoList.get(i).getDomesticJijiabiao().getDisidang()) && jingshuixiaoshoujia < Float.valueOf(fahuoDtoList.get(i).getDomesticJijiabiao().getDiwudang())) {
                      jijiang = (Double.valueOf(xishu.getJingshui2()) * jingshuixiaoshoujia * Double.valueOf(fahuoDtoList.get(i).getShuliang()));
                      System.out.println("jiang" + jijiang);
                      BigDecimal b = new BigDecimal(jijiang);
                      double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                      fahuoDtoList.get(i).setJijiaticheng(String.valueOf(f1));
                      fahuoDtoList.get(i).setJijiatichengbili(xishu.getJingshui2());
                  }
                  if (jingshuixiaoshoujia >= Float.valueOf(fahuoDtoList.get(i).getDomesticJijiabiao().getDiwudang())) {
                      jijiang = ((Double.valueOf(xishu.getJingshui3()) * Float.valueOf(fahuoDtoList.get(i).getDomesticJijiabiao().getDiwudang())) + (Double.valueOf(xishu.getJingshui3b()) * ((jingshuixiaoshoujia - Double.valueOf(fahuoDtoList.get(i).getDomesticJijiabiao().getDiwudang()))))) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                      System.out.println("jiang" + jijiang);
                      BigDecimal b = new BigDecimal(jijiang);
                      double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                      fahuoDtoList.get(i).setJijiaticheng(String.valueOf(f1));
                      fahuoDtoList.get(i).setJijiatichengbili(xishu.getJingshui3());
                  }
                  jingshuixiaoshoujia = 0;
              }
              list3.add(huikuanlists.get(j));
              break;
          } else {
              yongs = Double.valueOf(huikuanlists.get(j).getJine());
              fajianhui = CommonUtils.getDistanceDays(fahuoDtoList.get(i).getDanjuriqi(), huikuanlists.get(j).getHuikuanriqi());
              if (CommonUtils.getDistanceDays(fahuoDtoList.get(i).getDanjuriqi(), huikuanlists.get(j).getHuikuanriqi()) >= 0) {
                  if (!StringUtils.isEmpty(huikuanlists.get(j).getChengduiriqi())) {
                      chengjianfa = CommonUtils.getDistanceDays(huikuanlists.get(j).getChengduiriqi(), fahuoDtoList.get(i).getDanjuriqi());
                      chengjianhui = CommonUtils.getDistanceDays(huikuanlists.get(j).getChengduiriqi(), huikuanlists.get(j).getHuikuanriqi());
                  }else{
                      chengjianfa = -1;
                      chengjianhui = -1;
                  }
                  if (chengjianfa <= 0) {  //承兑减发货
                      xianjiang = (Double.parseDouble(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang())
                              - yongjins) * (yongs / Double.parseDouble(fahuoDtoList.get(i).getJiashuiheji())) * Double.valueOf(xishu.getXiankuan()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());

                      BigDecimal b = new BigDecimal(xianjiang);
                      double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                      huikuanlists.get(j).setXiankuanjiangbili(huikuanlists.get(j).getXiankuanjiangbili() + "," + xishu.getXiankuan());
                      huikuanlists.get(j).setXiankuanjiang(huikuanlists.get(j).getXiankuanjiang() + "," + f1);
                      huikuanlists.get(j).setBiaoshi(biaoshi);
                  } else {
                      if (chengjianhui > 0 && chengjianhui <= 90) {
                          xianjiang = (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang())
                                  - yongjins) * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * Double.valueOf(xishu.getWanwu()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                          BigDecimal b = new BigDecimal(xianjiang);
                          double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                          huikuanlists.get(j).setXiankuanjiangbili(huikuanlists.get(j).getXiankuanjiangbili() + "," + xishu.getWanwu());
                          huikuanlists.get(j).setXiankuanjiang(huikuanlists.get(j).getXiankuanjiang() + "," + f1);
                          huikuanlists.get(j).setBiaoshi(biaoshi);
                      } else if (chengjianhui > 90) {
                          xianjiang = (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang())
                                  - yongjins) * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * Double.valueOf(xishu.getWaner()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                          huikuanlists.get(j).setXiankuanjiangbili(huikuanlists.get(j).getXiankuanjiangbili() + "," + xishu.getWaner());
                          BigDecimal b = new BigDecimal(xianjiang);
                          double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                          huikuanlists.get(j).setXiankuanjiang(huikuanlists.get(j).getXiankuanjiang() + "," + f1);
                          huikuanlists.get(j).setBiaoshi(biaoshi);
                      }
                  }
              }else{ // 不是先款奖的情况
                  huikuanlists.get(j).setXiankuanjiang(huikuanlists.get(j).getXiankuanjiang() + "," + "0");
                  huikuanlists.get(j).setXiankuanjiangbili(huikuanlists.get(j).getXiankuanjiangbili() + "," + "0");
                  huikuanlists.get(j).setBiaoshi(biaoshi);
              } // 先款奖结束
              String str =  fahuoDtoList.get(i).getShoukuanxieyi();
              //正则表达式，用于匹配非数字串，+号用于匹配出多个非数字串
              String regEx = "[^0-9]+";
              Pattern pattern = Pattern.compile(regEx);
              if (str != null && !str.equals("")) {
                  String[] cs = pattern.split(str);
                  if (cs.length >= 2) {
                      nums = Integer.parseInt(cs[1]);
                  } else {
                      nums = 0;
                  }
              }else{
                  nums = 0;
              }
              if (Double.valueOf(nums) <= 10) {
                  if (!fahuoDtoList.get(i).getFangshi().equals("公路运输")){
                      nums = Integer.parseInt(xishu.getHuoyunxian());
                  } else {
                      nums = Integer.parseInt(xishu.getQiyunxian());
                  }
              } else if (Double.valueOf(nums) > 10 && Double.valueOf(nums) < Double.valueOf(xishu.getHuoyunxian())) {
                  if (!fahuoDtoList.get(i).getFangshi().equals("公路运输")) {
                      nums = Integer.parseInt(xishu.getHuoyunxian());
                  }else{
                      nums = nums;
                  }
              }
              if ((fajianhui +  nums) >= 0 ) { //   承兑减回款
                  //不超期部分
                  if (!StringUtils.isEmpty(huikuanlists.get(j).getChengduiriqi())) {
                      if (Double.valueOf(nums) <= 10) {
                          if (!fahuoDtoList.get(i).getFangshi().equals("公路运输")) {
                              lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                      (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                      * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getChenglixibuchaoqi()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                              BigDecimal b = new BigDecimal(lijiang);
                              double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                              huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                              huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," +"0.01/30*" + xishu.getChenglixibuchaoqi());
                              huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                              huikuanlists.get(j).setBiaoshi(biaoshi);
                          } else {
                              lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                      (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                      * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getChenglixibuchaoqi()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                              BigDecimal b = new BigDecimal(lijiang);
                              double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                              huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                              huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," +"0.01/30*" + xishu.getChenglixibuchaoqi());
                              huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                              huikuanlists.get(j).setBiaoshi(biaoshi);
                          }
                      } else if (Double.valueOf(nums) > 10) {
                          lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                  (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                  * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getChenglixibuchaoqi()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                          BigDecimal b = new BigDecimal(lijiang);
                          double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                          huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);

                          huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," +"0.01/30*" + xishu.getChenglixibuchaoqi());
                          huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                          huikuanlists.get(j).setBiaoshi(biaoshi);
                      }
                  } else {
                      if (Double.valueOf(nums) <= 10) {
                          if (!fahuoDtoList.get(i).getFangshi().equals("公路运输")) {
                              lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                      (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                      * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getBuchaoqi2()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                              BigDecimal b = new BigDecimal(lijiang);
                              double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                              huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                              huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," +"0.01/30*" + xishu.getBuchaoqi2());
                              huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                              huikuanlists.get(j).setBiaoshi(biaoshi);
                          } else {
                              lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                      (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                      * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getBuchaoqi1()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                              BigDecimal b = new BigDecimal(lijiang);
                              double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                              huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                              huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," +"0.01/30*" + xishu.getBuchaoqi1());
                              huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                              huikuanlists.get(j).setBiaoshi(biaoshi);
                          }
                      } else if (Double.valueOf(nums) > 10) {
                          lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                  (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                  * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getBuchaoqi1()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                          BigDecimal b = new BigDecimal(lijiang);
                          double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                          huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                          huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," +"0.01/30*" + xishu.getBuchaoqi1());
                          huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                          huikuanlists.get(j).setBiaoshi(biaoshi);
                      }
                  }
              } else {
                  // 超期中的不超期现象
                  if (!StringUtils.isEmpty(huikuanlists.get(j).getChengduiriqi())) {
                      if (chengjianhui >= 0 ) {   //  承兑减发货
                          if (Double.valueOf(nums) <= 10) {
                              if (!fahuoDtoList.get(i).getFangshi().equals("公路运输")) {
                                  lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                          (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                          * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getChenglixibuchaoqi()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                                  BigDecimal b = new BigDecimal(lijiang);
                                  double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                                  huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                                  huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," +"0.01/30*" + xishu.getChenglixibuchaoqi());
                                  huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                                  huikuanlists.get(j).setBiaoshi(biaoshi);
                              } else {
                                  lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                          (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                          * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getChenglixibuchaoqi()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                                  BigDecimal b = new BigDecimal(lijiang);
                                  double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                                  huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                                  huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," +"0.01/30*" + xishu.getChenglixibuchaoqi());
                                  huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                                  huikuanlists.get(j).setBiaoshi(biaoshi);
                              }
                          } else if (Double.valueOf(nums) > 10) {
                              lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                      (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                      * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getChenglixibuchaoqi()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                              BigDecimal b = new BigDecimal(lijiang);
                              double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                              huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                              huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," +"0.01/30*" + xishu.getChenglixibuchaoqi());
                              huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                              huikuanlists.get(j).setBiaoshi(biaoshi);
                          }
                      } else {
                          //承兑超期情况真超期部分 超期部分
                          if (Double.valueOf(nums) <= 10) {
                              if (!fahuoDtoList.get(i).getFangshi().equals("公路运输")) {
                                  lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                          (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                          * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getChenglixichaoqi()) * Double.valueOf(fahuoDtoList.get(i).getShuliang()) ;
                                  BigDecimal b = new BigDecimal(lijiang);
                                  double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                                  huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                                  huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," +"0.01/30*" + xishu.getChenglixichaoqi());
                                  huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                                  huikuanlists.get(j).setBiaoshi(biaoshi);
                              } else {
                                  lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                          (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                          * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getChenglixichaoqi()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                                  BigDecimal b = new BigDecimal(lijiang);
                                  double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                                  huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                                  huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," +"0.01/30*" + xishu.getChenglixichaoqi());
                                  huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                                  huikuanlists.get(j).setBiaoshi(biaoshi);
                              }
                          } else if (Double.valueOf(nums) > 10) {
                              if (fajianhui >= 0) {
                                  lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                          (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                          * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getChaoqi1()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                                  BigDecimal b = new BigDecimal(lijiang);
                                  double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                                  huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                                  huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," +"0.01/30*" + xishu.getChaoqi1());
                                  huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                                  huikuanlists.get(j).setBiaoshi(biaoshi);
                              } else {
                                  lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                          (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                          * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getChaoqi1()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                                  BigDecimal b = new BigDecimal(lijiang);
                                  double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                                  huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                                  huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," +"0.01/30*" + xishu.getChaoqi1());
                                  huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                                  huikuanlists.get(j).setBiaoshi(biaoshi);
                              }
                          }
                      }
                  } else {
                      if (chengjianfa <= 0 ) {
                          //真超期部分 超期部分
                          if (Double.valueOf(nums) <= 10) {
                              if (!fahuoDtoList.get(i).getFangshi().equals("公路运输")) {
                                  lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                          (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                          * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getBuchaoqi2()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                                  BigDecimal b = new BigDecimal(lijiang);
                                  double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                                  huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                                  huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," +"0.01/30*" + xishu.getBuchaoqi2());
                                  huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                                  huikuanlists.get(j).setBiaoshi(biaoshi);
                              } else {
                                  lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                          (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                          * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getBuchaoqi1()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                                  BigDecimal b = new BigDecimal(lijiang);
                                  double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                                  huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                                  huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," +"0.01/30*" + xishu.getBuchaoqi1());
                                  huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                                  huikuanlists.get(j).setBiaoshi(biaoshi);
                              }
                          } else if (Double.valueOf(nums) > 10) {
                              if (fajianhui >= 0) {
                                  lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                          (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                          * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getBuchaoqi1()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                                  BigDecimal b = new BigDecimal(lijiang);
                                  double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                                  huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                                  huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," +"0.01/30*" + xishu.getBuchaoqi1());
                                  huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                                  huikuanlists.get(j).setBiaoshi(biaoshi);
                              } else {
                                  lijiang = (Double.valueOf(nums) + Double.valueOf(fajianhui)) *
                                          (Double.valueOf(fahuoDtoList.get(i).getHanshuidanjia()) - Double.valueOf(fahuoDtoList.get(i).getYunshudanjia()) - Double.valueOf(fahuoDtoList.get(i).getDomesticBaozhuang().getBuhanbaozhuang()) - yongjins)
                                          * (yongs / Double.valueOf(fahuoDtoList.get(i).getJiashuiheji())) * 0.01 / 30 * Double.valueOf(xishu.getBuchaoqi1()) * Double.valueOf(fahuoDtoList.get(i).getShuliang());
                                  BigDecimal b = new BigDecimal(lijiang);
                                  double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                                  huikuanlists.get(j).setLixijiang(huikuanlists.get(j).getLixijiang() + "," + f1);
                                  huikuanlists.get(j).setLixijiangbili(huikuanlists.get(j).getLixijiangbili() + "," +"0.01/30*" + xishu.getBuchaoqi1());
                                  huikuanlists.get(j).setYufutianshu(huikuanlists.get(j).getYufutianshu() + "," + (nums+fajianhui));
                                  huikuanlists.get(j).setBiaoshi(biaoshi);
                              }
                          }
                      }
                  }
              }
              huikuanlists.get(j).setBiaoshi(biaoshi);
              huikuanlists.get(j).setShijishiyongjine(huikuanlists.get(j).getShijishiyongjine() + "," + huikuanlists.get(j).getJine());
              huikuanlists.get(j).setJine("0");
              list3.add(huikuanlists.get(j));
              continue;
           } // 循环一次回款不足的情况结束
          } //判断是否是发货同一个客户
        } // 回款的循环结束
       }
        List<DomesticFahuo> list =new ArrayList<>();
        for(int i = 0; i < fahuoDtoList.size(); i++){
            DomesticFahuo domesticFahuo = new DomesticFahuo();
            BeanUtils.copyProperties(fahuoDtoList.get(i),domesticFahuo);
            domesticFahuo.setId(Integer.valueOf(fahuoDtoList.get(i).getId()));
            list.add(domesticFahuo);
        }
        boolean b1 = domesticFahuoService.updateBatchById(list);
        boolean b = domesticHuikuanService.updateBatchById(list3);
        if(b1&& b){
         return  ResultVo.success();
        }
        return ResultVo.failed();
  }

}