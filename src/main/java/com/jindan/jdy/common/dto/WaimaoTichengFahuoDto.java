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

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WaimaoTichengFahuoDto extends PageVO implements Serializable {

        private static final long serialVersionUID = 1592364751490L;

        @TableId(value = "id", type = IdType.AUTO)
        @ApiModelProperty(name = "id" , value = "")
        private Integer id;

        @ApiModelProperty(name = "danjuhao" , value = "")
        private String danjuhao;

        @ApiModelProperty(name = "fahuotime" , value = "")
        private String fahuotime;

        @ApiModelProperty(name = "fapiaohao" , value = "")
        private String fapiaohao;

        @ApiModelProperty(name = "hetonghao" , value = "")
        private String hetonghao;

        @ApiModelProperty(name = "pici" , value = "")
        private String pici;

        @ApiModelProperty(name = "yewuyuan" , value = "")
        private String yewuyuan;

        @ApiModelProperty(name = "shouhuokehu" , value = "")
        private String shouhuokehu;

        @ApiModelProperty(name = "diqufenlei" , value = "")
        private String diqufenlei;

        @ApiModelProperty(name = "yujizhuangtime" , value = "")
        private String yujizhuangtime;

        @ApiModelProperty(name = "wuliaoming" , value = "")
        private String wuliaoming;

        @ApiModelProperty(name = "shuliang" , value = "")
        private Float shuliang;

        @ApiModelProperty(name = "hanshuidanjia" , value = "")
        private Float hanshuidanjia;

        @ApiModelProperty(name = "bizhong" , value = "")
        private String bizhong;

        @ApiModelProperty(name = "jiagetiaokuan" , value = "")
        private String jiagetiaokuan;

        @ApiModelProperty(name = "jiashuiheji" , value = "")
        private String jiashuiheji;

        @ApiModelProperty(name = "benbijiashuiheji" , value = "")
        private String benbijiashuiheji;

        @ApiModelProperty(name = "baozhuangwu" , value = "")
        private String baozhuangwu;

        @ApiModelProperty(name = "huikuanxieyi" , value = "")
        private String huikuanxieyi;

        @ApiModelProperty(name = "yunshudanjia" , value = "")
        private String yunshudanjia;

        @ApiModelProperty(name = "yunshujine" , value = "")
        private String yunshujine;

        @ApiModelProperty(name = "huilv" , value = "")
        private String huilv;

        @ApiModelProperty(name = "fobzongjia" , value = "")
        private String fobzongjia;

        @ApiModelProperty(name = "haiyunfei" , value = "")
        private String haiyunfei;

        @ApiModelProperty(name = "yongjin" , value = "")
        private String yongjin;

        @ApiModelProperty(name = "yufukuan" , value = "")
        private String yufukuan;

        @ApiModelProperty(name = "lixijiang" , value = "")
        private String lixijiang;

        @ApiModelProperty(name = "fobticheng" , value = "")
        private String fobticheng;

        @ApiModelProperty(name = "xinkehuticheng" , value = "")
        private String xinkehuticheng;

        @ApiModelProperty(name = "biaoji" , value = "")
        private String biaoji;

        @ApiModelProperty(name = "shifouxinkehu" , value = "")
        private String shifouxinkehu;

        @ApiModelProperty(name = "diushikehu" , value = "")
        private String diushikehu;

        @ApiModelProperty(name = "huikuanlv" , value = "")
        private String huikuanlv;

        @ApiModelProperty(name = "yijisuan" , value = "")
        private String yijisuan;

        @ApiModelProperty(name = "ceshi1" , value = "")
        private String ceshi1;

        @ApiModelProperty(name = "ceshi2" , value = "")
        private String ceshi2;

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

        private List<WaimaoTichengHuikuan> huikuanList;

        WaimaoTichengBaozhuang waimaoTichengBaozhuang;

        WaimaoTarget waimaoTarget;


        WaimaoTichengHuilv waimaoTichengHuilv;

        WaimaoTichengJijiabiao waimaoTichengJijiabiao;



}
