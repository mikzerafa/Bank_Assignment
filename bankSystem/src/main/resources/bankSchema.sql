DROP TABLE IF EXISTS Account;

CREATE TABLE Account (
  accountNumber VARCHAR(250) PRIMARY KEY,
  beneficiaryName VARCHAR(250) NOT NULL,
  pinNumber VARCHAR(4) NOT NULL,
  funds Double
);