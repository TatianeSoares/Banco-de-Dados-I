package model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SentidoRodovia {

    //rodovia.sentidoRodovia
    @NotNull
    @Getter @Setter private Integer idSentidoRodovia;
    @Size(max=30)
    @Getter @Setter private String descricaoSentidoRodovia;

}
