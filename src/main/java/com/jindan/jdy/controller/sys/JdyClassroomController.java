package com.jindan.jdy.controller.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.DepartmentSuggestDto;
import com.jindan.jdy.common.dto.JdyClassroomDto;
import com.jindan.jdy.common.dto.JdySspDto;
import com.jindan.jdy.common.pojo.DepartmentSuggest;
import com.jindan.jdy.common.pojo.JdyClassroom;
import com.jindan.jdy.common.pojo.JdySsp;
import com.jindan.jdy.common.pojo.JdyUserFile;
import com.jindan.jdy.service.department.JdySspService;
import com.jindan.jdy.service.sys.JdyClassroomService;
import com.jindan.jdy.common.utils.api.ResultVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
*
* <p>说明： 二维码目录API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年4月20日
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "金丹职教室")
@RestController
@RequestMapping("/jdyClassroom")
public class JdyClassroomController{

    @Autowired
    JdyClassroomService jdyClassroomService;

    @ApiOperation(value = "职教室管理", notes = "参数:职教室信息")
    @PostMapping("/seletejdyClassroom")
    public ResultVo seletejdyClassroom(@ApiParam(value = "jdyClassroom", required = false)
                                        @RequestBody JdyClassroomDto jdyClassroom){
        Page<JdyClassroom> list = jdyClassroomService.seletelist(jdyClassroom);
        return  ResultVo.success(list);
    }


    @ApiOperation("更新职教室信息")
    @PostMapping("/updatejdyClassroom")
    public ResultVo updatejdyClassroom(@ApiParam(value = "jdyClassroom", required = true)
                                   @RequestBody JdyClassroom jdyClassroom){
        boolean b = jdyClassroomService.updateById(jdyClassroom);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增职教室信息")
    @PostMapping("/addjdyClassroom")
    public ResultVo addjdyClassroom( @ApiParam(name = "jdyClassroom", required = true)
                                @RequestBody JdyClassroom jdyClassroom){
        List<JdyClassroom> list = jdyClassroomService.seleteseletelist(jdyClassroom);
         if(list.size() > 0){
             return ResultVo.failed();
         }else{
             boolean save = jdyClassroomService.save(jdyClassroom);
             if(save){
                 return ResultVo.success();
             }
         }
          return ResultVo.failed();
    }

    @ApiOperation("职教室信息")
    @DeleteMapping(value = "/deletejdyClassroom/{id}")
    public ResultVo deletejdyClassroom(@ApiParam(value = "id", name = "id", required = true) @PathVariable String  id){
        boolean b = jdyClassroomService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("职教室导出excle")
    @PostMapping(value = "/addJdyClassroom")
    public void downloadAllClassmate(HttpServletResponse response, JdyClassroom param) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("数据导出");
        List<JdyClassroom> classmatefa = jdyClassroomService.seleteseletelist(param);
        String fileName = "职教室预约导出"+".xls";
        int rowNum = 1;
        String[] headers = {"职教室", "培训人员", "部门",
                "手机号","开始日期","结束日期","培训内容"};
        HSSFRow row = sheet.createRow(0);
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        HSSFRow row1; 
        for (int i = 0; i <classmatefa.size() ; i++){
            row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(classmatefa.get(i).getJiaoshi());
            row1.createCell(1).setCellValue(classmatefa.get(i).getPerson());
            row1.createCell(2).setCellValue(classmatefa.get(i).getBumen());
            row1.createCell(3).setCellValue(classmatefa.get(i).getNumber());
            row1.createCell(4).setCellValue(classmatefa.get(i).getStarttime());
            row1.createCell(5).setCellValue(classmatefa.get(i).getEndtime());
            row1.createCell(6).setCellValue(classmatefa.get(i).getContents());
            rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }






}