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
                                   highest_bid INT,
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



ALTER TABLE lot
    ADD CONSTRAINT fk_highest_bid
        FOREIGN KEY (highest_bid)
            REFERENCES bid(id);


CREATE TABLE IF NOT EXISTS account_auction (
                                               account_id INT NOT NULL,
                                               auction_id INT NOT NULL,
                                               PRIMARY KEY (account_id, auction_id),
                                               FOREIGN KEY (account_id)
                                                   REFERENCES account(id),
                                               FOREIGN KEY (auction_id)
                                                   REFERENCES auction(id)
);

-- Insert a basic auction
INSERT INTO auction (name, description, start_timestamp, end_timestamp)
VALUES ('Cool Auction', 'Auction of Ukrainian items', '2024-10-15 10:00:00', '2024-10-15 13:00:00');

-- Insert a lot linked to the auction
INSERT INTO lot (name, image_url, start_price, order_num, auction_id)
VALUES ('Raspberry Jam', 'images/lots/Jam.jpg', 35.00, 1, 1);
INSERT INTO lot (name, image_url, start_price, order_num, auction_id)
VALUES ('Original Embroidery Shirt 1960th', 'images/lots/Vyshyvanka.jpg', 80.00, 1, 1);

INSERT INTO lot (name, image_url, start_price, order_num, auction_id)
VALUES ('Marble Statue with flower pot', 'images/lots/Statue.jpg', 110.00, 1, 1);

INSERT INTO lot (name, image_url, start_price, order_num, auction_id)
VALUES ('Traditional Cherry Liquor Set', 'images/lots/Vyshnia.jpg', 90.00, 1, 1);

INSERT INTO lot (name, image_url, start_price, order_num, auction_id)
VALUES ('Artillery Shell Vase', 'images/lots/Artillery.jpg', 160.00, 1, 1);

INSERT INTO lot (name, image_url, start_price, order_num, auction_id)
VALUES ('Premium Ukrainian Chocolate', 'images/lots/Chocolate.jpg', 30.00, 1, 1);