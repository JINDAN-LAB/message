package com.jd.message.dao;

import com.jd.message.entity.Project;

import java.util.List;

/**
 * Created by 13348 on 2021/4/13.
 */
public interface ProjectDao {

    int addProject(Project project);

    List<Project> selectProject(String name);

    int updateProject(Project project);

    int deleteProject(int id);
}
