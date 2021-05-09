package com.jindan.jdy.common.dto;


import com.jindan.jdy.common.pojo.WarehouseCheck;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WarehouseCheckHttpParam {


    List<WarehouseCheck> list;





}
