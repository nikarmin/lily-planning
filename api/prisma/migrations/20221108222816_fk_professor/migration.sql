/*
  Warnings:

  - Added the required column `fk_professor` to the `Anotacao` table without a default value. This is not possible if the table is not empty.

*/
BEGIN TRY

BEGIN TRAN;

-- AlterTable
ALTER TABLE [dbo].[Anotacao] ADD [fk_professor] INT NOT NULL;

-- AddForeignKey
ALTER TABLE [dbo].[Anotacao] ADD CONSTRAINT [Anotacao_fk_professor_fkey] FOREIGN KEY ([fk_professor]) REFERENCES [dbo].[Professor]([id_professor]) ON DELETE NO ACTION ON UPDATE CASCADE;

COMMIT TRAN;

END TRY
BEGIN CATCH

IF @@TRANCOUNT > 0
BEGIN
    ROLLBACK TRAN;
END;
THROW

END CATCH
