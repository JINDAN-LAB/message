package com.jindan.jdy.controller.waimao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.JdySspDto;
import com.jindan.jdy.common.dto.WaimaoFahuoDto;
import com.jindan.jdy.common.dto.WaimaoFahuoHuikuan;
import com.jindan.jdy.common.pojo.*;
import com.jindan.jdy.controller.utils.CommonUtils;
import com.jindan.jdy.controller.utils.WorkbookUtils;
import com.jindan.jdy.service.user.JdyRoleService;
import com.jindan.jdy.service.waimao.*;
import com.jindan.jdy.common.utils.api.ResultVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
*
* <p>说明： 规则API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年4月20日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "外贸报表发货源数据")
@RestController
@RequestMapping("/waimaoFahuo")
public class WaimaoFahuoController{

        @Autowired
        WaimaoTargetAccomplishService chaxunhuilv;

        @Autowired
        WaimaoTargetService waimaoTargetService;

        @Autowired
        WaimaoFahuoService waimaoFahuoService;

        @Autowired
        WaimaoAreaService waimaoAreaService;

        @Autowired
        WaimaoHuikuanService waimaoHuikuanService;


        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        DecimalFormat decimalFormat1 = new DecimalFormat("0.000");
        @ApiOperation(value = "发货信息导入", notes = "参数:发货信息导入")
        @PostMapping("/addfahuo")
        public ResultVo addfahuo(@RequestParam("file") MultipartFile file) throws Exception {
            //创建Excel工作薄
            Workbook work = WorkbookUtils.getWorkbook(file.getInputStream(),file.getOriginalFilename());
            System.out.println("zhixingle1111111");
            if(null == work){
                throw new Exception("创建Excel工作薄为空！");
            }
            System.out.println("work.getNumberOfSheets();"+ work.getNumberOfSheets());
            Sheet sheet  = work.getSheetAt(0);
            if(sheet==null){
                throw new Exception("创建Excel工作薄为空！");
            }
            List<WaimaoFahuo> jijiabiaos = new ArrayList<>();
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                Row row = sheet.getRow(j);
                if(row==null||row.getFirstCellNum()==j){continue;}
                WaimaoFahuo jijiabiao = new WaimaoFahuo();
                jijiabiao.setDanjuhao(row.getCell(0).getStringCellValue());
                jijiabiao.setFahuotime(row.getCell(1).getStringCellValue());
                jijiabiao.setFapiaohao(row.getCell(2).getStringCellValue());
                jijiabiao.setHetonghao(row.getCell(3).getStringCellValue());
                jijiabiao.setPici(row.getCell(4).getStringCellValue());
                jijiabiao.setYewuyuan(row.getCell(5).getStringCellValue());
                jijiabiao.setShouhuokehu(row.getCell(6).getStringCellValue());
                jijiabiao.setDiqufenlei(row.getCell(7).getStringCellValue());
                jijiabiao.setYujizhuangtime(row.getCell(8).getStringCellValue());
                jijiabiao.setWuliaoming(row.getCell(9).getStringCellValue());
                if(row.getCell(10)!=null){
                    row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
                    jijiabiao.setShuliang( row.getCell(10).getStringCellValue() );
                }
                if(row.getCell(11)!=null) {
                    row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
                    jijiabiao.setHanshuidanjia( row.getCell(11).getStringCellValue() );
                }
                if(row.getCell(12)!=null) {
                    row.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
                    jijiabiao.setBizhong(row.getCell(12).getStringCellValue());
                }
                if(row.getCell(13)!=null) {
                    row.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
                    jijiabiao.setJiagetiaokuan(row.getCell(13).getStringCellValue());
                }
                if(row.getCell(14)!=null) {
                    row.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
                    jijiabiao.setJiashuiheji((row.getCell(14).getStringCellValue()));
                }
                if(row.getCell(15)!=null) {
                    row.getCell(15).setCellType(Cell.CELL_TYPE_STRING);
                    jijiabiao.setBenbijiashuiheji(row.getCell(15).getStringCellValue());
                }
                if(row.getCell(16)!=null) {
                    row.getCell(16).setCellType(Cell.CELL_TYPE_STRING);
                    jijiabiao.setBaozhuangwu((row.getCell(16).getStringCellValue()));
                }
                if(row.getCell(17)!=null) {
                    row.getCell(17).setCellType(Cell.CELL_TYPE_STRING);
                    jijiabiao.setHuikuanxieyi( row.getCell(17).getStringCellValue());
                }
                if(row.getCell(18)!=null) {
                    row.getCell(18).setCellType(Cell.CELL_TYPE_STRING);
                    jijiabiao.setYunshudanjia(row.getCell(18).getStringCellValue());
                }
                if(row.getCell(19)!=null) {
                    row.getCell(19).setCellType(Cell.CELL_TYPE_STRING);
                    jijiabiao.setYunshujine( row.getCell(19).getStringCellValue());
                }
                if(row.getCell(20)!=null) {
                    row.getCell(20).setCellType(Cell.CELL_TYPE_STRING);
                    jijiabiao.setHuilv(row.getCell(20).getStringCellValue());
                }
                if(row.getCell(21)!=null) {
                    row.getCell(21).setCellType(Cell.CELL_TYPE_STRING);
                    jijiabiao.setFobzongjia(row.getCell(21).getStringCellValue());
                }
                if(row.getCell(22)!=null) {
                    row.getCell(22).setCellType(Cell.CELL_TYPE_STRING);
                    jijiabiao.setHaiyunfei(row.getCell(22).getStringCellValue());
                }
                jijiabiaos.add(jijiabiao);
            }
            waimaoFahuoService.saveBatch(jijiabiaos);
            return ResultVo.success();
        }

        @ApiOperation(value = "查询外贸发货信息", notes = "参数:查询外贸发货信息")
        @PostMapping("/seleteWaimaoFahuo")
        public ResultVo seleteWaimaoFahuo(@ApiParam(value = "jdyRole", required = false)
                                         @RequestBody WaimaoFahuoDto jdyRole){
            System.out.println("===========");
            System.out.println(jdyRole);
            Page<WaimaoFahuo> list = waimaoFahuoService.seletelist(jdyRole);
            return  ResultVo.success(list);
        }

        @ApiOperation("更新发货信息")
        @PostMapping("/updateWaimaoFahuo")
        public ResultVo updatefacility(@ApiParam(value = "jdyRole", required = true)
                                       @RequestBody WaimaoFahuo jdyRole){
            boolean b = waimaoFahuoService.updateById(jdyRole);
            if(b){
                return ResultVo.success(jdyRole);
            }
            return ResultVo.failed();
        }

        @ApiOperation("新增外贸发货信息")
        @PostMapping("/addWaimaoFahuo")
        public ResultVo addWaimaoFahuo( @ApiParam(name = "jdyRole", required = true)
                                    @RequestBody WaimaoFahuo jdyRole){
            boolean save = waimaoFahuoService.save(jdyRole);
            if(save){
                return ResultVo.success(jdyRole);
            }
            return ResultVo.failed();
        }

        @ApiOperation("删除外贸发货信息")
        @DeleteMapping("/deleteWaimaoFahuo/{id}")
        public ResultVo deleteWaimaoFahuo(@ApiParam(value = "id", name = "角色ID", required = true) @PathVariable String  id){
            boolean b = waimaoFahuoService.removeById(id);
            if(b){
                return ResultVo.success();
            }
            return ResultVo.failed();
        }


        public Float init(WaimaoFahuo jdyRole,WaimaoTargetAccomplishService chaxunhuilv){
            List<WaimaoTargetAccomplish> seletelist = chaxunhuilv.seletehuilvlist();
           if(seletelist.size() > 0){
            switch (jdyRole.getFahuotime()){
            case "01":
                return  Float.valueOf(seletelist.get(0).getYiyue());
            case "02":
                return Float.valueOf(seletelist.get(0).getEryue());
            case "03":
                return Float.valueOf(seletelist.get(0).getSanyue());
            case "04":
                return Float.valueOf(seletelist.get(0).getSiyue());
            case "05":
                return Float.valueOf(seletelist.get(0).getWuyue());
            case "06":
                return Float.valueOf(seletelist.get(0).getLiuyue());
            case "07":
                return Float.valueOf(seletelist.get(0).getQiyue());
            case "08":
                return Float.valueOf(seletelist.get(0).getBayue());
            case "09":
                return Float.valueOf(seletelist.get(0).getJiuyue());
            case "10":
                return Float.valueOf(seletelist.get(0).getShiyue());
            case "11":
                return Float.valueOf(seletelist.get(0).getShiyiyue());
            case "12":
                 return Float.valueOf(seletelist.get(0).getShieryue());
           default:
                return 1.0f;
            }
           }else{
               return 1.0f;
          }
        }

        @ApiOperation("导出当月的发货信息")
        @PostMapping(value = "/DangYuefenExcle")
        public void DangYuefenExcle(HttpServletResponse response, WaimaoFahuo param) throws IOException {
            Float jiashuiheji =0f;
            Float fobzongjia =0f;
            Float nums =0f;
            Float inithuilv = init(param,chaxunhuilv);
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("数据导出");
            List<WaimaoFahuo> classmatefa = waimaoFahuoService.seleteDangyuefenDayuAlllist(param);  // 查询出来的是发货明细表和回款明细表
            for (int i = 0; i < classmatefa.size(); i++) {
                if(classmatefa.get(i).getBizhong().trim().equals("人民币")){
                    System.out.println("人民币的信息进行调整"+classmatefa.get(i).getDanjuhao());
                    classmatefa.get(i).setJiashuiheji(decimalFormat.format( Float.valueOf(classmatefa.get(i).getJiashuiheji())/inithuilv));
                    classmatefa.get(i).setFobzongjia(  decimalFormat.format( Float.valueOf(classmatefa.get(i).getFobzongjia())/inithuilv));
                    classmatefa.get(i).setBizhong("美元");
                }
            }
            String fileName = param.getFahuotime()+"发货信息"+".xls";//设置要导出的文件的名字

            int rowNum = 1;
            String[] headers = {
                    "单据号", "发货日期","发票号","合同号","批次","业务员","收获客户","地区分类",
                    "预计装船日期","物料名称","数量","含税单价","价格条款","币种","价税合计","本币加税合计",
                    "包装物","回款协议","运输单价","运输金额","汇率","fob总价","海运费"
            };
            HSSFRow row = sheet.createRow(0);
            for(int i=0;i<headers.length;i++){
                HSSFCell cell = row.createCell(i);
                HSSFRichTextString text = new HSSFRichTextString(headers[i]);
                cell.setCellValue(text);
            }
            HSSFRow row1;
            for (int i = 0; i <classmatefa.size() ; i++){
                jiashuiheji =jiashuiheji+ Float.valueOf(classmatefa.get(i).getJiashuiheji());
                fobzongjia =fobzongjia+ Float.valueOf(classmatefa.get(i).getFobzongjia());
                nums = nums + Float.valueOf(classmatefa.get(i).getShuliang());
                row1 = sheet.createRow(rowNum);
                row1.createCell(0).setCellValue(classmatefa.get(i).getDanjuhao());
                row1.createCell(1).setCellValue(classmatefa.get(i).getFahuotime());
                row1.createCell(2).setCellValue(classmatefa.get(i).getFapiaohao());
                row1.createCell(3).setCellValue(classmatefa.get(i).getHetonghao());
                row1.createCell(4).setCellValue(classmatefa.get(i).getPici());
                row1.createCell(5).setCellValue(classmatefa.get(i).getYewuyuan());
                row1.createCell(6).setCellValue(classmatefa.get(i).getShouhuokehu());
                row1.createCell(7).setCellValue(classmatefa.get(i).getDiqufenlei());
                row1.createCell(8).setCellValue(classmatefa.get(i).getYujizhuangtime());
                row1.createCell(9).setCellValue(classmatefa.get(i).getWuliaoming());
                row1.createCell(10).setCellValue(Float.valueOf(classmatefa.get(i).getShuliang()));
                row1.createCell(11).setCellValue(Float.valueOf(classmatefa.get(i).getHanshuidanjia()));
                row1.createCell(12).setCellValue(classmatefa.get(i).getJiagetiaokuan());
                row1.createCell(13).setCellValue(classmatefa.get(i).getBizhong());
                row1.createCell(14).setCellValue(Float.valueOf(classmatefa.get(i).getJiashuiheji()));
                row1.createCell(15).setCellValue(Float.valueOf(classmatefa.get(i).getBenbijiashuiheji()));
                row1.createCell(16).setCellValue(classmatefa.get(i).getBaozhuangwu());
                row1.createCell(17).setCellValue(classmatefa.get(i).getHuikuanxieyi());
                row1.createCell(18).setCellValue(Float.valueOf(classmatefa.get(i).getYunshudanjia()));
                row1.createCell(19).setCellValue(Float.valueOf(classmatefa.get(i).getYunshujine()));
                row1.createCell(20).setCellValue(Float.valueOf(decimalFormat1.format(Float.valueOf(classmatefa.get(i).getHuilv()))));
                row1.createCell(21).setCellValue(Float.valueOf(decimalFormat1.format(Float.valueOf(classmatefa.get(i).getFobzongjia()))));
                row1.createCell(22).setCellValue(Float.valueOf(classmatefa.get(i).getHaiyunfei()));
                rowNum++;
                if(classmatefa.size()-1 == i){
                    row1 = sheet.createRow(rowNum);
                    row1.createCell(0).setCellValue("合计：");
                    row1.createCell(10).setCellValue(nums);
                    row1.createCell(14).setCellValue(jiashuiheji);
                    row1.createCell(21).setCellValue(fobzongjia);
                }
            }
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
            response.flushBuffer();
            workbook.write(response.getOutputStream());
        }


        @ApiOperation("月份的发货信息导出excle")
        @PostMapping(value = "/debtYuefenExcle")
        public void debyuefentExcle(HttpServletResponse response, WaimaoFahuo param) throws IOException {
            Float inithuilv = init(param,chaxunhuilv);
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("数据导出");
            List<WaimaoFahuo> classmatefa = waimaoFahuoService.seleteYuefenDayuAlllist(param);  // 查询出来的是发货明细表和回款明细表
            for (int i = 0; i < classmatefa.size(); i++) {
                if(classmatefa.get(i).getBizhong().equals("人民币")){
                    classmatefa.get(i).setJiashuiheji(decimalFormat.format(Float.valueOf(classmatefa.get(i).getJiashuiheji())/ inithuilv));
                    classmatefa.get(i).setFobzongjia(decimalFormat.format(Float.valueOf(classmatefa.get(i).getFobzongjia())/inithuilv));
                    classmatefa.get(i).setBizhong("美元");
                }
            }
    //      大类中的欠款表
            String fileName = "截止到"+param.getFahuotime()+"月份的发货信息"+".xls";//设置要导出的文件的名字
    //        //新增数据行，并且设置单元格数据
            int rowNum = 1;
            String[] headers = {
                    "单据号", "发货日期","发票号","合同号","批次","业务员","收获客户","地区分类",
                    "预计装船日期","物料名称","数量","含税单价","价格条款","币种","价税合计","本币加税合计",
                    "包装物","回款协议","运输单价","运输金额","汇率","fob总价","海运费"
            };
    //      headers表示excel表中第一行的表头
            HSSFRow row = sheet.createRow(0);
    //        //在excel表中添加表头
            for(int i=0;i<headers.length;i++){
                HSSFCell cell = row.createCell(i);
                HSSFRichTextString text = new HSSFRichTextString(headers[i]);
                cell.setCellValue(text);
            }
            HSSFRow row1;
    //        //在表中存放查询到的数据放入对应的列
            for (int i = 0; i <classmatefa.size() ; i++){
                row1 = sheet.createRow(rowNum);
                row1.createCell(0).setCellValue(classmatefa.get(i).getDanjuhao());
                row1.createCell(1).setCellValue(classmatefa.get(i).getFahuotime());
                row1.createCell(2).setCellValue(classmatefa.get(i).getFapiaohao());
                row1.createCell(3).setCellValue(classmatefa.get(i).getHetonghao());
                row1.createCell(4).setCellValue(classmatefa.get(i).getPici());
                row1.createCell(5).setCellValue(classmatefa.get(i).getYewuyuan());
                row1.createCell(6).setCellValue(classmatefa.get(i).getShouhuokehu());
                row1.createCell(7).setCellValue(classmatefa.get(i).getDiqufenlei());
                row1.createCell(8).setCellValue(classmatefa.get(i).getYujizhuangtime());
                row1.createCell(9).setCellValue(classmatefa.get(i).getWuliaoming());
                row1.createCell(10).setCellValue(Float.valueOf(classmatefa.get(i).getShuliang()));
                row1.createCell(11).setCellValue(Float.valueOf(classmatefa.get(i).getHanshuidanjia()));
                row1.createCell(12).setCellValue(classmatefa.get(i).getJiagetiaokuan());
                row1.createCell(13).setCellValue(classmatefa.get(i).getBizhong());
                row1.createCell(14).setCellValue(Float.valueOf(classmatefa.get(i).getJiashuiheji()));
                row1.createCell(15).setCellValue(Float.valueOf(classmatefa.get(i).getBenbijiashuiheji()));
                row1.createCell(16).setCellValue(classmatefa.get(i).getBaozhuangwu());
                row1.createCell(17).setCellValue(classmatefa.get(i).getHuikuanxieyi());
                row1.createCell(18).setCellValue(Float.valueOf(classmatefa.get(i).getYunshudanjia()));
                row1.createCell(19).setCellValue(Float.valueOf(classmatefa.get(i).getYunshujine()));
                row1.createCell(20).setCellValue(Float.valueOf(decimalFormat1.format(Float.valueOf(classmatefa.get(i).getHuilv()))));
                row1.createCell(21).setCellValue(Float.valueOf(decimalFormat1.format(classmatefa.get(i).getFobzongjia())));
                row1.createCell(22).setCellValue(Float.valueOf(classmatefa.get(i).getHaiyunfei()));
                rowNum++;
            }
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
            response.flushBuffer();
            workbook.write(response.getOutputStream());
        }


        //  分类导出
        @ApiOperation(value = "分类导出", notes = "参数:查询外贸发货信息")
        @PostMapping("/seleteAlllistgroupby")
        public void seleteAlllistgroupby(HttpServletResponse response, @ApiParam(value = "jdyRole", required = false)
                                          @RequestBody WaimaoFahuo  jdyRole) throws IOException{
            Float inithuilv = init(jdyRole,chaxunhuilv);
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("数据导出");
            Map<String,WaimaoFahuo> hashmap = new HashMap<>();
            List<WaimaoFahuo> list = waimaoFahuoService.seleteYuefenTongji(jdyRole);
            for (int i = 0; i <list.size() ; i++){
                if(hashmap.containsKey(list.get(i).getWuliaoming())){
                      if(list.get(i).getBizhong().equals("人民币")){
                          hashmap.get(list.get(i).getWuliaoming()).setShuliang(String.valueOf(Float.valueOf(hashmap.get(list.get(i).getWuliaoming()).getShuliang()) + Float.valueOf(list.get(i).getShuliang())));
                          hashmap.get(list.get(i).getWuliaoming()).setJiashuiheji( decimalFormat1.format(Float.valueOf(hashmap.get(list.get(i).getWuliaoming()).getJiashuiheji()) + Float.valueOf(list.get(i).getJiashuiheji())/inithuilv));
                          hashmap.get(list.get(i).getWuliaoming()).setFobzongjia(decimalFormat1.format(Float.valueOf(hashmap.get(list.get(i).getWuliaoming()).getFobzongjia())+Float.valueOf(list.get(i).getFobzongjia())/inithuilv));
                      }else{
                          hashmap.get(list.get(i).getWuliaoming()).setShuliang( String.valueOf(Float.valueOf(hashmap.get(list.get(i).getWuliaoming()).getShuliang()) + Float.valueOf(list.get(i).getShuliang()) ));
                          hashmap.get(list.get(i).getWuliaoming()).setJiashuiheji( String.valueOf(Float.valueOf(hashmap.get(list.get(i).getWuliaoming()).getJiashuiheji()) + Float.valueOf(list.get(i).getJiashuiheji())));
                          hashmap.get(list.get(i).getWuliaoming()).setFobzongjia(String.valueOf(Float.valueOf(hashmap.get(list.get(i).getWuliaoming()).getFobzongjia())+Float.valueOf(list.get(i).getFobzongjia())));
                      }
                }else{
                    if(list.get(i).getBizhong().equals("人民币")){
                        list.get(i).setShuliang(String.valueOf(Float.valueOf(list.get(i).getShuliang())));
                        list.get(i).setJiashuiheji( String.valueOf(Float.valueOf(list.get(i).getJiashuiheji())/inithuilv));
                        list.get(i).setFobzongjia(String.valueOf(Float.valueOf(list.get(i).getFobzongjia())/inithuilv));
                        hashmap.put(list.get(i).getWuliaoming(),list.get(i));
                    }else{
                        hashmap.put(list.get(i).getWuliaoming(),list.get(i));
                    }
                }
            }
            String fileName = "分类导出"+".xls";
            int rowNum = 1;
            HSSFRow row1;
            String[] headers = {"物料名称", "数量", "销售平均单价",
                    "销售金额","fob平均单价","fob总价"};
            HSSFRow row = sheet.createRow(0);
            for(int i=0;i<headers.length;i++){
                HSSFCell cell = row.createCell(i);
                HSSFRichTextString text = new HSSFRichTextString(headers[i]);
                cell.setCellValue(text);
            }
            for(WaimaoFahuo value : hashmap.values()){
                row1 = sheet.createRow(rowNum);
                row1.createCell(0).setCellValue(value.getWuliaoming());
                row1.createCell(1).setCellValue(Float.valueOf(decimalFormat1.format(Float.valueOf(value.getShuliang()))));
                row1.createCell(2).setCellValue(Float.valueOf(decimalFormat1.format(Float.valueOf(value.getJiashuiheji())/Float.valueOf(value.getShuliang()))));
                row1.createCell(3).setCellValue(Float.valueOf(decimalFormat1.format(Float.valueOf(value.getJiashuiheji()))));
                row1.createCell(4).setCellValue(Float.valueOf(decimalFormat1.format(Float.valueOf(value.getFobzongjia())/Float.valueOf(value.getShuliang()))));
                row1.createCell(5).setCellValue(Float.valueOf(decimalFormat1.format(Float.valueOf(value.getFobzongjia()))));
                rowNum++;
            }
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
            response.flushBuffer();
            workbook.write(response.getOutputStream());
        }


        // 销售区域统计表
        @ApiOperation(value = "销售区域统计表", notes = "参数:销售区域统计表")
        @PostMapping("/seletetSalesErritory")
        public void seletetSalesErritory(HttpServletResponse response,@ApiParam(value = "jdyRole", required = false)
                                         @RequestBody WaimaoFahuo  jdyRole) throws IOException {
            HSSFWorkbook workbook = new HSSFWorkbook();
            Float inithuilv = init(jdyRole,chaxunhuilv);
            HSSFSheet sheet = workbook.createSheet("数据导出");
            Map<String,Map<String,  WaimaoFahuo>> hashmap = new HashMap<>();
    //        获取销售区域的信息
             List<WaimaoArea> waimaoAreaList =  waimaoAreaService.seleteQuyulist();
            List<WaimaoFahuo> list = waimaoFahuoService.seleteYuefenTongji(jdyRole);
            for (int i = 0; i <list.size() ; i++){
                if(hashmap.containsKey(list.get(i).getDiqufenlei())) {
                    if (hashmap.get(list.get(i).getDiqufenlei()).containsKey(list.get(i).getWuliaoming())) {
                        if(list.get(i).getBizhong().equals("人民币")){
                            hashmap.get(list.get(i).getDiqufenlei()).get(list.get(i).getWuliaoming()).setShuliang( String.valueOf(Float.valueOf(hashmap.get(list.get(i).getDiqufenlei()).get(list.get(i).getWuliaoming()).getShuliang()) + Float.valueOf(list.get(i).getShuliang())));
                            hashmap.get(list.get(i).getDiqufenlei()).get(list.get(i).getWuliaoming()).setJiashuiheji( String.valueOf(Float.valueOf(hashmap.get(list.get(i).getDiqufenlei()).get(list.get(i).getWuliaoming()).getJiashuiheji()) + Float.valueOf(list.get(i).getJiashuiheji())/inithuilv));
                            hashmap.get(list.get(i).getDiqufenlei()).get(list.get(i).getWuliaoming()).setFobzongjia(String.valueOf(Float.valueOf(hashmap.get(list.get(i).getDiqufenlei()).get(list.get(i).getWuliaoming()).getFobzongjia())+Float.valueOf(list.get(i).getFobzongjia())/inithuilv));
                            hashmap.get(list.get(i).getDiqufenlei()).get(list.get(i).getWuliaoming()).setBizhong("美元");
                        }else{
                            hashmap.get(list.get(i).getDiqufenlei()).get(list.get(i).getWuliaoming()).setShuliang( String.valueOf(Float.valueOf(hashmap.get(list.get(i).getDiqufenlei()).get(list.get(i).getWuliaoming()).getShuliang()) + Float.valueOf(list.get(i).getShuliang())));
                            hashmap.get(list.get(i).getDiqufenlei()).get(list.get(i).getWuliaoming()).setJiashuiheji( String.valueOf(Float.valueOf(hashmap.get(list.get(i).getDiqufenlei()).get(list.get(i).getWuliaoming()).getJiashuiheji()) + Float.valueOf(list.get(i).getJiashuiheji())));
                            hashmap.get(list.get(i).getDiqufenlei()).get(list.get(i).getWuliaoming()).setFobzongjia(String.valueOf(Float.valueOf(hashmap.get(list.get(i).getDiqufenlei()).get(list.get(i).getWuliaoming()).getFobzongjia())+Float.valueOf(list.get(i).getFobzongjia())));
                        }
                      }else{  // 物料不存在的情况下
                        if(list.get(i).getBizhong().equals("人民币")){
                            WaimaoFahuo  waimaoFahuo =new WaimaoFahuo();
                            waimaoFahuo.setShuliang(String.valueOf(Float.valueOf(list.get(i).getShuliang())));
                            waimaoFahuo.setJiashuiheji(String.valueOf(Float.valueOf(list.get(i).getJiashuiheji())/inithuilv));
                            waimaoFahuo.setFobzongjia(String.valueOf(Float.valueOf(list.get(i).getFobzongjia())/inithuilv));
                            hashmap.get(list.get(i).getDiqufenlei()).get(list.get(i).getWuliaoming()).setBizhong("美元");
                            hashmap.get(list.get(i).getDiqufenlei()).put(list.get(i).getWuliaoming(),waimaoFahuo);
                        }else{
                            hashmap.get(list.get(i).getDiqufenlei()).put(list.get(i).getWuliaoming(),list.get(i));
                        }
                    }
                } else {   //地区分类不存在的情况下
                    Map<String,WaimaoFahuo> maps =new HashMap<>();
                    if(!list.get(i).getBizhong().equals("人民币")){
                        maps.put(list.get(i).getWuliaoming(),list.get(i));
                    }else{
                        WaimaoFahuo  waimaoFahuo =new WaimaoFahuo();
                        waimaoFahuo.setJiashuiheji(String.valueOf(Float.valueOf(list.get(i).getJiashuiheji())/inithuilv));
                        waimaoFahuo.setFobzongjia(String.valueOf(Float.valueOf(list.get(i).getFobzongjia())/inithuilv));
                        waimaoFahuo.setShuliang(String.valueOf(Float.valueOf(list.get(i).getFobzongjia())/inithuilv));
                        maps.put(list.get(i).getWuliaoming(),waimaoFahuo);
                    }
                    hashmap.put(list.get(i).getDiqufenlei(),maps);
                   }
                }   // for循环结束
            String fileName = "销售区域统计表"+".xls";
            int rowNum = 1;
            String[] headers = {"大区", "目的国", "物料名称",
                    "数量(吨)","销售平均单价","销售金额","FOB平均单价","FOB总价"};
            HSSFRow row = sheet.createRow(0);
            for(int i=0;i<headers.length;i++){
                HSSFCell cell = row.createCell(i);
                HSSFRichTextString text = new HSSFRichTextString(headers[i]);
                cell.setCellValue(text);
            }
            HSSFRow row1;
            boolean index1 = false;
            boolean index2 = false;
            for(String value : hashmap.keySet()){
                index1 = true;
                Map<String, WaimaoFahuo> stringWaimaoFahuoMap = hashmap.get(value);
                for (String values : stringWaimaoFahuoMap.keySet()){
                    for (int i = 0; i <waimaoAreaList.size() ; i++) {
                       if(waimaoAreaList.get(i).getDestinations().equals(value)){
                        row1 = sheet.createRow(rowNum);
                        if(index1){
                            row1.createCell(0).setCellValue(waimaoAreaList.get(i).getRegions());
                            row1.createCell(1).setCellValue(value);
                        }
                        row1.createCell(2).setCellValue(stringWaimaoFahuoMap.get(values).getWuliaoming()); //物料名称
                        row1.createCell(3).setCellValue(Float.valueOf(stringWaimaoFahuoMap.get(values).getShuliang())); // 数量
                        row1.createCell(4).setCellValue(Float.valueOf((Float.valueOf(stringWaimaoFahuoMap.get(values).getJiashuiheji())/Float.valueOf(stringWaimaoFahuoMap.get(values).getShuliang())))); // 平均销售单价
                        row1.createCell(5).setCellValue(Float.valueOf(stringWaimaoFahuoMap.get(values).getJiashuiheji())); //加税合计
                        row1.createCell(6).setCellValue(Float.valueOf((Float.valueOf(stringWaimaoFahuoMap.get(values).getFobzongjia())/Float.valueOf(stringWaimaoFahuoMap.get(values).getShuliang())))); // FOB 总价
                        row1.createCell(7).setCellValue(Float.valueOf(stringWaimaoFahuoMap.get(values).getFobzongjia()));
                        rowNum++;
                        index1 = false;
                        break;
                      }
                   }

                }
            }
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
            response.flushBuffer();
            workbook.write(response.getOutputStream());
          }




    //   客户排名表信息
        @ApiOperation(value = "客户排名表统计信息", notes = "参数:客户排名表统计信息")
        @PostMapping("/seleteTranking")
        public void seleteTranking(HttpServletResponse response,@ApiParam(value = "jdyRole", required = false)
                                   @RequestBody WaimaoFahuo  jdyRole) throws IOException {
            Float inithuilv = init(jdyRole,chaxunhuilv);
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("数据导出");
            List<WaimaoFahuo> listpaiming = waimaoFahuoService.seletePaimingTranking(jdyRole);   //按照销量排名
            List<WaimaoFahuo> list = waimaoFahuoService.seleteYuefenTongji(jdyRole);
            String fileName = "客户排名表统计信息"+".xls";
            int rowNum = 1;
            String[] headers = {"排名", "业务经理", "收获客户",
                    "物料名称","数量(吨)","平均销售单价","加税合计","FOB平均单价","FOB总价","权重"};
            HSSFRow row = sheet.createRow(0);
            for(int i=0;i<headers.length;i++){
                HSSFCell cell = row.createCell(i);
                HSSFRichTextString text = new HSSFRichTextString(headers[i]);
                cell.setCellValue(text);
            }
            Map<String,Map<String,  WaimaoFahuo>> hashmap = new HashMap<>();
            for (int i = 0; i <list.size() ; i++){
                if(hashmap.containsKey(list.get(i).getShouhuokehu())){
                    if (hashmap.get(list.get(i).getShouhuokehu()).containsKey(list.get(i).getWuliaoming())) {
                        if(list.get(i).getBizhong().equals("人民币")){
                            hashmap.get(list.get(i).getShouhuokehu()).get(list.get(i).getWuliaoming()).setShuliang( String.valueOf(Float.valueOf(hashmap.get(list.get(i).getShouhuokehu()).get(list.get(i).getWuliaoming()).getShuliang()) + Float.valueOf(list.get(i).getShuliang())));
                            hashmap.get(list.get(i).getShouhuokehu()).get(list.get(i).getWuliaoming()).setJiashuiheji( String.valueOf(Float.valueOf(hashmap.get(list.get(i).getShouhuokehu()).get(list.get(i).getWuliaoming()).getJiashuiheji()) + Float.valueOf(list.get(i).getJiashuiheji())));
                            hashmap.get(list.get(i).getShouhuokehu()).get(list.get(i).getWuliaoming()).setFobzongjia(String.valueOf(Float.valueOf(hashmap.get(list.get(i).getShouhuokehu()).get(list.get(i).getWuliaoming()).getFobzongjia())+Float.valueOf(list.get(i).getFobzongjia())));
                        }else{
                            hashmap.get(list.get(i).getShouhuokehu()).get(list.get(i).getWuliaoming()).setShuliang( String.valueOf(Float.valueOf(hashmap.get(list.get(i).getShouhuokehu()).get(list.get(i).getWuliaoming()).getShuliang()) + Float.valueOf(list.get(i).getShuliang())));
                            hashmap.get(list.get(i).getShouhuokehu()).get(list.get(i).getWuliaoming()).setJiashuiheji( String.valueOf(Float.valueOf(hashmap.get(list.get(i).getShouhuokehu()).get(list.get(i).getWuliaoming()).getJiashuiheji()) + Float.valueOf(list.get(i).getJiashuiheji())));
                            hashmap.get(list.get(i).getShouhuokehu()).get(list.get(i).getWuliaoming()).setFobzongjia(String.valueOf(Float.valueOf(hashmap.get(list.get(i).getShouhuokehu()).get(list.get(i).getWuliaoming()).getFobzongjia())+Float.valueOf(list.get(i).getFobzongjia())));
                        }
                    }else{  // 物料不存在的情况下
                        if(!list.get(i).getBizhong().equals("人民币")){
                            hashmap.get(list.get(i).getShouhuokehu()).put(list.get(i).getWuliaoming(),list.get(i));
                        }else{
                            WaimaoFahuo  waimaoFahuo =new WaimaoFahuo();
                            waimaoFahuo.setShuliang(String.valueOf(Float.valueOf(list.get(i).getShuliang())));
                            waimaoFahuo.setJiashuiheji(String.valueOf(Float.valueOf(list.get(i).getJiashuiheji()) / inithuilv));
                            waimaoFahuo.setFobzongjia(String.valueOf(Float.valueOf(list.get(i).getFobzongjia()) / inithuilv));
                            hashmap.get(list.get(i).getShouhuokehu()).put(list.get(i).getWuliaoming(),waimaoFahuo);
                        }
                    }
                } else {   //客户信息不存在的情况下
                    Map<String,WaimaoFahuo> maps =new HashMap<>();
                    if(!list.get(i).getBizhong().equals("人民币")){
                        maps.put(list.get(i).getWuliaoming(),list.get(i));
                    }else{
                        WaimaoFahuo  waimaoFahuo =new WaimaoFahuo();
                        waimaoFahuo.setJiashuiheji(String.valueOf(Float.valueOf(list.get(i).getJiashuiheji()) / inithuilv));
                        waimaoFahuo.setFobzongjia(String.valueOf(Float.valueOf(list.get(i).getFobzongjia()) / inithuilv));
                        waimaoFahuo.setShuliang(String.valueOf(Float.valueOf(list.get(i).getShuliang())));
                        maps.put(list.get(i).getWuliaoming(),waimaoFahuo);
                    }
                    hashmap.put(list.get(i).getShouhuokehu(),maps);
                }
            }
            HSSFRow row1;
            int paiming = 1;
            Float hejidunwei=0f;
            Float pingjunxiaoshou=0f;
            Float pingjiashouheji=0f;
            Float fobdanjia=0f;
            Float fobzongjia=0f;
            int jishu = 1;
            boolean index1 = false;
            for (int i = 0; i <listpaiming.size() ; i++){
                for(String value : hashmap.keySet()){
                   if(listpaiming.get(i).getShouhuokehu().equals(value) && i <= 20 ){
                    index1 =true;
                    Map<String, WaimaoFahuo> stringWaimaoFahuoMap = hashmap.get(value);

                    for (String values : stringWaimaoFahuoMap.keySet()){
                        row1 = sheet.createRow(rowNum);
                        if(index1){
                             if(hejidunwei > 0){
                                 row1.createCell(2).setCellValue("汇总：");
                                 row1.createCell(4).setCellValue(Float.valueOf(decimalFormat1.format(hejidunwei)));
                                 row1.createCell(5).setCellValue(Float.valueOf(decimalFormat1.format(pingjunxiaoshou/hejidunwei)));
                                 row1.createCell(6).setCellValue(Float.valueOf(decimalFormat1.format(pingjiashouheji)));
                                 row1.createCell(7).setCellValue(Float.valueOf(decimalFormat1.format(fobzongjia/jishu)));
                                 row1.createCell(8).setCellValue(Float.valueOf(decimalFormat1.format(fobzongjia)));
                                 row1.createCell(9).setCellValue(Float.valueOf(decimalFormat1.format(hejidunwei/Float.valueOf(listpaiming.get(i).getShuliang()))));
                                   hejidunwei=0f;
                                   pingjunxiaoshou=0f;
                                   pingjiashouheji=0f;
                                   fobdanjia=0f;
                                   fobzongjia=0f;
                                 rowNum++;
                                 row1 = sheet.createRow(rowNum);
                             }
                            row1.createCell(0).setCellValue(jishu);
                            row1.createCell(1).setCellValue(stringWaimaoFahuoMap.get(values).getYewuyuan());
                            row1.createCell(2).setCellValue(stringWaimaoFahuoMap.get(values).getShouhuokehu());
                        }
                        row1.createCell(3).setCellValue(stringWaimaoFahuoMap.get(values).getWuliaoming());
                        row1.createCell(4).setCellValue(Float.valueOf(stringWaimaoFahuoMap.get(values).getShuliang()));
                        row1.createCell(5).setCellValue(Float.valueOf(stringWaimaoFahuoMap.get(values).getJiashuiheji()) / Float.valueOf(stringWaimaoFahuoMap.get(values).getShuliang()));
                        row1.createCell(6).setCellValue(Float.valueOf(stringWaimaoFahuoMap.get(values).getJiashuiheji()));
                        row1.createCell(7).setCellValue(Float.valueOf(stringWaimaoFahuoMap.get(values).getFobzongjia()) / Float.valueOf(stringWaimaoFahuoMap.get(values).getShuliang()));
                        row1.createCell(8).setCellValue(Float.valueOf(stringWaimaoFahuoMap.get(values).getFobzongjia()));
                        rowNum++;
                        paiming++;
                          hejidunwei = hejidunwei + Float.valueOf(stringWaimaoFahuoMap.get(values).getShuliang());
                          pingjunxiaoshou= pingjunxiaoshou + Float.valueOf(stringWaimaoFahuoMap.get(values).getJiashuiheji()) / Float.valueOf(stringWaimaoFahuoMap.get(values).getShuliang());
                          pingjiashouheji= pingjiashouheji+ Float.valueOf(stringWaimaoFahuoMap.get(values).getJiashuiheji());
                          fobdanjia= fobdanjia + Float.valueOf(stringWaimaoFahuoMap.get(values).getFobzongjia()) / Float.valueOf(stringWaimaoFahuoMap.get(values).getShuliang());
                          fobzongjia= fobzongjia + Float.valueOf(stringWaimaoFahuoMap.get(values).getFobzongjia());
                        index1= false;
                        jishu++;
                    }
               }//判断客户是否相等
            }
          }
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
            response.flushBuffer();
            workbook.write(response.getOutputStream());
        }

    //     回款表导出
            @ApiOperation(value = "回款表导出", notes = "参数:回款表导出")
            @PostMapping("/huikuandaochu")
            public void huikuandaochu(HttpServletResponse response, @ApiParam(value = "jdyRole", required = false)
            @RequestBody WaimaoFahuo jdyRole) throws IOException{
                Float inithuilv = init(jdyRole,chaxunhuilv);
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("数据导出");
                List<WaimaoHuikuan> list = waimaoHuikuanService.seleteHuikuan(jdyRole);

                String fileName = "回款导出"+".xls";//设置要导出的文件的名字
                //        //新增数据行，并且设置单元格数据
                int rowNum = 1;
                HSSFRow row1;
                String[] headers = {"发票号", "回款日期", "回款金额",
                        "结汇银行","业务员","币种"};
                //        //headers表示excel表中第一行的表头
                HSSFRow row = sheet.createRow(0);
                for(int i=0;i<headers.length;i++){
                    HSSFCell cell = row.createCell(i);
                    HSSFRichTextString text = new HSSFRichTextString(headers[i]);
                    cell.setCellValue(text);
                }
                for (int i = 0; i <list.size() ; i++){
                    if( null !=list.get(i).getBizhong() &&  list.get(i).getBizhong().equals("人民币")) {
                        list.get(i).setBizhong("美元");
                        list.get(i).setHuikuanjine(String.valueOf(decimalFormat1.format(Float.valueOf(list.get(i).getHuikuanjine())/inithuilv)));
                    }
                    row1 = sheet.createRow(rowNum);
                    row1.createCell(0).setCellValue(list.get(i).getFapiaohao());
                    row1.createCell(1).setCellValue(list.get(i).getHuikuanriqi());
                    row1.createCell(2).setCellValue(Float.valueOf(decimalFormat1.format(Float.valueOf(list.get(i).getHuikuanjine()) )));
                    row1.createCell(3).setCellValue(list.get(i).getJiehuiyinhang() );
                    row1.createCell(4).setCellValue(list.get(i).getXiaoshouren() );
                    row1.createCell(5).setCellValue(list.get(i).getBizhong() );
                    rowNum++;
                }
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
                response.flushBuffer();
                workbook.write(response.getOutputStream());
            }



    @ApiOperation("欠款表导出excle")
    @PostMapping(value = "/debtExcle")
    public void debtExcle(HttpServletResponse response, WaimaoFahuo param) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("数据导出");
        List<WaimaoFahuo> lists = new ArrayList<>();
        List<WaimaoFahuo> classmatefadanju = waimaoFahuoService.seleteYuefenDayuAlllist(param);   // 查询出现所有的发货信息
        List<WaimaoHuikuan> classmatehui = waimaoHuikuanService.seleteHuikuanDangyueAllhao(param); //回款信息表
        boolean  index = false;
        Map<String,WaimaoHuikuan> map  = new HashMap<>();
        for (int i = 0; i < classmatefadanju.size(); i++){
            index = false;
            String regex="()[^\\d\r\n]*?(\\d+)[^\r\n]*?";
            Pattern p=Pattern.compile(regex);
            Matcher m=p.matcher(classmatefadanju.get(i).getHuikuanxieyi());
            String in1 = "0";
            String in2 = "0";
            while(m.find()){
                in1 = m.group(2);
                in2  = in1;
            }
            Date date =  sdf.parse( classmatefadanju.get(i).getYujizhuangtime());
            BigDecimal time = BigDecimal.valueOf(date.getTime()); // 得到指定日期的毫秒数
            BigDecimal day = BigDecimal.valueOf(Integer.valueOf(in2)).multiply(BigDecimal.valueOf(86400000)) ;
            System.out.println(day);
            time = time.add( day);
            Date dates = new Date();
            dates.setTime(time.longValue());
            classmatefadanju.get(i).setCeshi1((new SimpleDateFormat("yyyy-MM-dd").format(dates)));
             for (int j = 0; j < classmatehui.size() ; j++){
                  if(classmatefadanju.get(i).getFapiaohao().equals(classmatehui.get(j).getFapiaohao())){
                      if(CommonUtils.getDistanceDays(classmatehui.get(j).getHuikuanriqi(),in2) <= 0){
                         if(Float.valueOf(classmatefadanju.get(i).getFobzongjia()) >= Float.valueOf(classmatehui.get(j).getHuikuanjine())){
                               classmatefadanju.get(i).setJiashuiheji(String.valueOf(Float.valueOf(classmatefadanju.get(i).getFobzongjia()) - Float.valueOf(classmatehui.get(j).getHuikuanjine())));
                               classmatehui.get(j).setHuikuanjine("0");
                               continue;
                            }else{
                               classmatehui.get(j).setHuikuanjine(String.valueOf(Float.valueOf(classmatehui.get(j).getHuikuanjine())-Float.valueOf(classmatefadanju.get(i).getFobzongjia())));
                               index = true;
                           }
                       }else{
//                          欠款了
                          lists.add(classmatefadanju.get(i));
                      }
                      map.put(classmatefadanju.get(i).getFapiaohao(),classmatehui.get(j));
                  }
                if(!index && j == classmatehui.size()-1){
                    lists.add(classmatefadanju.get(i));
                }
            }
        }
//      截止到七月份回款信息
        Map<String ,WaimaoHuikuan>  huikuanmaps = new HashMap<>();
        List<WaimaoFahuo>  waimaoFahuos =  waimaoFahuoService.seleteFahuoDanjuhao(param);    //统计七月份的发货信息
        List<WaimaoHuikuan> waimaoHuikuans = waimaoHuikuanService.seleteHuikuanDanjuhao(param); // 统计七月份的回款信息
        for (int i = 0; i <waimaoFahuos.size() ; i++) {
            for (int j = 0; j <waimaoHuikuans.size() ; j++) {
                if(waimaoFahuos.get(i).getFapiaohao().equals(waimaoHuikuans.get(j).getFapiaohao())){
                     if(Float.valueOf(waimaoHuikuans.get(j).getHuikuanjine()) > Float.valueOf(waimaoFahuos.get(i).getJiashuiheji())){
                         waimaoHuikuans.get(j).setHuikuanjine(String.valueOf(Float.valueOf(waimaoHuikuans.get(j).getHuikuanjine()) - Float.valueOf(waimaoFahuos.get(i).getJiashuiheji())));
                         huikuanmaps.put(waimaoFahuos.get(i).getFapiaohao(),waimaoHuikuans.get(j));
                     }
                }
            }
        }
        //      大类中的欠款表
        String fileName = "欠款明细导出"+".xls";
        int rowNum = 1;
        String[] headers = {"业务员", "单据号", "发货日期",
                "发票号","收获客户","产品名称","数量","价格条款","含税单价",
                "加税合计","预计装船日期","回款协议","欠款到期日","是否逾期","收款金额","回款时间","包装物","币种"};
        //        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        HSSFRow row1;
        String mulv = "123";
        //        //在表中存放查询到的数据放入对应的列
        for (int i = 0; i <lists.size() ; i++){
            row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(lists.get(i).getYewuyuan());
            row1.createCell(1).setCellValue(lists.get(i).getDanjuhao());
            row1.createCell(2).setCellValue(lists.get(i).getFahuotime());
            row1.createCell(3).setCellValue(lists.get(i).getFapiaohao());
            row1.createCell(4).setCellValue(lists.get(i).getShouhuokehu());
            row1.createCell(5).setCellValue(lists.get(i).getWuliaoming());
            row1.createCell(6).setCellValue(lists.get(i).getShuliang());
            row1.createCell(7).setCellValue(lists.get(i).getJiagetiaokuan());
            row1.createCell(8).setCellValue(lists.get(i).getHanshuidanjia());
            row1.createCell(9).setCellValue(lists.get(i).getJiashuiheji());
            row1.createCell(10).setCellValue(lists.get(i).getYujizhuangtime());
            row1.createCell(11).setCellValue(lists.get(i).getHuikuanxieyi());
            row1.createCell(12).setCellValue(lists.get(i).getCeshi1());
            if(huikuanmaps.containsKey(lists.get(i).getFapiaohao()) && !mulv.equals(lists.get(i).getFapiaohao())){
                row1.createCell(13).setCellValue(CommonUtils.getDistanceDays(lists.get(i).getCeshi1(),huikuanmaps.get(lists.get(i).getFapiaohao()).getHuikuanriqi()) > 0?"否":"是");
                row1.createCell(14).setCellValue(huikuanmaps.get(lists.get(i).getFapiaohao()).getHuikuanjine());
                row1.createCell(15).setCellValue(huikuanmaps.get(lists.get(i).getFapiaohao()).getJiehuiyinhang());
                row1.createCell(16).setCellValue(huikuanmaps.get(lists.get(i).getFapiaohao()).getHuikuanriqi());
                row1.createCell(17).setCellValue(lists.get(i).getBaozhuangwu());
                row1.createCell(18).setCellValue(huikuanmaps.get(lists.get(i).getFapiaohao()).getBizhong());
            }
            rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }


        @ApiOperation(value = "业务员销售完成情况表", notes = "参数:业务员销售完成情况表")
        @PostMapping("/seleteCompletion")
        public void seleteCompletion(HttpServletResponse response,@ApiParam(value = "jdyRole", required = false)
                                      @RequestBody WaimaoFahuo  jdyRole) throws IOException, ParseException {

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("业务员销售完成情况表");
            List<WaimaoFahuoDto> list = waimaoFahuoService.seleteDangyueCompletion(jdyRole);  //当月销售
            List<WaimaoFahuoDto> list2 =  new ArrayList<>();// 上月销售
            Map<String,WaimaoHuikuan> listhuikuan = waimaoHuikuanService.seleteHuikuanyewuyuanDanjuhao(jdyRole); // 累计回款
            Map<String,WaimaoHuikuan>  dangyuehuikuan = waimaoHuikuanService.seleteHuikuanDangyuehuikuan(jdyRole);// 当月回款
            Map<String,Float> benyueqiankuan = waimaoHuikuanService.seleteQiankuan(jdyRole);  // 本月逾期欠款
            Map<String,Float> list3 = waimaoHuikuanService.seleteQuannianQiankuan(jdyRole);   // 累计欠款情况
            Map<String, WaimaoFahuo> lists = waimaoFahuoService.selelteLeijixiaoshou(jdyRole);//累计销售金额
            Map<String, Float> leijiqiankuan = waimaoFahuoService.selelteLeiqiankuan(jdyRole);//累计销
            String fileName = "业务员销售完成情况表"+".xls";
            int rowNum = 1;
            String[] headers = {"业务员", "本月目标量", "本月销量",
                    "本月销售达成率","上月销量","环比增长量","本月销售金额","本月回款","本月目标回款额","本月回款达成率","本月逾期欠款",
                    "年销售总量","累计目标量","累计销售","累计销售额","累计销售额达成率","全年销售额达成率","累计回款","累计目标回款",
                    "累计逾期欠款","累计回款达成","应收款总计"};
            HSSFRow row = sheet.createRow(0);
            for(int i=0;i<headers.length;i++){
                HSSFCell cell = row.createCell(i);
                HSSFRichTextString text = new HSSFRichTextString(headers[i]);
                cell.setCellValue(text);
            }
            HSSFRow row1;
            for (int i = 0; i <list.size() ; i++){
                 Float sellyear =0f;
                 row1 = sheet.createRow(rowNum);
                 Float sellSum =0.0f;  //年目标总量
                 Float sellM =0.0f;   // 当月目标量
                if(list.get(i).getWaimaoTarget() != null ){
                   sellyear =
                         Float.valueOf(StringUtils.isEmpty(list.get(i).getWaimaoTarget().getYiyue())?"0":list.get(i).getWaimaoTarget().getYiyue())
                        +Float.valueOf(StringUtils.isEmpty(list.get(i).getWaimaoTarget().getEryue())?"0":list.get(i).getWaimaoTarget().getEryue())
                        +Float.valueOf(StringUtils.isEmpty(list.get(i).getWaimaoTarget().getSanyue())?"0":list.get(i).getWaimaoTarget().getSanyue())
                        +Float.valueOf(StringUtils.isEmpty(list.get(i).getWaimaoTarget().getSiyue())?"0":list.get(i).getWaimaoTarget().getSiyue())
                        +Float.valueOf(StringUtils.isEmpty(list.get(i).getWaimaoTarget().getWuyue())?"0":list.get(i).getWaimaoTarget().getWuyue())
                        +Float.valueOf(StringUtils.isEmpty(list.get(i).getWaimaoTarget().getLiuyue())?"0":list.get(i).getWaimaoTarget().getLiuyue())
                        +Float.valueOf(StringUtils.isEmpty(list.get(i).getWaimaoTarget().getQiyue())?"0":list.get(i).getWaimaoTarget().getQiyue())
                        +Float.valueOf(StringUtils.isEmpty(list.get(i).getWaimaoTarget().getBayue())?"0":list.get(i).getWaimaoTarget().getBayue())
                        +Float.valueOf(StringUtils.isEmpty(list.get(i).getWaimaoTarget().getJiuyue())?"0":list.get(i).getWaimaoTarget().getJiuyue())
                        +Float.valueOf(StringUtils.isEmpty(list.get(i).getWaimaoTarget().getShiyue())?"0":list.get(i).getWaimaoTarget().getShiyue())
                        +Float.valueOf(StringUtils.isEmpty(list.get(i).getWaimaoTarget().getShiyiyue())?"0":list.get(i).getWaimaoTarget().getShiyiyue())
                        +Float.valueOf(StringUtils.isEmpty(list.get(i).getWaimaoTarget().getShieryue())?"0":list.get(i).getWaimaoTarget().getShieryue());
                 }
                 WaimaoFahuo waimaoFahuo =new WaimaoFahuo();
                switch (jdyRole.getFahuotime()){
                     case "01":
                         if(list.get(i).getWaimaoTarget() != null){
                         sellM= Float.valueOf(list.get(i).getWaimaoTarget().getYiyue());
                         sellSum= Float.valueOf(list.get(i).getWaimaoTarget().getYiyue());
                         }
                       break;
                     case "02":
                         if(list.get(i).getWaimaoTarget() != null){
                         waimaoFahuo.setFahuotime("01");
                         list2 = waimaoFahuoService.seleteDangyueCompletion(waimaoFahuo);
                         sellM= Float.valueOf(list.get(i).getWaimaoTarget().getEryue());
                         sellSum= Float.valueOf(list.get(i).getWaimaoTarget().getYiyue())+Float.valueOf(list.get(i).getWaimaoTarget().getEryue());
                         }
                         break;
                     case "03":
                         if(list.get(i).getWaimaoTarget() != null){
                         waimaoFahuo.setFahuotime("02");
                         list2 = waimaoFahuoService.seleteDangyueCompletion(waimaoFahuo);
                         sellM= Float.valueOf(list.get(i).getWaimaoTarget().getSanyue());
                         sellSum= Float.valueOf(list.get(i).getWaimaoTarget().getYiyue())+Float.valueOf(list.get(i).getWaimaoTarget().getYiyue())
                                  +Float.valueOf(list.get(i).getWaimaoTarget().getSanyue());
                         }
                         break;
                     case "04":
                         if(list.get(i).getWaimaoTarget() != null){
                         waimaoFahuo.setFahuotime("03");
                         list2 = waimaoFahuoService.seleteDangyueCompletion(waimaoFahuo);
                         sellM= Float.valueOf(list.get(i).getWaimaoTarget().getSiyue());
                         sellSum= Float.valueOf(list.get(i).getWaimaoTarget().getYiyue())+Float.valueOf(list.get(i).getWaimaoTarget().getYiyue())
                                 +Float.valueOf(list.get(i).getWaimaoTarget().getSanyue())+Float.valueOf(list.get(i).getWaimaoTarget().getSiyue());
                         }
                         break;
                     case "05":
                         if(list.get(i).getWaimaoTarget() != null){
                         waimaoFahuo.setFahuotime("04");
                         list2 = waimaoFahuoService.seleteDangyueCompletion(waimaoFahuo);
                         sellM= Float.valueOf(list.get(i).getWaimaoTarget().getWuyue());
                         sellSum= Float.valueOf(list.get(i).getWaimaoTarget().getYiyue())+Float.valueOf(list.get(i).getWaimaoTarget().getYiyue())
                                 +Float.valueOf(list.get(i).getWaimaoTarget().getSanyue())+Float.valueOf(list.get(i).getWaimaoTarget().getSiyue())
                                 +Float.valueOf(list.get(i).getWaimaoTarget().getWuyue());
                         }
                         break;
                     case "06":
                         if(list.get(i).getWaimaoTarget() != null){
                         waimaoFahuo.setFahuotime("05");
                         list2 = waimaoFahuoService.seleteDangyueCompletion(waimaoFahuo);
                         sellM= Float.valueOf(list.get(i).getWaimaoTarget().getLiuyue());
                         sellSum= Float.valueOf(list.get(i).getWaimaoTarget().getYiyue())+Float.valueOf(list.get(i).getWaimaoTarget().getYiyue())
                                 +Float.valueOf(list.get(i).getWaimaoTarget().getSanyue())+Float.valueOf(list.get(i).getWaimaoTarget().getSiyue())
                                 +Float.valueOf(list.get(i).getWaimaoTarget().getWuyue())+Float.valueOf(list.get(i).getWaimaoTarget().getLiuyue());
                         }
                         break;
                     case "07":
                         if(list.get(i).getWaimaoTarget() != null){
                         waimaoFahuo.setFahuotime("06");
                         list2 = waimaoFahuoService.seleteDangyueCompletion(waimaoFahuo);
                         sellM= Float.valueOf(list.get(i).getWaimaoTarget().getQiyue());
                         sellSum= Float.valueOf(list.get(i).getWaimaoTarget().getYiyue())+Float.valueOf(list.get(i).getWaimaoTarget().getYiyue())
                                 +Float.valueOf(list.get(i).getWaimaoTarget().getSanyue())+Float.valueOf(list.get(i).getWaimaoTarget().getSiyue())
                                 +Float.valueOf(list.get(i).getWaimaoTarget().getWuyue())+Float.valueOf(list.get(i).getWaimaoTarget().getLiuyue())
                                 +Float.valueOf(list.get(i).getWaimaoTarget().getQiyue());
                         }
                         break;
                     case "08":
                         if(list.get(i).getWaimaoTarget() != null){
                         waimaoFahuo.setFahuotime("07");
                         list2 = waimaoFahuoService.seleteDangyueCompletion(waimaoFahuo);
                         sellM= Float.valueOf(list.get(i).getWaimaoTarget().getBayue());
                         sellSum= Float.valueOf(list.get(i).getWaimaoTarget().getYiyue())+Float.valueOf(list.get(i).getWaimaoTarget().getYiyue())
                                 +Float.valueOf(list.get(i).getWaimaoTarget().getSanyue())+Float.valueOf(list.get(i).getWaimaoTarget().getSiyue())
                                 +Float.valueOf(list.get(i).getWaimaoTarget().getWuyue())+Float.valueOf(list.get(i).getWaimaoTarget().getLiuyue())
                                 +Float.valueOf(list.get(i).getWaimaoTarget().getQiyue())+Float.valueOf(list.get(i).getWaimaoTarget().getBayue());
                         }
                         break;
                     case "09":
                         if(list.get(i).getWaimaoTarget() != null){
                         waimaoFahuo.setFahuotime("08");
                         list2 = waimaoFahuoService.seleteDangyueCompletion(waimaoFahuo);
                         sellM= Float.valueOf(list.get(i).getWaimaoTarget().getJiuyue());
                         sellSum= Float.valueOf(list.get(i).getWaimaoTarget().getYiyue())+Float.valueOf(list.get(i).getWaimaoTarget().getYiyue())
                                 +Float.valueOf(list.get(i).getWaimaoTarget().getSanyue())+Float.valueOf(list.get(i).getWaimaoTarget().getSiyue())
                                 +Float.valueOf(list.get(i).getWaimaoTarget().getWuyue())+Float.valueOf(list.get(i).getWaimaoTarget().getLiuyue())
                                 +Float.valueOf(list.get(i).getWaimaoTarget().getQiyue())+Float.valueOf(list.get(i).getWaimaoTarget().getBayue())
                                 +Float.valueOf(list.get(i).getWaimaoTarget().getJiuyue());
                         }
                         break;
                     case "10":
                         if(list.get(i).getWaimaoTarget() != null){
                         waimaoFahuo.setFahuotime("09");
                         list2 = waimaoFahuoService.seleteDangyueCompletion(waimaoFahuo);
                         sellM= Float.valueOf(list.get(i).getWaimaoTarget().getShiyue());
                         sellSum= Float.valueOf(list.get(i).getWaimaoTarget().getYiyue())+Float.valueOf(list.get(i).getWaimaoTarget().getYiyue())
                                 +Float.valueOf(list.get(i).getWaimaoTarget().getSanyue())+Float.valueOf(list.get(i).getWaimaoTarget().getSiyue())
                                 +Float.valueOf(list.get(i).getWaimaoTarget().getWuyue())+Float.valueOf(list.get(i).getWaimaoTarget().getLiuyue())
                                 +Float.valueOf(list.get(i).getWaimaoTarget().getQiyue())+Float.valueOf(list.get(i).getWaimaoTarget().getBayue())
                                 +Float.valueOf(list.get(i).getWaimaoTarget().getJiuyue())+Float.valueOf(list.get(i).getWaimaoTarget().getShiyue());
                         }
                         break;
                     case "11":
                         if(list.get(i).getWaimaoTarget() != null){
                         waimaoFahuo.setFahuotime("10");
                         list2 = waimaoFahuoService.seleteDangyueCompletion(waimaoFahuo);
                         sellM= Float.valueOf(list.get(i).getWaimaoTarget().getShiyiyue());
                         sellSum=  Float.valueOf(list.get(i).getWaimaoTarget().getYiyue())+Float.valueOf(list.get(i).getWaimaoTarget().getYiyue())
                                 +Float.valueOf(list.get(i).getWaimaoTarget().getSanyue())+Float.valueOf(list.get(i).getWaimaoTarget().getSiyue())
                                 +Float.valueOf(list.get(i).getWaimaoTarget().getWuyue())+Float.valueOf(list.get(i).getWaimaoTarget().getLiuyue())
                                 +Float.valueOf(list.get(i).getWaimaoTarget().getQiyue())+Float.valueOf(list.get(i).getWaimaoTarget().getBayue())
                                 +Float.valueOf(list.get(i).getWaimaoTarget().getJiuyue())+Float.valueOf(list.get(i).getWaimaoTarget().getShiyue())
                                 +Float.valueOf(list.get(i).getWaimaoTarget().getShiyiyue());
                         }
                         break;
                     case "12":
                        if(list.get(i).getWaimaoTarget() != null) {
                             waimaoFahuo.setFahuotime("11");
                             list2 = waimaoFahuoService.seleteDangyueCompletion(waimaoFahuo);
                             sellM = Float.valueOf(list.get(i).getWaimaoTarget().getShieryue());
                             sellSum = Float.valueOf(list.get(i).getWaimaoTarget().getYiyue()) + Float.valueOf(list.get(i).getWaimaoTarget().getYiyue())
                                     + Float.valueOf(list.get(i).getWaimaoTarget().getSanyue()) + Float.valueOf(list.get(i).getWaimaoTarget().getSiyue())
                                     + Float.valueOf(list.get(i).getWaimaoTarget().getWuyue()) + Float.valueOf(list.get(i).getWaimaoTarget().getLiuyue())
                                     + Float.valueOf(list.get(i).getWaimaoTarget().getQiyue()) + Float.valueOf(list.get(i).getWaimaoTarget().getBayue())
                                     + Float.valueOf(list.get(i).getWaimaoTarget().getJiuyue()) + Float.valueOf(list.get(i).getWaimaoTarget().getShiyue())
                                     + Float.valueOf(list.get(i).getWaimaoTarget().getShiyiyue()) + Float.valueOf(list.get(i).getWaimaoTarget().getShieryue());
                         }
                         break;
                    }
                for (int j = 0; j <list2.size() ; j++){
                    if(list2.get(j).getYewuyuan().equals(list.get(i).getYewuyuan())){
                        row1.createCell(0).setCellValue(list.get(i).getYewuyuan());  //业务员
                        row1.createCell(1).setCellValue(sellM);    //本月目标销量
                        row1.createCell(2).setCellValue(list.get(i).getShuliang()); //本月销量
                        row1.createCell(3).setCellValue(sellSum/sellM+"%");
                        row1.createCell(4).setCellValue(" ");
                        row1.createCell(5).setCellValue(" ");
                        row1.createCell(6).setCellValue(list2.get(j).getShuliang()); //上月销量
                        row1.createCell(7).setCellValue(Float.valueOf(list.get(i).getShuliang())-Float.valueOf(list2.get(j).getShuliang()));
                        row1.createCell(8).setCellValue(list.get(i).getFobzongjia());  // 本月销售金额
                        row1.createCell(9).setCellValue(listhuikuan.get(list.get(i).getYewuyuan()).getHuikuanjine());  // 本月回款金额
                        row1.createCell(10).setCellValue( benyueqiankuan.get(list.get(i).getYewuyuan())+listhuikuan.get(list.get(i).getYewuyuan()).getHuikuanjine() ); //  本月目标回款额
                        row1.createCell(11).setCellValue(Float.valueOf(benyueqiankuan.get(listhuikuan.get(list.get(i).getYewuyuan()).getHuikuanjine()))/Float.valueOf((benyueqiankuan.get(list.get(i).getYewuyuan())+listhuikuan.get(list.get(i).getYewuyuan()).getHuikuanjine()))+"%");  // 本月回款达成率
                        row1.createCell(12).setCellValue(benyueqiankuan.get(list.get(i).getYewuyuan()));  // 本月逾期欠款
                        row1.createCell(13).setCellValue(sellyear);
                        row1.createCell(14).setCellValue(sellSum);
                        row1.createCell(15).setCellValue(lists.get(list.get(i).getYewuyuan()).getShuliang());
                        row1.createCell(16).setCellValue(lists.get(list.get(i).getYewuyuan()).getJiashuiheji());
                        row1.createCell(17).setCellValue(Float.valueOf(lists.get(list.get(i).getYewuyuan()).getShuliang())/Float.valueOf(sellSum)+"%");
                        row1.createCell(18).setCellValue(Float.valueOf(lists.get(list.get(i).getYewuyuan()).getShuliang())/Float.valueOf(sellyear)+"%");
                        row1.createCell(19).setCellValue(listhuikuan.get(lists.get(list.get(i).getYewuyuan())).getHuikuanjine());     //累计回款
                        row1.createCell(20).setCellValue(Float.valueOf(listhuikuan.get(lists.get(list.get(i).getYewuyuan())).getHuikuanjine())+Float.valueOf(list3.get(list.get(i).getYewuyuan())));  //累计目标回款
                        row1.createCell(21).setCellValue(list3.get(list.get(i).getYewuyuan()));      // 累计欠款表
                        row1.createCell(22).setCellValue(Float.valueOf(listhuikuan.get(lists.get(list.get(i).getYewuyuan())).getHuikuanjine())+Float.valueOf(list3.get(list.get(i).getYewuyuan()))/Float.valueOf(listhuikuan.get(lists.get(list.get(i).getYewuyuan())).getHuikuanjine())+"%");         //累计回款/目标回款
                        row1.createCell(23).setCellValue(leijiqiankuan.get(list.get(i).getYewuyuan()));
                        rowNum++;
                    }
                }
            }
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
            response.flushBuffer();
            workbook.write(response.getOutputStream());
        }

        @ApiOperation(value = "每月产品分类汇总信息", notes = "参数:每月产品分类汇总信息")
        @PostMapping("/productByTheMonth")
        public ResultVo productByTheMonth(@ApiParam(value = "yuefen", required = false)
                                          @RequestBody String  yuefen){
            return  null;
        }















}