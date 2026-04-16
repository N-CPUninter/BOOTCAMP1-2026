
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
