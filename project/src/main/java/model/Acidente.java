package model;

import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
import java.sql.Time;
import javax.validation.constraints.NotNull;

public class Acidente {
    @NotNull
    @Getter @Setter private Integer idAcidente;
    @NotNull
    @Getter @Setter private Date data;
    @Getter @Setter private Time hora;
    @NotNull
    @Getter @Setter private Integer nrOcorrencia;
    @Getter @Setter private float km;
    @Getter @Setter private int automovel;
    @Getter @Setter private int bicicleta;
    @Getter @Setter private int caminhao;
    @Getter @Setter private int moto;
    @Getter @Setter private int onibus;
    @Getter @Setter private int outros;
    @Getter @Setter private int tracaoAnimal;
    @Getter @Setter private int cargaEspecial;
    @Getter @Setter private int tratorMaquina;
    @Getter @Setter private int utilitario;
    @Getter @Setter private Integer ileso;
    @Getter @Setter private Integer levementeFerido;
    @Getter @Setter private Integer gravementeFerido;
    @Getter @Setter private Integer mortos;
    @Getter @Setter private String idTrechoRodovia;
    @Getter @Setter private String idSentidoRodovia;
    @Getter @Setter private String idTipoOcorrencia;
    @Getter @Setter private String idTipoAcidente;

}