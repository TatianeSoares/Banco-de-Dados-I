package model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TipoPista {

    //rodovia.tipoPista
    @NotNull
    @Getter @Setter private Integer idTipoPista;
    @Size(max=30)
    @Getter @Setter private String descricaoTipoPista;
}
