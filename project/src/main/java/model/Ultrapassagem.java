package model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Ultrapassagem {

    //rodovia.ultrapassagem
    @NotNull
    @Getter @Setter private Integer idUltrapassagem;
    @Size(max=30)
    @Getter @Setter private String situacao;
    @Size(max=2)
    @Getter @Setter private String uf;
    @Getter @Setter private Integer anoPnvSnc;
    @Getter @Setter private float kmInicial;
    @Getter @Setter private float kmFinal;
    @Getter @Setter private float longitudeFinal;
    @Getter @Setter private float longitudeInicial;
    @Getter @Setter private float latitudeFinal;
    @Getter @Setter private float latitudeInicial;
    @Getter @Setter private Integer idTrechoRodovia;
    @Getter @Setter private Integer idSentidoRodovia;
    @Getter @Setter private Integer idTipoPista;

}
