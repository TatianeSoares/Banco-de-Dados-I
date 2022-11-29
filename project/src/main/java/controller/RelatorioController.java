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

        List<Acidente> acidenteList;
        float trecho40, trecho60, trecho80;

        try(DAOFactory daoFactory = DAOFactory.getInstance()){
            VelocidadeMaximaDAO velocidadeMaximaDAO = daoFactory.getVelocidadeMaximaDAO();
            TrechoRodoviaDAO trechoRodoviaDAO = daoFactory.getTrechoRodoviaDAO();
            AcidenteDAO acidenteDAO = daoFactory.getAcidenteDAO();
            acidenteList = acidenteDAO.all();

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
        pieModel.setTitle("Relação de Velocidades regulamentadas com acidentes graves");
        pieModel.setLegendPosition("e");
        pieModel.setFill(true);
        pieModel.setShowDataLabels(true);
        pieModel.setDiameter(150);
    }


}
