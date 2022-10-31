package DAO.SentidoRodovia;

import model.SentidoRodovia;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PgSentidoRodoviaDAO implements SentidoRodoviaDAO{

  private final Connection connection;

  private static final String INSERT_SENTIDO_RODOVIA =
      "INSERT INTO rodovia.sentidoRodovia(id, descricao)" +
          "VALUES()";

  private static final String BUSCA_TODOS_SENTIDO_RODOVIAS =
      "SELECT id, descricao" +
          "FROM rodovia.sentidoRodovia" +
          "ORDER BY id";

  private static final String BUSCA_SENTIDO_RODOVIA =
      "SELECT id, descricao" +
          "FROM rodovia.sentidoRodovia" +
          "WHERE id = ?;";

  private static final String UPDATE_SENTIDO_RODOVIA =
      "UPDATE FROM rodovia.sentidoRodovia" +
          "SET id, descricao" +
          "WHERE id = ;";

  private static final String DELETE_SENTIDO_RODOVIA =
      "DELETE FROM rodovia.sentidoRodovia" +
          "WHERE id = ;";

  public PgSentidoRodoviaDAO(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void create(SentidoRodovia sentidoRodovia) throws SQLException {

  }

  @Override
  public SentidoRodovia read(Integer id) throws SQLException {
    return null;
  }

  @Override
  public void update(SentidoRodovia sentidoRodovia) throws SQLException {

  }

  @Override
  public void delete(Integer id) throws SQLException {

  }

  @Override
  public List<SentidoRodovia> all() throws SQLException {
    return null;
  }
}
