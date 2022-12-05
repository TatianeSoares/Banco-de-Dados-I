package controller;

import DAO.Acidente.AcidenteDAO;
import DAO.DAOFactory;
import DAO.PgDAOFactory;
import DAO.Sinalizacao.VelocidadeMaximaDAO;
import DAO.TrechoRodovia.TrechoRodoviaDAO;
import lombok.Getter;
import lombok.Setter;
import model.Acidente;
import model.TrechoRodovia;
import model.VelocidadeMaxima;
import org.primefaces.model.chart.PieChartModel;

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

    @Getter @Setter
    private PieChartModel pieModel;
    @Getter @Setter private String rodoviaSelecionada;
    @Getter @Setter private String relatorioSelecionado;

    public void init() {

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
