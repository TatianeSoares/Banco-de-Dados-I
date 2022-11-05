package DAO.TipoPista;

import model.TipoPista;
import org.omnifaces.util.Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

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
    return null;
  }

  @Override
  public void update(TipoPista tipoPista) throws SQLException {

  }

  @Override
  public void delete(Integer id) throws SQLException {

  }

  @Override
  public List<TipoPista> all() throws SQLException {
    return null;
  }
}
