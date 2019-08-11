package dynamicsql.mapper;

import dynamicsql.entity.Employee;

import java.util.List;

public interface DynamicSQLMapper {

    List<Employee> queryEmpsByCondition(Employee employee);

    List<Employee> queryEmpsByConditionTrim(Employee employee);

    boolean updateUseSet(Employee employee);

    List<Employee> queryEmpsByConditionChoose(Employee employee);
}
