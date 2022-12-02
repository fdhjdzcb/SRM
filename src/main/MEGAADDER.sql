create table orders
(
    id            integer                                                    not null
        primary key,
    product_name  varchar(100)     default 'Нет названия'::character varying not null,
    max_price     double precision default 0                                 not null,
    count         integer          default 0                                 not null,
    expected_date date             default CURRENT_DATE                      not null,
    description   varchar(100),
    real_price    double precision default 0,
    real_date     date             default CURRENT_DATE,
    author        varchar(50),
    is_approved   boolean          default false,
    provider      varchar(20)
);

insert into public.orders (id, product_name, max_price, count, expected_date, description, real_price, real_date, author, is_approved, provider)
values  (13, 'Качели', 250000, 10, '2022-12-04', 'Детские качели для качания во дворе', 0, null, '11111111', true, null),
        (9, 'Стол', 5500, 20, '2022-12-09', 'Письменный стол', 0, null, '11111111', false, null),
        (2, 'Камера', 80000, 2, '2022-11-02', 'Камера Sony AS-7', 75000, '2022-09-14', '11111111', false, 'Поставщик 5'),
        (4, 'Тапки', 150, 100, '2022-11-20', 'Гучи-тапки', 135, '2022-11-16', '11111111', true, 'Поставщик 4'),
        (1, 'Качели', 250000, 3, '2022-12-11', 'Детские качели для качания во дворе', 237500, '2022-11-20', '11111111', true, 'Поставщик 2'),
        (21, '564', 456, 456, '6644-05-04', '645', 0, null, '11111111', false, null);

create table past_orders
(
    id            integer                                                    not null
        primary key,
    product_name  varchar(100)     default 'Нет названия'::character varying not null,
    max_price     double precision default 0                                 not null,
    count         integer          default 0                                 not null,
    expected_date date             default CURRENT_DATE                      not null,
    description   varchar(100),
    real_price    double precision default 0,
    real_date     date             default CURRENT_DATE,
    status        varchar          default 'finished'::character varying     not null,
    author        varchar(50),
    provider      varchar(20)
);

insert into public.past_orders (id, product_name, max_price, count, expected_date, description, real_price, real_date, status, author, provider)
values  (6, 'Бетономешалка', 5000000, 1, '2022-11-24', 'Мешает бетон', 0, '2022-11-24', 'canceled', '11111111', null),
        (10, 'Зубная паста', 150, 100, '2022-12-02', 'Зубная паста чистит зубы щеткой', 0, null, 'canceled', '11111111', null),
        (19, '8', 8, 8, '2022-12-03', '8', 0, null, 'canceled', '11111111', null),
        (12, 'База данных', 1000000, 1, '2022-12-22', '2кк строк пользователей яндекса', 0, null, 'canceled', '11111111', null),
        (8, 'Курсовая', 200, 4, '2022-12-10', 'Курсовая по CALS и ООП', 195.93, '2022-12-10', 'completed', '22222222', 'Поставщик 2'),
        (20, 'Функционал системы', 1000, 2, '2022-12-20', 'Функционал системы AMIR', 990.82, '2022-12-16', 'completed', '11111111', 'Поставщик 1'),
        (7, 'Монитор', 15000, 5, '2022-11-11', 'Монитор LG 25'''' 1920:1080 144 Hz', 15000, '2022-11-02', 'completed', '11111111', 'Поставщик 3'),
        (5, 'RTX 2080', 20800, 1289, '2022-08-20', 'Видеокарта NVIDIA Geforce RTX 2080 16GB', 0, null, 'canceled', '11111111', null);

create table roles
(
    id   integer     not null
        primary key
        constraint fkoxu306cef946ub0alay6phtf9
            references users,
    role varchar(10) not null
);

insert into public.roles (id, role)
values  (44, 'USER'),
        (42, 'DIRECTOR'),
        (47, 'DIRECTOR'),
        (45, 'ADMIN');

create table users
(
    email           varchar(50)
        constraint "Users_email_key"
            unique,
    password        varchar(50) not null,
    id              serial
        primary key
        unique,
    username        varchar(50),
    active          boolean default false,
    activation_code varchar(100)
);

insert into public.users (email, password, id, username, active, activation_code)
values  ('hanici3366@xegge.com', '11111111', 45, '11111111', true, null),
        ('xogitib876@sunetoa.com', '22222222', 47, '22222222', true, null),
        ('hagom37236@lidely.com', '134567we34r', 42, '2453w6723456', true, null),
        ('sesaf23474@sopulit.com', '3245433241', 44, 'BestDirectorOfTheWorld', true, null);