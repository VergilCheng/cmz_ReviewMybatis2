package cn.uestc.service.impl;

import cn.uestc.mapper.studentMapper;
import cn.uestc.mapper.teacherMapper;
import cn.uestc.pojo.PageInfo;
import cn.uestc.pojo.Student;
import cn.uestc.pojo.Teacher;
import cn.uestc.service.StudentService;
import cn.uestc.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @Auther: cmz
 * @Date: 2019/4/1
 * @Description: cn.uestc.service.impl
 * @version: 1.0
 */
public class StudentServiceImpl implements StudentService {
    @Override
    public PageInfo showPage(String sname, String tname, String pageSizeStr, String pageNumberStr) {

        int pageSize = 2;
        if (pageSizeStr != null && !pageSizeStr.equalsIgnoreCase("")) {
            pageSize = Integer.parseInt(pageSizeStr);
        }
        int pageNumber = 1;
        if (pageNumberStr != null && !pageNumberStr.equalsIgnoreCase("")) {
            pageNumber = Integer.parseInt(pageNumberStr);
        }

        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNumber(pageNumber);
        pageInfo.setPageStart((pageNumber - 1) * pageSize);
        pageInfo.setPageSize(pageSize);
        pageInfo.setSname(sname);
        pageInfo.setTname(tname);
        SqlSession session = MybatisUtil.getSession();
        studentMapper studentMapper = session.getMapper(cn.uestc.mapper.studentMapper.class);
        teacherMapper teacherMapper = session.getMapper(cn.uestc.mapper.teacherMapper.class);
        List<Student> students = studentMapper.selByPage(pageInfo);
        //查询出每个学生对应的老师信息
        for (Student s:
             students) {
            Teacher teacher = teacherMapper.selById(s.getTid());
            s.setTeacher(teacher);
        }

        long count = studentMapper.selCount();
        pageInfo.setList(students);
        long total = count / pageSize == 0 ? count / pageSize : count / pageSize + 1;
        pageInfo.setTotal(total);

        return pageInfo;
    }
}
