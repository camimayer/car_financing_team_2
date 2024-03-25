package DAO;

import config.PostgresSQLConfig;
import model.Retrait;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RetraitDAOImpl implements RetraitDAO{
    @Override
    public void addRetrait(Retrait retrait) {
        String SQL_INSERT = "INSERT INTO investissement (montant, bankName, numtransit, numinst, numCompte, idinvestor, investissementDate, investissementType) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement statement = conn.prepareStatement(SQL_INSERT)) {

            statement.setDouble(1, retrait.getMontant());
            statement.setString(2, retrait.getNomBanque());
            statement.setString(3, retrait.getNumTransit());
            statement.setString(4, retrait.getNumInstituition());
            statement.setString(5, retrait.getNumCompte());
            statement.setInt(6, retrait.getIdInvestor());
            statement.setDate(7, Date.valueOf(retrait.getInvestissementDate()));
            statement.setString(8, retrait.getInvestissementType());

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("A retrait was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Retrait> getAllRetrait(int idInvestor) {
        String SQL_LIST = "SELECT * from retrait";
        List <Retrait> listFromRetrait = new ArrayList<>();

        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_LIST);
             ResultSet rs = pstmt.executeQuery()){

            while (rs.next()){
                Retrait retraitDB = new Retrait();

                retraitDB.setMontant(rs.getDouble("montant"));
                retraitDB.setIdInvestor(rs.getInt("idinvestor"));

                listFromRetrait.add(retraitDB);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listFromRetrait;
    }
}
