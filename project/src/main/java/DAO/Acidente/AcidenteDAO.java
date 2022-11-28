package DAO.Acidente;

import DAO.DAO;
import model.Acidente;

public interface AcidenteDAO extends DAO<Acidente> {

    public boolean verificarInAcidente(Acidente acidente);

}