package DAO.TipoOcorrencia;

import model.TipoOcorrencia;
import org.omnifaces.util.Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PgTipoOcorrenciaDAO implements TipoOcorrenciaDAO{

  private final Connection connection;

  private static final String INSERT_TIPO_OCORRENCIA =
      "INSERT INTO rodovia.tipoOcorrencia(id, descricao)" +
          "VALUES()";

  private static final String BUSCA_TODOS_TIPO_OCORRENCIA =
      "SELECT id, descricao" +
          "FROM rodovia.tipoOcorrencia" +
          "ORDER BY id";

  private static final String BUSCA_TIPO_OCORRENCIA =
      "SELECT id, descricao" +
          "FROM rodovia.tipoOcorrencia" +
          "WHERE id = ?;";

  private static final String UPDATE_TIPO_OCORRENCIA =
      "UPDATE FROM rodovia.tipoOcorrencia" +
          "SET id, descricao" +
          "WHERE id = ;";

  private static final String DELETE_TIPO_OCORRENCIA =
      "DELETE FROM rodovia.tipoOcorrencia" +
          "WHERE id = ;";

  public PgTipoOcorrenciaDAO(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void create(TipoOcorrencia tipoOcorrencia) throws SQLException {
    try (PreparedStatement statement = connection.prepareStatement(INSERT_TIPO_OCORRENCIA)) {
      statement.setInt(1, tipoOcorrencia.getIdTipoOcorrencia());
      statement.setString(2, tipoOcorrencia.getDescricaoTipoOcorrencia());
      statement.executeUpdate();
    } catch (SQLException ex) {
      Messages.addGlobalError("Erro ao inserir tipoOcorrencia");
    }
  }

  @Override
  public TipoOcorrencia read(Integer id) throws SQLException {
    return null;
  }

  @Override
  public void update(TipoOcorrencia tipoOcorrencia) throws SQLException {

  }

  @Override
  public void delete(Integer id) throws SQLException {

  }

  @Override
  public List<TipoOcorrencia> all() throws SQLException {
    return null;
  }
}
