package DAO.Acidente;

import model.Acidente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    try (PreparedStatement statement = connection.prepareStatement(INSERT_ACIDENTE)) {
      statement.setDate(1, acidente.getData());
      statement.setTime(2, acidente.getHora());
      statement.setInt(3, acidente.getNrOcorrencia());
      statement.setFloat(4, acidente.getKm());
      statement.setInt(5, acidente.getAutomovel());
      statement.setInt(6, acidente.getBicicleta());
      statement.setInt(7, acidente.getCaminhao());
      statement.setInt(8, acidente.getMoto());
      statement.setInt(9, acidente.getOnibus());
      statement.setInt(10, acidente.getOutros());
      statement.setInt(11, acidente.getTracaoAnimal());
      statement.setInt(12, acidente.getCargaEspecial());
      statement.setInt(13, acidente.getTratorMaquina());
      statement.setInt(14, acidente.getUtilitario());
      statement.setInt(15, acidente.getIleso());
      statement.setInt(16, acidente.getLevementeFerido());
      statement.setInt(17, acidente.getGravementeFerido());
      statement.setInt(18, acidente.getMortos());
      //TODO nao foi adicionado as fk

      statement.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(PgAcidenteDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

      if (ex.getMessage().contains("uq_user_login")) {
        throw new SQLException("Erro ao inserir acidente, informações já existente no banco de dados.");
      } else if (ex.getMessage().contains("not-null")) {
        //TODO talvez este exeption nao deva estar aqui
        throw new SQLException("Erro ao inserir acidente: pelo menos um campo está em branco.");
      } else {
        throw new SQLException("Erro ao inserir acidente.");
      }
    }
  }

  @Override
  public Acidente read(Integer id) throws SQLException {
    Acidente acidente = new Acidente();

    try (PreparedStatement statement = connection.prepareStatement(BUSCA_ACIDENTE)) {
      statement.setInt(1, id);
      try (ResultSet result = statement.executeQuery()) {
        if (result.next()) {
          acidente.setIdAcidente(result.getInt("idAcidente"));
          acidente.setData(result.getDate("data"));
          acidente.setHora(result.getTime("hora"));
          acidente.setNrOcorrencia(result.getInt("nrOcorrencia"));
          acidente.setKm(result.getInt("km"));
          acidente.setAutomovel(result.getInt("automovel"));
          acidente.setBicicleta(result.getInt("bicicleta"));
          acidente.setCaminhao(result.getInt("caminhao"));
          acidente.setMoto(result.getInt("moto"));
          acidente.setOnibus(result.getInt("onibus"));
          acidente.setOutros(result.getInt("outros"));
          acidente.setTracaoAnimal(result.getInt("tracaoAnimal"));
          acidente.setCargaEspecial(result.getInt("cargaEspecial"));
          acidente.setTratorMaquina(result.getInt("tratorMaquin"));
          acidente.setUtilitario(result.getInt("utilitario"));
          acidente.setIleso(result.getInt("ileso"));
          acidente.setLevementeFerido(result.getInt("levementeFeridos"));
          acidente.setGravementeFerido(result.getInt("gravementeFeridos"));
          acidente.setMortos(result.getInt("mortos"));
        } else {
          throw new SQLException("Erro ao visualizar: acidente não pode ser encontrado.");
        }
      }
    } catch (SQLException ex) {
      Logger.getLogger(PgAcidenteDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

      if (ex.getMessage().equals("Erro ao visualizar: acidente não pode ser encontrado.")) {
        throw ex;
      } else {
        throw new SQLException("Erro ao visualizar acidente.");
      }
    }
    return acidente;
  }

  @Override
  public void update(Acidente acidente) throws SQLException {

    try (PreparedStatement statement = connection.prepareStatement(UPDATE_ACIDENTE)) {
      statement.setInt(1, acidente.getIdAcidente());
      statement.setDate(2, acidente.getData());
      statement.setTime(3, acidente.getHora());
      statement.setInt(4, acidente.getNrOcorrencia());
      statement.setFloat(5, acidente.getKm());
      statement.setInt(6, acidente.getAutomovel());
      statement.setInt(7, acidente.getBicicleta());
      statement.setInt(8, acidente.getCaminhao());
      statement.setInt(9, acidente.getMoto());
      statement.setInt(10, acidente.getOnibus());
      statement.setInt(11, acidente.getOutros());
      statement.setInt(12, acidente.getTracaoAnimal());
      statement.setInt(13, acidente.getCargaEspecial());
      statement.setInt(14, acidente.getTratorMaquina());
      statement.setInt(15, acidente.getUtilitario());
      statement.setInt(16, acidente.getIleso());
      statement.setInt(17, acidente.getLevementeFerido());
      statement.setInt(18, acidente.getGravementeFerido());
      statement.setInt(19, acidente.getMortos());

      if (statement.executeUpdate() < 1) {
        throw new SQLException("Erro ao editar: acidente não pode ser encontrado.");
      }
    } catch (SQLException ex) {
      Logger.getLogger(PgAcidenteDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

      if (ex.getMessage().equals("Erro ao editar: acidente não pode ser encontrado.")) {
        throw ex;
      } else if (ex.getMessage().contains("uq_user_login")) {
        //TODO verificar este exception
        throw new SQLException("Erro ao editar usuário: login já existente.");
      } else if (ex.getMessage().contains("not-null")) {
        //TODO verificar este exception
        throw new SQLException("Erro ao editar usuário: pelo menos um campo está em branco.");
      } else {
        throw new SQLException("Erro ao editar acidente.");
      }
    }
  }

  @Override
  public void delete(Integer id) throws SQLException {
    try (PreparedStatement statement = connection.prepareStatement(DELETE_ACIDENTE)) {
      statement.setInt(1, id);

      if (statement.executeUpdate() < 1) {
        throw new SQLException("Erro ao excluir, acidente não pode ser encontrado.");
      }
    } catch (SQLException ex) {
      Logger.getLogger(PgAcidenteDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

      if (ex.getMessage().equals("Erro ao excluir, acidente não pode ser encontrado.")) {
        throw ex;
      } else {
        throw new SQLException("Erro ao excluir acidente.");
      }
    }
  }

  @Override
  public List<Acidente> all() throws SQLException {
    List<Acidente> acidenteList = new ArrayList<>();

    try (PreparedStatement statement = connection.prepareStatement(BUSCA_TODOS_ACIDENTES);
         ResultSet result = statement.executeQuery()) {
      while (result.next()) {
        Acidente acidente = new Acidente();
        acidente.setIdAcidente(result.getInt("idAcidente"));
        acidente.setData(result.getDate("data"));
        acidente.setHora(result.getTime("hora"));
        acidente.setNrOcorrencia(result.getInt("nrOcorrencia"));
        acidente.setKm(result.getInt("km"));
        acidente.setAutomovel(result.getInt("automovel"));
        acidente.setBicicleta(result.getInt("bicicleta"));
        acidente.setCaminhao(result.getInt("caminhao"));
        acidente.setMoto(result.getInt("moto"));
        acidente.setOnibus(result.getInt("onibus"));
        acidente.setOutros(result.getInt("outros"));
        acidente.setTracaoAnimal(result.getInt("tracaoAnimal"));
        acidente.setCargaEspecial(result.getInt("cargaEspecial"));
        acidente.setTratorMaquina(result.getInt("tratorMaquin"));
        acidente.setUtilitario(result.getInt("utilitario"));
        acidente.setIleso(result.getInt("ileso"));
        acidente.setLevementeFerido(result.getInt("levementeFeridos"));
        acidente.setGravementeFerido(result.getInt("gravementeFeridos"));
        acidente.setMortos(result.getInt("mortos"));

        acidenteList.add(acidente);
      }
    } catch (SQLException ex) {
      Logger.getLogger(PgAcidenteDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

      throw new SQLException("Erro ao listar acidentes.");
    }
    return acidenteList;
  }
}
