USE final;

CREATE TABLE users (
                       username varchar(15),
                       password varchar(100),
                       enabled tinyint(1),
                       PRIMARY KEY (username)
) ;

CREATE TABLE authorities (
                             username varchar(15),
                             authority varchar(25),
                             FOREIGN KEY (username) references users(username)
) ;

INSERT INTO users (username, password, enabled)
VALUES
    ('admin', '{bcrypt}$2a$12$GSXJLWk5Tj3TGFAno8RVs.Ck031Ppgp9gM75ZjtIvEjKd5ZkfWU4y', 1),
    ('customer1', '{bcrypt}$2a$12$46.CYgPMFSDaDBuMSR6mW.WcrzyJJBmi2TXRigkmIQhLPyiQkiRKC', 1),
    ('customer2', '{bcrypt}$2a$12$6fvKq4Ewv4OJTfEf9FQQDu4WcHfrXT.B1Textbz6H/An4HcFc9RkO', 1);

INSERT INTO authorities (username, authority)
VALUES
    ('admin', 'ROLE_ADMIN'),
    ('customer1', 'ROLE_CUSTOMER'),
    ('customer2', 'ROLE_CUSTOMER');
CREATE TABLE products (
                          id INT NOT NULL AUTO_INCREMENT,
                          title VARCHAR(45) NOT NULL,
                          cost INT NOT NULL,
                          PRIMARY KEY (`id`));

CREATE TABLE user_product (
                              user_name varchar(15) NOT NULL,
                              product_id int NOT NULL,
                              quality int NOT NULL,
                              PRIMARY KEY (user_name, product_id),
                              FOREIGN KEY (user_name) REFERENCES users(username),
                              FOREIGN KEY (product_id) REFERENCES products(id));