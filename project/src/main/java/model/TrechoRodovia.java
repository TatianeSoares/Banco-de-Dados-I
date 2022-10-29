package model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TrechoRodovia {

    //rodovia.trechoRodovia
    @NotNull
    private Integer idTrechoRodovia;
    @Size(max=30)
    private String descricaoTrechoRodovia;

}
