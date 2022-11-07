package DAO.TipoPista;

import DAO.Rodovia.PgRodoviaDAO;
import model.Rodovia;
import model.TipoPista;
import org.omnifaces.util.Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PgTipoPistaDAO implements TipoPistaDAO{

  private final Connection connection;

  private static final String INSERT_TIPO_PISTA =
      "INSERT INTO rodovia.tipoPista(id, descricao)" +
          "VALUES()";

  private static final String BUSCA_TODOS_TIPO_PISTA =
      "SELECT id, descricao" +
          "FROM rodovia.tipoPista" +
          "ORDER BY id";

  private static final String BUSCA_TIPO_PISTA =
      "SELECT id, descricao" +
          "FROM rodovia.tipoPista" +
          "WHERE id = ?;";

  private static final String UPDATE_TIPO_PISTA =
      "UPDATE FROM rodovia.tipoPista" +
          "SET id, descricao" +
          "WHERE id = ;";

  private static final String DELETE_TIPO_PISTA =
      "DELETE FROM rodovia.tipoPista" +
          "WHERE id = ;";

  public PgTipoPistaDAO(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void create(TipoPista tipoPista) throws SQLException {
    try (PreparedStatement statement = connection.prepareStatement(INSERT_TIPO_PISTA)) {
      statement.setInt(1, tipoPista.getIdTipoPista());
      statement.setString(2, tipoPista.getDescricaoTipoPista());
      statement.executeUpdate();
    } catch (SQLException ex) {
      Messages.addGlobalError("Erro ao inserir trechoRodovia");
    }
  }

  @Override
  public TipoPista read(Integer id) throws SQLException {
    TipoPista tipoPista = new TipoPista();

    try (PreparedStatement statement = connection.prepareStatement(BUSCA_TIPO_PISTA)) {
      statement.setInt(1, id);
      try (ResultSet result = statement.executeQuery()) {
        if (result.next()) {
          tipoPista.setIdTipoPista(result.getInt("idTipoPista"));
          tipoPista.setDescricaoTipoPista(result.getString("descricaoTipoPista"));
        } else {
          throw new SQLException("Erro ao visualizar: TipoPista não pode ser encontrado.");
        }
      }
    } catch (SQLException ex) {
      Logger.getLogger(PgTipoPistaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }
    return tipoPista;
  }

  @Override
  public void update(TipoPista tipoPista) throws SQLException {
    try (PreparedStatement statement = connection.prepareStatement(UPDATE_TIPO_PISTA)) {
      statement.setInt(1, tipoPista.getIdTipoPista());
      statement.setString(2, tipoPista.getDescricaoTipoPista());

    } catch (SQLException ex) {
      Logger.getLogger(PgTipoPistaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }
  }

  @Override
  public void delete(Integer id) throws SQLException {
    try (PreparedStatement statement = connection.prepareStatement(DELETE_TIPO_PISTA)) {
      statement.setInt(1, id);

    } catch (SQLException ex) {
      Logger.getLogger(PgTipoPistaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }
  }

  @Override
  public List<TipoPista> all() throws SQLException {
    List<TipoPista> tipoPistaList = new ArrayList<>();

    try (PreparedStatement statement = connection.prepareStatement(BUSCA_TODOS_TIPO_PISTA);
         ResultSet result = statement.executeQuery()) {
      while (result.next()) {
        TipoPista tipoPista = new TipoPista();
        tipoPista.setIdTipoPista(result.getInt("idTipoPista"));
        tipoPista.setDescricaoTipoPista(result.getString("descricaoTipoPista"));

        tipoPistaList.add(tipoPista);
      }
    } catch (SQLException ex) {
      Logger.getLogger(PgTipoPistaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }
    return tipoPistaList;
  }
}
