package model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TrechoRodovia {

    //rodovia.trechoRodovia
    @NotNull
    @Getter @Setter private Integer idTrechoRodovia;
    @Size(max=30)
    @Getter @Setter private String descricaoTrechoRodovia;

}
