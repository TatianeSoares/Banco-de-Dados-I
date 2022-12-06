package controller;

import DAO.DAOFactory;
import DAO.Relatorio.RelatorioDAO;
import lombok.Getter;
import lombok.Setter;
import model.Relatorio;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Named
@ViewScoped
public class DounutController implements Serializable {

  @Getter @Setter private List<Relatorio> listTAT;
  @Getter @Setter private List<Relatorio> listAUV;
  @Getter @Setter private DonutChartModel donutModel;
  @Getter @Setter private String trecho;
  @Getter @Setter private int acidente = 0;
  @Getter @Setter private int ultrapassagem = 0;
  @Getter @Setter private int velocidade = 0;

  @PostConstruct
  public void init() throws SQLException, IOException, ClassNotFoundException {
    listAUV = new ArrayList<>();
  }

  public void gerarVelocidadeXUltrapassagem(){
    try(DAOFactory daoFactory = DAOFactory.getInstance()){
      RelatorioDAO relatorioDAO = daoFactory.getRelatorioDAO();
      listAUV = relatorioDAO.all();
    } catch (Exception e) {
      e.printStackTrace();
    }
    gerarRelatorioDonut();
  }

  public void gerarRelatorioDonut(){
    donutModel = new DonutChartModel();
    ChartData data = new ChartData();

    Random gerador = new Random();
    trecho = listAUV.get(gerador.nextInt(getListAUV().size())).getTrechoRodovia();
    for(int i = 0; i < getListAUV().size(); i++){
      if(listAUV.get(i).getTrechoRodovia().equals(trecho)){
        acidente = listAUV.get(i).getQtAcidente();
        ultrapassagem = listAUV.get(i).getQtUltrapassagem();
        velocidade = listAUV.get(i).getQtVelocidade();
      }
    }

    Number a = acidente;
    Number u = ultrapassagem;
    Number v = velocidade;

    DonutChartDataSet dataSet = new DonutChartDataSet();
    List<Number> values = new ArrayList<>();
    values.add(a);
    values.add(u);
    values.add(v);
    dataSet.setData(values);

    List<String> bgColors = new ArrayList<>();
    bgColors.add("rgb(255, 99, 132)");
    bgColors.add("rgb(54, 162, 235)");
    bgColors.add("rgb(255, 205, 86)");
    dataSet.setBackgroundColor(bgColors);

    data.addChartDataSet(dataSet);
    List<String> labels = new ArrayList<>();
    labels.add("Acidentes");
    labels.add("Placa de Ultrapassagem");
    labels.add("Placa de Velocidade Máxima");
    data.setLabels(labels);

    donutModel.setData(data);
  }
}

