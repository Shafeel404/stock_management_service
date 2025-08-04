CREATE TABLE IF NOT EXISTS stock_management.shop (
    id uuid NOT NULL,
    name varchar NOT NULL,
    address varchar NULL,
    phone_number varchar NULL,
    email varchar NULL,
    CONSTRAINT shop_pk PRIMARY KEY (id),
    CONSTRAINT shop_unique UNIQUE (name)
)
INHERITS (public.audit); 