package DAO;

import model.Client;
import model.Retrait;

import java.util.List;

public interface RetraitDAO {
    void addRetrait(Retrait retrait);
    List<Retrait> getAllRetrait(int idInvestor);
}
