package controller;

import DAO.DAOFactory;
import DAO.TrechoRodovia.TrechoRodoviaDAO;
import controller.classes.UploadAcidente;
import controller.classes.UploadUltrapassagem;
import controller.classes.UploadVelocidadeMaxima;
import lombok.Getter;
import lombok.Setter;
import model.Rodovia;
import model.TrechoRodovia;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.*;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

@Named
@ViewScoped
public class InputController implements Serializable {

  @Getter @Setter private UploadedFile file;
  @Getter @Setter private String tipSelecionado;

  public void init() {

  }

  public void setType(String tipSelecionado){

    tipSelecionado = this.tipSelecionado;
  }

  public void uploadFile(FileUploadEvent fileUploadEvent)
      throws IOException, SQLException, ParseException, ClassNotFoundException {
      UploadedFile file = fileUploadEvent.getFile();

    /* Inserting Time in Carga */
    Calendar date = Calendar.getInstance();
    int hora = date.get(Calendar.HOUR_OF_DAY);
    int minutos = date.get(Calendar.MINUTE);
    int segundos = date.get(Calendar.SECOND);
    String timeInString = hora + ":" + minutos + ":" + segundos;
    timeInString = null;
//    carga.setHora_carga(Time.valueOf(timeInString));
//    logger.info("Hora da carga: " + carga.getHora_carga());

      if (file.getFileName() != null) {
        if (tipSelecionado.equals("acidente")) {
          UploadAcidente.readUploadAcidente(file);
        }
        if (tipSelecionado.equals("ultrapassagem")) {
          UploadUltrapassagem.readUploadUltrapassagem(file);
        }
        if (tipSelecionado.equals("velocidade")) {
          UploadVelocidadeMaxima.readUploadVelocidadeMaxima(file);
        }
      }

    tipSelecionado = null;
  }
}
