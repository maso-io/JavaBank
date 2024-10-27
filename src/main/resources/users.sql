CREATE TABLE Users (
account_number IDENTITY(100000000, 1) PRIMARY KEY,
pin_number VARCHAR(5) NOT NULL,
amount INT DEFAULT 0
);

TRUNCATE TABLE Users; -- Ensure to start with an empty database
INSERT INTO Users (pin_number) values ('12345'); -- Insert users into the database
INSERT INTO Users (pin_number) values ('22145');
INSERT INTO Users (pin_number) values ('78781');
