/*
  Warnings:

  - You are about to alter the column `anotacao` on the `Anotacao` table. The data in that column could be lost. The data in that column will be cast from `Text` to `NVarChar(1000)`.
  - You are about to alter the column `url_foto` on the `Upload` table. The data in that column could be lost. The data in that column will be cast from `Text` to `NVarChar(1000)`.
  - A unique constraint covering the columns `[email_aluno]` on the table `Aluno` will be added. If there are existing duplicate values, this will fail.
  - A unique constraint covering the columns `[email_professor]` on the table `Professor` will be added. If there are existing duplicate values, this will fail.

*/
BEGIN TRY

BEGIN TRAN;

-- AlterTable
ALTER TABLE [dbo].[Aluno] ALTER COLUMN [nome_aluno] NVARCHAR(1000) NOT NULL;
ALTER TABLE [dbo].[Aluno] ALTER COLUMN [email_aluno] NVARCHAR(1000) NOT NULL;
ALTER TABLE [dbo].[Aluno] ALTER COLUMN [senha_aluno] NVARCHAR(1000) NOT NULL;

-- AlterTable
ALTER TABLE [dbo].[AlunoTurma] ALTER COLUMN [ra_aluno] NVARCHAR(1000) NOT NULL;

-- AlterTable
ALTER TABLE [dbo].[Anotacao] ALTER COLUMN [anotacao] NVARCHAR(1000) NOT NULL;

-- AlterTable
ALTER TABLE [dbo].[Materia] ALTER COLUMN [nome_materia] NVARCHAR(1000) NOT NULL;
ALTER TABLE [dbo].[Materia] ALTER COLUMN [cor_etiqueta] NVARCHAR(1000) NULL;

-- AlterTable
ALTER TABLE [dbo].[Notas] ALTER COLUMN [bimestre] NVARCHAR(1000) NOT NULL;

-- AlterTable
ALTER TABLE [dbo].[Professor] ALTER COLUMN [nome_professor] NVARCHAR(1000) NOT NULL;
ALTER TABLE [dbo].[Professor] ALTER COLUMN [email_professor] NVARCHAR(1000) NOT NULL;
ALTER TABLE [dbo].[Professor] ALTER COLUMN [senha_professor] NVARCHAR(1000) NOT NULL;

-- AlterTable
ALTER TABLE [dbo].[ToDoList] ALTER COLUMN [nome_lista] NVARCHAR(1000) NOT NULL;

-- AlterTable
ALTER TABLE [dbo].[Turma] ALTER COLUMN [nome_turma] NVARCHAR(1000) NOT NULL;

-- AlterTable
ALTER TABLE [dbo].[Upload] ALTER COLUMN [url_foto] NVARCHAR(1000) NOT NULL;

-- CreateIndex
ALTER TABLE [dbo].[Aluno] ADD CONSTRAINT [Aluno_email_aluno_key] UNIQUE NONCLUSTERED ([email_aluno]);

-- CreateIndex
ALTER TABLE [dbo].[Professor] ADD CONSTRAINT [Professor_email_professor_key] UNIQUE NONCLUSTERED ([email_professor]);

COMMIT TRAN;

END TRY
BEGIN CATCH

IF @@TRANCOUNT > 0
BEGIN
    ROLLBACK TRAN;
END;
THROW

END CATCH
