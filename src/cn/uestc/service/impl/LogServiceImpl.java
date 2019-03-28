package cn.uestc.service.impl;

import cn.uestc.mapper.logMapper;
import cn.uestc.pojo.Log;
import cn.uestc.service.LogService;
import cn.uestc.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * @Auther: cmz
 * @Date: 2019/3/27
 * @Description: cn.uestc.service.impl
 * 简化了原来的代码
 * @version: 1.0
 */
public class LogServiceImpl implements LogService {
    @Override
    public int insert(Log log) {
        SqlSession session = MybatisUtil.getSession();
        logMapper mapper = session.getMapper(logMapper.class);
        int i = mapper.insert(log);
        return i;
    }
}
