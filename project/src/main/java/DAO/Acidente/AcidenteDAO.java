package DAO.Acidente;

import DAO.DAO;
import model.Acidente;

import java.sql.SQLException;
import java.util.List;

public interface AcidenteDAO extends DAO<Acidente> {

    public void adicionarAcidente(Acidente acidente) throws SQLException;

    public Acidente getAcidente(int id) throws SQLException;

    public List<Acidente> getTodosAcidentes() throws SQLException;

    public boolean verificarInAcidente(Acidente acidente);

}