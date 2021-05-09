package com.jindan.jdy.controller.department;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.JdySspDto;
import com.jindan.jdy.common.pojo.JdySsp;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.department.JdySspService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
*
* <p>说明： 二维码目录API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年4月20日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "随手拍")
@RestController
@RequestMapping("/jdySsp")
public class JdySspController{

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    JdySspService jdySspService;

    @ApiOperation(value = "随手拍管理", notes = "参数:随手拍信息")
    @PostMapping("/seletejdySsp")
    public ResultVo seleteJdySsp(@ApiParam(value = "jdySsp", required = false)
                                     @RequestBody JdySspDto jdySsp){
        if(jdySsp.getId() != null){
            if(redisUtil.get(jdySsp.getId()) == null){
                Page<JdySsp> list = jdySspService.seletePagelist(jdySsp);
                redisUtil.set(jdySsp.getId(),list);
                return  ResultVo.success(list);
            }else{
                return  ResultVo.success(redisUtil.get(jdySsp.getId()));
            }
        }else{
            Page<JdySsp> list = jdySspService.seletePagelist(jdySsp);
            return  ResultVo.success(list);
        }

    }

    @ApiOperation("更新角色信息")
    @PostMapping("/updatejdySsp")
    public ResultVo updatefacility(@ApiParam(value = "jdySsp", required = true)
                                   @RequestBody JdySsp jdySsp){
        boolean b = jdySspService.updateById(jdySsp);
        if(b){
            redisUtil.del(jdySsp.getId());
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增随手拍信息")
    @PostMapping("/addjdySsp")
    public ResultVo addjdyRole( @ApiParam(name = "jdySsp", required = true)
                                @RequestBody JdySsp jdySsp){
        boolean save = jdySspService.save(jdySsp);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("随手拍信息")
    @DeleteMapping("/deletejdyRole/{id}")
    public ResultVo deletejdySsp(@PathVariable String id){
        boolean b = jdySspService.deleteById(id);
        if(b){
            redisUtil.del(id);
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("随手拍导出excle")
    @PostMapping("/addJdySspDto")
    public void downloadAllClassmate(HttpServletResponse response, JdySspDto param) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("数据导出");
        List<JdySsp> classmatefa = jdySspService.seletelist(param);
        String fileName = "随手拍导出"+".xls";//设置要导出的文件的名字
//        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = {"内容", "地址", "状态",
                "处理时长","处理人","预期状态","反馈内容"};
//        //headers表示excel表中第一行的表头
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
            row1.createCell(0).setCellValue(classmatefa.get(i).getContent());
            row1.createCell(1).setCellValue(classmatefa.get(i).getLocations());
            row1.createCell(2).setCellValue(classmatefa.get(i).getStatus());
            row1.createCell(3).setCellValue(classmatefa.get(i).getChuliTime());
            row1.createCell(4).setCellValue(classmatefa.get(i).getResultPer());
            row1.createCell(5).setCellValue(classmatefa.get(i).getExceedTime());
            row1.createCell(6).setCellValue(classmatefa.get(i).getResultContent());
            rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }







}