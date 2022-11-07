package DAO.TipoOcorrencia;

import DAO.Rodovia.PgRodoviaDAO;
import model.Acidente;
import model.Rodovia;
import model.TipoAcidente;
import model.TipoOcorrencia;
import org.omnifaces.util.Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
  public void adicionarTipoOcorrencia(TipoOcorrencia tipoOcorrencia) throws SQLException {
    create(tipoOcorrencia);
  }

  @Override
  public TipoOcorrencia getTipoOcorrencia(int id) throws SQLException {
    return read(id);
  }

  @Override
  public List<TipoOcorrencia> getTodosTipoOcorrencias() throws SQLException {
    return all();
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
    TipoOcorrencia tipoOcorrencia = new TipoOcorrencia();

    try (PreparedStatement statement = connection.prepareStatement(BUSCA_TIPO_OCORRENCIA)) {
      statement.setInt(1, id);
      try (ResultSet result = statement.executeQuery()) {
        if (result.next()) {
          tipoOcorrencia.setIdTipoOcorrencia(result.getInt("idTipoOcorrencia"));
          tipoOcorrencia.setDescricaoTipoOcorrencia(result.getString("descricaoTipoOcorrencia"));
        } else {
          throw new SQLException("Erro ao visualizar: tipoOcorrencia não pode ser encontrado.");
        }
      }
    } catch (SQLException ex) {
      Logger.getLogger(PgTipoOcorrenciaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }
    return tipoOcorrencia;
  }

  @Override
  public void update(TipoOcorrencia tipoOcorrencia) throws SQLException {
    try (PreparedStatement statement = connection.prepareStatement(UPDATE_TIPO_OCORRENCIA)) {
      statement.setInt(1, tipoOcorrencia.getIdTipoOcorrencia());
      statement.setString(2, tipoOcorrencia.getDescricaoTipoOcorrencia());

    } catch (SQLException ex) {
      Logger.getLogger(PgTipoOcorrenciaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }
  }

  @Override
  public void delete(Integer id) throws SQLException {
    try (PreparedStatement statement = connection.prepareStatement(DELETE_TIPO_OCORRENCIA)) {
      statement.setInt(1, id);

    } catch (SQLException ex) {
      Logger.getLogger(PgTipoOcorrenciaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }
  }

  @Override
  public List<TipoOcorrencia> all() throws SQLException {
    List<TipoOcorrencia> tipoOcorrenciaList = new ArrayList<>();

    try (PreparedStatement statement = connection.prepareStatement(BUSCA_TODOS_TIPO_OCORRENCIA);
         ResultSet result = statement.executeQuery()) {
      while (result.next()) {
        TipoOcorrencia tipoOcorrencia = new TipoOcorrencia();
        tipoOcorrencia.setIdTipoOcorrencia(result.getInt("idTipoOcorrencia"));
        tipoOcorrencia.setDescricaoTipoOcorrencia(result.getString("descricaoTipoOcorrencia"));

        tipoOcorrenciaList.add(tipoOcorrencia);
      }
    } catch (SQLException ex) {
      Logger.getLogger(PgTipoOcorrenciaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }
    return tipoOcorrenciaList;
  }
}
