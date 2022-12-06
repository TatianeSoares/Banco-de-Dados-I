package DAO.Acidente;

import DAO.DAO;
import model.Acidente;

import java.sql.SQLException;
import java.util.List;

public interface AcidenteDAO extends DAO<Acidente> {

    public boolean verificarInAcidente(Acidente acidente);

    public List<Integer> getAcidentesFataisProximos1KmCount();

}