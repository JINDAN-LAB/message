package com.jd.message.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * Created by 13348 on 2021/4/13.
 */
public class DaoUtils {

    public static SqlSession getSqlSession(SqlSessionFactory sqlSessionFactory) {
        SqlSessionDaoSupport sqlSessionDaoSupport = new JDSessionDaoSupport();
        sqlSessionDaoSupport.setSqlSessionFactory(sqlSessionFactory);

        return sqlSessionDaoSupport.getSqlSession();
    }
}

class JDSessionDaoSupport extends SqlSessionDaoSupport {
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
}
