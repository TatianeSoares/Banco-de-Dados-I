package controller.classes;

import DAO.DAOFactory;
import DAO.Rodovia.RodoviaDAO;
import DAO.SentidoRodovia.SentidoRodoviaDAO;
import DAO.Sinalizacao.UltrapassagemDAO;
import DAO.TipoPista.TipoPistaDAO;
import DAO.TrechoRodovia.TrechoRodoviaDAO;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.Getter;
import lombok.Setter;
import model.*;
import org.primefaces.model.file.UploadedFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

public class UploadUltrapassagem {

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

    public static void readUploadUltrapassagem(UploadedFile file)
        throws IOException, ParseException, SQLException, ClassNotFoundException {
        int nomeColuna = 1;
        int i;
        int j = -1;
        int tamanhoTable;
        byte[] contents = file.getContent();
        CSVParser parserCv = new CSVParserBuilder().withSeparator(';').build();
        CSVReader csvReader = new CSVReaderBuilder((new InputStreamReader(new ByteArrayInputStream(contents))))
                .withCSVParser(parserCv).build();
        List<String[]> table = csvReader.readAll();
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
            String descricaoRodovia = linha[i++];
            Integer anoPnvSnc = isObjectEmpty(linha[i++]);
            String uf = linha[i++];
            String descricaoTrechoRodovia = linha[i++];
            String descricaoTipoPista = linha[i++];
            String descricaoSentidoRodovia = linha[i++];
            String direcao = linha[i++];
            String situacao = linha[i++];
            String dataInativacao = linha[i++];
            float kmInicial = stringToFloat(linha[i++]);
            float latitudeInicial = stringToFloat(linha[i++]);
            float longitudeInicial = stringToFloat(linha[i++]);
            float kmFinal = stringToFloat(linha[i++]);
            float latitudeFinal = stringToFloat(linha[i++]);
            float longitudeFinal = stringToFloat(linha[i++]);

            try(DAOFactory daoFactory = DAOFactory.getInstance()){
                if (!(descricaoRodovia.isEmpty())){
                    Rodovia rodovia = new Rodovia();
                    rodovia.setDescricaoRodovia(descricaoRodovia);
                    RodoviaDAO rodoviaDAO = daoFactory.getRodoviaDAO();
                    rodoviaDAO.create(rodovia);
                }
                if (!(descricaoRodovia.isEmpty()) && !(descricaoTrechoRodovia.isEmpty())){
                    TrechoRodovia trechoRodovia = new TrechoRodovia();
                    trechoRodovia.setDescricaoTrechoRodovia(descricaoTrechoRodovia);
                    trechoRodovia.setIdRodovia(descricaoRodovia);
                    TrechoRodoviaDAO trechoRodoviaDAO = daoFactory.getTrechoRodoviaDAO();
                    trechoRodoviaDAO.create(trechoRodovia);
                }
                if(!(descricaoSentidoRodovia.isEmpty())) {
                    SentidoRodovia sentidoRodovia = new SentidoRodovia();
                    sentidoRodovia.setDescricaoSentidoRodovia(descricaoSentidoRodovia);
                    SentidoRodoviaDAO sentidoRodoviaDAO = daoFactory.getSentidoRodoviaDAO();
                    sentidoRodoviaDAO.create(sentidoRodovia);
                }
                if (!(descricaoTipoPista.isEmpty())) {
                    TipoPista tipoPista = new TipoPista();
                    tipoPista.setDescricaoTipoPista(descricaoTipoPista);
                    TipoPistaDAO tipoPistaDAO = daoFactory.getTipoPistaDAO();
                    tipoPistaDAO.create(tipoPista);
                }
                if (!(descricaoRodovia.isEmpty()) && !(descricaoTrechoRodovia.isEmpty())
                    && !(descricaoSentidoRodovia.isEmpty()) && !(descricaoTipoPista.isEmpty())) {
                    Ultrapassagem ultrapassagem = new Ultrapassagem();
                    ultrapassagem.setAnoPnvSnc(anoPnvSnc);
                    ultrapassagem.setUf(uf);
                    ultrapassagem.setSituacao(situacao);
                    ultrapassagem.setKmInicial(kmInicial);
                    ultrapassagem.setLatitudeInicial(latitudeInicial);
                    ultrapassagem.setLongitudeInicial(longitudeInicial);
                    ultrapassagem.setKmFinal(kmFinal);
                    ultrapassagem.setLatitudeFinal(latitudeFinal);
                    ultrapassagem.setLongitudeFinal(longitudeFinal);
                    ultrapassagem.setIdTrechoRodovia(descricaoTrechoRodovia);
                    ultrapassagem.setIdSentidoRodovia(descricaoSentidoRodovia);
                    ultrapassagem.setIdTipoPista(descricaoTipoPista);
                    UltrapassagemDAO ultrapassagemDAO = daoFactory.getUltrapassagemDAO();
                    ultrapassagemDAO.create(ultrapassagem);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
