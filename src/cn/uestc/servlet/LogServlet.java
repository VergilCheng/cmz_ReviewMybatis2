package cn.uestc.servlet;

import cn.uestc.pojo.Log;
import cn.uestc.service.LogService;
import cn.uestc.service.impl.LogServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Auther: cmz
 * @Date: 2019/3/27
 * @Description: cn.uestc.servlet
 * @version: 1.0
 */
@WebServlet("/insert")
public class LogServlet extends HttpServlet {

    private LogService logService = new LogServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        HttpSession httpSession = req.getSession();
        Log log = new Log();
        log.setBalance(Double.parseDouble(req.getParameter("money")));
        log.setAcconIn(req.getParameter("accin"));
        log.setAcconOut(req.getParameter("accout"));
        int index = logService.insert(log);
        try {
            if (index > 0) {
                httpSession.setAttribute("result","success");
                resp.sendRedirect("/cmz_ReviewMybatis2/success.jsp");
            } else {
                httpSession.setAttribute("result","error");
                resp.sendRedirect("/cmz_ReviewMybatis2/error.jsp");
            }
        } catch (Exception e) {
            Logger logger = Logger.getLogger(LogServlet.class);
            logger.error(e.getMessage());
            throw e;
        }
    }
}
