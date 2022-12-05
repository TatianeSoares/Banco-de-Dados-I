package DAO;

import DAO.Acidente.AcidenteDAO;
import DAO.HistoricoCarga.HistoricoCargaDAO;
import DAO.Relatorio.RelatorioDAO;
import DAO.Rodovia.RodoviaDAO;
import DAO.SentidoRodovia.SentidoRodoviaDAO;
import DAO.Sinalizacao.UltrapassagemDAO;
import DAO.Sinalizacao.VelocidadeMaximaDAO;
import DAO.TipoAcidente.TipoAcidenteDAO;
import DAO.TipoOcorrencia.TipoOcorrenciaDAO;
import DAO.TipoPista.TipoPistaDAO;
import DAO.TrechoRodovia.TrechoRodoviaDAO;
import jdbc.ConnectionFactory;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAOFactory implements AutoCloseable{

  protected Connection connection;

  public static DAOFactory getInstance() throws ClassNotFoundException, IOException, SQLException {
    Connection connection = ConnectionFactory.getInstance().getConnection();
    DAOFactory factory;

    if (ConnectionFactory.getDbServer().equals("postgresql")) {
      factory = new PgDAOFactory(connection);
    }
    else {
      throw new RuntimeException("Servidor de banco de dados não suportado.");
    }
    return factory;
  }

  public void beginTransaction() throws SQLException {
    try {
      connection.setAutoCommit(false);
    } catch (SQLException ex) {
      System.err.println(ex.getMessage());

      throw new SQLException("Erro ao abrir transação.");
    }
  }

  public void commitTransaction() throws SQLException {
    try {
      connection.commit();
    } catch (SQLException ex) {
      System.err.println(ex.getMessage());

      throw new SQLException("Erro ao finalizar transação.");
    }
  }

  public void rollbackTransaction() throws SQLException {
    try {
      connection.rollback();
    } catch (SQLException ex) {
      System.err.println(ex.getMessage());

      throw new SQLException("Erro ao executar transação.");
    }
  }

  public void endTransaction() throws SQLException {
    try {
      connection.setAutoCommit(true);
    } catch (SQLException ex) {
      System.err.println(ex.getMessage());

      throw new SQLException("Erro ao finalizar transação.");
    }
  }

  public void closeConnection() throws SQLException {
    try {
      connection.close();
    } catch (SQLException ex) {
      System.err.println(ex.getMessage());

      throw new SQLException("Erro ao fechar conexão ao banco de dados.");
    }
  }

  @Override
  public void close() throws Exception {

  }

  public abstract AcidenteDAO getAcidenteDAO();
  public abstract RodoviaDAO getRodoviaDAO();
  public abstract SentidoRodoviaDAO getSentidoRodoviaDAO();
  public abstract TipoAcidenteDAO getTipoAcidenteDAO();
  public abstract TipoOcorrenciaDAO getTipoOcorrencia();
  public abstract TipoPistaDAO getTipoPistaDAO();
  public abstract TrechoRodoviaDAO getTrechoRodoviaDAO();
  public abstract UltrapassagemDAO getUltrapassagemDAO();
  public abstract VelocidadeMaximaDAO getVelocidadeMaximaDAO();
  public abstract HistoricoCargaDAO getHistoricoCargaDAO();
  public abstract RelatorioDAO getRelatorioDAO();

}