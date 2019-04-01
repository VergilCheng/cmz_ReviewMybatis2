package cn.uestc.pojo;

import java.util.List;

/**
 * @Auther: cmz
 * @Date: 2019/4/1
 * @Description:该类的构造类似于分页插件
 * 该类有两个作用：
 * 1.该类用来携带客户端传递过来的参数，并对其进行包装，这样就不用在mapper接口方法中
 * 写大量方法参数了，只需要传递类就可以了。
 * 2.用来对数据库的查询结果进行封装返回给前端显示
 * @version: 1.0
 */
public class PageInfo {

    private int pageSize;
    private int pageNumber;
    private Long total;
    private List<?> list;
    //学生姓名
    private String sname;
    //老师姓名
    private String tname;
    //方便mysql分页的pageStart：起始行
    private int pageStart;


    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }
}
