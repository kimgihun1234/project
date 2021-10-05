package cse.knu.cdp1.MMS_test;


import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionTest {
    /* Please change values if your environment is different. */
    private static final String DRIVER = "org.mariadb.jdbc.Driver";
    private static final String url = "jdbc:mariadb://localhost:4306/mms";
    private static final String user = "root";
    private static final String password = "root";

    @Test
    public void testConnection() {
        try {
            Class.forName(DRIVER);
        } catch(Exception e) {
            e.printStackTrace();
        }

        try(Connection con = DriverManager.getConnection(url, user, password)){
            System.out.println(con);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}