
-- Assignment 1
-- Create a function to calculate number of orders in a month. Month and year will be input parameter to function.
DELIMITER $$
CREATE function calculate_orders(order_month INT ,order_year INT)
RETURNS INT
DETERMINISTIC
BEGIN
  DECLARE numbers_of_orders INT;
    select count(order_id) INTO numbers_of_orders FROM orders WHERE Month(order_date) = order_month AND YEAR(order_date) = order_year; 
    return numbers_of_orders; 
END$$
DELIMITER $$


-- Create a function to return month in a year having maximum orders. Year will be input parameter.
DELIMITER $$
CREATE FUNCTION calculate_month(order_year INT)
RETURNS INT
DETERMINISTIC
BEGIN 
	DECLARE max_months INT;
    select  Month(order_date) INTO max_months 
    FROM orders 
    WHERE YEAR(order_date)= order_year
    GROUP BY Month(order_date)
    ORDER BY count(order_id) DESC
    LIMIT 1 ;
    return max_months ;
END $$
DELIMITER $$




-- Create a Stored procedure to retrieve average sales of each product in a month. Month and year will be input parameter to function.
DELIMITER $$
CREATE PROCEDURE avg_sales(IN order_month INT , IN order_year INT)
BEGIN
SELECT p.product_name , AVG(o.amount) AS Sales , order_month
FROM orders o
INNER JOIN product p ON p.product_id = o.product_id
WHERE Month(order_date) = order_month
AND YEAR(order_date) = order_year
group by p.product_id; 

END $$
DELIMITER ; 



-- Create a stored procedure to retrieve table having order detail with status for a given period. Start date and end date will be input parameter. Put validation on input dates like start date is less than end date. If start date is greater than end date take first date of month as start date.
DELIMITER $$
CREATE PROCEDURE detail(IN start_date  date , IN end_date date )
BEGIN
		IF start_date>end_date THEN
		SET start_date = DATE_FORMAT(NOW(), "%Y-%m-01");
		END IF;
	SELECT o.order_id,o.product_id,  o.amount, s.status
    FROM orders o
    INNER JOIN shipping s ON
    o.order_id = s.order_id
    WHERE order_date between start_date AND end_date ; 
END $$
DELIMITER ;



-- Identify the columns require indexing in order, product, category tables and create indexes.
CREATE INDEX index_product ON product(product_id) ; 
CREATE INDEX index_category ON catgories(categorie_id) ;
CREATE INDEX index_order ON orders (order_id) ; 
 
