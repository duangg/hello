package com.nolan.mybatis;

import com.nolan.mybatis.dao.PoiDetailDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest {
    private static SqlSessionFactory factory;

    public static SqlSessionFactory getFactory() {
        if (factory == null) {
            String resource = "mybatis/mybatis-config.xml";
            InputStream inputStream = null;
            try {
                inputStream = Resources.getResourceAsStream(resource);
            } catch (IOException e) {
                e.printStackTrace();
            }
            factory = new SqlSessionFactoryBuilder().build(inputStream);
        }
        return factory;
    }

    public static void main(String[] args) {
        getFactory();
        SqlSession session = factory.openSession();
        PoiDetailDao dao = session.getMapper(PoiDetailDao.class);

        PoiModel poi = dao.selectOne(27L);
//        PoiModel poi = session.selectOne("com.nolan.mybatis.dao.PoiDetailDao", 1);
        System.out.println(poi.getAddressDetail());
    }
}
