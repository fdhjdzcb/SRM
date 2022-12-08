drop table if exists roles cascade;
drop table if exists users cascade;
drop table if exists orders;
drop table if exists past_orders;
drop table if exists news;

create table users
(
    email           varchar(50)
        constraint "Users_email_key"
            unique,
    password        varchar(70) not null,
    id              serial
        primary key
        unique,
    username        varchar(50),
    active          boolean default false,
    activation_code varchar(100),
    name            varchar(20),
    surname         varchar(20)
);

create table roles
(
    id   integer     not null
        primary key
        constraint fkoxu306cef946ub0alay6phtf9
            references users,
    role varchar(10) not null
);

create table orders
(
    id            integer                               not null
        primary key,
    product_name  varchar(100)     default 'Нет названия'::character varying not null,
    max_price     double precision default 0            not null,
    count         integer          default 0            not null,
    expected_date date             default CURRENT_DATE not null,
    description   varchar(100),
    real_price    double precision default 0,
    real_date     date             default CURRENT_DATE,
    author        varchar(50),
    is_approved   boolean          default false,
    provider      varchar(20)
);

create table past_orders
(
    id            integer                               not null
        primary key,
    product_name  varchar(100)     default 'Нет названия'::character varying not null,
    max_price     double precision default 0            not null,
    count         integer          default 0            not null,
    expected_date date             default CURRENT_DATE not null,
    description   varchar(100),
    real_price    double precision default 0,
    real_date     date             default CURRENT_DATE,
    status        varchar          default 'finished'::character varying not null,
    author        varchar(50),
    provider      varchar(20)
);

create table news
(
    author   varchar(30),
    text     varchar(300),
    pub_date date,
    title    varchar(50),
    id       integer
);

insert into public.users (email, password, username, active, activation_code, name, surname)
values  ('hejaca8912@diratu.com', '$2a$08$NMnQSdJMp8ZHc5YoVWWFVu99AqJyh59gRqxOCpV0BQ.t6uWZPIaTC', '33333333', true, null, 'Амир', 'Галимуллин'),
        ('vitina8738@edinel.com', '$2a$08$9EYtnzUCj/kMLzbjltahfuDVRAXq/nnD1kl.DSX3VHHi7eEwkhXVW', '11111111', true, null, 'Админ', 'Админов'),
        ('kirot35533@eilnews.com', '$2a$08$L4o5a/.EA1hn2C/XH.lf6u82h8.P2ZRNMnVtp3PkFooe5Tbt9D3jW', 'director', true, null, 'Директор', 'Директоров');

insert into public.roles (role)
values  ('USER'),
        ('ADMIN'),
        ('DIRECTOR');

insert into public.orders (product_name, max_price, count, expected_date, description, real_price, real_date, author, is_approved, provider)
values  ('Заказ', 1000, 10, '2022-12-16', 'Описание заказа', 0, null, '11111111', false, null),
        ('товар', 200, 2, '2022-12-17', 'товар', 0, null, '11111111', false, null),
        ('впава', 3000, 7, '2022-12-13', 'ыпвавпаы', 0, null, '11111111', false, null),
        ('Курсовая', 200, 4, '2022-12-10', 'Курсовая по CALS и ООП', 0, null, '22222222', true, null),
        ('RTX 2080', 20800, 10, '2022-12-17', 'Видеокарта NVIDIA Geforce RTX 2080 16GB', 20404, '2022-12-15', '11111111', true, 'Поставщик 3');

insert into public.past_orders (product_name, max_price, count, expected_date, description, real_price, real_date, status, author, provider)
values  ('Зубная паста', 150, 100, '2022-12-02', 'Зубная паста чистит зубы щеткой', 0, null, 'canceled', '11111111', null),
        ('База данных', 1000000, 1, '2022-12-22', '2кк строк пользователей яндекса', 0, null, 'canceled', '11111111', null),
        ('Курсовая', 200, 4, '2022-12-10', 'Курсовая по CALS и ООП', 195.93, '2022-12-10', 'completed', '22222222', 'Поставщик 2'),
        ('Функционал системы', 1000, 2, '2022-12-20', 'Функционал системы AMIR', 990.82, '2022-12-16', 'completed', '11111111', 'Поставщик 1'),
        ('Монитор', 15000, 5, '2022-11-11', 'Монитор LG 25'''' 1920:1080 144 Hz', 15000, '2022-11-02', 'completed', '11111111', 'Поставщик 3'),
        ('RTX 2080', 20800, 1289, '2022-08-20', 'Видеокарта NVIDIA Geforce RTX 2080 16GB', 0, null, 'canceled', '11111111', null),
        ('Камера', 80000, 2, '2022-11-02', 'Камера Sony AS-7', 75000, '2022-09-14', 'canceled', '11111111', 'Поставщик 5'),
        ('Тапки', 150, 100, '2022-11-20', 'Гучи-тапки', 135, '2022-11-16', 'completed', '11111111', 'Поставщик 4'),
        ('Качели', 250000, 3, '2022-12-11', 'Детские качели для качания во дворе', 237500, '2022-11-20', 'completed', '11111111', 'Поставщик 2'),
        ('Монитор', 15000, 5, '2022-11-11', 'Монитор LG 25'''' 1920:1080 144 Hz', 0, null, 'canceled', '11111111', null),
        ('Качели', 250000, 3, '2022-12-11', 'Детские качели для качания во дворе', 0, null, 'canceled', '11111111', null),
        ('Бетономешалка', 5000000, 1, '2022-11-24', 'Мешает бетон', 0, null, 'canceled', '11111111', null),
        ('Стол', 5500, 20, '2022-12-09', 'Письменный стол', 0, null, 'canceled', '11111111', null),
        ('Функционал системы', 1000, 2, '2022-12-20', 'Функционал системы AMIR', 0, null, 'canceled', '11111111', null),
        ('Курсовая', 200, 4, '2022-12-10', 'Курсовая по CALS и ООП', 193.08, '2022-12-06', 'completed', '22222222', 'Поставщик 1');

insert into public.news (author, text, pub_date, title, id)
values  ('11111111', 'Я добавил новости в 13:44 06.12.2022 вот это да!!!!!!!!!', '2022-12-06', 'Я добавил новости', 34),
        ('11111111', 'esrdtyuio;lkjfhxdg', '2022-12-06', 'fesgruk', 35),
        ('11111111', '123423142314342234', '2022-12-06', '1231324312', 36),
        ('11111111', '423412314321423412341', '2022-12-06', '243132412341', 37),
        ('11111111', '32412431243134212314', '2022-12-06', '342121432432314', 38),
        ('11111111', 'fdsgdgfsdgfdgfsdgfsdfgs', '2022-12-06', 'sdgffgsdfdgssfdg', 39);