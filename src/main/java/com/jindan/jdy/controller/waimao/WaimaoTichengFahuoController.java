package com.jindan.jdy.controller.waimao;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.WaimaoTichengFahuoDto;
import com.jindan.jdy.common.dto.WaimaoTichengFahuoTargetDto;
import com.jindan.jdy.common.pojo.*;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.CommonUtils;
import com.jindan.jdy.controller.utils.WorkbookUtils;
import com.jindan.jdy.service.waimao.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * <p>说明： 外贸提成API接口层</P>
 *
 * @version: V1.0
 * @author: kong
 * @time 2020年5月28日
 */
@Slf4j
@Api(tags = "外贸提成发货管理")
@RestController
@RequestMapping("/waimaoTichengFahuo")
public class WaimaoTichengFahuoController {

    @Autowired
    WaimaoTichengFahuoService waimaoTichengFahuoService;

    @Autowired
    WaimaoTichengHuikuanService waimaoTichengHuikuanService;

    @Autowired
    WaimaoTichengSalesTargetService waimaoTichengSalesTargetService;

    @Autowired
    WaimaoTichengJijiabiaoService waimaoTichengJijiabiaoService;

    public static List<String> bigConsumer = Arrays.asList("PURAC (Thailand) Limited", "OOO MCD pischevie dobavki", "UD CHEMIE GMBH", "Danisco Malaysia Sdn Bhd", "上海木沙系诺贸易有限公司", "PHARMCHEMPRODUCT LLC"
            , "NEO Chemical LLC", "REDOX PTY LTD", "Chemical Distribution Network","JINDAN EUROPE B.V.");

    @ApiOperation(value = "发货信息批量导入", notes = "参数:发货信息批量导入")
    @PostMapping("addBatchTichengFahuo")
    public ResultVo addTichengFahuo(@RequestParam("file") MultipartFile file) throws Exception {

        String presenttime = CommonUtils.getPresenttime();
        //创建Excel工作薄
        Workbook work = WorkbookUtils.getWorkbook(file.getInputStream(), file.getOriginalFilename());
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        log.info("work.getNumberOfSheets()的值为：" + work.getNumberOfSheets());
        Sheet sheet = work.getSheetAt(0);
        if (sheet == null) {
            throw new Exception("创建Excel工作薄为空！");
        }
        List<WaimaoTichengFahuo> jijiabiaos = new ArrayList<>();
        for (int j = 1; j <= sheet.getLastRowNum(); j++) {
            Row row = sheet.getRow(j);
            if (row.getCell(1) == null || StringUtils.isEmpty(row.getCell(1).getStringCellValue())) {
                break;
            }
            WaimaoTichengFahuo jijiabiao = new WaimaoTichengFahuo();
            if (row.getCell(0) != null) {
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setDanjuhao(row.getCell(0).getStringCellValue());
            }
            if (row.getCell(1) != null) {
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setFahuotime(row.getCell(1).getStringCellValue());
            }
            if (row.getCell(2) != null) {
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setFapiaohao(row.getCell(2).getStringCellValue());
            }
            if (row.getCell(3) != null) {
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setHetonghao((row.getCell(3).getStringCellValue()));
            }
            if (row.getCell(4) != null) {
                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setPici(row.getCell(4).getStringCellValue());
            }
            if (row.getCell(5) != null) {
                row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setYewuyuan((row.getCell(5).getStringCellValue()));
            }
            if (row.getCell(6) != null) {
                row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setShouhuokehu(row.getCell(6).getStringCellValue());
            }
            if (row.getCell(7) != null) {
                row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setDiqufenlei(row.getCell(7).getStringCellValue());
            }
            if (row.getCell(8) != null) {
                row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setYujizhuangtime(row.getCell(8).getStringCellValue());
            }
            if (row.getCell(9) != null) {
                row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setWuliaoming(row.getCell(9).getStringCellValue());
            }
            if (row.getCell(10) != null) {
                row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setShuliang(Float.valueOf(row.getCell(10).getStringCellValue()));
            }
            if (row.getCell(11) != null) {
                row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setHanshuidanjia(Float.valueOf(row.getCell(11).getStringCellValue()));
            }
            if (row.getCell(12) != null) {
                row.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setBizhong(row.getCell(12).getStringCellValue());
            }
            if (row.getCell(13) != null) {
                row.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setJiagetiaokuan(row.getCell(13).getStringCellValue());
            }
            if (row.getCell(14) != null) {
                row.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setJiashuiheji(row.getCell(14).getStringCellValue());
            }
            if (row.getCell(15) != null) {
                row.getCell(15).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setBenbijiashuiheji(row.getCell(15).getStringCellValue());
            }
            if (row.getCell(16) != null) {
                row.getCell(16).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setBaozhuangwu(row.getCell(16).getStringCellValue());
            }
            if (row.getCell(17) != null) {
                row.getCell(17).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setHuikuanxieyi(row.getCell(17).getStringCellValue());
            }
            if (row.getCell(18) != null) {
                row.getCell(18).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setYunshudanjia(row.getCell(18).getStringCellValue());
            }
            if (row.getCell(19) != null) {
                row.getCell(19).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setYunshujine(row.getCell(19).getStringCellValue());
            }
            if (row.getCell(20) != null) {
                switch (row.getCell(20).getCellType()) {
                    case HSSFCell.CELL_TYPE_NUMERIC:
                        Cell cell = row.getCell(20);
                        double huilv = cell.getNumericCellValue();
                        jijiabiao.setHuilv(huilv + "");
                        break;
                    case HSSFCell.CELL_TYPE_STRING:
                        // 转换
                        String huilvStr = row.getCell(0).getStringCellValue();
                        Float f = Float.valueOf(huilvStr);
                        BigDecimal b = new BigDecimal(f);
                        float hf = b.setScale(4, BigDecimal.ROUND_HALF_UP).floatValue();
                        jijiabiao.setHuilv(hf + "");
                        break;
                }
//                row.getCell(20).setCellType(Cell.CELL_TYPE_STRING);
//                jijiabiao.setHuilv(row.getCell(20).getStringCellValue());
            }
            if (row.getCell(21) != null) {
                row.getCell(21).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setFobzongjia(row.getCell(21).getStringCellValue());
            }
            if (row.getCell(22) != null) {
                row.getCell(22).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setHaiyunfei(row.getCell(22).getStringCellValue());
            }
            if (row.getCell(23) != null) {
                row.getCell(23).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setYongjin(row.getCell(23).getStringCellValue());
            }
            if (row.getCell(24) != null) {
                row.getCell(24).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setCeshi2(row.getCell(24).getStringCellValue());
            }
            jijiabiao.setYijisuan("未计算");
            jijiabiaos.add(jijiabiao);
        }
        waimaoTichengFahuoService.saveBatch(jijiabiaos);
        return ResultVo.success();
    }

    @ApiOperation(value = "查询提成发货信息", notes = "参数:查询包装信息")
    @PostMapping("/seleteTichengFahuo")
    public ResultVo seleteTichengFahuo(@ApiParam(value = "jdyRole", required = false)
                                       @RequestBody WaimaoTichengFahuoDto jdyRole) {
        jdyRole.setYijisuan("未计算");
        Page<WaimaoTichengFahuo> seletelist = waimaoTichengFahuoService.seletelistByPage(jdyRole);
        return ResultVo.success(seletelist);
    }

    @ApiOperation(value = "查询已计算信息", notes = "参数:发货信息")
    @PostMapping("/selectCalculatedFahuo")
    public ResultVo selectCalculatedFahuo(@ApiParam(value = "fahuoDto", required = false)
                                          @RequestBody WaimaoTichengFahuoDto fahuoDto) {
        fahuoDto.setYijisuan("已计算");
        Page<WaimaoTichengFahuo> seletelist = waimaoTichengFahuoService.seletelistByPage(fahuoDto);
        return ResultVo.success(seletelist);
    }

    @ApiOperation(value = "查询已计算详情息", notes = "参数:查询外贸发货信息")
    @GetMapping("/selectCalculatedDetail/{id}")
    public ResultVo seleteWaimaoFahuo(@PathVariable String id) throws Exception {

        log.info("查询外贸已计算信息详情 id：" + id);
        List<String> ids = new ArrayList<>();
        ids.add(id);
        List<WaimaoTichengFahuoDto> fahuoDetailList = waimaoTichengFahuoService.seleteInFaHui(ids);
        WaimaoTichengFahuoDto result = fahuoDetailList.get(0);
        return ResultVo.success(result);
    }

    @ApiOperation("更新提成发货信息")
    @PostMapping("/updateTichengFahuo")
    public ResultVo updateTichengFahuo(@ApiParam(value = "jdyRole", required = true)
                                       @RequestBody WaimaoTichengFahuo jdyRole) {
        boolean b = waimaoTichengFahuoService.updateById(jdyRole);
        if (b) {
            return ResultVo.success(jdyRole);
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增外贸提成发货信息")
    @PostMapping("/addTichengFahuo")
    public ResultVo addTichengFahuo(@ApiParam(name = "jdyRole", required = true)
                                    @RequestBody WaimaoTichengFahuo jdyRole) {
        boolean save = waimaoTichengFahuoService.save(jdyRole);
        if (save) {
            return ResultVo.success(jdyRole);
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除外贸发货信息")
    @DeleteMapping("/deleteTichengFahuo/{id}")
    public ResultVo deleteTichengFahuo(@ApiParam(value = "id", name = "角色ID", required = true) @PathVariable String id) {
        boolean b = waimaoTichengFahuoService.removeById(id);
        if (b) {
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("计算之前详细展示")
    @PostMapping("/detailslist")
    public ResultVo detailslist(@ApiParam(name = "list", required = false)
                                @RequestBody List<String> list) throws Exception {
        Map<String, Float> fahuoMap = new HashMap<>();// 发货信息  key:发票号，value:价税合计
        Map<String, Float> huikuanMap = new HashMap<>();// 回款信息   key：发票号   value：回款金额
        List<WaimaoTichengFahuoDto> fahuoDetailResult = new ArrayList<>(); // 过滤后的需要计算的发货信息
        //查询发货信息详情
        List<WaimaoTichengFahuoDto> fahuoDetailList = waimaoTichengFahuoService.seleteInFaHui(list);
        WaimaoTichengFahuoDto fahuoDetail = null;
        WaimaoTichengBaozhuang baozhuang = null;
        WaimaoTichengJijiabiao jijiabao = null;
        WaimaoTichengHuikuan huikuan = null;
        for (int i = 0; i < fahuoDetailList.size(); i++) {
            fahuoDetail = fahuoDetailList.get(i);
            baozhuang = fahuoDetail.getWaimaoTichengBaozhuang();//包装物
            jijiabao = fahuoDetail.getWaimaoTichengJijiabiao();//基价表
            if (baozhuang == null) {
                return ResultVo.failed(fahuoDetail.getBaozhuangwu() + ":包装信息不能为空！");
            }
            if (jijiabao == null) {
                return ResultVo.failed(fahuoDetail.getWuliaoming() + ":基价数据信息不能为空！");
            }
            List<WaimaoTichengHuikuan> huikuanList = fahuoDetail.getHuikuanList();
            if (baozhuang.getBzw() != null && StringUtils.isNotEmpty(jijiabao.getFirst())) {
                // 累加相同发票号的 价税合计值
                if (fahuoMap.containsKey(fahuoDetail.getFapiaohao())) {
                    fahuoMap.put(fahuoDetail.getFapiaohao(), fahuoMap.get(fahuoDetail.getFapiaohao()) + Float.valueOf(fahuoDetail.getJiashuiheji()));
                } else {
                    fahuoMap.put(fahuoDetail.getFapiaohao(), Float.valueOf(fahuoDetail.getJiashuiheji()));
                }
                for (int j = 0; j < huikuanList.size(); j++) {
                    huikuan = huikuanList.get(j);
                    // 累加相同发票号的回款金额
                    if (huikuanMap.containsKey(huikuan.getFapiaohao())) {
                        huikuanMap.put(huikuan.getFapiaohao(), huikuanMap.get(huikuan.getFapiaohao()) + Float.valueOf(huikuan.getHuikuanjine()));
                    } else {
                        huikuanMap.put(huikuan.getFapiaohao(), Float.valueOf(huikuan.getHuikuanjine()));
                    }
                }
            }
        }
        // 过滤掉回款金额不够的发票号
        Iterator<Map.Entry<String, Float>> iterator = fahuoMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Float> m = iterator.next();
            Float huikuanjine = huikuanMap.get(m.getKey());
            if (huikuanjine != null) {
                log.info("外贸发货信息--发票号:" + m.getKey() + "； 价税合计金额:" + m.getValue() + "；回款金额：" + huikuanjine.toString());
                if (m.getValue() > huikuanjine) {// 如果价税合计金额 > 回款金额，说明回款不够，则不作计算
                    iterator.remove();
                }
            } else {
                log.info("外贸发货信息--发票号:" + m.getKey() + "；没有对应的回款信息，不作计算！");
            }
        }

        for (int i = 0; i < fahuoDetailList.size(); i++) {
            if (fahuoMap.containsKey(fahuoDetailList.get(i).getFapiaohao())) {
                fahuoDetailResult.add(fahuoDetailList.get(i));
            }
        }

        return ResultVo.success(fahuoDetailResult);
    }

    @ApiOperation("进行批量计算")
    @PostMapping("/list")
    public ResultVo addjijia2post(@ApiParam(name = "list", required = false)
                                  @RequestBody List<WaimaoTichengFahuoDto> fahuoDtoList) throws Exception {

        try {
//            List<String> ids = new ArrayList<>();
//            ids.add("1");
//            ids.add("2");
//            ids.add("3");
//            ids.add("4");
//            ids.add("6");
//            ids.add("22");
//            ids.add("24");
//            ids.add("44");
//            ids.add("65");
//            ids.add("69");
//            ids.add("70");
//            fahuoDtoList = waimaoTichengFahuoService.seleteInFaHui(ids);
            // 不能按照页面传过来的数据统计业务员每月的发货数量，页面传过来的数量可能不完整，要从数据库统计
            List<WaimaoTichengFahuoTargetDto> waimaoTichengFahuos = waimaoTichengFahuoService.selectFahuoShuliang();
            //遍历发货单，统计每个业务员每个月的发货总数量
            Map<String, Map<String, Float>> yewuyuanMap = new HashMap<>();// key:业务员名称，value：<key:日期，value：数量>
            for (WaimaoTichengFahuoTargetDto waimaoTichengFahuoDto : waimaoTichengFahuos) {
                String yewuyuanName = waimaoTichengFahuoDto.getYewuyuan();
                if (!yewuyuanMap.containsKey(yewuyuanName)) {
                    yewuyuanMap.put(yewuyuanName, new HashMap<>());
                }
                Map<String, Float> countMap = yewuyuanMap.get(yewuyuanName);
                String fahuoDate = waimaoTichengFahuoDto.getFahuotime().substring(0, 7);// 截取到年月 2021-08
                Float shuliang = waimaoTichengFahuoDto.getShuliang();
                if (countMap.containsKey(fahuoDate)) {
                    countMap.put(fahuoDate, countMap.get(fahuoDate) + shuliang);
                } else {
                    countMap.put(fahuoDate, shuliang);
                }
            }

            // 将业务员发货数量信息 按月累加
            waimaoTichengFahuoService.sumFahuoshuliang(yewuyuanMap);
            log.info("!!!!!!!!!!!!!" + JSONObject.toJSONString(yewuyuanMap));

            //从数据库中取出销售目标数据
            List<WaimaoTichengSalesTarget> salesTargetList = waimaoTichengSalesTargetService.list();
            //将salesTargetList转成map结构
            Map<String, Map<String, Float>> salesTargetMap = new HashMap<>();// key:业务员名称，value：<key:日期，value：数量>
            for (WaimaoTichengSalesTarget salesTarget : salesTargetList) {
                if (!salesTargetMap.containsKey(salesTarget.getSalesman())) {
                    salesTargetMap.put(salesTarget.getSalesman(), new HashMap<>());
                }
                Map<String, Float> countMap = salesTargetMap.get(salesTarget.getSalesman());
                String date = salesTarget.getTargetDate();
                countMap.put(date, Float.valueOf(salesTarget.getTarget()));
            }

            // 将业务员发货目标数量信息 按月累加
            waimaoTichengFahuoService.sumTargetshuliang(salesTargetMap);
            log.info("--------------" + JSONObject.toJSONString(salesTargetMap));


            // 以上统计完每个业务员每月的数量完成率，后面计算提成要用到。

            for (int j = 0; j < fahuoDtoList.size(); j++) {
                double huikuanlv = 0;
                double shouticheng = 0; //提成
                double xinkehu = 0;  //新客户提成
                long y = 0;  //计算天数
                double yongjien = 0; //佣金
                double jin = 0;  //回款金额
                double jingshui = 0; // 价税合计
                double jingshui1 = 0; //净水销售额
                boolean flag = false;
                String calFormula = ""; // 存放计算公式，放在发货表ceshi2字段
                WaimaoTichengFahuoDto fahuo = fahuoDtoList.get(j);
                WaimaoTichengBaozhuang baozhuang = fahuo.getWaimaoTichengBaozhuang();
                WaimaoTichengJijiabiao jijiabiao = fahuo.getWaimaoTichengJijiabiao();
                String titleName = jijiabiao.getTitlename();
                if ("优质级乳酸80%".equals(jijiabiao.getTitlename())) {
                    titleName = "精制级乳酸80%";
                } else if ("优质级乳酸85%".equals(jijiabiao.getTitlename())) {
                    titleName = "精制级乳酸85%";
                } else if ("优质级乳酸88%".equals(jijiabiao.getTitlename())) {
                    titleName = "精制级乳酸88%";
                } else if ("优质级乳酸90%".equals(jijiabiao.getTitlename())) {
                    titleName = "精制级乳酸90%";
                } else if ("优质级乳酸50%".equals(jijiabiao.getTitlename())) {
                    titleName = "精制级乳酸80%";
                } else if ("优质级乳酸92%".equals(jijiabiao.getTitlename())) {
                    titleName = "精制级乳酸92%";
                }
                WaimaoTichengJijiabiao jijiabiaoParam = new WaimaoTichengJijiabiao();
                jijiabiaoParam.setTitlename(titleName);
                jijiabiao = waimaoTichengJijiabiaoService.seletelist(jijiabiaoParam).get(0);
                List<WaimaoTichengHuikuan> huikuanlist = waimaoTichengHuikuanService.selectHuikuanByFaopiaohao(fahuo.getFapiaohao());
                List<WaimaoTichengHuikuan> updateHuikuanList = new ArrayList<>();
                for (int h = 0; h < huikuanlist.size(); h++) {
                    jin = Float.valueOf(huikuanlist.get(h).getHuikuanjine()) + jin;
                    if (Float.valueOf(fahuo.getJiashuiheji()) - jin <= 60) {
                        huikuanlist.get(h).setBiaoshi(huikuanlist.get(h).getBiaoshi() + ',' + fahuo.getId());
                        if (Float.valueOf(fahuo.getJiashuiheji()) <= jin) {
                            double shijishiyong = Float.valueOf(huikuanlist.get(h).getHuikuanjine()) - (jin - Float.valueOf(fahuo.getJiashuiheji())); // 实际使用
                            huikuanlist.get(h).setShijishiyong(huikuanlist.get(h).getShijishiyong() + "," + shijishiyong);
                            huikuanlist.get(h).setHuikuanjine((jin - Float.valueOf(fahuo.getJiashuiheji())) + "");  // 剩余的回款金额
                        } else {
                            double shijishiyong = (Float.valueOf(huikuanlist.get(h).getHuikuanjine()) + Float.valueOf(fahuo.getJiashuiheji()) - jin);
                            huikuanlist.get(h).setShijishiyong(huikuanlist.get(h).getShijishiyong() + "," + shijishiyong);
                            huikuanlist.get(h).setHuikuanjine("0");
                        }
                        //净水销售额
                        if ("人民币".equals(fahuo.getBizhong())) {
                            // （价税合计-海运费-佣金）/ 1.13 - 运输 - 包装
                            jingshui = (Float.valueOf(fahuo.getJiashuiheji()) - Float.valueOf(fahuo.getHaiyunfei()) - Float.valueOf(fahuo.getYongjin())*Float.valueOf(fahuo.getShuliang())) / 1.13
                                    - Float.valueOf(baozhuang.getBaozhuangjia()) * Float.valueOf(fahuo.getShuliang())
                                    - Float.valueOf(fahuo.getYunshujine());
                            calFormula = "(" + Float.valueOf(fahuo.getJiashuiheji()) + " - " + Float.valueOf(fahuo.getHaiyunfei()) + " - " + Float.valueOf(fahuo.getYongjin()) +"*"+fahuo.getShuliang() +") / 1.13 - "
                                    + Float.valueOf(baozhuang.getBaozhuangjia()) + " * " + Float.valueOf(fahuo.getShuliang())
                                    + " - " + Float.valueOf(fahuo.getYunshujine());
                        } else {
                            // （价税合计-海运费-佣金）* 汇率 - 运输 - 包装
                            jingshui = (Float.valueOf(fahuo.getJiashuiheji()) - Float.valueOf(fahuo.getHaiyunfei()) - Float.valueOf(fahuo.getYongjin())*Float.valueOf(fahuo.getShuliang())) * Float.valueOf(fahuo.getHuilv())
                                    - Float.valueOf(baozhuang.getBaozhuangjia()) * Float.valueOf(fahuo.getShuliang())
                                    - Float.valueOf("".equals(fahuo.getYunshujine()) ? "0" : fahuo.getYunshujine());
                            calFormula = "(" + Float.valueOf(fahuo.getJiashuiheji()) + " - " + Float.valueOf(fahuo.getHaiyunfei()) + " - " + Float.valueOf(fahuo.getYongjin())
                                    +"*"+fahuo.getShuliang() + ") * " + Float.valueOf(fahuo.getHuilv()) + " - " + Float.valueOf(baozhuang.getBaozhuangjia()) + " * " + Float.valueOf(fahuo.getShuliang())
                                    + " - " + Float.valueOf("".equals(fahuo.getYunshujine()) ? "0" : fahuo.getYunshujine());
                        }

                        jingshui1 = jingshui / Float.valueOf(fahuo.getShuliang());
//                        calFormula = "(" + calFormula + ") / " + Float.valueOf(fahuo.getShuliang());
//                        if (!fahuo.getYongjin().isEmpty()) {
//                            yongjien = Float.valueOf(fahuo.getYongjin());
//                        }
//                        jingshui1 = jingshui1;
//                        calFormula += " - " + yongjien;
                        y = CommonUtils.getDistanceDays(huikuanlist.get(h).getHuikuanriqi(), fahuo.getYujizhuangtime());
                        if (fahuo.getYewuyuan().equals("孙少彬") || fahuo.getYewuyuan().equals("郑浩亮")) {
                            y = y + 25;
                        } else if (fahuo.getYewuyuan().equals("朱梦玺")) {
                            y = y + 25;
                        }
                        if ("新客户".equals(fahuo.getShifouxinkehu()) || "新产品".equals(fahuo.getShifouxinkehu())) {
                            if (fahuo.getShifouxinkehu().equals("新产品")) {
                                xinkehu = jingshui1 * fahuo.getShuliang() * 0.01;
                            } else {
                                xinkehu = jingshui1 * fahuo.getShuliang() * 0.003;
                            }
                        } else {
                            xinkehu = 0;
                        }
                        if (jingshui1 >= (Float.valueOf(jijiabiao.getFifth()))) {
                            if (y <= 0) {
                                shouticheng = jingshui1 * fahuo.getShuliang() * 0.006;
                                huikuanlv = 0.006;
                            } else if (y >= 1 && y <= 30) {
                                shouticheng = jingshui1 * fahuo.getShuliang() * 0.0057;
                                huikuanlv = 0.0057;
                            } else if (y >= 31 && y <= 60) {
                                shouticheng = jingshui1 * fahuo.getShuliang() * 0.0055;
                                huikuanlv = 0.0055;
                            } else if (y >= 61 && y <= 90) {
                                shouticheng = jingshui1 * fahuo.getShuliang() * 0.005;
                                huikuanlv = 0.005;
                            } else {
                                // 大于等于90的情况
                                shouticheng = jingshui1 * fahuo.getShuliang() * 0.0048;
                                huikuanlv = 0.0048;
                            }
                        } else if (jingshui1 >= (Float.valueOf(jijiabiao.getFourthly())) && jingshui1 < (Float.valueOf(jijiabiao.getFifth()))) {
                            if (y <= 0) {
                                shouticheng = jingshui1 * fahuo.getShuliang() * 0.004;
                                huikuanlv = 0.004;
                            } else if (y >= 1 && y <= 30) {
                                shouticheng = jingshui1 * fahuo.getShuliang() * 0.0037;
                                huikuanlv = 0.0037;
                            } else if (y >= 31 && y <= 60) {
                                shouticheng = jingshui1 * fahuo.getShuliang() * 0.0035;
                                huikuanlv = 0.0035;
                            } else if (y >= 61 && y <= 90) {
                                shouticheng = jingshui1 * fahuo.getShuliang() * 0.003;
                                huikuanlv = 0.003;
                            } else {
                                // 大于等于91的情况
                                shouticheng = jingshui1 * fahuo.getShuliang() * 0.0028;
                                huikuanlv = 0.0028;
                            }
                        } else if (jingshui1 >= (Float.valueOf(jijiabiao.getThirdly())) && jingshui1 < (Float.valueOf(jijiabiao.getFourthly()))) {
                            if (y <= 0) {
                                shouticheng = jingshui1 * fahuo.getShuliang() * 0.003;
                                huikuanlv = 0.003;
                            } else if (y >= 1 && y <= 30) {
                                shouticheng = jingshui1 * fahuo.getShuliang() * 0.0027;
                                huikuanlv = 0.0027;
                            } else if (y >= 31 && y <= 60) {
                                shouticheng = jingshui1 * fahuo.getShuliang() * 0.0025;
                                huikuanlv = 0.0025;
                            } else if (y >= 61 && y <= 90) {
                                shouticheng = jingshui1 * fahuo.getShuliang() * 0.002;
                                huikuanlv = 0.002;
                            } else {
                                // 大于等于91的情况
                                shouticheng = jingshui1 * fahuo.getShuliang() * 0.0018;
                                huikuanlv = 0.0018;
                            }
                        } else {
                            shouticheng = 0;
                            huikuanlv = 0;
                        }
                        calFormula = "(" + calFormula + ")" + " * " + huikuanlv;
                        // 计算业务员本月销售目标完成率
                        //业务需求：累计数量达成率100%(含) 才能享受全额提成;累计数量达成在100%-85%(含), 同比列折扣;累计数量达成在85%-75%(含),按20%折扣; 累计数量达成在75-65%按按30%折扣;累计数量达成在65%-50%(含)按50%折扣;累计数量达成在50%以下,不再享受当月提成;任何时候不再补发.
                        Map<String, Float> countMap = yewuyuanMap.get(fahuo.getYewuyuan());
                        Float totalCount = countMap.get(fahuo.getFahuotime().substring(0, 7));// 获取当前业务员的当月完成的数量
                        Float rate = 1f; // 销售目标完成率
                        // 以下是为了判断，销售目标数据没有配置的情况下，不计算业务员的完成率
                        if (salesTargetMap.containsKey(fahuo.getYewuyuan())) {
                            Map<String, Float> stringFloatMap = salesTargetMap.get(fahuo.getYewuyuan());
                            String date = fahuo.getFahuotime().substring(0, 7);
                            if (!stringFloatMap.containsKey(date)) {
                                rate = 1f;
                            } else {
                                rate = totalCount / stringFloatMap.get(date);
                            }
                        }
                        Float result = 1f;
                        if (0.85 <= rate && rate < 1) {
                            result = rate;
                        } else if (rate >= 0.75 && rate < 0.85) {
                            result = 0.75f;
                        } else if (rate >= 0.65 && rate < 0.75) {
                            result = 0.65f;
                        } else if (rate >= 0.5 && rate < 0.65) {
                            result = 0.5f;
                        } else if (rate < 0.5f) {
                            result = 0f;
                        }
                        BigDecimal resultB = new BigDecimal(result);
                        result = resultB.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
                        shouticheng = shouticheng * result;
                        calFormula += "*" + result;
                        // 判断是否大客户
                        if (bigConsumer.contains(fahuo.getShouhuokehu())) {
                            shouticheng = fahuo.getShuliang() * 10;
                            calFormula = fahuo.getShuliang() + " * 10";
                        }
                        log.info(fahuo.getDanjuhao() + "----------------" + shouticheng);
                        BigDecimal b = new BigDecimal(shouticheng);
                        shouticheng = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        b = new BigDecimal(rate);
                        rate = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
                        fahuo.setBiaoji(fahuo.getBiaoji() + "," + huikuanlist.get(h).getId());
                        fahuo.setXinkehuticheng(String.valueOf(xinkehu));
                        fahuo.setYijisuan("已计算");
                        fahuo.setCeshi1(rate + "");// 保存业务员的完成率
                        fahuo.setFobticheng(shouticheng + "");
                        fahuo.setHuikuanlv(String.valueOf(huikuanlv));
                        fahuo.setCeshi2(calFormula);
                        waimaoTichengHuikuanService.updateById(huikuanlist.get(h));
                        flag = true;
                        break;
                    } else { /// 如果回款金额不满足情况的时候调用
                        huikuanlist.get(h).setBiaoshi(huikuanlist.get(h).getBiaoshi() + "," + fahuo.getId());
                        huikuanlist.get(h).setShijishiyong(huikuanlist.get(h).getShijishiyong() + "," + huikuanlist.get(h).getHuikuanjine());
                        huikuanlist.get(h).setHuikuanjine("0");
                        fahuo.setBiaoji(fahuo.getBiaoji() + "," + huikuanlist.get(h).getId());
                        updateHuikuanList.add(huikuanlist.get(h));
                        continue;
                    }
                }//回款发票号等于发货单发票号
                if (flag) {
                    WaimaoTichengFahuo domesticFahuo = new WaimaoTichengFahuo();
                    BeanUtils.copyProperties(fahuo, domesticFahuo);
                    waimaoTichengFahuoService.updateById(domesticFahuo);
                    if (CollectionUtils.isNotEmpty(updateHuikuanList)) {
                        waimaoTichengHuikuanService.updateBatchById(updateHuikuanList);
                    }
                }
            }
            return ResultVo.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultVo.failed();

    }

    private Float getTarget(Map<String, Float> countMap,String fahuoTime){
        //  fahuoTime    2021-09-11
        String time = fahuoTime.substring(0, 7);
        String year = time.substring(0,4);
        String month = time.substring(5,7);
        if("01".equals(month) || "02".equals(month) || "03".equals(month)){
            return countMap.get(year+"-03");
        }else if("04".equals(month) || "05".equals(month) || "06".equals(month)){
            return countMap.get(year+"-06");
        }else if("07".equals(month) || "08".equals(month) || "09".equals(month)){
            return countMap.get(year+"-09");
        }else if("10".equals(month) || "11".equals(month) || "12".equals(month)){
            return countMap.get(year+"-12");
        }
        log.info("获取目标销量比出错了！！！！！！！！！！请核查！");
        return 0f;
    }

//    public static void main(String[] args) {
//        WaimaoTichengFahuoController waimao = new WaimaoTichengFahuoController();
//        Map<String, Float> countMap = new HashMap<>();
//        countMap.put("2021-01",100f);
//        countMap.put("2021-02",200f);
//        countMap.put("2021-03",300f);
//        countMap.put("2021-04",100f);
//        countMap.put("2021-05",200f);
//        countMap.put("2021-06",300f);
//        countMap.put("2021-07",100f);
//        countMap.put("2021-08",200f);
//        countMap.put("2021-09",300f);
//        countMap.put("2021-10",100f);
//        countMap.put("2021-11",200f);
//        countMap.put("2021-12",300f);
//        Float target = waimao.getTarget(countMap, "2021-10");
//        System.out.println(target);
//    }

    /**
     * 删除已计算
     *
     * @return
     */
    @ApiOperation(value = "已计算根据ID进行删除为未计算", notes = "参数:发货表表id")
    @GetMapping("/delcalculated/{id}")
    public ResultVo deleteCalculated(@PathVariable String id) {
        log.info("外贸删除已计算 id：" + id);
        if (StringUtils.isNotEmpty(id)) {
            WaimaoTichengFahuo waimaoTichengFahuo = waimaoTichengFahuoService.getById(id);
            List<WaimaoTichengHuikuan> waimaoTichengHuikuans = waimaoTichengHuikuanService.selectHuikuanByBiaoshi(id);
            for (WaimaoTichengHuikuan waimaoTichengHuikuan : waimaoTichengHuikuans) {
                String biaoshi = waimaoTichengHuikuan.getBiaoshi();
                String shijishiyong = waimaoTichengHuikuan.getShijishiyong();
                String[] biaoshiArr = biaoshi.split(",");
                String[] shijishiyongArr = shijishiyong.split(",");
                if (biaoshiArr.length == shijishiyongArr.length) {
                    for (int i = 0; i < biaoshiArr.length; i++) {
                        if (id.equals(biaoshiArr[i])) {
                            waimaoTichengHuikuan.setHuikuanjine(String.valueOf(Float.valueOf(waimaoTichengHuikuan.getHuikuanjine()) + Float.valueOf(shijishiyongArr[i])));
                            biaoshiArr[i] = "0";
                            shijishiyongArr[i] = "0";
                        }
                    }
                    String biaoshiTemp = "";
                    String shijishiyongTemp = "";
                    for (int k = 0; k < biaoshiArr.length; k++) {
                        if (!biaoshiArr[k].equals("0")) {
                            biaoshiTemp += biaoshiArr[k] + ",";
                            shijishiyongTemp += shijishiyongArr[k] + ",";
                        }
                    }
                    waimaoTichengHuikuan.setBiaoshi(biaoshiTemp);
                    waimaoTichengHuikuan.setShijishiyong(shijishiyongTemp);
                }
                waimaoTichengHuikuanService.updateById(waimaoTichengHuikuan);
            }
            waimaoTichengFahuo.setBiaoji("");
            waimaoTichengFahuo.setYijisuan("未计算");
            waimaoTichengFahuo.setFobticheng("");
            waimaoTichengFahuo.setXinkehuticheng("");
            waimaoTichengFahuo.setHuikuanlv("");
            waimaoTichengFahuo.setCeshi1("");
            waimaoTichengFahuo.setCeshi2("");
            waimaoTichengFahuoService.updateById(waimaoTichengFahuo);
            return ResultVo.success();
        }
        return ResultVo.failed("id值不能为空！");
    }


    //    查询回款
    @ApiOperation(value = "客户余额表", notes = "参数:客户余额表")
    @PostMapping(value = "/yuebiao")
    public ResultVo yuebiao(@ApiParam(name = "fahuo", required = true)
                            @RequestBody DomesticFahuo fahuo) {
        List<WaimaoTichengFahuo> lisfahuo = waimaoTichengFahuoService.seleteYuebiaoFa(fahuo);
        List<WaimaoTichengHuikuan> listhuikuan = waimaoTichengHuikuanService.seleteYuebiaoHuikuan();
        Map<String, Float> map = new HashMap<>();
        for (int i = 0; i < lisfahuo.size(); i++) {
            for (int j = 0; j < listhuikuan.size(); j++) {
                if (lisfahuo.get(i).getFapiaohao().equals(listhuikuan.get(j).getFapiaohao())) {
                    map.put(lisfahuo.get(i).getFapiaohao(), Float.valueOf(lisfahuo.get(i).getJiashuiheji()) - Float.valueOf(listhuikuan.get(j).getJine()));
                }
            }
        }
        for (int i = 0; i < lisfahuo.size(); i++) {
            if (!map.containsKey(lisfahuo.get(i).getFapiaohao())) {
                map.put(lisfahuo.get(i).getFapiaohao(), Float.valueOf(lisfahuo.get(i).getJiashuiheji()));
            }
        }
        for (int j = 0; j < listhuikuan.size(); j++) {
            if (!map.containsKey(listhuikuan.get(j).getFapiaohao())) {
                map.put(listhuikuan.get(j).getFapiaohao(), -Float.valueOf(listhuikuan.get(j).getHuikuanjine()));
            }
        }
        return ResultVo.success(map);
    }

    @ApiOperation(value = "客户余额表导出", notes = "参数:客户余额表导出")
    @PostMapping(value = "/yueexcle")
    public void yueexcle(HttpServletResponse response, DomesticFahuo fahuo) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("数据导出");
        List<WaimaoTichengFahuo> lisfahuo = waimaoTichengFahuoService.seleteYuebiaoFa(fahuo);
        List<WaimaoTichengHuikuan> listhuikuan = waimaoTichengHuikuanService.seleteYuebiaoHuikuan();
        Map<String, Float> map = new HashMap<>();
        for (int i = 0; i < lisfahuo.size(); i++) {
            for (int j = 0; j < listhuikuan.size(); j++) {
                if (lisfahuo.get(i).getShouhuokehu().equals(listhuikuan.get(j).getFapiaohao())) {
                    map.put(lisfahuo.get(i).getShouhuokehu(), Float.valueOf(lisfahuo.get(i).getJiashuiheji()) - Float.valueOf(listhuikuan.get(j).getHuikuanjine()));
                }
            }
        }
        for (int i = 0; i < lisfahuo.size(); i++) {
            if (!map.containsKey(lisfahuo.get(i).getShouhuokehu())) {
                map.put(lisfahuo.get(i).getShouhuokehu(), Float.valueOf(lisfahuo.get(i).getJiashuiheji()));
            }
        }
        for (int j = 0; j < listhuikuan.size(); j++) {
            if (!map.containsKey(listhuikuan.get(j).getFapiaohao())) {
                map.put(listhuikuan.get(j).getFapiaohao(), -Float.valueOf(listhuikuan.get(j).getHuikuanjine()));
            }
        }
        String fileName = "客户余额表导出" + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = {"客户名称", "客户余额"};
        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        HSSFRow row1;
        //在表中存放查询到的数据放入对应的列
        for (Map.Entry<String, Float> maps : map.entrySet()) {
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

    @ApiOperation(value = "发货已计算信息导出", notes = "参数:发货已计算信息导出")
    @PostMapping(value = "/exportHuikuanCalculated")
    public void downloadAllYijisuanClassmate(HttpServletResponse response, @RequestBody String fahuoDate) throws Exception {
        int currow = 0;
        String fileName = "外贸发货数据导出" + ".xls";//设置要导出的文件的名字
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("数据导出");
        String[] headers = {"单据号", "发货日期", "发票号", "业务员", "净水提成", "新客户提成", "物料名称", "吨位", "包装", "运输金额"
                , "净水单价", "价税合计", "币种", "汇率", "佣金", "海运费", "回款日期", "回款天数", "预计装船日期", "收货客户", "回款协议", "包装费用", "价格条款", "当月目标完成率","计算公式"};
        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        HSSFRow row1;
        log.info("外贸发货已计算导出fahuoDate的值为：" + fahuoDate);
        // 查询大于发货日期的发货单
        List<WaimaoTichengFahuoDto> waimaoTichengFahuoDtos = waimaoTichengFahuoService.selectFaHuiCal(fahuoDate);
        List<Integer> result = new ArrayList<>();
        for (WaimaoTichengFahuoDto fahuoDto : waimaoTichengFahuoDtos) {
            double jingshui = 0;
            double jingshuiPrice = 0;// 净水单价
            WaimaoTichengHuilv waimaoTichengHuilv = fahuoDto.getWaimaoTichengHuilv();
            WaimaoTichengBaozhuang baozhuang = fahuoDto.getWaimaoTichengBaozhuang();
            if ("人民币".equals(fahuoDto.getBizhong())) {
                // （价税合计-海运费-佣金）/ 1.13 - 运输 - 包装
                jingshui = (Float.valueOf(fahuoDto.getJiashuiheji()) - Float.valueOf(fahuoDto.getHaiyunfei()) - Float.valueOf(fahuoDto.getYongjin())*Float.valueOf(fahuoDto.getShuliang())) / 1.13
                        - Float.valueOf(baozhuang.getBaozhuangjia()) * Float.valueOf(fahuoDto.getShuliang())
                        - Float.valueOf(fahuoDto.getYunshujine());
            } else {
                // （价税合计-海运费-佣金）* 汇率 - 运输 - 包装
                jingshui = (Float.valueOf(fahuoDto.getJiashuiheji()) - Float.valueOf(fahuoDto.getHaiyunfei()) - Float.valueOf(fahuoDto.getYongjin())*Float.valueOf(fahuoDto.getShuliang())) * Float.valueOf(fahuoDto.getHuilv())
                        - Float.valueOf(baozhuang.getBaozhuangjia()) * Float.valueOf(fahuoDto.getShuliang())
                        - Float.valueOf("".equals(fahuoDto.getYunshujine()) ? "0" : fahuoDto.getYunshujine());
            }
            jingshuiPrice = jingshui / fahuoDto.getShuliang();

            List<WaimaoTichengHuikuan> huikuanList = fahuoDto.getHuikuanList();
            String[] biaoshi = fahuoDto.getBiaoji().split(",");
            for (int i = 1; i < biaoshi.length; i++) {
                for (WaimaoTichengHuikuan waimaoTichengHuikuan : huikuanList) {
                    if (biaoshi[i].equals(waimaoTichengHuikuan.getId() + "")) {
                        String[] huikuanBiaoshi = waimaoTichengHuikuan.getBiaoshi().split(",");
                        for (int j = 0; j < huikuanBiaoshi.length; j++) {
                            if (huikuanBiaoshi[j].equals(fahuoDto.getId() + "")) {
                                if (!result.contains(fahuoDto.getId())) {
                                    currow++;
                                    long distanceDays = CommonUtils.getDistanceDays(waimaoTichengHuikuan.getHuikuanriqi(), fahuoDto.getYujizhuangtime());
                                    result.add(fahuoDto.getId());
                                    row1 = sheet.createRow(currow);
                                    row1.createCell(0).setCellValue(fahuoDto.getDanjuhao());
                                    row1.createCell(1).setCellValue(fahuoDto.getFahuotime());
                                    row1.createCell(2).setCellValue(fahuoDto.getFapiaohao());
                                    row1.createCell(3).setCellValue(fahuoDto.getYewuyuan());
                                    row1.createCell(4).setCellValue(fahuoDto.getFobticheng());
                                    row1.createCell(5).setCellValue(fahuoDto.getXinkehuticheng());
                                    row1.createCell(6).setCellValue(fahuoDto.getWuliaoming());
                                    row1.createCell(7).setCellValue(fahuoDto.getShuliang());
                                    row1.createCell(8).setCellValue(fahuoDto.getBaozhuangwu());
                                    row1.createCell(9).setCellValue(fahuoDto.getYunshujine());
                                    BigDecimal b = new BigDecimal(jingshuiPrice);
                                    jingshuiPrice = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                                    row1.createCell(10).setCellValue(jingshuiPrice);
                                    row1.createCell(11).setCellValue(fahuoDto.getJiashuiheji());
                                    row1.createCell(12).setCellValue(fahuoDto.getBizhong());
                                    row1.createCell(13).setCellValue(fahuoDto.getHuilv());
                                    row1.createCell(14).setCellValue(fahuoDto.getYongjin());
                                    row1.createCell(15).setCellValue(fahuoDto.getHaiyunfei());
                                    row1.createCell(16).setCellValue(waimaoTichengHuikuan.getHuikuanriqi());
                                    row1.createCell(17).setCellValue(distanceDays);
                                    row1.createCell(18).setCellValue(fahuoDto.getYujizhuangtime());
                                    row1.createCell(19).setCellValue(fahuoDto.getShouhuokehu());
                                    row1.createCell(20).setCellValue(fahuoDto.getHuikuanxieyi());
                                    row1.createCell(21).setCellValue(baozhuang.getBaozhuangjia());
                                    row1.createCell(22).setCellValue(fahuoDto.getJiagetiaokuan());
                                    row1.createCell(23).setCellValue(fahuoDto.getCeshi1());
                                    row1.createCell(24).setCellValue(fahuoDto.getCeshi2());
                                }
                            }
                        }
                    }
                }
            }

        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

}