package DAO.Acidente;

import model.Acidente;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PgAcidenteDAO implements AcidenteDAO{

  private final Connection connection;

  private static final String INSERT_ACIDENTE =
      "INSERT INTO rodovia.acidente(data" +
          "hora, nrOcorrencia, km, automovel, bicicleta, caminhao" +
          "moto, onibus, outros, tracaoAnimal, cargaEspecial, tratorMaquina, utilitario" +
          "ileso, levementeFerido, gravementeFerido, mortos)" +
      "VALUES();";

  private static final String BUSCA_TODOS_ACIDENTES =
      "SELECT data, hora, nrOcorrencia, km, automovel, bicicleta, caminhao, moto, onibus, outros, tracaoAnimal, cargaEspecial, tratorMaquina, utilitario, ileso, levementeFerido, gravementeFerido, mortos" +
      "FROM rodovia.acidente" +
      "ORDER BY data, nrOcorrencia;";

  private static final String BUSCA_ACIDENTE =
      "SELECT data, hora, nrOcorrencia, km, automovel, bicicleta, caminhao, moto, onibus, outros, tracaoAnimal, cargaEspecial, tratorMaquina, utilitario, ileso, levementeFerido, gravementeFerido, mortos" +
      "FROM rodovia.acidente" +
      "WHERE id = ?;";

  private static final String UPDATE_ACIDENTE =
      "UPDATE FROM rodovia.acidente" +
          "SET " +
          "data = " +
          "hora = " +
          "nrOcorrencia =" +
          "km =" +
          "automovel =" +
          "bicicleta =" +
          "caminhao =" +
          "moto =" +
          "onibus =" +
          "outros =" +
          "tracaoAnimal =" +
          "cargaEspecial =" +
          "tratorMaquina =" +
          "utilitario =" +
          "ileso =" +
          "levementeFerido =" +
          "gravementeFerido =" +
          "mortos =" +
          "WHERE id = ;";

  private static final String DELETE_ACIDENTE =
      "DELETE FROM rodovia.acidente" +
      "WHERE id = ;";

  public PgAcidenteDAO(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void adicionarAcidente(Acidente acidente) {

  }

  @Override
  public Acidente getAcidente(int id) {
    return null;
  }

  @Override
  public List<Acidente> getTodosAcidentes() {
    return null;
  }

  @Override
  public void create(Acidente acidente) throws SQLException {

  }

  @Override
  public Acidente read(Integer id) throws SQLException {
    return null;
  }

  @Override
  public void update(Acidente acidente) throws SQLException {

  }

  @Override
  public void delete(Integer id) throws SQLException {

  }

  @Override
  public List<Acidente> all() throws SQLException {
    return null;
  }
}
