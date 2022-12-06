package DAO.Relatorio;

import DAO.DAO;
import model.Relatorio;

import java.sql.SQLException;
import java.util.List;

public interface RelatorioDAO extends DAO<Relatorio> {

  public List<Relatorio> buscaAcidentePorTipo(String tipoOcorrencia) throws SQLException;
}