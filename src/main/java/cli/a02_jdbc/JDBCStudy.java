package cli.a02_jdbc;

import java.sql.*;

public class JDBCStudy {

    public static void main(String[] args) {
        work();
    }

    public static void work() {

        Connection connection = null;
        Statement statement = null;

        try {

            Class.forName("org.h2.Driver");
            // jdbc url
            //String url = "jdbc:h2:~/test;DB_CLOSE_ON_EXIT=TRUE;FILE_LOCK=NO;";
            String url = "jdbc:h2:~/test";
            // connect
            connection = DriverManager.getConnection(url, "sa", "");

            connection.setAutoCommit(false);

            // statement
            statement = connection.createStatement();

            //statement.execute("create table member(id int auto_increment, username varchar(255) not null, password varchar(255) not null, primary key(id))");

            statement.executeUpdate("insert into member(username, password) values ('psd', '1234')");

            connection.commit();

            ResultSet resultSet = statement.executeQuery("select id, username, password from member");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                System.out.println("id: " + id + "/name: " + username + "/pw: " + password);
            }


        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();

            try {
                connection.rollback();
            } catch (SQLException throwables) {

                throwables.printStackTrace();

            }

        } finally {
            try {

                statement.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }


    }


}
