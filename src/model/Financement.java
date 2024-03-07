package model;

public class Financement {
    private String vin;
    private String marque;
    private String modele;
    private int annee;
    private double montant;
    private int kilometrage;

    private int duree;
    private String type;

    public Financement(String vin, String marque, String modele, int annee, double montant, int kilometrage, int duree, String type) {
        this.vin = vin;
        this.marque = marque;
        this.modele = modele;
        this.annee = annee;
        this.montant = montant;
        this.kilometrage = kilometrage;
        this.duree = duree;
        this.type = type;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(int kilometrage) {
        this.kilometrage = kilometrage;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
