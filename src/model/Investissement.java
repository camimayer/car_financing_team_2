package model;

public class Investissement {
    private int idInvestor;
    private String nomBanque;
    private String numTransit;
    private String numInstituition;
    private String numCompte;
    private double montant;


    public Investissement(int idInvestor, String nomBanque, String numTransit, String numInstituition, String numCompte, double montant) {
        this.idInvestor = idInvestor;
        this.nomBanque = nomBanque;
        this.numTransit = numTransit;
        this.numInstituition = numInstituition;
        this.numCompte = numCompte;
        this.montant = montant;
    }

    public Investissement() {

    }

    public int getIdInvestor() {
        return idInvestor;
    }

    public void setIdInvestor(int idInvestor) {
        this.idInvestor = idInvestor;
    }

    public String getNomBanque() {
        return nomBanque;
    }

    public void setNomBanque(String nomBanque) {
        this.nomBanque = nomBanque;
    }

    public String getNumTransit() {
        return numTransit;
    }

    public void setNumTransit(String numTransit) {
        this.numTransit = numTransit;
    }

    public String getNumInstituition() {
        return numInstituition;
    }

    public void setNumInstituition(String numInstituition) {
        this.numInstituition = numInstituition;
    }

    public String getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(String numCompte) {
        this.numCompte = numCompte;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
}
