package model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TipoPista {

    //rodovia.tipoPista
    @NotNull
    private Integer idTipoPista;
    @Size(max=30)
    private String descricaoTipoPista;
}
