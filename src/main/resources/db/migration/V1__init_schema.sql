CREATE TABLE IF NOT EXISTS category
(
    id   SERIAL PRIMARY KEY,
    name   character varying(250) NOT NULL,
    active boolean                NOT NULL,
    created_by         character varying(255),
    date_created       timestamp,
    last_modified_by   character varying(255),
    last_modified_date timestamp
    );

CREATE TABLE IF NOT EXISTS birds_purchased
(
    id     SERIAL PRIMARY KEY,
    name   character varying(250) NOT NULL,
    purchased_date   character varying(255),
    number_of_birds   integer,
    prices   DOUBLE PRECISION,
    active boolean                NOT NULL,
    created_by         character varying(255),
    date_created       timestamp,
    last_modified_by   character varying(255),
    last_modified_date timestamp
    );

CREATE TABLE IF NOT EXISTS birds_mortalities
(
    id     SERIAL PRIMARY KEY,
    name   character varying(250) NOT NULL,
    number_of_birds   integer,
    active boolean                NOT NULL,
    created_by         character varying(255),
    date_created       timestamp,
    last_modified_by   character varying(255),
    last_modified_date timestamp
    );

CREATE TABLE IF NOT EXISTS feeds
(
    id     SERIAL PRIMARY KEY,
    name   character varying(250) NOT NULL,
    quantity   integer,
    date   character varying(250) NOT NULL,
    price   DOUBLE PRECISION,
    total_price   DOUBLE PRECISION,
    active boolean                NOT NULL,
    currency         character varying(255),
    created_by         character varying(255),
    date_created       timestamp,
    last_modified_by   character varying(255),
    last_modified_date timestamp
    );

CREATE TABLE IF NOT EXISTS birds
(
    id     SERIAL PRIMARY KEY,
    name   character varying(250) NOT NULL,
    number_of_birds   decimal,
    date   character varying(250) NOT NULL,
    house   character varying(250) NOT NULL,
    category_id integer NOT NULL,
    active boolean                NOT NULL,
    created_by         character varying(255),
    date_created       timestamp,
    last_modified_by   character varying(255),
    last_modified_date timestamp
    );

CREATE TABLE IF NOT EXISTS eggs
(
    id  SERIAL PRIMARY KEY,
    date   character varying(250) NOT NULL,
    number_of_eggs   integer,
    bad_eggs   integer,
    trays DOUBLE PRECISION,
    notes   character varying(250) NOT NULL,
    active boolean NOT NULL,
    created_by         character varying(255),
    date_created       timestamp,
    last_modified_by   character varying(255),
    last_modified_date timestamp
    );

CREATE TABLE IF NOT EXISTS orders
(
    id  SERIAL PRIMARY KEY,
    phone_number  character varying(250) NOT NULL,
    prod_name   character varying(250) NOT NULL,
    cost  DOUBLE PRECISION,
    status character varying(250) NOT NULL,
    active boolean NOT NULL,
    created_by         character varying(255),
    date_created       timestamp,
    last_modified_by   character varying(255),
    last_modified_date timestamp
    );