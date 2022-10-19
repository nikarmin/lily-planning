require('dotenv/config')
const { create } = require('domain')
const { json } = require('stream/consumers')
const { prisma } = require('../database/prismaClient')

module.exports = {
  async getAll(req, res) {
    return res.json(
      await prisma.alunoTurma.findMany({
        select: {
          id_aluno_turma: true,
          ra_aluno: true,
          fk_turma: true,
        },
      })
    )
  },

  async getById(req, res) {
    const { id } = req.params

    return res.json(
      await prisma.alunoTurma.findUnique({
        where: { id_aluno_turma: Number(id) },
        select: {
          id_aluno_turma: true,
          ra_aluno: true,
          fk_turma: true,
        },
      })
    )
  },

  async create(req, res) {
    const { ra_aluno, fk_turma } = req.body

    const alunoTurma = await prisma.alunoTurma.create({
      data: {
        ra_aluno,
        fk_turma,
      },
      select: {
        id_aluno_turma: true,
      },
    })

    return res.status(201).json({ id: alunoTurma.id_aluno_turma })
  },

  async update(req, res) {
    const { id } = req.params
    const { ra_aluno } = req.body

    const updateAlunoTurma = await prisma.alunoTurma.update({
      where: { id_aluno_turma: Number(id) },
      data: {
        ra_aluno: ra_aluno,
      },
      select: {
        id_aluno_turma: true,
      },
    })
    return res.status(201).json({ id: updateAlunoTurma.id_aluno_turma })
  },

  async delete(req, res) {
    const { id } = req.params

    return res.json(
      await prisma.alunoTurma.delete({
        where: { id_aluno_turma: Number(id) },
        select: {
          ra_aluno: true,
        },
      })
    )
  },

  /*
    id_aluno_turma Int     @id @default(autoincrement())
  ra_aluno       String
  turma          Turma   @relation(fields: [fk_turma], references: [id_turma])
  fk_turma       Int
  notas          Notas[]
    */
}
