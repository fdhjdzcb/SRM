create table orders
(
    id            integer                                                    not null
        primary key,
    product_name  varchar(100)     default 'Нет названия'::character varying not null,
    max_price     double precision default 0                                 not null,
    count         integer          default 0                                 not null,
    expected_date varchar(10)      default CURRENT_DATE                      not null,
    description   varchar(100),
    real_price    double precision default 0,
    real_date     varchar(10)      default CURRENT_DATE,
    author        varchar(50),
    is_approved   boolean          default false
);

insert into public.orders (id, product_name, max_price, count, expected_date, description, real_price, real_date, author, is_approved)
values  (3, 'Качели', 250000, 3, '2022-12-11', 'Детские качели для качания во дворе', 0, '2022-11-23', '11111111', false),
        (4, 'Стол', 5500, 10, '2022-12-09', 'Письменный стол', 0, null, '11111111', false),
        (6, 'перфоратор', 1000, 1, '2022-12-01', 'сверлит', 0, null, '11111111', false),
        (7, 'Бипки', 999999, 10, '2023-08-21', 'Отсосешь - скажу', 0, null, '11111111', true),
        (5, 'RTX 2080', 20800, 2080, '2022-08-20', 'Видеокарта NVIDIA Geforce RTX 2080 16GB', 0, null, '11111111', true);

create table past_orders
(
    id            integer generated always as identity
        primary key,
    product_name  varchar(100)     default 'Нет названия'::character varying not null,
    max_price     double precision default 0                                 not null,
    count         integer          default 0                                 not null,
    expected_date date             default CURRENT_DATE                      not null,
    description   varchar(100),
    real_price    double precision default 0,
    real_date     date             default CURRENT_DATE,
    status        varchar          default 'finished'::character varying     not null,
    author        varchar(50)
);

insert into public.past_orders (id, product_name, max_price, count, expected_date, description, real_price, real_date, status, author)
values  (default, 'Качели', 250000, 3, '2022-12-11', 'Детские качели для качания во дворе', 237500, '2022-11-20', 'completed', '11111111'),
        (default, 'Камера', 80000, 2, '2022-11-02', 'Камера Sony AS-7', 75000, '2022-09-14', 'completed', '11111111'),
        (default, 'Ковер', 10000, 5, '2022-11-26', 'Ковер турецкий', 0, '2022-11-24', 'canceled', '11111111'),
        (default, 'Тапки', 150, 100, '2022-11-20', 'Гучи-тапки', 135, '2022-11-16', 'completed', '11111111'),
        (default, 'Ламинат', 35000, 80, '2022-11-25', 'Напольное покрытие на 80 кв.м.', 0, '2022-11-24', 'canceled', '11111111'),
        (default, 'Бетономешалка', 5000000, 1, '2022-11-24', 'Мешает бетон', 0, '2022-11-24', 'canceled', '11111111'),
        (default, 'Монитор', 15000, 5, '2022-11-11', 'Монитор LG 25'''' 1920:1080 144 Hz', 15000, '2022-11-02', 'completed', '11111111');

create table roles
(
    id   integer     not null
        primary key
        constraint fkoxu306cef946ub0alay6phtf9
            references users,
    role varchar(10) not null
);

insert into public.roles (id, role)
values  (42, 'USER'),
        (47, 'USER'),
        (44, 'DIRECTOR'),
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
    activation_code varchar(100),
    role            varchar(255)
);

insert into public.users (email, password, id, username, active, activation_code, role)
values  ('sesaf23474@sopulit.com', '3245433241', 44, '214123432', true, null, null),
        ('hanici3366@xegge.com', '11111111', 45, '11111111', true, null, null),
        ('xogitib876@sunetoa.com', '22222222', 47, '22222222', true, null, null),
        ('hagom37236@lidely.com', '134567we34r', 42, '2453w6723456', true, null, null);