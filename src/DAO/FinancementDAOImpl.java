package DAO;

import config.PostgresSQLConfig;
import model.Financement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class FinancementDAOImpl implements FinancementDAO{
    @Override
    public void addFinancement(Financement financement) {

        String SQL_INSERT = "INSERT INTO financement (vin, marque, modele, annee, montant, kilometrage, duree, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement statement = conn.prepareStatement(SQL_INSERT)) {

            statement.setString(1, financement.getVin());
            statement.setString(2, financement.getMarque());
            statement.setString(3, financement.getModele());
            statement.setInt(4, financement.getAnnee());
            statement.setDouble(5, financement.getMontant());
            statement.setInt(6, financement.getKilometrage());
            statement.setInt(7, financement.getDuree());
            statement.setString(8, financement.getType());



            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("A financement was inserted successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFinancement(Financement financement) {
        // After
    }

    @Override
    public List<Financement> getAllFinancements() {
        // After
        return null;
    }
}
