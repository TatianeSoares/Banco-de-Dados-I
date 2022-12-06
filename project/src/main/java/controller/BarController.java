package controller;

import DAO.DAOFactory;
import DAO.Relatorio.RelatorioDAO;
import lombok.Getter;
import lombok.Setter;
import model.Relatorio;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.polar.PolarAreaChartDataSet;
import org.primefaces.model.charts.polar.PolarAreaChartModel;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class BarController implements Serializable {
  @Getter @Setter private List<Relatorio> semVitimaList;
  @Getter @Setter private List<Relatorio> comVitimaList;
  @Getter @Setter private String tipSelecionado;
  @Getter @Setter private PolarAreaChartModel polarAreaModel;

  @PostConstruct
  public void init() {
    semVitimaList = new ArrayList<>();
    comVitimaList = new ArrayList<>();
  }

  public void setType(String tipSelecionado){

    tipSelecionado = this.tipSelecionado;
  }

  public void gerarRelatorioAcidentePorTipo(){
    try(DAOFactory daoFactory = DAOFactory.getInstance()){
      RelatorioDAO relatorioDAO = daoFactory.getRelatorioDAO();
      semVitimaList = relatorioDAO.buscaAcidentePorTipo("sem vítima");
      comVitimaList = relatorioDAO.buscaAcidentePorTipo("com vítima");
    } catch (Exception e) {
      e.printStackTrace();
    }
    if(tipSelecionado.equals("sem")){
      gerarRelatorioBarSemVitima();
    }
    if(tipSelecionado.equals("com")){
      gerarRelatorioBarComVitima();
    }
  }

  public void gerarRelatorioBarSemVitima(){
    polarAreaModel = new PolarAreaChartModel();
    ChartData data = new ChartData();


    int value1 = semVitimaList.get(0).getQtAcidente();
    Number v1 = value1;

    int value2 = semVitimaList.get(1).getQtAcidente();
    Number v2 = value2;

    int value3 = semVitimaList.get(2).getQtAcidente();
    Number v3 = value3;

    int value4 = semVitimaList.get(3).getQtAcidente();
    Number v4 = value4;

    PolarAreaChartDataSet dataSet = new PolarAreaChartDataSet();
    List<Number> values = new ArrayList<>();
    values.add(v1);
    values.add(v2);
    values.add(v3);
    values.add(v4);
    dataSet.setData(values);

    List<String> bgColors = new ArrayList<>();
    bgColors.add("rgb(255, 99, 132)");
    bgColors.add("rgb(75, 192, 192)");
    bgColors.add("rgb(255, 205, 86)");
    bgColors.add("rgb(201, 203, 207)");
    dataSet.setBackgroundColor(bgColors);

    data.addChartDataSet(dataSet);
    List<String> labels = new ArrayList<>();
    labels.add("BR-393");
    labels.add("BR-364");
    labels.add("BR-163");
    labels.add("BR-070");
    data.setLabels(labels);

    polarAreaModel.setData(data);
  }

  public void gerarRelatorioBarComVitima(){
    polarAreaModel = new PolarAreaChartModel();
    ChartData data = new ChartData();


    int value1 = comVitimaList.get(0).getQtAcidente();
    Number v1 = value1;

    int value2 = comVitimaList.get(1).getQtAcidente();
    Number v2 = value2;

    int value3 = comVitimaList.get(2).getQtAcidente();
    Number v3 = value3;

    int value4 = comVitimaList.get(3).getQtAcidente();
    Number v4 = value4;

    int value5 = comVitimaList.get(4).getQtAcidente();
    Number v5 = value5;

    int value6 = comVitimaList.get(5).getQtAcidente();
    Number v6 = value6;

    int value7 = comVitimaList.get(6).getQtAcidente();
    Number v7 = value7;

    PolarAreaChartDataSet dataSet = new PolarAreaChartDataSet();
    List<Number> values = new ArrayList<>();
    values.add(v1);
    values.add(v2);
    values.add(v3);
    values.add(v4);
    values.add(v5);
    values.add(v6);
    values.add(v7);
    dataSet.setData(values);

    List<String> bgColors = new ArrayList<>();
    bgColors.add("rgb(255, 99, 132)");
    bgColors.add("rgb(75, 192, 192)");
    bgColors.add("rgb(255, 205, 86)");
    bgColors.add("rgb(201, 203, 207)");
    bgColors.add("rgb(99, 207, 207)");
    bgColors.add("rgb(100, 192, 207)");
    bgColors.add("rgb(233, 193, 207)");
    dataSet.setBackgroundColor(bgColors);

    data.addChartDataSet(dataSet);
    List<String> labels = new ArrayList<>();

    labels.add("BR-448");
    labels.add("BR-364");
    labels.add("BR-070");
    labels.add("BR-163");
    labels.add("BR-393");
    labels.add("BR-101");
    labels.add("BR-290");
    data.setLabels(labels);

    polarAreaModel.setData(data);
  }
}
