package cn.uestc.service;

import cn.uestc.pojo.PageInfo;

/**
 * @Auther: cmz
 * @Date: 2019/4/1
 * @Description: cn.uestc.service
 * @version: 1.0
 */
public interface StudentService {

    PageInfo showPage(String sname, String tname, String pageSize, String pageNumber);

}
