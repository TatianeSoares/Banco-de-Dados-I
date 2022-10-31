package DAO.Acidente;

import DAO.DAO;
import model.Acidente;

import java.util.List;

public interface AcidenteDAO extends DAO<Acidente> {

    public void adicionarAcidente(Acidente acidente);

    public Acidente getAcidente(int id);

    public List<Acidente> getTodosAcidentes();

}