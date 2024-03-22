package DAO;

import config.PostgresSQLConfig;
import model.Investissement;
import model.Investor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvestissementDAOImpl implements InvestissementDAO {
    @Override
    public void addInvestissement(Investissement investissement) {
        String SQL_INSERT = "INSERT INTO investissement (  montant, bankName, numtransit, numinst, numcompte, idinvestor) VALUES ( ?, ?, ?, ?, ?, ?)";
        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement statement = conn.prepareStatement(SQL_INSERT)) {

            statement.setDouble(1, investissement.getMontant());
            statement.setString(2, investissement.getNomBanque());
            statement.setString(3, investissement.getNumTransit());
            statement.setString(4, investissement.getNumInstituition());
            statement.setString(5, investissement.getNumCompte());
            statement.setInt(5, investissement.getIdInvestor());

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("A investor was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Investissement> getAllInvestissement() {
        String SQL_LIST = "SELECT montant, bankName, numtransit, numinst, numcompte from investissement";
        List <Investissement> listFromInvestissement = new ArrayList<>();

        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_LIST);
             ResultSet rs = pstmt.executeQuery()){

            while (rs.next()){
                Investissement investissementDB = new Investissement();

                investissementDB.setMontant(rs.getDouble("montant"));
                investissementDB.setNomBanque(rs.getString("bankName"));
                investissementDB.setNumTransit(rs.getString("numtransit"));
                investissementDB.setNumInstituition(rs.getString("numinst"));
                investissementDB.setNumCompte(rs.getString("numcompte"));

                listFromInvestissement.add(investissementDB);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listFromInvestissement;
    }
}
