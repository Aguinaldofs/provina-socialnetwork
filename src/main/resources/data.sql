INSERT INTO USER(name, email, password) VALUES('Aguinaldo', 'aguinaldojunior@gec.inatel.br', '123456');

INSERT INTO CATEGORY(name, category) VALUES('Engenharia da Computação','Algoritmos');
INSERT INTO CATEGORY(name, category) VALUES('Engenharia de Software','Algoritmos');

INSERT INTO ITEM(name, url, voting, creationdate, category_id) VALUES('Prova Algoritmos 3', 'www.google.com','true', '2019-05-05 20:00:00', 1);

INSERT INTO ITEM(name, url, voting, creationdate, category_id) VALUES('Prova Algoritmos 2', 'www.facebook.com','false', '2020-03-07 02:00:00', 1);

INSERT INTO ITEM(name, url, voting, creationdate, category_id) VALUES('Prova Algoritmos 1', 'www.instagram.com','true', '2020-03-07 01:00:00', 2);

INSERT INTO COMMENT(text, user_id, item_id) VALUES('Prova muito boa', '1', '2'); 