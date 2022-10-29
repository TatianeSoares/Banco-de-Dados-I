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
	descricao VARCHAR(50) NOT NULL,
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
	nrOcorrencia Integer NOT NULL,
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
	idTrechoRodovia INT DEFAULT 0,
	idSentidoRodovia INT DEFAULT 0,
	idTipoAcidente INT DEFAULT 0,
	idTipoOcorrencia INT DEFAULT 0,
	CONSTRAINT pk_acidente PRIMARY KEY(data, nrOcorrencia),
	CONSTRAINT fk_aci_trechoRod FOREIGN KEY(idTrechoRodovia)
		REFERENCES rodovia."trechoRodovia"(id),
	CONSTRAINT fk_aci_sentRod FOREIGN KEY(idSentidoRodovia)
		REFERENCES rodovia."sentidoRodovia"(id),
	CONSTRAINT fk_aci_tipAci FOREIGN KEY(idTipoAcidente)
		REFERENCES rodovia."tipoAcidente"(id),
	CONSTRAINT fk_aci_tipOco FOREIGN KEY(idTipoOcorrencia)
		REFERENCES rodovia."tipoOcorrencia"(id),
	CONSTRAINT ck_aci CHECK (km >= 0.00)
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
		REFERENCES rodovia."tipoPista"(id),
	CONSTRAINT ck_velMax CHECK (km >= 0.00)
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
		REFERENCES rodovia."tipoPista"(id),
	CONSTRAINT ck_ultra CHECK (kmInicial >= 0.00 AND (kmFinal >= 0.00))
);

-- 3 RODOVIAS
INSERT INTO rodovia.rodovias(id, descricao)
	VALUES (1, 'Rodovia do Aço');
INSERT INTO rodovia.rodovias(id, descricao)
	VALUES (2, 'Autopista Fernão Dias');
INSERT INTO rodovia.rodovias(id, descricao)
	VALUES (3, 'CRO');
    
-- TRECHO RODOVIA
INSERT INTO rodovia."trechoRodovia"(id, descricao, idRodovia)
	VALUES (1, 'BR-393', 1);
INSERT INTO rodovia."trechoRodovia"(id, descricao, idRodovia)
	VALUES (2, 'BR-381', 2); 
INSERT INTO rodovia."trechoRodovia"(id, descricao, idRodovia)
	VALUES (3, 'BR-163', 3); 
    
-- SENTIDO RODOVIA
INSERT INTO rodovia."sentidoRodovia"(id, descricao)
	VALUES (1, 'Decrescente');
INSERT INTO rodovia."sentidoRodovia"(id, descricao)
	VALUES (2, 'Crescente');   
INSERT INTO rodovia."sentidoRodovia"(id, descricao)
	VALUES (3, 'Norte');
INSERT INTO rodovia."sentidoRodovia"(id, descricao)
	VALUES (4, 'Sul');
    
-- TIPO PISTA
INSERT INTO rodovia."tipoPista"(id, descricao)
	VALUES (1, 'Principal');
INSERT INTO rodovia."tipoPista"(id, descricao)
	VALUES (2, 'Marginal');  

-- TIPO ACIDENTE 
INSERT INTO rodovia."tipoAcidente"(id, descricao)
	VALUES (1, 'Derrapagem');
INSERT INTO rodovia."tipoAcidente"(id, descricao)
	VALUES (2, 'Colisão lateral no sentido contrário');
INSERT INTO rodovia."tipoAcidente"(id, descricao)
	VALUES (3, 'Tombamento');
INSERT INTO rodovia."tipoAcidente"(id, descricao)
	VALUES (4, 'Colisão Traseira');
    
-- TIPO OCORRÊNCIA
INSERT INTO rodovia."tipoOcorrencia"(id, descricao)
	VALUES (1, 'sem vítima');
INSERT INTO rodovia."tipoOcorrencia"(id, descricao)
	VALUES (2, 'com vítima');
    
-- PLACAS VELOCIDADE MAXIMA
-- caso 1 - Sinalização VelMax AÇO
INSERT INTO rodovia."velocidadeMaxima"(id, situacao, uf, anoPnvSnv, veloVeicPesado, veloVeicLeve, latitude, longitude, municipio, km, idTrechoRodovia, idSentidoRodovia, idTipoPista)
	VALUES (1, 'Ativo', 'RJ', 2016, 60, 60, -22.4466, -43.7943, 'Barra do Piraí', 250.35, 1, 1, 1);

-- caso 2 - Sinalização VelMax AÇO
INSERT INTO rodovia."velocidadeMaxima"(id, situacao, uf, anoPnvSnv, veloVeicPesado, veloVeicLeve, latitude, longitude, municipio, km, idTrechoRodovia, idSentidoRodovia, idTipoPista)
	VALUES (2, 'Ativo', 'RJ', 2016, 60, 60, -22.4441, -43.7916, 'Barra do Piraí', 249.94, 1, 1, 1);
    
-- caso 3 - Sinalização VelMax Fernão Dias
INSERT INTO rodovia."velocidadeMaxima"(id, situacao, uf, anoPnvSnv, veloVeicPesado, veloVeicLeve, latitude, longitude, municipio, km, idTrechoRodovia, idSentidoRodovia, idTipoPista)
	VALUES (3, 'Ativo', 'MG', 2007, 60, 60, -19.9642, -44.1714, 'Betim', 493.235, 2, 2, 1);

-- caso 4 - Sinalização VelMax Fernão Dias
INSERT INTO rodovia."velocidadeMaxima"(id, situacao, uf, anoPnvSnv, veloVeicPesado, veloVeicLeve, latitude, longitude, municipio, km, idTrechoRodovia, idSentidoRodovia, idTipoPista)
	VALUES (4, 'Ativo', 'MG', 2007, 90, 110, -19.9805, -44.2009, 'Betim', 497.16, 2, 2, 1);
    
-- caso 5 - Sinalização VelMax Fernão Dias
INSERT INTO rodovia."velocidadeMaxima"(id, situacao, uf, anoPnvSnv, veloVeicPesado, veloVeicLeve, latitude, longitude, municipio, km, idTrechoRodovia, idSentidoRodovia, idTipoPista)
	VALUES (5, 'Ativo', 'SP', 2007, 40, 40, -23.3424, -46.5734, 'Mairiporã', 68, 2, 1, 1);
    
-- caso 6 - Sinalização VelMax Fernão Dias
INSERT INTO rodovia."velocidadeMaxima"(id, situacao, uf, anoPnvSnv, veloVeicPesado, veloVeicLeve, latitude, longitude, municipio, km, idTrechoRodovia, idSentidoRodovia, idTipoPista)
	VALUES (6, 'Ativo', 'SP', 2007, 80, 100, -23.3468, -46.5638, 'Mairiporã', 69.22, 2, 1, 1);

-- caso 7 - Sinalização VelMax CRO 
INSERT INTO rodovia."velocidadeMaxima"(id, situacao, uf, anoPnvSnv, veloVeicPesado, veloVeicLeve, latitude, longitude, municipio, km, idTrechoRodovia, idSentidoRodovia, idTipoPista)
	VALUES (7, 'Ativo', 'MT', 2016, 60, 60, -15.7005, -56.0453, 'Cuiabá', 503.93, 3, 2, 1);

-- caso 8 - Sinalização VelMax CRO 
INSERT INTO rodovia."velocidadeMaxima"(id, situacao, uf, anoPnvSnv, veloVeicPesado, veloVeicLeve, latitude, longitude, municipio, km, idTrechoRodovia, idSentidoRodovia, idTipoPista)
	VALUES (8, 'Ativo', 'MT', 2016, 80, 80, -15.701, -56.0502, 'Cuiabá', 504.43, 3, 2, 1);

-- PLACAS ULTRAPASSAGEM
-- caso 1 - Sinalização Ultrapassagem AÇO
INSERT INTO rodovia.ultrapassagem(id, situacao, uf, anoPnvSnv, kmInicial, kmFinal, longitudeFinal, longitudeInicial, latitudeFinal, latitudeInicial, idTrechoRodovia, idSentidoRodovia, idTipoPista)
	VALUES (1, 'Ativo', 'RJ', 2016, 248.32, 251.66, -43.8057, -43.7788, -22.4531, -22.4359, 1, 2, 1);
-- caso 2 - Sinalização Ultrapassagem AÇO
INSERT INTO rodovia.ultrapassagem(id, situacao, uf, anoPnvSnv, kmInicial, kmFinal, longitudeFinal, longitudeInicial, latitudeFinal, latitudeInicial, idTrechoRodovia, idSentidoRodovia, idTipoPista)
	VALUES (2, 'Ativo', 'RJ', 2016, 252.03, 256.81, -43.8505, -43.8082, -22.4612, -22.4534, 1, 2, 1); 
-- caso 3 - Sinalização Ultrapassagem CRO
INSERT INTO rodovia.ultrapassagem(id, situacao, uf, anoPnvSnv, kmInicial, kmFinal, longitudeFinal, longitudeInicial, latitudeFinal, latitudeInicial, idTrechoRodovia, idSentidoRodovia, idTipoPista)
	VALUES (3, 'Ativo', 'MT', 2016, 671.2, 671.8, -55.9958, -55.998, -13.2052, -13.2101, 3, 1, 1); 
-- caso 4 - Sinalização Ultrapassagem CRO
INSERT INTO rodovia.ultrapassagem(id, situacao, uf, anoPnvSnv, kmInicial, kmFinal, longitudeFinal, longitudeInicial, latitudeFinal, latitudeInicial, idTrechoRodovia, idSentidoRodovia, idTipoPista)
	VALUES (4, 'Ativo', 'MT', 2016, 630.8, 631.1, -56.0262, -56.0261, -13.5303, -13.5312, 3, 1, 1);    

-- ACIDENTES
-- caso 1 - Acidente AÇO
INSERT INTO rodovia.acidente(data, hora, nrOcorrencia, km, automovel, bicicleta, caminhao, moto, onibus, outros, tracaoAnimal, cargaEspecial, tratorMaquina, utilitario, ileso, levementeFerido, gravementeFerido, mortos, idTrechoRodovia, idSentidoRodovia, idTipoAcidente, idTipoOcorrencia)
	VALUES ('2010-01-01', '04:21:00', 18, 167, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 3, 1, 1);
-- caso 2 - Acidente AÇO 
INSERT INTO rodovia.acidente(data, hora, nrOcorrencia, km, automovel, bicicleta, caminhao, moto, onibus, outros, tracaoAnimal, cargaEspecial, tratorMaquina, utilitario, ileso, levementeFerido, gravementeFerido, mortos, idTrechoRodovia, idSentidoRodovia, idTipoAcidente, idTipoOcorrencia)
	VALUES ('2017-01-18', '11:33:00', 36, 168, 1, 0, 0, 0, 1, 0, 0, 0, 0, 2, 1, 0, 0, 0, 1, 3, 2, 2);
-- caso 3 - Acidente Fernão Dias
 INSERT INTO rodovia.acidente(data, hora, nrOcorrencia, km, automovel, bicicleta, caminhao, moto, onibus, outros, tracaoAnimal, cargaEspecial, tratorMaquina, utilitario, ileso, levementeFerido, gravementeFerido, mortos, idTrechoRodovia, idSentidoRodovia, idTipoAcidente, idTipoOcorrencia)
	VALUES ('2019-06-30', '09:05:00', 93, 529.5, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 2, 4, 3, 2);
-- caso 4 - Acidente Fernão Dias
 INSERT INTO rodovia.acidente(data, hora, nrOcorrencia, km, automovel, bicicleta, caminhao, moto, onibus, outros, tracaoAnimal, cargaEspecial, tratorMaquina, utilitario, ileso, levementeFerido, gravementeFerido, mortos, idTrechoRodovia, idSentidoRodovia, idTipoAcidente, idTipoOcorrencia)
	VALUES ('2019-06-30', '09:16:00', 100, 529.5, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 3, 2, 0, 0, 2, 4, 4, 2);
