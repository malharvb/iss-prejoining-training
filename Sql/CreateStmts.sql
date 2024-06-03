-- Create and starting using new database
CREATE DATABASE car_part_management_system;
USE car_part_management_system;

-- Create schema tables
CREATE TABLE car (
    car_id INT AUTO_INCREMENT PRIMARY KEY,
    company VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL,
    model_year YEAR NOT NULL
);

CREATE TABLE manufacturer (
    manufacturer_id INT AUTO_INCREMENT PRIMARY KEY,
    manufacturer_name VARCHAR(100) NOT NULL,
    contact_info VARCHAR(255)
);

CREATE TABLE part (
    part_id INT AUTO_INCREMENT PRIMARY KEY,
    part_name VARCHAR(255) NOT NULL,
    part_number VARCHAR(255) UNIQUE NOT NULL,
    car_id INT NOT NULL,
    manufacturer_id INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    stock_quantity INT NOT NULL,
    CONSTRAINT fk_part_car FOREIGN KEY (car_id) REFERENCES car(car_id),
    CONSTRAINT fk_part_manufacturer FOREIGN KEY (manufacturer_id) REFERENCES manufacturer(manufacturer_id)
);

CREATE TABLE part_order (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    part_id INT NOT NULL,
    order_date DATE NOT NULL,
    quantity INT NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_part_order_part FOREIGN KEY (part_id) REFERENCES part(part_id)
);

-- Create Duplicate table
CREATE TABLE part_backup SELECT * FROM
    part;

SELECT * FROM part_backup;

-- Create index
CREATE INDEX idx_car_car_id ON car (car_id);

-- Create view
CREATE VIEW part_order_2024 AS
    SELECT 
        *
    FROM
        part_order
    WHERE
        order_date BETWEEN '2024-01-01' AND '2024-12-31';
        
SELECT 
    *
FROM
    part_order_2024;

