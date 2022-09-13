USE lesson7;

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

INSERT INTO lesson7.users (username, password, enabled)
VALUES
    ('anastasia', '{bcrypt}anastasia', 1),
    ('olga', '{bcrypt}olga', 1);

INSERT INTO lesson7.authorities (username, authority)
VALUES
    ('anastasia', 'ROLE_ADMIN'),
    ('olga', 'ROLE_MANAGER');