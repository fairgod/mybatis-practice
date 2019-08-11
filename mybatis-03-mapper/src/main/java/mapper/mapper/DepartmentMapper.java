package mapper.mapper;

import mapper.entity.Department;

public interface DepartmentMapper {
    Department getById(Integer id);

    Department getDeptEmpsById(Integer id);


    Department getDeptEmpsByStep(Integer id);
}
