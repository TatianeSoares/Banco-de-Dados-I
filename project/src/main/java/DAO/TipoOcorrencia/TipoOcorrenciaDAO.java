package DAO.TipoOcorrencia;

import DAO.DAO;
import model.TipoAcidente;
import model.TipoOcorrencia;

import java.sql.SQLException;
import java.util.List;

public interface TipoOcorrenciaDAO extends DAO<TipoOcorrencia> {

    public void adicionarTipoOcorrencia(TipoOcorrencia tipoOcorrencia) throws SQLException;

    public TipoOcorrencia getTipoOcorrencia(int id) throws SQLException;

    public List<TipoOcorrencia> getTodosTipoOcorrencias() throws SQLException;
}
