require('dotenv/config')
const { create } = require('domain')
const { prisma } = require('../database/prismaClient')

module.exports = {
  async getAll(req, res) {
    return res.json(
      await prisma.toDoList.findMany({
        select: {
          id_todo_list: true,
          data_inicio: true,
          data_entrega: true,
          nome_lista: true,
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
    const { data_inicio, data_entrega, nome_lista, anotacao } = req.body

    const todolist = await prisma.toDoList.create({
      data: {
        data_inicio,
        data_entrega,
        nome_lista,
        anotacao,
      },
      select: {
        id_todo_list: true,
      },
    })

    return res.status(201).json({ id: todolist.id_todo_list })
  },
}
