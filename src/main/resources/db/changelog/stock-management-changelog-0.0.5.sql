CREATE TABLE IF NOT EXISTS stock_management.customer_address (
    id uuid NOT NULL,
    customer_id uuid NULL,
    address_line1 varchar NOT NULL,
    address_line2 varchar NULL,
    city varchar NULL,
    state varchar NULL,
    postal_code varchar NULL,
    country varchar NULL,
    latitude float8 NULL,
    longitude float8 NULL,
    is_default boolean DEFAULT false,
    CONSTRAINT customer_address_pk PRIMARY KEY (id)
)
INHERITS (public.audit); 