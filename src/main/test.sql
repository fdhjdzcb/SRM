create table past_orders
(
    id            integer generated always as identity,
    product_name  varchar(100)     default 'Нет названия'::character varying not null,
    max_price     double precision default 0                                 not null,
    count         integer          default 0                                 not null,
    expected_date date             default CURRENT_DATE                      not null,
    description   varchar(100),
    real_price    double precision default 0,
    real_date     date             default CURRENT_DATE
);

comment on column orders.id is 'ID заказа';

comment on column orders.product_name is 'Наименование товара';

comment on column orders.max_price is 'Цена';

comment on column orders.count is 'Количество';

comment on column orders.expected_date is 'Ожидаемая дата доставки';

alter table orders
    owner to postgres;