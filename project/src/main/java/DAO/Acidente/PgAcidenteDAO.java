package DAO.Acidente;

import model.Acidente;
import org.omnifaces.util.Messages;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PgAcidenteDAO implements AcidenteDAO{

  private final Connection connection;

  private static final String INSERT_ACIDENTE =
      "INSERT INTO rodovia.acidente(data, " +
          "hora, nrocorrencia, km, automovel, bicicleta, caminhao, " +
          "moto, onibus, outros, tracaoanimal, cargaespecial, tratormaquina, utilitario, " +
          "ileso, levementeferido, gravementeferido, mortos, idtrechorodovia, idsentidorodovia, idtipoocorrencia, idtipoacidente) " +
      "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

  private static final String BUSCA_TODOS_ACIDENTES =
      "SELECT data, hora, nrOcorrencia, km, automovel, bicicleta, caminhao, moto, onibus, outros, tracaoAnimal, cargaEspecial, tratorMaquina, utilitario, ileso, levementeFerido, gravementeFerido, mortos, idTrechoRodovia, idSentidoRodovia, idTipoOcorrencia, idTipoAcidente " +
      "FROM rodovia.acidente " +
      "ORDER BY data, nrOcorrencia;";

  private static final String BUSCA_ACIDENTE =
      "SELECT data, hora, nrOcorrencia, km, automovel, bicicleta, caminhao, moto, onibus, outros, tracaoAnimal, cargaEspecial, tratorMaquina, utilitario, ileso, levementeFerido, gravementeFerido, mortos, idTrechoRodovia, idSentidoRodovia, idTipoOcorrencia, idTipoAcidente " +
      "FROM rodovia.acidente " +
      "WHERE data = ? AND nrOcorrencia = ?;";

  private static final String UPDATE_ACIDENTE =
      "UPDATE rodovia.acidente " +
          "SET " +
          "data = ?," +
          "hora = ?," +
          "nrOcorrencia = ?," +
          "km = ?," +
          "automovel = ?," +
          "bicicleta = ?," +
          "caminhao = ?," +
          "moto = ?," +
          "onibus = ?," +
          "outros = ?," +
          "tracaoAnimal = ?," +
          "cargaEspecial = ?," +
          "tratorMaquina = ?," +
          "utilitario = ?," +
          "ileso = ?," +
          "levementeFerido = ?," +
          "gravementeFerido = ?," +
          "mortos = ?," +
          "idTrechoRodovia = ?," +
          "idSentidoRodovia = ?," +
          "idTipoOcorrencia = ?," +
          "idTipoAcidente = ?" +
          "WHERE data = ? AND nrOcorrencia = ?;";

  private static final String DELETE_ACIDENTE =
      "DELETE FROM rodovia.acidente " +
      "WHERE data = ? AND nrOcorrencia = ?;";


  private static final String BUSCA_FATAIS_ACIDENTES =
          "SELECT data, hora, nrOcorrencia, km, automovel, bicicleta, caminhao, moto, onibus, outros, tracaoAnimal, cargaEspecial, tratorMaquina, utilitario, ileso, levementeFerido, gravementeFerido, mortos, idTrechoRodovia, idSentidoRodovia, idTipoOcorrencia, idTipoAcidente " +
                  "FROM rodovia.acidente " +
                  "WHERE mortos != 0;";

  private static final String BUSCA_FATAIS_ACIDENTES_COUNT =
          "SELECT COUNT(case when vm.veloveicleve = 40 then 1 end) count40, COUNT(case when vm.veloveicleve = 60 then 1 end) count60, " +
          "COUNT(case when vm.veloveicleve = 80 then 1 end) count80, COUNT(case when vm.veloveicleve = 110 then 1 end) count110 " +
          "FROM rodovia.acidente a " +
          "JOIN rodovia.trechoRodovia tr ON a.idTrechoRodovia = tr.descricao " +
          "JOIN rodovia.sentidoRodovia sr ON a.idSentidoRodovia = sr.descricao " +
          "JOIN rodovia.rodovias r ON tr.idRodovia = r.descricao " +
          "JOIN rodovia.velocidadeMaxima vm ON vm.idTrechoRodovia = tr.descricao AND vm.idSentidoRodovia = sr.descricao " +
          "WHERE ABS(a.km - vm.km) < 1 AND (a.levementeferido != 0 OR a.gravementeferido != 0 OR a.mortos != 0);";

  public PgAcidenteDAO(Connection connection) {
    this.connection = connection;
  }

  public List<Integer> getAcidentesFataisProximos1KmCount() {
    List<Acidente> acidenteList = new ArrayList<>();
    List<Integer> counts = new ArrayList<>();;
    try (PreparedStatement statement = connection.prepareStatement(BUSCA_FATAIS_ACIDENTES_COUNT);
         ResultSet result = statement.executeQuery()) {
      while (result.next()) {
        int count40 = result.getInt("count40");
        int count60 = result.getInt("count60");
        int count80 = result.getInt("count80");
        int count110 = result.getInt("count110");
        counts.add(count40);
        counts.add(count60);
        counts.add(count80);
        counts.add(count110);
      }

    } catch (SQLException ex) {
      Logger.getLogger(PgAcidenteDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }

    return counts;
  }

//  public List<Acidente> getAcidentesFataisProximos1Km(){
//    List<Acidente> acidenteList = new ArrayList<>();
//    try (PreparedStatement statement = connection.prepareStatement(BUSCA_FATAIS_ACIDENTES_PROXIMOS_1KM);
//      ResultSet result = statement.executeQuery()) {
//        while (result.next()) {
//          Acidente acidente = new Acidente();
//          acidente.setData(result.getDate("data"));
//          acidente.setHora(result.getTime("hora"));
//          acidente.setNrOcorrencia(result.getInt("nrOcorrencia"));
//          acidente.setKm(result.getFloat("km"));
//          acidente.setAutomovel(result.getInt("automovel"));
//          acidente.setBicicleta(result.getInt("bicicleta"));
//          acidente.setCaminhao(result.getInt("caminhao"));
//          acidente.setMoto(result.getInt("moto"));
//          acidente.setOnibus(result.getInt("onibus"));
//          acidente.setOutros(result.getInt("outros"));
//          acidente.setTracaoAnimal(result.getInt("tracaoAnimal"));
//          acidente.setCargaEspecial(result.getInt("cargaEspecial"));
//          acidente.setTratorMaquina(result.getInt("tratorMaquina"));
//          acidente.setUtilitario(result.getInt("utilitario"));
//          acidente.setIleso(result.getInt("ileso"));
//          acidente.setLevementeFerido(result.getInt("levementeFerido"));
//          acidente.setGravementeFerido(result.getInt("gravementeFerido"));
//          acidente.setMortos(result.getInt("mortos"));
//          acidente.setIdTrechoRodovia(result.getString("idTrechoRodovia"));
//          acidente.setIdSentidoRodovia(result.getString("idSentidoRodovia"));
//          acidente.setIdTipoOcorrencia(result.getString("idTipoOcorrencia"));
//          acidente.setIdTipoAcidente(result.getString("idTipoAcidente"));
//
//          acidenteList.add(acidente);
//        }
//
//    }catch (SQLException ex) {
//      Logger.getLogger(PgAcidenteDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
//    }
//
//    return acidenteList;
//  }

  public List<Acidente> getAcidentesFatais() throws SQLException {

    List<Acidente> acidenteList = new ArrayList<>();
    try (PreparedStatement statement = connection.prepareStatement(BUSCA_FATAIS_ACIDENTES);
          ResultSet result = statement.executeQuery()) {
      while (result.next()) {
        Acidente acidente = new Acidente();
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
        statement.setString(19, acidente.getIdTrechoRodovia());
        statement.setString(20, acidente.getIdSentidoRodovia());
        statement.setString(21, acidente.getIdTipoOcorrencia());
        statement.setString(22, acidente.getIdTipoAcidente());

        acidenteList.add(acidente);
      }

    } catch (SQLException ex) {
      Logger.getLogger(PgAcidenteDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }

    return acidenteList;
  }

  public boolean verificarInAcidente(Acidente acidente){
    return true;
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
      statement.setString(19, acidente.getIdTrechoRodovia());
      statement.setString(20, acidente.getIdSentidoRodovia());
      statement.setString(21, acidente.getIdTipoOcorrencia());
      statement.setString(22, acidente.getIdTipoAcidente());

      statement.executeUpdate();
    } catch (SQLException ex) {
      Messages.addGlobalError("Erro ao inserir acidente");
    }
  }

  @Override
  public Acidente read(String id) throws SQLException {
    Acidente acidente = new Acidente();
//
//    try (PreparedStatement statement = connection.prepareStatement(BUSCA_ACIDENTE)) {
//      statement.setInt(1, id);
//      try (ResultSet result = statement.executeQuery()) {
//        if (result.next()) {
//          acidente.setData(result.getDate("data"));
//          acidente.setHora(result.getTime("hora"));
//          acidente.setNrOcorrencia(result.getInt("nrOcorrencia"));
//          acidente.setKm(result.getFloat("km"));
//          acidente.setAutomovel(result.getInt("automovel"));
//          acidente.setBicicleta(result.getInt("bicicleta"));
//          acidente.setCaminhao(result.getInt("caminhao"));
//          acidente.setMoto(result.getInt("moto"));
//          acidente.setOnibus(result.getInt("onibus"));
//          acidente.setOutros(result.getInt("outros"));
//          acidente.setTracaoAnimal(result.getInt("tracaoAnimal"));
//          acidente.setCargaEspecial(result.getInt("cargaEspecial"));
//          acidente.setTratorMaquina(result.getInt("tratorMaquin"));
//          acidente.setUtilitario(result.getInt("utilitario"));
//          acidente.setIleso(result.getInt("ileso"));
//          acidente.setLevementeFerido(result.getInt("levementeFeridos"));
//          acidente.setGravementeFerido(result.getInt("gravementeFeridos"));
//          acidente.setMortos(result.getInt("mortos"));
//          acidente.setIdTrechoRodovia(result.getString("idTrechoRodovia"));
//          acidente.setIdSentidoRodovia(result.getString("idSentidoRodovia"));
//          acidente.setIdTipoOcorrencia(result.getString("idTipoOcorrencia"));
//          acidente.setIdTipoAcidente((result.getString("idTipoAcidente")));
//        } else {
//          throw new SQLException("Erro ao visualizar: acidente não pode ser encontrado.");
//        }
//      }
//    } catch (SQLException ex) {
//      Logger.getLogger(PgAcidenteDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
//    }
    return acidente;
  }

  @Override
  public void update(Acidente acidente) throws SQLException {

    try (PreparedStatement statement = connection.prepareStatement(UPDATE_ACIDENTE)) {
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
      statement.setString(19, acidente.getIdTrechoRodovia());
      statement.setString(20, acidente.getIdSentidoRodovia());
      statement.setString(21, acidente.getIdTipoOcorrencia());
      statement.setString(22, acidente.getIdTipoAcidente());

    } catch (SQLException ex) {
      Logger.getLogger(PgAcidenteDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }
  }

  @Override
  public void delete(Integer id) throws SQLException {
    try (PreparedStatement statement = connection.prepareStatement(DELETE_ACIDENTE)) {
      statement.setInt(1, id);

    } catch (SQLException ex) {
      Logger.getLogger(PgAcidenteDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }
  }

  @Override
  public List<Acidente> all() throws SQLException {
    List<Acidente> acidenteList = new ArrayList<>();

    try (PreparedStatement statement = connection.prepareStatement(BUSCA_TODOS_ACIDENTES);
         ResultSet result = statement.executeQuery()) {
      while (result.next()) {
        Acidente acidente = new Acidente();
        acidente.setData(result.getDate("data"));
        acidente.setHora(result.getTime("hora"));
        acidente.setNrOcorrencia(result.getInt("nrOcorrencia"));
        acidente.setKm(result.getFloat("km"));
        acidente.setAutomovel(result.getInt("automovel"));
        acidente.setBicicleta(result.getInt("bicicleta"));
        acidente.setCaminhao(result.getInt("caminhao"));
        acidente.setMoto(result.getInt("moto"));
        acidente.setOnibus(result.getInt("onibus"));
        acidente.setOutros(result.getInt("outros"));
        acidente.setTracaoAnimal(result.getInt("tracaoAnimal"));
        acidente.setCargaEspecial(result.getInt("cargaEspecial"));
        acidente.setTratorMaquina(result.getInt("tratorMaquina"));
        acidente.setUtilitario(result.getInt("utilitario"));
        acidente.setIleso(result.getInt("ileso"));
        acidente.setLevementeFerido(result.getInt("levementeFerido"));
        acidente.setGravementeFerido(result.getInt("gravementeFerido"));
        acidente.setMortos(result.getInt("mortos"));
        acidente.setIdTrechoRodovia(result.getString("idTrechoRodovia"));
        acidente.setIdSentidoRodovia(result.getString("idSentidoRodovia"));
        acidente.setIdTipoOcorrencia(result.getString("idTipoOcorrencia"));
        acidente.setIdTipoAcidente(result.getString("idTipoAcidente"));

        acidenteList.add(acidente);
      }
    } catch (SQLException ex) {
      Logger.getLogger(PgAcidenteDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }
    return acidenteList;
  }
}
