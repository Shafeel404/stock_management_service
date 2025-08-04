CREATE TABLE IF NOT EXISTS stock_management.customer (
    id uuid PRIMARY KEY,
    name varchar NOT NULL,
    email varchar NOT NULL UNIQUE,
    phone_number varchar,
    password varchar NOT NULL,
    is_active boolean DEFAULT true,
    created_at timestamp DEFAULT now(),
    updated_at timestamp DEFAULT now()
); 