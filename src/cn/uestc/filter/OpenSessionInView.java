package cn.uestc.filter;

import cn.uestc.util.MybatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Auther: cmz
 * @Date: 2019/3/27
 * @Description:
 * 用来执行service层中公共代码块的过滤器
 *
 * OpenSessinInView开始是由Spring框架提出的，整合Hibernate框架是使用的是OpenSessionInView
 *
 * @version: 1.0
 */
@WebFilter("/*")
public class OpenSessionInView implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        SqlSession sqlSession = MybatisUtil.getSession();

        //fiter调用servlet,并捕获处理异常
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            sqlSession.commit();
        } catch (Exception e) {
            Logger logger = Logger.getLogger(OpenSessionInView.class);
            logger.error(e.getMessage());
            sqlSession.rollback();
        }  finally {
            MybatisUtil.closeSession();
        }
    }

    @Override
    public void destroy() {

    }
}
