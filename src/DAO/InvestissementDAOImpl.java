package DAO;

import config.PostgresSQLConfig;
import model.Investissement;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InvestissementDAOImpl implements InvestissementDAO {
    @Override
    public void addInvestissement(Investissement investissement) {
        String SQL_INSERT = "INSERT INTO investissement (montant, bankName, numtransit, numinst, numCompte, idinvestor, investissementDate, investissementType) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement statement = conn.prepareStatement(SQL_INSERT)) {

            statement.setDouble(1, investissement.getMontant());
            statement.setString(2, investissement.getNomBanque());
            statement.setString(3, investissement.getNumTransit());
            statement.setString(4, investissement.getNumInstituition());
            statement.setString(5, investissement.getNumCompte());
            statement.setInt(6, investissement.getIdInvestor());
            statement.setDate(7, Date.valueOf(investissement.getInvestissementDate()));
            statement.setString(8, investissement.getInvestissementType());

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("A investissement was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Investissement> getAllInvestissement(int idInvestor) {
        String SQL_LIST = "SELECT * FROM investissement WHERE idInvestor = ?";
        List<Investissement> listFromInvestissement = new ArrayList<>();

        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_LIST)) {

            pstmt.setInt(1, idInvestor); // Set the investorId parameter
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Investissement investissementDB = new Investissement();

                investissementDB.setIdInvestor(rs.getInt("idInvestor"));
                investissementDB.setNomBanque(rs.getString("bankName"));
                investissementDB.setNumTransit(rs.getString("numtransit"));
                investissementDB.setNumInstituition(rs.getString("numinst"));
                investissementDB.setNumCompte(rs.getString("numcompte"));
                investissementDB.setMontant(rs.getDouble("montant"));
                investissementDB.setInvestissementDate(LocalDate.parse(rs.getString("investissementDate")));
                investissementDB.setInvestissementType(rs.getString("investissementType"));
                listFromInvestissement.add(investissementDB);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listFromInvestissement;
    }

}
