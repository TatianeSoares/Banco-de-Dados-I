package DAO.TipoAcidente;

import DAO.Rodovia.PgRodoviaDAO;
import model.Acidente;
import model.Rodovia;
import model.TipoAcidente;
import org.omnifaces.util.Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PgTipoAcidenteDAO implements TipoAcidenteDAO{

  private final Connection connection;

  private static final String INSERT_TIPO_ACIDENTE =
      "INSERT INTO rodovia.tipoAcidente(descricao) " +
          "VALUES(?)";

  private static final String BUSCA_TODOS_TIPOSACIDENTES =
      "SELECT descricao " +
          "FROM rodovia.tipoAcidente " +
          "ORDER BY descricao";

  private static final String BUSCA_TIPO_ACIDENTE =
      "SELECT descricao " +
          "FROM rodovia.tipoAcidente " +
          "WHERE descricao = ?;";

  private static final String UPDATE_TIPO_ACIDENTE =
      "UPDATE rodovia.tipoAcidente " +
          "SET descricao " +
          "WHERE descricao = ?;";

  private static final String DELETE_TIPO_ACIDENTE =
      "DELETE FROM rodovia.tipoAcidente " +
          "WHERE descricao = ?;";

  public PgTipoAcidenteDAO(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void create(TipoAcidente tipoAcidente) throws SQLException {
    try (PreparedStatement statement = connection.prepareStatement(INSERT_TIPO_ACIDENTE)) {
      statement.setString(1, tipoAcidente.getDescricaoTipoAcidente());
      statement.executeUpdate();
    } catch (SQLException ex) {
      Messages.addGlobalError("Erro ao inserir tipoAcidente");
    }
  }

  @Override
  public TipoAcidente read(Integer id) throws SQLException {
    TipoAcidente tipoAcidente = new TipoAcidente();

    try (PreparedStatement statement = connection.prepareStatement(BUSCA_TIPO_ACIDENTE)) {
      statement.setInt(1, id);
      try (ResultSet result = statement.executeQuery()) {
        if (result.next()) {
          tipoAcidente.setIdTipoAcidente(result.getInt("idTipoAcidente"));
          tipoAcidente.setDescricaoTipoAcidente(result.getString("descricaoTipoAcidente"));
        } else {
          throw new SQLException("Erro ao visualizar: tipoAcidente não pode ser encontrado.");
        }
      }
    } catch (SQLException ex) {
      Logger.getLogger(PgTipoAcidenteDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }
    return tipoAcidente;
  }

  @Override
  public void update(TipoAcidente tipoAcidente) throws SQLException {
    try (PreparedStatement statement = connection.prepareStatement(UPDATE_TIPO_ACIDENTE)) {
      statement.setInt(1, tipoAcidente.getIdTipoAcidente());
      statement.setString(2, tipoAcidente.getDescricaoTipoAcidente());

    } catch (SQLException ex) {
      Logger.getLogger(PgTipoAcidenteDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }
  }

  @Override
  public void delete(Integer id) throws SQLException {
    try (PreparedStatement statement = connection.prepareStatement(DELETE_TIPO_ACIDENTE)) {
      statement.setInt(1, id);

    } catch (SQLException ex) {
      Logger.getLogger(PgTipoAcidenteDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }
  }

  @Override
  public List<TipoAcidente> all() throws SQLException {
    List<TipoAcidente> tipoAcidenteList = new ArrayList<>();

    try (PreparedStatement statement = connection.prepareStatement(BUSCA_TODOS_TIPOSACIDENTES);
         ResultSet result = statement.executeQuery()) {
      while (result.next()) {
        TipoAcidente tipoAcidente = new TipoAcidente();
        tipoAcidente.setIdTipoAcidente(result.getInt("idTipoAcidente"));
        tipoAcidente.setDescricaoTipoAcidente(result.getString("descricaoTipoAcidente"));

        tipoAcidenteList.add(tipoAcidente);
      }
    } catch (SQLException ex) {
      Logger.getLogger(PgTipoAcidenteDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }
    return tipoAcidenteList;
  }
}
