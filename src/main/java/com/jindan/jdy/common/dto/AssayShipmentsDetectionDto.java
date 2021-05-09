package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jindan.jdy.common.vo.PageVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AssayShipmentsDetectionDto extends PageVO {

    private static final long serialVersionUID = 1598011512953L;

    @TableId(value = "fid", type = IdType.UUID)
    @ApiModelProperty(name = "fid" , value = "")
    private String fid;

    @ApiModelProperty(name = "suppliesName" , value = "物料名称")
    private String suppliesName;

    @ApiModelProperty(name = "invoiceNumber" , value = "发票号")
    private String invoiceNumber;

    @ApiModelProperty(name = "batchNumber" , value = "批次号")
    private String batchNumber;

    @ApiModelProperty(name = "packGoods" , value = "包装物")
    private String packGoods;

    @ApiModelProperty(name = "productStandard" , value = "产品标准")
    private String productStandard;

    @ApiModelProperty(name = "numbers" , value = "数量（吨）")
    private String numbers;

    @ApiModelProperty(name = "qualityRequire" , value = "品质要求")
    private String qualityRequire;

    @ApiModelProperty(name = "countryOfDestination" , value = "目的国")
    private String countryOfDestination;

    @ApiModelProperty(name = "salesman" , value = "业务员")
    private String salesman;

    @ApiModelProperty(name = "putTime" , value = "入库时间")
    private String putTime;

    @ApiModelProperty(name = "shipmentsTime" , value = "发货时间")
    private String shipmentsTime;

    @ApiModelProperty(name = "measurement" , value = "实测结果")
    private String measurement;

    @TableLogic
    @ApiModelProperty(name = "deleteId" , value = "")
    private Integer deleteId;

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

}
