package cn.uestc.mapper;

import cn.uestc.pojo.PageInfo;
import cn.uestc.pojo.Student;

import java.util.List;

public interface studentMapper {

    List<Student> selByPage(PageInfo pageInfo);

    long selCount();

}
