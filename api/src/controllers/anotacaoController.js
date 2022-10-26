require('dotenv/config')
const { create } = require('domain')
const { json } = require('stream/consumers')
const { prisma } = require('../database/prismaClient')

module.exports = {
  async getAll(req, res) {
    return res.json(
      await prisma.anotacao.findMany({
        select: {
          id_anotacao: true,
          anotacao: true,
          fk_aluno: true,
        },
      })
    )
  },

  async getByFk(req, res) {
    const { id } = req.params

    return res.json(
      await prisma.anotacao.findMany({
        where: { fk_aluno: Number(id) },
        select: {
          id_anotacao: true,
          anotacao: true,
          fk_aluno: true,
        },
      })
    )
  },

  async getById(req, res) {
    const { id } = req.params

    return res.json(
      await prisma.anotacao.findUnique({
        where: { id_anotacao: Number(id) },
        select: {
          id_anotacao: true,
          anotacao: true,
          fk_aluno: true,
        },
      })
    )
  },

  async create(req, res) {
    const { anotacao, fk_aluno } = req.body
    
    const nota = await prisma.anotacao.create({
      data: {
        anotacao,
        fk_aluno,
      },
      select: {
        id_anotacao: true,
      },
    })

    return res.status(201).json({ id: nota.id_anotacao })
  },

  async delete(req, res) {
    const { id_anotacao } = req.params

    return res.json(
      await prisma.anotacao.delete({
        where: { id_anotacao: Number(id_anotacao) },
        select: {
          anotacao: true,
        },
      })
    )
  },

  async update(req, res) {
    const { id } = req.params

    const { anotacao } = req.body

    const updateAnotacao = await prisma.anotacao.update({
      where: { id_anotacao: Number(id) },
      data: {
        anotacao: anotacao,
      },
      select: {
        id_anotacao: true,
      },
    })

    return res.status(201).json({ id: updateAnotacao.id_anotacao })
  },
}
