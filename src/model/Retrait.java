package model;

public class Retrait {
    private int idInvestor;
    private double montant;

    public Retrait(int idInvestor, double montant) {
        this.idInvestor = idInvestor;
        this.montant = montant;
    }

    public Retrait() {

    }

    public int getIdInvestor() {
        return idInvestor;
    }

    public void setIdInvestor(int idInvestor) {
        this.idInvestor = idInvestor;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
}
