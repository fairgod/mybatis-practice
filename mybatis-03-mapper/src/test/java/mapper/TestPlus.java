package mapper;

import mapper.entity.Department;
import mapper.entity.Employee;
import mapper.mapper.DepartmentMapper;
import mapper.mapper.EmployeeMapperPlus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.util.Map;

public class TestPlus {


    /**
     * 级联属性
     *
     * @throws Exception
     */
    @Test
    public void testgetEmpInfo() throws Exception {
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mapper/mybatis-config.xml"));
        SqlSession session = factory.openSession();
        try {
            EmployeeMapperPlus mapper = session.getMapper(EmployeeMapperPlus.class);
            Employee e = mapper.getById(10);
            System.out.println(e);

        } finally {
            session.close();
        }
    }


    /**
     * 分步查询 employee 的 dept 属性
     *
     * @throws Exception
     */
    @Test
    public void testgetInfoByStep() throws Exception {
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mapper/mybatis-config.xml"));
        SqlSession session = factory.openSession();
        try {
            EmployeeMapperPlus mapper = session.getMapper(EmployeeMapperPlus.class);
            Employee e = mapper.getInfoByStep(13);
            System.out.println(e.getLastName());
            System.out.println(e.getDept());
        } finally {
            session.close();
        }
    }


    /**
     * 级联属性-集合
     *
     * @throws Exception
     */
    @Test
    public void testGetDeptEmpsById() throws Exception {
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mapper/mybatis-config.xml"));
        SqlSession session = factory.openSession();
        try {
            DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
            Department department = mapper.getDeptEmpsById(7);
            System.out.println(department);
            System.out.println(department.getEmps());
        } finally {
            session.close();
        }
    }


    /**
     * 级联属性-集合 分步查询
     *
     * @throws Exception
     */
    @Test
    public void testGetDeptEmpsByStep() throws Exception {
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mapper/mybatis-config.xml"));
        SqlSession session = factory.openSession();
        try {
            DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
            Department department = mapper.getDeptEmpsByStep(6);
            System.out.println(department.getDeptName());
            System.out.println(department.getEmps());
        } finally {
            session.close();
        }
    }

    /**
     *
     * 1.将对象返回 Map 集合，将 返回值类型 设置为 map ，属性名作为：key ， 结果作为：value
     *
     * 2.将结果集 封装为 Map<Object,Employee> 格式
     *  通过 @MapKey("id") 注解来指定 哪个属性作为 Map 的key
     * @throws Exception
     */
    @Test
    public void testGetEmpMap() throws Exception{
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mapper/mybatis-config.xml"));
        SqlSession session = factory.openSession();
        try{
            EmployeeMapperPlus mapper = session.getMapper(EmployeeMapperPlus.class);
//            Map<String,Object> map=mapper.getEmpMap(12);
            Map<Object,Employee> maps=mapper.queryEmpsForMap();
            System.out.println(maps);
        }finally {
            session.close();
        }
    }
}
