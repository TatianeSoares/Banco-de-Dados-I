package DAO;

import DAO.Acidente.AcidenteDAO;
import DAO.Acidente.PgAcidenteDAO;
import DAO.HistoricoCarga.HistoricoCargaDAO;
import DAO.HistoricoCarga.PgHistoricoCargaDAO;
import DAO.Relatorio.PgRelatorioDAO;
import DAO.Relatorio.RelatorioDAO;
import DAO.Rodovia.PgRodoviaDAO;
import DAO.Rodovia.RodoviaDAO;
import DAO.SentidoRodovia.PgSentidoRodoviaDAO;
import DAO.SentidoRodovia.SentidoRodoviaDAO;
import DAO.Sinalizacao.PgUltrapassagemDAO;
import DAO.Sinalizacao.PgVelocidadeMaximaDAO;
import DAO.Sinalizacao.UltrapassagemDAO;
import DAO.Sinalizacao.VelocidadeMaximaDAO;
import DAO.TipoAcidente.PgTipoAcidenteDAO;
import DAO.TipoAcidente.TipoAcidenteDAO;
import DAO.TipoOcorrencia.PgTipoOcorrenciaDAO;
import DAO.TipoOcorrencia.TipoOcorrenciaDAO;
import DAO.TipoPista.PgTipoPistaDAO;
import DAO.TipoPista.TipoPistaDAO;
import DAO.TrechoRodovia.PgTrechoRodoviaDAO;
import DAO.TrechoRodovia.TrechoRodoviaDAO;

import java.sql.Connection;

public class PgDAOFactory extends DAOFactory {

  public PgDAOFactory(Connection connection) {this.connection = connection;}
  public AcidenteDAO getAcidenteDAO(){return new PgAcidenteDAO(this.connection);}
  public RodoviaDAO getRodoviaDAO(){
    return new PgRodoviaDAO(this.connection);
  }
  public SentidoRodoviaDAO getSentidoRodoviaDAO(){
    return new PgSentidoRodoviaDAO(this.connection);
  }
  public TipoAcidenteDAO getTipoAcidenteDAO(){
    return new PgTipoAcidenteDAO(this.connection);
  }
  public TipoOcorrenciaDAO getTipoOcorrencia(){
    return new PgTipoOcorrenciaDAO(this.connection);
  }
  public TipoPistaDAO getTipoPistaDAO(){
    return new PgTipoPistaDAO(this.connection);
  }
  public TrechoRodoviaDAO getTrechoRodoviaDAO(){
    return new PgTrechoRodoviaDAO(this.connection);
  }
  public UltrapassagemDAO getUltrapassagemDAO(){
    return new PgUltrapassagemDAO(this.connection);
  }
  public VelocidadeMaximaDAO getVelocidadeMaximaDAO(){return new PgVelocidadeMaximaDAO(this.connection);}
  public HistoricoCargaDAO getHistoricoCargaDAO() {return new PgHistoricoCargaDAO(this.connection);}
  public RelatorioDAO getRelatorioDAO() {return new PgRelatorioDAO(this.connection);}
}
