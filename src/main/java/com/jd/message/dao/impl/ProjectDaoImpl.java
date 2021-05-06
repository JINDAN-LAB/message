package com.jd.message.dao.impl;


import com.jd.message.dao.ProjectDao;
import com.jd.message.entity.Project;
import com.jd.message.entity.Project;
import com.jd.message.util.DaoUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 13348 on 2021/4/13.
 */
@Repository
public class ProjectDaoImpl implements ProjectDao {

    private static final String SQL_ID = "ProjectMapper.%s";

    @Autowired
    @Qualifier("sessionFactory")
    private SqlSessionFactory sessionFactory;

    @Override
    public int addProject(Project project) {
        return DaoUtils.getSqlSession(sessionFactory).insert(String.format(SQL_ID, "addProject"),project);
    }

    @Override
    public List<Project> selectProject(String name) {
        return DaoUtils.getSqlSession(sessionFactory).selectList(String.format(SQL_ID, "selectProject"),name);
    }

    @Override
    public int updateProject(Project project) {
        return DaoUtils.getSqlSession(sessionFactory).update(String.format(SQL_ID, "updateProject"),project);
    }

    @Override
    public int deleteProject(int id) {
        return DaoUtils.getSqlSession(sessionFactory).delete(String.format(SQL_ID, "deleteProject"),id);
    }
}
