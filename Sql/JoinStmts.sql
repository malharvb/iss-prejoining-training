-- Inner join
SELECT 
    manufacturer_name, part_number
FROM
    manufacturer AS m
        INNER JOIN
    part AS p ON m.manufacturer_id = p.manufacturer_id;
    
-- Left join
SELECT 
    part_name, part_number
FROM
    part p
        LEFT JOIN
    part_order po ON p.part_id = po.part_id
WHERE
    po.order_id IS NULL;
    
-- Right join
SELECT 
    *
FROM
    part p
        RIGHT JOIN
    part_order po ON p.part_id = po.part_id;
    
-- Full join using Union
SELECT 
    part_name, part_number
FROM
    part p
        LEFT JOIN
    part_order po ON p.part_id = po.part_id 
UNION SELECT 
    part_name, part_number
FROM
    part p
        RIGHT JOIN
    part_order po ON p.part_id = po.part_id;