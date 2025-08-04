CREATE TABLE IF NOT EXISTS public.audit (
	created_by varchar NOT NULL,
	created_at timestamp DEFAULT now() NOT NULL,
	last_modified_by varchar NULL,
	last_modified_at timestamp NULL,
	status int4 DEFAULT 1 NOT NULL
);

CREATE SCHEMA IF NOT EXISTS stock_management;

CREATE TABLE stock_management.m_role (
	status int4 DEFAULT 1 NOT NULL,
	id uuid NOT NULL,
	"name" varchar NOT NULL,
	description varchar NULL,
	CONSTRAINT m_role_pk PRIMARY KEY (id),
	CONSTRAINT m_role_unique UNIQUE (name)
)
INHERITS (public.audit);

INSERT INTO stock_management.m_role
(created_by, created_at, last_modified_by, last_modified_at, status, id, "name", description)
VALUES('admin', '2025-05-05 11:30:12.295', 'admin', NULL, 1, '245c68bf-71d6-499e-8df8-0973e4dc46fb'::uuid, 'Super Admin', 'Super Admin');

CREATE TABLE IF NOT EXISTS stock_management."user" (
	id uuid NOT NULL,
	username varchar NOT NULL,
	"password" varchar NOT NULL,
	role_id uuid NULL,
	email varchar NULL,
	phone_number varchar NULL,
	full_name varchar NULL,
	counter_id uuid NULL,
	CONSTRAINT user_pk PRIMARY KEY (id),
	CONSTRAINT user_unique UNIQUE (username)
)
INHERITS (public.audit);

INSERT INTO stock_management."user"
(created_by, created_at, last_modified_by, last_modified_at, status, id, username, "password", role_id, email, phone_number, full_name, counter_id)
VALUES('admin', '2025-05-05 11:24:28.688', 'admin', '2025-05-05 11:24:28.689', 1, '219cff46-9dd0-4b84-a1c3-53f2c2d523b9'::uuid, 'super.admin', '$2a$10$o7GIR4oYaQ5NityoHIET9.XcM4WPFfSQTtWguHS2vTI5jDI5hEDne', '245c68bf-71d6-499e-8df8-0973e4dc46fb'::uuid, 'super.admin@gmail.com', '9988776655', 'string', NULL);

CREATE TABLE stock_management.m_category (
	id uuid NOT NULL,
	"name" varchar NOT NULL,
	description varchar NULL,
	CONSTRAINT category_pk PRIMARY KEY (id),
	CONSTRAINT category_unique UNIQUE (name)
)
INHERITS (public.audit);

CREATE TABLE IF NOT EXISTS stock_management.counter (
	id uuid NOT NULL,
	counter_name varchar NOT NULL,
	counter_number int4 NOT NULL,
	CONSTRAINT counter_pk PRIMARY KEY (id),
	CONSTRAINT counter_unique UNIQUE (counter_name),
	CONSTRAINT counter_unique_1 UNIQUE (counter_number)
)
INHERITS (public.audit);

CREATE TABLE IF NOT EXISTS stock_management.inventory (
	id uuid NOT NULL,
	product_name varchar NOT NULL,
	product_code varchar NOT NULL,
	description varchar NULL,
	hsn_sac_code varchar NULL,
	category_id uuid NOT NULL,
	remaining_quantity int4 NOT NULL,
	total_quantity int4 NOT NULL,
	"month" varchar NULL,
	CONSTRAINT inventory_pk PRIMARY KEY (id),
	CONSTRAINT inventory_unique UNIQUE (product_code)
)
INHERITS (public.audit);

CREATE TABLE IF NOT EXISTS stock_management.inventory_commercials_mapping (
	id uuid NOT NULL,
	cost_price float8 NOT NULL,
	maximum_retail_price float8 NOT NULL,
	selling_price float8 NOT NULL,
	non_tax_bill_selling_price float8 NOT NULL,
	inventory_id uuid NULL,
	tax float8 NULL,
	CONSTRAINT inventory_commercials_mapping_pk PRIMARY KEY (id)
);

