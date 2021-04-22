package dev.fneira.db;

import dev.fneira.business.BookData;
import dev.fneira.business.LoginData;
import dev.fneira.business.StudentData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseActions {

    private final Connection connection;

    public DatabaseActions() {
        try {
            String URL = "jdbc:sqlite:DB-DemoQA.sqlite";
            connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw new RuntimeException("Error al generar conexión con bd SQLite");
        }
    }

    public StudentData getStudentData() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM persona limit 1");

            if (!resultSet.next()) {
                throw new RuntimeException("No se encontraron datos en la table persona");
            }

            return new StudentData()
                    .setName(resultSet.getString("name"))
                    .setLastName(resultSet.getString("last_name"))
                    .setEmail(resultSet.getString("email"))
                    .setGender(resultSet.getString("gender"))
                    .setMobile(resultSet.getString("mobile"))
                    .setDateBirth(resultSet.getString("date_birth"))
                    .setCurrentAddress(resultSet.getString("current_address"))
                    .setState(resultSet.getString("state"))
                    .setCity(resultSet.getString("city"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("Error en consulta a DB SQLite");
        }
    }

    public LoginData getLoginData() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM login limit 1");

            if (!resultSet.next()) {
                throw new RuntimeException("No se encontraron datos en la table login");
            }

            return new LoginData()
                    .setUsername(resultSet.getString("usuario"))
                    .setPassword(resultSet.getString("password"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("Error en consulta a DB SQLite");
        }
    }

    public BookData getBook(String isbn) {
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM libro WHERE isbn = ?");
            pst.setString(1, isbn);

            ResultSet resultSet = pst.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

            return new BookData()
                    .setIsbn(resultSet.getString("isbn"))
                    .setTitle(resultSet.getString("tile_book"))
                    .setSubTitle(resultSet.getString("Subtitle_book"))
                    .setAuthor(resultSet.getString("author_book"))
                    .setPublisher(resultSet.getString("publisher"))
                    .setTotalPages(resultSet.getString("total_page"))
                    .setWebsite(resultSet.getString("web_site"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("Error en consulta a DB SQLite");
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throw new RuntimeException("Error al cerrar la conexión de la db");
        }
    }

}
