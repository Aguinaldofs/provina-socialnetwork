INSERT INTO USERS(name, email, password) VALUES('Aguinaldo', 'aguinaldojunior@gec.inatel.br', '$2a$10$gjhp5CWxCl/tOUosLTwrMOt/H45h1/0hJUH6CdixQhX9AAw/XktRG');

INSERT INTO CATEGORY(name, category) VALUES('Engenharia da Computação','Algoritmos');
INSERT INTO CATEGORY(name, category) VALUES('Engenharia de Software','Algoritmos');

INSERT INTO ITEM(name, url, creationdate, category_id) VALUES('Prova Algoritmos 3', 'www.google.com', '2019-05-05 20:00:00', 1);

INSERT INTO ITEM(name, url, creationdate, category_id) VALUES('Prova Algoritmos 2', 'www.facebook.com', '2020-03-07 02:00:00', 1);

INSERT INTO ITEM(name, url, creationdate, category_id) VALUES('Prova Algoritmos 1', 'www.instagram.com', '2020-03-07 01:00:00', 2);

INSERT INTO COMMENT(text, user_id, item_id) VALUES('Prova muito boa', '1', '2'); 

INSERT INTO UPVOTE(status, user_id, item_id) VALUES('UPVOTE', '1','1');