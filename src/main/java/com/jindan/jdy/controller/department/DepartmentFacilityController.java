package com.jindan.jdy.controller.department;

import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.DepartmentFacilityDto;
import com.jindan.jdy.common.dto.JdyPurchaseDto;
import com.jindan.jdy.common.dto.JdyUserDto;
import com.jindan.jdy.common.enumerate.Status;
import com.jindan.jdy.common.pojo.DepartmentFacility;
import com.jindan.jdy.common.pojo.JdyFlow;
import com.jindan.jdy.common.pojo.JdyPurchase;
import com.jindan.jdy.shiro.TokenUtil;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.QRCodeUtil;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.controller.utils.WorkbookUtils;
import com.jindan.jdy.enumerate.RankUtils;
import com.jindan.jdy.service.department.DepartmentFacilityService;
import com.jindan.jdy.service.flow.JdyFlowService;
import com.jindan.jdy.service.purchase.JdyPurchaseService;
import com.jindan.jdy.service.user.JdyUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
*
* <p>说明： API应用KEYAPI接口层</P>
* @version: V1.0
* @author: kong
* @time    2019年10月16日
*/

@Slf4j
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "部门设备信息")
@RestController
@RequestMapping("/departmentFacility")
public class DepartmentFacilityController{

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    DepartmentFacilityService departmentFacilityService;

    @Autowired
    JdyUserService jdyUserService;

    @Autowired
    JdyPurchaseService jdyPurchaseService;

    @Autowired
    JdyFlowService jdyFlowService;


    @ApiOperation(value = "设备信息导入", notes = "参数:设备信息导入")
    @PostMapping("/addFacilityexcle")
    public ResultVo addfahuo(@RequestParam("file") MultipartFile file) throws Exception {
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
        List<DepartmentFacility> jijiabiaos = new ArrayList<>();
        for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
            Row row = sheet.getRow(j);
//          row = sheet.getRow(j);
            if(row==null||row.getFirstCellNum()==j){continue;}
//                    for(int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
//                        Cell cell  = row.getCell(y);
//                        log.info("for(int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++)循环开始执行");
//                        log.info("cell的值为："+cell);
//                    }
            DepartmentFacility jijiabiao = new DepartmentFacility();
            if(row.getCell(0)!=null){
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setBrand(row.getCell(0).getStringCellValue());
            }
            if(row.getCell(1)!=null){
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setModel(row.getCell(1).getStringCellValue());
            }
            if(row.getCell(2)!=null){
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setNuit((row.getCell(2).getStringCellValue()));
            }
            if(row.getCell(3)!=null){
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setNumber(Integer.parseInt(Integer.valueOf(row.getCell(3).getStringCellValue()) > 0? row.getCell(3).getStringCellValue() :"1"));
            }
            if(row.getCell(4)!=null){
                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setBitNumber(row.getCell(4).getStringCellValue());
            }
            if(row.getCell(5)!=null){
                row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setMaintainPerson(row.getCell(5).getStringCellValue());
            }
            if(row.getCell(6)!=null){
                row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setIsRepair(row.getCell(6).getStringCellValue());
            }
            if(row.getCell(7)!=null){
                row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setRepairPeriod(row.getCell(7).getStringCellValue());
            }
            if(row.getCell(8)!=null){
                row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setPidNumber((row.getCell(8).getStringCellValue()));
            }
            if(row.getCell(9)!=null){
                row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);

                jijiabiao.setSpecifications((row.getCell(9).getStringCellValue()));
            }
            if(row.getCell(10)!=null){
                row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setDepartments((row.getCell(10).getStringCellValue()));
            }
            if(row.getCell(11)!=null){
                row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setSerialnumber(row.getCell(11).getStringCellValue());
            }
            if(row.getCell(12)!=null){
                row.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setAssetnumber(row.getCell(12).getStringCellValue());
            }
            if(row.getCell(13)!=null){
                row.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setAllkg(row.getCell(13).getStringCellValue());
            }
            if(row.getCell(14)!=null){
                row.getCell(14).setCellType(Cell.CELL_TYPE_STRING);

                jijiabiao.setInstalltime((row.getCell(14).getStringCellValue()));
            }
            if(row.getCell(15)!=null){
                row.getCell(15).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setUsetime((row.getCell(15).getStringCellValue()));
            }
            if(row.getCell(16)!=null){
                row.getCell(16).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setRateOfWork(row.getCell(16).getStringCellValue());
            }
            if(row.getCell(17)!=null){
                row.getCell(17).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setPowers(row.getCell(17).getStringCellValue());
            }
            if(row.getCell(18)!=null){
                row.getCell(18).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setParameters(row.getCell(18).getStringCellValue());
            }
            if(row.getCell(19)!=null){
                row.getCell(19).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setLubrication(row.getCell(19).getStringCellValue()); //润滑方式
            }
            if(row.getCell(20)!=null){
                row.getCell(20).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setTexture((row.getCell(20).getStringCellValue()));  //保养类型
            }
            if(row.getCell(21)!=null){
                row.getCell(21).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setAllWeight((row.getCell(21).getStringCellValue()));
            }
            if(row.getCell(22)!=null){
                row.getCell(22).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setRevolutionsPerMinute(row.getCell(22).getStringCellValue());
            }
            if(row.getCell(23)!=null){
                row.getCell(23).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setCommodityName(row.getCell(23).getStringCellValue());
            }
            if(row.getCell(24)!=null){
                row.getCell(24).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setFrequencys(row.getCell(24).getStringCellValue());
            }
            if(row.getCell(25)!=null){
                row.getCell(25).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setWarehouseId((row.getCell(25).getStringCellValue()));
            }
            if(row.getCell(26)!=null){
                row.getCell(26).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setMaintainPeriod((row.getCell(26).getStringCellValue()));
            }
            if(row.getCell(27)!=null){
                row.getCell(27).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setTypes(row.getCell(27).getStringCellValue());
            }
            if(row.getCell(28)!=null){
                row.getCell(28).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName1((row.getCell(28).getStringCellValue()));
            }
            if(row.getCell(29)!=null){
                row.getCell(29).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName2((row.getCell(29).getStringCellValue()));
            }
            if(row.getCell(30)!=null){
                row.getCell(30).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName3((row.getCell(30).getStringCellValue()));
            }
            if(row.getCell(31)!=null){
                row.getCell(31).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName4(row.getCell(31).getStringCellValue());
            }
            if(row.getCell(32)!=null){
                row.getCell(32).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName5(row.getCell(32).getStringCellValue());
            }
            if(row.getCell(33)!=null){
                row.getCell(33).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName6(row.getCell(33).getStringCellValue());
            }
            if(row.getCell(34)!=null){
                row.getCell(34).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName7((row.getCell(34).getStringCellValue()));
            }
            if(row.getCell(35)!=null){
                row.getCell(35).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName8((row.getCell(35).getStringCellValue()));
            }
            if(row.getCell(36)!=null){
                row.getCell(36).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName9(row.getCell(36).getStringCellValue());
            }
            if(row.getCell(37)!=null){
                row.getCell(37).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName10(row.getCell(37).getStringCellValue());
            }
            if(row.getCell(38)!=null){
                row.getCell(38).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName11(row.getCell(38).getStringCellValue());
            }
            if(row.getCell(39)!=null){
                row.getCell(39).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName12(row.getCell(39).getStringCellValue());
            }
            if(row.getCell(40)!=null){
                row.getCell(40).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName13((row.getCell(40).getStringCellValue()));
            }
            if(row.getCell(41)!=null){
                row.getCell(41).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName14((row.getCell(41).getStringCellValue()));
            }
            if(row.getCell(42)!=null){
                row.getCell(42).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName15(row.getCell(42).getStringCellValue());
            }
            if(row.getCell(43)!=null){
                row.getCell(43).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName16(row.getCell(43).getStringCellValue());
            }
            if(row.getCell(44)!=null){
                row.getCell(44).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName17(row.getCell(44).getStringCellValue());
            }
            if(row.getCell(45)!=null){
                row.getCell(45).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName18((row.getCell(45).getStringCellValue()));
            }
            if(row.getCell(46)!=null){
                row.getCell(46).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName19((row.getCell(46).getStringCellValue()));
            }
            if(row.getCell(47)!=null){
                row.getCell(47).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName20((row.getCell(47).getStringCellValue()));
            }
            if(row.getCell(48)!=null){
                row.getCell(48).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName21(row.getCell(48).getStringCellValue());
            }
            if(row.getCell(49)!=null){
                row.getCell(49).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName22(row.getCell(49).getStringCellValue());
            }
            if(row.getCell(50)!=null){
                row.getCell(50).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName23(row.getCell(50).getStringCellValue());
            }
            if(row.getCell(51)!=null){
                row.getCell(51).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName24((row.getCell(51).getStringCellValue()));
            }
            if(row.getCell(52)!=null){
                row.getCell(52).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName25((row.getCell(52).getStringCellValue()));
            }
            if(row.getCell(53)!=null){
                row.getCell(53).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName26(row.getCell(53).getStringCellValue());
            }
            if(row.getCell(54)!=null){
                row.getCell(54).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName27(row.getCell(54).getStringCellValue());
            }
            if(row.getCell(55)!=null){
                row.getCell(55).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName28(row.getCell(55).getStringCellValue());
            }
            if(row.getCell(56)!=null){
                row.getCell(56).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName29(row.getCell(56).getStringCellValue());
            }
            if(row.getCell(57)!=null){
                row.getCell(57).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName30((row.getCell(57).getStringCellValue()));
            }
            if(row.getCell(58)!=null){
                row.getCell(58).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName31(row.getCell(58).getStringCellValue());
            }
            if(row.getCell(59)!=null){
                row.getCell(59).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName32((row.getCell(59).getStringCellValue()));
            }
            if(row.getCell(60)!=null){
                row.getCell(60).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName33(row.getCell(60).getStringCellValue());
            }
            if(row.getCell(61)!=null){
                row.getCell(61).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName34((row.getCell(61).getStringCellValue()));
            }
            if(row.getCell(62)!=null){
                row.getCell(62).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setName35(row.getCell(62).getStringCellValue());
            }
            if(row.getCell(63)!=null){
                row.getCell(63).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setPtype((row.getCell(63).getStringCellValue()));
            }
            if(row.getCell(64)!=null){
                row.getCell(64).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setRemarks(row.getCell(64).getStringCellValue());
            }
            jijiabiaos.add(jijiabiao);
        }
        departmentFacilityService.saveBatch(jijiabiaos);
        return ResultVo.success();
    }


    @ApiOperation("领料审核")
    @PostMapping("updatejdyCommodityFlow")
    public ResultVo updatejdyCommodityFlow(@ApiParam(name = "jdyPurchase", required = true)
                                           @RequestBody JdyPurchaseDto jdyPurchase, HttpServletRequest httpRequest){

        JdyUserDto jdyUserDto = jdyUserService.seleteUserDetailsOne(TokenUtil.getRequestToken(httpRequest));
//      MINISTER(1, "部长")
        if(RankUtils.MINISTER.getValue().equals(jdyUserDto.getPower()) && jdyPurchase.reults && (Integer.valueOf(jdyPurchase.getStatus()).equals(Integer.valueOf(RankUtils.MINISTER.getStatus())))){
                jdyPurchase.setStatics(Status.OUTPUTSTATIC.getStatus().toString());
         }else if(RankUtils.MINISTER.getValue().equals(jdyUserDto.getPower()) && !jdyPurchase.reults){
                jdyPurchase.setStatics(Status.ERROR.getStatus().toString());
                jdyPurchase.setStatus(RankUtils.STAFF.getStatus().toString());
         }
        JdyPurchase jdyPurchase1 = new JdyPurchase();
        jdyPurchase1.setStatus(jdyPurchase.getStatus());
        jdyPurchase1.setPurchaseId(jdyPurchase.getPurchaseId());
        jdyPurchase1.setStatics(jdyPurchase.getStatics());
        boolean b = jdyPurchaseService.updateById(jdyPurchase1);
          if(b){
                log.info("if(b)判断开始执行");
                JdyFlow jdyFlow =new JdyFlow();
                jdyFlow.setParentId(jdyPurchase.getPurchaseId());
                jdyFlow.setFlowPersom(jdyUserDto.getUserId());
                jdyFlow.setFlowRemarks(jdyPurchase.getFlowRemarks());
                jdyFlow.setFlowResult(String.valueOf(jdyPurchase.reults));
                jdyFlow.setFlowType("领料");
                boolean b1 = jdyFlowService.save(jdyFlow);
                if(b1){
                    return ResultVo.success();
                }
                return ResultVo.failed();
            }
        return ResultVo.failed();
    }

    @ApiOperation(value = "部门设备查询", notes = "参数:部门设备查询条件")
    @PostMapping("/seleteUserPermission")
    public ResultVo seleteDepartment(@ApiParam(name = "departmentSuggestDto", required = false)
                                     @RequestBody DepartmentFacilityDto departmentSuggestDto ){
        PageInfo<DepartmentFacilityDto> departmentFacilityDtoPageInfo = departmentFacilityService.seleteDepartmentFacility(departmentSuggestDto);
        return  ResultVo.success(departmentFacilityDtoPageInfo);
    }


    @ApiOperation("更新建议信息")
    @PostMapping("updateDepartmentFacility")
    public ResultVo updateDepartmentFacility(@ApiParam(name = "userPermission", required = true)
                                   @RequestBody DepartmentFacility userPermission){

        boolean b = departmentFacilityService.updateById(userPermission);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增建议信息")
    @PostMapping("addDepartmentFacility")
    public ResultVo addDepartmentFacility( @ApiParam(name = "userPermission", required = true)
                               @RequestBody  DepartmentFacility userPermission){
        DepartmentFacility userPermission1 = departmentFacilityService.insertSave(userPermission);
        if(userPermission1!=null){
            return ResultVo.success(userPermission1);
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除建议信息")
    @DeleteMapping("deleteDepartmentFacility/{id}")
    public ResultVo deleteDepartmentFacility(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){

        boolean b = departmentFacilityService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("生成二维码图片")
    @PostMapping("/detailserweima")
    public ResultVo Detailssubset(@ApiParam(name = "zxingErweim", required = true) @RequestBody String userPermission) throws Exception {
//  String result= UUID.randomUUID().toString().replace("-", "").toUpperCase();
        String qrCode3 = QRCodeUtil.createQRCode3(userPermission);
        if(qrCode3 != null && !qrCode3.equals("")){
            return ResultVo.success(qrCode3);
        }
        return ResultVo.failed();
    }


    @ApiOperation("设备生成二维码图片")
    @PostMapping("/adderweima")
    public ResultVo addsubset(@ApiParam(name = "zxingErweim", required = true) @RequestBody  DepartmentFacility userPermission) throws Exception {
//  String result= UUID.randomUUID().toString().replace("-", "").toUpperCase();
        String qrCode3 = QRCodeUtil.createQRCode3(userPermission.getId());
        if(qrCode3 != null && !qrCode3.equals("")){
            userPermission.setErweima(qrCode3);
            boolean save = departmentFacilityService.updateById(userPermission);
            if(save){
                return ResultVo.success(userPermission);
            }
            return ResultVo.failed();
        }
        return ResultVo.failed();
    }

    @ApiOperation("设备导出")
    @PostMapping(value = "/addexclefacility")
    public void downloadAllClassmate(HttpServletResponse response, DepartmentFacilityDto param) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("数据导出");
        List<DepartmentFacilityDto> classmatefa = departmentFacilityService.exportExclelist(param);

        String fileName = "部门设备导出"  + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据
        int rowNum = 1;

        String[] headers = { "设备ID","品牌", "型号", "数量", "计数单位","状态","设备名称","位号",
                "设备责任人","维修周期","PID编码","规格","所在部门","序号","固定资产编号",
        "总重","安装时间","使用时间","功率","参数","润滑方式","商品名称"
        };
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
            row1.createCell(0).setCellValue(classmatefa.get(i).getId());
            row1.createCell(1).setCellValue(classmatefa.get(i).getBrand());
            row1.createCell(2).setCellValue(classmatefa.get(i).getModel());
            row1.createCell(3).setCellValue(classmatefa.get(i).getNumber());
            row1.createCell(4).setCellValue(classmatefa.get(i).getNuit());
            row1.createCell(5).setCellValue(classmatefa.get(i).getStates());
            row1.createCell(6).setCellValue(classmatefa.get(i).getCreateName());
            row1.createCell(7).setCellValue(classmatefa.get(i).getBitNumber());
            row1.createCell(8).setCellValue(classmatefa.get(i).getMaintainPerson());
            row1.createCell(9).setCellValue(classmatefa.get(i).getRepairPeriod());
            row1.createCell(10).setCellValue(classmatefa.get(i).getPidNumber());
            row1.createCell(11).setCellValue(classmatefa.get(i).getRepairPeriod());
            row1.createCell(12).setCellValue(classmatefa.get(i).getSerialnumber());
            row1.createCell(13).setCellValue(classmatefa.get(i).getDepartments());
            row1.createCell(14).setCellValue(classmatefa.get(i).getSerialnumber());
            row1.createCell(15).setCellValue(classmatefa.get(i).getAssetnumber());
            row1.createCell(16).setCellValue(classmatefa.get(i).getAllWeight());
            row1.createCell(17).setCellValue(classmatefa.get(i).getInstalltime());
            row1.createCell(18).setCellValue(classmatefa.get(i).getUsetime());
            row1.createCell(19).setCellValue(classmatefa.get(i).getPowers());
            row1.createCell(20).setCellValue(classmatefa.get(i).getParameters());
            row1.createCell(21).setCellValue(classmatefa.get(i).getLubrication());
            row1.createCell(22).setCellValue(classmatefa.get(i).getCommodityName());
             if(classmatefa.get(1).getDepartmentSubfacilities().size() > 0){
               for (int j = 0; j < classmatefa.get(1).getDepartmentSubfacilities().size() ; j++) {
                 rowNum++;
                 row1 = sheet.createRow(rowNum);
                   row1.createCell(1).setCellValue(classmatefa.get(i).getBrand());
                   row1.createCell(2).setCellValue(classmatefa.get(i).getModel());
                   row1.createCell(3).setCellValue(classmatefa.get(i).getNumber());
                   row1.createCell(4).setCellValue(classmatefa.get(i).getNuit());
                   row1.createCell(5).setCellValue(classmatefa.get(i).getStates());
                   row1.createCell(6).setCellValue(classmatefa.get(i).getCreateName());
                   row1.createCell(7).setCellValue(classmatefa.get(i).getBitNumber());
                   row1.createCell(8).setCellValue(classmatefa.get(i).getMaintainPerson());
                   row1.createCell(9).setCellValue(classmatefa.get(i).getRepairPeriod());
                   row1.createCell(10).setCellValue(classmatefa.get(i).getPidNumber());
                   row1.createCell(11).setCellValue(classmatefa.get(i).getRepairPeriod());
                   row1.createCell(12).setCellValue(classmatefa.get(i).getSerialnumber());
                   row1.createCell(13).setCellValue(classmatefa.get(i).getDepartments());
                   row1.createCell(14).setCellValue(classmatefa.get(i).getSerialnumber());
                   row1.createCell(15).setCellValue(classmatefa.get(i).getAssetnumber());
                   row1.createCell(16).setCellValue(classmatefa.get(i).getAllWeight());
                   row1.createCell(17).setCellValue(classmatefa.get(i).getInstalltime());
                   row1.createCell(18).setCellValue(classmatefa.get(i).getUsetime());
                   row1.createCell(19).setCellValue(classmatefa.get(i).getPowers());
                   row1.createCell(20).setCellValue(classmatefa.get(i).getParameters());
                   row1.createCell(21).setCellValue(classmatefa.get(i).getLubrication());
                   row1.createCell(22).setCellValue(classmatefa.get(i).getCommodityName());
               }
             }
            rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

}