DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS customers;



create table customers
(
    id         serial not null
        constraint customers_pkey1
            primary key,
    name       varchar,
    email      varchar,
    age        integer
);
create table accounts
(
    id          serial not null
        constraint accounts_pkey
            primary key,
    number      varchar,
    currency    varchar,
    balance     double precision,
    customer_id integer
        constraint "customerId"
            references customers
);
