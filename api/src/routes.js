//import { PrismaClient } from '@prisma/client';
const { Router } = require('express')
const alunoController = require('./controllers/alunoController')
const aluno_turmaController = require('./controllers/aluno_turmaController')
const anotacaoController = require('./controllers/anotacaoController')
const materiaController = require('./controllers/materiaController')
const notaController = require('./controllers/notaController')
const professorController = require('./controllers/professorController')
const todolistController = require('./controllers/todolistController')
const turmaController = require('./controllers/turmaController')

//const prisma = new PrismaClient();

const router = Router()

router.get('/', async (req, res) => {
  res.send('get request')
})

// alunos
router.get('/alunos', alunoController.getAll)
router.get('/alunos/:email', alunoController.getByEmail)
router.get('/alunos/token/:token', alunoController.getByToken)
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
router.get('/anotacoes/:id', anotacaoController.getByFk)
router.post('/anotacoes', anotacaoController.create)
router.delete('/anotacoes/:id', anotacaoController.delete)
router.put('/anotacoes/:id', anotacaoController.update)

// materia

router.get('/materias', materiaController.getAll)
router.get('/materias/:id', materiaController.getById)
router.post('/materias', materiaController.create)
router.delete('/materias/:id', materiaController.delete)
router.put('/materias/:id', materiaController.update)

// nota

router.get('/notas', notaController.getAll)
router.get('/notas/:id', notaController.getById)
router.post('/notas', notaController.create)
router.delete('/notas/:id', notaController.delete)
router.put('/notas/:id', notaController.update)

// turma

router.get('/turmas', turmaController.getAll)
router.get('/turmas/:id', turmaController.getById)
router.post('/turmas', turmaController.create)
router.delete('/turmas/:id', turmaController.delete)
router.put('/turmas/:id', turmaController.update)

// alunoTurma

router.get('/alunosturmas', aluno_turmaController.getAll)
router.get('/alunosturmas/:id', aluno_turmaController.getById)
router.post('/alunosturmas', aluno_turmaController.create)
router.delete('/alunosturmas/:id', aluno_turmaController.delete)
router.put('/alunosturmas/:id', aluno_turmaController.update)

module.exports = { router }
