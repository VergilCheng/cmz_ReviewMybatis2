package cn.uestc.pojo;

import java.util.List;

/**
 * @Auther: cmz
 * @Date: 2019/4/1
 * @Description: cn.uestc.pojo
 * @version: 1.0
 */
public class Teacher {

    private Integer id;
    private String name;
    private List<Student> list;

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", list=" + list +
                '}';
    }

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
