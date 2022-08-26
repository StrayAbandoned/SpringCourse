CREATE SCHEMA `lesson6` ;
CREATE TABLE lesson6.users (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(15),
  PRIMARY KEY (id)
);

CREATE TABLE lesson6.products (
  id int NOT NULL AUTO_INCREMENT,
  title varchar(15) UNIQUE,
  cost int NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE lesson6.user_product (
  user_id int NOT NULL,
  product_id int NOT NULL,
  PRIMARY KEY (user_id, product_id),
  FOREIGN KEY (user_id) REFERENCES lesson6.users(id),
  FOREIGN KEY (product_id) REFERENCES lesson6.products(id));