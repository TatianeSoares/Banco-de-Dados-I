package model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Rodovia {

    //rodovia.rodovias
    @NotNull
    private Integer idRodovia;
    @Size(max=30)
    private String descricaoRodovia;

    //rodovia.trechoRodovia
    @NotNull
    private Integer idTrechoRodovia;
    @Size(max=30)
    private String descricaoTrechoRodovia;

    //rodovia.sentidoRodovia
    @NotNull
    private Integer idSentidoRodovia;
    @Size(max=30)
    private String descricaoSentidoRodovia;

    //rodovia.tipoPista
    @NotNull
    private Integer idTipoPista;
    @Size(max=30)
    private String descricaoTipoPista;

}
