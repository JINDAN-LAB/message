package com.jindan.jdy.controller.purchase;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.JdyCommodityDto;
import com.jindan.jdy.common.dto.JdyPurchaseDto;
import com.jindan.jdy.common.dto.JdyUserDto;
import com.jindan.jdy.common.enumerate.Status;
import com.jindan.jdy.common.pojo.*;
import com.jindan.jdy.common.shiro.TokenUtil;
import com.jindan.jdy.controller.utils.CommonUtils;
import com.jindan.jdy.enumerate.RankUtils;
import com.jindan.jdy.service.flow.JdyFlowService;
import com.jindan.jdy.service.purchase.JdyCommodityService;
import com.jindan.jdy.service.purchase.JdyPurchaseService;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.purchase.PurchaseDecisionService;
import com.jindan.jdy.service.sys.JdyRuleService;
import com.jindan.jdy.service.user.JdyUserService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
*
* <p>说明： API应用KEYAPI接口层</P>
* @version: V1.0
* @author: kong
* @time    2019年10月16日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags ="采购")
@RestController
@RequestMapping("/jdyPurchase")
public class JdyPurchaseController{

    @Autowired
    JdyPurchaseService jdyPurchaseService;

    @Autowired
    JdyCommodityService jdyCommodityService;

    @Autowired
    JdyRuleService jdyRuleService;

    @Autowired
    JdyUserService jdyUserService;

    @Autowired
    JdyFlowService jdyFlowService;

    @Autowired
    PurchaseDecisionService purchaseDecisionService;

    @ApiOperation("开始审批")
    @PostMapping("updatejdyCommodityFlow")
    public ResultVo updatejdyCommodityFlow(@ApiParam(name = "jdyPurchase", required = true)
                                           @RequestBody JdyPurchaseDto jdyPurchase, HttpServletRequest httpRequest) {
        String status = jdyPurchase.getStatus();
        JdyUserDto jdyUserDto = jdyUserService.seleteUserDetailsOne(TokenUtil.getRequestToken(httpRequest));
        JdyRule jdyRule = new JdyRule();
        jdyRule.setDepartments(jdyUserDto.getDepartments());
        List<JdyRule> seletelist = jdyRuleService.seletelist(jdyRule);
        PurchaseDecision purchaseDecision = new PurchaseDecision();
        purchaseDecision.setParentId(jdyPurchase.getPurchaseId());
        if (seletelist.size() > 0) {
            if (RankUtils.MINISTER.getValue().equals(jdyUserDto.getPower()) && jdyPurchase.reults && Integer.parseInt(status) == Integer.parseInt(RankUtils.MINISTER.getStatus().toString())) {
                if (Float.valueOf(jdyPurchase.getAllMoney()) >= Float.valueOf(seletelist.get(0).getRuleTwoCondition())) {
                    jdyPurchase.setStatus(RankUtils.MAJOR.getStatus().toString());
                    purchaseDecision.setName(jdyUserDto.getUsername());
                    purchaseDecision.setResults(String.valueOf(jdyPurchase.reults));
                } else {
                    if (RankUtils.MINISTER.getValue().equals(jdyUserDto.getPower()) && !jdyPurchase.reults && Integer.parseInt(status) == Integer.parseInt(RankUtils.MINISTER.getStatus().toString())) {
                        jdyPurchase.setStatics(Status.ERROR.getStatus().toString());
                        jdyPurchase.setStatus(RankUtils.STAFF.getStatus().toString());
                        purchaseDecision.setName(jdyUserDto.getUsername());
                        purchaseDecision.setResults(String.valueOf(jdyPurchase.reults));
                    } else {
                        jdyPurchase.setStatics(Status.PURCHASEING.getStatus().toString());
                        jdyPurchase.setStatus(RankUtils.SHENHE.getStatus().toString());
                    }
                }
            }
            if (RankUtils.MAJOR.getValue().equals(jdyUserDto.getPower()) && jdyPurchase.reults && Integer.parseInt(status) == Integer.parseInt(RankUtils.MAJOR.getStatus().toString())) {
                    if (Float.valueOf(jdyPurchase.getAllMoney()) >= Float.valueOf(seletelist.get(0).getRuleThreeCondition())) {
                        jdyPurchase.setStatus(RankUtils.MANAGER.getStatus().toString());
                        purchaseDecision.setName(jdyUserDto.getUsername());
                        purchaseDecision.setResults(String.valueOf(jdyPurchase.reults));
                    } else {
                        jdyPurchase.setStatics(Status.PURCHASEING.getStatus().toString());
                        jdyPurchase.setStatus(RankUtils.SHENHE.getStatus().toString());
                        purchaseDecision.setName(jdyUserDto.getUsername());
                        purchaseDecision.setResults(String.valueOf(jdyPurchase.reults));
                    }
                } else if (RankUtils.MAJOR.getValue().equals(jdyUserDto.getPower()) && !jdyPurchase.reults && Integer.parseInt(status) == Integer.parseInt(RankUtils.MAJOR.getStatus().toString())) {
                    jdyPurchase.setStatics(Status.ERROR.getStatus().toString());
                    jdyPurchase.setStatus(RankUtils.MINISTER.getStatus().toString());
                    purchaseDecision.setName(jdyUserDto.getUsername());
                    purchaseDecision.setResults(String.valueOf(jdyPurchase.reults));
                }
                if (RankUtils.MANAGER.getValue().equals(jdyUserDto.getPower()) && jdyPurchase.reults   && Integer.parseInt(status) == Integer.parseInt(RankUtils.MANAGER.getStatus().toString())) {
                    if (Float.valueOf(jdyPurchase.getAllMoney()) >= Float.valueOf(seletelist.get(0).getRuleFourCondition())) {
                        jdyPurchase.setStatus(RankUtils.CHAIRMAN.getStatus().toString());
                        purchaseDecision.setName(jdyUserDto.getUsername());
                        purchaseDecision.setResults(String.valueOf(jdyPurchase.reults));
                    } else {
                        if((RankUtils.MANAGER.getValue().equals(jdyUserDto.getPower()) &&  jdyPurchase.reults) ){
                            jdyPurchase.setStatus(RankUtils.SHENHE.getStatus().toString());
                            jdyPurchase.setStatics( Status.PURCHASEING.getStatus().toString());
                            purchaseDecision.setName(jdyUserDto.getUsername());
                            purchaseDecision.setResults(String.valueOf(jdyPurchase.reults));
                        }else if((RankUtils.MANAGER.getValue().equals(jdyUserDto.getPower()) &&  !jdyPurchase.reults)) {
                            jdyPurchase.setStatics(Status.ERROR.getStatus().toString());
                            jdyPurchase.setStatus(RankUtils.MAJOR.getStatus().toString());
                            purchaseDecision.setName(jdyUserDto.getUsername());
                            purchaseDecision.setResults(String.valueOf(jdyPurchase.reults));
                        }
                    }
                }
                if (RankUtils.CHAIRMAN.getValue().equals(jdyUserDto.getPower()) && jdyPurchase.reults && Integer.parseInt(status) == Integer.parseInt(RankUtils.CHAIRMAN.getStatus().toString())) {
                    jdyPurchase.setStatus(RankUtils.SHENHE.getStatus().toString());
                    jdyPurchase.setStatics(Status.PURCHASEING.getStatus().toString());
                    purchaseDecision.setName(jdyUserDto.getUsername());
                    purchaseDecision.setResults(String.valueOf(jdyPurchase.reults));
                } else if (RankUtils.CHAIRMAN.getValue().equals(jdyUserDto.getPower()) && !jdyPurchase.reults && Integer.parseInt(status) == Integer.parseInt(RankUtils.CHAIRMAN.getStatus().toString())) {
                    jdyPurchase.setStatus(RankUtils.MANAGER.getStatus().toString());
                    purchaseDecision.setName(jdyUserDto.getUsername());
                    purchaseDecision.setResults(String.valueOf(jdyPurchase.reults));
                }
                JdyPurchase jdyPurchase1 = new JdyPurchase();
                BeanUtils.copyProperties(jdyPurchase, jdyPurchase1);
                boolean b = jdyPurchaseService.updateById(jdyPurchase1);
                if (b) {
                    JdyFlow jdyFlow = new JdyFlow();
                    jdyFlow.setParentId(jdyPurchase.getPurchaseId());
                    jdyFlow.setFlowPersom(jdyUserDto.getUsername());
                    jdyFlow.setFlowRemarks(jdyPurchase.getFlowRemarks());
                    jdyFlow.setFlowResult(String.valueOf(jdyPurchase.reults));
                    jdyFlow.setFlowType("采购");
                    boolean b1 = jdyFlowService.save(jdyFlow);
                    boolean save = purchaseDecisionService.save(purchaseDecision);
                    if (b1 && save) {
                        return ResultVo.success();
                    }
                    return ResultVo.failed();
                }
                return ResultVo.failed();
            }
            return ResultVo.failed();
        }

    @ApiOperation(value = "采购查询", notes = "参数:采购信息")
    @PostMapping("/seleteJdyPurchase")
    public ResultVo seleteDepartment(@ApiParam(name = "jdyPurchaseDto", required = false)
                                     @RequestBody JdyPurchaseDto jdyPurchaseDto){
        if(jdyPurchaseDto.getCurrentPage() <= 0   || jdyPurchaseDto.getPageSize()  <= 0){
            jdyPurchaseDto.setCurrentPage(1);
        }
        PageHelper.startPage(jdyPurchaseDto.getCurrentPage(), jdyPurchaseDto.getPageSize());
        List<JdyPurchase> list = jdyPurchaseService.seletelist(jdyPurchaseDto);
        PageInfo<JdyPurchase> pageInfo = new PageInfo(list, 5);
        return ResultVo.success(pageInfo);
    }


    @ApiOperation(value = "采购查询详情", notes = "参数:采购信息")
    @PostMapping("/seleteAllJdyPurchase")
    public ResultVo seleteAllJdyPurchase(@ApiParam(name = "jdyPurchaseDto", required = false)
                                         @RequestBody JdyPurchaseDto jdyPurchaseDto){
        if(jdyPurchaseDto.getCurrentPage() <= 0 || jdyPurchaseDto.getPageSize() <= 0){
            jdyPurchaseDto.setCurrentPage(1);
        }
        PageHelper.startPage(jdyPurchaseDto.getCurrentPage(), 200);
        List<JdyPurchaseDto> list = jdyPurchaseService.seleteAllJdyPurchase(jdyPurchaseDto);
        PageInfo<JdyPurchaseDto> pageInfo = new PageInfo(list, 5);
        return ResultVo.success(pageInfo);
    }


    @ApiOperation("更新采购和商品信息")
    @PostMapping("updateAllPurchase")
    public ResultVo updatefacility(@ApiParam(name = "jdyPurchaseDto", required = true)
                                   @RequestBody JdyPurchaseDto jdyPurchaseDto){
        JdyPurchase jdyPurchase = new JdyPurchase();
        BeanUtils.copyProperties(jdyPurchaseDto,jdyPurchase);
        boolean save = jdyPurchaseService.updateById(jdyPurchase);
        int a = 0;
        boolean deleteremove = jdyCommodityService.deleteremove(jdyPurchase.getPuuids());
          for (int i = 0; i < jdyPurchaseDto.getListsCommodity().size(); i++) {
            if(jdyPurchaseDto.getListsCommodity().get(i) != null){
                jdyPurchaseDto.getListsCommodity().get(i).setParentId(jdyPurchase.getPuuids());
                boolean save1 = jdyCommodityService.save(jdyPurchaseDto.getListsCommodity().get(i));
                if(save1){
                    a++;
                }
            }
          }
        if(jdyPurchaseDto.getListsCommodity().size() == a && save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("更新采购表头信息")
    @PostMapping("updatejdyPurchase")
    public ResultVo updatejdyPurchase(@ApiParam(name = "jdyPurchase", required = true)
                                       @RequestBody JdyPurchase jdyPurchase){
        boolean save = jdyPurchaseService.updateById(jdyPurchase);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("更新采购商品信息")
    @PostMapping("updatejdyCommodity")
    public ResultVo updatejdyCommodity(@ApiParam(name = "jdyCommodity", required = true)
                                   @RequestBody JdyCommodity jdyCommodity){
        boolean save = jdyCommodityService.updateById(jdyCommodity);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


    @ApiOperation("新增采购信息")
    @PostMapping("addJdyPurchase")
    public ResultVo addsubset(@ApiParam(name = "jdyPurchaseDto", required = true)
                               @RequestBody JdyPurchaseDto jdyPurchaseDto){
        String s = CommonUtils.GetGUID();
        JdyPurchase jdyPurchase  = new JdyPurchase();
        BeanUtils.copyProperties(jdyPurchaseDto,jdyPurchase);
        jdyPurchase.setPuuids(s);
        JdyPurchase jdyPurchase1 = jdyPurchaseService.insertSave(jdyPurchase);
        int a = 0;
        for (int i = 0; i <jdyPurchaseDto.getListsCommodity().size() ; i++) {
            jdyPurchaseDto.getListsCommodity().get(i).setParentId(s);
        }
        boolean save1 = jdyCommodityService.saveBatch(jdyPurchaseDto.getListsCommodity());
        if(save1   ){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除采购信息")
    @DeleteMapping("deleteJdyPurchase/{purchaseId}")
    public ResultVo deletefacility(@ApiParam(name = "purchaseId", value = "删除ID", required = true) @PathVariable String  purchaseId){
        JdyPurchase byId = jdyPurchaseService.getById(purchaseId);
        boolean b = jdyPurchaseService.removeById(purchaseId);
        if(b){
            boolean remove = jdyCommodityService.deleteremove(byId.getPuuids());
            if(remove){
                return ResultVo.success();
            }
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除采购商品信息")
    @DeleteMapping("deleteJdyCommodity/{commodityId}")
    public ResultVo deletejdyCommodity(@ApiParam(name = "commodityId", value = "删除ID", required = true) @PathVariable String  commodityId){
        System.out.println("deletejdyCommodity");
        System.out.println(commodityId);
            boolean remove = jdyCommodityService.removeById(commodityId);
            if(remove){
                return ResultVo.success();
            }
            return ResultVo.failed();
    }

    @ApiOperation(value = "采购商品信息查询", notes = "参数:商品信息")
    @PostMapping("/seleteJdyCommodityDto")
    public ResultVo seletejdyCommodity(@ApiParam(name = "jdyCommodityDto", required = false)
                                     @RequestBody JdyCommodityDto jdyCommodityDto){
        if(jdyCommodityDto.getCurrentPage() <= 0   || jdyCommodityDto.getPageSize()  <= 0){
            jdyCommodityDto.setCurrentPage(1);
        }
        PageHelper.startPage(jdyCommodityDto.getCurrentPage(), 200);
        List<JdyCommodity> list = jdyCommodityService.seleteListJdyCommodity(jdyCommodityDto);
        PageInfo<JdyPurchase> pageInfo = new PageInfo(list, 5);
        return ResultVo.success(pageInfo);
    }

    @ApiOperation("新增商品信息")
    @PostMapping("addJdyCommodity")
    public ResultVo addJdyCommodity(@ApiParam(name = "jdyCommodity", required = true)
                               @RequestBody JdyCommodity jdyCommodity){
        boolean save = jdyCommodityService.save(jdyCommodity);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


}