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

        try (Connection conn = connect();
             Statement statement = conn.createStatement()) {
            // Execute the SQL statement to create the table
            statement.execute(createTableSQL);
            System.out.println("Table 'financement' created or already exists.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
