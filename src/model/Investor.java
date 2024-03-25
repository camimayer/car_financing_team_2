package model;

public class Investor extends User{
    private String bankName;
    private String accountDetails;
    private String riskLevel;
    private String levelEducation;

    public int getIdInvestor() {
        return idInvestor;
    }

    public void setIdInvestor(int idInvestor) {
        this.idInvestor = idInvestor;
    }

    private int idInvestor;



    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountDetails() {
        return accountDetails;
    }

    public void setAccountDetails(String accountDetails) {
        this.accountDetails = accountDetails;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getLevelEducation() {
        return levelEducation;
    }

    public void setLevelEducation(String levelEducation) {
        this.levelEducation = levelEducation;
    }

    @Override
    public String toString() {
        return "Investor{" +
                ", fullName='" + getFullName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phoneNumber=" + getPhoneNumber() +
                ", bankName='" + bankName + '\'' +
                ", accountDetails='" + accountDetails + '\'' +
                ", riskLevel='" + riskLevel + '\'' +
                ", levelEducation='" + levelEducation + '\'' +
                '}';
    }
}
