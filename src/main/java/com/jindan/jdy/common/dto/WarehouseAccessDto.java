package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jindan.jdy.common.pojo.JdyDepartments;
import com.jindan.jdy.common.pojo.WarehouseDepository;
import com.jindan.jdy.common.pojo.WarehouseGoods;
import com.jindan.jdy.common.pojo.WarehouseSpecs;
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
public class WarehouseAccessDto extends PageVO {

    private static final long serialVersionUID = 15893855L;

    @TableId(value = "id", type = IdType.UUID)
    @ApiModelProperty(name = "id" , value = "id")
    private String id;

    @ApiModelProperty(name = "type" , value = "类型：1入库  2出库")
    private String type;

    @ApiModelProperty(name = "goodsId" , value = "货物id")
    private String goodsId;

    @ApiModelProperty(name = "goodsName" , value = "货物名称")
    private String goodsName;

    @ApiModelProperty(name = "specsId" , value = "规格id")
    private String specsId;

    @ApiModelProperty(name = "number" , value = "数量")
    private Integer number;

    @ApiModelProperty(name = "depaId" , value = "部门id")
    private Integer depaId;

    @ApiModelProperty(name = "depaName" , value = "部门name")
    private String depaName;

    @ApiModelProperty(name = "createName" , value = "创建人姓名")
    private String createName;

    @ApiModelProperty(name = "createId" , value = "创建人id")
    private Integer createId;

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
    @ApiModelProperty(name = "deleteId" , value = "删除状态")
    private Integer deleteId;

    WarehouseGoods warehouseGoods;

    WarehouseSpecs  warehouseSpecs;

    WarehouseDepository warehouseDepository;


}
