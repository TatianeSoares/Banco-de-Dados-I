package controller.classes;

import DAO.Acidente.AcidenteDAO;
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

  public static void readUploadAcidente(UploadedFile file) throws IOException {

    String fileName = file.getFileName();
    byte[] contents = file.getContent();
    CSVParser parserCv = new CSVParserBuilder().withSeparator(';').build();
    CSVReader csvReader = new CSVReaderBuilder((new InputStreamReader(new ByteArrayInputStream(contents))))
        .withCSVParser(parserCv).build();

    List<String[]> table = csvReader.readAll();

//    BufferedReader br = new BufferedReader(new FileReader(file.getFileName()));
//    String line = br.readLine(); // pega o cabeçalho
//    line = br.readLine();   // primeira linha com conteúdo
//    while(line != null){
//      String[] info = line.split(";");
//
//        // Data
//        String dataInput = info[0];
//        SimpleDateFormat parser = new SimpleDateFormat();
//        Date data = parser.parse(dataInput);
//        // Horário
//        Time hora = new Time(new SimpleDateFormat("HH:mm:ss").parse(info[1]).getTime());
//        // Numero da ocorrencia
//        Integer nrOcorrencia = parseInt(info[2]);
//        // Tipo de ocorrencia *nova tabela
//        String descricaoTipoOcorrencia = info[3];
//        // Km
//        Float km = Float.parseFloat(info[4]);
//        // Trecho Rodovia *nova tabela
//        String descricaoTrechoRodovia = info[5];
//        // Sentido Rodovia *nova tabela
//        String descricaoSentidoRodovia = info[6];
//        // Tipo de acidente *nova tabela
//        String descricaoTipoAcidente = info[7];
//        // Automovel
//        int automovel = parseInt(info[8]);
//        // Bicicleta
//        int bicicleta = parseInt(info[9]);
//        // Caminhao
//        int caminhao = parseInt(info[10]);
//        // Moto
//        int moto = parseInt(info[11]);
//        // Onibus
//        int onibus = parseInt(info[12]);
//        // Outros
//        int outros = parseInt(info[13]);
//        // Tracao animal
//        int tracaoAnimal = parseInt(info[14]);
//        // Transporte de cargas especiais
//        int cargaEspecial = parseInt(info[15]);
//        // Trator Maquina
//        int tratorMaquina = parseInt(info[16]);
//        // Utilitario
//        int utilitario = parseInt(info[17]);
//        // Ileso
//        int ileso = parseInt(info[18]);
//        // Levemente Ferido
//        int levementeFerido = parseInt(info[19]);
//        // Moderamente Ferido *não utilizado
//        int moderamenteFerido = parseInt(info[20]);
//        // Gravemente Ferido
//        int gravementeFerido = parseInt(info[21]);
//        // Mortos
//        int mortos = parseInt(info[22]);
//
//        Acidente acidente = new Acidente();
//        acidente.setData((java.sql.Date) data);
//        acidente.setHora(hora);
//        acidente.setNrOcorrencia(nrOcorrencia);
//        acidente.setKm(km);
//        acidente.setAutomovel(automovel);
//        acidente.setBicicleta(bicicleta);
//        acidente.setCaminhao(caminhao);
//        acidente.setMoto(moto);
//        acidente.setOnibus(onibus);
//        acidente.setOutros(outros);
//        acidente.setTracaoAnimal(tracaoAnimal);
//        acidente.setCargaEspecial(cargaEspecial);
//        acidente.setTratorMaquina(tratorMaquina);
//        acidente.setUtilitario(utilitario);
//        acidente.setIleso(ileso);
//        acidente.setLevementeFerido(levementeFerido);
//        acidente.setGravementeFerido(gravementeFerido);
//        acidente.setMortos(mortos);
//        // TODO criar funções verificarIn*'s
//        TrechoRodovia trechoRodovia = new TrechoRodovia();
//        trechoRodovia.setDescricaoTrechoRodovia(descricaoTrechoRodovia);
//
//        SentidoRodovia sentidoRodovia = new SentidoRodovia();
//        sentidoRodovia.setDescricaoSentidoRodovia(descricaoSentidoRodovia);
//
//        acidente.setIdTrechoRodovia(trechoRodovia.getIdTrechoRodovia());
//
//        TipoOcorrencia tipoOcorrencia = new TipoOcorrencia();
//        tipoOcorrencia.setDescricaoTipoOcorrencia(descricaoTipoOcorrencia);
//
//        TipoAcidente tipoAcidente = new TipoAcidente();
//        tipoAcidente.setDescricaoTipoAcidente(descricaoTipoAcidente);
//
//        if(data != null && hora != null && nrOcorrencia != null && descricaoTipoOcorrencia != null && km != null && descricaoTrechoRodovia != null && descricaoSentidoRodovia != null && descricaoTipoAcidente != null){
//          // TODO implementação dos outros
//          AcidenteDAO.adicionarAcidente(acidente);
//          //TipoAcidenteDAO.adicionarTipoAcidente(tipoAcidente);
//          //TipoOcorrenciaDAO.adicionarTipoOcorrencia(tipoOcorrencia);
//        }
//
//        line = br.readLine();
//      }
  }
}
