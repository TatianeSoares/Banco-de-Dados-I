CREATE SCHEMA rodovia;

CREATE TABLE "rodovia"."rodovias" (
                                      descricao VARCHAR(30) NOT NULL,
                                      CONSTRAINT pk_rodovia PRIMARY KEY(descricao)
);

CREATE TABLE "rodovia"."trechoRodovia" (
                                           descricao VARCHAR(30) NOT NULL,
                                           idRodovia VARCHAR(30),
                                           CONSTRAINT pk_treRod PRIMARY KEY(descricao),
                                           CONSTRAINT fk_treRod_rod FOREIGN KEY(idRodovia)
                                               REFERENCES rodovia.rodovias(descricao)
);

CREATE TABLE "rodovia"."sentidoRodovia" (
                                            descricao VARCHAR(30) NOT NULL,
                                            CONSTRAINT pk_sentRod PRIMARY KEY(descricao)
);

CREATE TABLE "rodovia"."tipoAcidente" (
                                          descricao VARCHAR(50) NOT NULL,
                                          CONSTRAINT pk_tipAcidente PRIMARY KEY(descricao)
);

CREATE TABLE "rodovia"."tipoOcorrencia" (
                                            descricao VARCHAR(30) NOT NULL,
                                            CONSTRAINT pk_tipOcorrencia PRIMARY KEY(descricao)
);

CREATE TABLE "rodovia"."tipoPista" (
                                       descricao VARCHAR(30) NOT NULL,
                                       CONSTRAINT pk_tipPista PRIMARY KEY(descricao)
);

CREATE TABLE "rodovia"."acidente" (
                                      data DATE NOT NULL,
                                      hora TIME,
                                      nrOcorrencia REAL NOT NULL,
                                      km REAL,
                                      automovel INT DEFAULT 0,
                                      bicicleta INT DEFAULT 0,
                                      caminhao INT DEFAULT 0,
                                      moto INT DEFAULT 0,
                                      onibus INT DEFAULT 0,
                                      outros INT DEFAULT 0,
                                      tracaoAnimal INT DEFAULT 0,
                                      cargaEspecial INT DEFAULT 0,
                                      tratorMaquina INT DEFAULT 0,
                                      utilitario INT DEFAULT 0,
                                      ileso INT DEFAULT 0,
                                      levementeFerido INT DEFAULT 0,
                                      gravementeFerido INT DEFAULT 0,
                                      mortos INT DEFAULT 0,
                                      idTrechoRodovia VARCHAR(30),
                                      idSentidoRodovia VARCHAR(30),
                                      idTipoAcidente VARCHAR(30),
                                      idTipoOcorrencia VARCHAR(30),
                                      CONSTRAINT pk_acidente PRIMARY KEY(data, nrOcorrencia),
                                      CONSTRAINT fk_aci_trechoRod FOREIGN KEY(idTrechoRodovia)
                                          REFERENCES rodovia."trechoRodovia"(descricao),
                                      CONSTRAINT fk_aci_sentRod FOREIGN KEY(idSentidoRodovia)
                                          REFERENCES rodovia."sentidoRodovia"(descricao),
                                      CONSTRAINT fk_aci_tipAci FOREIGN KEY(idTipoAcidente)
                                          REFERENCES rodovia."tipoAcidente"(descricao),
                                      CONSTRAINT fk_aci_tipOco FOREIGN KEY(idTipoOcorrencia)
                                          REFERENCES rodovia."tipoOcorrencia"(descricao),
                                      CONSTRAINT ck_aci CHECK (km >= 0.00)
);

CREATE TABLE "rodovia"."velocidadeMaxima" (
                                              id SERIAL,
                                              situacao VARCHAR(30),
                                              uf VARCHAR(2),
                                              anoPnvSnv INT,
                                              veloVeicPesado REAL,
                                              veloVeicLeve REAL,
                                              latitude REAL,
                                              longitude REAL,
                                              municipio VARCHAR(40),
                                              km REAL,
                                              idTrechoRodovia VARCHAR(30),
                                              idSentidoRodovia VARCHAR(30),
                                              idTipoPista VARCHAR(30),
                                              CONSTRAINT pk_veloMaxima PRIMARY KEY(id),
                                              CONSTRAINT fk_sina_treRod FOREIGN KEY(idTrechoRodovia)
                                                  REFERENCES rodovia."trechoRodovia"(descricao),
                                              CONSTRAINT fk_sina_sentRod FOREIGN KEY(idSentidoRodovia)
                                                  REFERENCES rodovia."sentidoRodovia"(descricao),
                                              CONSTRAINT fk_sina_tipPista FOREIGN KEY(idTipoPista)
                                                  REFERENCES rodovia."tipoPista"(descricao),
                                              CONSTRAINT ck_velMax CHECK (km >= 0.00)
);

CREATE TABLE "rodovia"."ultrapassagem" (
                                           id SERIAL,
                                           situacao VARCHAR(30),
                                           uf VARCHAR(2),
                                           anoPnvSnv INT,
                                           kmInicial REAL,
                                           kmFinal REAL,
                                           longitudeFinal REAL,
                                           longitudeInicial REAL,
                                           latitudeFinal REAL,
                                           latitudeInicial REAL,
                                           idTrechoRodovia VARCHAR(30),
                                           idSentidoRodovia VARCHAR(30),
                                           idTipoPista VARCHAR(30),
                                           CONSTRAINT pk_ultrapassagem PRIMARY KEY(id),
                                           CONSTRAINT fk_sina_treRod FOREIGN KEY(idTrechoRodovia)
                                               REFERENCES rodovia."trechoRodovia"(descricao),
                                           CONSTRAINT fk_sina_sentRod FOREIGN KEY(idSentidoRodovia)
                                               REFERENCES rodovia."sentidoRodovia"(descricao),
                                           CONSTRAINT fk_sina_tipPista FOREIGN KEY(idTipoPista)
                                               REFERENCES rodovia."tipoPista"(descricao),
                                           CONSTRAINT ck_ultra CHECK (kmInicial >= 0.00 AND (kmFinal >= 0.00))
);

CREATE TABLE "rodovia"."historicoCarga" (
                                            dataCarga DATE,
                                            horaCarga TIME,
                                            tipoCarga VARCHAR(25),
                                            CONSTRAINT pk_historicoCarga PRIMARY KEY(dataCarga, horaCarga)
);
