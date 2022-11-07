require('dotenv/config')
const { create } = require('domain')
const { Int } = require('mssql')
const { json } = require('stream/consumers')
const { prisma } = require('../database/prismaClient')

module.exports = {
  async getAll(req, res) {
    return res.json(
      await prisma.anotacao.findMany({
        select: {
          id_anotacao: true,
          anotacao: true,
          data_inicio: true,
          data_entrega: true,
          fk_aluno: true,
        },
      })
    )
  },

  async getByDateFinal(req, res) {
    const { date } = req.params

    console.log(String.toString(date))

    return res.json(
      await prisma.anotacao.findMany({
        where: { 
          //data_entrega: "2022-11-09T00:00:00.000Z"
          data_entrega: Date.toString(date)
        },
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
    const { anotacao, data_inicio, data_entrega, fk_aluno } = req.body

    const nota = await prisma.anotacao.create({
      data: {
        anotacao,
        data_inicio, //erro
        data_entrega,
        fk_aluno,
      },
      select: {
        id_anotacao: true,
      },
    })

    return res.status(201).json({ id: nota.id_anotacao })
  },

  async delete(req, res) {
    const { id } = req.params

    return res.json(
      await prisma.anotacao.delete({
        where: { id_anotacao: Number(id) },
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
