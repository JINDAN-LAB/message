package com.jindan.jdy.controller.waimao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.WaimaoTichengFahuoDto;
import com.jindan.jdy.common.pojo.DomesticFahuo;
import com.jindan.jdy.common.pojo.WaimaoTichengFahuo;
import com.jindan.jdy.common.pojo.WaimaoTichengHuikuan;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.CommonUtils;
import com.jindan.jdy.controller.utils.WorkbookUtils;
import com.jindan.jdy.service.waimao.WaimaoTichengFahuoService;
import com.jindan.jdy.service.waimao.WaimaoTichengHuikuanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
*
* <p>说明： 外贸提成API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年5月28日
*
*/
@Slf4j
@Api(tags = "外贸提成发货管理")
@RestController
@RequestMapping("/waimaoTichengFahuo")
public class WaimaoTichengFahuoController{

    @Autowired
    WaimaoTichengFahuoService waimaoTichengFahuoService;

    @Autowired
    WaimaoTichengHuikuanService   waimaoTichengHuikuanService;

    @ApiOperation(value = "发货信息批量导入", notes = "参数:发货信息批量导入")
    @PostMapping("addBatchTichengFahuo")
    public ResultVo addTichengFahuo(@RequestParam("file") MultipartFile file) throws Exception {

        String presenttime = CommonUtils.getPresenttime();
        //创建Excel工作薄
        Workbook work = WorkbookUtils.getWorkbook(file.getInputStream(),file.getOriginalFilename());
        if(null == work){
            throw new Exception("创建Excel工作薄为空！");
        }
        log.info("work.getNumberOfSheets()的值为："+ work.getNumberOfSheets());
        Sheet sheet  = work.getSheetAt(0);
        if(sheet==null){
            throw new Exception("创建Excel工作薄为空！");
        }
        List<WaimaoTichengFahuo> jijiabiaos = new ArrayList<>();
        for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++){
          Row row = sheet.getRow(j);
          if(row==null||row.getFirstCellNum()==j){continue;}
            WaimaoTichengFahuo jijiabiao = new WaimaoTichengFahuo();
            jijiabiao.setFahuotime(row.getCell(0).getStringCellValue());
            jijiabiao.setFapiaohao(row.getCell(1).getStringCellValue());
            jijiabiao.setHetonghao( (row.getCell(2).getStringCellValue()));
            jijiabiao.setPici(row.getCell(3).getStringCellValue());
            jijiabiao.setYewuyuan( (row.getCell(4).getStringCellValue()));
            jijiabiao.setShouhuokehu(row.getCell(5).getStringCellValue());
            jijiabiao.setDiqufenlei( (row.getCell(6).getStringCellValue()));
            jijiabiao.setYujizhuangtime(row.getCell(7).getStringCellValue());
            jijiabiao.setWuliaoming( (row.getCell(8).getStringCellValue()));
            jijiabiao.setShuliang(Float.valueOf(row.getCell(9).getStringCellValue()));
            jijiabiao.setHanshuidanjia(Float.valueOf(row.getCell(10).getStringCellValue()));
            jijiabiao.setBizhong((row.getCell(11).getStringCellValue()));
            jijiabiao.setJiagetiaokuan(row.getCell(12).getStringCellValue());
            jijiabiao.setJiashuiheji((row.getCell(13).getStringCellValue()));
            jijiabiao.setBenbijiashuiheji(row.getCell(14).getStringCellValue());
            jijiabiao.setBaozhuangwu((row.getCell(15).getStringCellValue()));
            jijiabiao.setHuikuanxieyi(row.getCell(16).getStringCellValue());
            jijiabiao.setYunshudanjia((row.getCell(17).getStringCellValue()));
            jijiabiao.setYunshujine(row.getCell(18).getStringCellValue());
            jijiabiao.setHuilv((row.getCell(19).getStringCellValue()));
            jijiabiao.setFobzongjia(row.getCell(20).getStringCellValue());
            jijiabiao.setHaiyunfei((row.getCell(21).getStringCellValue()));
            jijiabiao.setYongjin((row.getCell(22).getStringCellValue()));
            jijiabiao.setCeshi2(row.getCell(23).getStringCellValue());
            jijiabiaos.add(jijiabiao);
        }
        waimaoTichengFahuoService.saveBatch(jijiabiaos);
        return ResultVo.success();
    }

    @ApiOperation(value = "查询提成发货信息", notes = "参数:查询包装信息")
    @PostMapping("/seleteTichengFahuo")
    public ResultVo seleteTichengFahuo(@ApiParam(value = "jdyRole", required = false)
                                           @RequestBody WaimaoTichengFahuoDto jdyRole){
        Page<WaimaoTichengFahuo> seletelist = waimaoTichengFahuoService.seletelist(jdyRole);
        return  ResultVo.success(seletelist);
    }

    @ApiOperation("更新提成发货信息")
    @PostMapping("/updateTichengFahuo")
    public ResultVo updateTichengFahuo(@ApiParam(value = "jdyRole", required = true)
                                           @RequestBody WaimaoTichengFahuo jdyRole){
        boolean b = waimaoTichengFahuoService.updateById(jdyRole);
        if(b){
            return ResultVo.success(jdyRole);
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增外贸提成发货信息")
    @PostMapping("/addTichengFahuo")
    public ResultVo addTichengFahuo( @ApiParam(name = "jdyRole", required = true)
                                         @RequestBody WaimaoTichengFahuo jdyRole){
        boolean save = waimaoTichengFahuoService.save(jdyRole);
        if(save){
            return ResultVo.success(jdyRole);
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除外贸发货信息")
    @DeleteMapping("/deleteTichengFahuo/{id}")
    public ResultVo deleteTichengFahuo(@ApiParam(value = "id", name = "角色ID", required = true) @PathVariable String  id){
        boolean b = waimaoTichengFahuoService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("计算之前详细展示")
    @PostMapping("/detailslist")
    public ResultVo detailslist(@ApiParam(name = "list", required = false)
                                @RequestBody List<String> list) throws Exception {
      Map<String,Float> map =new HashMap<>();
      Map<String,Float> maphui =new HashMap<>();
      List<WaimaoTichengFahuoDto> list1 =new ArrayList<>();
      List<WaimaoTichengFahuoDto> lists = waimaoTichengFahuoService.seleteInFaHui(list);
        for(int i = 0; i < lists.size(); i++){
            if(lists.get(i).getWaimaoTichengBaozhuang().getBzw() !=null && lists.get(i).getWaimaoTichengJijiabiao().getFirst() !=null ){
                if(map.containsKey(lists.get(i).getFapiaohao())){
                    map.put(lists.get(i).getFapiaohao(),map.get(lists.get(i).getFapiaohao())+Float.valueOf(lists.get(i).getJiashuiheji()));
                }else{
                    map.put(lists.get(i).getFapiaohao(),Float.valueOf(lists.get(i).getJiashuiheji()));
                }
                for (int j = 0; j <lists.get(i).getHuikuanList().size(); j++){
                    if(maphui.containsKey(lists.get(i).getHuikuanList().get(j).getFapiaohao())){
                        maphui.put(lists.get(i).getHuikuanList().get(j).getFapiaohao(),maphui.get(lists.get(i).getHuikuanList().get(j).getFapiaohao())+Float.valueOf(lists.get(i).getHuikuanList().get(j).getHuikuanjine()));
                    }else{
                        maphui.put(lists.get(i).getHuikuanList().get(j).getFapiaohao(),Float.valueOf(lists.get(i).getHuikuanList().get(j).getHuikuanjine()));
                    }
                }
            }
        }
        for (Map.Entry<String, Float> m : map.entrySet()) {
            log.info("key的值为:" + m.getKey() + "； value的值为:" + m.getValue());
            if(m.getValue() > maphui.get(m.getKey())){
                map.remove(m.getKey());
            }
        }
        for (int i=0;i<lists.size(); i++){
            if(map.containsKey(lists.get(i).getFapiaohao())){
                list1.add(lists.get(i));
            }
        }
        return ResultVo.success(list1);
    }

    @ApiOperation("进行批量计算")
    @PostMapping("/list")
    public ResultVo addjijia2post(@ApiParam(name = "list", required = false)
                                  @RequestBody List<WaimaoTichengFahuoDto> fahuoDtoList) throws Exception {
        List<WaimaoTichengHuikuan> huikuanlist = new ArrayList<>();
        for (int j = 0; j < fahuoDtoList.size(); j++) {
            double huikuanlv = 0;
            double shouticheng = 0; //提成
            double xinkehu = 0;  //新客户提成
            long y = 0;  //计算天数
            double yongjien = 0; //佣金
            double jin = 0;  //回款金额
            double jingshui = 0; // 价税合计
            double jingshui1 = 0; //净水销售额
            for (int h = 0; h < fahuoDtoList.get(j).getHuikuanList().size(); h++) {
                if (Float.valueOf(fahuoDtoList.get(j).getJiashuiheji()) - Float.valueOf(fahuoDtoList.get(j).getHuikuanList().get(h).getHuikuanjine()) <= 60) {
                    double ji = 0; //回款实际使用金额
                    jin = Float.valueOf(fahuoDtoList.get(j).getHuikuanList().get(h).getHuikuanjine()) + jin;
                    fahuoDtoList.get(j).getHuikuanList().get(h).setBiaoshi(fahuoDtoList.get(j).getHuikuanList().get(h).getBiaoshi() + ',' + fahuoDtoList.get(j).getId());
                    if (Float.valueOf(fahuoDtoList.get(j).getJiashuiheji()) <= jin) {
                        double shijishiyong = Float.valueOf(fahuoDtoList.get(j).getHuikuanList().get(h).getHuikuanjine()) - (jin - Float.valueOf(fahuoDtoList.get(j).getJiashuiheji())); // 实际使用
                        fahuoDtoList.get(j).getHuikuanList().get(h).setShijishiyong(fahuoDtoList.get(j).getHuikuanList().get(h).getShijishiyong() + "," + shijishiyong);
                        fahuoDtoList.get(j).getHuikuanList().get(h).setHuikuanjine((jin - Float.valueOf(fahuoDtoList.get(j).getJiashuiheji())) + "");  // 剩余的回款金额
                    } else {
                        fahuoDtoList.get(j).getHuikuanList().get(h).setShijishiyong(fahuoDtoList.get(j).getHuikuanList().get(h).getShijishiyong() + (Float.valueOf(fahuoDtoList.get(j).getHuikuanList().get(h).getHuikuanjine()) + Float.valueOf(fahuoDtoList.get(j).getJiashuiheji()) - jin));
                        fahuoDtoList.get(j).getHuikuanList().get(h).setHuikuanjine("0");
                    }
                    //净水销售额
                    if (fahuoDtoList.get(j).getBizhong() == "人民币") {
                        jingshui = Float.valueOf(fahuoDtoList.get(j).getFobzongjia()) / 1.13 - Float.valueOf(fahuoDtoList.get(j).getWaimaoTichengBaozhuang().getBaozhuangjia()) * Float.valueOf(fahuoDtoList.get(j).getShuliang()) - Float.valueOf(fahuoDtoList.get(j).getYunshujine());
                    } else {
                        jingshui = Float.valueOf(fahuoDtoList.get(j).getFobzongjia()) * Float.valueOf(fahuoDtoList.get(j).getWaimaoTichengHuilv().getBili()) - Float.valueOf(fahuoDtoList.get(j).getWaimaoTichengBaozhuang().getBaozhuangjia()) * Float.valueOf(fahuoDtoList.get(j).getShuliang()) - Float.valueOf(fahuoDtoList.get(j).getYunshujine());
                    }
                    jingshui1 = jingshui / Float.valueOf(fahuoDtoList.get(j).getShuliang());
                    if (!fahuoDtoList.get(j).getYongjin().isEmpty()) {
                        yongjien = Float.valueOf(fahuoDtoList.get(j).getYongjin());
                    }
                    jingshui1 = jingshui1 - yongjien;
                    y = CommonUtils.getDistanceDays(fahuoDtoList.get(j).getHuikuanList().get(h).getHuikuanriqi(), fahuoDtoList.get(j).getYujizhuangtime());
                    if (fahuoDtoList.get(j).getYewuyuan().equals("孙少彬") || fahuoDtoList.get(j).getYewuyuan().equals("郑浩亮")) {
                        y = y + 25;
                    } else if (fahuoDtoList.get(j).getYewuyuan().equals("朱梦玺")) {
                        y = y + 25;
                    }
                    if (fahuoDtoList.get(j).getShifouxinkehu().equals("新客户") || fahuoDtoList.get(j).getShifouxinkehu().equals("新产品")) {
                        if (fahuoDtoList.get(j).getShifouxinkehu().equals("新产品")) {
                            xinkehu = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.01;
                        } else {
                            xinkehu = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.003;
                        }
                    } else {
                        xinkehu = 0;
                    }
                    if (jingshui1 >= (Float.valueOf(fahuoDtoList.get(j).getWaimaoTichengJijiabiao().getFifth()) + Float.valueOf(fahuoDtoList.get(j).getWaimaoTichengJijiabiao().getFifth()) * 0.05)) {
                        if (y <= 0) {
                            shouticheng = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.006;
                            huikuanlv = 0.006;
                        } else if (y >= 1 && y <= 30) {
                            shouticheng = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.005;
                            huikuanlv = 0.005;
                        } else if (y >= 31 && y <= 60) {
                            shouticheng = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.004;
                            huikuanlv = 0.004;
                        } else if (y >= 61 && y <= 90) {
                            shouticheng = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.003;
                            huikuanlv = 0.003;
                        } else if (y >= 91 && y <= 105) {
                            shouticheng = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.002;
                            huikuanlv = 0.002;
                        } else {
                            // 大于等于106的情况
                            shouticheng = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.001;
                            huikuanlv = 0.001;
                        }
                    } else if (jingshui1 >= (Float.valueOf(fahuoDtoList.get(j).getWaimaoTichengJijiabiao().getFourthly()) + Float.valueOf(fahuoDtoList.get(j).getWaimaoTichengJijiabiao().getFourthly()) * 0.05) && jingshui1 < (Float.valueOf(fahuoDtoList.get(j).getWaimaoTichengJijiabiao().getFifth()) + Float.valueOf(fahuoDtoList.get(j).getWaimaoTichengJijiabiao().getFourthly()) * 0.05)) {
                        if (y <= 0) {
                            shouticheng = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.005;
                            huikuanlv = 0.005;
                        } else if (y >= 1 && y <= 30) {
                            shouticheng = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.0045;
                            huikuanlv = 0.0045;
                        } else if (y >= 31 && y <= 60) {
                            shouticheng = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.0035;
                            huikuanlv = 0.0035;
                        } else if (y >= 61 && y <= 90) {
                            shouticheng = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.0025;
                            huikuanlv = 0.0025;
                        } else if (y >= 91 && y <= 105) {
                            shouticheng = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.0015;
                            huikuanlv = 0.0015;
                        } else {
                            // 大于等于106的情况
                            shouticheng = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.0008;
                            huikuanlv = 0.0008;
                        }
                    } else if (jingshui1 >= (Float.valueOf(fahuoDtoList.get(j).getWaimaoTichengJijiabiao().getThirdly()) + Float.valueOf(fahuoDtoList.get(j).getWaimaoTichengJijiabiao().getThirdly()) * 0.05) && jingshui1 < (Float.valueOf(fahuoDtoList.get(j).getWaimaoTichengJijiabiao().getFifth()) + Float.valueOf(fahuoDtoList.get(j).getWaimaoTichengJijiabiao().getFourthly()) * 0.05)) {
                        if (y <= 0) {
                            shouticheng = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.004;
                            huikuanlv = 0.004;
                        } else if (y >= 1 && y <= 30) {
                            shouticheng = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.0035;
                            huikuanlv = 0.0035;
                        } else if (y >= 31 && y <= 60) {
                            shouticheng = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.0025;
                            huikuanlv = 0.0025;
                        } else if (y >= 61 && y <= 90) {
                            shouticheng = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.0015;
                            huikuanlv = 0.0015;
                        } else if (y >= 91 && y <= 105) {
                            shouticheng = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.001;
                            huikuanlv = 0.001;
                        } else {
                            // 大于等于106的情况
                            shouticheng = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.0005;
                            huikuanlv = 0.0005;
                        }
                    } else if (jingshui1 >= (Float.valueOf(fahuoDtoList.get(j).getWaimaoTichengJijiabiao().getSecond()) + Float.valueOf(fahuoDtoList.get(j).getWaimaoTichengJijiabiao().getSecond()) * 0.05) && jingshui1 < (Float.valueOf(fahuoDtoList.get(j).getWaimaoTichengJijiabiao().getThirdly()) + Float.valueOf(fahuoDtoList.get(j).getWaimaoTichengJijiabiao().getThirdly()) * 0.05)) {

                        if (y <= 0) {
                            shouticheng = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.003;
                            huikuanlv = 0.003;
                        } else if (y >= 1 && y <= 30) {
                            shouticheng = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.0025;
                            huikuanlv = 0.0025;
                        } else if (y >= 31 && y <= 60) {
                            shouticheng = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.0015;
                            huikuanlv = 0.0015;
                        } else if (y >= 61 && y <= 90) {
                            shouticheng = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.001;
                            huikuanlv = 0.0001;
                        } else if (y >= 91 && y <= 105) {
                            shouticheng = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.0005;
                            huikuanlv = 0.0005;
                        } else {
                            // 大于等于106的情况
                            shouticheng = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.0003;
                            huikuanlv = 0.0003;
                        }
                    } else if (jingshui1 >= (Float.valueOf(fahuoDtoList.get(j).getWaimaoTichengJijiabiao().getFirst()) + Float.valueOf(fahuoDtoList.get(j).getWaimaoTichengJijiabiao().getFirst()) * 0.05) && jingshui1 < (Float.valueOf(fahuoDtoList.get(j).getWaimaoTichengJijiabiao().getSecond()) + Float.valueOf(fahuoDtoList.get(j).getWaimaoTichengJijiabiao().getSecond()) * 0.05)) {
                        if (y <= 0) {
                            shouticheng = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.002;
                            huikuanlv = 0.002;
                        } else if (y >= 1 && y <= 30) {
                            shouticheng = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.0015;
                            huikuanlv = 0.0015;
                        } else if (y >= 31 && y <= 60) {
                            shouticheng = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.001;
                            huikuanlv = 0.001;
                        } else if (y >= 61 && y <= 90) {
                            shouticheng = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.0005;
                            huikuanlv = 0.0005;
                        } else if (y >= 91 && y <= 105) {
                            shouticheng = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.0003;
                            huikuanlv = 0.0003;
                        } else {
                            // 大于等于106的情况
                            shouticheng = jingshui1 * fahuoDtoList.get(j).getShuliang() * 0;
                            huikuanlv = 0;
                        }
                    } else {
                        shouticheng = 0;
                        huikuanlv = 0;
                    }
                    switch (fahuoDtoList.get(j).getShouhuokehu()) {
                        case "JINDAN EUROPE B.V.":
                            shouticheng = shouticheng * 0.5;
                            break;
                        case "UD CHEMIE GMBH":
                            shouticheng = shouticheng * 0.5;
                            break;
                        case "Danisco Malaysia Sdn Bhd":
                            shouticheng = shouticheng * 0.5;
                            break;
                        case "OOO “KhimVneshTorg”":
                            shouticheng = shouticheng * 0.5;
                            break;
                        case "木沙系诺":
                            shouticheng = shouticheng * 0.5;
                            break;
                        case "武藏野":
                            shouticheng = shouticheng * 0.5;
                            break;
                    }
                    //正则表达式。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
                    String str = fahuoDtoList.get(j).getHuikuanxieyi();
                    //正则表达式，用于匹配非数字串，+号用于匹配出多个非数字串
                    String regEx = "[^0-9]+";
                    Pattern pattern = Pattern.compile(regEx);
                    //用定义好的正则表达式拆分字符串，把字符串中的数字留出来
                    int nums = 0;
                    if (str != null && !str.equals("")) {
                        String[] cs = pattern.split(str);
                        if (cs.length > 2) {
                            nums = Integer.parseInt(cs[1]);
                        } else {
                            nums = 0;
                        }
                    } else {
                        nums = 0;
                    }
                    long tianshu = CommonUtils.getDistanceDays((fahuoDtoList.get(j).getHuikuanList().get(h).getHuikuanriqi()), (fahuoDtoList.get(j).getYujizhuangtime())) - nums;
                    if (tianshu >= 15 && tianshu <= 30) {
                        shouticheng = shouticheng * 0.8;
                    }
                    if (tianshu >= 31 && tianshu <= 60) {
                        shouticheng = shouticheng * 0.5 - jingshui1 * fahuoDtoList.get(j).getShuliang() * 0.0003;
                    }
                    if (tianshu >= 61) {
                        shouticheng = -shouticheng * 0.0004;
                    }
                    fahuoDtoList.get(j).setBiaoji(fahuoDtoList.get(j).getBiaoji() + "," + fahuoDtoList.get(j).getHuikuanList().get(h).getId());
                    fahuoDtoList.get(j).setXinkehuticheng(String.valueOf(xinkehu));
                    fahuoDtoList.get(j).setYijisuan("已计算");
                    fahuoDtoList.get(j).setFobticheng(String.valueOf(shouticheng));
                    fahuoDtoList.get(j).setHuikuanlv(String.valueOf(huikuanlv));
                    fahuoDtoList.get(j).setYijisuan("已计算");
                    huikuanlist.add(fahuoDtoList.get(j).getHuikuanList().get(h));
                    break;
                } else { /// 如果回款金额不满足情况的时候调用
                    fahuoDtoList.get(j).getHuikuanList().get(h).setBiaoshi(fahuoDtoList.get(j).getHuikuanList().get(h).getBiaoshi() + "," + fahuoDtoList.get(j).getId());
                    fahuoDtoList.get(j).getHuikuanList().get(h).setShijishiyong(fahuoDtoList.get(j).getHuikuanList().get(h).getShijishiyong() + "," + fahuoDtoList.get(j).getHuikuanList().get(h).getHuikuanjine());
                    fahuoDtoList.get(j).getHuikuanList().get(h).setHuikuanjine("0");
                    fahuoDtoList.get(j).setBiaoji(fahuoDtoList.get(j).getBiaoji() + "," + fahuoDtoList.get(j).getHuikuanList().get(h).getId());
                    huikuanlist.add(fahuoDtoList.get(j).getHuikuanList().get(h));
                }
            }//回款发票号等于发货单发票号
//       进行批量跟新操作
        }
        List<WaimaoTichengFahuo> list = new ArrayList<>();
        for (int i = 0; i < fahuoDtoList.size(); i++) {
            WaimaoTichengFahuo domesticFahuo = new WaimaoTichengFahuo();
            BeanUtils.copyProperties(fahuoDtoList.get(i), domesticFahuo);
            list.add(domesticFahuo);
        }
        boolean b1 = waimaoTichengFahuoService.updateBatchById(list);
        boolean b = waimaoTichengHuikuanService.updateBatchById(huikuanlist);
        if (b1 && b) {
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

     //    查询回款
    @ApiOperation(value = "客户余额表", notes = "参数:客户余额表")
    @PostMapping(value = "/yuebiao")
    public ResultVo yuebiao( @ApiParam(name = "fahuo", required = true)
                             @RequestBody DomesticFahuo fahuo){
        List<WaimaoTichengFahuo> lisfahuo = waimaoTichengFahuoService.seleteYuebiaoFa(fahuo);
        List<WaimaoTichengHuikuan> listhuikuan = waimaoTichengHuikuanService.seleteYuebiaoHuikuan();
        Map<String,Float> map =new HashMap<>();
        for (int i = 0; i < lisfahuo.size(); i++) {
            for (int j = 0; j < listhuikuan.size() ; j++) {
                if(lisfahuo.get(i).getFapiaohao().equals(listhuikuan.get(j).getFapiaohao())){
                    map.put(lisfahuo.get(i).getFapiaohao(),Float.valueOf(lisfahuo.get(i).getJiashuiheji())-Float.valueOf(listhuikuan.get(j).getJine()));
                }
            }
        }
        for (int i = 0; i < lisfahuo.size(); i++) {
            if(!map.containsKey(lisfahuo.get(i).getFapiaohao())){
                map.put(lisfahuo.get(i).getFapiaohao(),Float.valueOf(lisfahuo.get(i).getJiashuiheji()));
            }
        }
        for (int j = 0; j < listhuikuan.size(); j++) {
            if(!map.containsKey(listhuikuan.get(j).getFapiaohao())){
                map.put(listhuikuan.get(j).getFapiaohao(),-Float.valueOf(listhuikuan.get(j).getHuikuanjine()));
            }
        }
        return ResultVo.success(map);
    }

    @ApiOperation(value = "客户余额表导出", notes = "参数:客户余额表导出")
    @PostMapping(value = "/yueexcle")
    public void yueexcle(HttpServletResponse response, DomesticFahuo  fahuo) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("数据导出");
        List<WaimaoTichengFahuo> lisfahuo = waimaoTichengFahuoService.seleteYuebiaoFa(fahuo);
        List<WaimaoTichengHuikuan> listhuikuan = waimaoTichengHuikuanService.seleteYuebiaoHuikuan();
        Map<String,Float> map =new HashMap<>();
        for (int i = 0; i < lisfahuo.size(); i++) {
            for (int j = 0; j < listhuikuan.size() ; j++) {
                if(lisfahuo.get(i).getShouhuokehu().equals(listhuikuan.get(j).getFapiaohao())){
                    map.put(lisfahuo.get(i).getShouhuokehu(),Float.valueOf(lisfahuo.get(i).getJiashuiheji())-Float.valueOf(listhuikuan.get(j).getHuikuanjine()));
                }
            }
        }
        for (int i = 0; i < lisfahuo.size(); i++) {
            if(!map.containsKey(lisfahuo.get(i).getShouhuokehu())){
                map.put(lisfahuo.get(i).getShouhuokehu(),Float.valueOf(lisfahuo.get(i).getJiashuiheji()));
            }
        }
        for (int j = 0; j < listhuikuan.size(); j++) {
            if(!map.containsKey(listhuikuan.get(j).getFapiaohao())){
                map.put(listhuikuan.get(j).getFapiaohao(),-Float.valueOf(listhuikuan.get(j).getHuikuanjine()));
            }
        }
        String fileName = "客户余额表导出"+".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = { "客户名称", "客户余额"};
        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        HSSFRow row1;
        //在表中存放查询到的数据放入对应的列
        for(Map.Entry<String, Float> maps : map.entrySet()){
            row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(maps.getKey());
            row1.createCell(1).setCellValue(maps.getValue());
            rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

}