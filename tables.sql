CREATE SCHEMA rodovia;

CREATE TABLE "rodovia"."rodovias" (
	id INT NOT NULL,
	descricao VARCHAR(30) NOT NULL,
	CONSTRAINT pk_rodovia PRIMARY KEY(id)
);

CREATE TABLE "rodovia"."trechoRodovia" (
	id INT NOT NULL,
	descricao VARCHAR(30) NOT NULL,
	idRodovia INT,
	CONSTRAINT pk_treRod PRIMARY KEY(id),
	CONSTRAINT fk_treRod_rod FOREIGN KEY(idRodovia)
		REFERENCES rodovia.rodovias(id)
);

CREATE TABLE "rodovia"."sentidoRodovia" (
	id INT NOT NULL,
	descricao VARCHAR(30) NOT NULL,
	CONSTRAINT pk_sentRod PRIMARY KEY(id)
);

CREATE TABLE "rodovia"."tipoAcidente" (
	id INT NOT NULL,
	descricao VARCHAR(30) NOT NULL,
	CONSTRAINT pk_tipAcidente PRIMARY KEY(id)
);

CREATE TABLE "rodovia"."tipoOcorrencia" (
	id INT NOT NULL,
	descricao VARCHAR(30) NOT NULL,
	CONSTRAINT pk_tipOcorrencia PRIMARY KEY(id)
);

CREATE TABLE "rodovia"."tipoPista" (
	id INT NOT NULL,
	descricao VARCHAR(30) NOT NULL,
	CONSTRAINT pk_tipPista PRIMARY KEY(id)
);

CREATE TABLE "rodovia"."acidente" (
	data DATE NOT NULL,
	hora TIME,
	nrOcorrencia REAL NOT NULL,
	km REAL, 
	automovel INT,
	bicicleta INT, 
	caminhao INT, 
	moto INT, 
	onibus INT, 
	outros INT, 
	tracaoAnimal INT, 
	cargaEspecial INT, 
	tratorMaquina INT, 
	utilitario INT, 
	ileso INT, 
	levementeFerido INT, 
	gravementeFerido INT, 
	mortos INT,
	idTrechoRodovia INT,
	idSentidoRodovia INT,
	idTipoAcidente INT,
	idTipoOcorrencia INT,
	CONSTRAINT pk_acidente PRIMARY KEY(data, nrOcorrencia),
	CONSTRAINT fk_aci_trechoRod FOREIGN KEY(idTrechoRodovia)
		REFERENCES rodovia."trechoRodovia"(id),
	CONSTRAINT fk_aci_sentRod FOREIGN KEY(idSentidoRodovia)
		REFERENCES rodovia."sentidoRodovia"(id),
	CONSTRAINT fk_aci_tipAci FOREIGN KEY(idTipoAcidente)
		REFERENCES rodovia."tipoAcidente"(id),
	CONSTRAINT fk_aci_tipOco FOREIGN KEY(idTipoOcorrencia)
		REFERENCES rodovia."tipoOcorrencia"(id)
);

CREATE TABLE "rodovia"."velocidadeMaxima" (
	id INT NOT NULL,
	situacao VARCHAR(30),
	uf VARCHAR(2),
	anoPnvSnv INT,
	veloVeicPesado REAL, 
	veloVeicLeve REAL, 
	latitude REAL, 
	longitude REAL, 
	municipio VARCHAR(40), 
	km REAL,
	idTrechoRodovia INT,
	idSentidoRodovia INT,
	idTipoPista INT,
	CONSTRAINT pk_veloMaxima PRIMARY KEY(id),
	CONSTRAINT fk_sina_treRod FOREIGN KEY(idTrechoRodovia)
		REFERENCES rodovia."trechoRodovia"(id),
	CONSTRAINT fk_sina_sentRod FOREIGN KEY(idSentidoRodovia)
		REFERENCES rodovia."sentidoRodovia"(id),
	CONSTRAINT fk_sina_tipPista FOREIGN KEY(idTipoPista)
		REFERENCES rodovia."tipoPista"(id)
);

CREATE TABLE "rodovia"."ultrapassagem" (
	id INT NOT NULL,
	situacao VARCHAR(30),
	uf VARCHAR(2),
	anoPnvSnv INT,
	kmInicial REAL, 
	kmFinal REAL, 
	longitudeFinal REAL, 
	longitudeInicial REAL, 
	latitudeFinal REAL, 
	latitudeInicial REAL,
	idTrechoRodovia INT,
	idSentidoRodovia INT,
	idTipoPista INT,
	CONSTRAINT pk_ultrapassagem PRIMARY KEY(id),
	CONSTRAINT fk_sina_treRod FOREIGN KEY(idTrechoRodovia)
		REFERENCES rodovia."trechoRodovia"(id),
	CONSTRAINT fk_sina_sentRod FOREIGN KEY(idSentidoRodovia)
		REFERENCES rodovia."sentidoRodovia"(id),
	CONSTRAINT fk_sina_tipPista FOREIGN KEY(idTipoPista)
		REFERENCES rodovia."tipoPista"(id)
);

