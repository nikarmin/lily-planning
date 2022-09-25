module.exports = {
    dialect: 'mssql',
    
    //name: "my-package",
    //scripts: {
    //    build: "babel src -d lib",
    //    test: "jest"
    //},

    host: 'DESKTOP-4TJ8N6G\\SQLEXPRESS',
    username: 'sa',
    password: '1221',
    database: 'master',
    define: {
        timestamps: true,
        underscored: true,
    },
};