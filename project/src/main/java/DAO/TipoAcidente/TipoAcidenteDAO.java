package DAO.TipoAcidente;

import DAO.DAO;
import model.Acidente;
import model.TipoAcidente;

import java.sql.SQLException;
import java.util.List;

public interface TipoAcidenteDAO extends DAO<TipoAcidente> {

    public void adicionarTipoAcidente(TipoAcidente tipoAcidente) throws SQLException;

    public TipoAcidente getTipoAcidente(int id) throws SQLException;

    public List<TipoAcidente> getTodosTipoAcidentes() throws SQLException;

}
