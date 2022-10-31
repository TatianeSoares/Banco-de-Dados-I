package model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

public class Acidente {

    //rodovia.acidente
    // PK = (data, nrOcorrencia)

    @NotNull
    @Getter @Setter private LocalDate data;
    @Getter @Setter private LocalTime hora;
    @NotNull
    @Getter @Setter private Integer nrOcorrencia;
    @Getter @Setter private float km;
    @Getter @Setter private int automovel;
    @Getter @Setter private int bicicleta;
    @Getter @Setter private int caminhao;
    @Getter @Setter private int moto;
    @Getter @Setter private int onibus;
    @Getter @Setter private int outros;
    @Getter @Setter private int tracaoAnimal;
    @Getter @Setter private int cargaEspecial;
    @Getter @Setter private int tratorMaquina;
    @Getter @Setter private int utilitario;
    @Getter @Setter private Integer ileso;
    @Getter @Setter private Integer levementeFerido;
    @Getter @Setter private Integer gravementeFerido;
    @Getter @Setter private Integer mortos;
    @Getter @Setter private Integer idTrechoRodovia;
    @Getter @Setter private Integer idSentidoRodovia;
    @Getter @Setter private Integer idTipoOcorrencia;
    @Getter @Setter private Integer idTipoAcidente;

}