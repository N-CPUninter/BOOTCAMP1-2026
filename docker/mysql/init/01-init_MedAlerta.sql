ALTER DATABASE medalerta
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

use medalerta;

create table Usuario (
	idUsuario int auto_increment not null,
	nome varchar(100) not null,
    telefone varchar(20) not null,
    email varchar(100) not null unique,
    enderecoRua varchar(100),
    enderecoNumero int,
    enderecoComplemento varchar(50),
    enderecoBairro varchar(50),
    enderecoCEP varchar(10),
    enderecoCidade varchar(50),
    enderecoEstado varchar(02),
    primary key (idUsuario)
);

create table Medicamento (
	idMedicamento int auto_increment not null,
    nomeComercial varchar(100) not null,
    nomeGenerico varchar(100),
    quantidade enum('UNIDADE', 'ML'),
    formaUso varchar(100),
    observacao varchar(200),
    primary key (idMedicamento)
);

CREATE TABLE Prescricao (
    idPrescricao INT AUTO_INCREMENT NOT NULL,
    idUsuario INT NOT NULL,
    idMedicamento INT NOT NULL,
    dosagemValor INT NOT NULL,
	dosagemUnidade VARCHAR(30) NOT NULL,
    frequenciaUso INT,
	frequenciaTipo ENUM('HORAS',
	    'DIAS', 'SEMANAS', 'DOSE_UNICA'),
    PRIMARY KEY (idPrescricao),
    FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario),
    FOREIGN KEY (idMedicamento) REFERENCES Medicamento(idMedicamento)
);

create table HorarioMedicamento (
    idHorarioMedicamento int auto_increment not null,
    idPrescricao int not null,
    horario time not null,
    primary key (idHorarioMedicamento),
    foreign key (idPrescricao) references Prescricao(idPrescricao)
);

CREATE TABLE Alerta(
	idAlerta INT auto_increment NOT NULL,
    idHorarioMedicamento INT NOT NULL,
    tempoMinutos INT NOT NULL,
    statusAlerta ENUM('EMITIDO', 'PENDENTE', 'CONFIRMADO'),
    ativo BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (idAlerta),
    FOREIGN KEY (idHorarioMedicamento) REFERENCES HorarioMedicamento(idHorarioMedicamento)
);

CREATE TABLE EstoqueMedicamento (
    idEstoque INT AUTO_INCREMENT NOT NULL,
    idPrescricao INT NOT NULL,
    quantidadeTotal INT NOT NULL,
    quantidadeAtual INT NOT NULL,
    PRIMARY KEY (idEstoque),
    FOREIGN KEY (idPrescricao) REFERENCES Prescricao(idPrescricao)
);

CREATE TABLE Cuidador (
    idCuidador INT NOT NULL AUTO_INCREMENT,
    idUsuario INT NOT NULL UNIQUE,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    enderecoRua VARCHAR(100) NOT NULL,
    enderecoNumero INT NOT NULL,
    enderecoComplemento VARCHAR(50),
    enderecoBairro VARCHAR(50) NOT NULL,
    enderecoCep VARCHAR(10) NOT NULL,
    enderecoCidade VARCHAR(50) NOT NULL,
    enderecoEstado CHAR(2) NOT NULL,
    PRIMARY KEY (idCuidador),
    CONSTRAINT fk_Usuario
      FOREIGN KEY (idUsuario)
          REFERENCES Usuario (idUsuario)
          ON DELETE CASCADE
          ON UPDATE CASCADE
);

INSERT INTO Usuario (nome, telefone, email, enderecoRua, enderecoNumero, enderecoComplemento, enderecoBairro, enderecoCEP, enderecoCidade, enderecoEstado) VALUES
('João Silva', '(11) 98765-4321', 'joao.silva@email.com', 'Rua das Flores', 123, 'Apto 101', 'Centro', '01234-567', 'São Paulo', 'SP'),
('Maria Santos', '(21) 91234-5678', 'maria.santos@email.com', 'Avenida Principal', 456, NULL, 'Botafogo', '23456-789', 'Rio de Janeiro', 'RJ'),
('Pedro Oliveira', '(31) 99876-5432', 'pedro.oliveira@email.com', 'Rua Verde', 789, 'Casa 2', 'Savassi', '34567-890', 'Belo Horizonte', 'MG'),
('Ana Costa', '(41) 97654-3210', 'ana.costa@email.com', 'Alameda das Árvores', 321, 'Bloco B', 'Batel', '45678-901', 'Curitiba', 'PR'),
('Carlos Ferreira', '(51) 96543-2109', 'carlos.ferreira@email.com', 'Rua do Porto', 654, NULL, 'Centro Histórico', '56789-012', 'Porto Alegre', 'RS');

INSERT INTO Medicamento (nomeComercial, nomeGenerico, quantidade, formaUso, observacao) VALUES
('Dipirona', 'Dipirona Sódica', 'ml', 'Gotas', 'Analgésico e antitérmico'),
('Paracetamol', 'Paracetamol', 'unidade', 'Comprimido', 'Analgésico e antitérmico'),
('Amoxicilina', 'Amoxicilina', 'unidade', 'Cápsula', 'Antibiótico'),
('Losartana', 'Losartana Potássica', 'unidade', 'Comprimido', 'Anti-hipertensivo'),
('Omeprazol', 'Omeprazol', 'unidade', 'Cápsula', 'Redutor de acidez estomacal'),
('Ibuprofeno', 'Ibuprofeno', 'unidade', 'Comprimido', 'Anti-inflamatório'),
('Diazepam', 'Diazepam', 'unidade', 'Comprimido', 'Ansiolítico'),
('Salbutamol', 'Salbutamol', 'ml', 'Spray', 'Broncodilatador'),
('Metformina', 'Metformina', 'unidade', 'Comprimido', 'Antidiabético'),
('Atorvastatina', 'Atorvastatina', 'unidade', 'Comprimido', 'Redutor de colesterol');

INSERT INTO Prescricao (idUsuario, idMedicamento, dosagemValor, dosagemUnidade, frequenciaUso, frequenciaTipo) VALUES
(1, 1, 10, 'ml', 8, 'horas'),          -- João - Dipirona 10ml a cada 8 horas
(1, 2, 500, 'mg', 6, 'horas'),          -- João - Paracetamol 500mg a cada 6 horas
(2, 3, 500, 'mg', 8, 'horas'),          -- Maria - Amoxicilina 500mg a cada 8 horas
(2, 5, 20, 'mg', 1, 'dias'),            -- Maria - Omeprazol 20mg 1 vez por dia
(3, 4, 50, 'mg', 12, 'horas'),          -- Pedro - Losartana 50mg a cada 12 horas
(3, 6, 400, 'mg', 8, 'horas'),          -- Pedro - Ibuprofeno 400mg a cada 8 horas
(4, 7, 5, 'mg', 1, 'dias'),             -- Ana - Diazepam 5mg 1 vez por dia
(4, 8, 2, 'mg', NULL, 'dose única'), -- Ana - Salbutamol dose única
(5, 9, 850, 'mg', 12, 'horas'),          -- Carlos - Metformina 850mg a cada 12 horas
(5, 10, 20, 'mg', 1, 'dias');           -- Carlos - Atorvastatina 20mg 1 vez por dia

INSERT INTO HorarioMedicamento (idPrescricao, horario) VALUES
(1, '08:00:00'),  -- João - Dipirona 8h
(1, '16:00:00'),  -- João - Dipirona 16h
(1, '00:00:00'),  -- João - Dipirona 24h
(2, '08:00:00'),  -- João - Paracetamol 8h
(2, '14:00:00'),  -- João - Paracetamol 14h
(2, '20:00:00'),  -- João - Paracetamol 20h
(2, '02:00:00'),  -- João - Paracetamol 2h
(3, '08:00:00'),  -- Maria - Amoxicilina 8h
(3, '16:00:00'),  -- Maria - Amoxicilina 16h
(3, '00:00:00'),  -- Maria - Amoxicilina 24h
(4, '07:00:00'),  -- Maria - Omeprazol 7h
(5, '08:00:00'),  -- Pedro - Losartana 8h
(5, '20:00:00'),  -- Pedro - Losartana 20h
(6, '09:00:00'),  -- Pedro - Ibuprofeno 9h
(6, '17:00:00'),  -- Pedro - Ibuprofeno 17h
(6, '01:00:00'),  -- Pedro - Ibuprofeno 1h
(7, '22:00:00'),  -- Ana - Diazepam 22h
(8, '12:00:00'),  -- Ana - Salbutamol 12h
(9, '08:00:00'),  -- Carlos - Metformina 8h
(9, '20:00:00'),  -- Carlos - Metformina 20h
(10, '21:00:00'); -- Carlos - Atorvastatina 21h

INSERT INTO Alerta (idHorarioMedicamento, tempoMinutos, statusAlerta, ativo) VALUES
(1, 15, 'pendente', TRUE),   -- João - Dipirona 8h, alerta 15min antes
(2, 15, 'pendente', TRUE),   -- João - Dipirona 16h, alerta 15min antes
(3, 15, 'pendente', TRUE),   -- João - Dipirona 24h, alerta 15min antes
(4, 10, 'pendente', TRUE),   -- João - Paracetamol 8h, alerta 10min antes
(5, 10, 'pendente', TRUE),   -- João - Paracetamol 14h, alerta 10min antes
(6, 10, 'pendente', TRUE),   -- João - Paracetamol 20h, alerta 10min antes
(7, 10, 'pendente', TRUE),   -- João - Paracetamol 2h, alerta 10min antes
(8, 20, 'pendente', TRUE),   -- Maria - Amoxicilina 8h, alerta 20min antes
(9, 20, 'pendente', TRUE),   -- Maria - Amoxicilina 16h, alerta 20min antes
(10, 20, 'pendente', TRUE),  -- Maria - Amoxicilina 24h, alerta 20min antes
(11, 30, 'pendente', TRUE),  -- Maria - Omeprazol 7h, alerta 30min antes
(12, 15, 'pendente', TRUE),  -- Pedro - Losartana 8h, alerta 15min antes
(13, 15, 'pendente', TRUE),  -- Pedro - Losartana 20h, alerta 15min antes
(14, 10, 'pendente', TRUE),  -- Pedro - Ibuprofeno 9h, alerta 10min antes
(15, 10, 'pendente', TRUE),  -- Pedro - Ibuprofeno 17h, alerta 10min antes
(16, 10, 'pendente', TRUE),  -- Pedro - Ibuprofeno 1h, alerta 10min antes
(17, 30, 'pendente', TRUE),  -- Ana - Diazepam 22h, alerta 30min antes
(18, 5, 'pendente', TRUE),   -- Ana - Salbutamol 12h, alerta 5min antes
(19, 15, 'pendente', TRUE),  -- Carlos - Metformina 8h, alerta 15min antes
(20, 15, 'pendente', TRUE),  -- Carlos - Metformina 20h, alerta 15min antes
(21, 30, 'pendente', TRUE);  -- Carlos - Atorvastatina 21h, alerta 30min antes

INSERT INTO EstoqueMedicamento (idPrescricao, quantidadeTotal, quantidadeAtual) VALUES
(1, 30, 25),   -- João - Dipirona: 30ml total, 25ml disponível
(2, 20, 18),   -- João - Paracetamol: 20 comp total, 18 comp disponível
(3, 24, 20),   -- Maria - Amoxicilina: 24 comp total, 20 comp disponível
(4, 30, 28),   -- Maria - Omeprazol: 30 comp total, 28 comp disponível
(5, 60, 55),   -- Pedro - Losartana: 60 comp total, 55 comp disponível
(6, 21, 15),   -- Pedro - Ibuprofeno: 21 comp total, 15 comp disponível
(7, 10, 8),    -- Ana - Diazepam: 10 comp total, 8 comp disponível
(8, 1, 1),     -- Ana - Salbutamol: 1 spray total, 1 spray disponível
(9, 60, 50),   -- Carlos - Metformina: 60 comp total, 50 comp disponível
(10, 30, 25);  -- Carlos - Atorvastatina: 30 comp total, 25 comp disponível

INSERT INTO Cuidador (idUsuario, nome, telefone, email, enderecoRua, enderecoNumero, enderecoComplemento, enderecoBairro, enderecoCEP, enderecoCidade, enderecoEstado) VALUES
(1, 'Dona Francisca', '(11) 98888-7777', 'francisca.cuidadora@email.com', 'Rua das Flores', 123, 'Apto 102', 'Centro', '01234-567', 'São Paulo', 'SP'),
(2, 'Seu José', '(21) 97777-6666', 'jose.cuidador@email.com', 'Avenida Principal', 456, 'Apto 201', 'Botafogo', '23456-789', 'Rio de Janeiro', 'RJ'),
(3, 'Dona Maria', '(31) 96666-5555', 'maria.cuidadora@email.com', 'Rua Verde', 789, 'Casa 1', 'Savassi', '34567-890', 'Belo Horizonte', 'MG');

