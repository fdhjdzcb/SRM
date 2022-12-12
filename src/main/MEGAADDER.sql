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
    id            serial                                                     not null
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

create table past_orders
(
    id            int                                                        not null
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

create table news
(
    author    varchar(30),
    text      varchar(300),
    pub_date  date,
    title     varchar(50),
    id        integer,
    new_image varchar(100)
);

insert into users (email, password, username, active, activation_code, name, surname)
values ('hejaca8912@diratu.com', '$2a$08$NMnQSdJMp8ZHc5YoVWWFVu99AqJyh59gRqxOCpV0BQ.t6uWZPIaTC', '33333333', true, null,
        'Амир', 'Галимуллин'),
       ('vitina8738@edinel.com', '$2a$08$9EYtnzUCj/kMLzbjltahfuDVRAXq/nnD1kl.DSX3VHHi7eEwkhXVW', '11111111', true, null,
        'Админ', 'Админов'),
       ('kirot35533@eilnews.com', '$2a$08$L4o5a/.EA1hn2C/XH.lf6u82h8.P2ZRNMnVtp3PkFooe5Tbt9D3jW', 'director', true,
        null, 'Директор', 'Директоров'),
       ('jihan26550@dmonies.com', '$2a$08$4lfEB9gcl3uJl6l0QO2uX.txLix9wOfCLZJ/EFEZRSJLLWPe4Qw9m', 'kekekeke', true,
        null, 'Матвей', 'Емельянов'),
       ('xofoj80757@dmonies.com', '$2a$08$ZPOPBtzIhueEckEB8.l6J.I/XIFHzbl0MmzqQV40G9nNoy/UQZ4qK', 'lolololo', false,
        null, 'Ivan', 'Николаев'),
       ('531bd35b4a@emailkom.live', '$2a$08$vAdtC5vbX2CyvYx2eueQj.E49mLcuuNaNhIWMFsV2w4.rwv5y/knK', 'arcasha1', true,
        null, 'Аркадий', 'Цой'),
       ('7a4b1cfd6e@emailkom.live', '$2a$08$c1jliUXU5HBABAITMfZR.eEtYMYQOYtt6F/MkN811SBeJQNf4DJym', 'Aoa226_1', false,
        null, 'Jack', 'Jobs'),
       ('cevag47123@dmonies.com', '$2a$08$9NFc7MFPHuwXIA3NOBvFwuGMKQqCvJ/XLCtXuJsyraFI5C4nyyBEa', 'nick_13!', false,
        null, 'Николай', 'Басков'),
       ('gwevsd@mailna.co', '$2a$08$lTyuQidwLkORxebQiyXUjuZY1dlfNhMGf6TQXGhfXvjhCjdE9O0t6', 'petrovich', true, null,
        'Геннадий', 'Зюганов'),
       ('2brsd6mz@mailto.plus', '$2a$08$Ann6el62hnsvt0fefecimepQI9P0h4e9rzJo2KYamuFxNoxvUI9ES', 'baragoze', true, null,
        'Дмитрий', 'Медведев'),
       ('mh5ert245@pamaweb.com', '$2a$08$wXWbt7z/2O1dtdevF5Yer.QHSVWiYl50finvb.klPzXjwQN02aOc.', 'mambo-banano', true,
        null, 'Александр', 'Беркович'),
       ('zoy@stud.etu.ru', '$2a$08$hTYbzisXY2dCfkNmtwttB.7WmxWDP97qQT5NlDRbmkY3HOtODZIoa', 'zolushka', true, null,
        'Антонина', 'Журавлева');

insert into roles (id, role)
values (1, 'USER'),
       (2, 'ADMIN'),
       (3, 'DIRECTOR'),
       (4, 'ADMIN'),
       (5, 'USER'),
       (6, 'DIRECTOR'),
       (7, 'USER'),
       (8, 'DIRECTOR'),
       (9, 'USER'),
       (10, 'USER'),
       (11, 'USER'),
       (12, 'ADMIN');

insert into orders (id, product_name, max_price, count, expected_date, description, real_price, real_date,
                           author, is_approved, provider)
values (35, 'Радио', 15000, 1, '2022-11-23', 'Скучно в офисе', 12990, '2022-11-29', 'nick_13!', true, 'Поставщик 1'),
       (36, 'товар', 200, 2, '2022-12-17', 'товар', 0, null, 'petrovich', false, null),
       (37, 'Зип-пакеты', 199, 1000, '2022-12-19', 'Пакеты для упаковки товара', 0, null, '11111111', false, null),
       (38, 'Курсовая CALS', 200, 4, '2022-12-10', 'Курсовая по CALS', 0, null, '33333333', false, null),
       (39, 'RTX 2080', 20800, 10, '2022-12-17', 'Видеокарта NVIDIA Geforce RTX 2080 16GB', 20490, '2022-12-15',
        'lolololo', true, 'Поставщик 12'),
       (40, 'Лексус 2002 года', 1500000, 3, '2023-02-09', 'Для встречи делегации из Японии', 0, null, 'kekekeke', true,
        null),
       (41, 'Кружка', 7000, 10, '2022-12-31', 'Подарок акционерам на новый год', 0, null, 'arcasha1', true, null),
       (42, 'Путевка в Сочи', 5000, 50, '2023-01-15', 'Корпоратив в Сочи', 3500, '2023-01-13', 'arcasha1', true,
        'Поставщик 2'),
       (43, 'Шариковые ручки', 50, 350, '2022-12-24', 'Заканчиваются ручки в офисе', 0, null, 'petrovich', false, null),
       (44, 'Холодильник', 70000, 1, '2022-12-19', 'Для обедов сотрудников', 0, null, 'zolushka', false, null),
       (45, 'Монитор', 20000, 3, '2022-12-15', 'Для новых рабочих мест; разрешение 21:9', 0, null, 'mambo-banano', true,
        null),
       (46, 'Офисный стул', 10000, 3, '2022-12-15', 'Для новых рабочих мест; на колесиках', 7990, '2022-12-17',
        '11111111', true, 'Поставщик 5'),
       (47, 'Курсовая ООП', 1999, 1, '2022-12-29', 'Курсовая по ООП', 0, null, 'baragoze', false, null);

insert into past_orders (id, product_name, max_price, count, expected_date, description, real_price, real_date,
                                status, author, provider)
values (1, 'Зубная паста', 150, 100, '2022-12-26', 'Зубная паста чистит зубы щеткой', 0, null, 'canceled', '11111111',
        null),
       (2, 'База данных', 1000000, 1, '2022-12-30', '2кк строк пользователей яндекса', 0, null, 'canceled',
        'mambo-banano', null),
       (3, '7 Лабораторная работа', 800, 2, '2022-12-12', '7 ЛР по ИКСиС', 750.9, '2022-12-22', 'completed', 'kekekeke',
        'Поставщик 2'),
       (4, 'Функционал системы', 1000, 2, '2022-11-20', 'Функционал системы AMIR', 990.82, '2022-12-16', 'completed',
        'arcasha1', 'Поставщик 1'),
       (5, 'Монитор', 15000, 5, '2022-10-23', 'Монитор LG 25'' 1920:1080 144 Hz', 15000, '2022-11-02', 'completed',
        'zolushka', 'Поставщик 3'),
       (6, 'RTX 2080', 20800, 1289, '2022-08-20', 'Видеокарта NVIDIA Geforce RTX 2080 16GB', 0, null, 'canceled',
        'zolushka', null),
       (7, 'Камера', 80000, 2, '2022-05-02', 'Камера Sony AS-7', 75000, '2022-09-14', 'canceled', '33333333',
        'Поставщик 5'),
       (8, 'Тапки', 150, 100, '2022-09-24', 'Гучи-тапки', 135, '2022-11-16', 'completed', 'director', 'Поставщик 6'),
       (9, 'Качели', 250000, 3, '2022-12-11', 'Детские качели для качания во дворе', 237500, '2022-11-20', 'completed',
        'nick_13!', 'Поставщик 2'),
       (10, 'Монитор', 15000, 5, '2022-10-30', 'Монитор LG 25'' 1920:1080 144 Hz', 0, null, 'canceled', 'Aoa226_1',
        null),
       (11, 'Качели', 250000, 3, '2022-12-05', 'Детские качели для качания во дворе', 0, null, 'canceled', 'baragoze',
        null),
       (12, 'Бетономешалка', 5000000, 1, '2022-11-24', 'Мешает бетон', 0, null, 'canceled', 'petrovich', null),
       (13, 'Стол', 5500, 20, '2022-12-09', 'Письменный стол', 0, null, 'canceled', 'director', null),
       (14, '61 серия Аватара', 1000, 61, '2022-11-17', 'Культовое аниме', 0, null, 'canceled', 'kekekeke', null),
       (15, 'Клавиатура', 200, 4, '2022-12-10', 'Печатать символы', 193.08, '2022-12-06', 'completed', 'lolololo',
        'Поставщик 10'),
       (16, 'Ноутбук', 150000, 3, '2022-12-18', 'Для работы из кафе', 137500, '2022-11-20', 'completed', '33333333',
        'Поставщик 4'),
       (17, 'Мышь беспроводная', 1000, 3, '2022-11-11', 'Для работы из кафе с комфортом', 0, null, 'canceled',
        '11111111', null),
       (18, 'Стул', 9000, 11, '2022-12-11', 'Стул для сидения', 0, null, 'canceled', 'mambo-banano', null),
       (19, 'Одноразовая посуда', 110, 1000, '2022-11-24', 'Экономим на воде для мытья посуды', 0, null, 'canceled',
        'Aoa226_1', null),
       (20, 'Гарнитура', 2300, 9, '2022-11-09', 'Для видеоконференций', 0, null, 'canceled', 'petrovich1', null),
       (21, 'Микроволновка', 8700, 2, '2022-12-15', 'Чтобы волновались не только сотрудники', 0, null, 'canceled',
        'baragoze', null),
       (22, 'Клей общего назначения', 850, 38, '2022-12-08', 'Клеить коробки', 193.08, '2022-12-08', 'completed',
        'nick_13!', 'Поставщик 2'),
       (23, 'Гвозди', 10, 40, '2022-11-12', 'Для крепления доски почета к стене', 0, null, 'canceled', 'arcasha1',
        null),
       (24, 'Антикварные часы', 30000, 1, '2022-12-03', 'Для атмосферы в офисе', 28680, '2022-12-04', 'completed',
        'kekekeke', 'Поставщик 9'),
       (25, 'Матовые лампочки', 200, 37, '2022-12-01', 'Для атмосферы в офисе', 0, null, 'canceled', 'director', null),
       (26, 'Коллекционняе игрушки', 120000, 15, '2022-11-14', 'Для атмосферы в офисе', 0, null, 'canceled',
        'mambo-banano', null),
       (27, 'Пакет программ Microsoft Office', 3990, 16, '2022-12-20', 'Для новых ноутбуков', 0, null, 'canceled',
        '33333333', null),
       (28, 'Журналы с кроссвордами', 300, 10, '2022-12-12', 'Чтобы не скучать на ежедневных митингах', 270,
        '2022-12-12', 'completed', 'zolushka', 'Поставщик 1'),
       (29, 'Инструкция для оригами-журавля', 500, 3, '2022-12-15', 'Для проведения мастер-класса', 0, null, 'canceled',
        'nick_13!', null),
       (30, 'Учебник русского языка в 2-х частях', 2300, 2, '2022-12-19', 'Для стажера из Японии', 2190, '2022-12-18',
        'completed', 'Aoa226_1', 'Поставщик 3'),
       (31, 'Макет логотипа компании', 20000, 1, '2022-12-10', 'Для атмосферы в офисе', 0, null, 'canceled', 'lolololo',
        null),
       (32, 'Неоновая вывеска с названием компании', 17000, 3, '2022-12-06', 'Для атмосферы в офисе', 0, null,
        'canceled', 'baragoze', null),
       (33, 'Футболка с логотипом компании', 2450, 70, '2022-12-30', 'Вместо препий сотрудникам', 0, null, 'canceled',
        '11111111', null),
       (34, 'Картонная каробка XL', 420, 4, '2022-12-17', 'Уволенным сотрудникам для вещей', 415, '2022-12-08',
        'completed', 'petrovich', 'Поставщик 7');

insert into news (author, text, pub_date, title, id, new_image)
values  ('11111111', 'Напоминаем вам, что для лучшей производительности нужно и уметь правильно проводить свой отдых. Выключаем телевизор в субботу, в воскресенье выпускаем свой стартап!', '2022-12-12', 'Выходные в декабре', 34, '56e863e8-8aca-442d-9d04-57edd8185b9b.weekend.png'),
        ('Aoa226_1', 'Кот Василий блестяще проявил свои таланты в поддержке наших сотрудников в трудные времена. И даже почти не помешал их работе!', '2022-12-12', 'Сотрудник месяца', 35, '0b2b74e4-452d-4854-9b97-f24cc00cc8d8.worker_of_the_month.jpg'),
        ('arcasha1', 'Напоминаем всем нашим сотрудникам, что вторник - официальный день пиццы в нашей компании. Каждому достанется по своей личной пепперони! Приятного аппетита!', '2022-12-12', 'День пиццы', 36, '73250063-ad26-473f-a98b-771715515985.pepperoni.jpg'),
        ('director', 'Каждый из нас вовсю отдается, работая на нашу компанию. И нет большей награды, чем в перерыв пойти в буфет и съесть классный пончик с глазурью. Какую глазурь вы бы выбрали сегодня? К сожалению, сегодня вы не выберете... Антон, верни пончики! Мы знаем, что это сделал ты!', '2022-12-12', 'Таинственный вор пончиков раскрыт', 37, '708823da-7a6d-4281-9bec-f6f8427bb00a.donut_thief.jpg'),
        ('zolushka', 'Дорогие сотрудники! Напоминаем вам, что работа должна выполняться в том объеме, в котором вас просит начальство! Иначе вас настигнет участь Антона! С уважением, ваш директор, Миленин А.Ю.', '2022-12-12', 'Что будет, если не работать?', 38, '73fec3a3-6970-4933-be0e-3e6a6423f5cc.coffin_dance.jpg'),
        ('lolololo', 'Напоминаем нашим дорогим сотрудникам, что собрание литературного клуба уже в этот четверг. Мы искренне надеемся, что все уже прочитали "Война и мир" Льва Николаевича Толстого. ', '2022-12-12', 'Собрание книжного клуба в четверг', 39, 'f737f027-4ba7-4d76-8859-17e70238e420.literature_club.png'),
        ('Aoa226_1', 'Мы рады сообщить нашим сотрудникам, что мы вернули кулеры обратно в офисы. Теперь вам не придется выпрашивать воду у наших соседей по зданию!', '2022-12-12', 'Вода снова в ваших офисах!', 40, 'f5e953b1-f6ed-4185-b0a3-e18c10023832.kuler.jpg'),
        ('petrovich', 'Семинар по межличностным коммуникациям.', '2022-12-12', 'Новый семинар уже в эту пятницу!', 41, '997dadb6-fdb7-47e9-9031-66d0f30712f0.seminar.jpg');