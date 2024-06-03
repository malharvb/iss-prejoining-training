-- Alter table to add Default constraint
ALTER TABLE part_order
ALTER order_date SET DEFAULT (CURRENT_DATE);

-- Alter table to add Check constraint
ALTER TABLE car
ADD CONSTRAINT chk_model_year CHECK (model_year <= 2024);

-- Alter table to add column in table
ALTER TABLE car ADD COLUMN vin VARCHAR(255);

-- Alter table to add column in table
ALTER TABLE car DROP COLUMN vin;

-- Drop table
DROP TABLE part_backup;

-- Truncate table i.e. remove all contents but keep schema
TRUNCATE TABLE part_backup;