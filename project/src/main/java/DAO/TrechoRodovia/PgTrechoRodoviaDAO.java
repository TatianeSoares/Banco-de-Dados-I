package DAO.TrechoRodovia;

import model.TrechoRodovia;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PgTrechoRodoviaDAO implements TrechoRodoviaDAO{

  private final Connection connection;

  private static final String INSERT_TRECHO_RODOVIA =
      "INSERT INTO rodovia.trechoRodovia(id, descricao)" +
          "VALUES()";

  private static final String BUSCA_TODOS_TRECHO_RODOVIA =
      "SELECT id, descricao" +
          "FROM rodovia.trechoRodovia" +
          "ORDER BY id";

  private static final String BUSCA_TRECHO_RODOVIA =
      "SELECT id, descricao" +
          "FROM rodovia.trechoRodovia" +
          "WHERE id = ?;";

  private static final String UPDATE_TRECHO_RODOVIA =
      "UPDATE FROM rodovia.trechoRodovia" +
          "SET id, descricao" +
          "WHERE id = ;";

  private static final String DELETE_TRECHO_RODOVIA =
      "DELETE FROM rodovia.trechoRodovia" +
          "WHERE id = ;";

  public PgTrechoRodoviaDAO(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void create(TrechoRodovia trechoRodovia) throws SQLException {

  }

  @Override
  public TrechoRodovia read(Integer id) throws SQLException {
    return null;
  }

  @Override
  public void update(TrechoRodovia trechoRodovia) throws SQLException {

  }

  @Override
  public void delete(Integer id) throws SQLException {

  }

  @Override
  public List<TrechoRodovia> all() throws SQLException {
    return null;
  }
}
