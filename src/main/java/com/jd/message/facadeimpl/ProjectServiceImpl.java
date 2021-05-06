package com.jd.message.facadeimpl;

import com.jd.message.dao.ProjectDao;
import com.jd.message.entity.Project;
import com.jd.message.facade.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 13348 on 2021/4/14.
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Autowired
    private ProjectDao projectDao;

    public int addProject(Project project) {
        return projectDao.addProject(project);
    }

    @Override
    public List<Project> selectProject(String name) {
        return projectDao.selectProject(name);
    }

    @Override
    public int updateProject(Project project) {
        return projectDao.updateProject(project);
    }

    @Override
    public int deleteProject(int id) {
        return projectDao.deleteProject(id);
    }
}
