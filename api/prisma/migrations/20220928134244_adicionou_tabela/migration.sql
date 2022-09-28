BEGIN TRY

BEGIN TRAN;

-- CreateTable
CREATE TABLE [dbo].[Aluno] (
    [id_aluno] INT NOT NULL IDENTITY(1,1),
    [nome_aluno] VARCHAR(30) NOT NULL,
    [email_aluno] VARCHAR(256) NOT NULL,
    [senha_aluno] NVARCHAR(12) NOT NULL,
    CONSTRAINT [Aluno_pkey] PRIMARY KEY CLUSTERED ([id_aluno])
);

-- CreateTable
CREATE TABLE [dbo].[Professor] (
    [id_professor] INT NOT NULL IDENTITY(1,1),
    [nome_professor] VARCHAR(30) NOT NULL,
    [email_professor] VARCHAR(256) NOT NULL,
    [senha_professor] NVARCHAR(12) NOT NULL,
    CONSTRAINT [Professor_pkey] PRIMARY KEY CLUSTERED ([id_professor])
);

-- CreateTable
CREATE TABLE [dbo].[Materia] (
    [id_materia] INT NOT NULL IDENTITY(1,1),
    [nome_materia] VARCHAR(30) NOT NULL,
    [cor_etiqueta] VARCHAR(20),
    CONSTRAINT [Materia_pkey] PRIMARY KEY CLUSTERED ([id_materia])
);

-- CreateTable
CREATE TABLE [dbo].[Anotacao] (
    [id_anotacao] INT NOT NULL IDENTITY(1,1),
    [anotacao] TEXT NOT NULL,
    CONSTRAINT [Anotacao_pkey] PRIMARY KEY CLUSTERED ([id_anotacao])
);

-- CreateTable
CREATE TABLE [dbo].[ToDoList] (
    [id_todo_list] INT NOT NULL IDENTITY(1,1),
    [data_inicio] DATETIME2 NOT NULL,
    [data_entrega] DATETIME2 NOT NULL,
    [nome_lista] VARCHAR(20) NOT NULL,
    [fk_anotacao] INT NOT NULL,
    CONSTRAINT [ToDoList_pkey] PRIMARY KEY CLUSTERED ([id_todo_list])
);

-- CreateTable
CREATE TABLE [dbo].[Upload] (
    [id_upload] INT NOT NULL IDENTITY(1,1),
    [url_foto] TEXT NOT NULL,
    CONSTRAINT [Upload_pkey] PRIMARY KEY CLUSTERED ([id_upload])
);

-- CreateTable
CREATE TABLE [dbo].[Turma] (
    [id_turma] INT NOT NULL IDENTITY(1,1),
    [nome_turma] NVARCHAR(12) NOT NULL,
    [qtd_alunos] INT NOT NULL,
    CONSTRAINT [Turma_pkey] PRIMARY KEY CLUSTERED ([id_turma])
);

-- CreateTable
CREATE TABLE [dbo].[AlunoTurma] (
    [id_aluno_turma] INT NOT NULL IDENTITY(1,1),
    [ra_aluno] NVARCHAR(5) NOT NULL,
    [fk_turma] INT NOT NULL,
    CONSTRAINT [AlunoTurma_pkey] PRIMARY KEY CLUSTERED ([id_aluno_turma])
);

-- CreateTable
CREATE TABLE [dbo].[Notas] (
    [id_notas] INT NOT NULL IDENTITY(1,1),
    [nota] FLOAT(53) NOT NULL,
    [bimestre] NVARCHAR(1) NOT NULL,
    [fk_materia] INT NOT NULL,
    [fk_aluno_turma] INT NOT NULL,
    CONSTRAINT [Notas_pkey] PRIMARY KEY CLUSTERED ([id_notas])
);

-- CreateTable
CREATE TABLE [dbo].[CalendarioAluno] (
    [id_calendario_aluno] INT NOT NULL IDENTITY(1,1),
    [fk_aluno] INT NOT NULL,
    [fk_anotacao] INT NOT NULL,
    [data_inicio] DATETIME2 NOT NULL,
    [data_entrega] DATETIME2 NOT NULL,
    CONSTRAINT [CalendarioAluno_pkey] PRIMARY KEY CLUSTERED ([id_calendario_aluno])
);

-- CreateTable
CREATE TABLE [dbo].[CalendarioProfessor] (
    [id_calendario_professor] INT NOT NULL IDENTITY(1,1),
    [fk_professor] INT NOT NULL,
    [fk_anotacao] INT NOT NULL,
    [data_inicio] DATETIME2 NOT NULL,
    [data_entrega] DATETIME2 NOT NULL,
    CONSTRAINT [CalendarioProfessor_pkey] PRIMARY KEY CLUSTERED ([id_calendario_professor])
);

-- AddForeignKey
ALTER TABLE [dbo].[ToDoList] ADD CONSTRAINT [ToDoList_fk_anotacao_fkey] FOREIGN KEY ([fk_anotacao]) REFERENCES [dbo].[Anotacao]([id_anotacao]) ON DELETE NO ACTION ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE [dbo].[AlunoTurma] ADD CONSTRAINT [AlunoTurma_fk_turma_fkey] FOREIGN KEY ([fk_turma]) REFERENCES [dbo].[Turma]([id_turma]) ON DELETE NO ACTION ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE [dbo].[Notas] ADD CONSTRAINT [Notas_fk_materia_fkey] FOREIGN KEY ([fk_materia]) REFERENCES [dbo].[Materia]([id_materia]) ON DELETE NO ACTION ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE [dbo].[Notas] ADD CONSTRAINT [Notas_fk_aluno_turma_fkey] FOREIGN KEY ([fk_aluno_turma]) REFERENCES [dbo].[AlunoTurma]([id_aluno_turma]) ON DELETE NO ACTION ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE [dbo].[CalendarioAluno] ADD CONSTRAINT [CalendarioAluno_fk_aluno_fkey] FOREIGN KEY ([fk_aluno]) REFERENCES [dbo].[Aluno]([id_aluno]) ON DELETE NO ACTION ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE [dbo].[CalendarioAluno] ADD CONSTRAINT [CalendarioAluno_fk_anotacao_fkey] FOREIGN KEY ([fk_anotacao]) REFERENCES [dbo].[Anotacao]([id_anotacao]) ON DELETE NO ACTION ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE [dbo].[CalendarioProfessor] ADD CONSTRAINT [CalendarioProfessor_fk_professor_fkey] FOREIGN KEY ([fk_professor]) REFERENCES [dbo].[Professor]([id_professor]) ON DELETE NO ACTION ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE [dbo].[CalendarioProfessor] ADD CONSTRAINT [CalendarioProfessor_fk_anotacao_fkey] FOREIGN KEY ([fk_anotacao]) REFERENCES [dbo].[Anotacao]([id_anotacao]) ON DELETE NO ACTION ON UPDATE CASCADE;

COMMIT TRAN;

END TRY
BEGIN CATCH

IF @@TRANCOUNT > 0
BEGIN
    ROLLBACK TRAN;
END;
THROW

END CATCH
