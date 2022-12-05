package controller;

import DAO.Acidente.AcidenteDAO;
import DAO.DAOFactory;
import DAO.Sinalizacao.UltrapassagemDAO;
import DAO.Sinalizacao.VelocidadeMaximaDAO;
import DAO.TrechoRodovia.TrechoRodoviaDAO;
import lombok.Getter;
import lombok.Setter;
import model.Acidente;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

    @Getter @Setter private PieChartModel pieModel;
//    @Getter @Setter private BarChartModel barModel2;
    @Getter @Setter private String rodoviaSelecionada;
    @Getter @Setter private String relatorioSelecionado;

    public void init() {

    }

    public void setType(String relatorioSelecionado){

        relatorioSelecionado = this.relatorioSelecionado;
    }

    public void gerarRelatorio() throws Exception {
        if(relatorioSelecionado.equals("obitos")){
            getDataVelocidadeObitos();
        }
        if(relatorioSelecionado.equals("compara")){
            gerarVelocidadeXUltrapassagem();
        }
 
    }

    public void gerarVelocidadeXUltrapassagem() throws SQLException, IOException, ClassNotFoundException {
        try(DAOFactory daoFactory = DAOFactory.getInstance()){
            VelocidadeMaximaDAO velocidadeMaximaDAO = daoFactory.getVelocidadeMaximaDAO();
            UltrapassagemDAO ultrapassagemDAO = daoFactory.getUltrapassagemDAO();
            TrechoRodoviaDAO trechoRodoviaDAO = daoFactory.getTrechoRodoviaDAO();
            AcidenteDAO acidenteDAO = daoFactory.getAcidenteDAO();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getDataVelocidadeObitos() throws Exception {

        List<Acidente> acidenteList;
        List<Float> kmList = new ArrayList<>();
        float trecho40, trecho60, trecho80;

        try(DAOFactory daoFactory = DAOFactory.getInstance()){
            VelocidadeMaximaDAO velocidadeMaximaDAO = daoFactory.getVelocidadeMaximaDAO();
            TrechoRodoviaDAO trechoRodoviaDAO = daoFactory.getTrechoRodoviaDAO();
            AcidenteDAO acidenteDAO = daoFactory.getAcidenteDAO();
            acidenteList = acidenteDAO.getAcidentesFataisProximos1Km();

            gerarVelocidadeObitos(acidenteList);
        }
    }

    private void gerarVelocidadeObitos(List<Acidente> acidenteList){

        pieModel = new PieChartModel();
        int i = 0;
        for(Acidente acidente : acidenteList){
            if(i == 5){
                break;
            }

            pieModel.set(String.valueOf(acidente.getKm()), acidente.getMortos() + i);
            i++;
        }
        pieModel.setTitle("Relação de velocidades regulamentadas com acidentes graves");
        pieModel.setLegendPosition("e");
        pieModel.setFill(true);
        pieModel.setShowDataLabels(true);
        pieModel.setDiameter(150);
    }


}
