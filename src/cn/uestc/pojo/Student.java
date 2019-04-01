package cn.uestc.pojo;

/**
 * @Auther: cmz
 * @Date: 2019/4/1
 * @Description: cn.uestc.pojo
 * @version: 1.0
 */
public class Student {
    private Integer id;
    private String name;
    private Integer age;
    private Integer tid;



    //如果我们希望一个类能携带多个表查询出来的数据，那么我们应该把另外一个表的pojo类作为属性传给该类
    private Teacher teacher;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", tid=" + tid +
                ", teacher=" + teacher +
                '}';
    }



    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }
}
