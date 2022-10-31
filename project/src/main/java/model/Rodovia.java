package model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Rodovia {

    //rodovia.rodovias
    @NotNull
    @Getter @Setter private Integer idRodovia;
    @Size(max=30)
    @Getter @Setter private String descricaoRodovia;

}
