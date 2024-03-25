package DAO;

import config.PostgresSQLConfig;
import model.Investor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvestorDAOImpl implements InvestorDAO{

    @Override
    public void addInvestor(Investor investor) {
        String SQL_INSERT = "INSERT INTO investor (fullName, email, password, phoneNumber, bankName, accountDetails, riskLevel, levelEducation) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement statement = conn.prepareStatement(SQL_INSERT)) {

            statement.setString(1, investor.getFullName());
            statement.setString(2, investor.getEmail());
            statement.setString(3, investor.getPassword());
            statement.setString(4, investor.getPhoneNumber());
            statement.setString(5, investor.getBankName());
            statement.setString(6, investor.getAccountDetails());
            statement.setString(7, investor.getRiskLevel());
            statement.setString(8, investor.getLevelEducation());

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("A investor was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteInvestor(Investor investor) {

    }

    @Override
    public List<Investor> getAllInvestor() {
        String SQL_LIST = "SELECT idinvestor, fullname, email, password, phonenumber, bankname, accountdetails from investor";
        List <Investor> listFromInvestors = new ArrayList<>();

        try (Connection conn = PostgresSQLConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_LIST);
             ResultSet rs = pstmt.executeQuery()){

            while (rs.next()){
                Investor investorDB = new Investor();

                investorDB.setFullName(rs.getString("fullname"));
                investorDB.setEmail(rs.getString("email"));
                investorDB.setPassword(rs.getString("password"));
                investorDB.setIdInvestor(rs.getInt("idinvestor"));

                listFromInvestors.add(investorDB);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listFromInvestors;
    }
}
