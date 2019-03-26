package cn.uestc.mapper;

import cn.uestc.pojo.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface logMapper {
    List<Log> selectAllLogs();

    /**
     * 使用@Param注解可以实现在mapper.xml文件中#{}括号中不需要写索引或者param1等，
     * 其中@Param注解中的值需要与#{}括号中的值相同，与接口方法的参数名没关系，可以相同也可以不同。
     *
     * Param注解底层其实使用了map，将注解中的字符串设置为key，接口方法参数值为value，put进入map中，
     * 并在mapper.xml文件的sql中查找与key相同的#{}，并进行参数注入。
     *
     *
     * @param acconOut
     * @param acconIn
     * @param id
     * @return
     */
    Log selectByAccInAccoutId(@Param("accout") String acconOut, @Param("accin") String acconIn, @Param("id") Integer id);

    List<Log> selectByAccInAccout(@Param("accout") String acconOut, @Param("accin") String acconIn);
    List<Log> selectByIds(@Param("ids")Integer... ids);

    int updateLog(Log log);

}
