require('dotenv/config')
const { create } = require('domain')
const { json } = require('stream/consumers')
const { prisma } = require('../database/prismaClient')

module.exports = {
  async getAll(req, res) {
    return res.json(
      await prisma.turma.findMany({
        select: {
          id_turma: true,
          nome_turma: true,
          qtd_alunos: true,
        },
      })
    )
  },

  async getById(req, res) {
    const { id } = req.params

    return res.json(
      await prisma.turma.findUnique({
        where: { id_turma: Number(id) },
        select: {
          id_turma: true,
          nome_turma: true,
          qtd_alunos: true,
        },
      })
    )
  },

  async create(req, res) {
    const { nome_turma, qtd_alunos } = req.body

    const turma = await prisma.turma.create({
      data: {
        nome_turma,
        qtd_alunos,
      },
      select: {
        id_turma: true,
      },
    })

    return res.status(201).json({ id: turma.id_turma })
  },

  async update(req, res) {
    const { id } = req.params
    const { nome_turma, qtd_alunos } = req.body

    const updateTurma = await prisma.turma.update({
      where: { id_turma: Number(id) },
      data: {
        nome_turma: nome_turma,
        qtd_alunos: qtd_alunos,
      },
      select: {
        id_turma: true,
      },
    })
    return res.status(201).json({ id: updateTurma.id_turma })
  },

  async delete(req, res) {
    const { id } = req.params

    return res.json(
      await prisma.turma.delete({
        where: { id_turma: Number(id) },
        select: {
          id_turma: true,
        },
      })
    )
  },

  /*
      id_turma    Int          @id @default(autoincrement())
  nome_turma  String
  qtd_alunos  Int
  aluno_turma AlunoTurma[]
      
      */
}
