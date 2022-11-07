package controller;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.file.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class inputController implements Serializable {

  @Getter @Setter
  private UploadedFile file;
  private String escolha;

  public void init(){

  }

  public void verificarArquivo(){

  }

  public void upload(){
    if(file != null){
      FacesMessage message = new FacesMessage("Sucesso", file.getFileName() + " upload está feito.");
      FacesContext.getCurrentInstance().addMessage(null, message);
    }

  }
}
