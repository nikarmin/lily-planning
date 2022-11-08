require('dotenv/config')
const { create } = require('domain')
const { Int } = require('mssql')
const { json } = require('stream/consumers')
const { prisma } = require('../database/prismaClient')

module.exports = {
  async getAll(req, res) {
    return res.json(
      await prisma.upload.findMany({
        select: {
          id_upload: true,
          url_foto: true,
        },
      })
    )
  },
  async getById(req, res) {
    const { id } = req.params

    return res.json(
      await prisma.upload.findUnique({
        where: { id_upload: Number(id) },
        select: {
          id_upload: true,
          url_foto: true,
        },
      })
    )
  },
  async create(req, res) {
    const { url_foto } = req.body

    const upload = await prisma.upload.create({
      data: {
        url_foto,
      },
      select: {
        id_upload: true,
      },
    })

    return res.status(201).json({ id: upload.id_upload })
  },
  async delete(req, res) {
    const { id } = req.params

    return res.json(
      await prisma.upload.delete({
        where: { id_upload: Number(id) },
        select: {
          url_foto: true,
        },
      })
    )
  },
}
