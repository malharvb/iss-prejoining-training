-- Stored procedure is added using GUI in MySQL Workbench
-- Change delemiter to tell MySQL start of procedure block
DELIMITER //

CREATE PROCEDURE insert_new_part (
    IN partName VARCHAR(255),
    IN partNumber VARCHAR(255),
    IN carId INT,
    IN manufacturerId INT,
    IN partPrice DECIMAL(10, 2),
    IN stockQty INT
)
BEGIN
    DECLARE carExists INT;
    DECLARE manufacturerExists INT;

	-- Check if car id exists that there is an actual car for the id
	SELECT 
		COUNT(*)
	INTO carExists FROM
		car
	WHERE
		car_id = carId;

	-- Check if manufacturer id exists that there is an actual manufacturer for the id
	SELECT 
		COUNT(*)
	INTO manufacturerExists FROM
		manufacturer
	WHERE
		manufacturer_id = manufacturerId;

	-- If both car and manufacturer exist then insert the new part
    IF carExists = 1 AND manufacturerExists = 1 THEN
        INSERT INTO part (part_name, part_number, car_id, manufacturer_id, price, stock_quantity) 
        VALUES (partName, partNumber, carId, manufacturerId, partPrice, stockQty);
    ELSEIF carExists = 0 THEN
		-- Exception handling is performed using Signals. These allow for custom error codes and messages
        -- This thrown exception can be further handled using JDBC
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Car ID does not exist.';
	ELSE
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Manufacturer ID does not exist.';
    END IF;
END //

-- Change delimiter back to original 
DELIMITER ;


-- Call stored procedure 
CALL insert_new_part('Windshield', 'WS021', 1, 1, 2500.00, 50);

SELECT 
    *
FROM
    part;
    
DELETE FROM part WHERE part_name = "Windshield";

