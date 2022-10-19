/*
  Warnings:

  - A unique constraint covering the columns `[ra_aluno]` on the table `AlunoTurma` will be added. If there are existing duplicate values, this will fail.

*/
BEGIN TRY

BEGIN TRAN;

-- CreateIndex
ALTER TABLE [dbo].[AlunoTurma] ADD CONSTRAINT [AlunoTurma_ra_aluno_key] UNIQUE NONCLUSTERED ([ra_aluno]);

COMMIT TRAN;

END TRY
BEGIN CATCH

IF @@TRANCOUNT > 0
BEGIN
    ROLLBACK TRAN;
END;
THROW

END CATCH
