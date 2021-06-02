package com.jindan.jdy.controller.zxing;

import com.jindan.jdy.common.pojo.JdyDomain;
import com.jindan.jdy.common.pojo.ZxingErweim;
import com.jindan.jdy.common.pojo.ZxingVerify;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.sys.JdyDomainService;
import com.jindan.jdy.service.zxing.ZxingErweimService;
import com.jindan.jdy.service.zxing.ZxingVerifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "二维码验证模块")
@Controller
@RequestMapping("/ZxingVerify")
public class ZxingVerifys {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    ZxingErweimService zxingErweimService;

    @Autowired
    JdyDomainService jdyDomainService;

    @Autowired
    ZxingVerifyService zxingVerifyService;

    @ApiOperation("获取验证数据根据")
    @GetMapping("/verify/{id}")
    public String deletefacility(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String id){
        List<ZxingVerify > page1 = zxingVerifyService.seletelist(null);
        List<JdyDomain> list = jdyDomainService.seletelist(null);
        if(list.size() > 0){
          try{
            String mds =null;
              List<ZxingErweim> list1 = zxingErweimService.seleteIdlist(id);
              if(list1.size() > 0){
                ZxingErweim   byId =  list1.get(0);
               if(byId.equals("") || byId != null){
                Map<String,Object> hap =new HashMap<>();
                hap.put("url",list.get(0).getDomain());
                hap.put("content",byId);
                hap.put("imgurl",page1);
                return   "redirect:"+list.get(0).getSuccessUrl()+"?md="+id;
              }
           }
          }catch(Exception e){
            e.printStackTrace();
          }
        }
        return   "redirect:"+list.get(0).getErrorUrl()+"?md="+id;
    }



    @ApiOperation("获取验证数据根据")
    @GetMapping("/verify123/{id}")
    public ModelAndView deletefacility123(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String id){
        List<ZxingVerify > page1 = zxingVerifyService.seletelist(null);
        List<JdyDomain> list = jdyDomainService.seletelist(null);

        log.info("list的值为："+list);
        if(list.size() > 0){
            try{
                String mds =null;
                List<ZxingErweim> list1 = zxingErweimService.seleteIdlist(id);
                if(list1.size() > 0){
                    ZxingErweim   byId =  list1.get(0);
                    if(byId.equals("") || byId != null){
                        Map<String,Object> hap =new HashMap<>();
                        hap.put("url",list.get(0).getDomain());
                        hap.put("content",byId);
                        hap.put("imgurl",page1);
                        return  new ModelAndView(new RedirectView(list.get(0).getSuccessUrl()+"?md="+id));
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return new ModelAndView(new RedirectView(list.get(0).getErrorUrl()+"?md="+id));
    }

}
