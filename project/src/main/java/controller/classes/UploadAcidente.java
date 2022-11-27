package controller.classes;

import DAO.Acidente.AcidenteDAO;
import DAO.Acidente.PgAcidenteDAO;
import DAO.DAOFactory;
import DAO.Rodovia.RodoviaDAO;
import DAO.SentidoRodovia.SentidoRodoviaDAO;
import DAO.TipoAcidente.TipoAcidenteDAO;
import DAO.TipoOcorrencia.TipoOcorrenciaDAO;
import DAO.TrechoRodovia.TrechoRodoviaDAO;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import model.*;
import org.primefaces.model.file.UploadedFile;

import java.io.*;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

public class UploadAcidente {

  public static int isObjectEmpty(String objeto) {
    if(objeto.isEmpty()) {
      return 0;
    } else {
      return parseInt(objeto);
    }
  }

  public static float stringToFloat(String entrada){
    float saida;
    if(entrada.contains(",")){
      String[] entradaSeparada = entrada.split(",");
      saida = (float) (Integer.parseInt(entradaSeparada[0]) + Integer.parseInt(entradaSeparada[1]) * 0.1);
    }
    else{
      saida = Float.parseFloat(entrada);
    }
    return saida;
  }

  public static String separaTrecho(String entrada){
    if(entrada.contains("/")){
      String[] entradaSeparada = entrada.split("/");
      String saida = entradaSeparada[0];
      return saida;
    }
    else{
      return entrada;
    }
  }


  public static void readUploadAcidente(UploadedFile file)
      throws IOException, ParseException, SQLException {
    int nomeColuna = 1;
    int i;
    int j = -1;
    int tamanhoTable;
    byte[] contents = file.getContent();
    CSVParser parserCv = new CSVParserBuilder().withSeparator(';').build();
    CSVReader csvReader = new CSVReaderBuilder((new InputStreamReader(new ByteArrayInputStream(contents))))
        .withCSVParser(parserCv).build();

    List<String[]> table = csvReader.readAll();
    //TODO primeiro ler a linha com os nomes de colunas da table

    tamanhoTable = table.size();
    String[] linha;
    while(j <= tamanhoTable) {
      j++;
      i = 0;
      linha = table.get(j);
      if(nomeColuna == 1){
        j++;
        nomeColuna = 0;
        linha = table.get(j);
      }

      String dataInput = linha[i++];
      SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
      Date data = parser.parse(dataInput);
      Time hora = new Time(new SimpleDateFormat("HH:mm:ss").parse(linha[i++]).getTime());
      Integer nrOcorrencia = isObjectEmpty(linha[i++]);
      String descricaoTipoOcorrencia = linha[i++];
      float km = stringToFloat(linha[i++]);
      String descricaoTrechoRodovia = linha[i++];
      String descricaoSentidoRodovia = linha[i++];
      String descricaoTipoAcidente = linha[i++];
      int automovel = isObjectEmpty(linha[i++]);
      int bicicleta = isObjectEmpty(linha[i++]);
      int caminhao = isObjectEmpty(linha[i++]);
      int moto = isObjectEmpty(linha[i++]);
      int onibus = isObjectEmpty(linha[i++]);
      int outros = isObjectEmpty(linha[i++]);
      int tracaoAnimal = isObjectEmpty(linha[i++]);
      int cargaEspecial = isObjectEmpty(linha[i++]);
      int tratorMaquina = isObjectEmpty(linha[i++]);
      int utilitario = isObjectEmpty(linha[i++]);
      int ileso = isObjectEmpty(linha[i++]);
      int levementeFerido = isObjectEmpty(linha[i++]);
      int moderamenteFerido = isObjectEmpty(linha[i++]);
      int gravementeFerido = isObjectEmpty(linha[i++]);
      int mortos = isObjectEmpty(linha[i++]);
      // TODO criar funções verificarIn*'s


      try(DAOFactory daoFactory = DAOFactory.getInstance()){
        //verificar se ja existe as insercoes em rodovia, trechoRod etc
        //antes de inserir trechorodovia inserir rodovia
          // inserir primeiro rodovia
        //para adicionar um acidente precisamos primeiro buscar os trechos de rodovia já exixtentes e adiiconar outra caso seja necessario
        //porem temos a questão de que não tem o nome da rodovia no csv de adicente, só o trecho
        //preciso pensar o que fazer nessa situação


        if(!(descricaoSentidoRodovia.isEmpty())) {
          SentidoRodovia sentidoRodovia = new SentidoRodovia();
          sentidoRodovia.setDescricaoSentidoRodovia(descricaoSentidoRodovia);
          SentidoRodoviaDAO sentidoRodoviaDAO = daoFactory.getSentidoRodoviaDAO();
          sentidoRodoviaDAO.create(sentidoRodovia);
        }
        if(!(descricaoTipoOcorrencia.isEmpty())){
          TipoOcorrencia tipoOcorrencia = new TipoOcorrencia();
          tipoOcorrencia.setDescricaoTipoOcorrencia(descricaoTipoOcorrencia);
          TipoOcorrenciaDAO tipoOcorrenciaDAO = daoFactory.getTipoOcorrencia();
          tipoOcorrenciaDAO.create(tipoOcorrencia);
        }
        if (!(descricaoTipoAcidente.isEmpty())) {
          TipoAcidente tipoAcidente = new TipoAcidente();
          tipoAcidente.setDescricaoTipoAcidente(descricaoTipoAcidente);
          TipoAcidenteDAO tipoAcidenteDAO = daoFactory.getTipoAcidenteDAO();
          tipoAcidenteDAO.create(tipoAcidente);
        }
        if (data != null && nrOcorrencia != 0 && !(descricaoSentidoRodovia.isEmpty())
        && !(descricaoTipoOcorrencia.isEmpty()) && !(descricaoTipoAcidente.isEmpty())) {
          Acidente acidente = new Acidente();
          acidente.setData(new java.sql.Date(data.getTime()));
          acidente.setHora(hora);
          acidente.setNrOcorrencia(nrOcorrencia);
          acidente.setKm(km);
          acidente.setAutomovel(automovel);
          acidente.setBicicleta(bicicleta);
          acidente.setCaminhao(caminhao);
          acidente.setMoto(moto);
          acidente.setOnibus(onibus);
          acidente.setOutros(outros);
          acidente.setTracaoAnimal(tracaoAnimal);
          acidente.setCargaEspecial(cargaEspecial);
          acidente.setTratorMaquina(tratorMaquina);
          acidente.setUtilitario(utilitario);
          acidente.setIleso(ileso);
          acidente.setLevementeFerido(levementeFerido);
          acidente.setGravementeFerido(gravementeFerido);
          acidente.setMortos(mortos);
          acidente.setIdTipoOcorrencia(descricaoTipoOcorrencia);
          acidente.setIdSentidoRodovia(descricaoSentidoRodovia);
          acidente.setIdTipoAcidente(descricaoTipoAcidente);
          TrechoRodoviaDAO trechoRodoviaDAO = daoFactory.getTrechoRodoviaDAO();
          descricaoTrechoRodovia = separaTrecho(descricaoTrechoRodovia);
          acidente.setIdTrechoRodovia(descricaoTrechoRodovia);
          if(trechoRodoviaDAO.read(descricaoTrechoRodovia) != null) {
            AcidenteDAO acidenteDAO = daoFactory.getAcidenteDAO();
            acidenteDAO.create(acidente);
          }
        }
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      }
      // TODO implementação dos outros
    }
  }
}
