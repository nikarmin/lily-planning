require('dotenv/config')
const { create } = require('domain')
const { prisma } = require('../database/prismaClient')
const { hash, genSalt, compare } = require('bcrypt')
const { sign } = require('jsonwebtoken')

module.exports = {
  async getAll(req, res) {
    return res.json(
      await prisma.notas.findMany({
        select: {
          id_notas: true,
          nota: true,
          bimestre: true,
          fk_materia: true,
          fk_aluno_turma: true,
        },
      })
    )
  },

  async getById(req, res) {
    const { id } = req.params

    return res.json(
      await prisma.notas.findUnique({
        where: { id_notas: Number(id) },
        select: {
          id_notas: true,
          nota: true,
          bimestre: true,
          fk_materia: true,
          fk_aluno_turma: true,
        },
      })
    )
  },

  async create(req, res) {
    const { nota, bimestre, fk_materia, fk_aluno_turma } = req.body

    const notaCreate = await prisma.notas.create({
      data: {
        nota,
        bimestre,
        fk_materia,
        fk_aluno_turma,
      },
      select: {
        id_notas: true,
      },
    })

    return res.status(201).json({ id: notaCreate.id_notas })
  },

  async update(req, res) {
    const { id } = req.params
    const { nota, bimestre } = req.body

    const updateNota = await prisma.notas.update({
      where: { id_notas: Number(id) },
      data: {
        nota: nota,
        bimestre: bimestre,
      },
      select: {
        id_notas: true,
      },
    })
    return res.status(201).json({ id: updateNota.id_notas })
  },

  async delete(req, res) {
    const { id } = req.params

    return res.json(
      await prisma.notas.delete({
        where: { id_notas: Number(id) },
        select: {
          nota: true,
        },
      })
    )
  },
}
