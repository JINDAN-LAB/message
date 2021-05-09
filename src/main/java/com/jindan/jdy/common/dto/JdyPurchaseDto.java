package com.jindan.jdy.common.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jindan.jdy.common.pojo.JdyCommodity;
import com.jindan.jdy.common.pojo.JdyPurchase;
import com.jindan.jdy.common.vo.PageVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel
public class JdyPurchaseDto extends PageVO {

        private static final long serialVersionUID = 1586871957992L;

        @TableId(value = "purchase_id", type = IdType.UUID)
        @ApiModelProperty(name = "purchaseId" , value = "采购主键")
        private String purchaseId;

        @ApiModelProperty(name = "invoices" , value = "发票信息")
        private String invoices;

        @ApiModelProperty(name = "supplierId" , value = "供应商名称")
        private String supplierId;

        @ApiModelProperty(name = "operator" , value = "")
        private String operator;

        @ApiModelProperty(name = "purchaseType" , value = "")
        private String purchaseType;

        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        @ApiModelProperty(name = "insertTime" , value = "插入时间 不需要填入")
        @TableField(fill = FieldFill.INSERT)
        private Date insertTime;

        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        @ApiModelProperty(name = "updateTime" , value = "修改日期 不需要填入")
        @TableField(fill = FieldFill.INSERT_UPDATE)
        private Date updateTime;

        @TableLogic
        @ApiModelProperty(name = "deleteId" , value = "")
        private Integer deleteId;

        @ApiModelProperty(name = "statics" , value = "")
        private String statics;

        @ApiModelProperty(name = "purchaseTime" , value = "")
        private String purchaseTime;

        @ApiModelProperty(name = "departments" , value = "")
        private String departments;

        @ApiModelProperty(name = "status" , value = "")
        private String status;

        @ApiModelProperty(name = "allMoney" , value = "")
        private String allMoney;

        @ApiModelProperty(name = "transports" , value = "")
        private String transports;

        List<JdyCommodity> listsCommodity;

        @ApiModelProperty(name = "reults" , value = "")
        public boolean reults;

        @ApiModelProperty(name = "flowRemarks" , value = "")
        private String flowRemarks;

        @ApiModelProperty(name = "puuids" , value = "关联商品")
        private String puuids;

}
