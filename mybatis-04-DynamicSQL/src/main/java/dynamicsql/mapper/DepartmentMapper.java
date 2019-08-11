package dynamicsql.mapper;

import dynamicsql.entity.Department;

public interface DepartmentMapper {
    Department getById(Integer id);

    Department getDeptEmpsById(Integer id);


    Department getDeptEmpsByStep(Integer id);
}
