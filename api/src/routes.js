//import { PrismaClient } from '@prisma/client';
import { Router } from 'express';

//const prisma = new PrismaClient();

const router = Router();

router.get('/teste', async (req, res) => {
    res.send("GET REQUEST OK");
})

export { router }