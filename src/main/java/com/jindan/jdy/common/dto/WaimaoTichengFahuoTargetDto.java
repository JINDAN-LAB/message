package com.jindan.jdy.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by 13348 on 2021/8/26.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WaimaoTichengFahuoTargetDto {

        private static final long serialVersionUID = 1592364751490L;

        private String yewuyuan;

        private Float shuliang;

        private String fahuotime;
}
