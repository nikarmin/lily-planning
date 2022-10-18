//import { PrismaClient } from '@prisma/client';
const { Router } = require('express')
const alunoController = require('./controllers/alunoController')
const anotacaoController = require('./controllers/anotacaoController')
const professorController = require('./controllers/professorController')
const todolistController = require('./controllers/todolistController')

//const prisma = new PrismaClient();

const router = Router()

router.get('/', async (req, res) => {
  res.send('get request')
})

// alunos
router.get('/alunos', alunoController.getAll)
router.get('/alunos/:email', alunoController.getByEmail)
router.post('/alunos', alunoController.create)
router.post('/alunos/auth', alunoController.auth)
router.put('/alunos/:id', alunoController.update)
router.delete('/alunos/:id', alunoController.delete)

// professores
router.get('/professores', professorController.getAll)
router.get('/professores/:email', professorController.getByEmail)
router.post('/professores', professorController.create)
router.post('/professores/auth', professorController.auth)
router.put('/professores/:id', professorController.update)
router.delete('/professores/:id', professorController.delete)

// todolist
router.get('/tarefas', todolistController.getAll)
router.get('/tarefas/:id', todolistController.getById)
router.post('/tarefas', todolistController.create)
router.delete('/tarefas/:id', todolistController.delete)

// anotacao
router.get('/anotacoes', anotacaoController.getAll)
router.get('/anotacoes/:id', anotacaoController.getById)
router.post('/anotacoes', anotacaoController.create)
router.delete('/anotacoes/:id', anotacaoController.delete)
router.put('/anotacoes/:id', anotacaoController.update)

module.exports = { router }
