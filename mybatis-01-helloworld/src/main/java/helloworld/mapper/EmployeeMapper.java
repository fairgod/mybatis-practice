package helloworld.mapper;

import helloworld.entity.Employee;

public interface EmployeeMapper {

    Employee selectEmployeeById(Integer id);
}
