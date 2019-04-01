package cn.uestc.servlet;

import cn.uestc.pojo.PageInfo;
import cn.uestc.service.StudentService;
import cn.uestc.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: cmz
 * @Date: 2019/4/1
 * @Description: cn.uestc.servlet
 * @version: 1.0
 */
@WebServlet("/showSandT")
public class StudentServlet extends HttpServlet {

    private StudentService service = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get方式提交表单，会出现中文乱码，需要按照如下方式进行编码转换
        String sname = req.getParameter("sname");
        /*防止第一次访问主页转发跳转没有sname以及tname发生空指针异常*/
        if (sname!=null&&!sname.equalsIgnoreCase("")) {
            sname = new String(sname.getBytes("iso-8859-1"), "utf-8");
        }
        String tname = req.getParameter("tname");
        if (tname!=null&&!tname.equalsIgnoreCase("")) {
            tname = req.getParameter("tname");
            tname = new String(tname.getBytes("iso-8859-1"), "utf-8");
        }
        String pageSizeStr = req.getParameter("pageSize");
        String pageNumberStr = req.getParameter("pageNumber");
        PageInfo pageInfo = service.showPage(sname, tname, pageSizeStr, pageNumberStr);
        req.setAttribute("pageInfo", pageInfo);
        req.getRequestDispatcher("student.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
