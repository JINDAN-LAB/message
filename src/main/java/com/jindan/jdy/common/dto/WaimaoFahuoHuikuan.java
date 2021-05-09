package com.jindan.jdy.common.dto;

import com.jindan.jdy.common.pojo.WaimaoFahuo;
import com.jindan.jdy.common.pojo.WaimaoHuikuan;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WaimaoFahuoHuikuan implements Serializable {

    WaimaoFahuo waimaoFahuo;

    WaimaoHuikuan  waimaoHuikuan;


}
