package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jindan.jdy.common.pojo.JdyAppletFoodSafety;
import com.jindan.jdy.common.pojo.JdyAppletFoodSafetyProblemsReult;
import com.jindan.jdy.common.pojo.JdyAppletFootSafetyPerson;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class JdyAppletFoodSafetyProblemsExcle  implements Serializable {

    @TableId(value = "pid", type = IdType.UUID)
    @ApiModelProperty(name = "pid" , value = "")
    private String pid;

    @ApiModelProperty(name = "username" , value = "")
    private String username;

    @ApiModelProperty(name = "password" , value = "")
    private String password;

    @ApiModelProperty(name = "departments" , value = "")
    private String departments;

    @ApiModelProperty(name = "chejian" , value = "")
    private String chejian;

    @ApiModelProperty(name = "gongduan" , value = "")
    private String gongduan;

    @ApiModelProperty(name = "banzu" , value = "")
    private String banzu;

    @ApiModelProperty(name = "quanxian" , value = "班组，车间，总监，公司")
    private String quanxian;

    @ApiModelProperty(name = "zongjianid" , value = "总监ID")
    private String zongjianid;

    @ApiModelProperty(name = "gongsi" , value = "公司ID")
    private String gongsi;



    JdyAppletFoodSafety  jdyAppletFoodSafety;

    List<JdyAppletFoodSafetyProblemsReult>   jdyAppletFoodSafetyProblemsReultList;

}
