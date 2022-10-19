require('dotenv/config')
const { create } = require('domain')
const { json } = require('stream/consumers')
const { prisma } = require('../database/prismaClient')

module.exports = {
  async getAll(req, res) {
    return res.json(
      await prisma.materia.findMany({
        select: {
          id_materia: true,
          nome_materia: true,
          cor_etiqueta: true,
          notas: true,
        },
      })
    )
  },

  async getById(req, res) {
    const { id } = req.params

    return res.json(
      await prisma.materia.findUnique({
        where: { id_materia: Number(id) },
        select: {
          id_materia: true,
          nome_materia: true,
          cor_etiqueta: true,
          notas: true,
        },
      })
    )
  },

  async create(req, res) {
    const { nome_materia, cor_etiqueta } = req.body

    const materia = await prisma.materia.create({
      data: {
        nome_materia,
        cor_etiqueta,
      },
      select: {
        id_materia: true,
      },
    })

    return res.status(201).json({ id: materia.id_materia })
  },

  async update(req, res) {
    const { id } = req.params
    const { nome_materia, cor_etiqueta } = req.body

    const updateMateria = await prisma.materia.update({
      where: { id_materia: Number(id) },
      data: {
        nome_materia: nome_materia,
        cor_etiqueta: cor_etiqueta,
      },
      select: {
        id_materia: true,
      },
    })
    return res.status(201).json({ id: updateMateria.id_materia })
  },

  async delete(req, res) {
    const { id } = req.params

    return res.json(
      await prisma.materia.delete({
        where: { id_materia: Number(id) },
        select: {
          nome_materia: true,
        },
      })
    )
  },
}

/*
  id_materia   Int     @id @default(autoincrement())
  nome_materia String
  cor_etiqueta String?
  notas        Notas[]
*/
