package DAO;

import config.PostgresSQLConfig;
import model.Investissement;
import model.Retrait;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RetraitDAOImpl implements RetraitDAO{
    @Override
    public void addRetrait(Retrait retrait) {
        String SQL_INSERT = "INSERT INTO retrait (idinvestor, montant) VALUES (?, ?)";
        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement statement = conn.prepareStatement(SQL_INSERT)) {

            statement.setInt(1, retrait.getIdInvestor());
            statement.setDouble(2, retrait.getMontant());

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("A investor was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Retrait> getAllRetrait() {
        String SQL_LIST = "SELECT idinvestor, montant from retrait";
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
