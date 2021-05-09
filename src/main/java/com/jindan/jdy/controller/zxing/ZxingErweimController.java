package com.jindan.jdy.controller.zxing;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.zxing.WriterException;
import com.jindan.jdy.common.config.AesEncryptUtils;
import com.jindan.jdy.common.dto.ZxingErweimDto;
import com.jindan.jdy.common.pojo.*;
import com.jindan.jdy.controller.utils.QRCodeUtil;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.sys.JdyDomainService;
import com.jindan.jdy.service.user.UserPermissionService;
import com.jindan.jdy.service.zxing.ZxingErweimService;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.zxing.ZxingVerifyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.io.IOException;
import java.util.*;

/**
*
* <p>说明： 二维码API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年4月20日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "用户模板生成的二维码")
@RestController
@RequestMapping("/zxingErweim")
public class ZxingErweimController{

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    ZxingErweimService zxingErweimService;

    @Autowired
    JdyDomainService jdyDomainService;

    @Autowired
    ZxingVerifyService zxingVerifyService;

    @ApiOperation("获取日期")
    @GetMapping("seleteErweimriqi")
    public ResultVo seleteErweimriqi() throws Exception {
          return  ResultVo.success(redisUtil.get("addseleteErweimriqi"));
    }

    @ApiOperation("获取批号")
    @GetMapping("seleteErweimpihao")
    public ResultVo seleteErweimpihao() throws Exception {
        return  ResultVo.success(redisUtil.get("seleteErweimpihao"));
    }

    @ApiOperation("添加批号")
    @GetMapping("addseleteErweimpihao")
    public ResultVo addseleteErweimpihao(String pihao) throws Exception {
        if( redisUtil.get("seleteErweimpihao") == null){
            List<String> page1 =new ArrayList<>();
            page1.add(pihao);
            redisUtil.set("seleteErweimpihao",page1);
            return  ResultVo.success(page1);
        }else{
            List<String> page1  = (List<String>) redisUtil.get("seleteErweimpihao");
            page1.add(pihao);
            redisUtil.set("seleteErweimpihao",page1);
            return  ResultVo.success();
        }
    }


    @ApiOperation("添加日期")
    @GetMapping("addseleteErweimriqi")
    public ResultVo addseleteErweimriqi(String riqi) throws Exception {
        if( redisUtil.get("addseleteErweimriqi") == null){
            List<String> page1 =new ArrayList<>();
            page1.add(riqi);
            redisUtil.set("addseleteErweimriqi",page1);
            return  ResultVo.success(page1);
        }else{
            List<String> page1  = (List<String>) redisUtil.get("addseleteErweimriqi");
            page1.add(riqi);
            redisUtil.set("addseleteErweimriqi",page1);
            return  ResultVo.success(redisUtil.get("addseleteErweimriqi"));
        }
    }


    @ApiOperation("根据ID进行查询")
    @GetMapping("seleteOneZxingErweim/{id}")
    public ResultVo seleteOneZxingErweim(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id) throws Exception {
         List<ZxingErweimDto> byId = zxingErweimService.seletelistseleteOne(id);
         List<ZxingVerify> list = zxingVerifyService.list(null);
         Map<String,Object> map =new HashMap<>();
        if(byId.size() > 0 && list.size() > 0){
            map.put("content",byId.get(0));
            map.put("pathurl",list);
        }else{
            map.put("content","");
            map.put("pathurl",list);
        }
        return ResultVo.success(map);
    }

    @ApiOperation(value = "二维码信息", notes = "参数：二维码信息")
    @PostMapping("/seleteUserPermission")
    public ResultVo seleteDepartment(@ApiParam(name = "zxingErweimDto", required = false)
                                     @RequestBody ZxingErweimDto zxingErweimDto){

          Page<ZxingErweim> list = zxingErweimService.seleteListZxing(zxingErweimDto);
        return  ResultVo.success(list);
    }

    @ApiOperation("更新二维码信息")
    @PostMapping("updateUserPermission")
    public ResultVo updatefacility(@ApiParam(name = "zxingErweim", required = true)
                                   @RequestBody ZxingErweim zxingErweim){
        boolean b = zxingErweimService.updateById(zxingErweim);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


    @ApiOperation("新增二维码信息")
    @PostMapping("addUserPermission")
    public ResultVo addsubset(@ApiParam(name = "zxingErweim", required = true) @RequestBody  ZxingErweim zxingErweim) throws Exception {
        String result= UUID.randomUUID().toString().replace("-", "").toUpperCase();
        List<JdyDomain> list = jdyDomainService.list(null);
         if(list.size() > 0){
             String qrCode3 = QRCodeUtil.createQRCode3(list.get(0).getDomain()+result);
             if(qrCode3 != null && !qrCode3.equals("")){
                 zxingErweim.setImgurl(qrCode3);
                 zxingErweim.setMd(result);
                 boolean save = zxingErweimService.save(zxingErweim);
                 if(save){
                     return ResultVo.success(zxingErweim);
                 }
                 return ResultVo.failed();
             }
         }
        return ResultVo.failed();
    }

    @ApiOperation("删除二维码信息")
    @DeleteMapping("deleteUserPermission/{id}")
    public ResultVo deletefacility(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){
        boolean b = zxingErweimService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }



}