package mapper;


import mapper.entity.Employee;
import mapper.mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.util.*;

public class TestMapper {


    @org.junit.Test
    public void testSelectAll() throws Exception {
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mapper/mybatis-config.xml"));
        SqlSession session = sessionFactory.openSession();
        try {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            List<Employee> employees = mapper.selectAll();
            for (Employee e : employees) {
                System.out.println(e);
            }
        } finally {
            session.close();
        }
    }

    @org.junit.Test
    public void testSelectOne() throws Exception {
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mapper/mybatis-config.xml"));
        SqlSession session = sessionFactory.openSession();
        try {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            Employee employee = mapper.selectEmployeeById(1);

            System.out.println(employee);

        } finally {
            session.close();
        }
    }

    /**
     *
     * Mybatis 中 #{} 和 ${} 的区别
     *
     * #{} 作为参数时：会使用 PreparedStatement 对象通过占位符的方式，来插入参数，这种方式安全，迅速，防止SQL注入。
     *      不过 #{} 只支持 jdbc 可以转义的位置。 其他地方如：SQL 中的元数据（表名、列名、排序的字段）是不支持的。
     *
     * ${} mybatis 会直接将 ${} 的值作为字符串替换到 SQL 中，
     *      可以动态的设置表名、列名、排序字段等。
     *      但是 ${} 不能作为客户输入的参数来使用，这样存在SQL注入。
     *
     *
     * #{} 作为参数输出 SQL 语句：select * from Employee where id = ? and lastName=?
     * ${} 作为参数输出 SQL 语句：select * from Employee where id = 10 and lastName=?
     *
     *
     *
     * @throws Exception
     */
    @org.junit.Test
    public void testSelectByIdAndLastName() throws Exception {
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mapper/mybatis-config.xml"));
        SqlSession session = sessionFactory.openSession();
        try {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            Employee employee = mapper.selectByIdAndLastName(10, "juzi");
            System.out.println(employee);

        } finally {
            session.close();
        }
    }


    @org.junit.Test
    public void testSelectByIds() throws Exception {
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mapper/mybatis-config.xml"));
        SqlSession session = sessionFactory.openSession();
        try {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);

            List<Employee> employees = mapper.selectByIds(Arrays.asList(10, 12, 13));
            for (Employee employee : employees) {

                System.out.println(employee);
            }

        } finally {
            session.close();
        }
    }


    @org.junit.Test
    public void testSelectByMap() throws Exception {
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mapper/mybatis-config.xml"));
        SqlSession session = sessionFactory.openSession();
        try {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            Map<String, Object> map = new HashMap<>();
            map.put("id", 10);
            map.put("lastName", "juzi");
            Employee employee = mapper.selectByMap(map);
            System.out.println(employee);

        } finally {
            session.close();
        }
    }


    @org.junit.Test
    public void testSaveEmployee() throws Exception {
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mapper/mybatis-config.xml"));
        SqlSession session = sessionFactory.openSession();
        try {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
//            boolean  flag = mapper.save(new Employee("juzi","juzi@fadou.com",1,1));
            boolean flag = mapper.save(new Employee("effy", "effy@fadou.com", 0, 2));
            session.commit();
            System.out.println(flag);

        } finally {
            session.close();
        }
    }


    /**
     * 不推荐使用，想了解的话可以看一下。
     *  对于不可以设置自增列的数据库，可以通过 selectKey 的标签设置主键的值。
     * @throws Exception
     */
    @org.junit.Test
    public void testGeneratedKeyBySelecte() throws Exception {
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mapper/mybatis-config.xml"));
        SqlSession session = sessionFactory.openSession();
        try {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
//            boolean  flag = mapper.save(new Employee("juzi","juzi@fadou.com",1,1));

            boolean flag = mapper.insertGeneratedIdBySelect(new Employee("rose", "rose@fadou.com", 0, 2));
            session.commit();
            System.out.println(flag);

        } finally {
            session.close();
        }
    }

    @Test
    public void testBatchInsert() throws Exception {
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mapper/mybatis-config.xml"));
        SqlSession session = sessionFactory.openSession();
        try {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
//            boolean  flag = mapper.save(new Employee("juzi","juzi@fadou.com",1,1));
            List<Employee> emps = new ArrayList<>();
            emps.add(new Employee("tom", "tom@fadou.com", 1, 2));
            emps.add(new Employee("marry", "marry@fadou.com", 0, 3));
            boolean flag = mapper.batchInsert(emps);
            session.commit();
            System.out.println(flag);

        } finally {
            session.close();
        }
    }


    @org.junit.Test
    public void testUpdateEmployee() throws Exception {
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mapper/mybatis-config.xml"));
        SqlSession session = sessionFactory.openSession();
        try {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            boolean flag = mapper.update(new Employee(12, "acheng", "acheng@fadou.com", 1, 1));
            session.commit();
            System.out.println(flag);

        } finally {
            session.close();
        }
    }
}
