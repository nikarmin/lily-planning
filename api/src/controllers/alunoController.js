require('dotenv/config')
const { create } = require('domain')
const { prisma } = require('../database/prismaClient')
const { hash, genSalt, compare } = require('bcrypt')
const { sign } = require('jsonwebtoken')

module.exports = {
  async getAll(req, res) {
    return res.json(
      await prisma.aluno.findMany({
        select: {
          id_aluno: true,
          nome_aluno: true,
          email_aluno: true,
          calendario_aluno: true,
        },
      })
    )
  },

  async getById(req, res) {
    const { id } = req.params

    return res.json(
      await prisma.aluno.findUnique({
        where: { id_aluno: Number(id) },
        select: {
          id_aluno: true,
          nome_aluno: true,
          email_aluno: true,
          calendario_aluno: true,
        },
      })
    )
  },

  async create(req, res) {
    const { nome_aluno, senha_aluno, email_aluno } = req.body

    const aluno = await prisma.aluno.create({
      data: {
        email_aluno,
        nome_aluno,
        senha_aluno: await hash(senha_aluno, await genSalt()),
      },
      select: {
        id_aluno: true,
      },
    })

    return res.status(201).json({ id: aluno.id_aluno })
  },

  async auth(req, res) {
    const { email_aluno, senha_aluno } = req.body

    const aluno = await prisma.aluno.findUnique({ where: { email_aluno } })

    const authenticated = await compare(senha_aluno, aluno.senha_aluno)

    if (authenticated) {
      const token = sign(
        {
          id_aluno: aluno.id_aluno,
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
