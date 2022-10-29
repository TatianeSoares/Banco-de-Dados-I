package model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Rodovia {

    //rodovia.rodovias
    @NotNull
    private Integer idRodovia;
    @Size(max=30)
    private String descricaoRodovia;

    private Integer idTrechoRodovia;

    private Integer idSentidoRodovia;

    private Integer idTipoPista;

}
