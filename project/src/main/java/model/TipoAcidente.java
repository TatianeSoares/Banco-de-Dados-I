package model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TipoAcidente {

    //rodovia.tipoAcidente
    @NotNull
    @Getter @Setter private Integer idTipoAcidente;
    @Size(max=50)
    @Getter @Setter private String descricaoTipoAcidente;

}
