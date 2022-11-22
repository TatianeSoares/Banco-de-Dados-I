package DAO;

import DAO.Acidente.AcidenteDAO;
import DAO.Acidente.PgAcidenteDAO;

import java.sql.Connection;

public class PgDAOFactory extends DAOFactory {

  public PgDAOFactory(Connection connection) {

    this.connection = connection;
  }

  public AcidenteDAO getAcidenteDAO(){
    return new PgAcidenteDAO(this.connection);
  }


}
