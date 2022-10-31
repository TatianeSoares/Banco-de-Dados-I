package model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TipoOcorrencia {

    //rodovia.tipoOcorrencia
    @NotNull
    @Getter @Setter private Integer idTipoOcorrencia;
    @Size(max=30)
    @Getter @Setter private String descricaoTipoOcorrencia;

}
