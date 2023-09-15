package org.example;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        DataSource dataSource = createDataSource();
//        DriverManager.getConnection("jdbc:h2:mem:;INIT=RUNSCRIPT FROM 'classpath:users.sql'")
        try (Connection connection = dataSource.getConnection()) {

            System.out.println("connection.isValid(0) = " + connection.isValid(0));

//            // CRUD
//
//            // select
//            PreparedStatement ps = connection.prepareStatement("select * from USERS where name = ?");
//            ps.setString(1, "Macro");
//
//            ResultSet resultSet = ps.executeQuery();
//            while (resultSet.next()){
//                System.out.println(resultSet.getInt("id" + " - " + resultSet.getString("name")));
//            }

            try (Statement statement = connection.createStatement()) {
                // Execute the SQL script
                statement.execute("CREATE TABLE IF NOT EXISTS USERS (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255) NOT NULL)");
                statement.execute("INSERT INTO USERS (name) VALUES ('Macro')");

                try (ResultSet resultSet = statement.executeQuery("SELECT id, name FROM USERS")) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        System.out.println("id: " + id + ", name: " + name);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    private static DataSource createDataSource() {
//        hikariCP
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:h2:mem:;INIT=RUNSCRIPT FROM 'classpath:users.sql'");
        return ds;
    }
}