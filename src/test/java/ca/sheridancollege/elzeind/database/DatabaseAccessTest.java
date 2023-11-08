package ca.sheridancollege.elzeind.database;

import ca.sherdanc.on.elzeind.lec51_H2andJDBCTemplate.databases.DatabaseAccess;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@SpringBootTest
@AutoConfigureTestDatabase

public class DatabaseAccessTest {
    @Autowired
    private DatabaseAccess da;

    @Test
    public void testGetStudentList() {
        

    }






}
