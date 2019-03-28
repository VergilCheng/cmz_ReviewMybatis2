package cn.uestc.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * @Auther: cmz
 * @Date: 2019/3/27
 * @Description: 该工具类与cn.uestc.filter包中的OpenSessionInView用来优化mybatis第二天银行转账的能力提升代码：
 * service层频繁创建SqlSessionFactory的性能损耗，以及简化频繁书写创建SqlSessionFactory与SqlSession、以及sqlSession的close方法。
 * 由于业务代码是在创建SqlSession与关闭SqlSession之间的，也就是说公共代码在业务代码的头尾两侧，所以我们的公共代码，可以放到filter中执行，
 * 因为servlet的链式责任，filter会在servlet执行前后都进行dofilter方法的执行。
 * @version: 1.0
 */
public class MybatisUtil {
    //factory实例化的过程比较消耗性能，保证有且只有一个factory
    private static SqlSessionFactory factory;
    //filter和servlet（servlet包括service层，只是将servlet的功能分层了而已）中的方法是同一个线程执行
    //由于SqlSession在filter中产生，所以为了保证filter和servlet中的Sqlsession是同一个对象（不是同一个对象
    //可能会导致当前事务不是由当前SqlSession提交，导致数据污染），我们可以将SqlSession对象作为参数从filter
    //中传递到servlet中。但是方法参数传递由于servlet层的service方法以及doGet，doPost方法已经确定了参数，再加
    //SqlSession参数则是方法重载而不是重写了，所以方法参数传递不可行。我们利用他们是同一个线程的特性，将SqlSession
    //放到ThreadLocal中则可以实现参数传递。
    private static ThreadLocal<SqlSession> local = new ThreadLocal<SqlSession>();

    /**
     * 功能描述:构造器私有化，单例模式
     * @param:
     * @return:
     * @auther: cmz
     * @date:
     */
    private MybatisUtil() {

    }

    static {
        try {
            factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能描述:返回sqlSession对象，如果ThreadLocal中有sqlSession对象，则不需要工厂创建，直接返回该对象
     * 防止在filter和service层通过MybatisUtil获取到的SqlSesiion不同（不加判断的话filter和service层会各自创建
     * 一个SqlSession对象）
     * @param: 
     * @return: 
     * @auther: cmz
     * @date:
     */
    public static SqlSession getSession() {
        if (local.get() != null) {
            return local.get();
        } else {
            SqlSession sqlSession = factory.openSession();
            local.set(sqlSession);
            return sqlSession;
        }
    }

    /**
     * 功能描述:关闭sqlSession,并将ThreadLocal中的SqlSession清空
     * @param:
     * @return:
     * @auther: cmz
     * @date:
     */
    public static void closeSession() {
        SqlSession sqlSession = local.get();
        if (sqlSession != null) {
            sqlSession.close();
        }
        //清空SqlSession
        local.set(null);
    }
}
