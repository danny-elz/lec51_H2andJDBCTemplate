package ca.sherdanc.on.elzeind.lec51_H2andJDBCTemplate.beans;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class Student {
    private long id;
    private String name;
    private String lastName;
    private String Age;
    private String degreeType;
}
