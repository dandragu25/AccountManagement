INSERT INTO ACCOUNTS (USER_NAME, EMAIL, PASSWORD, NAME, ROLE)
VALUES ('admin', 'user1@testmail.com', '$2a$10$4nWWhBuAIY8hqMyXyIiWHuRQm2P9F4CpDrO2wL/thaF33piic734W', 'Administrator', 'ROLE_ADMIN'),
       ('user1', 'user1@testmail.com', '$2a$10$4nWWhBuAIY8hqMyXyIiWHuRQm2P9F4CpDrO2wL/thaF33piic734W', 'User 1', 'ROLE_USER');


INSERT INTO TRANSACTIONS (ACCOUNT_ID, AMOUNT, TIMESTAMP, DETAILS) VALUES (2, 2.5,  CURRENT_TIMESTAMP(), 'test');

