package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jindan.jdy.common.pojo.DepartmentFacility;
import com.jindan.jdy.common.pojo.DepartmentSubfacility;
import com.jindan.jdy.common.pojo.JdyUserFile;
import com.jindan.jdy.common.vo.PageVO;
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
public class DepartmentFacilityDto extends PageVO {

        private static final long serialVersionUID = 1586872037952L;

        @TableId(value = "id", type = IdType.UUID)
        @ApiModelProperty(name = "id" , value = "")
        private String id;

        @ApiModelProperty(name = "goodsId" , value = "购物ID")
        private String goodsId;

        @ApiModelProperty(name = "brand" , value = "")
        private String brand;

        @ApiModelProperty(name = "model" , value = "")
        private String model;

        @ApiModelProperty(name = "nuit" , value = "")
        private String nuit;

        @ApiModelProperty(name = "number" , value = "")
        private Integer number;

        @ApiModelProperty(name = "price" , value = "")
        private Float price;

        @ApiModelProperty(name = "states" , value = "")
        private String states;

        @ApiModelProperty(name = "createName" , value = "")
        private String createName;

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
        @ApiModelProperty(name = "insertTime" , value = "")
        @TableField(fill = FieldFill.INSERT)
        private Date insertTime;

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
        @ApiModelProperty(name = "updateTime" , value = "")
        @TableField(fill = FieldFill.INSERT_UPDATE)
        private Date updateTime;

        @ApiModelProperty(name = "fileId" , value = "")
        private String fileId;

        @ApiModelProperty(name = "imgurl" , value = "")
        private String imgurl;

        @ApiModelProperty(name = "parentId" , value = "")
        private String parentId;

        @TableLogic
        @ApiModelProperty(name = "deleteId" , value = "")
        private Integer deleteId;

        @ApiModelProperty(name = "bitNumber" , value = "位号")
        private String bitNumber;

        @ApiModelProperty(name = "maintainPerson" , value = "责任人")
        private String maintainPerson;

        @ApiModelProperty(name = "isRepair" , value = "是否定期检修")
        private String isRepair;
        @ApiModelProperty(name = "repairPeriod" , value = "定期检修周期")
        private String repairPeriod;

        @ApiModelProperty(name = "pidNumber" , value = "PID编码")
        private String pidNumber;

        @ApiModelProperty(name = "remarks" , value = "备注")
        private String remarks;

        @ApiModelProperty(name = "specifications" , value = "规格")
        private String specifications;

        @ApiModelProperty(name = "departments" , value = "部门")
        private String departments;

        @ApiModelProperty(name = "serialnumber" , value = "序号")
        private String serialnumber;

        @ApiModelProperty(name = "assetnumber" , value = "固定资产编号")
        private String assetnumber;

        @ApiModelProperty(name = "allkg" , value = "总重")
        private String allkg;

        @ApiModelProperty(name = "installtime" , value = "安装时间")
        private String installtime;


        @ApiModelProperty(name = "usetime" , value = "使用时间")
        private String usetime;

        @ApiModelProperty(name = "rateOfWork" , value = "功率")
        private String rateOfWork;


        @ApiModelProperty(name = "erweima" , value = "二维码")
        private String erweima;


        @ApiModelProperty(name = "powers" , value = "")
        private String powers;

        @ApiModelProperty(name = "parameters" , value = "")
        private String parameters;

        @ApiModelProperty(name = "lubrication" , value = "")
        private String lubrication;

        @ApiModelProperty(name = "texture" , value = "")
        private String texture;

        @ApiModelProperty(name = "allWeight" , value = "")
        private String allWeight;

        @ApiModelProperty(name = "revolutionsPerMinute" , value = "")
        private String revolutionsPerMinute;

        @ApiModelProperty(name = "commodityName" , value = "")
        private String commodityName;

        @ApiModelProperty(name = "frequencys" , value = "")
        private String frequencys;

        @ApiModelProperty(name = "warehouseId" , value = "")
        private String warehouseId;

        private List<DepartmentFacility> facilityList;

        @ApiModelProperty(name = "maintainPeriod" , value = "")
        private String maintainPeriod;

        @ApiModelProperty(name = "allday" , value = "使用到期时间")
        private String allday;

        private List<DepartmentSubfacility> departmentSubfacilities;

        @ApiModelProperty(name = "types" , value = "产品类型")
        private String types;

        @ApiModelProperty(name = "name1" , value = "产品类型")
        private String name1;

        @ApiModelProperty(name = "name2" , value = "产品类型")
        private String name2;
        @ApiModelProperty(name = "name3" , value = "产品类型")
        private String name3;
        @ApiModelProperty(name = "name4" , value = "产品类型")
        private String name4;
        @ApiModelProperty(name = "name5" , value = "产品类型")
        private String name5;
        @ApiModelProperty(name = "name6" , value = "产品类型")
        private String name6;
        @ApiModelProperty(name = "name7" , value = "产品类型")
        private String name7;
        @ApiModelProperty(name = "name8" , value = "产品类型")
        private String name8;
        @ApiModelProperty(name = "name9" , value = "产品类型")
        private String name9;
        @ApiModelProperty(name = "name10" , value = "产品类型")
        private String name10;
        @ApiModelProperty(name = "name11" , value = "产品类型")
        private String name11;
        @ApiModelProperty(name = "name12" , value = "产品类型")
        private String name12;
        @ApiModelProperty(name = "name13" , value = "产品类型")
        private String name13;
        @ApiModelProperty(name = "name14" , value = "产品类型")
        private String name14;

        @ApiModelProperty(name = "name15" , value = "产品类型")
        private String name15;

        @ApiModelProperty(name = "name16" , value = "产品类型")
        private String name16;

        @ApiModelProperty(name = "name17" , value = "产品类型")
        private String name17;

        @ApiModelProperty(name = "name18" , value = "产品类型")
        private String name18;

        @ApiModelProperty(name = "name19" , value = "产品类型")
        private String name19;

        @ApiModelProperty(name = "name20" , value = "产品类型")
        private String name20;

        @ApiModelProperty(name = "name21" , value = "产品类型")
        private String name21;

        @ApiModelProperty(name = "name22" , value = "产品类型")
        private String name22;

        @ApiModelProperty(name = "name23" , value = "产品类型")
        private String name23;

        @ApiModelProperty(name = "name24" , value = "产品类型")
        private String name24;

        @ApiModelProperty(name = "name25" , value = "产品类型")
        private String name25;

        @ApiModelProperty(name = "name26" , value = "产品类型")
        private String name26;

        @ApiModelProperty(name = "name27" , value = "产品类型")
        private String name27;

        @ApiModelProperty(name = "name28" , value = "产品类型")
        private String name28;

        @ApiModelProperty(name = "name29" , value = "产品类型")
        private String name29;

        @ApiModelProperty(name = "name30" , value = "产品类型")
        private String name30;

        @ApiModelProperty(name = "name31" , value = "")
        private String name31;

        @ApiModelProperty(name = "name32" , value = "")
        private String name32;

        @ApiModelProperty(name = "name33" , value = "")
        private String name33;

        @ApiModelProperty(name = "name34" , value = "")
        private String name34;

        @ApiModelProperty(name = "name35" , value = "")
        private String name35;

        @ApiModelProperty(name = "ptype" , value = "产品类型")
        private String ptype;

        private List<JdyUserFile> jdyUserFiles;


}
