package DAO.HistoricoCarga;


import model.HistoricoCarga;
import org.omnifaces.util.Messages;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PgHistoricoCargaDAO implements HistoricoCargaDAO{

  private final Connection connection;

  private static final String INSERT_CARGA =
      "INSERT INTO rodovia.historicocarga(dataCarga, horaCarga, tipoCarga) " +
          "VALUES(?, ?, ?)";

  private static final String BUSCA_TODAS_CARGAS =
      "SELECT * " +
          "FROM rodovia.historicocarga " +
          "ORDER BY dataCarga, horaCarga";

  public PgHistoricoCargaDAO(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void create(HistoricoCarga historicoCarga) throws SQLException {
    try (PreparedStatement statement = connection.prepareStatement(INSERT_CARGA)) {
      statement.setDate(1, historicoCarga.getDataCarga());
      statement.setTime(2, historicoCarga.getHoraCarga());
      statement.setString(3, historicoCarga.getTipoCarga());
      statement.executeUpdate();
    } catch (SQLException ex) {
      Messages.addGlobalError("Erro ao inserir carga");
    }
  }

  @Override
  public HistoricoCarga read(String id) throws SQLException {
    return null;
  }

  @Override
  public void update(HistoricoCarga historicoCarga) throws SQLException {

  }

  @Override
  public void delete(Integer id) throws SQLException {

  }

  @Override
  public List<HistoricoCarga> all() throws SQLException {
    List<HistoricoCarga> historicoCargaList = new ArrayList<>();

    try (PreparedStatement statement = connection.prepareStatement(BUSCA_TODAS_CARGAS);
         ResultSet result = statement.executeQuery()) {
      while (result.next()) {
        HistoricoCarga historicoCarga = new HistoricoCarga();
        historicoCarga.setDataCarga(result.getDate("dataCarga"));
        historicoCarga.setHoraCarga(result.getTime("horaCarga"));
        historicoCarga.setTipoCarga(result.getString("tipoCarga"));

        historicoCargaList.add(historicoCarga);
      }
    } catch (SQLException ex) {
      Logger.getLogger(PgHistoricoCargaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }
    return historicoCargaList;
  }
}
