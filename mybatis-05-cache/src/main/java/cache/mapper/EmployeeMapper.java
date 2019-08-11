package cache.mapper;

import cache.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {

    /**
     * mybatis 中一个参数的方法，可以不用 @Param 注解指定参数的名称
     *
     * @param id
     * @return
     */
    Employee selectEmployeeById(Integer id);


    /**
     * mybatis 方法中有多个参数的话，需要明确指定参数的名称
     *
     * @param id
     * @param lastName
     * @return
     */
    Employee selectByIdAndLastName(@Param("id") Integer id, @Param("name") String lastName);


    Employee selectByMap(Map<String, Object> map);


    List<Employee> selectByIds(List<Integer> ids);


    List<Employee> selectAll();

    boolean save(Employee employee);

    boolean update(Employee employee);


    boolean insertGeneratedIdBySelect(Employee employeee);
    /**
     * @param emps 设置 mybatis 中使用的参数名称为：emps
     * @return
     */
    boolean batchInsert(@Param("emps") List<Employee> emps);
}
