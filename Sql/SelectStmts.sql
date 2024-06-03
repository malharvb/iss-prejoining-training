-- Select all query
SELECT 
    *
FROM
    car;

-- Select limited number of rows
SELECT 
    part_name
FROM
    part
LIMIT 5;

-- Select using Where clause
SELECT 
    model, model_year
FROM
    car
WHERE
    company = 'Mahindra';

-- Select using AND & OR
SELECT 
    part_name
FROM
    part
WHERE
    car_id = 1 AND price > 300;
    
SELECT 
    order_date
FROM
    part_order
WHERE
    quantity > 50 OR total_price > 10000;
    
-- Select using Order by
SELECT 
    part_name, stock_quantity
FROM
    part
ORDER BY stock_quantity DESC;

-- Select using Like, Wildcard and Distinct
SELECT DISTINCT
    company
FROM
    car
WHERE
    company LIKE 'm%';
    
-- Select using In and Between

SELECT 
    model
FROM
    car
WHERE
    model_year IN (2018 , 2019);
    
SELECT 
    *
FROM
    part_order
WHERE
    order_date BETWEEN '2023-01-01' AND '2023-12-31';
    
-- Select using Group by and Having clauses
SELECT 
    company, COUNT(*) AS number_of_models
FROM
    car
GROUP BY company
HAVING COUNT(*) > 1;

SELECT 
    part_name, SUM(stock_quantity)
FROM
    part
GROUP BY part_name;

-- Other MySQL function
SELECT 
    AVG(price)
FROM
    part;
    
SELECT 
    CONCAT(company, ' ', model) AS full_name
FROM
    car;
    
SELECT 
    YEAR(order_date) AS order_year,
    SUM(total_price) AS total_sales
FROM
    part_order
GROUP BY YEAR(order_date);
