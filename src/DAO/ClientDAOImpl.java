package DAO;

import config.PostgresSQLConfig;
import model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements ClientDAO{
    @Override
    public void addClient(Client client) {
        String SQL_INSERT = "INSERT INTO client (fullName, email, password, phoneNumber, jobInfo, annualIncome, creditNote, birthDateField, maritalStatus, yearsInCanada) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement statement = conn.prepareStatement(SQL_INSERT)) {

            statement.setString(1, client.getFullName());
            statement.setString(2, client.getEmail());
            statement.setString(3, client.getPassword());
            statement.setString(4, client.getPhoneNumber());
            statement.setString(5, client.getJobInfo());
            statement.setDouble(6, client.getAnnualIncome());
            statement.setInt(7, client.getCreditNote());
            statement.setDate(8, Date.valueOf(client.getBirthDateField()));
            statement.setString(9, client.getMaritalStatus());
            statement.setInt(10, client.getYearsInCanada());

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("A client was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteClient(Client client) {

    }

    @Override
    public List<Client> getAllClients(Client client) {
        String SQL_LIST = "SELECT idClient, fullName, email, password from client WHERE idClient = 1";
        List <Client> listFromClients = new ArrayList<>();

        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_LIST);
             ResultSet rs = pstmt.executeQuery()){

            while (rs.next()){
                String emailDB = rs.getString("EmailDB");
                String passwordDB = rs.getString("PasswordDB");
                Client clientDB = new Client(emailDB, passwordDB);
                listFromClients.add(clientDB);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listFromClients;
    }
}
