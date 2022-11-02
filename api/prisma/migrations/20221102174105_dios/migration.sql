/*
  Warnings:

  - You are about to drop the `ToDoList` table. If the table is not empty, all the data it contains will be lost.
  - Added the required column `data_entrega` to the `Anotacao` table without a default value. This is not possible if the table is not empty.

*/
BEGIN TRY

BEGIN TRAN;

-- DropForeignKey
ALTER TABLE [dbo].[ToDoList] DROP CONSTRAINT [ToDoList_fk_anotacao_fkey];

-- AlterTable
ALTER TABLE [dbo].[Anotacao] ADD [data_entrega] DATE NOT NULL,
[data_inicio] DATE NOT NULL CONSTRAINT [Anotacao_data_inicio_df] DEFAULT CURRENT_TIMESTAMP;

-- DropTable
DROP TABLE [dbo].[ToDoList];

COMMIT TRAN;

END TRY
BEGIN CATCH

IF @@TRANCOUNT > 0
BEGIN
    ROLLBACK TRAN;
END;
THROW

END CATCH
