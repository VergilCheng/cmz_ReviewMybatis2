package cn.uestc.pojo;

/**
 * @Auther: cmz
 * @Date: 2019/4/1
 * @Description: cn.uestc.pojo
 * 用于测试单表使用resultMap标签进行查询所构建的pojo类
 * @version: 1.0
 */

/**
 * resultMap 标签
 * 1. <resultMap>标签写在 mapper.xml中,由程序员控制 SQL查询结果与
 * 实体类的映射关系.
 * 1.1 默认 MyBatis 使用 Auto Mapping 特性.
 * 2. 使用<resultMap>标签时,<select>标签不写 resultType 属性,而是使
 * 用 resultMap 属性引用<resultMap>标签.
 * 3.实体类的属性名称可以与数据库列名不相同。
 */
public class ResultMap_Teacher {
    private Integer id1;
    private String name1;

    @Override
    public String toString() {
        return "ResultMap_Teacher{" +
                "id1=" + id1 +
                ", name1='" + name1 + '\'' +
                '}';
    }

    public Integer getId1() {
        return id1;
    }

    public void setId1(Integer id1) {
        this.id1 = id1;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }
}
