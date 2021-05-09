package com.jindan.jdy.common.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubjectOneVo {

    private String id;
    private String title;
    private List<SubjectTwoVo> children = new ArrayList<>();
}
