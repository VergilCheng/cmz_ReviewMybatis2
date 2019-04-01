package cn.uestc.test;

import cn.uestc.pojo.ResultMap_Teacher;
import cn.uestc.pojo.Student;
import cn.uestc.pojo.Teacher;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Auther: cmz
 * @Date: 2019/4/1
 * @Description: cn.uestc.test
 *
 *
 * 演示Mybatis多表查询：所有情况的具体实现步骤参照mybatis第四天对应笔记
 * 1.Mybatis 实现多表查询方式
 *      1.1 业务装配.对两个表编写单表查询语句,在业务(Service)把查询
 *      的两个结果进行关联.(例如今天的能力提升：学生与老师多表查询，dao层分别为单表查询，
 *      service层将单表查询结果整合在Student类中，Student类中注入Teacher属性)
 *      1.2 使用 Auto Mapping 特性,在实现两表联合查询时通过别名完成映射.(不能使用N+1方式，只能使用联合查询sql)
 *          PS:使用AutoMapping+别名方法不适用于集合属性，也就是下文2.2的情况，只能借助于collection标签。
 *      1.3 使用 MyBatis 的<resultMap>标签进行实现.
 *          1.3.1 使用<resulMap>标签实现多表查询——>N+1方式，不需要编写联合查询sql，在各自表中编写各自的查询sql（原理同1.1业务装配）
 *                N+1方式：N+1 查询方式,先查询出某个表的全部信息,根据这个表的信息查询另一个表的信息.
 *                N+1方式解释：与业务装配的本质是一样的，只是将service层中的java代码，变成mapper.xml文件中采用resultMap和association注解来实现
 *          1.3.2 使用<resulMap>标签实现多表查询——>需要编写联合查询sql
 * 2.Mybatis多表查询返回的结果,封装方式通常为：
 *   将一个表对应的类作为属性注入到另一个表对应的类中,即本类中包含另一个类作为本类的属性，这个属性可以是：
 *      2.1 单个：例如A类中包含B类属性：private B b，mapper.xml文件中应当使用association标签进行声明。
 *      2.2 集合：例如A类中包含B类属性：private List<B> list，mapper.xml文件中应当使用collection标签进行声明。
 *
 *
 * @version: 1.0
 */
public class TestResultMap {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
    }


    /**
     * 测试单表使用resultMap的写法
     */
    @Test
    public void test1() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<ResultMap_Teacher> list = sqlSession.selectList("cn.uestc.mapper.teacherMapper.selAll");
        for (ResultMap_Teacher t :
                list) {
            System.out.println(t);
        }
    }

    /**
     * 测试使用 resultMap 实现关联单个对象(N+1 方式)
     */
    @Test
    public void tes2() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Student> list = sqlSession.selectList("cn.uestc.mapper.studentMapper.selAll");
        for (Student s :
                list) {
            System.out.println(s);
        }
    }
    /**
     * 测试使用 resultMap 实现关联单个对象
     * (大前提：N+1 方式
     * 单独给tid进行映射配置)
     */
    @Test
    public void tes2_1() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Student> list = sqlSession.selectList("cn.uestc.mapper.studentMapper.selAll2");
        for (Student s :
                list) {
            System.out.println(s);
        }
    }

    /**
     * 测试使用 resultMap 实现关联单个对象(联合查询方式)
     */
    @Test
    public void tes3() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Student> list = sqlSession.selectList("cn.uestc.mapper.studentMapper.selAll3");
        for (Student s :
                list) {
            System.out.println(s);
        }
    }


    /**
     * 测试使用resultMap关联集合对象(N+1方式)
     *
     * 注意：mybatis可以通过主键判断对象是否被加载过，所以不需要担心创建重复对象
     * 因为teacher表中只有两个teacher，所以返回结果为两个teacher对象，对象中的属性list包含了多个学生对象
     */
    @Test
    public void tes4() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Teacher> list = sqlSession.selectList("cn.uestc.mapper.teacherMapper.selAll2");
        for (Teacher t : list) {
            System.out.println(t);
        }
    }

    /**
     * 测试使用resultMap关联集合对象(联合查询方式)
     * 注意：mybatis可以通过主键判断对象是否被加载过，所以不需要担心创建重复对象
     * 因为teacher表中只有两个teacher，所以返回结果为两个teacher对象，对象中的属性list包含了多个学生对象
     */
    @Test
    public void tes5() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Teacher> list = sqlSession.selectList("cn.uestc.mapper.teacherMapper.selAll3");
        for (Teacher t : list) {
            System.out.println(t);
        }
    }
    /**
     * 测试使用别名+AutoMapping特性进行多表查询（必须使用联合查询sql）
     */
    @Test
    public void tes6() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Object> list = sqlSession.selectList("cn.uestc.mapper.studentMapper.selAll4");
        for (Object st : list) {
            System.out.println(st);
        }
    }
}

