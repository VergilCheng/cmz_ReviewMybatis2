package cn.uestc.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Auther: cmz
 * @Date: 2019/4/1
 * @Description: cn.uestc.filter
 * 防止get方法或者post方法的request和response对象的中文乱码
 *
 * 注意：get方法提交表单的乱码问题不会受到filter控制器控制，会默认使用tomcat的编码iso-8859-1
 * 需要我们对客户端参数进行手动编码替换
 * @version: 1.0
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {

    protected FilterConfig config;
    protected String encoding = null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;

        /* 从web.xml里读取编码的配置初始值 */
        this.encoding = config.getInitParameter("Encoding");
    }

    /**
     * 具体实现的过滤方法
     * */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (encoding == null) {
            encoding = "UTF-8";
        }
        //基本上这三种编码设置后,请求\响应都不会出现乱码了
        servletResponse.setCharacterEncoding(encoding);
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setContentType("text/html;charset=" + encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        encoding = null;
    }
}
