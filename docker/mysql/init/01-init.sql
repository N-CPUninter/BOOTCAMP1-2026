-- Arquivo para dados iniciais.
-- Adicione inserts de exemplo para nossos testes e correção.
-- Exemplo simples de inserção de dados.
-- Mostraremos 2 tabelas com 3 colunas cada e relacionadas entre si.
USE medalerta;
-- depois de fazer o clone do projeto que estamos usando (atenção, não é o da faculdade!) voce pode abrir no vs code o projeto, como ta aberto
-- aqui. no futuro, quando vc for iniciar o desenvolvimento, depois de fazer o clone e se tem outras pessoas ja trabalhando no projeto,
-- o ideal é sempre dar git pull antes. pra isso, abra um novo terminal ^

-- o meu n fez nada pq nao tem nada pra puxar, n tem nada novo. seguimos. agora vc pode começar o desenvolvimento:
CREATE TABLE Alerta(
	idAlerta INT auto_increment NOT NULL,
    idHorarioMedicamento INT NOT NULL,
    tempoMinutos INT NOT NULL,
    statusAlerta ENUM('emitido', 'pendente', 'confirmado'),
    ativo BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (idAlerta),
    FOREIGN KEY (idHorarioMedicamento) REFERENCES HorarioMedicamento(idHorarioMedicamento)
);

--adicionadas as modificacoes, o ideal seria testar o codigo. caso nao tenha feito ainda, segue o que deve ser feito:
-- copia do .env: cp .env.exemplo .env
-- rodar o container (o prof explicou pelo devcontainers la mas so sei fazer na mao)
-- docker compose up -d

-- pode demorar mais se for a primeira vez.
-- alias, deveria ser --build
-- docker compose up --build -d (o -d é pra liberar o terminal p vc usar, pode fazer sem e criar um novo terminal, assim vc consegue ver os 
-- logs em tempo real tb)

-- com o docker criado, vamos testar o comando acima no mysql workbench

--nao deu certo :D acho que pq nao salvei o arquivo. tentamos de novo. pra parar o docker, docker compose down

--seta pra cima no terminal mostra os ultimos comandos.