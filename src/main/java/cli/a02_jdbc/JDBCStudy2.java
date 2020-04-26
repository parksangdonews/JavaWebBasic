package cli.a02_jdbc;

import java.sql.*;

public class JDBCStudy2 {

    public static void main(String[] args) {
        work();
    }

    public static void work() {

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

        An object that may hold resources (such as file or socket handles)until it is closed.The close () method of
        an AutoCloseable object is called automatically when exiting a try -with - resources block for which the
        object has been declared in the resource specification header.This construction ensures prompt release, avoiding
        resource exhaustion exceptions and errors that may otherwise occur.*/

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
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                System.out.println("id: " + id + "/name: " + username + "/pw: " + password);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
