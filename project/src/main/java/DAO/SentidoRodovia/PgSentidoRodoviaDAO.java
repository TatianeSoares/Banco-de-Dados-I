package DAO.SentidoRodovia;

import DAO.Rodovia.PgRodoviaDAO;
import model.Rodovia;
import model.SentidoRodovia;
import org.omnifaces.util.Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PgSentidoRodoviaDAO implements SentidoRodoviaDAO{

  private final Connection connection;

  private static final String INSERT_SENTIDO_RODOVIA =
      "INSERT INTO rodovia.sentidoRodovia(descricao) " +
          "VALUES(?)";

  private static final String BUSCA_TODOS_SENTIDO_RODOVIAS =
      "SELECT descricao " +
          "FROM rodovia.sentidoRodovia " +
          "ORDER BY descricao";

  private static final String BUSCA_SENTIDO_RODOVIA =
      "SELECT descricao " +
          "FROM rodovia.sentidoRodovia " +
          "WHERE descricao = ?;";

  private static final String UPDATE_SENTIDO_RODOVIA =
      "UPDATE FROM rodovia.sentidoRodovia " +
          "SET descricao " +
          "WHERE descricao = ?;";

  private static final String DELETE_SENTIDO_RODOVIA =
      "DELETE FROM rodovia.sentidoRodovia " +
          "WHERE descricao = ?;";

  public PgSentidoRodoviaDAO(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void create(SentidoRodovia sentidoRodovia) throws SQLException {
    try (PreparedStatement statement = connection.prepareStatement(INSERT_SENTIDO_RODOVIA)) {
      statement.setString(1, sentidoRodovia.getDescricaoSentidoRodovia());
      statement.executeUpdate();
    } catch (SQLException ex) {
      Messages.addGlobalError("Erro ao inserir sentidoRodovia");
    }
  }

  @Override
  public SentidoRodovia read(Integer id) throws SQLException {
    SentidoRodovia sentidoRodovia = new SentidoRodovia();

    try (PreparedStatement statement = connection.prepareStatement(BUSCA_SENTIDO_RODOVIA)) {
      statement.setInt(1, id);
      try (ResultSet result = statement.executeQuery()) {
        if (result.next()) {
          sentidoRodovia.setIdSentidoRodovia(result.getInt("idSentidoRodovia"));
          sentidoRodovia.setDescricaoSentidoRodovia(result.getString("descricaoSentidoRodovia"));
        } else {
          throw new SQLException("Erro ao visualizar: sentidoRodovia não pode ser encontrado.");
        }
      }
    } catch (SQLException ex) {
      Logger.getLogger(PgSentidoRodoviaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }
    return sentidoRodovia;
  }

  @Override
  public void update(SentidoRodovia sentidoRodovia) throws SQLException {

    try (PreparedStatement statement = connection.prepareStatement(UPDATE_SENTIDO_RODOVIA)) {
      statement.setInt(1, sentidoRodovia.getIdSentidoRodovia());
      statement.setString(2, sentidoRodovia.getDescricaoSentidoRodovia());

    } catch (SQLException ex) {
      Logger.getLogger(PgSentidoRodoviaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }
  }

  @Override
  public void delete(Integer id) throws SQLException {
    try (PreparedStatement statement = connection.prepareStatement(DELETE_SENTIDO_RODOVIA)) {
      statement.setInt(1, id);

    } catch (SQLException ex) {
      Logger.getLogger(PgSentidoRodoviaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }
  }

  @Override
  public List<SentidoRodovia> all() throws SQLException {
    List<SentidoRodovia> sentidoRodoviaList = new ArrayList<>();

    try (PreparedStatement statement = connection.prepareStatement(BUSCA_TODOS_SENTIDO_RODOVIAS);
         ResultSet result = statement.executeQuery()) {
      while (result.next()) {
        SentidoRodovia sentidoRodovia = new SentidoRodovia();
        sentidoRodovia.setIdSentidoRodovia(result.getInt("idSentidoRodovia"));
        sentidoRodovia.setDescricaoSentidoRodovia(result.getString("descricaoSentidoRodovia"));

        sentidoRodoviaList.add(sentidoRodovia);
      }
    } catch (SQLException ex) {
      Logger.getLogger(PgSentidoRodoviaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }
    return sentidoRodoviaList;
  }
}
