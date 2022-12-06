package controller;

import DAO.Acidente.AcidenteDAO;
import DAO.DAOFactory;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.chart.PieChartModel;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class PieController implements Serializable {

  @Getter @Setter private PieChartModel pieModel;

  public void init() throws Exception {

  }

  public void getDataVelocidadeObitos() throws Exception {

    List<Integer> countList;

    try(DAOFactory daoFactory = DAOFactory.getInstance()){
      AcidenteDAO acidenteDAO = daoFactory.getAcidenteDAO();
      countList = acidenteDAO.getAcidentesFataisProximos1KmCount();
      gerarVelocidadeObitos(countList);
    }
  }

  private void gerarVelocidadeObitos(List<Integer> counts){

    pieModel = new PieChartModel();

    pieModel.set("40km/h", counts.get(0));
    pieModel.set("60km/h", counts.get(1));
    pieModel.set("80km/h", counts.get(2));
    pieModel.set("110km/h", counts.get(3));
    pieModel.setTitle("Relação de Velocidades regulamentadas com acidentes com feridos");
    pieModel.setLegendPosition("e");
    pieModel.setFill(true);
    pieModel.setShowDataLabels(true);
    pieModel.setDiameter(150);
  }
}