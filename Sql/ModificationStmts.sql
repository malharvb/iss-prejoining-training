-- Update statement using Where
UPDATE car 
SET 
    model_year = 2024
WHERE
    model = 'City';
    
SELECT 
    *
FROM
    car
WHERE
    model = 'City';
    
-- Delete statment using Where
INSERT INTO car (company, model, model_year) VALUES ('Mahindra', 'Scorpio', 2011);

DELETE FROM car 
WHERE
    model = 'Scorpio';