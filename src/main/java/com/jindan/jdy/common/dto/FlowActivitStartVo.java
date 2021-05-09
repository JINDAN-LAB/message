package com.jindan.jdy.common.dto;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FlowActivitStartVo  implements Serializable {

   private  String  byId;

   private  String  basessKey;

   private  String  names;

   Map<String, Object> mmap;





}
