package config.mapper;

import config.entity.Employee;

public interface EmployeeMapper {

    Employee selectEmployeeById(Integer id);
}
