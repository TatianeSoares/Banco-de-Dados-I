package controller.classes;

import DAO.Sinalizacao.VelocidadeMaximaDAO;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import model.VelocidadeMaxima;
import org.primefaces.model.file.UploadedFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static java.lang.Integer.parseInt;

public class UploadVelocidadeMaxima {
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


    public static void readUploadVelocidadeMaxima(UploadedFile file)
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
            if (nomeColuna == 1) {
                j++;
                nomeColuna = 0;
                linha = table.get(j);
            }

            //verificar se existe já a rodovia com uma função
            String descricaoRodovia = linha[i++];

            Integer anoPnvSnc = isObjectEmpty(linha[i++]);
            String descricaoTrechoRodovia = linha[i++];
            float km = stringToFloat(linha[i++]);
            String descricaoTipoPista = linha[i++];
            String uf = linha[i++];
            String municipio = linha[i++];
            String descricaoSentidoRodovia = linha[i++];
            String direcao = linha[i++];
            float latitude = stringToFloat(linha[i++]);
            float longitude = stringToFloat(linha[i++]);
            float veloVeicLeve = stringToFloat(linha[i++]);
            float veloVeicPesado = stringToFloat(linha[i++]);
            String situacao = linha[i++];
            String dataInativacao = linha[i++];

            VelocidadeMaxima velocidadeMaxima = new VelocidadeMaxima();
            velocidadeMaxima.setAnoPnvSnc(anoPnvSnc);
            velocidadeMaxima.setKm(km);
            velocidadeMaxima.setMunicipio(municipio);
            velocidadeMaxima.setLatitude(latitude);
            velocidadeMaxima.setLongitude(longitude);
            velocidadeMaxima.setVeloVeicLeve(veloVeicLeve);
            velocidadeMaxima.setVeloVeicPesado(veloVeicPesado);
            velocidadeMaxima.setSituacao(situacao);

            // TODO retornar ja existente ou criar
            //Rodovia rodovia = new Rodovia();
            //rodovia.setDescricaoRodovia(descricaoRodovia);
            //TrechoRodovia trechoRodovia = new TrechoRodovia();
            //trechoRodovia.setDescricaoTrechoRodovia(descricaoTrechoRodovia);
            //SentidoRodovia sentidoRodovia = new SentidoRodovia();
            //sentidoRodovia.setDescricaoSentidoRodovia(descricaoSentidoRodovia);
            //TipoPista tipoPista = new TipoPista();
            //tipoPista.setDescricaoTipoPista(descricaoTipoPista);

        }


    }

}
