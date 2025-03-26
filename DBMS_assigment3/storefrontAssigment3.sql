
-- Assignemt 1
CREATE TABLE user(
user_id INT PRIMARY KEY ,     -- PRIMARY KEY
name VARCHAR(50) NOT NULL,   -- NOT NULL
mobile_no  BIGINT NOT NULL   -- NOT NULL
);

CREATE TABLE product(
product_id INT PRIMARY KEY ,    -- PRIMARY KEY
product_name VARCHAR(50) NOT NULL ,      -- NOT NULL
product_dec  VARCHAR(60)  NOT NULL ,       -- NOT NULL
price  INT,
catgorie_id INT ,      
avalible_stock INT
) ; 

-- Write a command to remove Product table of the StoreFront database.
drop table product ; 



CREATE TABLE catgories(
catgorie_name VARCHAR(50), 
categorie_id INT PRIMARY KEY ,  -- PRIMARY KEY
praent_id INT default NULL,      -- default
FOREIGN KEY(praent_id) REFERENCES catgories(categorie_id) 
); 


CREATE TABLE product(
product_id INT PRIMARY KEY ,   -- PRIMARY KEY
product_name VARCHAR(50) NOT NULL ,    -- NOT NULL
product_dec  VARCHAR(60)  NOT NULL ,      -- NOT NULL
price  INT,
catgorie_id INT ,   -- FOREIGN KEY
avalible_stock INT , 
FOREIGN KEY(catgorie_id) references catgories(categorie_id)
) ; 





CREATE TABLE orders(
order_id INT PRIMARY KEY ,   -- PRIMARY KEY
Product_id INT,    -- FOREIGN KEY
unit int , 
amount int , 
user_id INT,  -- FOREIGN KEY
order_days INT,
order_date DATE, 
FOREIGN KEY(Product_id) REFERENCES product(product_id),  
FOREIGN KEY(user_id) REFERENCES user(user_id)
); 


CREATE TABLE shipping(
shipping_id INT PRIMARY KEY ,  -- PRIMARY KEY
order_id INT ,              -- FOREIGN KEY
user_id INT,            -- FOREIGN KEY
status ENUM('shipped', 'return' ,'pending' ,'cancelled') ,
adderss text, 
city varchar(20) ,
country varchar(20),
zipcode INT,
foreign key (order_id) references orders(order_id),
foreign key(user_id) references user(user_id) 
); 


CREATE TABLE image(
image_id INT PRIMARY KEY , -- PRIMARY KEY
product_id INT ,           -- FOREIGN KEY
isiamge varchar(1),
FOREIGN KEY(product_id) REFERENCES product(product_id) 
); 

-- Assignemt 2

INSERT INTO user
(user_id , name , mobile_no)
values 
(100, 'prakash sirvi' , 9004040), 
(101, 'mahesh', 74548574), 
(102, 'kp' , 5454549) , 
(103 , 'jasu' ,456565) ;


INSERT INTO catgories (categorie_id, catgorie_name, praent_id) VALUES
(10, 'Electronics', NULL),
(12, 'Laptops', 10),
(13, 'Mobile', 10),
(14, 'Clothing', NULL); 

INSERT INTO product (product_id,catgorie_id, product_name, product_dec, price, avalible_stock) VALUES
(11111, 12, 'MacBook Pro', 'Apple laptop',        20, 10),
(12508, 13, 'iPhone 14'  , 'Apple smartphone',    12, 5),
(10325, 14, 'T-shirt'    , 'Cotton T-shirt',      20, 50),
(12483, 12, 'DEll'       , 'dell laptop',         30, 100),
(12324, 14, 'Gloves'     , 'black - white combo', 45, 23);

INSERT INTO orders(order_id, Product_id,unit ,amount,user_id,order_days,order_date)VALUES
(10001, 11111, 1, 20,  103, 11 , 20250313), 
(10002, 12324, 1, 45,  102, 5  , 20250319), 
(10003, 10325, 1, 20 , 103, 25 , 20250228), 
(10004, 12483, 1, 30 , 100, 11 , 20250313), 
(10005 ,10325 ,1 ,20 , 101 ,2 , 20250325) ; 


INSERT INTO shipping(shipping_id, order_id, user_id, status ,adderss,city,country,zipcode)
VALUES
(121, 10001, 103, 'shipped', "m1 office",  "jaipur", "india", 302233),
(131, 10002, 102, 'pending',  "sector 10" ,  "mumbai", "india", 349221 ),  
(141, 10003, 103, 'return',   'sector 11',    'jaipur', 'india', 345454),
(142, 10004, 100, 'shipped',  'mahveer nagar' , 'pali' ,'india', 306702),
(143 , 10005 ,100 ,'cancelled', 'mahesh nagar', 'mumbai', 'india',394944);



INSERT INTO image(image_id , product_id , isiamge)VALUES
(22 , 11111, 'y'), 
(33 ,12324 , 'y' ),
(44 ,12483 ,'n' ),
(55 , 10325 ,'n'),
(66 ,12508 ,'y') ;





-- Assigment 2 - 

-- Display the list of products (Id, Title, Count of Categories) which fall in more than one Category
SELECT p.product_id, p.product_name ,Count(c.categorie_id) AS Category_counting
FROM product p
INNER JOIN catgories c ON
p.catgorie_id = c.categorie_id
GROUP BY p.product_id 
HAVING Count(c.categorie_id)>1; 

-- Display Count of products as per below price range:
SELECT Count(product_id) FROM product WHERE price between 0 AND 100 ; 
SELECT Count(product_id) FROM product WHERE price between 101 AND 500 ; 
SELECT Count(product_id) FROM product WHERE price > 500 ; 


-- Display the Categories along with number of products under each category.
SELECT c.categorie_id ,c.catgorie_name ,COUNT(p.catgorie_id) AS number_of_products
FROM catgories c
LEFT JOIN product p ON c.categorie_id = p.catgorie_id
GROUP BY c.categorie_id ; 








-- Assigment 3


-- Mark the products as Inactive which are not ordered in last 90 days.
UPDATE product 
SET avalible_stock=0 
WHERE product_id IN (SELECT product_id FROM orders WHERE order_days>90) ; 



-- Display top 20 Products which are ordered most in last 60 days along with numbers
SELECT p.product_name , Count(o.product_id) AS most_order_product
FROM product p
INNER JOIN orders o ON
p.product_id=o.product_id
WHERE order_days<=60
GROUP BY o.product_id 
ORDER BY Count(o.product_id) DESC LIMIT 20; 

-- Display Monthly sales revenue of the StoreFront for last 6 months. It should display each month’s sale.
SELECT FLOOR(o.order_days/30 ) AS month , SUM(o.amount) AS month_revenue
FROM orders o
WHERE o.order_days>=(SELECT MAX(order_days) FROM orders) - (6*30)
GROUP BY month ORDER BY month DESC;

-- Given a category search keyword, display all the Products present in this category/categories.
SELECT p.product_id , p.product_name AS Title
FROM product p
INNER JOIN catgories c ON
c.categorie_id = p.catgorie_id
WHERE c.catgorie_name LIKE '%laptops%';


-- Display top 10 Items which were cancelled most.
SELECT p.product_id ,p.product_name , Count(o.order_id) AS number_of_cancelled
FROM product p
INNER JOIN orders o 
ON p.product_id=o.product_id
INNER JOIN shipping s ON o.order_id=s.order_id
WHERE status='cancelled' group by o.order_id 
ORDER BY Count(o.order_id) DESC LIMIT 10;

-- Given a category search keyword, display all the Products present in this category/categories. 






-- Display Shopper’s information along with number of orders he/she placed during last 30 days.
SELECT u.user_id , u.name , Count(o.user_id) AS number_of_orders
FROM user u
INNER JOIN orders o ON
o.user_id=u.user_id
WHERE order_days<=30
GROUP BY user_id; 

-- Display the top 10 Shoppers who generated maximum number of revenue(spent) in last 30 days.
SELECT u.name , SUM(o.amount) AS total_spent
FROM user u
INNER JOIN orders o ON 
u.user_id = o.user_id
WHERE order_days<=30
GROUP BY u.user_id
ORDER BY SUM(o.amount) DESC
LIMIT 10;




-- Assignemt 4

CREATE TABLE state(
state_id INT PRIMARY KEY  , 
state_name VARCHAR(55) NOT NULL
); 

CREATE TABLE city(
city_id INT PRIMARY KEY , 
city_name VARCHAR(55) NOT NULL, 
state_id INT, 
FOREIGN KEY(state_id) REFERENCES state(state_id)
); 

CREATE TABLE zipcode(
zip_code INT PRIMARY KEY ,  
city_id INT, 
FOREIGN KEY (city_id) REFERENCES city(city_id)
); 


INSERT INTO state(state_id,state_name) VALUES
(1,'Rajasthan'), 
(2,'Gujrat'),
(3,'Haryana'); 

INSERT INTO city(state_id, city_id,city_name) VALUES
(1,1001,'jaipur'), 
(2,1002,'Gandhia Nagar'),
(3,1003,'Ambala'); 

INSERT INTO zipcode(zip_code, city_id) VALUES
(302033,1001), 
(486584,1002),
(243454,1003); 


SELECT s.state_name, c.city_name, z.zip_code
FROM city c
INNER JOIN zipcode z ON
z.city_id=c.city_id
INNER JOIN state s ON
s.state_id = c.state_id
ORDER BY s.state_name , c.city_name ; 




-- Assigment 5
-- Create a view displaying the order information (Id, Title, Price, Shopper’s name, Email, Orderdate, Status) with latest ordered items should be displayed first for last 60 days.
CREATE VIEW order_information AS
SELECT u.name ,u.mobile_no ,o.order_id , p.product_name AS Title ,o.order_date AS Orderdate ,o.amount  , s.Status
FROM orders o
INNER JOIN user u ON 
u.user_id=o.user_id 
INNER JOIN product p ON
o.product_id=p.product_id
INNER JOIN shipping s ON
s.order_id=o.order_id
WHERE order_days<=60
ORDER BY o.amount;



-- Use the above view to display the Products(Items) which are in ‘shipped’ state.
SELECT order_id ,Title, Status FROM order_information WHERE Status='shipped' ;

-- Use the above view to display the top 5 most selling products.
SELECT Title , Count(order_id) AS most_selling_product FROM order_information WHERE Status='shipped' GROUP BY order_id ORDER BY COUNT(order_id) DESC LIMIT 5 ;


