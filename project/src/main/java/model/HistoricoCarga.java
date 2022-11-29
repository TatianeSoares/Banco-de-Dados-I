package model;

import lombok.Getter;
import lombok.Setter;
import java.sql.Time;
import java.sql.Date;

public class HistoricoCarga {

  @Getter @Setter private Date dataCarga;
  @Getter @Setter private Time horaCarga;
  @Getter @Setter private String tipoCarga;
}
