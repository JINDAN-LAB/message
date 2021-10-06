package com.jindan.jdy.common.dto;

import com.jindan.jdy.common.pojo.JdyUser;
import com.jindan.jdy.common.pojo.RiskControlSituation;
import com.jindan.jdy.common.vo.PageVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RiskPersonnelHierarchyGridDto extends PageVO {

    private static final long serialVersionUID = 1L;

    /**
     * 人员姓名
     */
    @ApiModelProperty(name = "jdyUser" , value = "")
    private String jdyUserName;

    /**
     * 部门
     */
    @ApiModelProperty(name = "department" , value = "")
    private String department;

    /**
     * 职务
     */
    @ApiModelProperty(name = "post" , value = "")
    private String post;

    /**
     * 职位等级
     */
    @ApiModelProperty(name = "positionLevel" , value = "")
    private String positionLevel;

    /**
     * 是否部门负责人
     */
    @ApiModelProperty(name = "isPersonInCharge" , value = "")
    private String isPersonInCharge;

    /**
     * 人员网格管控情况
     */
    @ApiModelProperty(name = "riskGridControls" , value = "")
    private List<RiskGridControlDto> riskGridControlDto;
}
