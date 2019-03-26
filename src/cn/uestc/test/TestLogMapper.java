package cn.uestc.test;

import cn.uestc.mapper.logMapper;
import cn.uestc.pojo.Log;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 采用接口绑定mapper.xml文件进行查询测试
 */

public class TestLogMapper {
    /**
     * 测试接口绑定mapper.xml文件查询
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /*
            使用sqlSession获得一个logMapper的实现类

            接口，为什么能实例化？
            因为底层使用的是JDK动态代理设计模式，返回的一个实例化对象。

         */
        logMapper logMapper = sqlSession.getMapper(logMapper.class);
        List<Log> logs = logMapper.selectAllLogs();
        for (Log log:
             logs) {
            System.out.println(log);
        }

    }
    /**
     * 测试接口绑定mapper.xml文件多参数查询
     */
    @Test
    public void test2() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        logMapper logMapper = sqlSession.getMapper(logMapper.class);
        Log log = logMapper.selectByAccInAccout("6227", "0038",2 );
        System.out.println(log);

    }
}
