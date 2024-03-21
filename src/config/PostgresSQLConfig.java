package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgresSQLConfig {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static Connection connect() throws SQLException {

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Method to initialize the database by creating necessary tables
    public static void initializeDatabase() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS financement (" +
                "id SERIAL PRIMARY KEY," +
                "vin VARCHAR(17)," +
                "marque VARCHAR(25)," +
                "modele VARCHAR(25)," +
                "annee INT," +
                "montant DECIMAL(10, 2)," +
                "kilometrage INT," +
                "duree INT," +
                "type VARCHAR(20)" +
                ");";
        String createTableClientSQL = "CREATE TABLE IF NOT EXISTS client (" +
                "idClient SERIAL PRIMARY KEY," +
                "fullName VARCHAR(30)," +
                "email VARCHAR(25)," +
                "password VARCHAR(1024)," +
                "phoneNumber VARCHAR(15)," +
                "jobInfo VARCHAR(25)," +
                "annualIncome DECIMAL(10, 2)," +
                "creditNote INT," +
                "birthDateField date," +
                "maritalStatus VARCHAR(15)," +
                "yearsInCanada INT" +
                ");";

        String createTableInvestorSQL = "CREATE TABLE IF NOT EXISTS investor (" +
                "idInvestor SERIAL PRIMARY KEY," +
                "fullName VARCHAR(30)," +
                "email VARCHAR(25)," +
                "password VARCHAR(1024)," +
                "phoneNumber VARCHAR(15)," +
                "bankName VARCHAR(20)," +
                "accountDetails VARCHAR(15)," +
                "riskLevel VARCHAR(15)," +
                "levelEducation VARCHAR(15)" +
                ");";

        String createTableInvestissementSQL = "CREATE TABLE IF NOT EXISTS investissement (" +
                "idInvestissement SERIAL PRIMARY KEY," +
                "idInvestor INT," +
                "numTransit VARCHAR(25)," +
                "numInst VARCHAR(10)," +
                "numCompt VARCHAR(15)," +
                "bankName VARCHAR(25)," +
                "montant DECIMAL(10,2)" +
                ");";

        String createTableRetraitSQL = "CREATE TABLE IF NOT EXISTS retrait (" +
                "idRetrait SERIAL PRIMARY KEY," +
                "idInvestor INT," +
                "montant DECIMAL(10,2)" +
                ");";


        try (Connection conn = connect();
             Statement statement = conn.createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Table 'financement' created or already exists.");
//            statement.execute("DROP TABLE client;");
            statement.execute(createTableClientSQL);
            System.out.println("Table 'client' created or already exists.");
//            statement.execute("DROP TABLE investor;");
            statement.execute(createTableInvestorSQL);
            System.out.println("Table 'Investor' created or already exists.");

            statement.execute(createTableInvestissementSQL);
            System.out.println("Table 'Investissement' created or already exists.");

            statement.execute(createTableRetraitSQL);
            System.out.println("Table 'Retrait' created or already exists.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
