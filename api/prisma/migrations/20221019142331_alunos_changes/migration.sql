/*
  Warnings:

  - Added the required column `fk_aluno` to the `Anotacao` table without a default value. This is not possible if the table is not empty.

*/
BEGIN TRY

BEGIN TRAN;

-- DropForeignKey
ALTER TABLE [dbo].[CalendarioAluno] DROP CONSTRAINT [CalendarioAluno_fk_aluno_fkey];

-- AlterTable
ALTER TABLE [dbo].[Anotacao] ADD [fk_aluno] INT NOT NULL;

-- AddForeignKey
ALTER TABLE [dbo].[Anotacao] ADD CONSTRAINT [Anotacao_fk_aluno_fkey] FOREIGN KEY ([fk_aluno]) REFERENCES [dbo].[Aluno]([id_aluno]) ON DELETE NO ACTION ON UPDATE CASCADE;

COMMIT TRAN;

END TRY
BEGIN CATCH

IF @@TRANCOUNT > 0
BEGIN
    ROLLBACK TRAN;
END;
THROW

END CATCH
