package model;

import javax.validation.constraints.Size;

public class VelocidadeMaxima {

    //rodovia.velocidadeMaxima
    private float veloVeicPesado;
    private float veloVeicLeve;
    private float latitude;
    private float longitude;
    @Size(max=40)
    private String municipio;
    private float km;

}
