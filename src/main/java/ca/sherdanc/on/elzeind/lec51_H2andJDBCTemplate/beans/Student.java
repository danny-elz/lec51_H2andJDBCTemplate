package ca.sherdanc.on.elzeind.lec51_H2andJDBCTemplate.beans;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;
@Data
@NoArgsConstructor
@Component
public class Student {
    @Id
    private long id;
    private String name;
    private String lastName;
    private String Age;
    private String degreeType;
}
