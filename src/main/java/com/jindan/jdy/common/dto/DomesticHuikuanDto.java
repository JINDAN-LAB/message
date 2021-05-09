package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jindan.jdy.common.pojo.DomesticHuikuan;
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
public class DomesticHuikuanDto extends PageVO {

        private static final long serialVersionUID = 15888729307L;

        @TableId(value = "id", type = IdType.UUID)
        @ApiModelProperty(name = "id" , value = "")
        private String id;

        @ApiModelProperty(name = "huikuanriqi" , value = "")
        private String huikuanriqi;

        @ApiModelProperty(name = "xingming" , value = "")
        private String xingming;

        @ApiModelProperty(name = "kehumingcheng" , value = "")
        private String kehumingcheng;

        @ApiModelProperty(name = "jine" , value = "")
        private String jine;

        @ApiModelProperty(name = "jine2" , value = "")
        private Float jine2;

        @ApiModelProperty(name = "shijishiyongjine" , value = "")
        private String shijishiyongjine;

        @ApiModelProperty(name = "chengduiriqi" , value = "")
        private String chengduiriqi;

        @ApiModelProperty(name = "xiankuanjiang" , value = "")
        private String xiankuanjiang;

        @ApiModelProperty(name = "lixijiang" , value = "")
        private String lixijiang;

        @ApiModelProperty(name = "xiankuanjiangbili" , value = "")
        private String xiankuanjiangbili;

        @ApiModelProperty(name = "lixijiangbili" , value = "")
        private String lixijiangbili;

        @ApiModelProperty(name = "biaoshi" , value = "")
        private String biaoshi;

        @ApiModelProperty(name = "yufutianshu" , value = "")
        private String yufutianshu;

        @ApiModelProperty(name = "daorutime" , value = "")
        private String daorutime;

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



}
