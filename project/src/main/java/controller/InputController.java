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
import java.sql.SQLException;
import java.text.ParseException;

@Named
@ViewScoped
public class InputController implements Serializable {

  @Getter @Setter private UploadedFile file;
  @Getter @Setter private String tipSelecionado;
  @Getter @Setter private int tipo = 0;

  public void init() {

  }

  public void setType(String tipSelecionado){
    tipSelecionado = this.tipSelecionado;
  }

  public void uploadFile(FileUploadEvent fileUploadEvent)
      throws IOException, SQLException, ParseException {
      UploadedFile file = fileUploadEvent.getFile();

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
  }
}
