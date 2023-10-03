package ca.sherdanc.on.elzeind.lec51_H2andJDBCTemplate.databases;
import ca.sherdanc.on.elzeind.lec51_H2andJDBCTemplate.beans.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseAccess {
    @Autowired
    protected NamedParameterJdbcTemplate jdbc;
    public void insertStudent(Student student) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("studentName", student.getName()); // Adding a named parameter
        String query = "INSERT INTO student(name) VALUES (:studentName)"; // Using the named parameter
        int rowsAffected = jdbc.update(query, namedParameters);
        if (rowsAffected > 0) System.out.println("Hard coded student inserted into database");
    }
}
