package model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TrechoRodovia {

    //rodovia.trechoRodovia
    @Size(max=30) @NotEmpty
    @Getter @Setter private String idRodovia;
    @Size(max=30) @NotEmpty
    @Getter @Setter private String descricaoTrechoRodovia;

}
