package controller;

import DAO.Acidente.AcidenteDAO;
import DAO.DAOFactory;
import DAO.Relatorio.RelatorioDAO;
import DAO.Sinalizacao.UltrapassagemDAO;
import DAO.Sinalizacao.VelocidadeMaximaDAO;
import DAO.TrechoRodovia.TrechoRodoviaDAO;
import lombok.Getter;
import lombok.Setter;
import model.Acidente;
import model.Relatorio;
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
    @Getter @Setter private String rodoviaSelecionada;
    @Getter @Setter private String relatorioSelecionado;
    @Getter @Setter private List<Relatorio> listTAT;
    @Getter @Setter private List<Relatorio> listAUV;
    @Getter @Setter private Boolean flgPie;
    @Getter @Setter private Boolean flgBar ;
    @Getter @Setter private BarChartModel barModel2;

    public void init() {
        listAUV = new ArrayList<>();
        listTAT = new ArrayList<>();
    }

    public void setType(String relatorioSelecionado){

        relatorioSelecionado = this.relatorioSelecionado;
    }

    public void gerarRelatorio() throws Exception {
//        if(relatorioSelecionado.equals("feridos")){
            getDataVelocidadeObitos();
//        }
//        if(relatorioSelecionado.equals("quantidadeAciUlVel")){
            gerarVelocidadeXUltrapassagem();
//        }
//        if(relatorioSelecionado.equals("acidenteTipo")){
//            gerarRelatorioAcidentePorTipo();
//        }

    }
    //<div class="card">
//    <h:form>
//        <p:growl id="growl" showDetail="true"/>
//

//    </h:form>
//</div>
    public void gerarVelocidadeXUltrapassagem() throws SQLException, IOException, ClassNotFoundException {
        barModel2 = new BarChartModel();
        ChartData data = new ChartData();
        Number value;

        try(DAOFactory daoFactory = DAOFactory.getInstance()){
            RelatorioDAO relatorioDAO = daoFactory.getRelatorioDAO();
            //auv retorna o trecho de rodovia qt aci, e placas
            listAUV = relatorioDAO.all();
            BarChartDataSet barDataSet = new BarChartDataSet();
            barDataSet.setLabel("Acidentes");
            barDataSet.setBackgroundColor("rgba(255, 99, 132, 0.2)");
            barDataSet.setBorderColor("rgb(255, 99, 132)");
            barDataSet.setBorderWidth(1);

            List<Number> acidentes = new ArrayList<>();
            for(int i = 0; i < 5; i++){
                acidentes.add(listAUV.get(i).getQtAcidente());
            }

            barDataSet.setData(acidentes);

            BarChartDataSet barDataSet2 = new BarChartDataSet();
            barDataSet2.setLabel("Ultrapassagem");
            barDataSet2.setBackgroundColor("rgba(255, 159, 64, 0.2)");
            barDataSet2.setBorderColor("rgb(255, 159, 64)");
            barDataSet2.setBorderWidth(1);

            List<Number> ultrapassagem = new ArrayList<>();
            for(int i = 0; i < 5; i++){
                ultrapassagem.add(listAUV.get(i).getQtUltrapassagem());
            }

            barDataSet.setData(ultrapassagem);

            BarChartDataSet barDataSet3 = new BarChartDataSet();
            barDataSet3.setLabel("Velocidade Máxima");
            barDataSet3.setBackgroundColor("rgba(255, 99, 132, 0.2)");
            barDataSet3.setBorderColor("rgb(255, 99, 64)");
            barDataSet3.setBorderWidth(1);

            List<Number> velocidade = new ArrayList<>();
            for(int i = 0; i < 5; i++){
                velocidade.add(listAUV.get(i).getQtVelocidade());
            }

            barDataSet.setData(velocidade);

            data.addChartDataSet(barDataSet);
            data.addChartDataSet(barDataSet2);
            data.addChartDataSet(barDataSet3);

//            List<String> labels = new ArrayList<>();
//            labels.add("January");
//            labels.add("February");
//            labels.add("March");
//            labels.add("April");
//            labels.add("May");
//            labels.add("June");
//            labels.add("July");
//            data.setLabels(labels);
//            barModel2.setLegendLabel(data);
//                setData(data);

        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void getDataVelocidadeObitos() throws Exception {

        List<Integer> countList = new ArrayList<>();

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
