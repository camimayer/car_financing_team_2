package DAO;

import config.PostgresSQLConfig;
import model.Client;
import model.Financement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        String SQL_LIST = "SELECT * from financement";
        List <Financement> listFromFinancements = new ArrayList<>();

        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_LIST);
             ResultSet rs = pstmt.executeQuery()){

            while (rs.next()){
                String vinDB = rs.getString("vin");
                String marqueDB = rs.getString("marque");
                String modeleDB = rs.getString("modele");
                Integer anneeDB = rs.getInt("annee");
                Double montantDB = rs.getDouble("montant");
                Integer kilometrageDB = rs.getInt("kilometrage");
                Integer dureeDB = rs.getInt("duree");
                String typeDB = rs.getString("type");
                Financement financementDB = new Financement(vinDB, marqueDB, modeleDB, anneeDB, montantDB, kilometrageDB, dureeDB, typeDB);
                listFromFinancements.add(financementDB);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listFromFinancements;
    }
}
