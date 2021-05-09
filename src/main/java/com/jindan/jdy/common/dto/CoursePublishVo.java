package com.jindan.jdy.common.dto;


import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value = "信息")
@Data
public class CoursePublishVo {

    private String id;

    private String cover;
    private String lessonNum;
    private String price;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String title;


}
