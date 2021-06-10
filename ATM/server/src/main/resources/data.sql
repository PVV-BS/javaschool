DROP TABLE IF EXISTS Accounts;
DROP TABLE IF EXISTS Clients;

CREATE TABLE Clients (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR NOT NULL
);

CREATE TABLE Accounts (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  client_id  INT NOT NULL,
  balance DOUBLE DEFAULT 0,
  account VARCHAR NOT NULL,
  pin INT NOT NULL,
  currency VARCHAR NOT NULL,

  foreign key (client_id) references Clients(id)
);

INSERT INTO Clients (name) VALUES
  ('Mr. Jon'),
  ('Mr. Nikolas'),
  ('Mr. President');

INSERT INTO Accounts (client_id, balance, account, pin, currency) VALUES
  (1, 23123, '11111222334444455668', 5678, 'RUB'),
  (2, 3242342, '12345678901234567890', 8765, 'EUR'),
  (3, 21234234.32, '11111111111111111111', 1111, 'USD');
