INSERT INTO customers (name, password, phone_number, age, email) VALUES
                                             ('Alia Chadwick',   '1111', '+380961528025', 21, 'abc@gmail.com'),
                                             ('Maxim Zimmerman', '1111', '+380965478025',   35, 'abc@email.com'),
                                             ('Mahima Partridge','f3dgdfg5fdh7fgdfCGdf4', '+380961575025',   65, 'tst@outlook.com'),
                                             ('Claude Andrew',   'f3dgdfg54ehf4fgdfd434df4', '+380961528025',   80, 'bgf@outlook.com'),
                                             ('Patryk Lopez',    'fddgdfgg4ehf4fgdfd434df4', '+380954528025',   27, 'ryd@outlook.com'),
                                             ('Terrell Ware',    'fddgdfgg4ehf4fgdfd434df4', '+380965557025',  25, 'hwh@outlook.com'),
                                             ('Silas Ritter',    'fddgdfgg3ehf477fgdfd434df4', '+380961564725',  33, 'hwh@outlook.com'),
                                             ('Korben Castillo', 'fddgdfgg3ehf477fgdfCGdf4', '+380647478025',  45, 'hwh@outlook.com'),
                                             ('Nate Gilbert',    'fddgdfgg3ehf477fgdfCGdf4', '+380961757025',  20, 'hwh@outlook.com'),
                                             ('Monty Baker',     'fd643677fgdfCGdf4', '+380961575754',  78, 'hwh@outlook.com');

INSERT INTO accounts (number, currency, balance, customer_id) VALUES
                                                                  ('678ce8f1-7fbc-495f-afb4-d1f469afdcd4', 'EUR', 2455, 1),
                                                                  ('593c74fe-ba9e-441f-8567-1a551e0962fc', 'USD', 242200,  1),
                                                                  ('3d3fe3b4-83ba-4756-a92e-36873d3c4ae7', 'UAH', 10000, 3),
                                                                  ('788fab3b-7367-4b8b-962a-af1085f36ae9', 'CHF',1436000, 5),
                                                                  ('e4de26a2-fea4-4544-b40d-0106a47383ce', 'GBP', 58980, 6),
                                                                  ('ee4b9b2e-6d20-4c76-aef5-32a8983cd784', 'USD', 45610, 2);
INSERT INTO employers (name, address) VALUES
                                             ('Samsung', 'Samsung street'),
                                             ('Facebook', 'Facebook street'),
                                             ('Google', 'Google street'),
                                             ('Apple', 'Apple street'),
                                             ('Amazon', 'Amazon street'),
                                             ('Anthony', 'street');

INSERT INTO employers_customers (customers_id, employers_id) values
                                                    (1 ,1 ),
                                                    (2 , 2),
                                                    (4 , 2),
                                                    (5 , 2),
                                                    (8 , 1),
                                                    (8 , 3),
                                                    (5 , 4),
                                                    (1 , 5),
                                                    (3 , 6),
                                                    (5, 6),
                                                    (6, 5),
                                                    (1, 2),
                                                    (10, 2),
                                                    (3,1 );