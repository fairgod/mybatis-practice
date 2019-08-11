package mapper.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @Alias 为类指定别名
 */
@Alias("emp")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Integer id;
    private String lastName;
    private String email;
    private Integer gender;
    private Integer dId;

    private Department dept;

    public Employee(String lastName, String email, Integer gender, Integer dId) {
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.dId = dId;
    }

    public Employee(String lastName, String email, Integer gender, Department dept) {
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.dept = dept;
    }


    public Employee(Integer id, String lastName, String email, Integer gender, Integer dId) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.dId = dId;
    }
}
