package DynamicSQL;

import dynamicsql.entity.Employee;
import dynamicsql.mapper.DynamicSQLMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.util.List;

public class TestDynamicSQL {
    /**
     * 测试动态 sql
     * @throws Exception
     */
    @Test
    public void TestQueryEmpsByCondition() throws Exception{
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("dynamicsql/mybatis-config.xml"));
        SqlSession session=factory.openSession();
        try {
            DynamicSQLMapper mapper = session.getMapper(DynamicSQLMapper.class);
//            List<Employee> employees = mapper.queryEmpsByCondition(new Employee(null, "%a%", null, null, null));
//            List<Employee> employees = mapper.queryEmpsByConditionTrim(new Employee(null, "%a%", null, null, null));
//            Boolean flag= mapper.updateUseSet(new Employee(10, "aju", null, null, null));
            List<Employee> list= mapper.queryEmpsByConditionChoose(new Employee(null, null, null, null, null));
            System.out.println(list);
        }finally {
            session.commit();
            session.close();
        }
    }
}
