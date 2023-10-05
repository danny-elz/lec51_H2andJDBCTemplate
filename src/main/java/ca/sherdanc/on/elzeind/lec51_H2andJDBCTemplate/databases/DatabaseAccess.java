package ca.sherdanc.on.elzeind.lec51_H2andJDBCTemplate.databases;
import ca.sherdanc.on.elzeind.lec51_H2andJDBCTemplate.beans.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class DatabaseAccess {
    @Autowired
    protected NamedParameterJdbcTemplate jdbc;

    public void insertStudent(Student student) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "INSERT INTO student(name, lastName, age, degreeType) VALUES (:name,:lastName, :age, :degreeType)";
        namedParameters.addValue("name", student.getName());
        namedParameters.addValue("lastName", student.getLastName());
        namedParameters.addValue("age", student.getAge());
        namedParameters.addValue("degreeType", student.getDegreeType());
        namedParameters.addValue("nameType", student.getName());

        int rowsAffected = jdbc.update(query, namedParameters);

        if (rowsAffected > 0) {
            System.out.println("Student inserted into database");
        }}
    public List<Student> getStudentList() {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM student";
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Student>(Student.class));
    }
    public List<Student> getStudentsByDegreeType(String degreeType) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM student WHERE degreeType =  :degreeType";
        namedParameters.addValue("degreeType", degreeType);
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<>(Student.class));
    }
    public List<Student> getStudentsByName(String nameInput) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM student WHERE name = :nameInput";
        namedParameters.addValue("nameInput", nameInput);
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<>(Student.class));
    }}



