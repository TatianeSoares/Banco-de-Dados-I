package model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SentidoRodovia {

    //rodovia.sentidoRodovia
    @NotNull
    private Integer idSentidoRodovia;
    @Size(max=30)
    private String descricaoSentidoRodovia;

}
