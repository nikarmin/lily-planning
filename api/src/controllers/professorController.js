require('dotenv/config')
const { create } = require('domain')
const { prisma } = require('../database/prismaClient')

const { hash, genSalt, compare } = require('bcrypt')
const { sign } = require('jsonwebtoken')

module.exports = {
  async getAll(req, res) {
    return res.json(
      await prisma.professor.findMany({
        select: {
          id_professor: true,
          nome_professor: true,
          senha_professor: true,
          email_professor: true,
          calendario_professor: true,
        },
      })
    )
  },

  async getById(req, res) {
    const { id } = req.params

    return res.json(
      await prisma.professor.findUnique({
        where: { id_professor: Number(id) },
        select: {
          id_professor: true,
          senha_professor: true,
          email_professor: true,
          calendario_professor: true,
        },
      })
    )
  },

  async create(req, res) {
    const { nome_professor, senha_professor, email_professor } = req.body

    const professor = await prisma.professor.create({
      data: {
        email_professor,
        nome_professor,
        senha_professor: await hash(senha_professor, await genSalt()),
      },
      select: {
        id_professor: true,
      },
    })

    return res.status(201).json({ id: professor.id_professor })
  },

  async auth(req, res) {
    const { email_professor, senha_professor } = req.body

    const professor = await prisma.professor.findUnique({
      where: { email_professor },
    })

    const authenticated = await compare(
      senha_professor,
      professor.senha_professor
    )

    if (authenticated) {
      const token = sign(
        {
          id_professor: professor.id_professor,
        },
        process.env.SESSION_SECRET,
        {
          expiresIn: 86400, // 3 dias
        }
      )

      return res.json({ token })
    }

    return res.status(401).json({ message: 'Bad credentials' })
  },
}
