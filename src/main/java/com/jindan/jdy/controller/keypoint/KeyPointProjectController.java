package com.jindan.jdy.controller.keypoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.KeyPointProjectDto;
import com.jindan.jdy.common.pojo.JdyUser;
import com.jindan.jdy.common.pojo.KeyPointProject;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.CommonUtils;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.keypoint.KeyPointProjectService;
import com.jindan.jdy.service.user.JdyUserService;
import com.jindan.jdy.wechat.AccessSmsXiaoxi;
import com.jindan.jdy.wechat.Datas;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*
* <p>说明： 重点项目API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年4月20日
*
*/
@Slf4j
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags ="重点项目情况")
@RestController
@RequestMapping("/keyPointProject")
public class KeyPointProjectController{

    @Autowired
    JdyUserService jdyUserService;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    KeyPointProjectService keyPointProjectService;

    @ApiOperation(value = "重点项查询", notes = "参数:重点项查询")
    @PostMapping("/seletekeyPointProject")
    public ResultVo seleteDepartment( @ApiParam(name = "departmentSuggestDto", required = false)
                                      @RequestBody KeyPointProjectDto departmentSuggestDto ) {
            Page<KeyPointProject> list = keyPointProjectService.seleteDepartmentFacility(departmentSuggestDto);
            return ResultVo.success(list);
    }

    @ApiOperation(value = "查看重点工作详情", notes = "参数:重点项查询")
    @PostMapping("/seletekeyPointProjectdetails")
    public ResultVo seletekeyPointProjectdetails( @ApiParam(name = "keyPointProject", required = false)
                                      @RequestBody KeyPointProject  keyPointProject ){
            List<KeyPointProjectDto> classmatefa = keyPointProjectService.seleteKeyPointProjectexcle(keyPointProject);
            return  ResultVo.success(classmatefa);
    }

    @ApiOperation("更新建议信息")
    @PostMapping("updatekeyPointProject")
    public ResultVo updatekeyPointPracticableService(@ApiParam(name = "userPermission", required = true)
                                                     @RequestBody KeyPointProject userPermission){
        log.info("======“更新建议信息接口”开始执行======");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        if(userPermission.getStatus().equals("1")){
             RestTemplate restTemplate=new RestTemplate();
             Map<String,String> params=new HashMap<>();
             params.put("appid","wxb5b6dfa9e8b12f50");
             params.put("secret","6da768a60cdd737cc30c32134d7071c6");
             ResponseEntity<Object> responseEntity = restTemplate.getForEntity("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={appid}&secret={secret}",Object.class,params);
             Map<String,String>  maps = (Map<String, String>) responseEntity.getBody();
            log.info("maps.get(\"access_token\")的值为："+maps.get("access_token"));
             RestTemplate restTemplates=new RestTemplate();
             JdyUser  jdyUser =new JdyUser();
             jdyUser.setDepartments(userPermission.getDeparement());
             List<JdyUser>  list  = jdyUserService.seleListUsers(jdyUser);
             for (int i = 0; i <list.size() ; i++) {
                 AccessSmsXiaoxi accessSmsXiaoxi =new AccessSmsXiaoxi();
                 accessSmsXiaoxi.setTouser(list.get(i).getWecharId());
                 accessSmsXiaoxi.setTemplate_id("76gYqXCxZqRaSYGguPlz481Wurs2hMvYU6fjAhXaaG8");
                 Map<String, Datas> data  = new HashMap<>();
                 Datas datas =new Datas();
                 datas.setValue(userPermission.getTitles());
                 datas.setColor("FF0000");
                 data.put("first",datas);
                 Datas data1 =new Datas();
                 data1.setValue(list.get(i).getUsername());
                 data1.setColor("#000000");
                 data.put("keyword1",data1);
                 Datas data2 =new Datas();
                 data2.setValue(df.format(new Date()));
                 data2.setColor("#000000");
                 data.put("keyword2",data2);
                 Datas data3 =new Datas();
                 data3.setValue(userPermission.getContents());
                 data3.setColor("#000000");
                 data.put("keyword3",data3);
                 Datas data4 =new Datas();
                 data4.setValue(userPermission.getRemartks());
                 data4.setColor("#000000");
                 data.put("remark",data4);
                 accessSmsXiaoxi.setData(data);
                 //问题项啥需要处理
                 ResponseEntity<String> responseEntityss=restTemplates.postForEntity("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+maps.get("access_token"),accessSmsXiaoxi,String.class); //提交的body内容为user对象，请求的返回的body类型为String
                 String body=responseEntityss.getBody();
             }
         }
        boolean b = keyPointProjectService.updateById(userPermission);
        if(b){
            redisUtil.del(userPermission.getId());
            redisUtil.del(userPermission.getId()+"zhong");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增信息")
    @PostMapping("addkeyPointProject")
    public ResultVo addkeyPointPracticableService( @ApiParam(name = "userPermission", required = true)
                                                   @RequestBody  KeyPointProject userPermission) throws Exception {
        userPermission.setSubmitTime(CommonUtils.getPresenttime());
        boolean save = keyPointProjectService.save(userPermission);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除信息")
    @DeleteMapping("deletekeyPointProject/{id}")
    public ResultVo deletekeyPointPracticableService(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){

        boolean b = keyPointProjectService.removeById(id);
        if(b){
            redisUtil.del(id);
            redisUtil.del(id+"zhong");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


    @ApiOperation("重点工作导出excle")
    @PostMapping(value = "/downloadAllClassmate")
    public void downloadAllClassmate(HttpServletResponse response, KeyPointProject keyPointProject) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("数据导出");
        List<KeyPointProjectDto> classmatefa = keyPointProjectService.seleteKeyPointProjectexcle(keyPointProject);
        String fileName = "重点工作导出"+".xls";//设置要导出的文件的名字
//        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = {"标题", "内容", "增加日期",
                "状态","文件路径","部门","提交人","备注"};
//        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
//        //在excel表中添加表头
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        HSSFRow row1;
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }


}