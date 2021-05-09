package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jindan.jdy.common.pojo.*;
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
public class DomesticFahuoDto extends PageVO {

        private static final long serialVersionUID = 1588822714062L;

        @TableId(value = "id", type = IdType.UUID)
        @ApiModelProperty(name = "id" , value = "")
        private Integer id;

        @ApiModelProperty(name = "danjuhao" , value = "")
        private String danjuhao;

        @ApiModelProperty(name = "danjuriqi" , value = "")
        private String danjuriqi;

        @ApiModelProperty(name = "yewuyuan" , value = "")
        private String yewuyuan;

        @ApiModelProperty(name = "fangshi" , value = "")
        private String fangshi;

        @ApiModelProperty(name = "shouhuokehu" , value = "")
        private String shouhuokehu;

        @ApiModelProperty(name = "wuliaomingcheng" , value = "")
        private String wuliaomingcheng;

        @ApiModelProperty(name = "bzw" , value = "")
        private String bzw;

        @ApiModelProperty(name = "shuliang" , value = "")
        private String shuliang;

        @ApiModelProperty(name = "hanshuidanjia" , value = "")
        private String hanshuidanjia;

        @ApiModelProperty(name = "jiashuiheji" , value = "")
        private String jiashuiheji;

        @ApiModelProperty(name = "yunshudanjia" , value = "")
        private String yunshudanjia;

        @ApiModelProperty(name = "bhbz" , value = "")
        private String bhbz;

        @ApiModelProperty(name = "shifouweixinkehu" , value = "")
        private String shifouweixinkehu;

        @ApiModelProperty(name = "yongjinbili" , value = "")
        private String yongjinbili;

        @ApiModelProperty(name = "yongjin" , value = "")
        private String yongjin;

        @ApiModelProperty(name = "danhao" , value = "")
        private String danhao;

        @ApiModelProperty(name = "jijiaticheng" , value = "")
        private String jijiaticheng;

        @ApiModelProperty(name = "jijiatichengbili" , value = "")
        private String jijiatichengbili;

        @ApiModelProperty(name = "shoukuanxieyi" , value = "")
        private String shoukuanxieyi;

        @ApiModelProperty(name = "jisuanriqi" , value = "")
        private String jisuanriqi;

        @ApiModelProperty(name = "dayin" , value = "")
        private String dayin;

        @ApiModelProperty(name = "queren" , value = "")
        private String queren;

        @ApiModelProperty(name = "dayintime" , value = "")
        private String dayintime;

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

        DomesticBaozhuang domesticBaozhuang;

        DomesticJijiabiao domesticJijiabiao;

        List<DomesticHuikuan> huikuanList;

        DomesticXishu domesticXishu;


        @ApiModelProperty(name = "chanmofeiyong" , value = "")
        private String chanmofeiyong;



 }

