package DAO.Relatorio;

import DAO.Acidente.PgAcidenteDAO;
import model.Relatorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PgRelatorioDAO implements RelatorioDAO{

  private final Connection connection;

  private static final String BUSCA_ACIDENTE_POR_TIPO =
      "SELECT tr.descricao as Trecho, " +
          "       a.idTipoOcorrencia as Tipo_Ocorrencia, " +
          "       a.idTipoAcidente as Acidente, " +
          "       a.data as Data_Acidente, " +
          "       count(1) as Qtde " +
          "FROM rodovia.trechoRodovia tr " +
          "         JOIN rodovia.acidente a ON a.idTrechoRodovia = tr.descricao " +
          "         JOIN rodovia.tipoacidente tpa ON a.idTipoAcidente = tpa.descricao " +
          "         JOIN rodovia.sentidorodovia sr ON a.idSentidoRodovia = sr.descricao " +
          "         JOIN rodovia.tipoocorrencia tpo ON a.idTipoOcorrencia = tpo.descricao " +
          "where a.idtipoocorrencia = ? " +
          "group by tr.descricao, a.idTipoOcorrencia, a.idTipoAcidente, a.data;";

  private static final String BUSCA_ACI_ULT_VELO =
          "SELECT tr.descricao, " +
          "       COALESCE(sum(A.QtdeAcidente), 0) QtdeAcidente, " +
          "       COALESCE(sum(UT.QtdeUltrapassagem), 0) QtdeUltrapassagem, " +
          "       COALESCE(sum(VM.QtdeVelMaxima), 0) QtdeVelMaxima " +
          "FROM rodovia.trechoRodovia tr " +
          "         LEFT JOIN (SELECT a.idTrechoRodovia as Trecho, " +
          "                           a.idTipoOcorrencia as Ocorrencia, " +
          "                           a.data as DataAcidente, " +
          "                           count(1) as QtdeAcidente " +
          "                    FROM rodovia.relatorio a " +
          "                             JOIN rodovia.trechoRodovia t ON a.idTrechoRodovia = t.descricao " +
          "                    group by a.idTrechoRodovia, a.idTipoOcorrencia, a.data) A " +
          "                   ON A.Trecho = tr.descricao " +
          "         LEFT JOIN (SELECT u.idTrechoRodovia as Trecho, " +
          "                           count(1) as QtdeUltrapassagem " +
          "                    FROM rodovia.ultrapassagem u " +
          "                             JOIN rodovia.trechoRodovia t ON u.idTrechoRodovia = t.descricao " +
          "                    group by u.idTrechoRodovia) UT " +
          "                   ON UT.Trecho = tr.descricao " +
          "         LEFT JOIN (SELECT v.idTrechoRodovia as Trecho, " +
          "                           v.veloVeicPesado as VelVeicPesado, " +
          "                           v.veloVeicLeve as VelVeicLeve, " +
          "                           count(1) as QtdeVelMaxima " +
          "                    FROM rodovia.velocidadeMaxima v " +
          "                             JOIN rodovia.trechoRodovia t ON v.idTrechoRodovia = t.descricao " +
          "                    group by v.idTrechoRodovia, v.veloVeicPesado, v.veloVeicLeve) VM " +
          "                   ON VM.Trecho = tr.descricao " +
          "                   GROUP BY tr.descricao;";

  public PgRelatorioDAO(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void create(Relatorio relatorio) throws SQLException {

  }

  @Override
  public Relatorio read(String id) throws SQLException {
    return null;
  }

  @Override
  public void update(Relatorio relatorio) throws SQLException {

  }

  @Override
  public void delete(Integer id) throws SQLException {

  }

  public List<Relatorio> buscaAcidentePorTipo(String tipoOcorrencia) throws SQLException {
    List<Relatorio> relatorioList = new ArrayList<>();

    try (PreparedStatement statement = connection.prepareStatement(BUSCA_ACIDENTE_POR_TIPO);
         ResultSet result = statement.executeQuery()) {
         statement.setString(22, tipoOcorrencia);
      while (result.next()) {
        Relatorio relatorio = new Relatorio();
        relatorio.setTrechoRodovia(result.getString("trecho"));
        relatorio.setTipoOcorrencia(result.getString("tipo_ocorrencia"));
        relatorio.setTipoAcidente(result.getString("acidente"));
        relatorio.setDataAcidente(result.getDate("data_acidente"));
        relatorio.setQtAcidente(result.getInt("qtde"));

        relatorioList.add(relatorio);
      }
    } catch (SQLException ex) {
      Logger.getLogger(PgAcidenteDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }
    return relatorioList;
  }
  @Override
  public List<Relatorio> all() throws SQLException {
    List<Relatorio> relatorioList = new ArrayList<>();

    try (PreparedStatement statement = connection.prepareStatement(BUSCA_ACI_ULT_VELO);
         ResultSet result = statement.executeQuery()) {
      while (result.next()) {
        Relatorio relatorio = new Relatorio();
        relatorio.setTrechoRodovia(result.getString("descricao"));
        relatorio.setQtAcidente(result.getInt("qtdeacidente"));
        relatorio.setQtUltrapassagem(result.getInt("qtdeultrapassagem"));
        relatorio.setQtVelocidade(result.getInt("qtdevelmaxima"));

        relatorioList.add(relatorio);
      }
    } catch (SQLException ex) {
      Logger.getLogger(PgAcidenteDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
    }
    return relatorioList;
  }
}
