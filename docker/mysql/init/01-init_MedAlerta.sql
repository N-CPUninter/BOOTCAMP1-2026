ALTER DATABASE medalerta
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

use medalerta;

create table Usuario (
	idUsuario int auto_increment not null,
	nome varchar(100) not null,
    telefone varchar(20) not null,
    email varchar(100) not null,
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
    quantidade enum('unidade', 'ml'),
    formaUso varchar(100),
    observacao varchar(200),
    primary key (idMedicamento)
);

create table UsuarioMedicamento (
	idUsuario int not null,
	idMedicamento int not null,
    horarioUso time not null,
    frequenciaUso varchar(50),
    dosagem varchar(50) not null,
    dataHorarioAlerta datetime not null,
    statusAlerta enum('emitido', 'não emitido') not null,
    dataHorarioConsumo datetime,
    confirmacaoConsumo enum('sim', 'não') not null,
    primary key (idUsuario, idMedicamento),
    foreign key (idUsuario) references Usuario (idUsuario),
    foreign key (idMedicamento) references Medicamento (idMedicamento)
);

CREATE TABLE Prescricao (
    idPrescricao INT AUTO_INCREMENT NOT NULL,
    idUsuario INT NOT NULL,
    idMedicamento INT NOT NULL,
    dosagem VARCHAR(50) NOT NULL,
    frequenciaUso VARCHAR(50),
    PRIMARY KEY (idPrescricao),
    FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario),
    FOREIGN KEY (idMedicamento) REFERENCES Medicamento(idMedicamento)
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
	idUsuario INT NOT NULL,
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

insert into Usuario (nome, telefone, email, enderecoRua, enderecoNumero, enderecoComplemento, enderecoBairro, enderecoCEP, enderecoCidade, enderecoEstado) values
	('Ana Souza', '41999990001', 'ana.souza@email.com', 'Rua A', 10, null, 'Centro', '80000-001', 'Curitiba', 'PR'),
	('Bruno Lima', '41999990002', 'bruno.lima@email.com', 'Rua B', 20, 'Casa', 'Batel', '80000-002', 'Curitiba', 'PR'),
	('Carla Mendes', '41999990003', 'carla.mendes@email.com', 'Rua C', 30, null, 'Água Verde', '80000-003', 'Curitiba', 'PR'),
	('Daniel Rocha', '41999990004', 'daniel.rocha@email.com', 'Rua D', 40, 'Apto 101', 'Portão', '80000-004', 'Curitiba', 'PR'),
	('Elisa Torres', '41999990005', 'elisa.torres@email.com', 'Rua E', 50, null, 'Santa Quitéria', '80000-005', 'Curitiba', 'PR'),
	('Felipe Alves', '41999990006', 'felipe.alves@email.com', 'Rua F', 60, null, 'Cabral', '80000-006', 'Curitiba', 'PR'),
	('Gabriela Nunes', '41999990007', 'gabriela.nunes@email.com', 'Rua G', 70, 'Fundos', 'Boqueirão', '80000-007', 'Curitiba', 'PR'),
	('Henrique Pires', '41999990008', 'henrique.pires@email.com', 'Rua H', 80, null, 'Xaxim', '80000-008', 'Curitiba', 'PR'),
	('Isabela Costa', '41999990009', 'isabela.costa@email.com', 'Rua I', 90, 'Apto 202', 'Rebouças', '80000-009', 'Curitiba', 'PR'),
	('João Martins', '41999990010', 'joao.martins@email.com', 'Rua J', 100, null, 'Centro Cívico', '80000-010', 'Curitiba', 'PR');
select * from Usuario;

insert into Medicamento (nomeComercial, nomeGenerico, quantidade, formaUso, observacao) values
	('Tylenol', 'Paracetamol', 'unidade', 'Via oral', 'Não exceder a dose'),
	('Advil', 'Ibuprofeno', 'unidade', 'Via oral', 'Tomar após refeições'),
	('Amoxil', 'Amoxicilina', 'ml', 'Via oral', 'Agitar antes de usar'),
	('Dipirona', 'Dipirona Sódica', 'unidade', 'Via oral', null),
	('Buscopan', 'Butilbrometo', 'unidade', 'Via oral', 'Uso em dor abdominal'),
	('Novalgina', 'Dipirona', 'ml', 'Via oral', null),
	('Atenol', 'Atenolol', 'unidade', 'Via oral', 'Controle da pressão'),
	('Losartana', 'Losartana Potássica', 'unidade', 'Via oral', null),
	('Vick', 'Camphora', 'ml', 'Uso tópico', 'Não ingerir'),
	('Cataflam', 'Diclofenaco', 'unidade', 'Via oral', 'Uso com cautela');
select * from Medicamento;

insert into UsuarioMedicamento (idUsuario, idMedicamento, horarioUso, frequenciaUso, dosagem, dataHorarioAlerta, statusAlerta, dataHorarioConsumo,
	confirmacaoConsumo) values
	(1, 1, '08:00:00', '8 em 8 horas', '1 comprimido', '2026-04-15 07:55:00', 'emitido', '2026-04-15 08:05:00', 'sim'),
	(1, 2, '20:00:00', '1 vez ao dia', '1 comprimido', '2026-04-15 19:55:00', 'emitido', '2026-04-15 20:10:00', 'sim'),
	(2, 3, '09:00:00', '12 em 12 horas', '10 ml', '2026-04-15 08:55:00', 'não emitido', null, 'não'),
	(2, 4, '18:00:00', '1 vez ao dia', '1 comprimido', '2026-04-15 17:55:00', 'emitido', '2026-04-15 18:03:00', 'sim'),
	(3, 5, '07:00:00', '8 em 8 horas', '1 comprimido', '2026-04-15 06:55:00', 'emitido', '2026-04-15 07:05:00', 'sim'),
	(3, 6, '22:00:00', '1 vez ao dia', '15 ml', '2026-04-15 21:55:00', 'não emitido', null, 'não'),
	(4, 7, '08:00:00', '1 vez ao dia', '1 comprimido', '2026-04-15 07:55:00', 'emitido', '2026-04-15 08:00:00', 'sim'),
	(4, 8, '20:00:00', '1 vez ao dia', '1 comprimido', '2026-04-15 19:55:00', 'emitido', '2026-04-15 20:02:00', 'sim'),
	(5, 9, '12:00:00', '2 vezes ao dia', '5 ml', '2026-04-15 11:55:00', 'emitido', '2026-04-15 12:06:00', 'sim'),
	(6, 10, '19:00:00', '1 vez ao dia', '1 comprimido', '2026-04-15 18:55:00', 'não emitido', null, 'não'),
	(7, 1, '08:00:00', '1 vez ao dia', '1 comprimido', '2026-04-15 07:55:00', 'emitido', '2026-04-15 08:01:00', 'sim'),
	(8, 2, '21:00:00', '1 vez ao dia', '1 comprimido', '2026-04-15 20:55:00', 'emitido', '2026-04-15 21:10:00', 'sim'),
	(9, 3, '10:00:00', '12 em 12 horas', '10 ml', '2026-04-15 09:55:00', 'não emitido', null, 'não'),
	(10, 4, '18:30:00', '1 vez ao dia', '1 comprimido', '2026-04-15 18:25:00', 'emitido', '2026-04-15 18:40:00', 'sim'),
	(10, 5, '22:00:00', '1 vez ao dia', '1 comprimido', '2026-04-15 21:55:00', 'emitido', '2026-04-15 22:05:00', 'sim');
select * from UsuarioMedicamento;

INSERT INTO Prescricao (idUsuario, idMedicamento, dosagem, frequenciaUso) 
VALUES 
  	(1, 1, '1 comprimido', 'A cada 8 horas'),
	(1, 4, '1 comprimido', '1 vez ao dia'),
	(2, 2, '40 gotas', 'A cada 6 horas'),
	(3, 3, '1 cápsula', 'A cada 12 horas'),
	(4, 5, '1 comprimido', 'Após o almoço'),
	(4, 7, '1 cápsula', 'Em jejum'),
	(5, 6, '2 gotas', 'Antes de dormir'),
	(6, 8, '1 comprimido', '1 vez ao dia'),
	(7, 9, '1 cápsula', 'Se houver dor'),
	(8, 10, '15 ml', 'A cada 8 horas'),
	(9, 4, '1 comprimido', '1 vez ao dia'),
	(10, 1, '1 comprimido', 'A cada 8 horas'),
	(2, 5, '1 comprimido', 'Após o almoço'),
	(3, 7, '1 cápsula', 'Em jejum'),
	(5, 8, '1 comprimido', '1 vez ao dia');


INSERT INTO EstoqueMedicamento (idPrescricao, quantidadeTotal, quantidadeAtual) 
VALUES 
	(1, 30, 29),  
	(2, 30, 29),  
	(3, 600, 560), 
	(4, 14, 14),   
	(5, 30, 29),   
	(6, 30, 29),   
	(7, 300, 300), 
	(8, 10, 10),   
	(9, 20, 19),   
	(10, 150, 135),
	(11, 30, 30),  
	(12, 30, 29),  
	(13, 30, 29),  
	(14, 14, 13),  
	(15, 10, 9); 

INSERT INTO Cuidador (idUsuario, nome, telefone, email, enderecoRua, enderecoNumero, enderecoComplemento, enderecoBairro, enderecoCEP, enderecoCidade, enderecoEstado) VALUES
	('Lucas Fernandes', '41999990011', 'lucas.fernandes@email.com', 'Rua K', 110, 'Sala 4', 'Prado Velho', '80000-011', 'Curitiba', 'PR', 1),
    ('Mariana Ribeiro', '41999990012', 'mariana.ribeiro@email.com', 'Rua L', 120, null, 'Tarumã', '80000-012', 'Curitiba', 'PR', 2),
    ('Nicolas Silva', '41999990013', 'nicolas.silva@email.com', 'Rua M', 130, 'Sobrado', 'Cristo Rei', '80000-013', 'Curitiba', 'PR', 1),
    ('Olivia Castro', '41999990014', 'olivia.castro@email.com', 'Rua N', 140, null, 'Juvevê', '80000-014', 'Curitiba', 'PR', 2),
    ('Paulo Gomes', '41999990015', 'paulo.gomes@email.com', 'Rua O', 150, 'Apto 303', 'Ahú', '80000-015', 'Curitiba', 'PR', 1),
    ('Quintino Lima', '41999990016', 'quintino.lima@email.com', 'Rua P', 160, null, 'Boa Vista', '80000-016', 'Curitiba', 'PR', 2),
    ('Rafaela Vieira', '41999990017', 'rafaela.vieira@email.com', 'Rua Q', 170, 'Bloco B', 'Santa Felicidade', '80000-017', 'Curitiba', 'PR', 1),
    ('Samuel Dias', '41999990018', 'samuel.dias@email.com', 'Rua R', 180, null, 'Campina do Siqueira', '80000-018', 'Curitiba', 'PR', 2),
    ('Tatiana Moraes', '41999990019', 'tatiana.moraes@email.com', 'Rua S', 190, 'Casa 2', 'Seminário', '80000-019', 'Curitiba', 'PR', 1),
    ('Ulisses Carvalho', '41999990020', 'ulisses.carvalho@email.com', 'Rua T', 200, null, 'Mercês', '80000-020', 'Curitiba', 'PR', 2);
select * from Cuidador; 

/* Listar todos os usuários, inclusive os que não utilizam nenhum medicamento. */
select Usuario.nome as 'Usuário'
from Usuario;

/* Listar os usuários e os medicamentos que os mesmos utilizam. */
select Usuario.nome as 'Usuário', Medicamento.nomeComercial as 'Medicamento'
from Usuario, Medicamento, UsuarioMedicamento
where Usuario.idUsuario = UsuarioMedicamento.idUsuario and
	Medicamento.idMedicamento = UsuarioMedicamento.idMedicamento;

/* Listar os medicamentos utilizados por um usuário específico. */
select Usuario.nome as 'Usuário', Medicamento.nomeComercial as 'Medicamento', UsuarioMedicamento.dosagem as 'Dosagem'
from Usuario, Medicamento, UsuarioMedicamento
where Usuario.idUsuario = UsuarioMedicamento.idUsuario and
	Medicamento.idMedicamento = UsuarioMedicamento.idMedicamento and
	Usuario.nome = 'Ana Souza';

/* Listar os usuários que não confirmaram o consumo do medicamento e notificar os terceiros. */
select Usuario.nome as 'Usuário', Medicamento.nomeComercial as 'Medicamento', UsuarioMedicamento.confirmacaoConsumo as 'Confirmação', Cuidador.nome as 'Cuidador', Cuidador.email as 'Contato'
from Usuario, Medicamento, UsuarioMedicamento, Cuidador
where Usuario.idUsuario = UsuarioMedicamento.idUsuario and
	Medicamento.idMedicamento = UsuarioMedicamento.idMedicamento and Cuidador.idUsuario = Usuario.idUsuario and 
	UsuarioMedicamento.confirmacaoConsumo = 'não';

/* Listar os alertas já emitidos. */
select Usuario.nome as 'Usuário', Medicamento.nomeComercial as 'Medicamento', UsuarioMedicamento.dataHorarioAlerta as 'Alerta'
from Usuario, Medicamento, UsuarioMedicamento
where Usuario.idUsuario = UsuarioMedicamento.idUsuario and
	Medicamento.idMedicamento = UsuarioMedicamento.idMedicamento and
	UsuarioMedicamento.statusAlerta = 'emitido';

/* Listar a quantidade de medicamentos por usuário. */
select U.nome as 'Usuário', count(UM.idMedicamento) as 'Total Medicamento'
from Usuario U
left join UsuarioMedicamento UM on U.idUsuario = UM.idUsuario
group by U.idUsuario;

/* Listar a quantidade de usuários por medicamento. */
select M.nomeComercial as 'Medicamento', count(UM.idUsuario) as 'Total Usuário'
from Medicamento M
left join UsuarioMedicamento UM on M.idMedicamento = UM.idMedicamento
group by M.idMedicamento;

/* Listar os usuários que consumiram medicamento em determinada data. */
select U.nome as 'Usuário', M.nomeComercial as 'Medicamento', UM.dataHorarioConsumo as 'Consumo'
from Usuario U
join UsuarioMedicamento UM on U.idUsuario = UM.idUsuario
join Medicamento M on M.idMedicamento = UM.idMedicamento
where date(UM.dataHorarioConsumo) = '2026-04-15';

/* Listar a quantidade de alertas não emitidos por usuário. */
select U.nome as 'Usuário', count(*) as 'Alertas não emitidos'
from Usuario U
join UsuarioMedicamento UM on U.idUsuario = UM.idUsuario
where UM.statusAlerta = 'não emitido'
group by U.idUsuario;

/* Listar um resumo geral do uso de medicamentos. */
select U.nome as 'Usuário', M.nomeComercial as 'Medicamento', UM.horarioUso as 'Horário de uso',
	UM.frequenciaUso as 'Frequência de uso', UM.confirmacaoConsumo as 'Confirmação de consumo'
from Usuario U
join UsuarioMedicamento UM on U.idUsuario = UM.idUsuario
join Medicamento M ON M.idMedicamento = UM.idMedicamento
order by U.nome, UM.horarioUso;