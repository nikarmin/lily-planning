/*
  Warnings:

  - You are about to drop the column `fk_professor` on the `Anotacao` table. All the data in the column will be lost.
  - You are about to drop the column `fk_aluno` on the `CalendarioAluno` table. All the data in the column will be lost.
  - You are about to drop the column `fk_professor` on the `CalendarioProfessor` table. All the data in the column will be lost.

*/
BEGIN TRY

BEGIN TRAN;

-- DropForeignKey
ALTER TABLE [dbo].[Anotacao] DROP CONSTRAINT [Anotacao_fk_professor_fkey];

-- AlterTable
ALTER TABLE [dbo].[Anotacao] DROP COLUMN [fk_professor];

-- AlterTable
ALTER TABLE [dbo].[CalendarioAluno] DROP COLUMN [fk_aluno];

-- AlterTable
ALTER TABLE [dbo].[CalendarioProfessor] DROP COLUMN [fk_professor];

COMMIT TRAN;

END TRY
BEGIN CATCH

IF @@TRANCOUNT > 0
BEGIN
    ROLLBACK TRAN;
END;
THROW

END CATCH
