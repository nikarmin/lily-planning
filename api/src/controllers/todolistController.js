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
          fk_anotacao: true,
        },
      })
    )
  },

  async getById(req, res) {
    const { id } = req.params

    return res.json(
      await prisma.toDoList.findUnique({
        where: { id_todo_list: Number(id) },
        select: {
          id_todo_list: true,
          data_inicio: true,
          data_entrega: true,
          nome_lista: true,
          fk_anotacao: true,
        },
      })
    )
  },

  async create(req, res) {
    const { data_inicio, data_entrega, nome_lista, fk_anotacao } = req.body

    const todolist = await prisma.toDoList.create({
      data: {
        data_inicio,
        data_entrega,
        nome_lista,
        fk_anotacao,
      },
      select: {
        id_todo_list: true,
      },
    })

    return res.status(201).json({ id: todolist.id_todo_list })
  },

  async update(req, res) {
    const { id } = req.params
    const { data_inicio, data_entrega, nome_lista, fk_anotacao } = req.body

    const updateTodolist = await prisma.toDoList.update({
      where: { id_todo_list: Number(id) },
      data: {
        data_inicio: data_inicio,
        data_entrega: data_entrega,
        nome_lista: nome_lista,
        fk_anotacao: fk_anotacao,
      },
      select: {
        id_todo_list: true,
      },
    })
    return res.status(201).json({ id: updateTodolist.id_todo_list })
  },

  async delete(req, res) {
    const { id } = req.params

    return res.json(
      await prisma.toDoList.delete({
        where: { id_todo_list: Number(id) },
        select: {
          id_todo_list: true,
        },
      })
    )
  },
}
