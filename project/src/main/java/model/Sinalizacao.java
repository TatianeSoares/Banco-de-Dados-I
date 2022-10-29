package model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Sinalizacao {
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

}
