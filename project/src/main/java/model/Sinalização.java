package model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Sinalização {
    // Em comum
    @NotNull
    private Integer idSinalizacao;
    @Size(max=30)
    private String situacao;
    @Size(max=2)
    private String uf;
    private Integer anoPnvSnc;
    private Integer idTrechoRodovia;
    private Integer idSentidoRodovia;
    private Integer idTipoPista;

    //rodovia.velocidadeMaxima
    private float veloVeicPesado;
    private float veloVeicLeve;
    private float latitude;
    private float longitude;
    @Size(max=40)
    private String municipio;
    private float km;

    //rodovia.ultrapassagem
    private float kmInicial;
    private float kmFinal;
    private float longitudeFinal;
    private float longitudeInicial;
    private float latitudeFinal;
    private float latitudeInicial;
}
