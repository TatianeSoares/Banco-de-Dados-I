package controller;

import controller.classes.UploadAcidente;
import controller.classes.UploadUltrapassagem;
import controller.classes.UploadVelocidadeMaxima;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.*;
import java.util.Calendar;

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
      throws Exception {
      UploadedFile file = fileUploadEvent.getFile();

    Calendar date = Calendar.getInstance();
    int dia = date.get(Calendar.DAY_OF_MONTH);
    int mes = date.get(Calendar.MONTH) + 1;
    int ano = date.get(Calendar.YEAR);
    int semana = date.get(Calendar.DAY_OF_WEEK);
    int hora = date.get(Calendar.HOUR_OF_DAY);
    int minutos = date.get(Calendar.MINUTE);
    int segundos = date.get(Calendar.SECOND);
    String dataString = dia + "/" + mes + "/" + ano;
    String horaString = hora + ":" + minutos + ":" + segundos;


      if (file.getFileName() != null) {
        if (tipSelecionado.equals("acidente")) {
          UploadAcidente.readUploadAcidente(file, dataString, horaString, tipSelecionado);
        }
        if (tipSelecionado.equals("ultrapassagem")) {
          UploadUltrapassagem.readUploadUltrapassagem(file, dataString, horaString, tipSelecionado);
        }
        if (tipSelecionado.equals("velocidade")) {
          UploadVelocidadeMaxima.readUploadVelocidadeMaxima(file, dataString, horaString, tipSelecionado);
        }
      }

    tipSelecionado = null;
  }
}
