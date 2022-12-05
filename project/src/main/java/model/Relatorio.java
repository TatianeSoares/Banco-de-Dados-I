package model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

public class Relatorio {

  @Getter @Setter private String trechoRodovia;
  @Getter @Setter private String tipoOcorrencia;
  @Getter @Setter private String tipoAcidente;
  @Getter @Setter private Date dataAcidente;
  @Getter @Setter private Integer qtAcidente;
  @Getter @Setter private Integer qtUltrapassagem;
  @Getter @Setter private Integer qtVelocidade;
}
