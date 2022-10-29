package model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TipoOcorrencia {

    //rodovia.tipoOcorrencia
    @NotNull
    private Integer idTipoOcorrencia;
    @Size(max=30)
    private String descricaoTipoOcorrencia;

}
