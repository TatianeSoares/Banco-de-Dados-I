package model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class VelocidadeMaxima {

    //rodovia.velocidadeMaxima
    @NotNull
    @Getter @Setter private Integer idVelocidadeMaxima;
    @Size(max=30)
    @Getter @Setter private String situacao;
    @Size(max=2)
    @Getter @Setter private String uf;
    @Getter @Setter private Integer anoPnvSnc;
    @Getter @Setter private float veloVeicPesado;
    @Getter @Setter private float veloVeicLeve;
    @Getter @Setter private float latitude;
    @Getter @Setter private float longitude;
    @Size(max=40)
    @Getter @Setter private String municipio;
    @Getter @Setter private float km;
    @Getter @Setter private String idTrechoRodovia;
    @Getter @Setter private String idSentidoRodovia;
    @Getter @Setter private String idTipoPista;

}