package controller;

import DAO.DAOFactory;
import DAO.Relatorio.RelatorioDAO;
import lombok.Getter;
import lombok.Setter;
import model.Relatorio;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.bar.BarChartDataSet;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class RelatorioController implements Serializable {

  @Getter @Setter private List<Relatorio> listTAT;
  @Getter @Setter private List<Relatorio> listAUV
  @Getter @Setter private BarChartModel barModel2;

  public void init() throws SQLException, IOException, ClassNotFoundException {
    listAUV = new ArrayList<>();
    listTAT = new ArrayList<>();
  }

  public void gerarVelocidadeXUltrapassagem() throws SQLException, IOException, ClassNotFoundException {
    barModel2 = new BarChartModel();
    ChartData data = new ChartData();

    try(DAOFactory daoFactory = DAOFactory.getInstance()){
      RelatorioDAO relatorioDAO = daoFactory.getRelatorioDAO();
      //auv retorna o trecho de rodovia qt aci, e placas
      listAUV = relatorioDAO.all();
    } catch (Exception e) {
      e.printStackTrace();
    }

    BarChartDataSet barDataSet = new BarChartDataSet();
//    barDataSet.setLabel("Acidentes");
//    barDataSet.setBackgroundColor("rgba(255, 99, 132, 0.2)");
//    barDataSet.setBorderColor("rgb(255, 99, 132)");
//    barDataSet.setBorderWidth(1);

    List<Number> acidentes = new ArrayList<>();
    for(int i = 0; i < 5; i++){
      acidentes.add(listAUV.get(i).getQtAcidente());
    }

    barDataSet.setData(acidentes);

    BarChartDataSet barDataSet2 = new BarChartDataSet();
//    barDataSet2.setLabel("Ultrapassagem");
//    barDataSet2.setBackgroundColor("rgba(255, 159, 64, 0.2)");
//    barDataSet2.setBorderColor("rgb(255, 159, 64)");
//    barDataSet2.setBorderWidth(1);

    List<Number> ultrapassagem = new ArrayList<>();
    for(int i = 0; i < 5; i++){
      ultrapassagem.add(listAUV.get(i).getQtUltrapassagem());
    }

    barDataSet.setData(ultrapassagem);

    BarChartDataSet barDataSet3 = new BarChartDataSet();
//    barDataSet3.setLabel("Velocidade Máxima");
//    barDataSet3.setBackgroundColor("rgba(255, 99, 132, 0.2)");
//    barDataSet3.setBorderColor("rgb(255, 99, 64)");
//    barDataSet3.setBorderWidth(1);

    List<Number> velocidade = new ArrayList<>();
    for(int i = 0; i < 5; i++){
      velocidade.add(listAUV.get(i).getQtVelocidade());
    }

    barDataSet.setData(velocidade);

    data.addChartDataSet(barDataSet);
    data.addChartDataSet(barDataSet2);
    data.addChartDataSet(barDataSet3);
  }

  public void gerarRelatorioAcidentePorTipo(){
    try(DAOFactory daoFactory = DAOFactory.getInstance()){
      RelatorioDAO relatorioDAO = daoFactory.getRelatorioDAO();
      //tat retorna quantidade de acidente naquela rodovia do tipo tal
      listTAT = relatorioDAO.buscaAcidentePorTipo("Sem vítima");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}