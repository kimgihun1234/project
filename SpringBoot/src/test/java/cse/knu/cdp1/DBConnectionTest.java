package cse.knu.cdp1;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class DBConnectionTest {

    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /* Please change values if your environment is different. */
    private static String url = "jdbc:mariadb://localhost:4306/coffee";
    private static String user = "root";
    private static String password = "root";

    @Test
    public void testConnection() {
        try (Connection connection = DriverManager.getConnection(
                url,
                user,
                password)) {
            log.info(connection);
            if(connection != null) {
                System.out.println("DB Connection Success.");
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}