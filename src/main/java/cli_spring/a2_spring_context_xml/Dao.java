package cli_spring.a2_spring_context_xml;

import cli_spring.a1_spring_context.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class Dao {

    public static Dao createDao() {
        log.info("Dao.createDao call..");
        return new Dao();
    }


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
                String now = LocalTime.now().toString();

                // statement.execute("delete from member where username = 'psd'");

                statement.executeUpdate("insert into member(username, password) values ('psd', '" + now + "')");

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
