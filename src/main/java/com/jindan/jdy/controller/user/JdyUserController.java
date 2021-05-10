package com.jindan.jdy.controller.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.JdyUserDto;
import com.jindan.jdy.common.pojo.JdyUser;
import com.jindan.jdy.common.pojo.StarchOrganizationPut;
import com.jindan.jdy.common.pojo.UserRole;
import com.jindan.jdy.shiro.TokenUtil;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.WorkbookUtils;
import com.jindan.jdy.service.user.JdyUserService;
import com.jindan.jdy.service.user.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*
* <p>说明： API应用KEYAPI接口层</P>
* @version: V1.0
* @author: BianPeng
* @time    2019年10月16日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags ="用户管理")
@RestController
@RequestMapping("/jdyUser")
public class JdyUserController{

    @Autowired
    JdyUserService jdyUserService;

    @Autowired
    UserRoleService userRoleService;

//  人员的批量导入
    @ApiOperation(value = "人员的批量导入", notes = "参数:人员的批量导入")
    @PostMapping("/addFacilityrenyuan")
    public ResultVo addFacilityrenyuan(@RequestParam("file") MultipartFile file) throws Exception {
        //创建Excel工作薄
        Workbook work = WorkbookUtils.getWorkbook(file.getInputStream(),file.getOriginalFilename());
        if(null == work){
            throw new Exception("创建Excel工作薄为空！");
        }
        System.out.println("work.getNumberOfSheets();"+ work.getNumberOfSheets());
        Sheet sheet  = work.getSheetAt(0);
        if(sheet==null){
            throw new Exception("创建Excel工作薄为空！");
        }
        List<StarchOrganizationPut> jijiabiaos = new ArrayList<>();
        for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
            Row row = sheet.getRow(j);
            if(row==null||row.getFirstCellNum()==j){continue;}
            JdyUser jijiabiao = new JdyUser();
            if(row.getCell(0)!=null){
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setUsername(row.getCell(0).getStringCellValue());
            }
            if(row.getCell(1)!=null){
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setSex(row.getCell(1).getStringCellValue());
            }
            if(row.getCell(2)!=null){
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setDepartments((row.getCell(2).getStringCellValue()));
            }
            if(row.getCell(3)!=null){
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                jijiabiao.setPhone(String.valueOf(row.getCell(3).getStringCellValue()));
            }
            if(row.getCell(4)!=null){
                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
            }
            UserRole userRole = new UserRole();
            userRole.setRoleId(String.valueOf(row.getCell(4).getStringCellValue())  );
            jijiabiao.setPower("员工");
            boolean save = jdyUserService.save(jijiabiao);
            if(save){
                userRole.setUserId(jijiabiao.getUserId());
                userRoleService.save(userRole);
            }
        }
        return ResultVo.success();
    }


    @ApiOperation(value = "登陆", notes = "参数:用户名 密码")
    @PostMapping("/login")
    public ResultVo login(@ApiParam(name = "jdyUser", required = true)
                           @RequestBody JdyUser jdyUser){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(jdyUser.getUsername(), jdyUser.getPassword());
        try {
            subject.login(token);
            Map map = new HashMap();
            map.put("token",((JdyUser)subject.getPrincipal()).getUserId());
            return ResultVo.success(map);
        } catch (Exception e){
            return ResultVo.failed();
        }

    }

    @ApiOperation(value = "获取用户信息", notes = "参数:用户信息")
    @GetMapping("/info/{id}")
    public ResultVo info(@PathVariable String id){
        List<JdyUserDto> jdyUserRole =  jdyUserService.seleAllUserUserRole(id);
      return ResultVo.success(jdyUserRole);
   }

    @ApiOperation(value = "人员查询", notes = "参数:人员查询")
    @PostMapping("/seleteUser")
    public ResultVo seleteUser(@ApiParam(name = "jdyUserDto", required = false)
                               @RequestBody JdyUserDto jdyUserDto){
        if(jdyUserDto.getCurrentPage() <= 0   || jdyUserDto.getPageSize()  <= 0){
            jdyUserDto.setCurrentPage(1);
        }
        PageHelper.startPage(jdyUserDto.getCurrentPage(), 200);
        List<JdyUser> list = jdyUserService.seletelist(jdyUserDto);
        PageInfo<JdyUser> pageInfo = new PageInfo(list, 5);
        return  ResultVo.success(pageInfo) ;
    }

    @ApiOperation("更新用户信息")
    @PostMapping("updateJdyUser")
    public ResultVo updatefacility(@ApiParam(name = "jdyUser", required = true)
                                   @RequestBody JdyUserDto jdyUser){
        JdyUser jdyUse = new JdyUser();
        BeanUtils.copyProperties(jdyUser,jdyUse);
        System.out.println(jdyUse);
        Map<String,Object> hahs =new HashMap<>();
        hahs.put("user_id",jdyUser.getUserId());
        boolean b1 = userRoleService.removeByMap(hahs);

        boolean b = jdyUserService.updateById(jdyUse);
        for (int i = 0; i < jdyUser.getRoleList().size(); i++) {
            UserRole userRole =new UserRole();
            userRole.setUserId(jdyUse.getUserId());
            userRole.setRoleId(jdyUser.getRoleList().get(i).getRoleId());
            userRoleService.save(userRole);
        }
        return ResultVo.success();
    }

    @ApiOperation("新增用户角色信息")
    @PostMapping("addUser")
    public ResultVo addsubset( @ApiParam(name = "jdyUser", required = true)
                               @RequestBody JdyUserDto jdyUser){
        JdyUser jdyUse = new JdyUser();
        BeanUtils.copyProperties(jdyUser,jdyUse);
        JdyUser jdyUsers = jdyUserService.saveOne(jdyUse);
        for (int i = 0; i < jdyUser.getRoleList().size(); i++) {
            UserRole userRole =new UserRole();
            userRole.setUserId(jdyUsers.getUserId());
            userRole.setRoleId(jdyUser.getRoleList().get(i).getRoleId());
            userRoleService.save(userRole);
        }
        if(jdyUsers.getUserId() != null){
            return ResultVo.success(jdyUsers);
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除用户信息")
    @DeleteMapping("deleteUser/{id}")
    public ResultVo deletefacility(@ApiParam(name = "id", value = "角色ID", required = true) @PathVariable String  id){
        boolean b = jdyUserService.removeById(id);
        if(b){
            Map<String,Object> hahs =new HashMap<>();
            hahs.put("user_id",id);
            boolean b1 = userRoleService.removeByMap(hahs);
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


    @ApiOperation("获取当前用户的详细信息")
    @PostMapping("seleteDetailsUser/{id}")
    public ResultVo seleteDetailsUser(@ApiParam(name = "id", value = "角色ID", required = true) @PathVariable String  id){

        JdyUserDto jdyUserDto = jdyUserService.seleteUserDetailsOne(id);
        if(jdyUserDto != null ){
            return ResultVo.success(jdyUserDto);
        }
        return ResultVo.failed();
    }


    @ApiOperation("获取当前用户的详细信息")
    @PostMapping("seleteTokenUser")
    public ResultVo seleteTokenUser(HttpServletRequest httpRequest){
        String requestToken = TokenUtil.getRequestToken(httpRequest);
        JdyUserDto jdyUserDto = jdyUserService.seleteUserDetailsOne(requestToken);

        if(jdyUserDto != null ){
            return ResultVo.success(jdyUserDto);
        }
        return ResultVo.failed();
    }


    @ApiOperation("微信消息推送认证")
    @PostMapping("weixinRenzheng")
    public ResultVo weixinRenzheng(@ApiParam(name = "jdyUser", required = true) @RequestBody JdyUser  jdyUser){
        JdyUser  jdyUsesr  = jdyUserService.seleteOne(jdyUser);
        if(jdyUsesr != null){
            JdyUser  jdyUss = new JdyUser();
            jdyUss.setUserId(jdyUsesr.getUserId());
            jdyUss.setWecharId(jdyUser.getWecharId());
            boolean b = jdyUserService.updateById(jdyUss);
            if(b){
                return ResultVo.success();
            }else{
                return ResultVo.failed();
            }
        }
        return ResultVo.failed();
    }








}