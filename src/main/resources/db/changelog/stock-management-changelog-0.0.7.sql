ALTER TABLE stock_management.customer 
ADD COLUMN username varchar UNIQUE;

ALTER TABLE stock_management.customer 
RENAME COLUMN name TO full_name;