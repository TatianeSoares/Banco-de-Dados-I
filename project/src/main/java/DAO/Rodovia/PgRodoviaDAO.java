package DAO.Rodovia;

import model.Rodovia;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PgRodoviaDAO implements RodoviaDAO {

  private final Connection connection;
  //TODO talvez tenhamos de mudar no bd os nomes dos id
  private static final String INSERT_RODOVIA =
      "INSERT INTO rodovia.rodovias(id, descricao)" +
          "VALUES()";

  private static final String BUSCA_TODAS_RODOVIAS =
      "SELECT id, descricao" +
          "FROM rodovia.rodovias" +
          "ORDER BY id";

  private static final String BUSCA_RODOVIA =
      "SELECT id, descricao" +
          "FROM rodovia.rodovias" +
          "WHERE id = ?;";

  private static final String UPDATE_RODOVIA =
      "UPDATE FROM rodovia.rodovias" +
          "SET id, descricao" +
          "WHERE id = ;";

  private static final String DELETE_RODOVIA =
      "DELETE FROM rodovia.rodovias" +
      "WHERE id = ;";

  public PgRodoviaDAO(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void create(Rodovia rodovia) throws SQLException {

  }

  @Override
  public Rodovia read(Integer id) throws SQLException {
    return null;
  }

  @Override
  public void update(Rodovia rodovia) throws SQLException {

  }

  @Override
  public void delete(Integer id) throws SQLException {

  }

  @Override
  public List<Rodovia> all() throws SQLException {
    return null;
  }
}
