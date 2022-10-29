package model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TipoAcidente {

    //rodovia.tipoAcidente
    @NotNull
    private Integer idTipoAcidente;
    @Size(max=50)
    private String descricaoTipoAcidente;

}
