package com.jindan.jdy.common.dto;

import com.jindan.jdy.common.vo.PageVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DepartmentAndDutiesDto extends PageVO{

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @ApiModelProperty(name = "jdyUserId" , value = "")
    private String jdyUserId;


    /**
     * 部门
     */
    @ApiModelProperty(name = "department" , value = "")
    private String department;

    /**
     * 职责
     */
    @ApiModelProperty(name = "duties" , value = "")
    private String duties;

}
