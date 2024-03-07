package DAO;

import model.Financement;

import java.util.List;

public interface FinancementDAO {
    void addFinancement(Financement financement);
    void deleteFinancement(Financement financement);
    List<Financement> getAllFinancements();
}
