package cli_spring.a1_spring_context;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class Dao {

    public void run() {

        // jdbc url
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //String url = "jdbc:h2:~/test;DB_CLOSE_ON_EXIT=TRUE;FILE_LOCK=NO;";
        String url = "jdbc:h2:~/test";

        /*
        Auto Closable..
        */

        try (Connection connection = DriverManager.getConnection(url, "sa", "");
             Statement statement = connection.createStatement();
        ) {

            connection.setAutoCommit(false);

            //statement.execute("create table member(id int auto_increment, username varchar(255) not null, password varchar(255) not null, primary key(id))");

            try {
                statement.executeUpdate("insert into member(username, password) values ('psd', '1234')");
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
            }

            ResultSet resultSet = statement.executeQuery("select id, username, password from member");

            while (resultSet.next()) {
                // Result Set
                Member member = new Member(resultSet);
                log.info(member.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
