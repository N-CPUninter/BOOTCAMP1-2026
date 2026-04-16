ALTER DATABASE medalerta
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

use medalerta;

create table HorarioMedicamento (
	idHorarioMedicamento int auto_increment not null,
	idUsuarioMedicamento int not null,
	horario time not null,
	frequenciaValor int not null,
	frequenciaUnidade enum('horas', 'dias', 'semanas', 'meses') not null,
	primary key (idHorarioMedicamento),
	foreign key (idUsuarioMedicamento), references UsuarioMedicamento(idUsuarioMedicamento)
);
