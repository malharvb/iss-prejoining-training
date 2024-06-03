INSERT INTO car (company, model, model_year) VALUES
('Maruti Suzuki', 'Swift', 2020),
('Hyundai', 'Creta', 2021),
('Tata Motors', 'Nexon', 2019),
('Mahindra', 'XUV500', 2018),
('Honda', 'City', 2022),
('Kia', 'Seltos', 2021),
('Toyota', 'Fortuner', 2020),
('Ford', 'EcoSport', 2019),
('Renault', 'Duster', 2018),
('MG', 'Hector', 2022),
('Volkswagen', 'Polo', 2020),
('Skoda', 'Superb', 2021),
('Nissan', 'Magnite', 2022),
('Jeep', 'Compass', 2020),
('Fiat', 'Punto', 2019),
('Maruti Suzuki', 'Baleno', 2021),
('Hyundai', 'Venue', 2020),
('Tata Motors', 'Harrier', 2022),
('Mahindra', 'Thar', 2021),
('Honda', 'Amaze', 2020);

INSERT INTO manufacturer (manufacturer_name, contact_info) VALUES
('Bosch', 'bosch@example.com'),
('Denso', 'denso@example.com'),
('Delphi', 'delphi@example.com'),
('Valeo', 'valeo@example.com'),
('Continental', 'continental@example.com'),
('Minda Industries', 'minda@example.com'),
('Spark Minda', 'spark_minda@example.com'),
('Sundaram-Clayton', 'sundaram@example.com'),
('TVS Group', 'tvs_group@example.com'),
('Lumax', 'lumax@example.com'),
('Varroc', 'varroc@example.com'),
('Amtek', 'amtek@example.com'),
('JBM Group', 'jbm_group@example.com'),
('Subros', 'subros@example.com'),
('WABCO', 'wabco@example.com');

INSERT INTO part (part_name, part_number, car_id, manufacturer_id, price, stock_quantity) VALUES
('Brake Pad', 'BP001', 1, 1, 500.00, 100),
('Oil Filter', 'OF002', 2, 2, 300.00, 200),
('Air Filter', 'AF003', 3, 3, 200.00, 150),
('Fuel Pump', 'FP004', 4, 4, 1500.00, 75),
('Spark Plug', 'SP005', 5, 5, 100.00, 300),
('Battery', 'BT006', 6, 6, 4500.00, 50),
('Alternator', 'AL007', 7, 7, 8000.00, 30),
('Radiator', 'RD008', 8, 8, 7000.00, 40),
('Clutch', 'CL009', 9, 9, 3500.00, 60),
('Shock Absorber', 'SA010', 10, 10, 6000.00, 20),
('Exhaust', 'EX011', 11, 11, 9000.00, 25),
('Headlight', 'HL012', 12, 12, 2500.00, 80),
('Tail Light', 'TL013', 13, 13, 2200.00, 90),
('Wiper Blade', 'WB014', 14, 14, 150.00, 400),
('Tyre', 'TY015', 15, 15, 5000.00, 100),
('Brake Pad', 'BP016', 16, 1, 550.00, 120),
('Oil Filter', 'OF017', 17, 2, 310.00, 180),
('Air Filter', 'AF018', 18, 3, 210.00, 160),
('Fuel Pump', 'FP019', 19, 4, 1550.00, 70),
('Spark Plug', 'SP020', 20, 5, 110.00, 320),
('Battery', 'BT011', 9, 6, 5500.00, 50),
('Alternator', 'AL021', 10, 7, 10000.00, 30),
('Radiator', 'RD031', 11, 8, 6000.00, 40);


INSERT INTO part_order (part_id, order_date, quantity, total_price) VALUES
(1, '2023-01-10', 10, 5000.00),
(2, '2023-02-15', 20, 6000.00),
(3, '2023-03-12', 15, 3000.00),
(4, '2023-04-20', 5, 7500.00),
(5, '2023-05-25', 30, 3000.00),
(6, '2023-06-18', 8, 36000.00),
(7, '2023-07-22', 3, 24000.00),
(8, '2023-08-10', 4, 28000.00),
(9, '2023-09-14', 6, 21000.00),
(10, '2023-10-19', 2, 12000.00),
(11, '2023-11-11', 5, 45000.00),
(12, '2023-12-08', 10, 25000.00),
(13, '2024-01-15', 9, 19800.00),
(14, '2024-02-20', 40, 6000.00),
(15, '2024-03-05', 10, 50000.00),
(16, '2024-04-10', 15, 8250.00),
(17, '2024-05-15', 20, 6200.00),
(18, '2024-06-12', 25, 5250.00),
(19, '2024-07-20', 30, 46500.00),
(20, '2024-08-25', 35, 7700.00);

    
-- Insert into a table already existing data from another table
CREATE TABLE part_id_info (
	part_number VARCHAR(255) PRIMARY KEY,
	part_name VARCHAR(255) NOT NULL
);

INSERT INTO part_id_info SELECT part_number, part_name FROM part;

SELECT * FROM part_id_info;
