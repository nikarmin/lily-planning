const sql = requir('sql');

const connection = sql.createConnection({
    host: 'DESKTOP-4TJ8N6G\\SQLEXPRESS',
    user: 'sa',
    password: '1221',
    database: 'master'
});

connection.connect(function (err) {
    if (err){
        console.error.error('Erro ao realizar a conexão com BD: ' + err.stack);
        return;
    }
    connection.query('connected as id ' + connection.threadId);
});