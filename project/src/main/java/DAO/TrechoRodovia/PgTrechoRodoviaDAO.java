package DAO.TrechoRodovia;

import DAO.TrechoRodovia.PgTrechoRodoviaDAO;
import model.TrechoRodovia;
import model.TrechoRodovia;
import org.omnifaces.util.Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PgTrechoRodoviaDAO implements TrechoRodoviaDAO{

  private final Connection connection;

  private static final String INSERT_TRECHO_RODOVIA =
      "INSERT INTO rodovia.trechoRodovia(descricao, idRodovia) " +
          "VALUES(?, ?)";

  private static final String BUSCA_TODOS_TRECHO_RODOVIA =
      "SELECT descricao, idRodovia " +
          "FROM rodovia.trechoRodovia " +
          "ORDER BY descricao";

  private static final String BUSCA_TRECHO_RODOVIA =
      "SELECT descricao, idRodovia " +
          "FROM rodovia.trechoRodovia " +
          "WHERE descricao = ?;";

  private static final String UPDATE_TRECHO_RODOVIA =
      "UPDATE FROM rodovia.trechoRodovia " +
          "SET descricao, idRodovia " +
          "WHERE descricao = ?;";

  private static final String DELETE_TRECHO_RODOVIA =
      "DELETE FROM rodovia.trechoRodovia " +
          "WHERE descricao = ?;";

  public PgTrechoRodoviaDAO(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void create(TrechoRodovia trechoRodovia) throws SQLException {
    try (PreparedStatement statement = connection.prepareStatement(INSERT_TRECHO_RODOVIA)) {
      statement.setString(1, trechoRodovia.getDescricaoTrechoRodovia());
      statement.setString(2, trechoRodovia.getIdRodovia());
      statement.executeUpdate();
    } catch (SQLException ex) {
      Messages.addGlobalError("Erro ao inserir trechoRodovia");
    }
  }

  @Override
  public TrechoRodovia read(String id) throws SQLException {
    TrechoRodovia trechoRodovia = new TrechoRodovia();

    try (PreparedStatement statement = connection.prepareStatement(BUSCA_TRECHO_RODOVIA)) {
      statement.setString(1, id);
      try (ResultSet result = statement.executeQuery()) {
        if (result.next()) {
          trechoRodovia.setDescricaoTrechoRodovia(result.getString("Descricao"));
          trechoRodovia.setIdRodovia(result.getString("idRodovia"));
        } else {
          trechoRodovia = null;
        }
      }
    } catch (SQLException ex) {
      Logger.getLogger(PgTrechoRodoviaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }
    return trechoRodovia;
  }

  @Override
  public void update(TrechoRodovia trechoRodovia) throws SQLException {
    try (PreparedStatement statement = connection.prepareStatement(UPDATE_TRECHO_RODOVIA)){
      statement.setString(1, trechoRodovia.getDescricaoTrechoRodovia());
      statement.setString(2, trechoRodovia.getIdRodovia());
    } catch (SQLException ex) {
      Logger.getLogger(PgTrechoRodoviaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }
  }

  @Override
  public void delete(Integer id) throws SQLException {
    try (PreparedStatement statement = connection.prepareStatement(DELETE_TRECHO_RODOVIA)) {
      statement.setInt(1, id);

    } catch (SQLException ex) {
      Logger.getLogger(PgTrechoRodoviaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }
  }

  @Override
  public List<TrechoRodovia> all() throws SQLException {
    List<TrechoRodovia> trechoRodoviaList = new ArrayList<>();

    try (PreparedStatement statement = connection.prepareStatement(BUSCA_TODOS_TRECHO_RODOVIA);
         ResultSet result = statement.executeQuery()) {
      while (result.next()) {
        TrechoRodovia trechoRodovia = new TrechoRodovia();
        trechoRodovia.setDescricaoTrechoRodovia(result.getString("Descricao"));
        trechoRodovia.setIdRodovia(result.getString("idRodovia"));

        trechoRodoviaList.add(trechoRodovia);
      }
    } catch (SQLException ex) {
      Logger.getLogger(PgTrechoRodoviaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }
    return trechoRodoviaList;
  }
}
