package DAO;

import model.Client;
import model.Investissement;

import java.util.List;

public interface InvestissementDAO {
    void addInvestissement(Investissement investissement);
    List<Investissement> getAllInvestissement();
}
