package com.jd.message.facade;

import com.jd.message.entity.Project;

import java.util.List;

/**
 * Created by 13348 on 2021/4/14.
 */
public interface ProjectService {

    int addProject(Project project);

    List<Project> selectProject(String name);

    int updateProject(Project project);

    int deleteProject(int id);
}
