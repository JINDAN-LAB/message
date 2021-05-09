package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jindan.jdy.common.pojo.StarchOrganizationPut;
import com.jindan.jdy.common.vo.PageVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class StarchPropertyScrapDto extends PageVO implements Serializable {

        private static final long serialVersionUID = 1598233957004L;

        @TableId(value = "bid", type = IdType.UUID)
        @ApiModelProperty(name = "bid" , value = "主键ID")
        private String bid;

        @ApiModelProperty(name = "status" , value = "办理状态")
        private String status;

        @ApiModelProperty(name = "clearNumber" , value = "清理单号")
        private String clearNumber;

        @ApiModelProperty(name = "clearDate" , value = "清理日期")
        private String clearDate;

        @ApiModelProperty(name = "clearPerson" , value = "清理人")
        private String clearPerson;

        @ApiModelProperty(name = "clearExplain" , value = "清理说明")
        private String clearExplain;

        @ApiModelProperty(name = "parentId" , value = "父类ID")
        private String parentId;

        @JsonIgnore
        @TableLogic
        @ApiModelProperty(name = "deleteId" , value = "")
        private Integer deleteId;

        List<StarchOrganizationPut> putList;

}
