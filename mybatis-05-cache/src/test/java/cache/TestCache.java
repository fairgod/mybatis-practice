package cache;

import cache.entity.Employee;
import cache.mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.util.List;

public class TestCache {

    /**
     * Mybatis 默认开启一级缓存（会话缓存 SqlSession 缓存），一级缓存是一直开启的
     *     仅在本次会话会保存当前缓存的数据。当会话关闭时，缓存则被清空
     *  一级缓存失效的情况
     *      1：SqlSession 不同。
     *      2：SqlSession 相同，中间执行 insert / update / delete ， 缓存会被清空。
     *      3：SqlSession 相同，手动清空缓存。
     *  二级缓存：（全局缓存）：基于 namespace 级别的缓存。一个 namespace 对应一个二级缓存。
     *      工作机制：
     *          1、一个会话，查询一个数据，会将这个数据放到当前会话的一级缓存中。
     *          2、当会话关闭时，一级缓存的数据会保存在二级缓存中；新的会话查询信息，就可以参照二级缓存
     *          3、SqlSession ===== EmployeeMapper ===> Employee
     *                        ===== DepartmentMapper ===> Department
     *               不同 namespace 查询的数据会放到自己对应的缓存中（mybatis中的缓存，其实就是一个map）
     *       使用：
     *          1、在全局配置文件中开启二级缓存的配置：<setting name="cacheEnabled" value="true" />
     *          2、在 xxxMapper.xml 中配置二级缓存 <cache></cache>
     *          3、POJO 需要实现 Serializable 接口
     *
     *
     *
     *  缓存属性的相关配置：
     *      全局配置中的 <setting name="cacheEnabled" value="false" /> 不使用二级缓存。不会影响一级缓存
     *      select 标签中 useCache="true" 当为 false 的时候，不使用二级缓存，不会影响一级缓存
     *      select 标签中 flushCache="false" 当为 true 时，一级和二级缓存都会被清空
     *      SqlSession 的 clearCache()方法只会清空一级缓存，不会影响二级缓存。
     *      全局配置中的：localCacheScope：一级缓存作用域  SESSION（一级缓存共享数据） | STATEMENT （可以禁用一级缓存）
     *
     * @throws Exception
     */
    @Test
    public void testCache() throws Exception{
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("cache/mybaits-config.xml"));
        SqlSession session=factory.openSession();
        try{
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            List<Employee> employees = mapper.selectAll();
//
            session.clearCache();
//
            mapper.selectAll();

            session.close();

            SqlSession session1=factory.openSession();
            EmployeeMapper mapper1= session1.getMapper(EmployeeMapper.class);

            List<Employee> emps = mapper1.selectAll();
            session1.close();
        }finally{
        }
    }
}
