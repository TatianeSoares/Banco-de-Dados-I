package model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

public class Acidente {

    //rodovia.acidente
    // PK = (data, nrOcorrencia)
    @NotNull
    private LocalDate data;
    private LocalTime hora;
    @NotNull
    private Integer nrOcorrencia;
    private float km;
    private int automovel;
    private int bicicleta;
    private int caminhao;
    private int moto;
    private int onibus;
    private int outros;
    private int tracaoAnimal;
    private int cargaEspecial;
    private int tratorMaquina;
    private int utilitario;
    private Integer ileso;
    private Integer levementeFerido;
    private Integer gravementeFerido;
    private Integer mortos;
    private Integer idTrechoRodovia;
    private Integer idSentidoRodovia;
    private Integer idTipoOcorrencia;
    private Integer idTipoAcidente;

}