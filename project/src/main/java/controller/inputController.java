package controller;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.file.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

@Named
@ViewScoped
public class inputController implements Serializable {

  @Getter @Setter
  private UploadedFile file;
  private String type;

  public void init(){
    createTypeList();
  }

  public List<String> createTypeList(){
    ArrayList<String> typeList = new ArrayList<>();

    typeList.add("Acidente");
    typeList.add("Ultrapassagem");
    typeList.add("Velocidade Máxima");

    return typeList;
  }

  public void verificarArquivo(){

  }

  public void readUploadAcidente(){
    try {
      BufferedReader br = new BufferedReader(new FileReader(file.getFileName()));

      String line = br.readLine(); // pega o cabeçalho
      line = br.readLine();   // primeira linha com conteúdo
      while(line != null){
        String[] info = line.split(";");
        // Data
        String dataInput = info[0];
        SimpleDateFormat parser = new SimpleDateFormat();
        Date data = parser.parse(dataInput);

        // Horário
        Time horario = Time.pars

        // Numero da ocorrencia
        Integer nrOcorrencia = parseInt(info[2]);

        // Tipo de ocorrencia *nova tabela
        String tipoOcorrencia = info[3];

        // Km
        String km = info[4];

        // Trecho
        String trecho = info[5];

        // Sentido
        String sentido = info[6];

        // Tipo de acidente *nova tabela
        String tipoAcidente = info[7];

        // Automovel
        int automovel = parseInt(info[8]);

        // Bicicleta
        int bicicleta = parseInt(info[9]);

        // Caminhao
        int caminhao = parseInt(info[10]);

        // Moto
        int moto = parseInt(info[11]);

        // Onibus
        int onibus = parseInt(info[12]);

        // Outros
        int outros = parseInt(info[13]);

        // Tracao animal
        int tracaoAnimal = parseInt(info[14]);

        // Transporte de cargas especiais
        int cargaEspecial = parseInt(info[15]);

        // Trator Maquina
        int tratorMaquina = parseInt(info[16]);

        // Utilitario
        int utilitario = parseInt(info[17]);

        // Ileso
        int ileso = parseInt(info[18]);

        // Levemente Ferido
        int levementeFerido = parseInt(info[19]);

        // Moderamente Ferido *não utilizado
        int moderamenteFerido = parseInt(info[20]);

        // Gravemente Ferido
        int gravementeFerido = parseInt(info[21]);

        // Mortos
        int mortos = parseInt(info[22]);

        Acidente acidente = new Acidente();






      }



    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }


  }

  public void upload(){

    try{
      BufferedReader br = new BufferedReader(new FileReader(file.getFileName()));
      if(file != null){
        FacesMessage message = new FacesMessage("Sucesso", file.getFileName() + " upload está feito.");
        FacesContext.getCurrentInstance().addMessage(null, message);

        if(type.equals("Acidente")){
          readUploadAcidente();
        }
        else if(type.equals("Ultrapassagem")){
          readUploadUltrapassagem();
        }
        else if(type.equals("Velocidade Máxima")){
          readUploadVelocidadeMaxima();
        }



      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }

  }
}
