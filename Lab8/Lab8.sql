CREATE DATABASE TV00138_LAB8;
GO
USE TV00138_LAB8;
GO
CREATE TABLE accounts (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100),
    fullname NVARCHAR(100),
    email NVARCHAR(100)
);

INSERT INTO accounts (username, password, fullname, email)
VALUES ('admin', '123', N'Quản trị viên', 'admin@gmail.com');

ALTER TABLE accounts ADD role NVARCHAR(20);
UPDATE accounts SET role = 'admin' WHERE username = 'admin';

ALTER TABLE accounts ADD admin BIT DEFAULT 0;
UPDATE accounts SET admin = 1 WHERE username = 'admin';
