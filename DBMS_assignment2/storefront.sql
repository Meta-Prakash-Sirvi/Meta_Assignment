CREATE DATABASE storefront;
USE storefront ; 

-- Assignemt 1
CREATE TABLE user(
user_id INT PRIMARY KEY , 
name VARCHAR(50) NOT NULL, 
mobile_no  BIGINT NOT NULL
);

CREATE TABLE product(
product_id INT PRIMARY KEY , 
product_name VARCHAR(50) NOT NULL , 
product_dec  VARCHAR(60)  NOT NULL , 
price  INT,
catgorie_id INT , 
avalible_stock INT
) ; 

drop table product ; 



CREATE TABLE catgories(
catgorie_name VARCHAR(50), 
categorie_id INT PRIMARY KEY , 
praent_id INT default NULL, 
FOREIGN KEY(praent_id) REFERENCES catgories(categorie_id) 
); 


CREATE TABLE product(
product_id INT PRIMARY KEY , 
product_name VARCHAR(50) NOT NULL , 
product_dec  VARCHAR(60)  NOT NULL , 
price  INT,
catgorie_id INT , 
avalible_stock INT , 
FOREIGN KEY(catgorie_id) references catgories(categorie_id)
) ; 





CREATE TABLE orders(
order_id INT PRIMARY KEY , 
Product_id INT, 
unit int , 
amount int , 
user_id INT, 
order_days INT,
order_date DATE, 
FOREIGN KEY(Product_id) REFERENCES product(product_id), 
FOREIGN KEY(user_id) REFERENCES user(user_id)
); 


CREATE TABLE shipping(
shipping_id INT PRIMARY KEY , 
order_id INT , 
user_id INT, 
status ENUM('shipped', 'return' ,'pending') ,
adderss text, 
city varchar(20) ,
country varchar(20),
zipcode INT,
foreign key (order_id) references orders(order_id),
foreign key(user_id) references user(user_id) 
); 


CREATE TABLE image(
image_id INT PRIMARY KEY , 
product_id INT , 
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
(10001, 11111, 2, 40,  103, 11 , 20250313), 
(10002, 12324, 1, 45,  102, 5  , 20250319), 
(10003, 10325, 1, 20 , 103, 25 , 20250228), 
(10004, 12483, 1, 30 , 100, 11 , 20250313) ; 


INSERT INTO shipping(shipping_id, order_id, user_id, status ,adderss,city,country,zipcode)
VALUES
(121, 10001, 103, 'shipped', "m1 office",  "jaipur", "india", 302233),
(131, 10002, 102, 'pending',  "sector 10" ,  "mumbai", "india", 349221 ),  
(141, 10003, 103, 'return',   'sector 11',    'jaipur', 'india', 345454),
(142, 10004, 100, 'shipped',  'mahveer nagar' , 'pali' ,'india', 306702); 


INSERT INTO image(image_id , product_id , isiamge)VALUES
(22 , 11111, 'y'), 
(44 ,12483 ,'n' ),
(33 ,12324 , 'y' ); 


SELECT * FROM user ; 
SELECT * FROM catgories ; 
SELECT * FROM product ; 
SELECT * FROM orders ; 



-- Display Id, Title, Parent Category Title of all the leaf Categories (categories which are not parent of any other category)
SELECT categorie_id, catgorie_name FROM catgories WHERE praent_id  IN (SELECT DISTINCT praent_id FROM catgories WHERE praent_id IS NOT NULL);

-- Display recently added products
SELECT product_id , product_name, price FROM product ORDER BY product_id DESC ;

-- Display products below inventory level (50)
SELECT product_id , product_name , avalible_stock FROM product WHERE avalible_stock <50 ; 


-- Display Product Title, Price & Description which falls into particular category Title (i.e. “Mobile”)
SELECT product_name ,product_dec, price FROM product WHERE catgorie_id IN(SELECT categorie_id FROM catgories WHERE catgorie_name='Mobile') ; 


-- Display Id, Title, Category Title, Price of the products which are Active and recently added products should be at top.
SELECT p.product_id , p.product_name,c.catgorie_name, p.price
FROM product  p 
INNER join catgories c ON p.catgorie_id = c.categorie_id
WHERE product_id 
IN (SELECT product_id FROM image WHERE isiamge ='y') ; 





-- Assignemt 3

-- Display Recent 50 Orders placed (Id, Order Date, Order Total).
SELECT order_id ,order_date ,amount FROM orders  ORDER BY order_date DESC LIMIT 50 ;  

--  Display 10 most expensive Orders.
SELECT  order_id ,product_id , amount FROM Orders ORDER BY amount DESC LIMIT 10 ; 


-- Display all the Orders which are placed more than 10 days old and one or more items from those orders are still not shipped.
SELECT * FROM orders WHERE order_days>10 AND order_id IN(SELECT order_id FROM shipping WHERE status='pending');



-- Display list of shoppers which haven't ordered anything since last month.
SELECT name FROM user WHERE user_id NOT IN(SELECT user_id FROM orders WHERE order_days<=30);

-- Display list of shopper along with orders placed by them in last 15 days.
SELECT u.name, o.order_id, o.order_days
FROM user u
INNER JOIN orders o ON u.user_id = o.user_id
WHERE o.order_days <= 15;


-- Display list of order items which are in “shipped” state for particular Order Id (i.e.: 1020))
SELECT order_id, product_id FROM orders WHERE order_id = 10001 AND order_id IN(SELECT order_id FROM shipping WHERE status='shipped');


-- Display list of order items along with order placed date which fall between Rs 20 to Rs 50 price
 
SELECT product_name FROM product WHERE product_id IN(SELECT product_id FROM orders WHERE amount between 20 AND 50); 




