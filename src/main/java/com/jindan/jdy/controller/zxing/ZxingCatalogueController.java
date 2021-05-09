package com.jindan.jdy.controller.zxing;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.ZxingCatalogueDto;
import com.jindan.jdy.common.pojo.ZxingCatalogue;
import com.jindan.jdy.common.pojo.ZxingErwei;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.zxing.ZxingCatalogueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "二维码目录")
@RestController
@RequestMapping("/zxingCatalogue")
public class ZxingCatalogueController{

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    ZxingCatalogueService zxingCatalogueService;

    @ApiOperation(value = "二维码目录管理", notes = "参数:二维码信息")
    @PostMapping("/seleteZxingCatalogue")
    public ResultVo seleteZxingCatalogue( @ApiParam(name = "zxingCatalogue", required = false)
                                      @RequestBody ZxingCatalogue zxingCatalogue ){
        if( redisUtil.get("seleteZxingCatalogue") == null){
            List<ZxingCatalogue> list = zxingCatalogueService.seletelist(zxingCatalogue);
            redisUtil.set("seleteZxingCatalogue",list);
            return  ResultVo.success(list);
        }else{
            return  ResultVo.success(redisUtil.get("seleteZxingCatalogue"));
        }
    }

    @ApiOperation("更新目录信息")
    @PostMapping("updateZxingCatalogue")
    public ResultVo updateZxingCatalogue(@ApiParam(name = "zxingCatalogue", required = true)
                                   @RequestBody ZxingCatalogue zxingCatalogue){
        boolean b = zxingCatalogueService.updateById(zxingCatalogue);
        if(b){
            redisUtil.del("seleteZxingCatalogue");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增目录信息")
    @PostMapping("addZxingCatalogue")
    public ResultVo addsubset( @ApiParam(name = "zxingCatalogue", required = true)
                               @RequestBody  ZxingCatalogue zxingCatalogue){
        boolean save = zxingCatalogueService.save(zxingCatalogue);
        if(save){
            redisUtil.del("seleteZxingCatalogue");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除目录信息")
    @DeleteMapping("deleteUserPermission/{id}")
    public ResultVo deleteZxingCatalogue(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){
        boolean b = zxingCatalogueService.removeById(id);
        if(b){
            redisUtil.del("seleteZxingCatalogue");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


    @ApiOperation("ceshi")
    @GetMapping("string")
    public String deleteZxingCatalogue(){
        String qrCode3= "";
        return qrCode3;
    }

    @ApiOperation("根据目录查询模板信息")
    @PostMapping("addZxingCatalogueTemplate")
    public ResultVo addZxingCatalogueTemplate(@ApiParam(name = "zxingCatalogue", required = false)
                                               @RequestBody ZxingCatalogueDto zxingCatalogue){
        if(zxingCatalogue.getCurrentPage() <= 0 || zxingCatalogue.getPageSize()  <= 0){
            zxingCatalogue.setCurrentPage(1);
        }
        PageHelper.startPage(zxingCatalogue.getCurrentPage(), 200);
        List<ZxingCatalogueDto> list = zxingCatalogueService.seletelistCatalogueTemplate(zxingCatalogue);
        PageInfo<ZxingErwei> pageInfo = new PageInfo(list, 5);
        if(list.size() > 0){
            return ResultVo.success(pageInfo);
        }
        return ResultVo.success();
    }

}