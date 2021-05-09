package com.jindan.jdy.wechat;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class AccessSmsXiaoxi {

    private String touser;
    private String template_id;
    private String url;
    private Map<String, Datas> data;

}


