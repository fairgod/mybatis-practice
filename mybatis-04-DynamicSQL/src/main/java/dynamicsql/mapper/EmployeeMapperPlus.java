package dynamicsql.mapper;

import dynamicsql.entity.Employee;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface EmployeeMapperPlus {

    Employee getById(Integer id);

    List<Employee> queryEmpsByDeptId(Integer id);

    Employee getInfoByStep(Integer id);

    Map<String,Object> getEmpMap(Integer id);

    /**
     * @MapKey("id") 当结果集为 Map<Object,Employee> 格式，得指定 key 是哪个属性
     *
     * @return
     */
    @MapKey("lastName")
    Map<Object,Employee> queryEmpsForMap();
}
