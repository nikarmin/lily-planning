/*
  Warnings:

  - You are about to alter the column `data_inicio` on the `ToDoList` table. The data in that column could be lost. The data in that column will be cast from `DateTime2` to `Date`.
  - You are about to alter the column `data_entrega` on the `ToDoList` table. The data in that column could be lost. The data in that column will be cast from `DateTime2` to `Date`.

*/
BEGIN TRY

BEGIN TRAN;

-- AlterTable
ALTER TABLE [dbo].[ToDoList] ALTER COLUMN [data_inicio] DATE NOT NULL;
ALTER TABLE [dbo].[ToDoList] ALTER COLUMN [data_entrega] DATE NOT NULL;

COMMIT TRAN;

END TRY
BEGIN CATCH

IF @@TRANCOUNT > 0
BEGIN
    ROLLBACK TRAN;
END;
THROW

END CATCH
