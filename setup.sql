DROP TABLE IF EXISTS account CASCADE;
DROP TABLE IF EXISTS auction CASCADE;
DROP TABLE IF EXISTS lot CASCADE;
DROP TABLE IF EXISTS bid CASCADE;
DROP TABLE IF EXISTS account_auction CASCADE;

DROP TYPE IF EXISTS user_role CASCADE;
DROP TYPE IF EXISTS auction_status CASCADE;
DROP TYPE IF EXISTS lot_status CASCADE;


CREATE TABLE IF NOT EXISTS account (
                                       id SERIAL NOT NULL,
                                       name VARCHAR NOT NULL,
                                       email VARCHAR NOT NULL,
                                       password VARCHAR NOT NULL,
                                       PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS auction (
                                       id SERIAL NOT NULL,
                                       name VARCHAR NOT NULL,
                                       description VARCHAR,
                                       start_timestamp TIMESTAMP,
                                       end_timestamp TIMESTAMP,
                                       PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS lot (
                                   id SERIAL NOT NULL,
                                   name VARCHAR NOT NULL,
                                   description VARCHAR,
                                   image_url VARCHAR,
                                   start_price NUMERIC(10, 2) DEFAULT 0.00,
    order_num INT NOT NULL,
    auction_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (auction_id)
    REFERENCES auction(id)
    );

CREATE TABLE IF NOT EXISTS bid (
                                   id SERIAL NOT NULL,
                                   amount NUMERIC(10, 2) NOT NULL,
    time_created TIMESTAMP NOT NULL,
    account_id INT NOT NULL,
    lot_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (account_id)
    REFERENCES account(id),
    FOREIGN KEY (lot_id)
    REFERENCES lot(id)
    );



-- Insert a basic account
INSERT INTO account (name, email, password) VALUES ('John Doe', 'john.doe@example.com', 'password123');
INSERT INTO account (name, email, password) VALUES ('Jane Smith', 'jane.smith@example.com', 'password456');

-- Insert a basic auction
INSERT INTO auction (name, description, start_timestamp, end_timestamp)
VALUES ('Cool Auction', 'Auction of Ukrainian items', '2024-10-15 10:00:00', '2024-10-15 13:00:00');

-- Insert a lot linked to the auction
INSERT INTO lot (name, description, image_url, start_price, order_num, auction_id) VALUES ('Vintage Vase', 'A beautiful vintage vase from the 19th century', 'https://res.cloudinary.com/dewzwcjc4/image/upload/cld-sample-5.jpg', 100.00, 1, 1);

-- Insert bids linked to the lot and accounts
INSERT INTO bid (amount, time_created, account_id, lot_id) VALUES (120.00, '2024-10-15 10:15:00', 1, 1);
INSERT INTO bid (amount, time_created, account_id, lot_id) VALUES (150.00, '2024-10-15 10:30:00', 2, 1);