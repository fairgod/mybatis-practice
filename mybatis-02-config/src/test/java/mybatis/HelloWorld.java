package mybatis;

import config.entity.Employee;
import config.mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

public class HelloWorld {

    /**
     * 使用 config 配置文件创建 SqlSessionFactory
     * 使用 mapper.xml 的 namespace 下的 某个方法 id  加载具体的方法
     *
     * @throws Exception
     */
    @Test
    public void test02() throws Exception {
        String configPath = "config/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(configPath);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = factory.openSession();

        try {
            // EmployeeMapper.selectEmployeeById 这个是 mapper 的 namespace（EmployeeMapper） 下的 标签 Id （selectEmployeeById）
            Employee employee = session.selectOne("config.mapper.EmployeeMapper.selectEmployeeById", 1);

            System.out.println(employee.toString());
        } finally {

            session.close();
        }
    }

    /**
     * 使用 Mapper 接口的方式，来创建 Mapper 对象
     * Mapper.xml 使用 namespace 指定 需要绑定的 Mapper接口
     * Mapper.xml 标记的每个方法的Id 必须和 Mapper 接口的 Id 相同 ，否则无法映射
     * @throws Exception
     */
    @Test
    public void test01() throws Exception {
        //获取 SessionFactory 对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("config/mybatis-config.xml"));
        //获取 SqlSession 对象 非线程安全的, 每次使用一定要新创建，用完一定要关闭
        SqlSession sqlSession = factory.openSession();
        try{
            // 为接口创建一个代理对象，使用代理对象操作 CRUD
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            System.out.println(mapper);
            Employee employee = mapper.selectEmployeeById(2);
            System.out.println(employee);
        }finally {
            sqlSession.close();
        }

    }
}
