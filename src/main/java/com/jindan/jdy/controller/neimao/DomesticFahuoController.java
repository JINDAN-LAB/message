package com.jindan.jdy.controller.neimao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.DomesticFahuoDto;
import com.jindan.jdy.common.dto.DomesticHuikuanDto;
import com.jindan.jdy.common.pojo.*;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.neimao.*;
import com.jindan.jdy.utils.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
*
* <p>说明： 内贸提成API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年4月20日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "内贸提成发货信息")
@RestController
@RequestMapping("/domesticFahuo")
@Slf4j
public class DomesticFahuoController{

        @Autowired
        private RedisUtil redisUtil;

        private final static String excel2003 =".xls";
        private final static String excel2007 =".xlsx";

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

        //    基价数据导入
        @ApiOperation(value = "基价信息导入", notes = "参数:基价信息导入")
        @PostMapping("/addjijiafile")
        public ResultVo addjijiafile(@RequestParam("file") MultipartFile file, String ids) throws Exception {
            int num=0;
            //创建Excel工作薄
            Workbook work = this.getWorkbook(file.getInputStream(),file.getOriginalFilename());
            log.info("======“基价信息导入接口”开始执行======");
            if(null == work){
                throw new Exception("创建Excel工作薄为空！");
            }
            log.info(" work.getNumberOfSheets()的值为："+ work.getNumberOfSheets());
            Sheet sheet  = work.getSheetAt(0);
            if(sheet==null){
                throw new Exception("创建Excel工作薄为空！");
            }
            List<DomesticJijiabiao> jijiabiaos = new ArrayList<>();
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                Row row = sheet.getRow(j);
                if(row==null||row.getFirstCellNum()==j){continue;}
                num++;
                DomesticJijiabiao jijiabiao = new DomesticJijiabiao();
                if(row.getCell(0)!=null){
                    row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                    jijiabiao.setName(row.getCell(0).getStringCellValue());
                }
                if(row.getCell(1)!=null){
                    row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                    jijiabiao.setDiyidang((row.getCell(1).getStringCellValue()));
                }
                if(row.getCell(2)!=null){
                    row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                    jijiabiao.setDierdang((row.getCell(2).getStringCellValue()));
                }
                if(row.getCell(3)!=null){
                    row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                    jijiabiao.setDisandang((row.getCell(3).getStringCellValue()));
                }
                if(row.getCell(4)!=null){
                    row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                    jijiabiao.setDisidang((row.getCell(4).getStringCellValue()));
                }
                if(row.getCell(5)!=null){
                    row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                    jijiabiao.setDiwudang((row.getCell(5).getStringCellValue()));
                }
                if(jijiabiao.getName() != null  && !jijiabiao.getName().equals(" ") ){
                    jijiabiaos.add(jijiabiao);
                }
            }
            domesticJijiabiaoService.saveBatch(jijiabiaos);
            return ResultVo.success(num);
        }

        //    基价表2导入
        @ApiOperation(value = "基价2信息导入", notes = "参数:基价2信息导入")
        @PostMapping("/addjijia2file")
        public ResultVo addjijia2file(@RequestParam("file") MultipartFile file, String ids) throws Exception {

            int num=0;
            //创建Excel工作薄
            Workbook work = this.getWorkbook(file.getInputStream(),file.getOriginalFilename());
            log.info("======“基价2信息导入接口”开始执行======");
            if(null == work){
                throw new Exception("创建Excel工作薄为空！");
            }
            log.info(" work.getNumberOfSheets()的值为："+ work.getNumberOfSheets());
            Sheet sheet  = work.getSheetAt(0);
            if(sheet==null){
                throw new Exception("创建Excel工作薄为空！");
            }
            List<DomesticJijiabiao2> jijiabiaos = new ArrayList<>();
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                Row row = sheet.getRow(j);
                if(row==null||row.getFirstCellNum()==j){continue;}
                num++;
                DomesticJijiabiao2 jijiabiao = new DomesticJijiabiao2();
                if(row.getCell(0)!=null){
                    row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                    jijiabiao.setName(row.getCell(0).getStringCellValue());
                }
                if(row.getCell(1)!=null){
                    row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);jijiabiao.setName(row.getCell(0).getStringCellValue());
                    jijiabiao.setDiyidang((row.getCell(1).getStringCellValue()));
                }
                if(row.getCell(2)!=null){
                    row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                    jijiabiao.setDierdang((row.getCell(2).getStringCellValue()));
                }
                if(row.getCell(3)!=null){
                    row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                    jijiabiao.setDisandang((row.getCell(3).getStringCellValue()));
                }
                if(row.getCell(4)!=null){
                    row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                    jijiabiao.setDisidang((row.getCell(4).getStringCellValue()));
                }
                if(row.getCell(5)!=null){
                    row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                    jijiabiao.setDiwudang((row.getCell(5).getStringCellValue()));
                }
                if(jijiabiao.getName() != null  && !jijiabiao.getName().equals(" ") ){
                    jijiabiaos.add(jijiabiao);
                }
            }
            domesticJijiabiao2Service.saveBatch(jijiabiaos);
            return ResultVo.success(num);
        }
        // 包装信息导入
        @ApiOperation(value = "包装信息导入", notes = "参数:包装信息导入")
        @PostMapping("/addbaozhuangfile")
        public ResultVo addbaozhuangfile(@RequestParam("file") MultipartFile file, String ids) throws Exception {
            int num=0;
            //创建Excel工作薄
            Workbook work = this.getWorkbook(file.getInputStream(),file.getOriginalFilename());
            log.info("======“包装信息导入接口”开始执行======");
            if(null == work){
                throw new Exception("创建Excel工作薄为空！");
            }
            log.info(" work.getNumberOfSheets()的值为："+ work.getNumberOfSheets());
            Sheet sheet  = work.getSheetAt(0);
            if(sheet==null){
                throw new Exception("创建Excel工作薄为空！");
            }
            List<DomesticBaozhuang> jijiabiaos = new ArrayList<>();
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++){
                Row row = sheet.getRow(j);
                if(row==null||row.getFirstCellNum()==j){continue;}
                num++;
                DomesticBaozhuang jijiabiao = new DomesticBaozhuang();
                if(row.getCell(0)!=null){
                    row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                    jijiabiao.setBaozhuangwu(row.getCell(0).getStringCellValue());
                }
                if(row.getCell(1)!=null){
                    row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                    jijiabiao.setBuhanbaozhuang(row.getCell(1).getStringCellValue());
                }
                if(jijiabiao.getBaozhuangwu() != null  && !jijiabiao.getBaozhuangwu().equals(" ") ){
                    jijiabiaos.add(jijiabiao);
                }
            }
            domesticBaozhuangService.saveBatch(jijiabiaos);
            return ResultVo.success(num);
        }
        // 回款数据信息
        @ApiOperation(value = "回款信息导入", notes = "参数:回款信息导入")
        @PostMapping("/addhuikuanfile")
        public ResultVo addhuikuanfile(@RequestParam("file") MultipartFile file, String ids) throws Exception {
            int num=0;
            Workbook work = this.getWorkbook(file.getInputStream(),file.getOriginalFilename());
            log.info("回款信息导入开始.........");
            if(null == work){
                throw new Exception("创建Excel工作薄为空！");
            }
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String str = sdf.format(d);
            Sheet sheet  = work.getSheetAt(0);
            if(sheet==null){
                throw new Exception("创建Excel工作薄为空！");
            }
            List<DomesticHuikuan> jijiabiaos = new ArrayList<>();
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                Row row = sheet.getRow(j);
                if(row==null||row.getFirstCellNum()==j){continue;}

                if(row.getCell(0) == null){ continue;}
                num++;
                DomesticHuikuan jijiabiao = new DomesticHuikuan();
                switch (row.getCell(0).getCellType()){
                    case HSSFCell.CELL_TYPE_NUMERIC:
                        if(row.getCell(0) != null){
                            Cell cell = row.getCell(0);
                            cell.setCellType(1);
                            String huankuanDate = cell.getStringCellValue() + "";
                            huankuanDate = DateUtils.getFormatDate(huankuanDate);
                            jijiabiao.setHuikuanriqi( huankuanDate);
                        }
                        break;
                    case HSSFCell.CELL_TYPE_STRING:
                        if(row.getCell(0) != null){
                            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                            // 转换
                            String huankuanDate = row.getCell(0).getStringCellValue();
                            huankuanDate = DateUtils.getFormatDate(huankuanDate);
                            jijiabiao.setHuikuanriqi(huankuanDate);
                         }
                        break;
                }
                if(row.getCell(1) != null){
                    row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                    jijiabiao.setXingming(row.getCell(1).getStringCellValue());
                }
                if(row.getCell(2) != null){
                    row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                    String kuhumingcheng = row.getCell(2).getStringCellValue();
                    kuhumingcheng = kuhumingcheng.replaceAll(" ","");
                    jijiabiao.setKehumingcheng(kuhumingcheng);
                }
                if(row.getCell(3) != null){
                    row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                    jijiabiao.setJine(row.getCell(3).getStringCellValue());
                }
                if(row.getCell(4) != null){
                    row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                    jijiabiao.setJine2( (row.getCell(4).getStringCellValue()));
                }
                switch (row.getCell(5).getCellType()){
                    case HSSFCell.CELL_TYPE_NUMERIC:
                        if(row.getCell(5) != null){
                            jijiabiao.setChengduiriqi( sdf.format(row.getCell(5).getDateCellValue()));
                        }
                        break;
                    case HSSFCell.CELL_TYPE_STRING:
                       if(row.getCell(5) != null){
                            row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                            jijiabiao.setChengduiriqi(row.getCell(5).getStringCellValue());
                        }
                        break;
                }
                if(jijiabiao.getHuikuanriqi() != null  && !jijiabiao.getHuikuanriqi().equals(" ") ){
                    jijiabiao.setDaorutime(str);
                    jijiabiaos.add(jijiabiao);
                }
            }
            domesticHuikuanService.saveAllHuiBatch(jijiabiaos);
            return ResultVo.success(num);
        }


        @ApiOperation(value = "发货信息导入", notes = "参数:发货信息导入")
        @PostMapping("/addfahuofile")
        public ResultVo addfahuofile(@RequestParam("file") MultipartFile file, String ids) throws Exception {
            int num=0;
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String str = sdf.format(d);
            Workbook work = this.getWorkbook(file.getInputStream(),file.getOriginalFilename());
            int x =0;
            int y=0;
            if(null == work){
                throw new Exception("创建Excel工作薄为空！");
            }
            Sheet sheet  = work.getSheetAt(0);
            if(sheet==null){
                throw new Exception("创建Excel工作薄为空！");
            }
            List<DomesticFahuo> jindantichengList = new ArrayList<>();
            for (int j = 0; j <= sheet.getLastRowNum(); j++){
                Row row = sheet.getRow(j);

                if(row.getCell(1).getStringCellValue()==null){break;}
                if(sheet.getFirstRowNum()==1){  y++; continue;}
                x++;
                num++;
                DomesticFahuo jindanticheng = new DomesticFahuo(); //14  17 18b
                if(row.getCell(0) != null){
                    row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                    jindanticheng.setDanjuhao(row.getCell(0).getStringCellValue());
                }
                if(row.getCell(1) != null){
                    row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                    jindanticheng.setDanjuriqi(row.getCell(1).getStringCellValue());
                }
                if(row.getCell(2) != null){
                    row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                    jindanticheng.setYewuyuan(row.getCell(2).getStringCellValue());
                }
                if(row.getCell(3) != null){
                    row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                    jindanticheng.setFangshi(row.getCell(3).getStringCellValue());
                }
                if(row.getCell(4) != null){
                    row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                    String shouhuokehu = row.getCell(4).getStringCellValue();
                    shouhuokehu = shouhuokehu.replaceAll(" ","");
                    jindanticheng.setShouhuokehu(shouhuokehu);
                }
                if(row.getCell(5) != null){
                    row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                    jindanticheng.setWuliaomingcheng(row.getCell(5).getStringCellValue());
                }
                if(row.getCell(6) != null){
                    row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                    jindanticheng.setBzw(row.getCell(6).getStringCellValue());
                }
                if(row.getCell(7) != null){
                    row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                    jindanticheng.setShuliang(row.getCell(7).getStringCellValue());
                }
                if(row.getCell(8) != null){
                    row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                    jindanticheng.setHanshuidanjia(row.getCell(8).getStringCellValue());
                }
                if(row.getCell(9) != null){
                    row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
                    jindanticheng.setJiashuiheji(row.getCell(9).getStringCellValue());
                }
                if(row.getCell(10) != null){
                    row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
                    jindanticheng.setYunshudanjia(row.getCell(10).getStringCellValue());
                }
                if(row.getCell(11) != null){
                    row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
                    jindanticheng.setBhbz(row.getCell(11).getStringCellValue());
                }
                if(row.getCell(12) != null){
                    row.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
                    jindanticheng.setYongjinbili(row.getCell(12).getStringCellValue());
                }
                if(row.getCell(13) != null){
                    row.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
                    jindanticheng.setYongjin(row.getCell(13).getStringCellValue());
                }
                if(row.getCell(14) != null){
                    row.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
                    jindanticheng.setShifouweixinkehu(row.getCell(14).getStringCellValue());
                }
                if(row.getCell(15) != null){
                    row.getCell(15).setCellType(Cell.CELL_TYPE_STRING);
                    jindanticheng.setShoukuanxieyi(row.getCell(15).getStringCellValue());
                }
                if(row.getCell(16) != null){
                    row.getCell(16).setCellType(Cell.CELL_TYPE_STRING);
                    jindanticheng.setChanmofeiyong(row.getCell(16).getStringCellValue());
                }
                if(jindanticheng.getDanjuriqi() != null  && !jindanticheng.getDanjuriqi().equals(" ") ){
                    jindanticheng.setQueren("未计算");
                    jindantichengList.add(jindanticheng);
                }
            }
             domesticFahuoService.saveAllBatch(jindantichengList);
            return ResultVo.success(num);
        }
            
        @ApiOperation(value = "发货信息列表", notes = "参数:包装信息列表")
        @PostMapping("/fahuolist")
        public ResultVo conditionpurchase(@ApiParam(name = "domesticFahuoDto", required = false)
                                   @RequestBody DomesticFahuoDto domesticFahuoDto){
            log.info("======“发货信息列表接口”开始执行======");
            IPage<DomesticFahuo> page1 = domesticFahuoService.seletepage(domesticFahuoDto);
            log.info("page1的值为："+page1);
            return ResultVo.success(page1);
        }

        @ApiOperation(value = "回款信息列表", notes = "参数:回款信息列表")
        @PostMapping("/huikuanlist")
        public ResultVo huikuanlist(@ApiParam(name = "domesticFahuoDto", required = false)
                             @RequestBody DomesticHuikuanDto domesticFahuoDto){
            log.info("======“回款信息列表接口”开始执行======");
            Page<DomesticHuikuan> page1 = domesticHuikuanService.seletepage(domesticFahuoDto);
            log.info("page1的值为："+page1);
            return ResultVo.success(page1);
        }

        @ApiOperation(value = "包装信息列表", notes = "参数:包装信息列表")
        @PostMapping("/baozhuanglist")
        public ResultVo baozhuanglist(@ApiParam(name = "domesticBaozhuang", required = false)
                               @RequestBody DomesticBaozhuang domesticBaozhuang){
                    List<DomesticBaozhuang> page1 = domesticBaozhuangService.seletelist(domesticBaozhuang);
                    return  ResultVo.success(page1);

        }

        @ApiOperation(value = "发货删除", notes = "参数:发货删除")
        @PostMapping("fahuodeletelist")
        public ResultVo fahuodeletelist(@ApiParam(name = "list", required = false)
                                        @RequestBody List<String> list) throws Exception {
            boolean b = domesticFahuoService.removeByIds(list);
            if(b){
                return  ResultVo.success();
            }
            return  ResultVo.failed();
        }

        @ApiOperation(value = "回款删除", notes = "参数:回款删除")
        @PostMapping("huikuandeletelist")
        public ResultVo huikuandeletelist(@ApiParam(name = "list", required = false)
                                          @RequestBody List<String> list) throws Exception {
            boolean b = domesticHuikuanService.removeByIds(list);
            if(b){
                return  ResultVo.success();
            }
            return  ResultVo.failed();
        }

        @ApiOperation(value = "基价信息列表", notes = "参数:基价信息列表")
        @PostMapping("/jijialist")
        public ResultVo jijialist(@ApiParam(name = "domesticJijiabiao", required = false)
                           @RequestBody DomesticJijiabiao domesticJijiabiao){
                    List<DomesticJijiabiao> page1 = domesticJijiabiaoService.seletelist(domesticJijiabiao);
                    return  ResultVo.success(page1);

        }

        @ApiOperation(value = "基价2信息列表", notes = "参数:基价2信息列表")
        @PostMapping("/jijia2list")
        public ResultVo jijia2list(@ApiParam(name = "domesticJijiabiao", required = false)
                            @RequestBody DomesticJijiabiao2 domesticJijiabiao){
            List<DomesticJijiabiao2> page1 = domesticJijiabiao2Service.seletelist(domesticJijiabiao);
            return  ResultVo.success(page1);
        }

        @ApiOperation(value = "计算系数列表", notes = "参数:计算系数列表")
        @GetMapping("/paramlist")
        public ResultVo paramlist(){
             if( redisUtil.get("paramlist") == null){
                 List<DomesticXishu> list = domesticXishuService.list(null);
                 redisUtil.set("paramlist",list.get(0));
                 return  ResultVo.success(list.get(0));
             }else{
                 return  ResultVo.success((com.jindan.jdy.common.pojo.DomesticXishu) redisUtil.get("paramlist"));
             }
        }

        @ApiOperation(value = "更新计算系数", notes = "参数:更新计算系数")
        @PostMapping("/updateparamlist")
        public ResultVo updateparamlist(@ApiParam(name = "domesticXishu", required = false)
                                 @RequestBody DomesticXishu domesticXishu){
            boolean b = domesticXishuService.updateById(domesticXishu);
            if(b){
                redisUtil.del("paramlist");
                return ResultVo.success();
            }
            return ResultVo.failed();
        }

        @ApiOperation(value = "删除计算系数", notes = "参数:删除计算系数")
        @DeleteMapping("/fahuoremoveById/{id}")
        public ResultVo deletefacilitysubset(@ApiParam(name = "id", value = "课程基本信息", required = true) @PathVariable String  id){
            boolean b = domesticFahuoService.removeById(id);
            if(b){
                redisUtil.del("paramlist");
                return  ResultVo.success();
            }
            return ResultVo.failed();
        }

        @ApiOperation(value = "更新发货信息", notes = "参数:更新发货信息")
        @PostMapping("/updatefahuo")
        public ResultVo updatefahuo(@ApiParam(name = "domesticFahuo", required = false)
                             @RequestBody DomesticFahuo domesticFahuo){
            boolean b = domesticFahuoService.updateById(domesticFahuo);
            if(b){
                return ResultVo.success();
            }
            return ResultVo.failed();
        }

        @ApiOperation(value = "删除回款信息", notes = "参数:删除回款信息")
        @DeleteMapping("/huikuanremoveById/{id}")
        public ResultVo huikuanremoveById(@ApiParam(name = "id", value = "课程基本信息", required = true) @PathVariable String  id){
            boolean b = domesticHuikuanService.removeById(id);
            if(b){
                return  ResultVo.success();
            }
            return  ResultVo.failed();
        }

    @ApiOperation("批量删除回款信息")
    @DeleteMapping("/allhuikuanremoveById")
    public ResultVo allhuikuanremoveById(@ApiParam(name = "list", required = false)
                                         @RequestBody List<String> list){
        boolean b = domesticHuikuanService.removeByIds(list);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation(value = "更新回款信息", notes = "参数:更新回款信息")
    @PostMapping("/updatehuikuan")
    public ResultVo updatefahuo(@ApiParam(name = "domesticHuikuan", required = false)
                                @RequestBody DomesticHuikuan domesticHuikuan){
            boolean b = domesticHuikuanService.updateById(domesticHuikuan);
            if(b){
                return ResultVo.success();
            }
            return ResultVo.failed();
        }

        @ApiOperation(value = "删除包装信息", notes = "参数:删除包装信息")
        @DeleteMapping("/baozhuangremoveById/{id}")
        public ResultVo baozhuangremoveById(@ApiParam(name = "id", value = "课程基本信息", required = true) @PathVariable String  id){
            boolean b = domesticBaozhuangService.removeById(id);
            if(b){
                redisUtil.del("baozhuanglist");
                redisUtil.del(id);
                return ResultVo.success();
            }
            return  ResultVo.failed();
        }

        @ApiOperation(value = "更新包装信息", notes = "参数:更新包装信息")
        @PostMapping("/updatebaozhuang")
        public ResultVo updatebaozhuang(@ApiParam(name = "domesticFahuo", required = false)
                                 @RequestBody DomesticBaozhuang domesticBaozhuang){
            boolean b = domesticBaozhuangService.updateById(domesticBaozhuang);
            if(b){
                redisUtil.del("baozhuanglist");
                redisUtil.del(domesticBaozhuang.getBaozhuangwu());
                return ResultVo.success();
            }
            return ResultVo.failed();
        }

        @ApiOperation(value = "基价删除信息", notes = "参数:基价删除信息")
        @DeleteMapping("/jijiaremoveById/{id}")
        public ResultVo jijiaremoveById(@ApiParam(name = "id", value = "课程基本信息", required = true) @PathVariable String  id){
            boolean b = domesticJijiabiaoService.removeById(id);
            if(b){
                redisUtil.del("jijialist");
                redisUtil.del(id);
                return  ResultVo.success();
            }
            return ResultVo.failed();
        }

        @ApiOperation(value = "更新基价信息", notes = "参数:更新基价信息")
        @PostMapping("/updatejijia")
        public ResultVo updatebaozhuang(@ApiParam(name = "domesticJijiabiao", required = false)
                                       @RequestBody DomesticJijiabiao domesticJijiabiao){
            boolean b = domesticJijiabiaoService.updateById(domesticJijiabiao);
            if(b){
                redisUtil.del("jijialist");
                redisUtil.del(domesticJijiabiao.getName());
                return ResultVo.success();
            }
            return ResultVo.failed();
        }

        @ApiOperation(value = "删除基价2信息", notes = "参数:删除基价2信息")
        @DeleteMapping("/jijia2removeById/{id}")
        public ResultVo jijia2removeById(@ApiParam(name = "id", value = "课程基本信息", required = true) @PathVariable String  id){
            boolean b = domesticJijiabiao2Service.removeById(id);
            if(b){
                return  ResultVo.success();
            }
            return  ResultVo.failed();
        }

        @ApiOperation(value = "更新基价2信息", notes = "参数:更新基价2信息")
        @PostMapping("/updatejijia2")
        public ResultVo updatebaozhuang(@ApiParam(name = "updatejijia2", required = false)
                                 @RequestBody DomesticJijiabiao2 updatejijia2){
            boolean b = domesticJijiabiao2Service.updateById(updatejijia2);
            if(b){
                return ResultVo.success();
            }
            return ResultVo.failed();
        }

        @ApiOperation(value = "增加发货信息", notes = "参数:增加发货信息")
        @PostMapping("/addfahuopost")
        public ResultVo conditionpurchase(@ApiParam(name = "domesticFahuo", required = false)
                                   @RequestBody DomesticFahuo domesticFahuo){
            boolean save = domesticFahuoService.save(domesticFahuo);
            if(save){
                return  ResultVo.success();
            }
            return  ResultVo.failed();
        }


    @ApiOperation(value = "增加计算系数信息", notes = "参数:增加计算系数信息")
    @PostMapping("/addjisuanxishupost")
    public ResultVo addjisuanxishupost(@ApiParam(name = "domesticFahuo", required = false)
                                      @RequestBody DomesticXishu domesticXishu){
        boolean save = domesticXishuService.save(domesticXishu);
        if(save){
            return  ResultVo.success();
        }
        return  ResultVo.failed();
    }

    @ApiOperation(value = "增加回款信息", notes = "参数:增加回款信息")
    @PostMapping("/addhuikuanpost")
    public ResultVo addhuikuanpost(@ApiParam(name = "domesticHuikuan", required = false)
                                @RequestBody DomesticHuikuan domesticHuikuan){
            boolean save = domesticHuikuanService.save(domesticHuikuan);
            if(save){
                return ResultVo.success();
            }
            return  ResultVo.failed();
    }

        @ApiOperation(value = "增加包装信息", notes = "参数:增加包装信息")
        @PostMapping("/addbaozhuangpost")
        public ResultVo addbaozhuangpost(@ApiParam(name = "domesticBaozhuang", required = false)
                                  @RequestBody DomesticBaozhuang domesticBaozhuang){
            boolean save = domesticBaozhuangService.save(domesticBaozhuang);
            if(save){
                redisUtil.del("baozhuanglist");
                redisUtil.del(domesticBaozhuang.getBaozhuangwu());
                return  ResultVo.success();
            }
            return ResultVo.failed();
        }

        @ApiOperation(value = "增加基价信息", notes = "参数:增加基价信息")
        @PostMapping("/addjijiapost")
        public ResultVo addjijiapost(@ApiParam(name = "domesticJijiabiao", required = false)
                              @RequestBody DomesticJijiabiao domesticJijiabiao){
            boolean save = domesticJijiabiaoService.save(domesticJijiabiao);
            if(save){
                redisUtil.del("jijialist");
                return ResultVo.success();
            }
            return  ResultVo.failed();
        }

        @ApiOperation(value = "增加基价2信息", notes = "参数:增加基价2信息")
        @PostMapping("/addjijia2post")
        public ResultVo addjijia2post(@ApiParam(name = "domesticJijiabiao2", required = false)
                               @RequestBody DomesticJijiabiao2 domesticJijiabiao2){
            boolean save = domesticJijiabiao2Service.save(domesticJijiabiao2);
            if(save){
                return ResultVo.success();
            }
            return  ResultVo.failed();
        }

        @ApiOperation("删除发货信息")
        @DeleteMapping("/deleteNeimaoFahuo/{id}")
        public ResultVo deleteTichengFahuo(@ApiParam(value = "id", name = "角色ID", required = true) @PathVariable String  id){
            boolean b = domesticFahuoService.removeById(id);
            if(b){
                return ResultVo.success();
            }
            return ResultVo.failed();
        }

    @ApiOperation("批量删除发货信息")
    @DeleteMapping("/alldeleteNeimaoFahuo")
    public ResultVo alldeleteNeimaoFahuo(@ApiParam(name = "list", required = false)
                                         @RequestBody List<String> list){
        boolean b = domesticFahuoService.removeByIds(list);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


        @ApiOperation(value = "已计算列表信息", notes = "参数:已计算列表信息")
        @PostMapping("/calculatelist")
        public ResultVo calculatelist(@ApiParam(name = "domesticFahuoDto", required = false)
                               @RequestBody DomesticFahuoDto domesticFahuoDto){
            log.info("======“已计算列表信息接口”开始执行======");
            Page<DomesticFahuo> page1 = domesticFahuoService.seleteyijisuanpage(domesticFahuoDto);
            log.info("page1的值为："+page1);
            return  ResultVo.success(page1);
        }


        @ApiOperation(value = "已计算不打印列表信息", notes = "参数:已计算不打印列表信息")
        @PostMapping("/calculatelistunprint")
        public ResultVo calculatelistunprint(@ApiParam(name = "domesticFahuoDto", required = false)
                                      @RequestBody DomesticFahuoDto domesticFahuoDto){
            Page<DomesticFahuo> page1 = domesticFahuoService.selebudayinpage(domesticFahuoDto);
            return  ResultVo.success( page1);
        }


        @ApiOperation(value = "已打印列表信息", notes = "参数:已打印列表信息")
        @PostMapping("calculateprint")
        public ResultVo calculateprint(@ApiParam(name = "domesticFahuoDto", required = false)
                                @RequestBody DomesticFahuoDto domesticFahuoDto){
          Page<DomesticFahuo> page1 = domesticFahuoService.seleteyidayinpage(domesticFahuoDto);
          return ResultVo.success(page1);
        }
        public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception{
            log.info("======“getWorkbook方法”开始执行======");
            Workbook wb = null;
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            if(excel2003.equals(fileType)){
                wb = new HSSFWorkbook(inStr);
            }else if(excel2007.equals(fileType)){
                wb = new XSSFWorkbook(inStr);
            }else{
                throw new Exception("解析的文件格式有误！");
            }
            return wb;
        }

        @ApiOperation(value = "发货信息导出", notes = "参数:发货信息导出")
        @PostMapping(value = "/fahuoexcle")
        public void downloadAllClassmate(HttpServletResponse response, DomesticFahuoDto param) throws IOException {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("数据导出");
            List<DomesticFahuo> classmatefa = domesticFahuoService.seleteExclelist(param);
            String fileName = "发货数据导出"+".xls";//设置要导出的文件的名字
            int rowNum = 1;
            String[] headers = { "单据号", "单据日期", "业务员", "运输方式","收获客户","物料名称","包装物",
                    "数量","含税单价","加税合计","运输单价","不含包装","新客户","状态"};
            HSSFRow row = sheet.createRow(0);
            //在excel表中添加表头
            for(int i=0;i<headers.length;i++){
                HSSFCell cell = row.createCell(i);
                HSSFRichTextString text = new HSSFRichTextString(headers[i]);
                cell.setCellValue(text);
            }
            HSSFRow row1;
            //在表中存放查询到的数据放入对应的列
            for (int i = 0; i <classmatefa.size() ; i++){
                rowNum++;
                row1 = sheet.createRow(rowNum);
                row1.createCell(0).setCellValue(classmatefa.get(i).getDanjuhao());
                row1.createCell(1).setCellValue(classmatefa.get(i).getDanjuriqi());
                row1.createCell(2).setCellValue(classmatefa.get(i).getYewuyuan());
                row1.createCell(3).setCellValue(classmatefa.get(i).getFangshi());
                row1.createCell(4).setCellValue(classmatefa.get(i).getShouhuokehu());
                row1.createCell(5).setCellValue(classmatefa.get(i).getWuliaomingcheng());
                row1.createCell(6).setCellValue(classmatefa.get(i).getBzw());
                row1.createCell(7).setCellValue(classmatefa.get(i).getShuliang());
                row1.createCell(8).setCellValue(classmatefa.get(i).getHanshuidanjia());
                row1.createCell(9).setCellValue(classmatefa.get(i).getJiashuiheji());
                row1.createCell(10).setCellValue(classmatefa.get(i).getYunshudanjia());
                row1.createCell(11).setCellValue(classmatefa.get(i).getBhbz());
                row1.createCell(12).setCellValue(classmatefa.get(i).getShifouweixinkehu());
                row1.createCell(13).setCellValue(classmatefa.get(i).getQueren());
            }
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            response.flushBuffer();
            workbook.write(response.getOutputStream());
        }

//      回款信息导出
        @ApiOperation(value = "回款信息导出", notes = "参数:回款信息导出")
        @PostMapping(value = "/huiexcle")
        public void huiexcle(HttpServletResponse response, DomesticHuikuan param) throws IOException {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("数据导出");
            List<DomesticHuikuan> classmatefa = domesticHuikuanService.seleteExclelist(param);
            String fileName = "回款数据导出"  + ".xls";//设置要导出的文件的名字
            //新增数据行，并且设置单元格数据
            int rowNum = 1;
            String[] headers = { "回款日期", "业务员", "客户名称","回款金额","备用金额","承兑日期"};
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
            for (int i = 0; i <classmatefa.size() ; i++){
                row1 = sheet.createRow(rowNum);
                row1.createCell(0).setCellValue(classmatefa.get(i).getHuikuanriqi());
                row1.createCell(1).setCellValue(classmatefa.get(i).getXingming());
                row1.createCell(2).setCellValue(classmatefa.get(i).getKehumingcheng());
                row1.createCell(3).setCellValue(classmatefa.get(i).getJine());
                row1.createCell(4).setCellValue(classmatefa.get(i).getJine2());
                row1.createCell(5).setCellValue(classmatefa.get(i).getChengduiriqi());
                rowNum++;
            }
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            response.flushBuffer();
            workbook.write(response.getOutputStream());
        }
//      包装导出
        @ApiOperation(value = "包装信息导出", notes = "参数:包装信息导出")
        @PostMapping(value = "/baozhuangexcle")
        public void baozhuangexcle(HttpServletResponse response) throws IOException{
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("数据导出");
            List<DomesticBaozhuang> classmatefa = domesticBaozhuangService.list(null);
            String fileName = "包装数据导出"  + ".xls";//设置要导出的文件的名字
            //新增数据行，并且设置单元格数据
            int rowNum = 1;
            String[] headers = { "包装物", "包装价"};
            HSSFRow row = sheet.createRow(0);
            for(int i=0;i<headers.length;i++){
                HSSFCell cell = row.createCell(i);
                HSSFRichTextString text = new HSSFRichTextString(headers[i]);
                cell.setCellValue(text);
            }
            HSSFRow row1;
            for (int i = 0; i <classmatefa.size() ; i++){
                row1 = sheet.createRow(rowNum);
                row1.createCell(0).setCellValue(classmatefa.get(i).getBaozhuangwu());
                row1.createCell(1).setCellValue(classmatefa.get(i).getBuhanbaozhuang());
                rowNum++;
            }
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            response.flushBuffer();
            workbook.write(response.getOutputStream());
       }
        @ApiOperation(value = "基价信息导出", notes = "参数:基价信息导出")
        @PostMapping(value = "jijiaexcle")
        public void jijiaexcle(HttpServletResponse response) throws IOException {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("数据导出");
            List<DomesticJijiabiao> classmatefa = domesticJijiabiaoService.list(null);
            String fileName = "基价数据导出"  + ".xls";//设置要导出的文件的名字
            //新增数据行，并且设置单元格数据
            int rowNum = 1;
            String[] headers = { "物料名称", "第一档","第二档","第三档","第四档","第五档"};
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
            for (int i = 0; i <classmatefa.size() ; i++){
                row1 = sheet.createRow(rowNum);
                row1.createCell(0).setCellValue(classmatefa.get(i).getName());
                row1.createCell(1).setCellValue(classmatefa.get(i).getDiyidang());
                row1.createCell(2).setCellValue(classmatefa.get(i).getDierdang());
                row1.createCell(3).setCellValue(classmatefa.get(i).getDisandang());
                row1.createCell(4).setCellValue(classmatefa.get(i).getDisidang());
                row1.createCell(5).setCellValue(classmatefa.get(i).getDiwudang());
                rowNum++;
            }
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            response.flushBuffer();
            workbook.write(response.getOutputStream());
        }

        @ApiOperation(value = "发货信息删除", notes = "参数:发货信息删除")
        @PostMapping("fahuodeletes")
        public ResultVo huikuandeletes( @ApiParam(name = "domesticFahuo", required = true)
                                        @RequestBody DomesticFahuo domesticFahuo){
            if(domesticFahuo.getJijiaticheng().equals(" ") && domesticFahuo.getQueren().equals(" ")){
                boolean remove = domesticFahuoService.removeById(domesticFahuo.getId());
                if(remove){
                    return ResultVo.success("data","删除成功");
                }
            }
            return ResultVo.failed("data","删除失败");
        }

        @ApiOperation(value = "回款信息删除", notes = "参数:回款信息删除")
        @PostMapping("huikuandeletes")
        public ResultVo huikuandeletes(@ApiParam(name = "domesticHuikuan", required = true)
                                       @RequestBody DomesticHuikuan domesticHuikuan){
            if(domesticHuikuan.getJine().equals(domesticHuikuan.getJine2()) && domesticHuikuan.getId() != null){
                boolean remove = domesticHuikuanService.removeById(domesticHuikuan.getId());
                if(remove){
                    return ResultVo.success("data","删除成功");
                }
            }
            return ResultVo.failed("data","删除失败");
        }
        //根据ID进行查询发货的ID
        @ApiOperation(value = "根据ID进行查询发货回款信息", notes = "参数:根据ID进行查询发货回款信息")
        @GetMapping("fahuodetails/{ids}")
        public ResultVo fahuodetails(@PathVariable Integer ids){
            List<DomesticFahuoDto> list =   domesticFahuoService.getOneById(ids);
            List<DomesticXishu> lisxishu = domesticXishuService.list(null);
            if(list.size() > 0){
                List<DomesticHuikuan> lishuikuan = new ArrayList<>();
                List<String> stringList =new ArrayList<>();
                String[] strAr = list.get(0).getDanhao().split(",");
                for(int i = 0; i <strAr.length ; i++){
                    if(strAr[i] != null && !strAr[i].equals("null") && !strAr[i].equals(" ")){
                        stringList.add(strAr[i]);
                    }
                }
                List<DomesticHuikuan> list1 = domesticHuikuanService.seleteInBiaoshi(stringList);
                DomesticFahuoDto domesticFahuoDto =new DomesticFahuoDto();
                BeanUtils.copyProperties(list.get(0),domesticFahuoDto);
                domesticFahuoDto.setDomesticXishu(lisxishu.get(0));
                domesticFahuoDto.setDomesticJijiabiao(list.get(0).getDomesticJijiabiao());
                domesticFahuoDto.setDomesticBaozhuang(list.get(0).getDomesticBaozhuang());
                for(int i = 0; i <list1.size() ; i++){
                    String[] strArr = list1.get(i).getBiaoshi().split(",");
                    String[] shiyongArr  = list1.get(i).getShijishiyongjine().split(",");
                    String[] biaoArr = list1.get(i).getBiaoshi().split(",");
                    String[] xiangArr = list1.get(i).getXiankuanjiang().split(",");
                    String[] xianbiligArr = list1.get(i).getXiankuanjiangbili().split(",");
                    String[] lixiArr = list1.get(i).getLixijiang().split(",");
                    String[] lixibiliArr = list1.get(i).getLixijiangbili().split(",");
                    String[] yufuArr = list1.get(i).getYufutianshu().split(",");
                    for(int j = 0; j < strArr.length; j++){
                        if(list.get(0).getId().toString().equals(strArr[j]) ){
                            DomesticHuikuan domesticHuikuan =new DomesticHuikuan();
                            BeanUtils.copyProperties(list1.get(i),domesticHuikuan);
                            domesticHuikuan.setShijishiyongjine(shiyongArr[j]);
                            domesticHuikuan.setXiankuanjiang(xiangArr[j]);
                            domesticHuikuan.setXiankuanjiangbili(xianbiligArr[j]);
                            domesticHuikuan.setLixijiang(lixiArr[j]);
                            if(lixibiliArr.length == biaoArr.length){
                                domesticHuikuan.setLixijiangbili(lixibiliArr[j]);
                            }
                            domesticHuikuan.setYufutianshu(yufuArr[j]);
                            lishuikuan.add(domesticHuikuan);
                        }
                    }
                }
                domesticFahuoDto.setHuikuanList(lishuikuan);
                return ResultVo.success(domesticFahuoDto);
            }
            return ResultVo.failed("data","error");
        }

        @ApiOperation(value = "跟新打印状态为不打印", notes = "参数:跟新打印状态为不打印")
        @PostMapping(value = "updatestatus")
        public ResultVo updatestatus( @ApiParam(name = "fahuo", required = true)
                                      @RequestBody DomesticFahuo fahuo){
            fahuo.setDayin("不打印");
            boolean b = domesticFahuoService.updateById(fahuo);
            if(b){
                return  ResultVo.success("data","success");
            }
            return   ResultVo.failed("data","error");
        }

        @ApiOperation(value = "已计算根据ID进行删除为未计算", notes = "参数:已计算根据ID进行删除为未计算")
        @GetMapping("deletestatus/{ids}")
        public ResultVo deletestatus(@PathVariable String ids){
            log.info("======“已计算根据ID进行删除为未计算接口”开始执行======");
            log.info("删除id为："+ids);
            DomesticFahuo  list =   domesticFahuoService.getById(ids);
            if(list != null){
                list.setJijiaticheng("");
                list.setDanhao("");
                list.setQueren("未计算");
                list.setJijiatichengbili("");
                list.setJisuanriqi("");
                List<DomesticHuikuan> lishuikuan = new ArrayList<>();
                List<DomesticHuikuan> list1 = domesticHuikuanService.listHuikuan(ids);
                for (int i = 0; i<list1.size(); i++){
                    boolean ins = false;
                    String[] strArr = list1.get(i).getBiaoshi().split(",");
                    String[] shijiArr = list1.get(i).getShijishiyongjine().split(",");
                    String[] xiangArr = list1.get(i).getXiankuanjiang().split(",");
                    String[] xianbiligArr = list1.get(i).getXiankuanjiangbili().split(",");
                    String[] lixiArr = list1.get(i).getLixijiang().split(",");
                    String[] lixibiliArr = list1.get(i).getLixijiangbili().split(",");
                    String[] yufuArr = list1.get(i).getYufutianshu().split(",");
                    for (int j = 0; j < strArr.length; j++) {
                        log.info("strArr[j]是否为空：" + (strArr[j] == null));
                        log.info("list.getId()的值为："+list.getId());
                        log.info("strArr[j]的值为："+strArr[j]);
                        log.info("比较list.getId()和strArr[j]的值是否相等："+String.valueOf(list.getId()).equals(strArr[j]));
                        log.info("shijiArr.length的值为："+shijiArr.length);
                        log.info("strArr.length的值为："+strArr.length);
                        log.info("比较shijiArr.length和strArr.length是否相等："+(shijiArr.length == strArr.length));
                        if(( !(strArr[j] == null) && String.valueOf(list.getId()).equals(strArr[j])) && (shijiArr.length == strArr.length)){
                            ins=true;
                            BigDecimal   b   =   new BigDecimal(Double.valueOf(shijiArr[j])+Double.valueOf(list1.get(i).getJine()));
                            double  f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                            list1.get(i).setJine(String.valueOf(f1));
                            shijiArr[j] = "0";
                            strArr[j] = "0";
                            xiangArr[j] = "0";
                            xianbiligArr[j] = "0";
                            lixiArr[j] = "0";
                            lixibiliArr[j] = "0";
                            yufuArr[j] = "0";
                        }
                    }
                    if(ins){
                        String shiji="";
                        String xiankuan="";
                        String lixi="";
                        String xianbili="";
                        String lixibili="";
                        String biaoshi="";
                        String yufutian="";
                        for (int k = 0; k <strArr.length ; k++) {
                            if(!strArr[k].equals("0")){
                                shiji+=shijiArr[k]+",";
                                xiankuan+=xiangArr[k]+",";
                                lixi+=strArr[k]+",";
                                xianbili+=xianbiligArr[k]+",";
                                lixibili+=strArr[k]+",";
                                biaoshi+=strArr[k]+",";
                                yufutian+=strArr[k]+",";
                            }
                        }
                        list1.get(i).setShijishiyongjine(shiji);
                        list1.get(i).setXiankuanjiang(xiankuan);
                        list1.get(i).setLixijiang(lixi);
                        list1.get(i).setXiankuanjiangbili(xianbili);
                        list1.get(i).setLixijiangbili(lixibili);
                        list1.get(i).setBiaoshi(biaoshi);
                        list1.get(i).setYufutianshu(yufutian);
                        lishuikuan.add(list1.get(i));
                    }
                }
                domesticHuikuanService.updateBatchById(lishuikuan);
                domesticFahuoService.updateById(list);
                return ResultVo.success("data","success");
            }
            return ResultVo.failed("data","error");
        }


    @ApiOperation(value = "批量已计算根据ID进行删除为未计算", notes = "参数:批量已计算根据ID进行删除为未计算")
    @PostMapping("deletePiLiangstatus")
    public ResultVo deletePiLiangstatus(@ApiParam(name = "list", required = false)
                                        @RequestBody  List<String> ids){
     for (int p = 0; p < ids.size(); p++){
        log.info("======“批量已计算根据ID进行删除为未计算接口”开始执行======");
        log.info("删除id为："+ids);
        DomesticFahuo  list =   domesticFahuoService.getById(ids.get(p));
        if(list != null){
            list.setJijiaticheng("");
            list.setDanhao("");
            list.setQueren("未计算");
            list.setJijiatichengbili("");
            list.setJisuanriqi("");
            List<DomesticHuikuan> lishuikuan = new ArrayList<>();
            List<DomesticHuikuan> list1 = domesticHuikuanService.listHuikuan(ids.get(p));
            for (int i = 0; i<list1.size(); i++){
                boolean ins = false;
                String[] strArr = list1.get(i).getBiaoshi().split(",");
                String[] shijiArr = list1.get(i).getShijishiyongjine().split(",");
                String[] xiangArr = list1.get(i).getXiankuanjiang().split(",");
                String[] xianbiligArr = list1.get(i).getXiankuanjiangbili().split(",");
                String[] lixiArr = list1.get(i).getLixijiang().split(",");
                String[] lixibiliArr = list1.get(i).getLixijiangbili().split(",");
                String[] yufuArr = list1.get(i).getYufutianshu().split(",");
                for (int j = 0; j < strArr.length; j++) {
                    if(( !(strArr[j] == null) && String.valueOf(list.getId()).equals(strArr[j])) && (shijiArr.length == strArr.length)){
                        ins=true;
                        BigDecimal   b   =   new BigDecimal(Double.valueOf(shijiArr[j])+Double.valueOf(list1.get(i).getJine()));
                        double  f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                        list1.get(i).setJine(String.valueOf(f1));
                        shijiArr[j] = "0";
                        strArr[j] = "0";
                        xiangArr[j] = "0";
                        xianbiligArr[j] = "0";
                        lixiArr[j] = "0";
                        lixibiliArr[j] = "0";
                        yufuArr[j] = "0";
                    }
                }
                if(ins){
                    String shiji="";
                    String xiankuan="";
                    String lixi="";
                    String xianbili="";
                    String lixibili="";
                    String biaoshi="";
                    String yufutian="";
                    for (int k = 0; k <strArr.length ; k++) {
                        if(!strArr[k].equals("0")){
                            shiji+=shijiArr[k]+",";
                            xiankuan+=xiangArr[k]+",";
                            lixi+=strArr[k]+",";
                            xianbili+=xianbiligArr[k]+",";
                            lixibili+=strArr[k]+",";
                            biaoshi+=strArr[k]+",";
                            yufutian+=strArr[k]+",";
                        }
                    }
                    list1.get(i).setShijishiyongjine(shiji);
                    list1.get(i).setXiankuanjiang(xiankuan);
                    list1.get(i).setLixijiang(lixi);
                    list1.get(i).setXiankuanjiangbili(xianbili);
                    list1.get(i).setLixijiangbili(lixibili);
                    list1.get(i).setBiaoshi(biaoshi);
                    list1.get(i).setYufutianshu(yufutian);
                    lishuikuan.add(list1.get(i));
                }
            }
            domesticHuikuanService.updateBatchById(lishuikuan);
            domesticFahuoService.updateById(list);
         }else{
            return ResultVo.failed("data","error");
         }
        }
        return ResultVo.success("data","error");
    }



        @ApiOperation(value = "客户进行汇总信息  所有的发货和回款信息", notes = "参数:客户进行汇总信息  所有的发货和回款信息")
        @PostMapping("collectdata")
        public ResultVo collectdata(@ApiParam(name = "domesticFahuo", required = false)
                             @RequestBody DomesticFahuo domesticFahuo){
            List<DomesticFahuo> domestics = domesticFahuoService.seleGroupBy(domesticFahuo);
            DomesticHuikuan huikuan = new DomesticHuikuan();
            huikuan.setHuikuanriqi(domesticFahuo.getDanjuriqi());
            huikuan.setKehumingcheng(domesticFahuo.getShouhuokehu());
            List<DomesticHuikuan> huikuans = domesticFahuoService.seleGrouphuikuanBy(huikuan);

            for (int i = 0; i <domestics.size() ; i++) {
                for (int j = 0; j <huikuans.size() ; j++) {
                    if(domestics.get(i).getShouhuokehu().equals(huikuans.get(j).getKehumingcheng())){
                        Double   lijiang  =   Double.valueOf(domestics.get(i).getJiashuiheji())-Double.valueOf(huikuans.get(j).getJine());
                        BigDecimal   b   =   new BigDecimal(lijiang);
                        double  f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                        domestics.get(i).setDayin(String.valueOf(f1));
                    }
                }
            }
            return  ResultVo.success(domestics);
        }


@ApiOperation(value = "发货已计算信息导出", notes = "参数:发货已计算信息导出")
@PostMapping(value = "/fahuoyijisuanexcle")
public void downloadAllYijisuanClassmate(HttpServletResponse response,  @RequestBody DomesticFahuoDto param) throws IOException {
    int num =0;
    String fileName = "发货数据导出"+".xls";//设置要导出的文件的名字
    HSSFWorkbook workbook = new HSSFWorkbook();
    HSSFSheet sheet = workbook.createSheet("数据导出");
    List<DomesticXishu> list = domesticXishuService.list(null);
    int rowNum = 1;
    String[] headers = { "单据号", "单据日期", "业务员", "运输方式","收获客户","物料名称","数量","含税单价",
            "价税合计","运输单价","包装物","包装费","佣金比例","佣金","新客户","收款协议","基价提成公式","基价提成","回款日期","回款金额"
    ,"承兑日期","先款公式","先款奖","利息奖公式","利息奖","计算日期","打托缠膜"};
    //headers表示excel表中第一行的表头
    HSSFRow row = sheet.createRow(0);
    //在excel表中添加表头
    for(int i=0;i<headers.length;i++){
        HSSFCell cell = row.createCell(i);
        HSSFRichTextString text = new HSSFRichTextString(headers[i]);
        cell.setCellValue(text);
    }
    HSSFRow row1;
    log.info("param的值为："+param);
    List<DomesticFahuoDto> classmatefa = domesticFahuoService.seleteYijisuanexcle(param);
    List<DomesticHuikuan> domesticHuikuans = domesticHuikuanService.seleteYijisuanexcle();
    log.info("domesticHuikuans的值为："+domesticHuikuans);
    String kehuoushu ="";
    String kehujishu ="";
    for (int i = 0; i <classmatefa.size() ; i++){
        int  kkkk =0;
        double xkj = 0;
        double lxj = 0;
        double j = 0;
        double ceshi = 0;
        double diwudang = 0;
        String kehu ="";
        for (int k = 0; k < domesticHuikuans.size(); k++) {
            String biaoji = domesticHuikuans.get(k).getBiaoshi();    //标记信息
            String shijishiyong = domesticHuikuans.get(k).getShijishiyongjine();   // 实际使用金额
            String xiankuanjiang = domesticHuikuans.get(k).getXiankuanjiang();     // 先款奖
            String lixijiang = domesticHuikuans.get(k).getLixijiang();             // 利息奖
            String xiankuanjiangbili = domesticHuikuans.get(k).getXiankuanjiangbili();  // 先款奖比例
            String lixijiangbili = domesticHuikuans.get(k).getLixijiangbili();  // 利息奖
            String yufutianshu = domesticHuikuans.get(k).getYufutianshu();      // 预付天数
            String huikuanriqi = domesticHuikuans.get(k).getHuikuanriqi();      // huikuanriqi
            String chengduiriqi = domesticHuikuans.get(k).getChengduiriqi();    // huikuanriqi
            String jine2 = domesticHuikuans.get(k).getJine2();// 回款金额
            String[] biaojis = biaoji.split(",");
            String[] shijishiyongs = shijishiyong.split(",");
            String[] lixijiangs = lixijiang.split(",");
            String[] xiankuanjiangbilis = xiankuanjiangbili.split(",");
            String[] xiankuanjiangs = xiankuanjiang.split(",");
            String[] yufutianshus = yufutianshu.split(",");
            String[] lixijiangbilis = lixijiangbili.split(",");
            for (int l = 0; l < lixijiangbilis.length; l++){
                String hanshui1 = "";
                String lixi = "";
                String ja = "";
                String xin = "";
                if (biaojis[l].equals(String.valueOf(classmatefa.get(i).getId()))){
                    kkkk++;
                    if (!StringUtils.isEmpty(classmatefa.get(i).getYongjin())){
                        hanshui1 = "(" + classmatefa.get(i).getHanshuidanjia() + "-" + classmatefa.get(i).getYongjin() + ")";
                        if(!StringUtils.isEmpty(classmatefa.get(i).getYongjinbili())){
                            if(!StringUtils.isEmpty(classmatefa.get(i).getChanmofeiyong())){
                                hanshui1 = "(" + classmatefa.get(i).getHanshuidanjia() + "-" + classmatefa.get(i).getYongjin() + "-"+ classmatefa.get(i).getHanshuidanjia() + "*" + classmatefa.get(i).getYongjinbili()+"-" + classmatefa.get(i).getChanmofeiyong() + ")";
                            }else{
                                hanshui1 = "(" + classmatefa.get(i).getHanshuidanjia() +"-" + classmatefa.get(i).getYongjin() + "-" + classmatefa.get(i).getHanshuidanjia() + "*" + classmatefa.get(i).getYongjinbili() + ")";
                            }
                        }else{
                            if(!StringUtils.isEmpty(classmatefa.get(i).getChanmofeiyong())){
                                hanshui1 = "(" + classmatefa.get(i).getHanshuidanjia() + "-" + classmatefa.get(i).getYongjin() + "-" + classmatefa.get(i).getChanmofeiyong() + ")";
                            }
                        }
                    }else if(!StringUtils.isEmpty(classmatefa.get(i).getYongjinbili())) {
                        hanshui1 = "(" + classmatefa.get(i).getHanshuidanjia() + "-" + classmatefa.get(i).getHanshuidanjia() + "*" + classmatefa.get(i).getYongjinbili() + ")";
                        if(!StringUtils.isEmpty(classmatefa.get(i).getChanmofeiyong())){
                            hanshui1 = "(" + classmatefa.get(i).getHanshuidanjia() + "-" + classmatefa.get(i).getHanshuidanjia() + "*" + classmatefa.get(i).getYongjinbili() + "-"+ classmatefa.get(i).getChanmofeiyong()+")";
                        }
                    }else{
                        if(!StringUtils.isEmpty(classmatefa.get(i).getChanmofeiyong())){
                            hanshui1 = "(" + classmatefa.get(i).getHanshuidanjia() + "-" + classmatefa.get(i).getChanmofeiyong() + ")";
                        }else{
                            hanshui1 = classmatefa.get(i).getHanshuidanjia();
                        }
                    }
                    if (Float.valueOf(xiankuanjiangs[l]) != 0) {
                        xin = "(" + hanshui1 + "-" + classmatefa.get(i).getYunshudanjia() + "-" + classmatefa.get(i).getDomesticBaozhuang().getBuhanbaozhuang() + ") * " + xiankuanjiangbilis[l] + "*( " + shijishiyongs[l] + "/" + classmatefa.get(i).getHanshuidanjia() + ")";
                    }else{
                        xin = "0";
                    }
                    if (!lixijiangs[l].equals("0")) {
                        lixi = yufutianshus[l] + "* (" + hanshui1 + "-" + classmatefa.get(i).getYunshudanjia() + "-" + classmatefa.get(i).getDomesticBaozhuang().getBuhanbaozhuang() + ")*" + lixijiangbilis[l] + "* (" + shijishiyongs[l] + "/" + classmatefa.get(i).getHanshuidanjia() + ")";
                    } else {
                        lixi = "0";
                    }
                    if ( list.get(0).getXinkehu3().equals(classmatefa.get(i).getJijiatichengbili())) {
                        ja = "(" + classmatefa.get(i).getJijiatichengbili() + "* " + classmatefa.get(i).getDomesticJijiabiao().getDiwudang() + "+" + list.get(0).getXinkehu3b() + " *(" + hanshui1 + "-" + classmatefa.get(i).getYunshudanjia() + "-" + classmatefa.get(i).getDomesticBaozhuang().getBuhanbaozhuang() + "-" + classmatefa.get(i).getDomesticJijiabiao().getDiwudang() + ")) * " + classmatefa.get(i).getShuliang();
                    } else if (Float.valueOf(classmatefa.get(i).getJijiatichengbili()) <=  0.0001 ) {
                        ja = "0";
                    } else if (classmatefa.get(i).getJijiatichengbili().equals(list.get(0).getJingshui3())){
                        ja = "(" + classmatefa.get(i).getJijiatichengbili() + "* " + classmatefa.get(i).getDomesticJijiabiao().getDiwudang() + " +" + list.get(0).getJingshui3b() + " *( " + hanshui1 + " -  " + classmatefa.get(i).getYunshudanjia() + " - " + classmatefa.get(i).getDomesticBaozhuang().getBuhanbaozhuang() + " - " + classmatefa.get(i).getDomesticJijiabiao().getDiwudang() + ")) * " + classmatefa.get(i).getShuliang();
                    } else {
                        ja = "(" + classmatefa.get(i).getJijiatichengbili() + "* ( " + hanshui1 + " - " + classmatefa.get(i).getYunshudanjia() + " - " + classmatefa.get(i).getDomesticBaozhuang().getBuhanbaozhuang() + " ))* " + classmatefa.get(i).getShuliang();
                    }
                    // 公式已经设置好了。
                    num++;
                    //在表中存放查询到的数据放入对应的列
                    row1 = sheet.createRow(num);
                  if(kkkk == 1){
                    row1.createCell(0).setCellValue(classmatefa.get(i).getDanjuhao());
                    row1.createCell(1).setCellValue(classmatefa.get(i).getDanjuriqi());
                    row1.createCell(2).setCellValue(classmatefa.get(i).getYewuyuan());
                    row1.createCell(3).setCellValue(classmatefa.get(i).getFangshi());
                    row1.createCell(4).setCellValue(classmatefa.get(i).getShouhuokehu());
                    row1.createCell(5).setCellValue(classmatefa.get(i).getWuliaomingcheng());
                    row1.createCell(6).setCellValue(classmatefa.get(i).getShuliang());
                    row1.createCell(7).setCellValue(classmatefa.get(i).getHanshuidanjia());
                    row1.createCell(8).setCellValue(classmatefa.get(i).getJiashuiheji());
                    row1.createCell(9).setCellValue(classmatefa.get(i).getYunshudanjia());
                    row1.createCell(10).setCellValue(classmatefa.get(i).getDomesticBaozhuang().getBaozhuangwu());
                    row1.createCell(11).setCellValue(classmatefa.get(i).getDomesticBaozhuang().getBuhanbaozhuang());
                    row1.createCell(12).setCellValue(classmatefa.get(i).getYongjinbili());
                    row1.createCell(13).setCellValue(classmatefa.get(i).getYongjin());
                    row1.createCell(14).setCellValue(classmatefa.get(i).getShifouweixinkehu());
                    row1.createCell(15).setCellValue(classmatefa.get(i).getShoukuanxieyi());
                    row1.createCell(16).setCellValue(ja);
                    row1.createCell(17).setCellValue(classmatefa.get(i).getJijiaticheng());
                   }
                    row1.createCell(18).setCellValue(domesticHuikuans.get(k).getHuikuanriqi());
                    row1.createCell(19).setCellValue(domesticHuikuans.get(k).getJine2());
                    row1.createCell(20).setCellValue(domesticHuikuans.get(k).getChengduiriqi());
                    row1.createCell(21).setCellValue(xin);
                    row1.createCell(22).setCellValue(xiankuanjiangs[l]);
                    row1.createCell(23).setCellValue(lixi);
                    row1.createCell(24).setCellValue(lixijiangs[l]);
                    row1.createCell(25).setCellValue(classmatefa.get(i).getJisuanriqi());
                    row1.createCell(26).setCellValue(classmatefa.get(i).getChanmofeiyong());
                }
            } //拆分循环结束
        } // 回款循环结束
    }  //发货循环结束
    response.setContentType("application/octet-stream");
    response.setHeader("Content-disposition", "attachment;filename=" + fileName);
    response.flushBuffer();
    workbook.write(response.getOutputStream());
  }

//   客户余额表
    @ApiOperation(value = "客户余额表", notes = "参数:客户余额表")
    @PostMapping(value = "/yuebiao")
    public ResultVo yuebiao( @ApiParam(name = "fahuo", required = true)
                                  @RequestBody DomesticFahuo fahuo){
           List<DomesticFahuo> lisfahuo = domesticFahuoService.seleteYuebiaoFa(fahuo);
           List<DomesticHuikuan> listhuikuan = domesticHuikuanService.seleteYuebiaoHuikuan();
           Map<String,Float> map =new HashMap<>();
           for (int i = 0; i < lisfahuo.size(); i++) {
             for (int j = 0; j < listhuikuan.size() ; j++) {
                   if(lisfahuo.get(i).getShouhuokehu().equals(listhuikuan.get(j).getKehumingcheng())){
                       map.put(lisfahuo.get(i).getShouhuokehu(),Float.valueOf(lisfahuo.get(i).getJiashuiheji())-Float.valueOf(listhuikuan.get(j).getJine2()));
                   }
              }
           }
        for (int i = 0; i < lisfahuo.size(); i++) {
            if(!map.containsKey(lisfahuo.get(i).getShouhuokehu())){
                map.put(lisfahuo.get(i).getShouhuokehu(),Float.valueOf(lisfahuo.get(i).getJiashuiheji()));
            }
        }
        for (int j = 0; j < listhuikuan.size(); j++) {
            if(!map.containsKey(listhuikuan.get(j).getKehumingcheng())){
                map.put(listhuikuan.get(j).getKehumingcheng(),-Float.valueOf(listhuikuan.get(j).getJine2()));
            }
        }
        return   ResultVo.success(map);
    }

    @ApiOperation(value = "客户余额表导出", notes = "参数:客户余额表导出")
    @PostMapping(value = "/yueexcle")
    public void yueexcle(HttpServletResponse response, DomesticFahuo  fahuo) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("数据导出");
        List<DomesticFahuo> lisfahuo = domesticFahuoService.seleteYuebiaoFa(fahuo);
        List<DomesticHuikuan> listhuikuan = domesticHuikuanService.seleteYuebiaoHuikuan();
        Map<String,Float> map =new HashMap<>();
        for (int i = 0; i < lisfahuo.size(); i++) {
            for (int j = 0; j < listhuikuan.size() ; j++) {
                if(lisfahuo.get(i).getShouhuokehu().equals(listhuikuan.get(j).getKehumingcheng())){
                    map.put(lisfahuo.get(i).getShouhuokehu(),Float.valueOf(lisfahuo.get(i).getJiashuiheji())-Float.valueOf(listhuikuan.get(j).getJine2()));
                }
            }
        }
        for (int i = 0; i < lisfahuo.size(); i++) {
            if(!map.containsKey(lisfahuo.get(i).getShouhuokehu())){
                map.put(lisfahuo.get(i).getShouhuokehu(),Float.valueOf(lisfahuo.get(i).getJiashuiheji()));
            }
        }
        for (int j = 0; j < listhuikuan.size(); j++) {
            if(!map.containsKey(listhuikuan.get(j).getKehumingcheng())){
                map.put(listhuikuan.get(j).getKehumingcheng(),-Float.valueOf(listhuikuan.get(j).getJine2()));
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