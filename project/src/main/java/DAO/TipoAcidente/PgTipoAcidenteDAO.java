package DAO.TipoAcidente;

import model.TipoAcidente;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PgTipoAcidenteDAO implements TipoAcidenteDAO{

  private final Connection connection;

  private static final String INSERT_TIPO_ACIDENTE =
      "INSERT INTO rodovia.tipoAcidente(id, descricao)" +
          "VALUES()";

  private static final String BUSCA_TODOS_TIPOSACIDENTES =
      "SELECT id, descricao" +
          "FROM rodovia.tipoAcidente" +
          "ORDER BY id";

  private static final String BUSCA_TIPO_ACIDENTE =
      "SELECT id, descricao" +
          "FROM rodovia.tipoAcidente" +
          "WHERE id = ?;";

  private static final String UPDATE_TIPO_ACIDENTE =
      "UPDATE FROM rodovia.tipoAcidente" +
          "SET id, descricao" +
          "WHERE id = ;";

  private static final String DELETE_TIPO_ACIDENTE =
      "DELETE FROM rodovia.tipoAcidente" +
          "WHERE id = ;";

  public PgTipoAcidenteDAO(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void create(TipoAcidente tipoAcidente) throws SQLException {

  }

  @Override
  public TipoAcidente read(Integer id) throws SQLException {
    return null;
  }

  @Override
  public void update(TipoAcidente tipoAcidente) throws SQLException {

  }

  @Override
  public void delete(Integer id) throws SQLException {

  }

  @Override
  public List<TipoAcidente> all() throws SQLException {
    return null;
  }
}
