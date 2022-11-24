package controller.classes;

import DAO.Sinalizacao.UltrapassagemDAO;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
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

            //verificar se existe já a rodovia com uma função
            String descricaoRodovia = linha[i++];

            Integer anoPnvSnc = isObjectEmpty(linha[i++]);
            String uf = linha[i++];
            //verificar se ja existe
            String descricaoTrechoRodovia = linha[i++];
            //verificar se ja existe
            String descricaoTipoPista = linha[i++];
            //verificar se ja existe
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

            // TODO retornar ja existente ou criar
            //Rodovia rodovia = new Rodovia();
            //rodovia.setDescricaoRodovia(descricaoRodovia);
            //TrechoRodovia trechoRodovia = new TrechoRodovia();
            //trechoRodovia.setDescricaoTrechoRodovia(descricaoTrechoRodovia);
            //SentidoRodovia sentidoRodovia = new SentidoRodovia();
            //sentidoRodovia.setDescricaoSentidoRodovia(descricaoSentidoRodovia);
            //TipoPista tipoPista = new TipoPista();
            //tipoPista.setDescricaoTipoPista(descricaoTipoPista);


            if ( descricaoRodovia != null && anoPnvSnc != 0 && uf != null && descricaoTipoPista != null && kmInicial >= 0 && descricaoTrechoRodovia != null && descricaoSentidoRodovia != null && kmFinal > kmInicial) {
                // TODO implementação dos outros
                UltrapassagemDAO.adicionarUltrapassagem(ultrapassagem);
                //TipoAcidenteDAO.adicionarTipoAcidente(tipoAcidente);
                //TipoOcorrenciaDAO.adicionarTipoOcorrencia(tipoOcorrencia);
            }
        }
    }

}
