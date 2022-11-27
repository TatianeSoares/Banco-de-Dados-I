package DAO.Rodovia;

import DAO.Acidente.PgAcidenteDAO;
import model.Acidente;
import model.Rodovia;
import org.omnifaces.util.Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PgRodoviaDAO implements RodoviaDAO {

  private final Connection connection;

  private static final String INSERT_RODOVIA =
      "INSERT INTO rodovia.rodovias(descricao) " +
          "VALUES(?)";

  private static final String BUSCA_TODAS_RODOVIAS =
      "SELECT descricao " +
          "FROM rodovia.rodovias " +
          "ORDER BY descricao";

  private static final String BUSCA_RODOVIA =
      "SELECT descricao " +
          "FROM rodovia.rodovias " +
          "WHERE descricao = ?;";

  private static final String UPDATE_RODOVIA =
      "UPDATE rodovia.rodovias " +
          "SET descricao " +
          "WHERE descricao = ;";

  private static final String DELETE_RODOVIA =
      "DELETE FROM rodovia.rodovias " +
      "WHERE descricao = ;";

  public PgRodoviaDAO(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void create(Rodovia rodovia) throws SQLException {
    try (PreparedStatement statement = connection.prepareStatement(INSERT_RODOVIA)) {
      statement.setString(1, rodovia.getDescricaoRodovia());
      statement.executeUpdate();
    } catch (SQLException ex) {
      Messages.addGlobalError("Erro ao inserir rodovia");
    }
  }

  @Override
  public Rodovia read(String id) throws SQLException {
    Rodovia rodovia = new Rodovia();
//
//    try (PreparedStatement statement = connection.prepareStatement(BUSCA_RODOVIA)) {
//      statement.setInt(1, id);
//      try (ResultSet result = statement.executeQuery()) {
//        if (result.next()) {
//          rodovia.setIdRodovia(result.getInt("idRodovia"));
//          rodovia.setDescricaoRodovia(result.getString("descricaoRodovia"));
//        } else {
//          throw new SQLException("Erro ao visualizar: rodovia não pode ser encontrada.");
//        }
//      }
//    } catch (SQLException ex) {
//      Logger.getLogger(PgRodoviaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
//    }
    return rodovia;
  }

  @Override
  public void update(Rodovia rodovia) throws SQLException {
    try (PreparedStatement statement = connection.prepareStatement(UPDATE_RODOVIA)) {
      statement.setInt(1, rodovia.getIdRodovia());
      statement.setString(2, rodovia.getDescricaoRodovia());

    } catch (SQLException ex) {
      Logger.getLogger(PgRodoviaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }
  }

  @Override
  public void delete(Integer id) throws SQLException {
    try (PreparedStatement statement = connection.prepareStatement(DELETE_RODOVIA)) {
      statement.setInt(1, id);

    } catch (SQLException ex) {
      Logger.getLogger(PgRodoviaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }
  }

  @Override
  public List<Rodovia> all() throws SQLException {
    List<Rodovia> rodoviaList = new ArrayList<>();

    try (PreparedStatement statement = connection.prepareStatement(BUSCA_TODAS_RODOVIAS);
         ResultSet result = statement.executeQuery()) {
      while (result.next()) {
        Rodovia rodovia = new Rodovia();
        rodovia.setIdRodovia(result.getInt("idRodovia"));
        rodovia.setDescricaoRodovia(result.getString("descricaoRodovia"));

        rodoviaList.add(rodovia);
      }
    } catch (SQLException ex) {
      Logger.getLogger(PgRodoviaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }
    return rodoviaList;
  }
}
