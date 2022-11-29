package controller;

import DAO.DAOFactory;
import DAO.HistoricoCarga.HistoricoCargaDAO;
import lombok.Getter;
import lombok.Setter;
import model.HistoricoCarga;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Named
@ViewScoped
public class historicoCargaController implements Serializable {

  @Getter @Setter List<HistoricoCarga> todasCargasList;

  public void init() throws SQLException, IOException, ClassNotFoundException {
    buscarCargas();
  }

  public void buscarCargas() throws SQLException, IOException, ClassNotFoundException {
    try(DAOFactory daoFactory = DAOFactory.getInstance()){
      HistoricoCargaDAO historicoCargaDAO = daoFactory.getHistoricoCargaDAO();
      todasCargasList = historicoCargaDAO.all();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
