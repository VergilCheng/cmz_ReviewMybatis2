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
 * @Auther: cmz
 * @Date: 2019/3/27
 * @Description: cn.uestc.test
 * mybatis针对查询进行了内存缓存优化，减少客户端和数据库之间的交互，提高效率和响应速度
 * @version: 1.0
 */
public class TestMybatisCatch {

    /**
     * 同一个session或者同一个session的不同mapper调用同一个select方法，
     * 只生成一条日志，说明第二次调用的方法得到的是缓存中的tatement对象
     * 而不是再次访问数据库。
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(stream);
        SqlSession session = sessionFactory.openSession();
        logMapper mapper = session.getMapper(logMapper.class);
        List<Log> logs = mapper.selectAllLogs();
        List<Log> logs1 = mapper.selectAllLogs();
        System.out.println(logs == logs1);//结果为true，说明同一个mapper调用同一个方法返回结果是相同对象
        session.close();
    }

    /**
     * 同一个session生成两个不同的mapper，两个不同mapper调用同一个select方法，
     * 只生成一条日志,返回的结果为同一个对象。也是运用到了缓存，这是因为mybatis不是
     * 按照mapper对象来生成缓存，而是按照SqlSession对象来生成缓存
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(stream);
        SqlSession session = sessionFactory.openSession();
        logMapper mapper = session.getMapper(logMapper.class);
        logMapper mapper1 = session.getMapper(logMapper.class);
        List<Log> logs = mapper.selectAllLogs();
        List<Log> logs1 = mapper1.selectAllLogs();
        System.out.println(logs==logs1);//true
        System.out.println(mapper==mapper1);//false
        session.close();

    }

    /**
     * 不同session调用同一个select方法生成两条日志，返回的logs也不相同
     */
    @Test
    public void test3() throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(stream);
        SqlSession session = sessionFactory.openSession();
        SqlSession session1 = sessionFactory.openSession();
        List<Object> list1 = session.selectList("cn.uestc.mapper.logMapper.selectAllLogs");
        session.close();
        List<Object> list = session1.selectList("cn.uestc.mapper.logMapper.selectAllLogs");
        System.out.println(list == list1);//false
        session1.close();
    }
    /**
     * 二级缓存:对SqlSessionFactory进行缓存，则不同session调用同一个select方法
     * 返回结果相同，生成日志为一条
     *
     * 二级缓存使用说明：
     * 1.二级缓存的空间与SqlSession缓存空间不同，只有SqlSession提交或者关闭，才会将SqlSession刷到SqlSessionFactory缓存区中
     * 2.当数据频繁被使用,很少被修改可使用二级缓存，频繁修改会造成缓存中的数据与数据库中数据一直不一致。即使commit或者close
     *   使得数据刷新也跟不上数据修改的速度
     * 3.使用二级缓存可以加快客户端访问，即使客户端重新开一个线程继续访问输入相同的sql语句，由于存在二级缓存，使得不同的SqlSession
     *   也能够返回相同结果，不会对数据库造成太大压力。
     * 做法：
     * 1.在mapper.xml文件中添加<cache readOnly="true"></cache>标签
     * 2.在mapper.xml文件中添加<cache></cache>标签，并将pojo类序列化，不序列化pojo类会报错
     */
    @Test
    public void test4() throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(stream);
        SqlSession session = sessionFactory.openSession();
        SqlSession session1 = sessionFactory.openSession();
        List<Object> list = session.selectList("cn.uestc.mapper.logMapper.selectAllLogs");
        session.close();
        List<Object> list1 = session1.selectList("cn.uestc.mapper.logMapper.selectAllLogs");
        System.out.println(session==session1);//false
        System.out.println(list == list1);//true
        session1.close();
    }
}
