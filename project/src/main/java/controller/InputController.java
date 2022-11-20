package controller;

import controller.classes.UploadAcidente;
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
  @Getter @Setter private String tipoSelecionado;

  public void init() {

  }

  public void uploadFile(FileUploadEvent fileUploadEvent)
      throws IOException, SQLException, ParseException {
      UploadedFile file = fileUploadEvent.getFile();

      if (file.getFileName() != null) {
        UploadAcidente.readUploadAcidente(file);

        //TODO verificar o tipoSelecionado, está retornando null
      }
  }
}
