require('dotenv/config')
const { create } = require('domain')
const { prisma } = require('../database/prismaClient')
const { hash, genSalt, compare } = require('bcrypt')
const { sign } = require('jsonwebtoken')

const validateEmail = (email) => {
  return String(email)
    .toLowerCase()
    .match(
      /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    )
}

/*function validateEmail(email) {
  var re = /\S+@\S+\.\S+/
  return re.test(email)
}*/

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

    if (validateEmail(email_aluno)) {
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
    } else {
      return res.status(400).json({ message: 'Informações incorretas!' })
    }
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
/*
  const { id } = req.params

  const { anotacao } = req.body

  const updateAnotacao = await prisma.anotacao.update({
    where: { id_anotacao: Number(id) },
    data: {
      anotacao: anotacao,
    },
    select: {
      id_anotacao: true,
    },*/

  async update(req, res) {

    const { id } = req.params
    const { email_aluno, senha_aluno } = req.body

    const updateAluno = await prisma.aluno.update({
      where: { id_aluno: Number(id) },
        data: {
          email_aluno: email_aluno,
          senha_aluno: senha_aluno,
        },
        select: {
          id_aluno: true,
        }
    })
    return res.status(201).json({ id: updateAluno.id_aluno });
  },

  async delete(req, res) {
    const { id } = req.params
    return res.json(
      await prisma.aluno.delete({
        where: { id_aluno: Number(id) },
        select: {
          nome_aluno: true,
        },
      })
    )
  },
}
